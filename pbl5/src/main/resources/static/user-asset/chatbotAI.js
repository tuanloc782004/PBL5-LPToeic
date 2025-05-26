document.addEventListener('DOMContentLoaded', function () {
	const chatbotIcon = document.querySelector('.message-icon a');
	const chatPopup = document.getElementById('chat-popup');
	const closeChatBtn = document.getElementById('close-chat');
	const sendChatBtn = document.getElementById('send-chat');
	const chatInput = document.getElementById('chat-input');
	const chatBody = document.getElementById('chat-body');

	chatbotIcon.addEventListener('click', function (e) {
		e.preventDefault();
		chatPopup.classList.toggle('hidden');
	});

	closeChatBtn.addEventListener('click', () => {
		chatPopup.classList.add('hidden');
	});

	sendChatBtn.addEventListener('click', sendMessage);
	chatInput.addEventListener('keypress', function (e) {
		if (e.key === 'Enter') sendMessage();
	});

	function displayMessage(sender, message, isUser = false) {
	    const messageDiv = document.createElement('div');
	    messageDiv.classList.add('chat-message');
	    if (isUser) {
	        messageDiv.classList.add('user-message');
	    } else {
	        messageDiv.classList.add('bot-message');
	    }
	    messageDiv.innerText = message;
	    chatBody.appendChild(messageDiv);
	    chatBody.scrollTop = chatBody.scrollHeight;
	}

	function sendMessage() {
		const message = chatInput.value.trim();
		if (!message) return;

		displayMessage("Bạn", message, true);
		chatInput.value = "";

		fetch("/user/chatbot/ask", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				"X-CSRF-TOKEN": document.querySelector('meta[name="_csrf"]').content
			},
			body: JSON.stringify({ prompt: message })
		})
		.then(response => response.json())
		.then(data => {
			displayMessage("AI", data.reply);
		})
		.catch(error => {
			console.error("Lỗi khi gửi câu hỏi:", error);
			displayMessage("AI", "Xin lỗi, đã xảy ra lỗi khi gửi câu hỏi.");
		});
	}
});