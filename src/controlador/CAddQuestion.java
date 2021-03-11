package controlador;
/**
 * Una clase para comunicar las clases del modelo a la de la vista "AddQuestion"
 * @version  10/03/2021
 * @author Gabriela Fernández
 */
import modelo.*;
import vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CAddQuestion{

    public Etiqueta[] etSel;
    public Etiqueta[] aux;

    public CAddQuestion(Stack stack, AddQuestion vista) {

        // Se rellena el componente JList con la información de las etiquetas que están en el stack
        DefaultListModel<String> etiquetas = new DefaultListModel<>();
        for (Etiqueta etiqueta : stack.getEtiquetas()) {
            etiquetas.addElement(etiqueta.getNombre()+": "+etiqueta.getDescripcion());
        }
        etiquetas.addElement("Agregar Etiqueta"); //Se agrega la opción de agregar una etiqueta
        vista.list1.setModel(etiquetas);

        // se muestra la ventana
        vista.setVisible(true);

        /**
         * Si se presiona el botón "volverButton" se redirige a la vista "Principal"
         */
        vista.volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                Principal vPrincipal = new Principal();
                new CPrincipal(stack,vPrincipal);
            }
        });

        /**
         * Si se presiona el botón "crearButton" se verifica que se haya escrito en "titulo" y "contenido" y de ser así
         * se llama a stack.ask() con la información obtenida y luego se redirige a la vista "Principal"
         */
        vista.crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vista.titulo.getText().equals("") || vista.contenido.getText().equals("")){
                    vista.success.setText("Debe ingresar titulo y contenido");
                    vista.success.setVisible(true);
                }else{
                    stack.ask(vista.titulo.getText(),vista.contenido.getText(),etSel);
                    vista.dispose();
                    Principal vPrincipal = new Principal();
                    new CPrincipal(stack,vPrincipal);
                }
            }
        });

        /**
         * Si se presiona alguna etiqueta de la lista se agrega su información a la etiquetas seleccionadas
          */
        vista.list1.getSelectionModel().addListSelectionListener(e -> {
            int index = vista.list1.getSelectedIndex();
            if (index == stack.getEtiquetas().length){
                vista.dispose();
                EtiquetaGUI vEtiqueta = new EtiquetaGUI();
                new CEtiqueta(stack,vEtiqueta);
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
            vista.etSelecionadas.setText(vista.etSelecionadas.getText()+"\n"+stack.getEtiquetas()[index].getNombre());
        });
        
    }
}
