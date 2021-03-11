package vista;
/**
 * clase que genera una ventana con un formulario para agregar una pregunta
 * @version 10/03/2021
 * @author Gabriela Fernández
 */

import javax.swing.*;

public class AddQuestion extends JFrame{
    public JPanel panel1;
    public JTextField titulo;
    public JTextField contenido;
    public JButton volverButton;
    public JButton crearButton;
    public JList<String> list1;
    public JTextArea etSelecionadas;
    public JLabel success;

    // Constructor de la ventana
    public AddQuestion() {
        super("");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((panel1));
        this.setSize(1450,850);
        etSelecionadas.setText("Etiquetas Seleccionadas:");
        success.setVisible(false);
    }
}
