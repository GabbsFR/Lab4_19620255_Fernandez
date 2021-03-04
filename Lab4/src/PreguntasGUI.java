import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreguntasGUI extends JFrame {
    private JLabel user;
    private JList list1;
    private JButton agregarRespuestaButton;
    private JButton votarRespuestaOPreguntaButton;
    private JButton agregarRecompensaButton;
    private JPanel preguntaG;
    private JLabel contenido;
    private JButton volver;
    private JButton cerrarSesiónButton;

    public PreguntasGUI(String title, Stack stack, int id) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((preguntaG));
        this.setSize(1450,850);
        user.setBounds(-50,20,30,20);
        Pregunta p = stack.getPreguntas()[id];
        user.setText(stack.getActivo().getName());
        contenido.setText(stack.getPreguntas()[id].mostrarPregunta());

        DefaultListModel respuestas = new DefaultListModel();
        for (Respuesta respuesta : p.getRespuestas()) {
            respuestas.addElement(respuesta.getId()+": "+respuesta.getContenido());
        }
        list1.setModel(respuestas);

        votarRespuestaOPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new Principal("Principal - StackOverflow GFR",stack);
                frame.setVisible(true);
            }
        });

        agregarRecompensaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        agregarRespuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
