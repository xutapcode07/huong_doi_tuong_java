package buoi14;

import java.io.*;
import java.util.*;

public class MainBai1 {

    public static void main(String[] args) {
        String inputFile = "shapes.txt";
        String outputFile = "output.txt";
        String sortedOutputFile = "sorted_output.txt";

        // Tạo file mẫu shapes.txt nếu chưa tồn tại để test
        createSampleInputFile(inputFile);

        // 3. Đọc danh sách hình học từ file văn bản
        List<Shape> shapes = readShapesFromFile(inputFile);

        // 3. Hiển thị danh sách ra màn hình
        System.out.println("=== DANH SÁCH HÌNH HỌC TỪ FILE ===");
        for (Shape s : shapes) {
            System.out.printf("%s | Diện tích: %.2f | Chu vi: %.2f\n", s.toString(), s.getArea(), s.getPerimeter());
        }

        // 4. Thống kê
        double totalArea = 0;
        double totalPerimeter = 0;
        int countCircle = 0;
        int countRectangle = 0;
        int countSquare = 0;

        for (Shape s : shapes) {
            totalArea += s.getArea();
            totalPerimeter += s.getPerimeter();

            // Sử dụng instanceof để đếm từng loại hình
            if (s instanceof Square) {
                countSquare++;
            } else if (s instanceof Rectangle) {
                countRectangle++;
            } else if (s instanceof Circle) {
                countCircle++;
            }
        }

        System.out.println("\n=== THỐNG KÊ ===");
        System.out.printf("Tổng diện tích: %.2f\n", totalArea);
        System.out.printf("Tổng chu vi: %.2f\n", totalPerimeter);
        System.out.println("Số lượng Circle: " + countCircle);
        System.out.println("Số lượng Rectangle: " + countRectangle);
        System.out.println("Số lượng Square: " + countSquare);

        // 5. Ghi kết quả ra file output.txt
        writeOutputToFile(outputFile, shapes, totalArea, totalPerimeter, countCircle, countRectangle, countSquare);

        // 6. Sắp xếp danh sách theo diện tích giảm dần và ghi vào sorted_output.txt
        shapes.sort(new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                return Double.compare(o2.getArea(), o1.getArea()); // Giảm dần
            }
        });

        writeSortedOutputToFile(sortedOutputFile, shapes);
    }

    private static List<Shape> readShapesFromFile(String filename) {
        List<Shape> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                String type = parts[0].trim();

                if (type.equalsIgnoreCase("Circle")) {
                    double radius = Double.parseDouble(parts[1].trim());
                    String color = parts[2].trim();
                    boolean filled = Boolean.parseBoolean(parts[3].trim());
                    list.add(new Circle(radius, color, filled));

                } else if (type.equalsIgnoreCase("Rectangle")) {
                    double width = Double.parseDouble(parts[1].trim());
                    double length = Double.parseDouble(parts[2].trim());
                    String color = parts[3].trim();
                    boolean filled = Boolean.parseBoolean(parts[4].trim());
                    list.add(new Rectangle(width, length, color, filled));

                } else if (type.equalsIgnoreCase("Square")) {
                    double side = Double.parseDouble(parts[1].trim());
                    String color = parts[2].trim();
                    boolean filled = Boolean.parseBoolean(parts[3].trim());
                    list.add(new Square(side, color, filled));
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file: " + e.getMessage());
        }
        return list;
    }

    private static void writeOutputToFile(String filename, List<Shape> shapes, double totalArea, double totalPerimeter, int cCircle, int cRect, int cSquare) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("=== DANH SÁCH HÌNH HỌC ===");
            for (Shape s : shapes) {
                pw.printf("%s | DT: %.2f | CV: %.2f\n", s.toString(), s.getArea(), s.getPerimeter());
            }
            pw.println("\n=== THỐNG KÊ ===");
            pw.printf("Tổng diện tích: %.2f\n", totalArea);
            pw.printf("Tổng chu vi: %.2f\n", totalPerimeter);
            pw.println("Số lượng Circle: " + cCircle);
            pw.println("Số lượng Rectangle: " + cRect);
            pw.println("Số lượng Square: " + cSquare);
            System.out.println("\nĐã ghi kết quả vào file: " + filename);
        } catch (IOException e) {
            System.err.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    private static void writeSortedOutputToFile(String filename, List<Shape> shapes) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("=== DANH SÁCH SẮP XẾP THEO DIỆN TÍCH GIẢM DẦN ===");
            for (Shape s : shapes) {
                pw.printf("%s | Diện tích: %.2f\n", s.toString(), s.getArea());
            }
            System.out.println("Đã ghi danh sách sắp xếp vào file: " + filename);
        } catch (IOException e) {
            System.err.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    private static void createSampleInputFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("Circle,1.5,red,true");
            pw.println("Rectangle,2.0,3.0,blue,false");
            pw.println("Square,4.0,green,true");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}