/*
Ventana que perimite tanto el ingreso como el registro de usuarios
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_Register extends JFrame{
    private JPanel inicio;
    private JTextField textUser;
    private JPasswordField textPass;
    private JButton ingresarButton;
    private JButton registrarseButton;
    private JLabel success;
    private JButton volver;

    public Login_Register(String title, Stack stack) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((inicio));
        this.setSize(450,450);
        this.setLocationRelativeTo(null);

        // si los campos de texto están rellenos y el nombre de usuario y contraseña coinciden con los registrados
        // se permite iniciar sesión
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

        // si los campos de texto están rellenos y el nombre de usuario no ha sido ocupado, permite
        // el registro de un nuevo usuario
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

        // permite volver a la página principal sin iniciar o registrar sesión
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new Principal("Principal - StackOverflow GFR",stack);
                frame.setVisible(true);
            }
        });
    }
}
