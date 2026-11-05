package buoi14;

import java.io.*;
import java.util.*;

public class MainBai2 {

    public static void main(String[] args) {
        String binaryFile = "shapes.dat";

        // 1. Tạo danh sách ban đầu gồm ít nhất 6 hình
        List<Shape> originalList = new ArrayList<>();
        originalList.add(new Circle(2.5, "red", true));
        originalList.add(new Rectangle(3.0, 4.0, "blue", false));
        originalList.add(new Square(5.0, "green", true));
        originalList.add(new Circle(1.0, "yellow", false));
        originalList.add(new Rectangle(5.0, 2.0, "black", true));
        originalList.add(new Square(2.5, "orange", false));

        // Ghi danh sách vào file nhị phân
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(binaryFile))) {
            oos.writeObject(originalList);
            System.out.println("Đã ghi thành công " + originalList.size() + " hình vào " + binaryFile);
        } catch (IOException e) {
            System.err.println("Lỗi ghi file nhị phân: " + e.getMessage());
        }

        // 2. Đọc lại danh sách từ file nhị phân
        List<Shape> readList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(binaryFile))) {
            readList = (List<Shape>) ois.readObject();
            System.out.println("Đã đọc thành công file " + binaryFile + "\n");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi đọc file nhị phân: " + e.getMessage());
        }

        // Hiển thị thông tin danh sách đọc được
        System.out.println("=== DỮ LIỆU ĐỌC ĐƯỢC TỪ FILE NHỊ PHÂN ===");
        for (Shape s : readList) {
            System.out.printf("%s | Diện tích: %.2f | Chu vi: %.2f\n", s.toString(), s.getArea(), s.getPerimeter());
        }

        // 3. Kiểm tra dữ liệu (So sánh 2 danh sách)
        System.out.println("\n=== KIỂM TRA DỮ LIỆU ===");
        boolean isSameSize = originalList.size() == readList.size();
        System.out.println("Số lượng phần tử trùng khớp: " + isSameSize);

        boolean isContentMatched = true;
        for (int i = 0; i < originalList.size(); i++) {
            Shape s1 = originalList.get(i);
            Shape s2 = readList.get(i);
            if (!s1.toString().equals(s2.toString()) || s1.getArea() != s2.getArea()) {
                isContentMatched = false;
                break;
            }
        }
        System.out.println("Nội dung tất cả các hình trùng khớp: " + isContentMatched);
    }
}