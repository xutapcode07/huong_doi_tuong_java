package buoi4.bai2;

public class bai2 {

    public static void main(String[] args) {

        // TAO DOI TUONG CUSTOMER

        Customer customer = new Customer(
                1,
                "Vu Le Kien",
                10
        );

        System.out.println("===== THONG TIN KHACH HANG =====");

        System.out.println("Ma khach hang: "
                + customer.getId());

        System.out.println("Ten khach hang: "
                + customer.getName());

        System.out.println("Phan tram giam gia: "
                + customer.getDiscount() + "%");

        System.out.println("Thong tin khach hang: "
                + customer);

        // TAO HOA DON

        Invoice invoice = new Invoice(
                1001,
                customer,
                1000.0
        );

        System.out.println();
        System.out.println("===== THONG TIN HOA DON =====");

        System.out.println("Ma hoa don: "
                + invoice.getId());

        System.out.println("Khach hang: "
                + invoice.getCustomer());

        System.out.println("So tien hoa don: "
                + invoice.getAmount());

        System.out.println("Ma khach hang: "
                + invoice.getCustomerId());

        System.out.println("Ten khach hang: "
                + invoice.getCustomerName());

        System.out.println("Phan tram giam gia: "
                + invoice.getCustomerDiscount() + "%");

        System.out.println("So tien sau khi giam gia: "
                + invoice.getAmountAfterDiscount());

        System.out.println("Thong tin hoa don: "
                + invoice);

        // THAY DOI DISCOUNT

        customer.setDiscount(20);

        System.out.println();
        System.out.println("===== SAU KHI THAY DOI GIAM GIA =====");

        System.out.println("Phan tram giam gia moi: "
                + customer.getDiscount() + "%");

        System.out.println("So tien ban dau: "
                + invoice.getAmount());

        System.out.println("So tien sau khi giam gia moi: "
                + invoice.getAmountAfterDiscount());
    }
}