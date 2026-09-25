//Vũ Lê Kiên
//MSV: 251020905771

import java.util.Scanner;

public class bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("nhap chieu dai: ");
        double dai = sc.nextDouble();

        System.out.print("nhap chieu rong: ");
        double rong = sc.nextDouble();

        Double dientich = dai * rong;
        Double chuvi = 2*(dai + rong);

        System.out.println("dien tich = " + dientich);
        System.out.println("chu vi = " + chuvi);

        sc.close();
    }
}
