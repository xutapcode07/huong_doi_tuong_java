//Vu Le Kien
//MSV 251020905771

package buoi3;

class Ball {
    // Thuộc tính private
    private float x;
    private float y;
    private int radius;
    private float xDelta;
    private float yDelta;

    // Constructor
    public Ball(float x, float y, int radius, float xDelta, float yDelta) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.xDelta = xDelta;
        this.yDelta = yDelta;
    }

    // Getters
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public float getXDelta() {
        return xDelta;
    }

    public float getYDelta() {
        return yDelta;
    }

    // Setters
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setXDelta(float xDelta) {
        this.xDelta = xDelta;
    }

    public void setYDelta(float yDelta) {
        this.yDelta = yDelta;
    }

    // Di chuyển quả bóng 1 bước
    public void move() {
        x += xDelta;
        y += yDelta;
    }

    // Đổi hướng di chuyển ngang (khi va chạm tường trái/phải)
    public void reflectHorizontal() {
        xDelta = -xDelta;
    }

    // Đổi hướng di chuyển dọc (khi va chạm tường trên/dưới)
    public void reflectVertical() {
        yDelta = -yDelta;
    }

    // Chuyển đối tượng thành Chuỗi dạng "Ball[(x,y),speed=(xDelta,yDelta)]"
    @Override
    public String toString() {
        return "Ball[(" + x + "," + y + "),speed=(" + xDelta + "," + yDelta + ")]";
    }
}
public class bai5 {
    public static void main(String[] args) {
        // Khởi tạo quả bóng tại tọa độ (1.1, 2.2), bán kính 10, xDelta = 0.5, yDelta = 1.5
        Ball ball = new Ball(1.1f, 2.2f, 10, 0.5f, 1.5f);
        System.out.println(ball); // Ball[(1.1,2.2),speed=(0.5,1.5)]

        // Thử di chuyển bóng
        ball.move();
        System.out.println("After move: " + ball); // Ball[(1.6,3.7),speed=(0.5,1.5)]

        // Nảy ngang (đổi hướng x)
        ball.reflectHorizontal();
        ball.move();
        System.out.println("After reflectHorizontal and move: " + ball);

        // Nảy dọc (đổi hướng y)
        ball.reflectVertical();
        ball.move();
        System.out.println("After reflectVertical and move: " + ball);
    }
}
