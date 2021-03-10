package vista;
/*
Ventana que muestra una pregunta con sus respuestas y permite que se agreguen respuestas,
se vote, se acepte, se agregue recompensa,etc.
 */
import javax.swing.*;

public class PreguntasGUI extends JFrame {
    public JLabel user;
    public JList<String> list1;
    public JButton agregarRespuestaButton;
    public JButton votarRespuestaAFavorButton;
    public JButton agregarRecompensaButton;
    public JPanel preguntaG;
    public JButton volver;
    public JButton votarPreguntaEnContraButton;
    public JTextArea respuestaSeleccionada;
    public JLabel success;
    public JButton votarPreguntaAFavorButton;
    public JButton votarRespuestaEnContraButton;
    public JSpinner spinner1;
    public JTextField contenidoRespuestaIngresada;
    public JButton aceptarRespuestaButton;
    public JLabel tituloPreg;
    public JTextPane infoPregunta;

    public PreguntasGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((preguntaG));
        this.setSize(1450,850);
        user.setBounds(-50,20,30,20);
        respuestaSeleccionada.setText("");

    }
}
