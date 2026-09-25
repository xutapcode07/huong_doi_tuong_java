//Vũ Lê Kiên
//MSV: 251020905771

import java.util.Scanner;

public class bai3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap thang (1-12): ");
        int month = scanner.nextInt();

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println("Thang " + month + " co 31 ngay.");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println("Thang " + month + " co 30 ngay.");
                break;
            case 2:
                System.out.println("Thang 2 co 28 ngay.");
                break;
            default:
                System.out.println("Thang khong hop le! Vui long nhap so tu 1 den 12.");
                break;
        }

        scanner.close();
    }
}
