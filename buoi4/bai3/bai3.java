package buoi4.bai3;

public class bai3 {

    public static void main(String[] args) {

        // KIEM TRA LOP MyPoint

        MyPoint p1 = new MyPoint();
        MyPoint p2 = new MyPoint(3, 4);

        System.out.println("===== KIEM TRA LOP MYPOINT =====");

        System.out.println("Toa do diem p1: " + p1);
        System.out.println("Toa do diem p2: " + p2);

        System.out.println("Hoanh do X cua p2: " + p2.getX());
        System.out.println("Tung do Y cua p2: " + p2.getY());

        System.out.println("Khoang cach tu p2 den goc toa do (0,0): "
                + p2.distance());

        System.out.println("Khoang cach tu p2 den diem (6,8): "
                + p2.distance(6, 8));

        System.out.println("Khoang cach tu p1 den p2: "
                + p1.distance(p2));

        // KIEM TRA LOP MyLine

        MyLine line1 = new MyLine(0, 0, 3, 4);

        System.out.println();
        System.out.println("===== KIEM TRA LOP MYLINE =====");

        System.out.println("Duong thang ban dau: " + line1);

        System.out.println("Diem dau cua duong thang: "
                + line1.getBegin());

        System.out.println("Diem cuoi cua duong thang: "
                + line1.getEnd());

        System.out.println("Do dai duong thang: "
                + line1.getLength());

        System.out.println("Goc cua duong thang (radian): "
                + line1.getGradient());

        // THAY DOI TOA DO

        line1.setBeginXY(1, 2);
        line1.setEndXY(4, 6);

        System.out.println();
        System.out.println("===== SAU KHI THAY DOI TOA DO =====");

        System.out.println("Duong thang sau khi thay doi: "
                + line1);

        System.out.println("Diem dau moi: "
                + line1.getBegin());

        System.out.println("Diem cuoi moi: "
                + line1.getEnd());

        System.out.println("Do dai moi cua duong thang: "
                + line1.getLength());

        System.out.println("Goc moi cua duong thang (radian): "
                + line1.getGradient());
    }
}