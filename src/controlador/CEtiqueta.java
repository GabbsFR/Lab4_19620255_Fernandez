package controlador;
/**
 * Una clase para comunicar las clases del modelo a la de la vista "EtiquetaGUI"
 * @version  10/03/2021
 * @author Gabriela Fernández
 */
import vista.*;
import modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CEtiqueta {

    public CEtiqueta(Stack stack,EtiquetaGUI vista) {
        // Se muestra la ventana
        vista.setVisible(true);

        /**
         * Si se presiona el botón "crearButton" y se rellenaron los espacion de texto, se permite crear una etiqueta
         */
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

        /**
         *  Si se presiona el botón "volverButton" se redirige a la vista "Principal"
         */
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
