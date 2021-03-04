import javax.swing.*;
import java.awt.event.*;

public class Principal extends JFrame{

    private JPanel principal;
    private JButton iniciar_login_logout;
    private JList<String> list1;
    private JTextPane textPane1;
    private JButton agregarPreguntaButton;
    private JButton button3;
    private JLabel success;
    private JButton tusPreguntasButton;
    private JLabel reputacion;

    public Principal(String title, Stack stack) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((principal));
        this.setSize(1450,850);
        if (stack.getActivo().getName().equals("")){
            iniciar_login_logout.setText("Ingresar o Registrarse");
            reputacion.setVisible(false);
            tusPreguntasButton.setVisible(false);
        }else{
            iniciar_login_logout.setText("Cerrar Sesión");
            reputacion.setText("Reputación: "+stack.getActivo().getReputacion());
            reputacion.setVisible(true);
            tusPreguntasButton.setVisible(true);
        }

        DefaultListModel preguntas = new DefaultListModel();
        for (Pregunta pregunta : stack.getPreguntas()) {
            preguntas.addElement(pregunta.getId()+": "+pregunta.getContenido());
        }
        list1.setModel(preguntas);

        iniciar_login_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stack.getActivo().getName().equals("")){
                    dispose();
                    JFrame frame = new Login_Register("StackOverflow GFR", stack);
                    frame.setVisible(true);
                }else{
                    stack.logout();
                    iniciar_login_logout.setText("Ingresar o Registrarse");
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
                    dispose();
                    JFrame frame = new AddQuestion("Agregar Pregunta - StackOverflow GFR",stack,"","");
                    frame.setVisible(true);
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
                    JFrame frame = new PreguntasGUI("Principal - StackOverflow GFR",stack,id-1);
                    frame.setVisible(true);
                }
            }
        });

        tusPreguntasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
