package QLSV;

public class Student {
    private String studentId;
    private String fullName;
    private int age;
    private double gpa;

    public Student() {
    }

    public Student(String studentId, String fullName, int age, double gpa) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.age = age;
        this.gpa = gpa;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    // Định dạng ghi file: MãSV;HọTên;Tuổi;GPA  (ví dụ: SV01;Nguyen Van A;20;3.2)
    @Override
    public String toString() {
        return studentId + ";" + fullName + ";" + age + ";" + gpa;
    }
}