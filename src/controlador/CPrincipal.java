package controlador;

import modelo.*;
import vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CPrincipal{

    public CPrincipal(Stack stack,Principal vista){

        // oculta la información que se pone visible solo para usuarios ingresados
        if (stack.getActivo().getName().equals("")){
            vista.iniciar_login_logout.setText("Ingresar o Registrarse");
            vista.reputacion.setVisible(false);
        }else{
            vista.iniciar_login_logout.setText("Cerrar Sesión");
            vista.reputacion.setText("Reputación: "+stack.getActivo().getReputacion());
            vista.reputacion.setVisible(true);
        }

        DefaultListModel<String> preguntas = new DefaultListModel<String>();
        for (Pregunta pregunta : stack.getPreguntas()) {
            preguntas.addElement(pregunta.getId()+": "+pregunta.getContenido());
        }
        vista.list1.setModel(preguntas);

        vista.setVisible(true);

        vista.list1.getSelectionModel().addListSelectionListener(e -> {
            vista.success.setText("");
            int id = vista.list1.getSelectedIndex();
            vista.textPane1.setText(stack.getPreguntas()[id].mostrarPregunta());
        });

        vista.agregarPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stack.getActivo().getName().equals("")) {
                    vista.success.setText("Debes tener sesión activa para realizar acciones");
                } else {
                    vista.dispose();
                    AddQuestion vistaAddQ = new AddQuestion();
                    new CAddQuestion(stack,vistaAddQ);
                }
            }
        });
        
        vista.button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vista.textPane1.getText().equals("")){
                    vista.success.setText("Debe seleccionar una pregunta primero");
                }else if(stack.getActivo().getName().equals("")){
                    vista.success.setText("Debe iniciar sesión antes de realizar cualquier acción");
                }else{
                    vista.dispose();
                    int id = Character.getNumericValue(vista.textPane1.getText().charAt(0));
                    PreguntasGUI vPregunta = new PreguntasGUI();
                    new CPregunta(stack,vPregunta,id-1);
                }
            }
        });
        
        vista.iniciar_login_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stack.getActivo().getName().equals("")){
                    vista.dispose();
                    Login_Register vistaLog = new Login_Register();
                    new CLogin_Register(stack,vistaLog);
                }else{
                    stack.logout();
                    vista.iniciar_login_logout.setText("Ingresar o Registrarse");
                }
            }
        });
    }
}
