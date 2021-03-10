package vista;
/*
Ventana que pide la información necesaria para crear una etiqueta y permite crearla
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EtiquetaGUI extends JFrame {
    public JPanel panel1;
    public JButton crearButton;
    public JTextField nombre;
    public JTextField descripcion;
    public JLabel success;
    public JButton volverButton;

    public EtiquetaGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((panel1));
        this.setSize(1450,850);
        success.setVisible(false);

    }
}
