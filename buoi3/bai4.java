//Vu Le Kien
//MSV 251020905771

package buoi3;

class Time {
    // Thuộc tính private
    private int hour;
    private int minute;
    private int second;

    // Constructor
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // Getters
    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    // Setters
    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    // Cập nhật cả 3 thuộc tính
    public void setTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // Chuyển thành chuỗi "hh:mm:ss"
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    // Tăng thời gian lên 1 giây
    public Time nextSecond() {
        second++;
        if (second >= 60) {
            second = 0;
            minute++;
            if (minute >= 60) {
                minute = 0;
                hour++;
                if (hour >= 24) {
                    hour = 0;
                }
            }
        }
        return this; // Trả về chính đối tượng này
    }

    // Giảm thời gian đi 1 giây
    public Time previousSecond() {
        second--;
        if (second < 0) {
            second = 59;
            minute--;
            if (minute < 0) {
                minute = 59;
                hour--;
                if (hour < 0) {
                    hour = 23;
                }
            }
        }
        return this; // Trả về chính đối tượng này
    }
}
public class bai4 {
    public static void main(String[] args) {
        // Tạo thời gian 23:59:58
        Time t1 = new Time(23, 59, 58);
        System.out.println(t1); // 23:59:58

        // Tăng 1 giây
        t1.nextSecond();
        System.out.println(t1); // 23:59:59

        // Tăng tiếp 1 giây nữa (chuyển sang ngày mới 00:00:00)
        t1.nextSecond();
        System.out.println(t1); // 00:00:00

        // Giảm 1 giây (quay lại 23:59:59)
        t1.previousSecond();
        System.out.println(t1); // 23:59:59

        // Có thể gọi liên tiếp vì hàm trả về this
        t1.nextSecond().nextSecond();
        System.out.println(t1); // 00:00:01
    }
}
