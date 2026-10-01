//Vu Le Kien
//MSV 251020905771

package buoi3;
class InvoiceItem {
    // Các thuộc tính private
    private String id;
    private String desc;
    private int qty;
    private double unitPrice;

    // Hàm khởi tạo (Constructor)
    public InvoiceItem(String id, String desc, int qty, double unitPrice) {
        this.id = id;
        this.desc = desc;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    // Các phương thức Getter và Setter
    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    // Phương thức tính tổng tiền: unitPrice * qty
    public double getTotal() {
        return unitPrice * qty;
    }

    // Phương thức chuyển đổi đối tượng thành Chuỗi
    @Override
    public String toString() {
        return "InvoiceItem[id=" + id + ",desc=" + desc + ",qty=" + qty + ",unitPrice=" + unitPrice + "]";
    }
}

public class bai1 {
    public static void main(String[] args) {
        // Khởi tạo một đối tượng InvoiceItem
        InvoiceItem item1 = new InvoiceItem("A101", "Pen Red", 888, 0.08);
        System.out.println(item1);  // Gọi phương thức toString()

        // Kiểm tra các phương thức setter
        item1.setQty(999);
        item1.setUnitPrice(0.99);
        System.out.println(item1);

        // Kiểm tra các phương thức getter và getTotal()
        System.out.println("id is: " + item1.getId());
        System.out.println("desc is: " + item1.getDesc());
        System.out.println("qty is: " + item1.getQty());
        System.out.println("unitPrice is: " + item1.getUnitPrice());
        System.out.println("The total is: " + item1.getTotal());
    }
}