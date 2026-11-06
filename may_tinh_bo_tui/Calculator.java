import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {

      // ====== Màu sắc ======
      private static final Color COLOR_NUMBER = new Color(245, 245, 245);
      private static final Color COLOR_OPERATOR = new Color(255, 149, 0);
      private static final Color COLOR_FUNCTION = new Color(200, 200, 205);
      private static final Color COLOR_CLEAR = new Color(220, 70, 70);

      // ====== Trạng thái máy tính ======
      private double a = 0;            // toán hạng thứ nhất
      private String op = null;        // phép toán đang chờ: + - x /
      private boolean newInput = true; // true: số kế tiếp sẽ xóa màn hình để nhập mới
      private boolean error = false;   // đang hiển thị lỗi

      private final JTextField tf = new JTextField("0");
      private final Font displayFont = new Font("Arial", Font.BOLD, 44);
      private final Font errorFont = new Font("Arial", Font.BOLD, 18);

      // Bố cục 5 hàng x 4 cột
      private static final String[] LABELS = {
              "C", "\u2190", "%", "/",
              "7", "8", "9", "x",
              "4", "5", "6", "-",
              "1", "2", "3", "+",
              "\u00B1", "0", ".", "="
      };

      Calculator() {
            setTitle("CALCULATOR");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(360, 520);
            setMinimumSize(new Dimension(300, 440));
            setLocationRelativeTo(null); // hiện giữa màn hình
            setLayout(new BorderLayout(5, 5));

            // --- Màn hình hiển thị ---
            tf.setFont(displayFont);
            tf.setHorizontalAlignment(JTextField.RIGHT); // căn lề phải
            tf.setEditable(false);
            tf.setBackground(Color.WHITE);
            tf.setPreferredSize(new Dimension(100, 90));
            tf.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            add(tf, BorderLayout.NORTH);

            // --- Lưới nút bấm ---
            JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));
            panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            for (String label : LABELS) {
                  JButton btn = new JButton(label);
                  btn.setFont(new Font("Arial", Font.BOLD, 24));
                  btn.setFocusable(false); // để phím Enter không kích hoạt nút đang focus
                  btn.setOpaque(true);
                  btn.setBorderPainted(false);
                  btn.setForeground(Color.BLACK);
                  btn.setBackground(colorFor(label));
                  if (label.equals("=") || label.equals("/") || label.equals("x")
                          || label.equals("-") || label.equals("+")) {
                        btn.setForeground(Color.WHITE);
                  }
                  if (label.equals("C")) {
                        btn.setForeground(Color.WHITE);
                  }
                  btn.setActionCommand(label);
                  btn.addActionListener(this);
                  panel.add(btn);
            }
            add(panel, BorderLayout.CENTER);

            setupKeyboard();
            setVisible(true);
      }

      private Color colorFor(String label) {
            switch (label) {
                  case "+": case "-": case "x": case "/": case "=":
                        return COLOR_OPERATOR;
                  case "C":
                        return COLOR_CLEAR;
                  case "\u2190": case "%": case "\u00B1":
                        return COLOR_FUNCTION;
                  default:
                        return COLOR_NUMBER;
            }
      }

      // ====== Bắt sự kiện bàn phím (Key Bindings) ======
      private void setupKeyboard() {
            JRootPane root = getRootPane();
            InputMap im = root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = root.getActionMap();

            String chars = "0123456789.+-*/%=";
            for (char c : chars.toCharArray()) {
                  String cmd = String.valueOf(c);
                  if (c == '*') cmd = "x";
                  bind(im, am, KeyStroke.getKeyStroke(c), cmd);
            }
            bind(im, am, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "=");
            bind(im, am, KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "\u2190");
            bind(im, am, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "C");
            bind(im, am, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "C");
      }

      private void bind(InputMap im, ActionMap am, KeyStroke ks, String cmd) {
            String key = "key_" + cmd + "_" + ks;
            im.put(ks, key);
            am.put(key, new AbstractAction() {
                  public void actionPerformed(ActionEvent e) {
                        handle(cmd);
                  }
            });
      }

      // ====== Xử lý nút chuột ======
      @Override
      public void actionPerformed(ActionEvent e) {
            handle(e.getActionCommand());
      }

      // ====== Xử lý trung tâm (dùng chung cho chuột và bàn phím) ======
      private void handle(String cmd) {
            // Đang hiển thị lỗi: chỉ cho C, số hoặc dấu chấm để thoát khỏi lỗi
            if (error) {
                  if (cmd.equals("C") || isDigit(cmd) || cmd.equals(".")) {
                        clearAll();
                        if (cmd.equals("C")) return;
                  } else {
                        return;
                  }
            }

            if (isDigit(cmd)) {
                  inputDigit(cmd);
            } else if (cmd.equals(".")) {
                  inputDot();
            } else if (cmd.equals("+") || cmd.equals("-") || cmd.equals("x") || cmd.equals("/")) {
                  inputOperator(cmd);
            } else if (cmd.equals("=")) {
                  calculateEquals();
            } else if (cmd.equals("C")) {
                  clearAll();
            } else if (cmd.equals("\u2190")) {
                  backspace();
            } else if (cmd.equals("\u00B1")) {
                  negate();
            } else if (cmd.equals("%")) {
                  percent();
            }
      }

      private boolean isDigit(String s) {
            return s.length() == 1 && Character.isDigit(s.charAt(0));
      }

      private void inputDigit(String d) {
            if (newInput || tf.getText().equals("0")) {
                  tf.setText(d);
            } else {
                  tf.setText(tf.getText() + d);
            }
            newInput = false;
      }

      // Chỉ cho phép 1 dấu chấm trong một số (tránh 5.2.3)
      private void inputDot() {
            if (newInput) {
                  tf.setText("0.");
                  newInput = false;
            } else if (!tf.getText().contains(".")) {
                  tf.setText(tf.getText() + ".");
            }
      }

      private void inputOperator(String newOp) {
            if (op != null && !newInput) {
                  // Tính liên tiếp: 5 + 3 + ... => tính 5 + 3 trước
                  double b = Double.parseDouble(tf.getText());
                  double r = compute(a, b, op);
                  if (error) return;
                  a = r;
                  tf.setText(format(a));
            } else if (op == null) {
                  a = Double.parseDouble(tf.getText());
            }
            // Nếu bấm liên tiếp 2 toán tử thì chỉ đổi toán tử
            op = newOp;
            newInput = true;
      }

      private void calculateEquals() {
            if (op == null || newInput) return;
            double b = Double.parseDouble(tf.getText());
            double r = compute(a, b, op);
            if (error) return;
            tf.setText(format(r));
            a = r;
            op = null;
            newInput = true; // bấm số mới sẽ xóa kết quả cũ
      }

      private double compute(double x, double y, String o) {
            switch (o) {
                  case "+": return x + y;
                  case "-": return x - y;
                  case "x": return x * y;
                  case "/":
                        if (y == 0) {
                              showError("Error: Cannot divide by zero");
                              return 0;
                        }
                        return x / y;
                  default: return y;
            }
      }

      private void backspace() {
            if (newInput) return; // không xóa lùi trên kết quả
            String s = tf.getText();
            if (s.length() <= 1 || (s.length() == 2 && s.startsWith("-"))) {
                  tf.setText("0");
            } else {
                  tf.setText(s.substring(0, s.length() - 1));
            }
      }

      private void negate() {
            String s = tf.getText();
            if (s.equals("0") || s.equals("0.")) return;
            if (s.startsWith("-")) tf.setText(s.substring(1));
            else tf.setText("-" + s);
      }

      private void percent() {
            double v = Double.parseDouble(tf.getText()) / 100.0;
            tf.setText(format(v));
            newInput = true;
      }

      private void clearAll() {
            a = 0;
            op = null;
            newInput = true;
            error = false;
            tf.setFont(displayFont);
            tf.setForeground(Color.BLACK);
            tf.setText("0");
      }

      private void showError(String msg) {
            error = true;
            op = null;
            newInput = true;
            tf.setFont(errorFont);
            tf.setForeground(Color.RED);
            tf.setText(msg);
      }

      // Định dạng số: bỏ ".0" thừa, làm tròn sai số dấu phẩy động (0.1+0.2)
      private String format(double v) {
            if (Double.isNaN(v) || Double.isInfinite(v)) {
                  showError("Error: Invalid result");
                  return tf.getText();
            }
            BigDecimal bd = new BigDecimal(v).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();
            return bd.toPlainString();
      }

      public static void main(String[] args) {
            try {
                  // Look&Feel mặc định của Java để màu nút hiển thị đúng trên mọi hệ điều hành
                  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            SwingUtilities.invokeLater(Calculator::new);
      }
}