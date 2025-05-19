document.addEventListener("DOMContentLoaded", function () {
	document.querySelectorAll(".question-detail").forEach((question) => {
		const answers = question.querySelectorAll("input[type='radio']");
		const checkAns = question.querySelector(".check-ans");
		const ansDetail = question.querySelector(".ans-detail");
		const correctAnswer = question.querySelector(".ans-detail span").textContent.trim();
		const hideButton = question.querySelector(".hide-explanation");
		const showButton = question.querySelector(".show-explanation");

		checkAns.style.display = "none"; // Ẩn phần check-ans ban đầu

		answers.forEach((answer, index) => {
			answer.addEventListener("change", function () {
				checkAns.style.display = "block"; // Hiện phần check-ans
				ansDetail.style.display = "block"; // Đảm bảo phần lời giải hiển thị
				hideButton.style.display = "inline";
				showButton.style.display = "none";

				// Disable các radio button
				answers.forEach((radio) => {
					radio.disabled = true;
				});

				const labels = question.querySelectorAll(".choose-ans label");
				labels.forEach((label) => {
					label.style.color = "";
					label.innerHTML = label.innerHTML.replace("✔️", "").replace("❌", "");
				});

				const selectedLabel = labels[index];
				const selectedAnswer = String.fromCharCode(65 + index);

				if (selectedAnswer === correctAnswer) {
					selectedLabel.style.color = "green";
					selectedLabel.style.fontWeight = "bold";
					selectedLabel.innerHTML += " ✔️";
				} else {
					selectedLabel.style.color = "red";
					selectedLabel.style.fontWeight = "bold";
					selectedLabel.innerHTML += " ❌";

					const correctIndex = [...labels].findIndex(l =>
						l.textContent.trim().startsWith(correctAnswer)
					);
					if (correctIndex !== -1) {
						labels[correctIndex].style.color = "green";
						labels[correctIndex].innerHTML += " ✔️";
					}
				}
			});
		});

		// Ẩn lời giải
		hideButton.addEventListener("click", function () {
			ansDetail.style.display = "none";
			hideButton.style.display = "none";
			showButton.style.display = "inline";
		});

		// Hiện lại lời giải
		showButton.addEventListener("click", function () {
			ansDetail.style.display = "block";
			hideButton.style.display = "inline";
			showButton.style.display = "none";
		});
	});
});