import javax.swing.*;

public class Register extends JFrame {
    private JPanel registro;
    private JTextField textField1;
    private JTextField textField2;

    public Register(String title, Stack stack) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((registro));
        this.pack();
    }
}
