
# 📚 PBL5-LPToeic – Ứng dụng Học và Thi thử TOEIC

---

### 🔍 Giới thiệu

**PBL5-LPToeic** là ứng dụng web hỗ trợ người học ôn luyện và thi thử TOEIC, bao gồm đầy đủ các kỹ năng Nghe, Đọc và Ngữ pháp. Ứng dụng sở hữu giao diện thân thiện, trực quan và dễ dàng sử dụng, giúp người dùng luyện tập hiệu quả.

---

### 🚀 Tính năng nổi bật

- 📝 **Luyện tập theo từng phần TOEIC:**
  - 🖼️ Part 1: Hình ảnh
  - ❓ Part 2: Hỏi – Đáp
  - 🗣️ Part 3: Đoạn hội thoại
  - 📢 Part 4: Bài nói chuyện
  - ✍️ Part 5: Hoàn thành câu
  - 📄 Part 6: Hoàn thành đoạn văn
  - 📚 Part 7: Đọc hiểu
- 📖 **Học ngữ pháp (Grammar):** bài giảng chi tiết kèm bài tập minh họa.
- ⏱️ **Thi thử:** mô phỏng cấu trúc đề thi thật với giới hạn thời gian chặt chẽ.
- ✅ **Tự động chấm điểm:** kết quả và lời giải được hiển thị ngay sau khi thi.
- 📊 **Thống kê kết quả:** theo dõi tiến trình học tập và cải thiện theo thời gian.
- 🗂️ **Danh sách từ vựng và mẹo làm bài:** giúp người học mở rộng vốn từ và nâng cao kỹ năng làm bài thi.

---

### 💻 Công nghệ sử dụng

- ☕ **Backend:** Java (Spring Boot)
- 🌐 **Frontend:** HTML, CSS, JavaScript, SCSS
- 🛢️ **Cơ sở dữ liệu:** MySQL

---

### 📂 Cấu trúc thư mục dự án

``` 
PBL5-LPToeic/
├── .metadata/           # Cấu hình dự án
├── pbl5/                # Mã nguồn backend
├── Upload Data/         # Dữ liệu tải lên
├── Dump.sql             # File sao lưu database
└── README.md            # Hướng dẫn này
``` 

### ⚙️ Hướng dẫn khởi chạy

1. Clone repo:
   bash
   git clone https://github.com/tuanloc782004/PBL5-LPToeic.git
   cd PBL5-LPToeic

2. Cấu hình và chạy backend:
   - Mở thư mục `pbl5` bằng IDE Java (IntelliJ/Eclipse).
   - Thiết lập kết nối database trong `application.properties`.
   - Chạy ứng dụng Spring Boot.
3. Frontend:
   - Mở trình duyệt truy cập giao diện frontend.
4. Cơ sở dữ liệu:
   - Import file `Dump.sql` vào MySQL.

### 📝 Giấy phép

Dự án cấp phép theo **MIT License**. Vui lòng xem file `LICENSE` để biết chi tiết.
