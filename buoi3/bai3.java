//Vu Le Kien
//MSV 251020905771

package buoi3;
class Date {
    // Thuộc tính private
    private int day;
    private int month;
    private int year;

    // Constructor
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Getters
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    // Setters
    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Cập nhật cả 3 thuộc tính
    public void setDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Chuyển đối tượng thành Chuỗi dạng "dd/mm/yyyy" có số 0 ở đầu
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }
}
public class bai3 {
    public static void main(String[] args) {
        // Khởi tạo ngày 1/2/2024
        Date d1 = new Date(1, 2, 2024);
        System.out.println(d1); // Kết quả sẽ là: 01/02/2024

        // Kiểm tra các setter
        d1.setMonth(12);
        d1.setDay(9);
        d1.setYear(2025);
        System.out.println(d1); // Kết quả: 09/12/2025

        // Kiểm tra setDate()
        d1.setDate(5, 5, 2026);
        System.out.println(d1); // Kết quả: 05/05/2026
    }
}
