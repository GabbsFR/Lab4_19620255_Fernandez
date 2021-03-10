package vista;
/*
Vista que despliega un formulario para crear una nueva etiqueta
 */
import javax.swing.*;

public class EtiquetaGUI extends JFrame {
    public JPanel panel1;
    public JButton crearButton;
    public JTextField nombre;
    public JTextField descripcion;
    public JLabel success;
    public JButton volverButton;

    // Constructor de la ventana
    public EtiquetaGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((panel1));
        this.setSize(1450,850);
        success.setVisible(false);

    }
}
