package vista;
/*
Ventana que perimite tanto el ingreso como el registro de usuarios
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_Register extends JFrame{
    public JPanel inicio;
    public JTextField textUser;
    public JPasswordField textPass;
    public JButton ingresarButton;
    public JButton registrarseButton;
    public JLabel success;
    public JButton volver;

    public Login_Register() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((inicio));
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
    }
}
