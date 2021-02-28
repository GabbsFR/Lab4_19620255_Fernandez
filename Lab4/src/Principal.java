import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;

public class Principal extends JFrame{

    private JPanel principal;
    private JButton button1;
    private JList<String> list1;
    private JTextPane textPane1;
    private JButton agregarPreguntaButton;
    private JButton button3;
    private JLabel success;

    public Principal(String title, Stack stack) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((principal));
        this.setSize(1450,850);
        if (stack.getActivo().getName().equals("")){
            button1.setText("Ingresar o Registrarse");
        }else{
            button1.setText(stack.getActivo().getName());
        }

        DefaultListModel preguntas = new DefaultListModel();
        for (Pregunta pregunta : stack.getPreguntas()) {
            preguntas.addElement(pregunta.getId()+": "+pregunta.getContenido());
        }
        list1.setModel(preguntas);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stack.getActivo().getName().equals("")){
                    dispose();
                    JFrame frame = new Login_Register("StackOverflow GFR", stack);
                    frame.setVisible(true);
                }else{

                }
            }
        });

        list1.getSelectionModel().addListSelectionListener(e -> {
            success.setText("");
            int id = list1.getSelectedIndex();
            textPane1.setText(stack.getPreguntas()[id].mostrarPregunta());
        });

        agregarPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(stack.getActivo().getName().equals("")){
                    success.setText("Debes tener sesión activa para realizar acciones");
                }else{

                }
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textPane1.getText().equals("")){
                    success.setText("Debe seleccionar una pregunta primero");
                }else if(stack.getActivo().getName().equals("")){
                    success.setText("Debe iniciar sesión antes de realizar cualquier acción");
                }else{
                    dispose();
                    int id = Character.getNumericValue(textPane1.getText().charAt(0));
                    JFrame frame = new PreguntasGUI("Principal - StackOverflow GFR",stack,id);
                    frame.setVisible(true);
                }
            }
        });
    }
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.inicial();
        JFrame frame = new Principal("StackOverflow Gabriela Fernández", stack);
        frame.setVisible(true);
    }
}
