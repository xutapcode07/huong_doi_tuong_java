package QLSV;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentManagement extends JFrame {

    private static final String FILE_NAME = "students.txt";

    // Dữ liệu chính của ứng dụng
    private final List<Student> students = new ArrayList<>();

    // Khu vực nhập liệu
    private final JTextField txtId = new JTextField();
    private final JTextField txtName = new JTextField();
    private final JTextField txtAge = new JTextField();
    private final JTextField txtGpa = new JTextField();
    private final JTextField txtSearch = new JTextField(20);
    private final JButton btnSearch = new JButton("Search");
    private final JButton btnShowAll = new JButton("Show All");

    // Khu vực bảng
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new String[] { "Mã SV", "Họ Tên", "Tuổi", "GPA" }, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // chỉ chỉnh sửa qua form
        }
    };
    private final JTable table = new JTable(tableModel);

    // Khu vực điều khiển
    private final JButton btnAdd = new JButton("Add");
    private final JButton btnUpdate = new JButton("Update");
    private final JButton btnDelete = new JButton("Delete");
    private final JButton btnSave = new JButton("Save to File");
    private final JButton btnLoad = new JButton("Load from File");
    private final JButton btnRefresh = new JButton("Refresh");

    public StudentManagement() {
        setTitle("QUẢN LÝ SINH VIÊN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        add(buildNorthPanel(), BorderLayout.NORTH);
        add(buildCenterPanel(), BorderLayout.CENTER);
        add(buildSouthPanel(), BorderLayout.SOUTH);

        addEvents();
        setVisible(true);
    }

    // ================= GIAO DIỆN =================

    private JPanel buildNorthPanel() {
        JPanel north = new JPanel(new BorderLayout(5, 5));
        north.setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));

        // Form nhập liệu: 2 hàng x 4 cột (nhãn - ô nhập - nhãn - ô nhập)
        JPanel form = new JPanel(new GridLayout(2, 4, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Thông tin sinh viên"));
        form.add(new JLabel("Mã sinh viên:"));
        form.add(txtId);
        form.add(new JLabel("Họ và tên:"));
        form.add(txtName);
        form.add(new JLabel("Tuổi:"));
        form.add(txtAge);
        form.add(new JLabel("GPA (0.0 - 4.0):"));
        form.add(txtGpa);

        // Tìm kiếm: ô nhập + nút bấm
        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        search.setBorder(BorderFactory.createTitledBorder("Tìm kiếm (theo Mã SV hoặc Họ tên)"));
        search.add(new JLabel("Từ khóa:"));
        search.add(txtSearch);
        search.add(btnSearch);
        search.add(btnShowAll);

        north.add(form, BorderLayout.CENTER);
        north.add(search, BorderLayout.SOUTH);
        return north;
    }

    private JScrollPane buildCenterPanel() {
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(24);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        return scroll;
    }

    private JPanel buildSouthPanel() {
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        south.add(btnAdd);
        south.add(btnUpdate);
        south.add(btnDelete);
        south.add(btnSave);
        south.add(btnLoad);
        south.add(btnRefresh);
        return south;
    }

    // ================= SỰ KIỆN =================

    private void addEvents() {
        // Click vào dòng trên bảng -> hiển thị lên form
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showSelectedRowOnForm();
            }
        });

        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnSave.addActionListener(e -> saveToFile());
        btnLoad.addActionListener(e -> loadFromFile());
        btnRefresh.addActionListener(e -> refreshForm());
        btnSearch.addActionListener(e -> searchStudent());
        btnShowAll.addActionListener(e -> {
            txtSearch.setText("");
            showTable(students);
        });
        txtSearch.addActionListener(e -> searchStudent()); // nhấn Enter để tìm
    }

    private void showSelectedRowOnForm() {
        int row = table.getSelectedRow();
        if (row < 0) {
            return;
        }
        txtId.setText(String.valueOf(tableModel.getValueAt(row, 0)));
        txtName.setText(String.valueOf(tableModel.getValueAt(row, 1)));
        txtAge.setText(String.valueOf(tableModel.getValueAt(row, 2)));
        txtGpa.setText(String.valueOf(tableModel.getValueAt(row, 3)));
        txtId.setEditable(false); // không cho sửa Mã SV
    }

    // ================= CHỨC NĂNG NGHIỆP VỤ =================

    // 1. Thêm sinh viên
    private void addStudent() {
        Student s = readFormAndValidate();
        if (s == null) {
            return;
        }
        if (findById(s.getStudentId()) != null) {
            showError("Mã sinh viên \"" + s.getStudentId() + "\" đã tồn tại!");
            return;
        }
        students.add(s);
        showTable(students);
        refreshForm();
        JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công!");
    }

    // 2. Cập nhật sinh viên (không đổi Mã SV)
    private void updateStudent() {
        int row = table.getSelectedRow();
        if (row < 0) {
            showError("Vui lòng chọn một sinh viên trên bảng để cập nhật!");
            return;
        }
        // Lấy mã từ bảng (không phải từ ô nhập) để chắc chắn không đổi Mã SV
        String id = String.valueOf(tableModel.getValueAt(row, 0));
        Student old = findById(id);
        if (old == null) {
            showError("Không tìm thấy sinh viên cần cập nhật!");
            return;
        }
        Student s = readFormAndValidate();
        if (s == null) {
            return;
        }
        old.setFullName(s.getFullName());
        old.setAge(s.getAge());
        old.setGpa(s.getGpa());

        reapplyCurrentView();
        refreshForm();
        JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
    }

    // 3. Xóa sinh viên (có xác nhận)
    private void deleteStudent() {
        int row = table.getSelectedRow();
        if (row < 0) {
            showError("Vui lòng chọn một sinh viên trên bảng để xóa!");
            return;
        }
        String id = String.valueOf(tableModel.getValueAt(row, 0));
        String name = String.valueOf(tableModel.getValueAt(row, 1));
        int choice = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa sinh viên " + id + " - " + name + "?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (choice != JOptionPane.YES_OPTION) {
            return;
        }
        Student s = findById(id);
        if (s != null) {
            students.remove(s);
        }
        reapplyCurrentView();
        refreshForm();
    }

    // 4. Tìm kiếm gần đúng theo Mã SV hoặc Họ tên
    private void searchStudent() {
        String keyword = txtSearch.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            showTable(students);
            return;
        }
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getStudentId().toLowerCase().contains(keyword)
                    || s.getFullName().toLowerCase().contains(keyword)) {
                result.add(s);
            }
        }
        showTable(result);
        if (result.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên nào phù hợp!");
        }
    }

    // 5. Lưu ra file students.txt
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(FILE_NAME), StandardCharsets.UTF_8))) {
            for (Student s : students) {
                bw.write(s.toString());
                bw.newLine();
            }
            JOptionPane.showMessageDialog(this,
                    "Đã lưu " + students.size() + " sinh viên vào file " + FILE_NAME);
        } catch (IOException ex) {
            showError("Lỗi khi ghi file: " + ex.getMessage());
        }
    }

    // 6. Đọc từ file students.txt
    private void loadFromFile() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            showError("Không tìm thấy file " + FILE_NAME + "!");
            return;
        }
        List<Student> loaded = new ArrayList<>();
        int skipped = 0;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] p = line.split(";");
                if (p.length != 4) {
                    skipped++;
                    continue;
                }
                try {
                    String id = p[0].trim();
                    String name = p[1].trim();
                    int age = Integer.parseInt(p[2].trim());
                    double gpa = Double.parseDouble(p[3].trim());
                    boolean duplicate = false;
                    for (Student x : loaded) {
                        if (x.getStudentId().equalsIgnoreCase(id)) {
                            duplicate = true;
                            break;
                        }
                    }
                    if (id.isEmpty() || name.isEmpty() || duplicate) {
                        skipped++;
                        continue;
                    }
                    loaded.add(new Student(id, name, age, gpa));
                } catch (NumberFormatException ex) {
                    skipped++;
                }
            }
        } catch (IOException ex) {
            showError("Lỗi khi đọc file: " + ex.getMessage());
            return;
        }
        students.clear();
        students.addAll(loaded);
        txtSearch.setText("");
        showTable(students);
        refreshForm();
        String msg = "Đã đọc " + loaded.size() + " sinh viên từ file " + FILE_NAME;
        if (skipped > 0) {
            msg += "\n(Bỏ qua " + skipped + " dòng không hợp lệ)";
        }
        JOptionPane.showMessageDialog(this, msg);
    }

    // ================= HÀM HỖ TRỢ =================

    // Đọc dữ liệu từ form và kiểm tra hợp lệ. Trả về null nếu có lỗi.
    private Student readFormAndValidate() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String ageStr = txtAge.getText().trim();
        String gpaStr = txtGpa.getText().trim().replace(',', '.');

        if (id.isEmpty() || name.isEmpty() || ageStr.isEmpty() || gpaStr.isEmpty()) {
            showError("Vui lòng nhập đầy đủ thông tin, không được để trống!");
            return null;
        }
        if (id.contains(";") || name.contains(";")) {
            showError("Mã SV và Họ tên không được chứa dấu chấm phẩy (;)!");
            return null;
        }
        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException ex) {
            showError("Tuổi phải là số nguyên!");
            return null;
        }
        if (age <= 0) {
            showError("Tuổi phải là số nguyên dương!");
            return null;
        }
        double gpa;
        try {
            gpa = Double.parseDouble(gpaStr);
        } catch (NumberFormatException ex) {
            showError("GPA phải là số thực (ví dụ: 3.2)!");
            return null;
        }
        if (Double.isNaN(gpa) || gpa < 0.0 || gpa > 4.0) {
            showError("GPA phải nằm trong khoảng từ 0.0 đến 4.0!");
            return null;
        }
        return new Student(id, name, age, gpa);
    }

    private Student findById(String id) {
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    // Đổ danh sách lên JTable
    private void showTable(List<Student> list) {
        tableModel.setRowCount(0);
        for (Student s : list) {
            tableModel.addRow(new Object[] { s.getStudentId(), s.getFullName(), s.getAge(), s.getGpa() });
        }
    }

    // Hiển thị lại bảng theo từ khóa tìm kiếm hiện tại (nếu có)
    private void reapplyCurrentView() {
        if (txtSearch.getText().trim().isEmpty()) {
            showTable(students);
        } else {
            List<Student> result = new ArrayList<>();
            String keyword = txtSearch.getText().trim().toLowerCase();
            for (Student s : students) {
                if (s.getStudentId().toLowerCase().contains(keyword)
                        || s.getFullName().toLowerCase().contains(keyword)) {
                    result.add(s);
                }
            }
            showTable(result);
        }
    }

    // Làm mới form
    private void refreshForm() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtGpa.setText("");
        txtId.setEditable(true);
        table.clearSelection();
        txtId.requestFocus();
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagement::new);
    }
}