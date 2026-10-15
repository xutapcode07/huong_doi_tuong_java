package buoi4.bai1;

public class bai1 {

    public static void main(String[] args) {

        // TAO DOI TUONG AUTHOR

        Author author = new Author(
                "Nguyen Van A",
                "nguyenvana@gmail.com",
                'm'
        );

        System.out.println("===== THONG TIN TAC GIA =====");

        System.out.println("Ten tac gia: "
                + author.getName());

        System.out.println("Email: "
                + author.getEmail());

        System.out.println("Gioi tinh: "
                + author.getGender());

        System.out.println("Thong tin tac gia: "
                + author);


        // TAO DOI TUONG BOOK

        Book book1 = new Book(
                "Java Programming",
                author,
                20.0
        );

        Book book2 = new Book(
                "OOP Java",
                author,
                30.0,
                10
        );

        System.out.println();
        System.out.println("===== THONG TIN SACH =====");

        System.out.println("Sach 1: "
                + book1);

        System.out.println("Sach 2: "
                + book2);


        // THAY DOI THONG TIN

        author.setEmail("newemail@gmail.com");
        author.setGender('f');

        book2.setPrice(35.0);
        book2.setQty(20);

        System.out.println();
        System.out.println("===== SAU KHI THAY DOI =====");

        System.out.println("Email tac gia moi: "
                + author.getEmail());

        System.out.println("Gioi tinh tac gia moi: "
                + author.getGender());

        System.out.println("Gia sach 2 moi: "
                + book2.getPrice());

        System.out.println("So luong sach 2 moi: "
                + book2.getQty());

        System.out.println("Thong tin sach 2 sau khi thay doi: "
                + book2);
    }
}