// 🚀 Khởi động tên lửa ngay khi DOM sẵn sàng
document.addEventListener('DOMContentLoaded', () => {
	const preloader = document.getElementById('preloader');
	if (!preloader) return;

	const numRockets = 30;

	for (let i = 0; i < numRockets; i++) {
		createRocket();
	}

	function createRocket() {
		const rocket = document.createElement('div');
		rocket.classList.add('rocket');
		rocket.innerHTML = '🚀';

		resetRocket(rocket);
		preloader.appendChild(rocket);
		animateRocket(rocket);
	}

	function resetRocket(rocket) {
		rocket.style.left = Math.random() * window.innerWidth + 'px';
		rocket.style.top = window.innerHeight + 40 + 'px';
		rocket.style.opacity = 0.8;
		rocket.style.transform = `scale(${0.5 + Math.random()}) rotate(${Math.random() * 360}deg)`;
		rocket.speed = 5000 + Math.random() * 5000;
	}

	function animateRocket(rocket) {
		let start = null;

		function step(timestamp) {
			if (!start) start = timestamp;
			const elapsed = timestamp - start;

			const progress = elapsed / rocket.speed;
			const y = window.innerHeight + 40 - progress * (window.innerHeight + 100);
			const rotation = (elapsed / 20) % 360;

			rocket.style.top = y + 'px';
			rocket.style.transform = `scale(${0.5 + Math.sin(elapsed / 500) * 0.5}) rotate(${rotation}deg)`;

			if (y < -50) {
				resetRocket(rocket);
				start = timestamp;
			}

			requestAnimationFrame(step);
		}

		requestAnimationFrame(step);
	}
});

// 🕓 Ẩn preloader và hiện content sau khi load toàn bộ tài nguyên
window.addEventListener('load', () => {
	const preloader = document.getElementById('preloader');
	const content = document.getElementById('content');

	preloader.classList.add('hidden');
	setTimeout(() => {
		preloader.style.display = 'none';
		if (content) content.style.display = 'block';
	}, 500);
});
