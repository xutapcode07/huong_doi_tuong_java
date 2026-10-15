package buoi4.bai3;

public class MyLine {

    private MyPoint begin;
    private MyPoint end;

    // Constructor với 4 tọa độ
    public MyLine(int x1, int y1, int x2, int y2) {
        begin = new MyPoint(x1, y1);
        end = new MyPoint(x2, y2);
    }

    // Constructor với 2 MyPoint
    public MyLine(MyPoint begin, MyPoint end) {
        this.begin = begin;
        this.end = end;
    }

    // getBegin
    public MyPoint getBegin() {
        return begin;
    }

    // setBegin
    public void setBegin(MyPoint begin) {
        this.begin = begin;
    }

    // getEnd
    public MyPoint getEnd() {
        return end;
    }

    // setEnd
    public void setEnd(MyPoint end) {
        this.end = end;
    }

    // getBeginX
    public int getBeginX() {
        return begin.getX();
    }

    // setBeginX
    public void setBeginX(int x) {
        begin.setX(x);
    }

    // getBeginY
    public int getBeginY() {
        return begin.getY();
    }

    // setBeginY
    public void setBeginY(int y) {
        begin.setY(y);
    }

    // getEndX
    public int getEndX() {
        return end.getX();
    }

    // setEndX
    public void setEndX(int x) {
        end.setX(x);
    }

    // getEndY
    public int getEndY() {
        return end.getY();
    }

    // setEndY
    public void setEndY(int y) {
        end.setY(y);
    }

    // getBeginXY
    public int[] getBeginXY() {
        return begin.getXY();
    }

    // setBeginXY
    public void setBeginXY(int x, int y) {
        begin.setXY(x, y);
    }

    // getEndXY
    public int[] getEndXY() {
        return end.getXY();
    }

    // setEndXY
    public void setEndXY(int x, int y) {
        end.setXY(x, y);
    }

    // Tính độ dài đường thẳng
    public double getLength() {
        return begin.distance(end);
    }

    // Tính góc của đường thẳng
    public double getGradient() {
        int xDiff = end.getX() - begin.getX();
        int yDiff = end.getY() - begin.getY();

        return Math.atan2(yDiff, xDiff);
    }

    // toString
    @Override
    public String toString() {
        return "MyLine[begin=" + begin + ",end=" + end + "]";
    }
}