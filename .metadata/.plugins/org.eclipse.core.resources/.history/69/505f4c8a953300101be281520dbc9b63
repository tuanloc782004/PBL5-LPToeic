window.addEventListener('load', () => {
  const preloader = document.getElementById('preloader');
  const content = document.getElementById('content');

  // Ẩn preloader sau 500ms
  preloader.classList.add('hidden');
  setTimeout(() => {
    preloader.style.display = 'none';
    if (content) content.style.display = 'block';
  }, 500);
});

window.addEventListener('DOMContentLoaded', () => {
  const preloader = document.getElementById('preloader');

  if (!preloader) return;

  const numRockets = 15; // số tên lửa bay

  // Tạo 15 tên lửa bay ngẫu nhiên
  for (let i = 0; i < numRockets; i++) {
    createRocket();
  }

  function createRocket() {
    const rocket = document.createElement('div');
    rocket.classList.add('rocket');
    rocket.innerHTML = '🚀'; // icon tên lửa

    resetRocket(rocket);

    preloader.appendChild(rocket);

    animateRocket(rocket);
  }

  function resetRocket(rocket) {
    // Vị trí ngang ngẫu nhiên trong viewport
    rocket.style.left = Math.random() * window.innerWidth + 'px';
    // Bắt đầu dưới màn hình (cao hơn 40px để vừa khung)
    rocket.style.top = window.innerHeight + 40 + 'px';
    rocket.style.opacity = 0.8;
    rocket.style.transform = `scale(${0.5 + Math.random()}) rotate(${Math.random()*360}deg)`;
    // Tốc độ bay ngẫu nhiên (5-10s)
    rocket.speed = 5000 + Math.random()*5000;
  }

  function animateRocket(rocket) {
    let start = null;

    function step(timestamp) {
      if (!start) start = timestamp;
      const elapsed = timestamp - start;

      // Tính vị trí Y: di chuyển từ dưới lên trên màn hình
      const progress = elapsed / rocket.speed;
      const y = window.innerHeight + 40 - progress * (window.innerHeight + 100);

      // Xoay tên lửa quay liên tục
      const rotation = (elapsed / 20) % 360;

      rocket.style.top = y + 'px';
      rocket.style.transform = `scale(${0.5 + Math.sin(elapsed/500)*0.5}) rotate(${rotation}deg)`;

      // Mờ dần khi gần hết màn hình
      if (y < -50) {
        resetRocket(rocket);
        start = timestamp;
      }

      requestAnimationFrame(step);
    }

    requestAnimationFrame(step);
  }
});
