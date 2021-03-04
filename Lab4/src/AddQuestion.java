/*
Ventana que pide la información necesaria para crear una nueva pregunta,
y permite crearla una vez esta información este rellenada
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddQuestion extends JFrame{
    private JPanel panel1;
    private JTextField titulo;
    private JTextField contenido;
    private JButton volverButton;
    private JButton crearButton;
    private JList<String> list1;
    private JTextArea etSelecionadas;
    private JLabel success;
    private Etiqueta[] etSel;
    private Etiqueta[] aux;

    public AddQuestion(String title, Stack stack,String t,String c) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((panel1));
        this.setSize(1450,850);
        etSelecionadas.setText("Etiquetas Seleccionadas:");
        success.setVisible(false);

        // se revisa si se tenia registro de un titulo y/o contenido (es importante cuando se crea una etiqueta
        if (!t.equals("") || !c.equals("")){
            titulo.setText(t);
            contenido.setText(c);
        }else{
            etSel = new Etiqueta[0];
        }

        DefaultListModel etiquetas = new DefaultListModel();
        for (Etiqueta etiqueta : stack.getEtiquetas()) {
            etiquetas.addElement(etiqueta.getNombre()+": "+etiqueta.getDescripcion());
        }
        etiquetas.addElement("Agregar Etiqueta");
        list1.setModel(etiquetas);

        // botón que permite volver a la ventana principal
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new Principal("Principal - StackOverflow GFR",stack);
                frame.setVisible(true);
            }
        });

        // cuando se selecciona una etiqueta se guarda como parte de las etiquetas seleccionadas
        // a menos que la etiqueta sea la que dice "Agregar Etiqueta"
        // se sabe por que el indice de esa "Etiqueta" es igual al largo de la lista general de etiquetas
        list1.getSelectionModel().addListSelectionListener(e -> {
            int index = list1.getSelectedIndex();
            if (index == stack.getEtiquetas().length){
                dispose();
                JFrame frame = new EtiquetaGUI("Agregar Etiqueta - StackOverflow GFR",stack,titulo.getText(),contenido.getText());
                frame.setVisible(true);
            }else{
                aux = new Etiqueta[etSel.length];
                for (int i = 0; i < etSel.length; i++) {
                    aux[i] = etSel[i];
                }
                etSel = new Etiqueta[aux.length +1];
                for (int i = 0; i < aux.length; i++) {
                    etSel[i] = aux[i];
                }
                etSel[aux.length] = stack.getEtiquetas()[index];
            }
            etSelecionadas.setText(etSelecionadas.getText()+"\n"+stack.getEtiquetas()[index].getNombre());
        });

        // botón que crea la pregunta siempre y cuando se tengan rellenos los textos de título y contenido
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titulo.getText().equals("") || contenido.getText().equals("")){
                    success.setText("Debe ingresar titulo y contenido");
                    success.setVisible(true);
                }else{
                    stack.ask(titulo.getText(),contenido.getText(),etSel);
                    dispose();
                    JFrame frame = new Principal("Principal - StackOverflow GFR",stack);
                    frame.setVisible(true);
                }
            }
        });

    }
}
