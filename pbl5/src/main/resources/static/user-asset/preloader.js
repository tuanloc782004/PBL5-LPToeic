window.addEventListener('load', () => {
	const preloader = document.getElementById('preloader');
	const content = document.getElementById('content'); // nếu có

	preloader.classList.add('hidden');
	setTimeout(() => {
		preloader.style.display = 'none';
		if (content) content.style.display = 'block';
	}, 500);
});
