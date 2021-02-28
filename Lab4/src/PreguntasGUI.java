import javax.swing.*;

public class PreguntasGUI extends JFrame {
    private JLabel user;
    private JList list1;
    private JButton agregarRespuestaButton;
    private JButton votarRespuestaOPreguntaButton;
    private JButton agregarRecompensaButton;
    private JPanel preguntaG;
    private JLabel contenido;

    public PreguntasGUI(String title, Stack stack, int id) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((preguntaG));
        this.setSize(1450,850);
        user.setBounds(-50,20,30,20);
        Pregunta p = stack.getPreguntas()[id -1];
        user.setText(stack.getActivo().getName());
        contenido.setText(stack.getPreguntas()[id].getContenido());

        DefaultListModel respuestas = new DefaultListModel();
        for (Respuesta respuesta : p.getRespuestas()) {
            respuestas.addElement(respuesta.getId()+": "+pregunta.getContenido());
        }
        list1.setModel(respuestas);

    }
}
