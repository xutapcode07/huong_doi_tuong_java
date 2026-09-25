//Vũ Lê Kiên
//MSV: 251020905771

import java.util.Scanner;

public class bai5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N;

        while (true){
            System.out.print("Nhap so nguyen duong N: ");
            N = scanner.nextInt();
            if (N > 0){
                break;
            }
            System.out.print("N phai lon hon 0. Vui long nhap lai!");
        }

        int tong = 0;
        int i = 1;

        while (i <= N){
            tong += i;
            i++;
        }
        System.out.println("Tong cac so nguyen tu 1 den " + N + " la: " + tong);

        scanner.close();
    }
}
