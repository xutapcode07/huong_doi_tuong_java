package buoi4.bai3;

public class MyPoint {

    private int x = 0;
    private int y = 0;

    // Constructor mặc định
    public MyPoint() {
    }

    // Constructor có tham số
    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getX
    public int getX() {
        return x;
    }

    // setX
    public void setX(int x) {
        this.x = x;
    }

    // getY
    public int getY() {
        return y;
    }

    // setY
    public void setY(int y) {
        this.y = y;
    }

    // getXY
    public int[] getXY() {
        return new int[]{x, y};
    }

    // setXY
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // toString
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    // Khoảng cách từ điểm hiện tại đến điểm (x,y)
    public double distance(int x, int y) {
        int xDiff = this.x - x;
        int yDiff = this.y - y;

        return Math.sqrt(
                xDiff * xDiff +
                        yDiff * yDiff
        );
    }

    // Khoảng cách từ điểm hiện tại đến một MyPoint khác
    public double distance(MyPoint another) {
        return distance(
                another.getX(),
                another.getY()
        );
    }

    // Khoảng cách từ điểm hiện tại đến (0,0)
    public double distance() {
        return Math.sqrt(
                x * x +
                        y * y
        );
    }
}