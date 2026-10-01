//Vu Le Kien
//MSV 251020905771

package buoi3;

 class Author {
    // Thuộc tính private
    private String name;
    private String email;
    private char gender;

    // Constructor
    public Author(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public char getGender() {
        return gender;
    }

    // Setter cho email
    public void setEmail(String email) {
        this.email = email;
    }

    // Chuyển đối tượng thành Chuỗi dạng "Author[name=?,email=?,gender=?]"
    @Override
    public String toString() {
        return "Author[name=" + name + ",email=" + email + ",gender=" + gender + "]";
    }
}
public class bai6 {
    public static void main(String[] args) {
        // Khởi tạo một đối tượng Author
        Author ahTeck = new Author("Tan Ah Teck", "ahteck@nowhere.com", 'm');
        System.out.println(ahTeck);  // In ra thông tin tác giả qua toString()

        // Thay đổi email
        ahTeck.setEmail("ahteck@somewhere.com");
        System.out.println(ahTeck);

        // Kiểm tra các phương thức getter
        System.out.println("name is: " + ahTeck.getName());
        System.out.println("email is: " + ahTeck.getEmail());
        System.out.println("gender is: " + ahTeck.getGender());
    }
}
