const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

// Kiểm tra nếu đường dẫn là /register thì hiển thị form đăng ký
if (window.location.pathname === '/register') {
	container.classList.add('active');
} else {
	container.classList.remove('active');
}

registerBtn.addEventListener('click', () => {
	container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
	container.classList.remove("active");
});
