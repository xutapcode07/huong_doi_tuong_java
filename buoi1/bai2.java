//Vũ Lê Kiên
//MSV: 251020905771/

import java.util.Scanner;

public class bai2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap so thu nhat: ");
        int a = scanner.nextInt();

        System.out.print("Nhap so thu hai: ");
        int b = scanner.nextInt();

        System.out.print("Nhap so thứ ba: ");
        int c = scanner.nextInt();

        int max = a;

        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }
        System.out.println("So lon nhat trong 3 so la: " + max);
        scanner.close();
    }
}
