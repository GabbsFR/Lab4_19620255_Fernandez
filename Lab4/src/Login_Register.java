import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Login_Register extends JFrame{
    private JPanel inicio;
    private JTextField textUser;
    private JPasswordField textPass;
    private JButton ingresarButton;
    private JButton registrarseButton;
    private JLabel success;

    public Login_Register(String title, Stack stack) {
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
                    dispose();
                    stack.login(user,pass);
                    JFrame frame = new Principal("Principal - StackOverflow GFR",stack);
                    frame.setVisible(true);

                }
            }
        });
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = textUser.getText();
                String pass = textPass.getText();
                boolean flag = stack.register(user,pass);

                if (flag){
                    dispose();
                    stack.login(user,pass);
                    JFrame frame = new Principal("Principal - StackOverflow GFR",stack);
                    frame.setVisible(true);
                }else{
                    success.setText("Usuario ya registrado, elija otro nombre de usuario");
                }
            }
        });
    }
}
