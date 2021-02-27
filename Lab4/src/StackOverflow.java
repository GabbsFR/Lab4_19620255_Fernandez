import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class StackOverflow extends JFrame{
    private JPanel inicio;
    private JTextField textUser;
    private JPasswordField textPass;
    private JButton ingresarButton;
    private JButton registrarseButton;
    private JLabel success;

    public StackOverflow(String title, Stack stack) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((inicio));
        this.pack();
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = textUser.getText();
                String pass = textPass.getText();
                boolean flag = stack.login(user,pass);

                if (flag){
                    success.setText("Usuario y/o contraseña incorrectas");
                }else{

                }
            }
        });
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Register("Registro StackOverflow Gabriela Fernández",stack);
                frame.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.inicial();
        JFrame frame = new StackOverflow("StackOverflow Gabriela Fernández", stack);
        frame.setVisible(true);
    }
}
