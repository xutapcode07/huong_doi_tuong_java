//Vu Le Kien
//MSV: 251020905771

package buoi3;
class Account {
    // Thuộc tính private
    private String id;
    private String name;
    private int balance = 0; // Giá trị mặc định là 0

    // Constructor 1: Khởi tạo với id và name
    public Account(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Constructor 2: Khởi tạo với id, name và balance
    public Account(String id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    // Nạp tiền vào tài khoản
    public int credit(int amount) {
        balance += amount;
        return balance;
    }

    // Rút tiền khỏi tài khoản
    public int debit(int amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Amount exceeded balance");
        }
        return balance;
    }

    // Chuyển tiền tới tài khoản khác
    public int transferTo(Account another, int amount) {
        if (amount <= balance) {
            this.balance -= amount;
            another.credit(amount); // Nạp số tiền vừa chuyển vào tài khoản nhận
        } else {
            System.out.println("Amount exceeded balance");
        }
        return this.balance;
    }

    // Chuyển đối tượng thành chuỗi
    @Override
    public String toString() {
        return "Account[id=" + id + ",name=" + name + ",balance=" + balance + "]";
    }
}
public class bai2 {
    public static void main(String[] args) {
        // Tạo 2 tài khoản
        Account a1 = new Account("A101", "Tan Ah Kow", 88);
        System.out.println(a1);  // toString()
        Account a2 = new Account("A102", "Kumar"); // balance mặc định là 0
        System.out.println(a2);

        // Kiểm tra Getters
        System.out.println("ID: " + a1.getId());
        System.out.println("Name: " + a1.getName());
        System.out.println("Balance: " + a1.getBalance());

        // Kiểm tra credit() và debit()
        a1.credit(100);
        System.out.println(a1);
        a1.debit(50);
        System.out.println(a1);
        a1.debit(500); // Thử rút quá số dư
        System.out.println(a1);

        // Kiểm tra transferTo()
        a1.transferTo(a2, 100); // Chuyển 100 từ a1 sang a2
        System.out.println(a1);
        System.out.println(a2);
    }
}