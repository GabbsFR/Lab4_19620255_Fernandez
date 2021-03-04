import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EtiquetaGUI extends JFrame {
    private JPanel panel1;
    private JButton volverButton;
    private JButton crearButton;
    private JTextField nombre;
    private JTextField descripcion;
    private JLabel success;

    public EtiquetaGUI(String title, Stack stack,String titulo, String contenido){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane((panel1));
        success.setVisible(false);


        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreText = nombre.getText();
                String descripcionText = descripcion.getText();

                if (!nombreText.equals("") && !descripcionText.equals("")){
                    Etiqueta etiqueta = new Etiqueta(nombreText, descripcionText);
                    stack.addEtiqueta(etiqueta);
                    dispose();
                    JFrame frame = new AddQuestion("Principal - StackOverflow GFR",stack,titulo,contenido);
                    frame.setVisible(true);
                }else{
                    success.setText("Debe escribir nombre y descripción");
                }
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new AddQuestion("Principal - StackOverflow GFR",stack,titulo,contenido);
                frame.setVisible(true);
            }
        });
    }
}
