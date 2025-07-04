MedGo 🏥

Ứng dụng Android đặt lịch khám bệnh tại các cơ sở y tế. Hỗ trợ quản lý hồ sơ bệnh nhân, xem lịch khám, và thực hiện đặt lịch với quy trình từng bước.

✨ Tính năng chính

🏠 Trang chủ: Tìm kiếm, xem tin tức, sự kiện y tế

⏰ Lịch khám: Xem danh sách lịch khám và trạng thái (chưa thanh toán, đã thanh toán, đã hủy, đã khám...)

➕ Đặt lịch: Chọn triệu chứng, khoa, cơ sở, bác sĩ, giờ khám, thanh toán

👥 Hồ sơ bệnh nhân: Quản lý nhiều hồ sơ cho gia đình

📅 Tài khoản: Đăng nhập/Đăng ký, xem thông tin cá nhân

🚀 Cách chạy project

Mở project bằng Android Studio Flamingo trở lên

Kết nối với thiết bị ảo hoặc điện thoại Android (API 29+)

Nhấn Run ▶️ để build và chạy app

🚫 Lưu ý: File local.properties chứa đường dẫn SDK sẽ được Git ignore, nên hãy đảm bảo Android Studio đã cài SDK

🤹 Cách clone repo và làm việc

git clone https://github.com/hongcer11/MedGo.git
cd MedGo
git checkout -b feature/ten-nhiem-vu

📚 Mỗi người code trong branch riêng, sau đó tạo pull request để gộp vào main

🔧 Cấu trúc project (tóm tắt)

MedGo/
├── app/
│   ├── src/
│   │   ├── main/java/com/group4/medgo/
│   │   │   ├── ui/                # Fragment theo module
│   │   │   ├── data/              # model, database, repository
│   │   │   └── MainActivity.java
│   │   └── res/
│   │       ├── layout/, values/, mipmap/, drawable/
└── .gitignore, build.gradle.kts, settings.gradle.kts

🙌 Góp ý / Đóng góp

Fork repo và tạo pull request

Hoặc thêm issue nếu bạn gặp bug hay có ý tưởng mới

🌟 Credits

Nhóm 4 - Project Môn Lập trình di động
