package controlador;

import modelo.*;
import vista.AddQuestion;
import vista.EtiquetaGUI;
import vista.Principal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CAddQuestion{

    public Etiqueta[] etSel;
    public Etiqueta[] aux;

    public CAddQuestion(Stack stack, AddQuestion vista) {

        DefaultListModel<String> etiquetas = new DefaultListModel<>();
        for (Etiqueta etiqueta : stack.getEtiquetas()) {
            etiquetas.addElement(etiqueta.getNombre()+": "+etiqueta.getDescripcion());
        }
        etiquetas.addElement("Agregar Etiqueta");
        vista.list1.setModel(etiquetas);

        vista.setVisible(true);

        vista.volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                Principal vPrincipal = new Principal();
                new CPrincipal(stack,vPrincipal);
            }
        });

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
                    CPrincipal cPrincipal = new CPrincipal(stack,vPrincipal);
                }
            }
        });

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
