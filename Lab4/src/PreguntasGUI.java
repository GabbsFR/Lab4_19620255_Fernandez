/*
Ventana que muestra una pregunta con sus respuestas y permite que se agreguen respuestas,
se vote, se acepte, se agregue recompensa,etc.
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreguntasGUI extends JFrame {
    private JLabel user;
    private JList<String> list1;
    private JButton agregarRespuestaButton;
    private JButton votarRespuestaAFavorButton;
    private JButton agregarRecompensaButton;
    private JPanel preguntaG;
    private JButton volver;
    private JButton votarPreguntaEnContraButton;
    private JTextArea respuestaSeleccionada;
    private JLabel success;
    private JButton votarPreguntaAFavorButton;
    private JButton votarRespuestaEnContraButton;
    private JSpinner spinner1;
    private JTextField contenidoRespuestaIngresada;
    private JButton aceptarRespuestaButton;
    private JLabel tituloPreg;
    private JTextPane infoPregunta;

    public PreguntasGUI(String title, Stack stack, int id) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((preguntaG));
        this.setSize(1450,850);
        user.setBounds(-50,20,30,20);
        Pregunta p = stack.getPreguntas()[id];
        user.setText(stack.getActivo().getName());
        tituloPreg.setText(p.getTitulo());
        infoPregunta.setText(p.mostrarPregunta());
        respuestaSeleccionada.setText("");

        // oculta las opciones que son solo para preguntas abiertas
        if (p.getEstado().equals("cerrada")){
            agregarRecompensaButton.setVisible(false);
            spinner1.setVisible(false);
            aceptarRespuestaButton.setVisible(false);
        }

        // oculta la opción que es solo para el/la autor/a de la pregunta
        if (!p.getAutor().getName().equals(stack.getActivo().getName())){
            aceptarRespuestaButton.setVisible(false);
        }

        DefaultListModel respuestas = new DefaultListModel();
        for (Respuesta respuesta : p.getRespuestas()) {
            respuestas.addElement(respuesta.getId()+": "+respuesta.getContenido());
        }
        list1.setModel(respuestas);

        // cuando se selecciona una respuesta de la lista de respuestas, pone la información de la misma en un recuadro en la ventana
        list1.getSelectionModel().addListSelectionListener(e -> {
            success.setText("");
            int idRespuesta = list1.getSelectedIndex();
            respuestaSeleccionada.setText(idRespuesta + stack.getPreguntas()[id].getRespuestas()[idRespuesta].mostrarRespuesta());
        });

        // permite volver a la ventana principal
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new Principal("Principal - StackOverflow GFR",stack);
                frame.setVisible(true);
            }
        });

        // permite agregar una respuesta siempre y cuando se rellene el campo de texto
        agregarRespuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contenidoRespuestaIngresada.getText().equals("")){
                    success.setText("Debe ingresar contenido de la respuesta");
                }else{
                    stack.answer(p,contenidoRespuestaIngresada.getText());
                    dispose();
                    JFrame frame = new PreguntasGUI("Pregunta - StackOverflow GFR",stack,id);
                    frame.setVisible(true);
                }
            }
        });

        // permite agregar una recompensa siempre que se cumpla con las condiciones
        agregarRecompensaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(spinner1.getValue().toString());
                if(value == 0 || value > stack.getActivo().getReputacion()){
                    success.setText("Debe poner un valor distinto de 0 y menor o igual a su reputación que es "+stack.getActivo().getReputacion());
                }else {
                    stack.reward(p,value);
                    dispose();
                    JFrame frame = new PreguntasGUI("Pregunta - StackOverflow GFR",stack,id);
                    frame.setVisible(true);
                }
            }
        });

        // permite aceptar una respuesta (esta opcion le saldra dispnible solo al autor de la pregunta)
        aceptarRespuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (respuestaSeleccionada.getText().equals("")){
                    success.setText("Debe seleccionar una respuesta primero");
                    success.setVisible(true);
                }else{
                    int idR = Character.getNumericValue(respuestaSeleccionada.getText().charAt(0));
                    Respuesta r = p.getRespuestas()[idR];
                    stack.accept(p,r);
                    dispose();
                    JFrame frame = new PreguntasGUI("Pregunta - StackOverflow GFR",stack,id);
                    frame.setVisible(true);
                }
            }
        });

        // Permite votar a favor de la pregunta
        votarPreguntaAFavorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(!respuestaSeleccionada.getText().equals("")){
                    stack.vote(p, true);
                    dispose();
                    JFrame frame = new PreguntasGUI("Principal - StackOverflow GFR", stack, id);
                    frame.setVisible(true);
                }else{
                    success.setText("Debe seleccionar una respuesta primero");
                }
            }
        });

        // Permite votar en contra de la pregunta
        votarPreguntaEnContraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!respuestaSeleccionada.getText().equals("")) {
                    stack.vote(p, false);
                    dispose();
                    JFrame frame = new PreguntasGUI("Principal - StackOverflow GFR", stack, id);
                    frame.setVisible(true);
                }else{
                    success.setText("Debe seleccionar una respuesta primero");
                }
            }
        });

        // Permite votar a favor de una respuesta siempre que se haya seleccionado alguna
        votarRespuestaAFavorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!respuestaSeleccionada.getText().equals("")){
                    int idR = Character.getNumericValue(respuestaSeleccionada.getText().charAt(0));
                    Respuesta r = p.getRespuestas()[idR];
                    stack.vote(r,true);
                    dispose();
                    JFrame frame = new PreguntasGUI("Principal - StackOverflow GFR",stack,id);
                    frame.setVisible(true);
                }else{
                    success.setText("Debe seleccionar una respuesta primero");
                }
            }
        });
        // Permite votar en contra de una respuesta siempre que se haya seleccionado alguna
        votarRespuestaEnContraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!respuestaSeleccionada.getText().equals("")){
                    int idR = Character.getNumericValue(respuestaSeleccionada.getText().charAt(0));
                    Respuesta r = p.getRespuestas()[idR];
                    stack.vote(r, false);
                    dispose();
                    JFrame frame = new PreguntasGUI("Principal - StackOverflow GFR", stack, id);
                    frame.setVisible(true);
                }else{
                    success.setText("Debe seleccionar una respuesta primero");
                }
            }
        });
    }
}
