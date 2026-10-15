package buoi4.bai2;

public class Invoice {
    private int id;
    private Customer customer;
    private double amount;

    // Constructor
    public Invoice(int id, Customer customer, double amount) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
    }

    // Getter
    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }

    // Setter
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Lấy ID của Customer
    public int getCustomerId() {
        return customer.getId();
    }

    // Lấy tên Customer
    public String getCustomerName() {
        return customer.getName();
    }

    // Lấy discount
    public int getCustomerDiscount() {
        return customer.getDiscount();
    }

    // Tính số tiền sau khi giảm giá
    public double getAmountAfterDiscount() {
        return amount * (1 - customer.getDiscount() / 100.0);
    }

    // toString
    @Override
    public String toString() {
        return "Invoice[id=" + id + ",customer=" + customer + ",amount=" + amount + "]";
    }
}