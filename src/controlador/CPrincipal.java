package controlador;
/**
 * clase que controla la vista "Principal",
 * se ocupa de reaccionar a las acciones que tome el usuario en la ventana "Principal"
 * @version 10/03/2021
 * @author Gabriela Fernández
 */
import modelo.*;
import vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CPrincipal{
    //Constructor
    public CPrincipal(Stack stack,Principal vista){
        // Se configura de maneras diferentes el boton de ingresar/registrarse o cerrar sesión y tambien el label de reputacion
        if (stack.getActivo().getName().equals("")){ //si no se tiene usuario activo
            vista.iniciar_login_logout.setText("Ingresar o Registrarse");
            vista.reputacion.setVisible(false);
        }else{//si se tiene usuario activo
            vista.iniciar_login_logout.setText("Cerrar Sesión");
            vista.reputacion.setText("Reputación: "+stack.getActivo().getReputacion());
            vista.reputacion.setVisible(true);
        }

        // Se rellena el componente JList con las preguntas que tiene el stack
        DefaultListModel<String> preguntas = new DefaultListModel<String>();
        for (Pregunta pregunta : stack.getPreguntas()) {
            preguntas.addElement(pregunta.getId()+": "+pregunta.getContenido());
        }
        vista.list1.setModel(preguntas);

        // Se muestra visible la ventana "Principal"
        vista.setVisible(true);
        /**
         * Al presionar en algun elemento de lista1, la pregunta seleccionada se despliega con mayor información en textPanel
         */
        vista.list1.getSelectionModel().addListSelectionListener(e -> {
            vista.success.setText("");
            int id = vista.list1.getSelectedIndex();
            vista.textPane1.setText(stack.getPreguntas()[id].mostrarPregunta());
        });
        /**
         * Si se presiona el botón "agregarPregunta" se redirige a la vista "AddQuestion" solo si existe usuario activo
         */
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
        /**
         * Si se presiona el botón "seleccionarPregunta" se redirige a la vista "PreguntasGUI"
         * solo si se tiene usuario activo y se ha seleccionado una pregunta en la lista
         */
        vista.seleccionarPregunta.addActionListener(new ActionListener() {
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
        /**
         * Si se presiona el botón "iniciar_login_logout" se redirige a la vista "Login_Register" si es que existe
         * usuario activo de lo contrario se redirige a la misma vista pero se cierra la sesión del stack
         */
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
