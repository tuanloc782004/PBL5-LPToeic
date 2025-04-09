-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pbl5
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `grammar_lesson`
--

DROP TABLE IF EXISTS `grammar_lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grammar_lesson` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `lesson_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grammar_lesson`
--

LOCK TABLES `grammar_lesson` WRITE;
/*!40000 ALTER TABLE `grammar_lesson` DISABLE KEYS */;
/*!40000 ALTER TABLE `grammar_lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listening_exercise`
--

DROP TABLE IF EXISTS `listening_exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listening_exercise` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `exercise_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listening_exercise`
--

LOCK TABLES `listening_exercise` WRITE;
/*!40000 ALTER TABLE `listening_exercise` DISABLE KEYS */;
INSERT INTO `listening_exercise` VALUES (1,'Test 1'),(2,'Test 2'),(3,'Test 3'),(4,'Test 4'),(5,'Test 1'),(6,'Test 2'),(7,'Test 3'),(8,'Test 4'),(9,'Test 1'),(10,'Test 2'),(11,'Test 3'),(12,'Test 4'),(13,'Test 1'),(14,'Test 2'),(15,'Test 3'),(16,'Test 4');
/*!40000 ALTER TABLE `listening_exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mock_exam`
--

DROP TABLE IF EXISTS `mock_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mock_exam` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mock_exam_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mock_exam`
--

LOCK TABLES `mock_exam` WRITE;
/*!40000 ALTER TABLE `mock_exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `mock_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part1`
--

DROP TABLE IF EXISTS `part1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part1` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `audio_url` varchar(255) NOT NULL,
  `correct_answer` varchar(1) NOT NULL,
  `explanation` text NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `number` bigint NOT NULL,
  `option_a` varchar(255) NOT NULL,
  `option_b` varchar(255) NOT NULL,
  `option_c` varchar(255) NOT NULL,
  `option_d` varchar(255) NOT NULL,
  `listening_exercise_id` bigint DEFAULT NULL,
  `mock_exam_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr4un0ixr7srb8f9k8drp04c3t` (`listening_exercise_id`),
  KEY `FKqk1qensmp0w4ldggfrrnvkqrx` (`mock_exam_id`),
  CONSTRAINT `FKqk1qensmp0w4ldggfrrnvkqrx` FOREIGN KEY (`mock_exam_id`) REFERENCES `mock_exam` (`id`),
  CONSTRAINT `FKr4un0ixr7srb8f9k8drp04c3t` FOREIGN KEY (`listening_exercise_id`) REFERENCES `listening_exercise` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part1`
--

LOCK TABLES `part1` WRITE;
/*!40000 ALTER TABLE `part1` DISABLE KEYS */;
INSERT INTO `part1` VALUES (1,'/upload-dir/audio/20250409_092944_cau1.mp3','A','(A) Người đàn ông đang cầm một ít hải sản. \n\n(B) Người phụ nữ đang nướng cua. \n\n(C) Họ sợ cua. \n\n(D) Gia đình đang đi mua bữa sáng.','/upload-dir/image/20250409_092944_cau1.png',1,'(A)','(B)','(C)','(D)',1,NULL),(2,'/upload-dir/audio/20250409_092944_cau2.mp3','A','(A) Người phụ nữ đang nấu thịt xông khói. \n\n(B) Ngươi phụ nữ đang nướng bánh. \n\n(C) Người phụ nữ đang chuẩn bị bữa tối. \n\n(D) Người phụ nữ đang rán cá.','/upload-dir/image/20250409_092944_cau2.png',2,'(A)','(B)','(C)','(D)',1,NULL),(3,'/upload-dir/audio/20250409_092944_cau3.mp3','A','(A) Có một số bàn ghế ở ngoài trời.\n(B) Có một vài người đang ngồi ở bàn.\n(C) Có những chiếc ô nhựa trên bàn.\n(D) Có rất nhiều hoa trong vườn.','/upload-dir/image/20250409_092944_cau3.png',3,'(A)','(B)','(C)','(D)',1,NULL),(4,'/upload-dir/audio/20250409_092944_cau4.mp3','B','(A) Người đàn ông đang sử dụng tuốc nơ vít để đóng đinh vào khung tòa nhà.\n(B) Người đàn ông đang đóng cái gì đó vào khung tòa nhà.\n(C) Người đàn ông đang làm khung bằng tay.\n(D) Người đàn ông đang đeo kính bảo hộ.','/upload-dir/image/20250409_092944_cau4.png',4,'(A)','(B)','(C)','(D)',1,NULL),(5,'/upload-dir/audio/20250409_092944_cau5.mp3','D','(A) Họ đang nhìn nhau.\n(B) Người phụ nữ đang gõ máy tính.\n(C) Người đàn ông đang sử dụng máy tính.\n(D) Người đàn ông đang viết gì đó vào sổ ghi chú.','/upload-dir/image/20250409_092944_cau5.png',5,'(A)','(B)','(C)','(D)',1,NULL),(6,'/upload-dir/audio/20250409_093002_cau1.mp3','A','(A) Người phụ nữ đang nói chuyện điện thoại. \n\n(B) Người phụ nữ đang sử dụng điện thoại di động. \n\n(C) Người phụ nữ đang đánh máy tính. \n\n(D) Người phụ nữ đang viết vào sổ tay.','/upload-dir/image/20250409_093002_cau1.png',1,'(A)','(B)','(C)','(D)',2,NULL),(7,'/upload-dir/audio/20250409_093002_cau2.mp3','A','(A) The plane is docked at the airport.\n(B) There is luggage being put onto the plane.\n(C) There are many people boarding the plane.\n(D) There are maintenance workers fixing the plane.','/upload-dir/image/20250409_093002_cau2.png',2,'(A)','(B)','(C)','(D)',2,NULL),(8,'/upload-dir/audio/20250409_093002_cau3.mp3','A','(A) The woman is drinking a cup of coffee\n(B) The woman is listening to music.\n(C) The woman is talking on her cell phone.\n(D) The woman is looking at the newspaper.','/upload-dir/image/20250409_093002_cau3.png',3,'(A)','(B)','(C)','(D)',2,NULL),(9,'/upload-dir/audio/20250409_093002_cau4.mp3','B','(A) She is fixing the wheel on her bike.\n(B) She is changing the tire on her car.\n(C) She is putting oil into her car.\n(D) She is standing behind the windmill.','/upload-dir/image/20250409_093002_cau4.png',4,'(A)','(B)','(C)','(D)',2,NULL),(10,'/upload-dir/audio/20250409_093002_cau5.mp3','C','(A) They are very close to the chairlift.\n(B) They are making snow.\n(C) The people are skiing down the mountain.\n(D) All of the skiers are wearing helmets.','/upload-dir/image/20250409_093002_cau5.png',5,'(A)','(B)','(C)','(D)',2,NULL),(11,'/upload-dir/audio/20250409_093021_cau1.mp3','B','(A) The people are drinking glasses of juice.\n(B) She is giving a presentation about September’s sales figures.\n(C) All of the women are sitting down.\n(D) One of the women is giving a business presentation on a whiteboard.','/upload-dir/image/20250409_093021_cau1.png',1,'(A)','(B)','(C)','(D)',3,NULL),(12,'/upload-dir/audio/20250409_093021_cau2.mp3','D','(A) The men are adjusting headsets.\n(B) The women are wearing headsets.\n(C) The men are using a mouse with the laptop.\n(D) The women are talking to each other.','/upload-dir/image/20250409_093021_cau2.png',2,'(A)','(B)','(C)','(D)',3,NULL),(13,'/upload-dir/audio/20250409_093021_cau3.mp3','A','(A) Người đàn ông đang cầm một ít hải sản. \n\n(B) Người phụ nữ đang nướng cua. \n\n(C) Họ sợ cua. \n\n(D) Gia đình đang đi mua bữa sáng.','/upload-dir/image/20250409_093021_cau3.png',3,'(A)','(B)','(C)','(D)',3,NULL),(14,'/upload-dir/audio/20250409_093021_cau4.mp3','A','(A) Người phụ nữ đang nấu thịt xông khói. \n\n(B) Ngươi phụ nữ đang nướng bánh. \n\n(C) Người phụ nữ đang chuẩn bị bữa tối. \n\n(D) Người phụ nữ đang rán cá.','/upload-dir/image/20250409_093021_cau4.png',4,'(A)','(B)','(C)','(D)',3,NULL),(15,'/upload-dir/audio/20250409_093021_cau5.mp3','A','(A) Có một số bàn ghế ở ngoài trời.\n(B) Có một vài người đang ngồi ở bàn.\n(C) Có những chiếc ô nhựa trên bàn.\n(D) Có rất nhiều hoa trong vườn.','/upload-dir/image/20250409_093021_cau5.png',5,'(A)','(B)','(C)','(D)',3,NULL),(16,'/upload-dir/audio/20250409_093040_cau4.mp3','A','(A) Người đàn ông đang sử dụng tuốc nơ vít để đóng đinh vào khung tòa nhà.\n(B) Người đàn ông đang đóng cái gì đó vào khung tòa nhà.\n(C) Người đàn ông đang làm khung bằng tay.\n(D) Người đàn ông đang đeo kính bảo hộ.','/upload-dir/image/20250409_093040_cau4.png',1,'(A)','(B)','(C)','(D)',4,NULL),(17,'/upload-dir/audio/20250409_093040_cau5.mp3','A','(A) Họ đang nhìn nhau.\n(B) Người phụ nữ đang gõ máy tính.\n(C) Người đàn ông đang sử dụng máy tính.\n(D) Người đàn ông đang viết gì đó vào sổ ghi chú.','/upload-dir/image/20250409_093040_cau5.png',2,'(A)','(B)','(C)','(D)',4,NULL),(18,'/upload-dir/audio/20250409_093040_cau1.mp3','A','(A) Người phụ nữ đang nói chuyện điện thoại. \n\n(B) Người phụ nữ đang sử dụng điện thoại di động. \n\n(C) Người phụ nữ đang đánh máy tính. \n\n(D) Người phụ nữ đang viết vào sổ tay.','/upload-dir/image/20250409_093040_cau1.png',3,'(A)','(B)','(C)','(D)',4,NULL),(19,'/upload-dir/audio/20250409_093040_cau2.mp3','A','(A) The plane is docked at the airport.\n(B) There is luggage being put onto the plane.\n(C) There are many people boarding the plane.\n(D) There are maintenance workers fixing the plane.','/upload-dir/image/20250409_093040_cau2.png',4,'(A)','(B)','(C)','(D)',4,NULL),(20,'/upload-dir/audio/20250409_093040_cau3.mp3','A','(A) The woman is drinking a cup of coffee\n(B) The woman is listening to music.\n(C) The woman is talking on her cell phone.\n(D) The woman is looking at the newspaper.','/upload-dir/image/20250409_093040_cau3.png',5,'(A)','(B)','(C)','(D)',4,NULL);
/*!40000 ALTER TABLE `part1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part2`
--

DROP TABLE IF EXISTS `part2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part2` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `audio_url` varchar(255) NOT NULL,
  `correct_answer` varchar(1) NOT NULL,
  `explanation` text NOT NULL,
  `number` bigint NOT NULL,
  `option_a` varchar(255) NOT NULL,
  `option_b` varchar(255) NOT NULL,
  `option_c` varchar(255) NOT NULL,
  `listening_exercise_id` bigint DEFAULT NULL,
  `mock_exam_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7qy6y80vv2n6qainykdg9gyos` (`listening_exercise_id`),
  KEY `FKte049aug3q84xd51yx1rm786h` (`mock_exam_id`),
  CONSTRAINT `FK7qy6y80vv2n6qainykdg9gyos` FOREIGN KEY (`listening_exercise_id`) REFERENCES `listening_exercise` (`id`),
  CONSTRAINT `FKte049aug3q84xd51yx1rm786h` FOREIGN KEY (`mock_exam_id`) REFERENCES `mock_exam` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part2`
--

LOCK TABLES `part2` WRITE;
/*!40000 ALTER TABLE `part2` DISABLE KEYS */;
INSERT INTO `part2` VALUES (1,'/upload-dir/audio/20250409_093102_cau1.mp3','C','Chuyến tham quan bảo tàng thế nào?.\n(A) Cửa sổ hướng ra đường.\n(B) Giữa phố Williams và Đại lộ Keller.\n(C) Nó rất nhiều thông tin.',1,'(A)','(B)','(C)',5,NULL),(2,'/upload-dir/audio/20250409_093102_cau2.mp3','C','Máy pha cà phê mới này rất đắt tiền.\n(A) Anh ấy có nhiều kinh nghiệm quản lý.\n(B) Có kẹt giấy trong máy photocopy.\n(C) Đó là lý do tại sao cà phê có hương vị tuyệt vời.',2,'(A)','(B)','(C)',5,NULL),(3,'/upload-dir/audio/20250409_093102_cau3.mp3','A','Tại sao chúng ta không chụp ảnh nhóm?.\n(A) Chắc chắn rồi, hãy thực hiện theo các bước.\n(B) Một máy ảnh kỹ thuật số.\n(C) Vâng, cô ấy trông thật hấp dẫn trong bức ảnh này.',3,'(A)','(B)','(C)',5,NULL),(4,'/upload-dir/audio/20250409_093102_cau4.mp3','B','Thang máy đã được sửa chữa rồi phải không?.\n(A) Cô ấy làm việc ở tầng ba.\n(B) Vâng, nó đang hoạt động trở lại.\n(C) Đó không phải là những gì tôi thấy.',4,'(A)','(B)','(C)',5,NULL),(5,'/upload-dir/audio/20250409_093102_cau5.mp3','B','Tại sao tờ rơi không được chuẩn bị kịp thời cho sự kiện?.\n(A) Đó không phải tờ in thường.\n(B) Máy photocopy bị trục trặc.\n(C) Đó là lễ kỷ niệm 40 năm thành lập công ty.',5,'(A)','(B)','(C)',5,NULL),(6,'/upload-dir/audio/20250409_093130_cau1.mp3','C','Bạn có muốn mượn cuốn sách này khi tôi đọc xong không?.\n(A) Cô Watson sẽ dẫn đầu nhóm.\n(B) Tôi sẽ đặt bàn cho bữa tối.\n(C) Không, tôi sẽ lấy nó từ thư viện.',1,'(A)','(B)','(C)',6,NULL),(7,'/upload-dir/audio/20250409_093130_cau2.mp3','B','Bạn đã kê ghế trong phòng họp phải không?.\n(A) Tôi cần một cuốn sách tham khảo.\n(B) Có, tổng cộng 200 chỗ.\n(C) Không, tôi không thể tìm thấy địa chỉ e-mail.',2,'(A)','(B)','(C)',6,NULL),(8,'/upload-dir/audio/20250409_093130_cau3.mp3','A','Khi nào nên bật điều hòa?.\n(A) Khi nhiệt độ đạt tới 25 độ.\n(B) Tôi đồng ý với bạn.\n(C) Chúng sẽ được phát sóng trong khoảng một giờ nữa.',3,'(A)','(B)','(C)',6,NULL),(9,'/upload-dir/audio/20250409_093130_cau4.mp3','A','Chi phí thay thế cửa sổ là bao nhiêu?.\n(A) Tôi nghĩ nó chưa đến 60 đô la.\n(B) Trong một cửa hàng bách hóa.\n(C) Nó không khó chút nào.',4,'(A)','(B)','(C)',6,NULL),(10,'/upload-dir/audio/20250409_093130_cau5.mp3','B','Xe buýt này đi đến đâu?.\n(A) Bạn cần thẻ chuyển tuyến.\n(B) Trạm xe buýt ở đằng kia.\n(C) Xe buýt này đến trái tim em.',5,'(A)','(B)','(C)',6,NULL),(11,'/upload-dir/audio/20250409_093144_cau1.mp3','C','Tôi rất ấn tượng với giọng hát của Alex.\n(A) Tôi quên tên ca sĩ.\n(B) Buổi hòa nhạc ở đâu?\n(C) Vâng, anh ấy có một giọng hát tuyệt vời.',1,'(A)','(B)','(C)',7,NULL),(12,'/upload-dir/audio/20250409_093144_cau2.mp3','B','Bạn đã cân nhắc việc xây dựng một hàng rào chưa?.\n(A) Ngôi nhà đang được rao bán.\n(B) Vâng, chúng ta sẽ làm điều đó sau.\n(C) Nhận xét của anh ta gây ra sự xúc phạm.',2,'(A)','(B)','(C)',7,NULL),(13,'/upload-dir/audio/20250409_093144_cau3.mp3','B','Bạn đã giới thiệu bản thân với nhân viên mới chưa?.\n(A) Một hệ thống khen thưởng mới sẽ sớm được giới thiệu.\n(B) Không, hôm nay tôi quá bận.\n(C) Rất vui được gặp bạn.',3,'(A)','(B)','(C)',7,NULL),(14,'/upload-dir/audio/20250409_093144_cau4.mp3','A','Tại sao tối nay tàu điện ngầm ngừng chạy sớm?.\n(A) Bởi vì đó là ngày nghỉ lễ.\n(B) Hãy xuống ở ga tiếp theo.\n(C) Không, ngày mai tôi sẽ không chạy.',4,'(A)','(B)','(C)',7,NULL),(15,'/upload-dir/audio/20250409_093144_cau5.mp3','B','Lẽ ra bây giờ đồ ăn của chúng ta đã được phục vụ rồi sao?.\n(A) Nó rất ngon.\n(B) Vâng, dịch vụ tối nay khá chậm.\n(C) Tôi sẽ gọi mì cà chua.',5,'(A)','(B)','(C)',7,NULL),(16,'/upload-dir/audio/20250409_093157_cau1.mp3','C','Ai sẽ phát biểu tại lễ khai mạc tối nay?.\n(A) Hàng ghế trước.\n(B) Ông Gibson sẽ đóng cửa lại.\n(C) Một tiểu thuyết gia nổi tiếng.',1,'(A)','(B)','(C)',8,NULL),(17,'/upload-dir/audio/20250409_093157_cau2.mp3','B','Chỗ ngồi nào là của tôi?.\n(A) Đó là một chiếc ghế thoải mái.\n(B) Hãy ngồi ở bất cứ đâu.\n(C) Hãy ghi nhớ điều đó.',2,'(A)','(B)','(C)',8,NULL),(18,'/upload-dir/audio/20250409_093157_cau3.mp3','A','Bạn định ra ngoài ăn tối hay ở nhà?.\n(A) Tôi sẽ đặt hàng giao hàng.\n(B) Vui lòng mang theo hóa đơn.\n(C) Vào một thời điểm thuận tiện.',3,'(A)','(B)','(C)',8,NULL),(19,'/upload-dir/audio/20250409_093157_cau4.mp3','A','Bạn chưa nhận được tiền lương phải không?.\n(A) Không, chúng sẽ được nhận vào tuần tới.\n(B) Chắc chắn rồi, tôi sẽ gửi e-mail cho anh ấy.\n(C) Cô ấy muốn được thăng chức.',4,'(A)','(B)','(C)',8,NULL),(20,'/upload-dir/audio/20250409_093157_cau5.mp3','C','Chuyến dã ngoại của công ty được tổ chức ở đâu?.\n(A) Vào tháng Tư.\n(B) Đồ giải khát sẽ được cung cấp.\n(C) Tại một công viên cạnh hồ.',5,'(A)','(B)','(C)',8,NULL);
/*!40000 ALTER TABLE `part2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part3`
--

DROP TABLE IF EXISTS `part3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part3` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `audio_url` varchar(255) NOT NULL,
  `listening_exercise_id` bigint DEFAULT NULL,
  `mock_exam_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKyp6t3qvrfq8wljohct4rrgci` (`listening_exercise_id`),
  KEY `FK19fu4gqhsck8gmibw7u1ei7lw` (`mock_exam_id`),
  CONSTRAINT `FK19fu4gqhsck8gmibw7u1ei7lw` FOREIGN KEY (`mock_exam_id`) REFERENCES `mock_exam` (`id`),
  CONSTRAINT `FKyp6t3qvrfq8wljohct4rrgci` FOREIGN KEY (`listening_exercise_id`) REFERENCES `listening_exercise` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part3`
--

LOCK TABLES `part3` WRITE;
/*!40000 ALTER TABLE `part3` DISABLE KEYS */;
INSERT INTO `part3` VALUES (1,'/upload-dir/audio/20250409_093214_audio.mp3',9,NULL),(2,'/upload-dir/audio/20250409_093300_audio.mp3',10,NULL),(3,'/upload-dir/audio/20250409_093317_audio.mp3',11,NULL),(4,'/upload-dir/audio/20250409_093331_audio.mp3',12,NULL);
/*!40000 ALTER TABLE `part3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part3_question`
--

DROP TABLE IF EXISTS `part3_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part3_question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correct_answer` varchar(255) NOT NULL,
  `explanation` text NOT NULL,
  `number` bigint NOT NULL,
  `option_a` varchar(255) NOT NULL,
  `option_b` varchar(255) NOT NULL,
  `option_c` varchar(255) NOT NULL,
  `option_d` varchar(255) NOT NULL,
  `question` varchar(255) NOT NULL,
  `part3_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKafo45qxu6kpbq7f37dr1tbtyf` (`part3_id`),
  CONSTRAINT `FKafo45qxu6kpbq7f37dr1tbtyf` FOREIGN KEY (`part3_id`) REFERENCES `part3` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part3_question`
--

LOCK TABLES `part3_question` WRITE;
/*!40000 ALTER TABLE `part3_question` DISABLE KEYS */;
INSERT INTO `part3_question` VALUES (1,'A','Giải thích',1,'(A) Lecturer','(B) Editor','(C) Journalist','(D) Superintendent','What position is the man applying for?',1),(2,'B','Giải thích',2,'(A) His academic background','(B) His previous work experience','(C) His public popularity','(D) His eloquence','What makes the man qualified for the position?',1),(3,'C','Giải thích',3,'(A) Health insurance','(B) Flexible hours','(C) A lot of free time','(D) Regular incentives','What extra benefit does the woman mention?',1),(4,'A','Giải thích',1,'(A) Orders for office supplies','(B) Equipment for a conference','(C) The budget reports','(D) Their colleague','What are the speakers discussing?',2),(5,'C','Giải thích',2,'(A) To inform her of the business hours','(B) To let her know she can’t order anything','(C) To explain that the second order would be late','(D) To imply that new equipment can’t be ordered','Why does the man mention when the supply company closes?',2),(6,'D','Giải thích',3,'(A) Pay for the new order','(B) Order the supplies herself','(C) Cancel a meeting','(D) Speak to their colleague','What does the woman offer to do?',2),(7,'B','Giải thích',1,'(A) A training seminar','(B) The installation of a television','(C) The date of a presentation','(D) A software upgrade','What are the speakers mainly discussing?',3),(8,'A','Giải thích',2,'(A) The necessary tools are unavailable.','(B) The office is closed.','(C) The wall is too weak.','(D) The phone number was wrong.','What is the problem?',3),(9,'C','Giải thích',3,'(A) Order a replacement part','(B) Consult an instruction manual','(C) Contact the woman','(D) Fill out a work order','What most likely will the man do first tomorrow?',3),(10,'D','Giải thích',1,'(A) A shop owner','(B) A construction worker','(C) A local resident','(D) A market researcher','Who most likely is the man?',4),(11,'B','Giải thích',2,'(A) It was recently renovated.','(B) It has sufficient parking space.','(C) It is attracting many tourists.','(D) It is located outside of town.','What does the woman mention about the mall?',4),(12,'C','Giải thích',3,'(A) To purchase groceries','(B) To meet with her clients','(C) To buy clothing','(D) To deliver products','Why does the woman usually visit the mall?',4);
/*!40000 ALTER TABLE `part3_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part4`
--

DROP TABLE IF EXISTS `part4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part4` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `audio_url` varchar(255) NOT NULL,
  `listening_exercise_id` bigint DEFAULT NULL,
  `mock_exam_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj2y737fi0vonn71psa1a0l0wq` (`listening_exercise_id`),
  KEY `FK9t8mebwiqgp1qhiv5by76962x` (`mock_exam_id`),
  CONSTRAINT `FK9t8mebwiqgp1qhiv5by76962x` FOREIGN KEY (`mock_exam_id`) REFERENCES `mock_exam` (`id`),
  CONSTRAINT `FKj2y737fi0vonn71psa1a0l0wq` FOREIGN KEY (`listening_exercise_id`) REFERENCES `listening_exercise` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part4`
--

LOCK TABLES `part4` WRITE;
/*!40000 ALTER TABLE `part4` DISABLE KEYS */;
INSERT INTO `part4` VALUES (1,'/upload-dir/audio/20250409_093351_audio.mp3',13,NULL),(2,'/upload-dir/audio/20250409_093404_audio.mp3',14,NULL),(3,'/upload-dir/audio/20250409_093415_audio.mp3',15,NULL),(4,'/upload-dir/audio/20250409_093427_audio.mp3',16,NULL);
/*!40000 ALTER TABLE `part4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part4_question`
--

DROP TABLE IF EXISTS `part4_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part4_question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correct_answer` varchar(255) NOT NULL,
  `explanation` text NOT NULL,
  `number` bigint NOT NULL,
  `option_a` varchar(255) NOT NULL,
  `option_b` varchar(255) NOT NULL,
  `option_c` varchar(255) NOT NULL,
  `option_d` varchar(255) NOT NULL,
  `question` varchar(255) NOT NULL,
  `part4_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKancopar58rinns8xy1yueyw15` (`part4_id`),
  CONSTRAINT `FKancopar58rinns8xy1yueyw15` FOREIGN KEY (`part4_id`) REFERENCES `part4` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part4_question`
--

LOCK TABLES `part4_question` WRITE;
/*!40000 ALTER TABLE `part4_question` DISABLE KEYS */;
INSERT INTO `part4_question` VALUES (1,'C','Q1 Amy, it is your responsibility to check that the store is clean and well-stocked for customers before we open for the day. Q2 Most importantly, I would like you to make sure that the proper price tags are displayed in front of their corresponding products. Customers get really confused and upset when the price of a product is displayed incorrectly. Q3 In the case that a customer ever does get displeased, please let me know right away so I can come and deal with the problem in person.',1,'(A) At a theater','(B) At a car dealership','(C) At a retail store','(D) At a library','Where most likely does the speaker work?',1),(2,'A','',2,'(A) Accurate prices','(B) Sales figures','(C) Business hours','(D) Name tags','What is the listener asked to double-check?',1),(3,'D','',3,'(A) If an employee is late for work','(B) If a technical problem occurs','(C) If an item is out of stock','(D) If a customer is dissatisfied','When should the listener contact the speaker?',1),(4,'A','Q1 The town of Dayton is excited to announce the opening of a new community center. The center provides daytime activities for kids and adults of all ages. Q2 For the grand opening, the local band Summer Heat, led by Jim Neilson, will perform a show in half an hour. Q3 Afterwards, attendees are encouraged to fill out a survey meant to judge the needs of local citizens. Thank you.\n\nHighlightNote',1,'(A) An opening of a public building','(B) A commemorative statue','(C) A singing contest','(D) A survey result','What is the announcement about?',2),(5,'C','',2,'(A) A mayor','(B) An instructor','(C) A musician','(D) An architect','Who is Jim Neilson?',2),(6,'B','',3,'(A) Reserve seats in advance','(B) Complete a survey','(C) Subscribe to a newsletter','(D) Contribute to a fundraiser','What are attendees asked to do?',2),(7,'B','Good morning, everyone. Q1 Welcome to a test screening of our pilot for a new daytime sitcom entitled Once Upon a Romance. Your participation in this focus group is essential for assessing audience reception. Q2 This television show is meant to appeal to middle- aged housewives, and that is why you have all been selected. Q3 After watching the pilot, we will take you to a meeting room where we will conduct an in-depth interview that will help us gather your feedback and responses. Thank you again for your cooperation.',1,'(A) A scholar','(B) A producer','(C) A pilot','(D) A programmer','Who most likely is the speaker?',3),(8,'C','',2,'(A) Potential investors','(B) Actors','(C) Housewives','(D) University students','Who are the listeners?',3),(9,'D','',3,'(A) Participate in a raffle','(B) Watch a video','(C) Enroll in a class','(D) Attend an interview','What will the listeners do in a meeting room?',3),(10,'D','Q1 This is a reminder that legendary soccer player Tommy Durant will be signing autographs at Dave’s Sport Shop at 1:00 P.M. tomorrow. Q2 You are encouraged to bring your own items, such as clothes or books, for Mr. Durant to autograph. Also at this time, parents will be able to sign their children up for a summer soccer camp that will be run by Tommy Durant. Q3 The camp is limited to twenty children, so anyone who is interested should sign up early.',1,'(A) To announce the results of a soccer match','(B) To promote a store\'s grand opening','(C) To advertise a new product','(D) To inform the listeners of a special event','What is the purpose of the broadcast?',4),(11,'C','',2,'(A) Wearing comfortable clothing','(B) Exercising on a regular basis','(C) Bringing personal belongings','(D) Booking a ticket in advance','What does the speaker suggest doing?',4),(12,'C','',3,'(A) It is free of charge.','(B) It will last three months.','(C) It has a restricted number of participants.','(D) It will be sponsored by Dave\'s Sport Shop.','What does the speaker say about the summer camp?',4);
/*!40000 ALTER TABLE `part4_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part5`
--

DROP TABLE IF EXISTS `part5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part5` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correct_answer` varchar(1) NOT NULL,
  `explanation` text NOT NULL,
  `number` bigint NOT NULL,
  `option_a` varchar(255) NOT NULL,
  `option_b` varchar(255) NOT NULL,
  `option_c` varchar(255) NOT NULL,
  `option_d` varchar(255) NOT NULL,
  `question` varchar(255) NOT NULL,
  `grammar_lesson` bigint DEFAULT NULL,
  `mock_exam_id` bigint DEFAULT NULL,
  `reading_exercise_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp11c1xt3m5r69glxjiq3dnmc9` (`grammar_lesson`),
  KEY `FKplvex7h3bh12u9bfunmbkhr03` (`mock_exam_id`),
  KEY `FK4c95h0v0u6o28q5fjkohn55d7` (`reading_exercise_id`),
  CONSTRAINT `FK4c95h0v0u6o28q5fjkohn55d7` FOREIGN KEY (`reading_exercise_id`) REFERENCES `reading_exercise` (`id`),
  CONSTRAINT `FKp11c1xt3m5r69glxjiq3dnmc9` FOREIGN KEY (`grammar_lesson`) REFERENCES `grammar_lesson` (`id`),
  CONSTRAINT `FKplvex7h3bh12u9bfunmbkhr03` FOREIGN KEY (`mock_exam_id`) REFERENCES `mock_exam` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part5`
--

LOCK TABLES `part5` WRITE;
/*!40000 ALTER TABLE `part5` DISABLE KEYS */;
INSERT INTO `part5` VALUES (1,'A','explanation',1,'(A) notify','(B) agree','(C) generate','(D) perform','The library staff posted signs to _____ patrons of the upcoming closure for renovations.',NULL,NULL,1),(2,'C','explanation',2,'(A) him','(B) himself','(C) his','(D) he','The artist sent _____ best pieces to the gallery to be reviewed by the owner',NULL,NULL,1),(3,'A','explanation',3,'(A) will employ','(B) to employ','(C) has been employed','(D) employ','Ms. Morgan recruited the individuals that the company _____ for the next three months.',NULL,NULL,1),(4,'A','explanation',4,'(A) treatment','(B) treat','(C) treated','(D) treating','The free clinic was founded by a group of doctors to give_____for various medical conditions.',NULL,NULL,1),(5,'D','explanation',5,'(A) obstructs','(B) obstructed','(C) obstruction','(D) obstructing','For optimal safety on the road, avoid _____ the view of the rear window and side-view mirrors.',NULL,NULL,1),(6,'B','explanation',1,'(A) In addition to','(B) The fact that','(C) As long as','(D) In keeping with','_____ sales of junk food have been steadily declining indicates that consumers are becoming more health-conscious.',NULL,NULL,2),(7,'C','explanation',2,'(A) himself','(B) he','(C) who','(D) which','Mr. Ross, _____is repainting the interior of the lobby, was recommended by a friend of the building manager.',NULL,NULL,2),(8,'A','explanation',3,'(A) mechanically','(B) mechanic','(C) mechanism','(D) mechanical','The sprinklers for the lawn’s irrigation system are _____controlled.',NULL,NULL,2),(9,'C','explanation',4,'(A) rarely','(B) tiredly','(C) openly','(D) highly','We would like to discuss this problem honestly and _____ at the next staff meeting.',NULL,NULL,2),(10,'C','explanation',5,'(A) cooperative','(B) visible','(C) essential','(D) alternative','Having proper ventilation throughout the building is _____ for protecting the health and well-being of the workers.',NULL,NULL,2),(11,'A','explanation',1,'(A) Now','(B) For','(C) As','(D) Though','_____ that the insulation has been replaced, the building is much more energy-efficient.',NULL,NULL,3),(12,'C','explanation',2,'(A) courteously','(B) initially','(C) periodically','(D) physically','The guidelines for the monthly publication are _____revised to adapt to the changing readers.',NULL,NULL,3),(13,'C','explanation',3,'(A) Then','(B) So that','(C) Before','(D) Whereas','_____Mr. Williams addressed the audience, he showed a brief video about the engine he had designed.',NULL,NULL,3),(14,'D','explanation',4,'(A) with','(B) at','(C) like','(B) at','Participants in the walking tour should gather_____ 533 Bates Road on Saturday morning.',NULL,NULL,3),(15,'B','explanation',5,'(A) strategy','(B) strategically','(C) strategies','(D) strategic','The company _____ lowered its prices to outsell its competitors and attract more customers.',NULL,NULL,3),(16,'A','explanation',1,'(A) necessitate','(B) necessarily','(C) necessary','(D) necessity','During the peak season, it is _____ to hire additional workers for the weekend shifts.',NULL,NULL,4),(17,'D','explanation',2,'(A) relevance','(B) relevantly','(C) more relevantly','(D) relevant','The figures that accompany the financial statement should be _____ to the spending category.',NULL,NULL,4),(18,'A','explanation',3,'(A) Anyone','(B) Whenever','(C) Other','(D) Fewer','_____ seeking a position at Tulare Designs must submit a portfolio of previous work.',NULL,NULL,4),(19,'A','explanation',4,'(A) In spite of','(B) Even if','(C) Whether','(D) Given that','_____ an ankle injury, the baseball player participated in the last game of the season.',NULL,NULL,4),(20,'C','explanation',5,'(A) more important','(B) importantly','(C) importance','(D) important','The company started to recognize the increasing _____ of using resources responsibly.',NULL,NULL,4),(21,'A','explanation',1,'(A) After','(B) Until','(C) Below','(D) Like','_____ restructuring several departments within the company, the majority of the problems with miscommunication have disappeared.',NULL,NULL,5),(22,'A','explanation',2,'(A) legal','(B) legalize','(C) legally','(D) legalizes','The governmental department used to provide financial aid, but now it offers _____ services only.',NULL,NULL,5),(23,'B','explanation',3,'(A) expressive','(B) reliable','(C) partial','(D) extreme','Mr. Sims needs a more _____ vehicle for commuting from his suburban home to his office downtown.',NULL,NULL,5),(24,'B','explanation',4,'(A) experience','(B) growth','(C) formula','(D) incentive','The contractor had a fifteen-percent _____ in his business after advertising in the local newspaper.',NULL,NULL,5),(25,'D','explanation',5,'(A) accessorized','(B) accessorize','(C) accessorizes','(D) accessories','The upscale boutique Jane’s Closet is known for selling the most stylish _____ for young professionals.',NULL,NULL,5);
/*!40000 ALTER TABLE `part5` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part6`
--

DROP TABLE IF EXISTS `part6`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part6` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `script` text,
  `mock_exam_id` bigint DEFAULT NULL,
  `reading_exercise_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKorbj4xonq4hx2hdsv3tsqdpti` (`mock_exam_id`),
  KEY `FKoayyfwr3j7xqu90opynt1h965` (`reading_exercise_id`),
  CONSTRAINT `FKoayyfwr3j7xqu90opynt1h965` FOREIGN KEY (`reading_exercise_id`) REFERENCES `reading_exercise` (`id`),
  CONSTRAINT `FKorbj4xonq4hx2hdsv3tsqdpti` FOREIGN KEY (`mock_exam_id`) REFERENCES `mock_exam` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part6`
--

LOCK TABLES `part6` WRITE;
/*!40000 ALTER TABLE `part6` DISABLE KEYS */;
INSERT INTO `part6` VALUES (1,'To:Kitchen staff,office employees\nFrom:Manager, LarryPark\nDate:March 23\nSubject: Renovations\n\nTo all kitchen staff and Harmon employees,\n\nFrom Sunday, March 23 to Thursday, March 27, the employee cafeteria kitchens will undergo renovations as new appliances and equipment (1) _____ in to replace the old ones. (2) _____ Instead, the convenience shops will carry more sandwiches, prepared lunch boxes,and snacks for the employees during this time.\n\nThe renovations will increase the number of sinks, ovens, and stove tops so that a larger volume of meals can be provided (3) _____ the lunch and dinner rushes. We apologize for the inconvenience but we hope that the changes will (4) _____ the services in the cafeteria.',NULL,6),(2,'Entry Position: Gold & Slide Accounting Firm\n\nWe are looking for enthusiastic candidates with an educational background in finance or (1) _____. All candidates should have some computer experience. Job experience is not (2) _____ but preferred. Candidates with a bilingual language ability (3) _____ favored. Positions include jobs in accounting, statistics, and general office assistant. If you are interested, please visit our website at www.G&Saccountingfirm.com/employment for more information. You can send your cover letters and resumes to Karen Hill at khill@G&S.com. We will begin interviewing candidates on Monday, November 5. (4) _____',NULL,7),(3,'Thank you for shopping at Larson’s China. Our products are known for their modern and unique patterns and color combinations, as well as (1) _____ and strength. (2) _____ Please note, however, that repeated drops and rough handling will (3) _____ eventual breakage. We suggest you store them carefully and that you don’t use harsh chemicals, steel sponges, or (4) _____ scrubbing when cleaning them. Please visit our website at www.larsonchina.com for information about handling and care or call us at 555-1234 if you have any questions or concerns.',NULL,8),(4,'Employee Spring Training\n\nLawrence Paper is dedicated to helping all of its employees fulfill their potential. That is why we have once again organized 2 days of spring training. Human Resources has put together a wide range of topics for this year’s workshops, (1) _____ sales techniques, computer skills, communication strategies, and goal setting. We still have two workshop time slots available, so if there is something you’ve been dying to learn about, please let us know. It’s quite possible we (2) _____ it into this year’s spring training. (3) _____ Feel free to (4) _____ any ideas you might have to Nancy Kensington in the human resources department.\nNLKensington@lawrencepaper.com',NULL,9),(5,'Vander Properties has been serving Houston for over 29 years. It is through our commitment to providing the highest degree of expertise market knowledge and (1) _______ service that we are recognized as an industry leader. We specialize in (2) _____ and corporate real estate here in the Denver area. (3) _____ you are looking to buy a new home or start a new business, we are the people you should be talking with. With our office centrally located downtown, we have our eyes on the whole city. Browse our site for listing, or give us a call today (4) _____.',NULL,10);
/*!40000 ALTER TABLE `part6` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part6_question`
--

DROP TABLE IF EXISTS `part6_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part6_question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correct_answer` varchar(1) NOT NULL,
  `explanation` text NOT NULL,
  `number` bigint NOT NULL,
  `option_a` varchar(255) NOT NULL,
  `option_b` varchar(255) NOT NULL,
  `option_c` varchar(255) NOT NULL,
  `option_d` varchar(255) NOT NULL,
  `part6_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKocn9nbkeycv1w3g2ku5n40agj` (`part6_id`),
  CONSTRAINT `FKocn9nbkeycv1w3g2ku5n40agj` FOREIGN KEY (`part6_id`) REFERENCES `part6` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part6_question`
--

LOCK TABLES `part6_question` WRITE;
/*!40000 ALTER TABLE `part6_question` DISABLE KEYS */;
INSERT INTO `part6_question` VALUES (1,'D','Giải thích',1,'(A) are bringing','(B) have brought','(C) bring','(D) are brought',1),(2,'C','Giải thích',2,'(A) This will take a lot of work.','(B) As a result, the convenience shops will be closed.','(C) Because of this, hot meals will not be available for the patrons.','(D) There will be noise and chaos as a result.',1),(3,'C','Giải thích',3,'(A) before','(B) after','(C) during','(D) within',1),(4,'B','Giải thích',4,'(A) develop','(B) improve','(C) rectify','(D) recover',1),(5,'C','Giải thích',1,'(A) account','(B) accountant','(C) accounting','(D) accounted',2),(6,'D','Giải thích',2,'(A) basic','(B) decisive','(C) additional','(D) necessary',2),(7,'B','Giải thích',3,'(A) is being','(B) will be','(C) has been','(D) were being',2),(8,'B','Giải thích',4,'(A) We sincerely thank you for your interest.','(B) The positions begin the following month.','(C) Please call us for more information.','(D) We apologize for any inconvenience',2),(9,'B','Giải thích',1,'(A) durably','(B) durability','(C) durabled','(D) durable',3),(10,'D','Giải thích',2,'(A) Larson’s utensils and silverware go great with the dinnerware.','(B) Our most popular line, the Spring Flower China is sold out at most locations,','(C) Visit our store to check out our other beautiful products.','(D) They are dishwasher- and microwave-safe and we’re confident that you’ll be using them for years to come.',3),(11,'A','Giải thích',3,'(A) result in','(B) occur to','(C) ending at','(D) stop with',3),(12,'C','Giải thích',4,'(A) ambitious','(B) combative','(C) aggressive','(D) complacent',3),(13,'C','Giải thích',1,'(A) distributing','(B) locating','(C) including','(D) advancing',4),(14,'D','Giải thích',2,'(A) were incorporating','(B) should incorporate','(C) are incorporating','(D) could incorporate',4),(15,'B','Giải thích',3,'(A) If we get many suggestions, we could also hold a workshop on the following weekend, December 4th.','(B) We’ll be finalizing our choices by the end of the week.','(C) A large amount of time and energy has gone into organizing this conference.','(D) Inviting friends and family to these events is always encouraged.',4),(16,'C','Giải thích',4,'(A) create','(B) request','(C) submit','(D) transfer',4),(17,'D','Giải thích',1,'(A) personalize','(B) personalizes','(C) personalizing','(D) personalized',5),(18,'C','Giải thích',2,'(A) productive','(B) promoted','(C) residential','(D) relevant',5),(19,'A','Giải thích',3,'(A) Whether','(B) Even if','(C) Even though','(D) Whenever',5),(20,'C','Giải thích',4,'(A) Take a drive out of town and come see us today.','(B) We appreciate your assistance.','(C) Of course, drop-ins are always welcome.','(D) Our kitchen is open from eight to five daily.',5);
/*!40000 ALTER TABLE `part6_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part7`
--

DROP TABLE IF EXISTS `part7`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part7` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `script` text,
  `mock_exam_id` bigint DEFAULT NULL,
  `reading_exercise_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr67s64xifda5qtcpnychr3ocj` (`mock_exam_id`),
  KEY `FKc52lmo9k3ei1jdrjau3m4wu6s` (`reading_exercise_id`),
  CONSTRAINT `FKc52lmo9k3ei1jdrjau3m4wu6s` FOREIGN KEY (`reading_exercise_id`) REFERENCES `reading_exercise` (`id`),
  CONSTRAINT `FKr67s64xifda5qtcpnychr3ocj` FOREIGN KEY (`mock_exam_id`) REFERENCES `mock_exam` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part7`
--

LOCK TABLES `part7` WRITE;
/*!40000 ALTER TABLE `part7` DISABLE KEYS */;
INSERT INTO `part7` VALUES (2,'To: Stacey Johnson <sjohnson@toplineelec.com>\nFrom: Josh Fleck <jfleck@toplineelec.com>\nDate: October 22\nSubject: Strategic Planning\n\nDear Ms. Johnson,\n\nDistrict Management at Topline Electronics would like to inform you about a new development that will be affecting your store. Another competing consumer electronics store will be opening in the Crayville area on November 12.\n\nIn order to ensure that Topline Electronics does not lose business to this new store, District Management is advising you to run a special sale on our new line of curved UHD televisions. These televisions provide state-of-the-art features at moderate prices. By showing your customers that Topline Electronics offers the best deals in the area, you will be able to maintain customer loyalty. We will be shipping the new televisions to your store within the next week. We suggest you prepare a prominent display space in your store to feature them. You should also update your website to advertise the sale.\n\nSmooth communication between you and US is vital to effective operational practices. If you have any questions concerning the new televisions or the marketing campaign in general, please don’t hesitate to contact District Management.\n\nSincerely,\n\nJosh Fleck District Manager,\nTopline Electronics',NULL,12),(5,'Attention Employees, The company will be hosting a health and wellness seminar this Friday at 2 PM in the cafeteria. Join us to learn more about maintaining a healthy lifestyle. - HR Department',NULL,15),(6,'Fulton Stainless steel Products\nFulton Stainless steel Products is a large-scale manufacturer making stainless steel industrial kitchen appliances in factories in Germany and France. The company’s products are trusted by chefs and bakers around the world to be durable, long-lasting, and of superb quality. The company produces kitchen stoves, ovens, microwaves, refrigerators, food processors, electric kettles, coffee makers, and other products.\nAfter acquiring the Visor Home Products Company in a deal last month, the company expected its overall profits to increase 20% compared to the last fiscal year. As a result, to meet increased demand, the company will employ more than 300 full-time workers in our six factories and offer good pay with an excellent benefits package. The company considers all of its employees valuable members of the Fulton family. Additionally, the company continues to research and develop new products to satisfy its customers and compete with other companies.',NULL,16),(7,'Dear Team, Our next meeting is scheduled for Wednesday at 10 AM in the main conference room. Please prepare your reports in advance. - HR Department',NULL,17),(8,'Special Offer! Enjoy a 30% discount on our summer travel packages. Book by June 30th and explore amazing destinations. Call us now! - Travel Agency',NULL,18);
/*!40000 ALTER TABLE `part7` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part7_question`
--

DROP TABLE IF EXISTS `part7_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part7_question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correct_answer` varchar(1) NOT NULL,
  `explanation` text NOT NULL,
  `number` bigint NOT NULL,
  `option_a` varchar(255) NOT NULL,
  `option_b` varchar(255) NOT NULL,
  `option_c` varchar(255) NOT NULL,
  `option_d` varchar(255) NOT NULL,
  `question` varchar(255) NOT NULL,
  `part7_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqnbpcfia4797cql26n8qxwsaq` (`part7_id`),
  CONSTRAINT `FKqnbpcfia4797cql26n8qxwsaq` FOREIGN KEY (`part7_id`) REFERENCES `part7` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part7_question`
--

LOCK TABLES `part7_question` WRITE;
/*!40000 ALTER TABLE `part7_question` DISABLE KEYS */;
INSERT INTO `part7_question` VALUES (1,'A','Giải thích',1,'(A) A store manager','(B) A customer','(C) A product developer','(D) are brought','Who most likely is Ms. Johnson?',2),(2,'C','Giải thích',2,'(A) It recently opened a new store location.','(B) It will be relocated to the Crayville area.','(C) It is concerned about market competition.','(D) It was nominated for an annual award.','What is indicated about Topline Electronics?',2),(3,'B','Giải thích',3,'(A) Add new information to a website','(B) Hire additional staff','(C) Contact Management if necessary','(D) Arrange a sale display area','What is Ms. Johnson NOT instructed to do?',2),(6,'B','The announcement invites employees to a seminar.',1,'(A) To introduce a new policy','(B) To invite employees to a seminar','(C) To announce a holiday','(D) To promote a new product','What is the purpose of this announcement?',5),(7,'B','The seminar is scheduled in the cafeteria.',2,'(A) In the main hall','(B) In the cafeteria','(C) In the gym','(D) In the meeting room','Where will the seminar take place?',5),(8,'B','The seminar focuses on health and wellness.',3,'(A) Career development','(B) Healthy lifestyle','(C) Company policies','(D) Team building','Where will the seminar take place?',5),(9,'B','Giải thích',1,'(A) A car manufacturer','(B) A restaurant','(C) A real estate agency','(D) A clothing store','Who is most likely to be a customer of Fulton Stainless steel Products?',6),(10,'C','Giải thích',2,'(A) It held a press conference.','(B) It opened a new factory.','(C) It obtained a company.','(D) It laid off some workers.','What did Fulton Stainless steel Products do last month?',6),(11,'B','Giải thích',3,'(A) It recently provided extra funding for research and development.','(B) It has released a budget proposal for next year.','(C) It offers a benefits package to its part- time employees.','(D) It plans to hire additional employees to work in factories.','What is mentioned about Fulton Stainless Steel Products?',6),(12,'C','The letter describes a new travel package.',1,'(A) A new flight route','(B) A discount on hotels','(C) A travel package','(D) A car rental service','What is being promoted in the letter?',7),(13,'C','The letter states the promotion lasts one month.',2,'(A) One week','(B) Two weeks','(C) One month','(D) Three months','How long is the promotional period?',7),(14,'A','Frequent travelers are encouraged to book early.',3,'(A) Frequent travelers','(B) Students','(C) Business owners','(D) Local residents','Who is the target audience of the letter?',7),(15,'A','Giải thích',1,'(A) A store manager','(B) A customer','(C) A product developer','(D) are brought','Who most likely is Ms. Johnson?',8),(16,'C','Giải thích',2,'(A) It recently opened a new store location.','(B) It will be relocated to the Crayville area.','(C) It is concerned about market competition.','(D) It was nominated for an annual award.','What is indicated about Topline Electronics?',8),(17,'B','Giải thích',3,'(A) Add new information to a website','(B) Hire additional staff','(C) Contact Management if necessary','(D) Arrange a sale display area','What is Ms. Johnson NOT instructed to do?',8);
/*!40000 ALTER TABLE `part7_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reading_exercise`
--

DROP TABLE IF EXISTS `reading_exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reading_exercise` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `exercise_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reading_exercise`
--

LOCK TABLES `reading_exercise` WRITE;
/*!40000 ALTER TABLE `reading_exercise` DISABLE KEYS */;
INSERT INTO `reading_exercise` VALUES (1,'Test 1'),(2,'Test 2'),(3,'Test 3'),(4,'Test 4'),(5,'Test 5'),(6,'Test 1'),(7,'Test 2'),(8,'Test 3'),(9,'Test 4'),(10,'Test 5'),(12,'Test 1'),(15,'Test 2'),(16,'Test 3'),(17,'Test 4'),(18,'Test 5');
/*!40000 ALTER TABLE `reading_exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(5) DEFAULT 'USER',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_result`
--

DROP TABLE IF EXISTS `test_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_result` (
  `id` int NOT NULL AUTO_INCREMENT,
  `correct_answers` int NOT NULL,
  `incorrect_answers` int NOT NULL,
  `mock_exam_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmm0ojhmejwfjojqxhd7gtttng` (`mock_exam_id`),
  KEY `FKmu7x4i4r6swolpxwqx0n21lnn` (`user_id`),
  CONSTRAINT `FKmm0ojhmejwfjojqxhd7gtttng` FOREIGN KEY (`mock_exam_id`) REFERENCES `mock_exam` (`id`),
  CONSTRAINT `FKmu7x4i4r6swolpxwqx0n21lnn` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_result`
--

LOCK TABLES `test_result` WRITE;
/*!40000 ALTER TABLE `test_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar_url` varchar(255) DEFAULT '/upload-dir/avatar.jpg',
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(50) NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (49,'/upload-dir/avatar/admin-ava.jpg','jobhere.22t.nhat1@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','admin',1),(50,'/upload-dir/avatar/default-avatar.jpg','hongtrangqn834@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','HongTrang834',2),(51,'/upload-dir/avatar/thanhphuong.jpg','phuongsuga333@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','TranThiThanhPhuong',2),(52,'/upload-dir/avatar/tuanloc.jpg','tuanloc782004@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','leo_tulo_6',2),(53,'/upload-dir/avatar/minhquang.jpg','quangtran1106@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','minhquang',2),(54,'/upload-dir/avatar/thanhhuy.jpg','hothanhhuy24062004@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','thanhhuy',2),(55,'/upload-dir/avatar/dinhtoan.jpg','toanledinh76@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','dinhtoan',2),(56,'/upload-dir/avatar/sithao.jpg','supersaza219@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','sithao',2),(57,'/upload-dir/avatar/vanchuong.jpg','nguyenvanchuongwind@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','vanchuong',2),(58,'/upload-dir/avatar/phanthanh.jpg','thanhlongtivip@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','phanthanh',2),(59,'/upload-dir/avatar/banam.jpg','namnguyenba148@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','banam',2),(60,'/upload-dir/avatar/lequyen.jpg','volequyen11042005@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','lequyen',2),(61,'/upload-dir/avatar/tuanvu.jpg','vudoan89@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','tuanvu',2),(62,'/upload-dir/avatar/default-avatar.jpg','letuan123@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','anhtuan',2),(63,'/upload-dir/avatar/default-avatar.jpg','leminh678@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','lemin',2),(64,'/upload-dir/avatar/default-avatar.jpg','thanhviet342@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','thanhviet',2),(65,'/upload-dir/avatar/default-avatar.jpg','huongbinh888@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','huongbinh',2),(66,'/upload-dir/avatar/default-avatar.jpg','khoinguyen789@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','khoinguyen',2),(67,'/upload-dir/avatar/default-avatar.jpg','duytran34@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','leduy',2),(68,'/upload-dir/avatar/default-avatar.jpg','tranhung230904@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','dinhhung',2),(69,'/upload-dir/avatar/default-avatar.jpg','thuclqh@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','hoangthuc',2),(70,'/upload-dir/avatar/default-avatar.jpg','viettu23@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','viettu',2),(71,'/upload-dir/avatar/default-avatar.jpg','test1@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','test1',2),(72,'/upload-dir/avatar/default-avatar.jpg','test5@gmail.com','$2a$10$uX8xty2atSYsbW9AV9r29ObE54h4clLzA4cc1jTMmQsn8/idoRJ0S','test5',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocabulary_lesson`
--

DROP TABLE IF EXISTS `vocabulary_lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vocabulary_lesson` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `lesson_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary_lesson`
--

LOCK TABLES `vocabulary_lesson` WRITE;
/*!40000 ALTER TABLE `vocabulary_lesson` DISABLE KEYS */;
INSERT INTO `vocabulary_lesson` VALUES (1,'Kế hoạch kinh doanh'),(2,'Lương và phúc lợi'),(3,'Máy tính và Internet');
/*!40000 ALTER TABLE `vocabulary_lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocabulary_lesson_content`
--

DROP TABLE IF EXISTS `vocabulary_lesson_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vocabulary_lesson_content` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `audio_url` varchar(255) DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `meaning` varchar(255) NOT NULL,
  `number` bigint NOT NULL,
  `sentence` text NOT NULL,
  `transcribe` varchar(255) DEFAULT NULL,
  `vocabulary_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK659xbx88984vbxtha128ro4du` (`vocabulary_id`),
  CONSTRAINT `FK659xbx88984vbxtha128ro4du` FOREIGN KEY (`vocabulary_id`) REFERENCES `vocabulary_lesson` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary_lesson_content`
--

LOCK TABLES `vocabulary_lesson_content` WRITE;
/*!40000 ALTER TABLE `vocabulary_lesson_content` DISABLE KEYS */;
INSERT INTO `vocabulary_lesson_content` VALUES (1,'/upload-dir/audio/20250409_092714_address.mp3','address','/upload-dir/image/20250409_092714_address.jpg','(n,v): (n) địa chỉ, diễn văn, bài nói chuyện, tác phong nói chuyện, sự khôn khéo; (v) trình bày',1,'Marco\'s business plan addresses the needs of small business owners','/ə\'dres/',1),(2,'/upload-dir/audio/20250409_092714_avoid.mp3','avoid','/upload-dir/image/20250409_092714_avoid.jpg','(v): tránh, tránh khỏi; hủy bỏ, bác bỏ',2,'To avoid going out of business, owners should prepare a proper business plan','/ə\'vɔid/',1),(3,'/upload-dir/audio/20250409_092714_demonstrate.mp3','demonstrate','/upload-dir/image/20250409_092714_demonstrate.jpg','(v): bày tỏ, biểu lộ, cho thấy; chứng minh, giải thích',3,'The professor demonstrated through a case study that a business plan can impress a lender','/\'demənstreit/',1),(4,'/upload-dir/audio/20250409_092714_develop.mp3','develop','/upload-dir/image/20250409_092714_develop.jpg','(v): phát triển, tiến triển, triển khai, mở rộng',4,'Lily developed her ideas into a business plan by taking a class at the community college','/di\'veləp/',1),(5,'/upload-dir/audio/20250409_092714_evaluate.mp3','evaluate','/upload-dir/image/20250409_092714_evaluate.jpg','(v): đánh giá, định giá; ước lượng',5,'It\'s important to evaluate your competition when making a business plan','/i\'væljueit/',1),(6,'/upload-dir/audio/20250409_092714_gather.mp3','gather','/upload-dir/image/20250409_092714_gather.jpg','(v): tập hợp, tụ thập, thu thập; kết luận, suy ra',6,'We gathered information for our plan from many sources','/\'gæðə/',1),(7,'/upload-dir/audio/20250409_092714_offer.mp3','offer','/upload-dir/image/20250409_092714_offer.jpg','(n,v): (n) đề xuất, đề nghị, chào mời, chào hàng, dạm, hỏi, ướm; (v) đề nghị',7,'Devon accepted our offer to write the business plan','/\'ɔfə/',1),(8,'/upload-dir/audio/20250409_092714_primarily.mp3','primarily','/upload-dir/image/20250409_092714_primarily.jpg','(adv): trước hết, đầu tiên; chính, chủ yếu, quan trọng nhất',8,'The developers are thinking primarily of how to enter the South American market','/\'praimərili/',1),(9,'/upload-dir/audio/20250409_092714_risk.mp3','risk','/upload-dir/image/20250409_092714_risk.jpg','n): nguy cơ, sự nguy hiểm, sự rủi ro',9,'The primary risk for most start-up businesses is insufficient capital','/rɪsk/',1),(10,'/upload-dir/audio/20250409_092714_strategy.mp3','strategy','/upload-dir/image/20250409_092714_strategy.jpg','(n): chiến lược, sự vạch kế hoạch hành động',10,'A business plan is a strategy for running a business and avoiding problems','/ˈstrætədʒi/',1),(11,'/upload-dir/audio/20250409_092741_basis.mp3','basis','/upload-dir/image/20250409_092741_basis.jpg','(n): nền tảng, cơ sở, căn cứ',1,'The manager didn\'t have any basis for firing the employee','/\'beisis/',2),(12,'/upload-dir/audio/20250409_092741_benefit.mp3','benefit','/upload-dir/image/20250409_092741_benefit.jpg','(n, v): (n) chế độ đãi ngộ, lợi ích, phúc lợi; (v) được lợi',2,'Although the analyst earned a better salary at his new job, his benefits were better at his previous job','/\'benifit/',2),(13,'/upload-dir/audio/20250409_092741_compensate.mp3','compensate','/upload-dir/image/20250409_092741_compensate.jpg','(v): bù, đền bù, bồi thường',3,'The company will compensate employees for any travel expenses','/\'kɔmpenseit/',2),(14,'/upload-dir/audio/20250409_092741_delicately.mp3','delicately','/upload-dir/image/20250409_092741_delicately.jpg','(adv): ‹một cách› tinh vi, tế nhị, lịch thiệp, khôn khéo, thận trọng',4,'The manager delicately asked about the health of his client','/ˈdelɪkət/',2),(15,'/upload-dir/audio/20250409_092741_eligible.mp3','eligible','/upload-dir/image/20250409_092741_eligible.jpg','(adj): thích hợp, đủ tư cách, đủ tiêu chuẩn; có thể chọn được',5,'Some employees may be eligible for the tuition reimbursement plan','/i\'liminəbl/',2),(16,'/upload-dir/audio/20250409_092741_flexibly.mp3','flexibly','/upload-dir/image/20250409_092741_flexibly.jpg','(adv): ‹một cách› mềm dẻo, linh hoạt, linh động',6,'My manager thinks flexibly, enabling herself to solve many sticky problems','/ˈfleksəbl/',2),(17,'/upload-dir/audio/20250409_092741_negotiate.mp3','negotiate','/upload-dir/image/20250409_092741_negotiate.jpg','(v): thương lượng, đàm phán, điều đình',7,'You must know what you want and what you can accept when you negotiate a salary','/ni\'gouʃieit/',2),(18,'/upload-dir/audio/20250409_092741_raise.mp3','raise','/upload-dir/image/20250409_092741_raise.jpg','(n, v): (n) ‹sự› nâng lên, tăng lên, tăng lương; (v) nâng lên, đưa lên',8,' We need to raise the standard for timeliness','/reiz/',2),(19,'/upload-dir/audio/20250409_092741_retire.mp3','retire','/upload-dir/image/20250409_092741_retire.jpg','(v): nghỉ hưu',9,'She was forced to retire early from teaching because of ill health','/ri\'taiə/',2),(20,'/upload-dir/audio/20250409_092741_vested.mp3','vested','/upload-dir/image/20250409_092741_vested.jpg','(adj): được trao, được ban, được phong; quyền được bảo đảm',10,'The day that Ms. Weng became fully vested in the retirement plan, she gave her two weeks\' notice','/\'vestid/',2),(21,'/upload-dir/audio/20250409_092821_access.mp3','access','/upload-dir/image/20250409_092821_access.jpg','(n, v): (n) lối vào, đường vào, sự/quyền truy cập; (v) truy cập',1,'You can\'t gain access to the files unless you know the password','/ˈækses/',3),(22,'/upload-dir/audio/20250409_092821_allocate.mp3','allocate','/upload-dir/image/20250409_092821_allocate.jpg','(v): cấp cho, phân phối, phân phát, chia phần; chỉ định, định rõ vị trí',2,'The office manager did not allocate enough money to purchase software','/ˈæləkeɪt/',3),(23,'/upload-dir/audio/20250409_092821_compatible.mp3','compatible','/upload-dir/image/20250409_092821_compatible.jpg','(adj): tương thích, tương hợp, hợp nhau, có thể dùng được với nhau',3,'This operating system is not compatible with this model computer','/kəm\'pætəbl/',3),(24,'/upload-dir/audio/20250409_092821_delete.mp3','delete','/upload-dir/image/20250409_092821_delete.jpg',' (v): xóa đi, bỏ đi, gạch đi (to remove, to erase)',4,'The technicians deleted all the data on the disk accidentally','/di\'li:t/',3),(25,'/upload-dir/audio/20250409_092821_display.mp3','display','/upload-dir/image/20250409_092821_display.jpg','(v, n): (v) hiển thị, biểu lộ, phô bày, trình bày, trưng bày; (n) sự trưng bày',5,'The accounting program displays a current balance when opened.','/dis\'plei/',3),(26,'/upload-dir/audio/20250409_092821_duplicate.mp3','duplicate','/upload-dir/image/20250409_092821_duplicate.jpg','(v): sao lại, làm thành 2 bản, gấp đôi, nhân đôi',6,'I think the new word processing program will duplicate the success of the one introduced last year','/\'dju:plikit/',3),(27,'/upload-dir/audio/20250409_092821_failure.mp3','failure','/upload-dir/image/20250409_092821_failure.jpg','(n): hỏng, thiếu, yếu, trượt, thất bại, bất thành',7,'Your failure to inform us about the changed password cost the company a day\'s work','/\'feiljə/',3),(28,'/upload-dir/audio/20250409_092821_figure_out.mp3','figure out','/upload-dir/image/20250409_092821_figure_out.jpg',' (v): tìm hiểu, đoán ra, tính toán ra, giải ra',8,'By examining all of the errors, the technicians figured out how to fix the problem','/ˈfɪɡə/ /aʊt/',3),(29,'/upload-dir/audio/20250409_092821_ignore.mp3','ignore','/upload-dir/image/20250409_092821_ignore.jpg','(v): bỏ qua, phớt lờ, không để ý tới',9,'He ignored all the \'No Smoking\' signs and lit up a cigarette','/ig\'nɔ:/',3),(30,'/upload-dir/audio/20250409_092821_search.mp3','search','/upload-dir/image/20250409_092821_search.jpg',' (n, v): (n) tìm kiếm, tìm hiểu; điều tra, thăm dò; (v) tìm',10,' Our search of the database produced very little information','/sə:tʃ/',3);
/*!40000 ALTER TABLE `vocabulary_lesson_content` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-09  9:43:00
