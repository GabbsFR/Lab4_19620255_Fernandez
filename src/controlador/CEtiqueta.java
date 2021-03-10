package controlador;

import vista.*;
import modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CEtiqueta {

    public CEtiqueta(Stack stack,EtiquetaGUI vista) {

        vista.setVisible(true);

        vista.crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreText = vista.nombre.getText();
                String descripcionText = vista.descripcion.getText();

                if (!nombreText.equals("") && !descripcionText.equals("")){
                    Etiqueta etiqueta = new Etiqueta(nombreText, descripcionText);
                    stack.addEtiqueta(etiqueta);
                    vista.dispose();
                    AddQuestion vAddQ = new AddQuestion();
                    new CAddQuestion(stack,vAddQ);
                }else{
                    vista.success.setText("Debe escribir nombre y descripción");
                }
            }
        });

        //botón que permite volver a la página principal
        vista.volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                AddQuestion vAddQ = new AddQuestion();
                new CAddQuestion(stack,vAddQ);
            }
        });
    }
}
