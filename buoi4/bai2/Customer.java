package buoi4.bai2;

public class Customer {
    private int id;
    private String name;
    private int discount;

    // Constructor
    public Customer(int id, String name, int discount) {
        this.id = id;
        this.name = name;
        this.discount = discount;
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDiscount() {
        return discount;
    }

    // Setter
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    // toString
    @Override
    public String toString() {
        return name + "(" + id + ")(" + discount + "%)";
    }
}