public class InvoiceItem {
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
