package controlador;

import vista.*;
import modelo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CPregunta {

    Stack stack;
    PreguntasGUI vista;
    Pregunta pregunta;
    int idP;

    public CPregunta(Stack s, PreguntasGUI vPreg, int id) {
       stack = s;
       vista = vPreg;
       idP = id;
       pregunta = stack.getPreguntas()[idP];

       inicializar();

       vista.setVisible(true);

        // cuando se selecciona una respuesta de la lista de respuestas, pone la información de la misma en un recuadro en la ventana
        vista.list1.getSelectionModel().addListSelectionListener(e -> {
           vista.success.setText("");
            int idRespuesta =vista.list1.getSelectedIndex();
           vista.respuestaSeleccionada.setText(idRespuesta +pregunta.getRespuestas()[idRespuesta].mostrarRespuesta());
        });

        // permite volver a la ventana principal
        vista.volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                Principal vPrincipal = new Principal();
                new CPrincipal(stack,vPrincipal);
            }
        });

        // permite agregar una respuesta siempre y cuando se rellene el campo de texto
        vista.agregarRespuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vista.contenidoRespuestaIngresada.getText().equals("")){
                    vista.success.setText("Debe ingresar contenido de la respuesta");
                }else{
                    stack.answer(pregunta,vista.contenidoRespuestaIngresada.getText());
                    vista.dispose();
                    PreguntasGUI vPregunta = new PreguntasGUI();
                    new CPregunta(stack,vPregunta,idP);
                }
            }
        });

        // permite agregar una recompensa siempre que se cumpla con las condiciones
        vista.agregarRecompensaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(vista.spinner1.getValue().toString());
                if(value == 0 || value > stack.getActivo().getReputacion()){
                    vista.success.setText("Debe poner un valor distinto de 0 y menor o igual a su reputación que es "+stack.getActivo().getReputacion());
                }else {
                    stack.reward(pregunta,value);
                    vista.dispose();
                    PreguntasGUI vPregunta = new PreguntasGUI();
                    new CPregunta(stack,vPregunta,idP);
                }
            }
        });

        // permite aceptar una respuesta (esta opcion le saldra dispnible solo al autor de la pregunta)
        vista.aceptarRespuestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vista.respuestaSeleccionada.getText().equals("")){
                    vista.success.setText("Debe seleccionar una respuesta primero");
                    vista.success.setVisible(true);
                }else {
                    int idR = Character.getNumericValue(vista.respuestaSeleccionada.getText().charAt(0));
                    Respuesta r = pregunta.getRespuestas()[idR];
                    stack.accept(pregunta, r);
                    vista.dispose();
                    PreguntasGUI vPregunta = new PreguntasGUI();
                    new CPregunta(stack, vPregunta, idP);
                }
            }
        });

        // Permite votar a favor de la pregunta
        vista.votarPreguntaAFavorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(!vista.respuestaSeleccionada.getText().equals("")){
                    stack.vote(pregunta, true);
                    vista.dispose();
                    PreguntasGUI vPregunta = new PreguntasGUI();
                    new CPregunta(stack,vPregunta,idP);
                }else{
                    vista.success.setText("Debe seleccionar una respuesta primero");
                }
            }
        });

        // Permite votar en contra de la pregunta
        vista.votarPreguntaEnContraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!vista.respuestaSeleccionada.getText().equals("")) {
                    stack.vote(pregunta, false);
                    vista.dispose();
                    PreguntasGUI vPregunta = new PreguntasGUI();
                    new CPregunta(stack,vPregunta,idP);
                }else{
                    vista.success.setText("Debe seleccionar una respuesta primero");
                }
            }
        });

        // Permite votar a favor de una respuesta siempre que se haya seleccionado alguna
        vista.votarRespuestaAFavorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!vista.respuestaSeleccionada.getText().equals("")){
                    int idR = Character.getNumericValue(vista.respuestaSeleccionada.getText().charAt(0));
                    Respuesta r = pregunta.getRespuestas()[idR];
                    stack.vote(r,true);
                    vista.dispose();
                    PreguntasGUI vPregunta = new PreguntasGUI();
                    new CPregunta(stack,vPregunta,idP);
                }else{
                    vista.success.setText("Debe seleccionar una respuesta primero");
                }
            }
        });
        // Permite votar en contra de una respuesta siempre que se haya seleccionado alguna
        vista.votarRespuestaEnContraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!vista.respuestaSeleccionada.getText().equals("")){
                    int idR = Character.getNumericValue(vista.respuestaSeleccionada.getText().charAt(0));
                    Respuesta r = pregunta.getRespuestas()[idR];
                    stack.vote(r, false);
                    vista.dispose();
                    PreguntasGUI vPregunta = new PreguntasGUI();
                    new CPregunta(stack,vPregunta,idP);
                }else{
                    vista.success.setText("Debe seleccionar una respuesta primero");
                }
            }
        });
    }

    public void inicializar(){
        vista.user.setText(stack.getActivo().getName());
        vista.tituloPreg.setText(pregunta.getTitulo());
        vista.infoPregunta.setText(pregunta.mostrarPregunta());

        // oculta las opciones que son solo para preguntas abiertas
        if (pregunta.getEstado().equals("cerrada")){
            vista.agregarRecompensaButton.setVisible(false);
            vista.spinner1.setVisible(false);
            vista.aceptarRespuestaButton.setVisible(false);
        }

        // oculta la opción que es solo para el/la autor/a de la pregunta
        if (!pregunta.getAutor().getName().equals(stack.getActivo().getName())){
            vista.aceptarRespuestaButton.setVisible(false);
        }

        DefaultListModel<String> respuestas = new DefaultListModel<>();
        for (Respuesta respuesta : pregunta.getRespuestas()) {
            respuestas.addElement(respuesta.getId()+": "+respuesta.getContenido());
        }
        vista.list1.setModel(respuestas);
    }

}
