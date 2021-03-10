package vista;

/*
Ventana principal que permite ver las preguntas y su información sin la necesidad de estar ingresado/a
 */

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

public class Principal extends JFrame{

    public JPanel panel;
    public JButton iniciar_login_logout;
    public JList<String> list1;
    public JTextPane textPane1;
    public JButton agregarPreguntaButton;
    public JButton button3;
    public JLabel success;
    public JLabel reputacion;

    public Principal() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.panel);
        this.setSize(1450,850);
    }
}
