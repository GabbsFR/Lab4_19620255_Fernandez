package controlador;
/**
 * Una clase comunicar las clases del modelo a la de la vista "PreguntasGUI"
 * @version  10/03/2021
 * @author Gabriela Fernández
 */
import vista.*;
import modelo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CPregunta {

    public Stack stack;
    public PreguntasGUI vista;
    public Pregunta pregunta;
    public int idP;

    public CPregunta(Stack s, PreguntasGUI vPreg, int id) {
       stack = s;
       vista = vPreg;
       idP = id;
       pregunta = stack.getPreguntas()[idP];

       inicializar();

       vista.setVisible(true);
        /**
         * cuando se selecciona una respuesta de la lista de respuestas,
         * pone la información de la misma en un recuadro en la ventana
         */
        vista.list1.getSelectionModel().addListSelectionListener(e -> {
           vista.success.setText("");
            int idRespuesta =vista.list1.getSelectedIndex();
           vista.respuestaSeleccionada.setText(idRespuesta +pregunta.getRespuestas()[idRespuesta].mostrarRespuesta());
        });

        /**
         * Al presionar el botón "volver" se redirige a la vista "Principal"
         */
        vista.volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                Principal vPrincipal = new Principal();
                new CPrincipal(stack,vPrincipal);
            }
        });

        /**
         * Al presionar el botón "agregarRespuestaButton" permite agregar una respuesta
         * siempre y cuando este relleno el campo de texto
         */
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

        /**
         * Al presionar el botón "agregarRecompensa" permite agregar una recompensa
         * siempre que se cumpla con las condiciones
         */
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

        /**
         * Al presionar el botón "aceptarRespuesta" permite aceptar una respuesta
         * (esta opción le saldrá disponible solo al autor de la pregunta)
         */
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

        /**
         * Al presionar el botón "votarPreguntaAFavorButton" Permite votar a favor de la pregunta
         */
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

        /**
         * Al presionar el botón "votarPreguntaEnContraButton" Permite votar a en contra de la pregunta
         */
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

        /**
         * Al presionar el botón "votarRespuestaAFavorButton" Permite votar a favor de una
         * respuesta siempre que se haya seleccionado alguna
         */
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
        /**
         * Al presionar el botón "votarRespuestaEnContraButton" Permite votar en contra de una
         * respuesta siempre que se haya seleccionado alguna
         */
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
    /**
     * Método que configura la ventana "PreguntasGUI" con la información del Stack y de la pregunta seleccionada
     */
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

        // rellena el componenete JList con las respuestas de la pregunta
        DefaultListModel<String> respuestas = new DefaultListModel<>();
        for (Respuesta respuesta : pregunta.getRespuestas()) {
            respuestas.addElement(respuesta.getId()+": "+respuesta.getContenido());
        }
        vista.list1.setModel(respuestas);
    }

}
