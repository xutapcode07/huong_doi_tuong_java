# Calculator using Java Swing

Ứng dụng máy tính bỏ túi (Calculator) viết bằng Java Swing, hỗ trợ các phép tính cơ bản và nâng cao, giao diện màu sắc rõ ràng, có xử lý lỗi và điều khiển bằng bàn phím.

## Tính năng

- Các phép tính cơ bản: cộng (`+`), trừ (`-`), nhân (`x`), chia (`/`)
- Các phép tính mở rộng:
  - `±`: đổi dấu dương/âm
  - `%`: tính phần trăm (chia số hiện tại cho 100)
  - `←`: xóa lùi từng ký tự (Backspace)
- Tính liên tiếp như máy tính thông thường (ví dụ `5 + 3 + 2` sẽ tính `5 + 3 = 8` trước rồi cộng tiếp 2)
- Sau khi bấm `=`, nhập số mới sẽ xóa kết quả cũ để bắt đầu phép tính mới
- Giao diện thân thiện, tự co giãn theo kích thước cửa sổ (`BorderLayout` + `GridLayout`)
- Phân biệt màu sắc giữa các nhóm nút:
  - Phím số: màu trắng/xám sáng
  - Phím phép tính `+ - x / =`: màu cam
  - Phím chức năng `± % ←`: màu xám đậm
  - Phím `C`: màu đỏ
- Màn hình hiển thị font lớn, căn lề phải; cửa sổ xuất hiện giữa màn hình
- Bắt lỗi:
  - Chia cho 0 hiển thị thông báo `Error: Cannot divide by zero`, chương trình không bị văng lỗi
  - Chỉ cho phép một dấu chấm thập phân trong mỗi số (không thể nhập `5.2.3`)
- Hỗ trợ bàn phím (xem bảng bên dưới)

## Cách sử dụng

1. Nhập số bằng các nút `0-9` (hoặc bàn phím).
2. Chọn phép tính `+`, `-`, `x`, `/`.
3. Dùng nút `.` để nhập số thập phân.
4. Dùng `±` để đổi dấu, `%` để tính phần trăm, `←` để xóa lùi một ký tự.
5. Bấm `=` để hiển thị kết quả.
6. Bấm `C` để xóa toàn bộ và bắt đầu lại.

### Phím tắt bàn phím

| Phím | Chức năng |
|---|---|
| `0` - `9` | Nhập chữ số |
| `.` | Dấu thập phân |
| `+` `-` `*` `/` | Phép cộng, trừ, nhân, chia |
| `%` | Phần trăm |
| `Enter` hoặc `=` | Tính kết quả |
| `Backspace` | Xóa lùi một ký tự |
| `Esc` hoặc `Delete` | Xóa toàn bộ (Clear) |

## Các lỗi đã sửa so với bản gốc

| Vấn đề | Nguyên nhân | Cách sửa |
|---|---|---|
| Nút bị tràn ra ngoài cửa sổ | Dùng `setLayout(null)` và `setBounds` với tọa độ lớn hơn cửa sổ 400x500 (x tới 1500, y tới 700) | Dùng `BorderLayout` cho tổng thể và `GridLayout(5, 4)` cho bàn phím |
| Lỗi cú pháp ở phép trừ, nhân (`result = a \n b;`) | Ký tự xuống dòng chen vào giữa toán hạng và toán tử | Viết lại đúng `a - b`, `a * b` trong `switch` |
| Số mới bị nối vào kết quả cũ, không tính liên tiếp được | Không có cờ theo dõi trạng thái nhập liệu | Thêm cờ `newInput`, và xử lý tính liên tiếp trong `inputOperator()` |
| Chia cho 0 trả về `Infinity` | Không kiểm tra mẫu số | Kiểm tra `b == 0` và hiển thị thông báo lỗi |
| Nhập nhiều dấu `.` gây `NumberFormatException` | Không kiểm tra dấu chấm đã tồn tại | Chỉ thêm `.` khi số hiện tại chưa có dấu chấm |

## Cài đặt và chạy

Cần cài đặt JDK (phiên bản 8 trở lên).

```bash
javac Calculator.java
java Calculator
```

## Cấu trúc dự án

- **Calculator.java**: Mã nguồn chính của máy tính. Lớp `Calculator` kế thừa `JFrame` và cài đặt `ActionListener`.
  - `handle(String cmd)`: hàm xử lý trung tâm, dùng chung cho cả chuột và bàn phím
  - `setupKeyboard()`: đăng ký phím tắt bằng Key Bindings
  - `compute()`, `format()`, `showError()`: tính toán, định dạng kết quả và hiển thị lỗi
- **README.md**: Tài liệu dự án.
- **Images**: Thư mục chứa ảnh minh họa.

## Ảnh minh họa

![Calculator Output](Images/Output1.png)
![Calculator Output](Images/Output2.png)

> Thay hai ảnh trên bằng ảnh chụp màn hình giao diện mới của bạn.

Chúc bạn tính toán vui vẻ! 🧮✨
