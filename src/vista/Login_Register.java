package vista;
/*
Vista que despliega un formulario para ingresar o registrarse en la plataforma
 */
import javax.swing.*;

public class Login_Register extends JFrame{
    public JPanel inicio;
    public JTextField textUser;
    public JPasswordField textPass;
    public JButton ingresarButton;
    public JButton registrarseButton;
    public JLabel success;
    public JButton volver;

    // Constructor de la ventana
    public Login_Register() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((inicio));
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
    }
}
