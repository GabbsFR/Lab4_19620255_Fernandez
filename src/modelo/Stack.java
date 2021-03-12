package modelo;
/**
 * clase para representar un contenedor de preguntas, respuestas, etiquetas y usuarios
 * @author Gabriela Fernández
 */

import java.util.Calendar;
import java.util.Date;

public class Stack {
    private Pregunta [] preguntas;
    private Respuesta [] respuestas;
    private User [] users;
    private User activo;
    private Etiqueta[] etiquetas;

    public Stack() {
        this.preguntas = new Pregunta[0];
        this.respuestas = new Respuesta[0];
        this.users = new User[0];
        this.activo = new User("","");
        this.etiquetas = new Etiqueta[0];
        this.inicial();
    }
    // Setters y Getters de atributos
    public Pregunta[] getPreguntas() {
        return preguntas;
    }
    public User[] getUsers() {
        return users;
    }
    public User getActivo() {
        return activo;
    }
    public void setActivo(User activo) {
        this.activo = activo;
    }
    public Etiqueta[] getEtiquetas() {
        return etiquetas;
    }

    // Métodos
    /**
     * método que permite agregar una pregunta al array de preguntas
     * @param pregunta la pregunta que se quiere agregar al stack
     */
    public void addPregunta(Pregunta pregunta){
        Pregunta[] auxP = this.preguntas;
        this.preguntas = new Pregunta[auxP.length +1];

        for (int i = 0; i < auxP.length; i++) {
            this.preguntas[i] = auxP[i];
        }
        this.preguntas[auxP.length] = pregunta;
    }
    /**
     * método que permite agregar una respuesta al array de respuestas
     * @param respuesta la respuesta que se quiere agregar al stack
     */
    public void addRespuesta(Respuesta respuesta){
        Respuesta[] auxR = this.respuestas;
        this.respuestas = new Respuesta[auxR.length +1];

        for (int i = 0; i < auxR.length; i++) {
            this.respuestas[i] = auxR[i];
        }
        this.respuestas[auxR.length] = respuesta;
    }

    /**
     * método que permite agregar un user al array de users
     * @param user usuario que se quiere agregar al stack
     */
    public void addUser(User user){
        User[] auxU = this.users;
        this.users = new User[auxU.length +1];

        for (int i = 0; i < auxU.length; i++) {
            this.users[i] = auxU[i];
        }
        this.users[auxU.length] = user;
    }
    /**
     * método que permite agregar una etiqueta al array de users
     * @param etiqueta etiqueta que se quiere agregar al stack
     */
    public void addEtiqueta(Etiqueta etiqueta){
        Etiqueta[] auxE = this.etiquetas;
        this.etiquetas = new Etiqueta[auxE.length +1];

        for (int i = 0; i < auxE.length; i++) {
            this.etiquetas[i] = auxE[i];
        }
        this.etiquetas[auxE.length] = etiqueta;
    }

    //
    // Métodos pedidos
    // register
    /**
     * Método que registra a un usuario con su nombre y contraseña
     * @param name nombre del usuario a registrar
     * @param pass contraseña que tendrá el usuario
     * @return true si se realizo el registro sin problemas, false si se encontró otro usuario con el mismo nombre
     */

    public boolean register(String name, String pass){
        boolean flag = true;
        for (User user : this.getUsers()) {
            if (user.getName().equals(name)){
                flag = false;
            }
        }
        if (flag){
            User usuario = new User(name,pass);
            this.addUser(usuario);
        }
        return flag;
    }
    // login
    /**
     * Método que ingresa a un usuario con su nombre y contraseña si estas coinciden con algún usuario registrado
     * @param name nombre del usuario que quiere ingresar
     * @param pass contraseña que da el usuario
     * @return true si no se encontró usuario y contraseña que coincidan, false si se logro ingresar al usuario
     */
    public boolean login(String name, String pass){
        boolean flag = true;
        for (User user : this.getUsers()) {
            if (user.getName().equals(name)){
                if (user.getPass().equals(pass)){
                    flag = false;
                    this.setActivo(user);
                }
            }
        }
        return flag;
    }
    // logout
    /**
     *  Método que cierra la sesión al usuario activo
     */
    public void logout(){
        if (!this.getActivo().getName().equals("")){
            User vacio = new User("","");
            this.setActivo(vacio);
        }
    }
    // ask
    /**
     * Método que ingresa una nueva pregunta
     * @param titulo título de la pregunta a crear
     * @param contenido contenido de la pregunta a crear
     * @param etiquetas etiquetas que llevara la pregunta
     */
    public void ask(String titulo, String contenido, Etiqueta[] etiquetas){
        int id = this.getPreguntas().length + 1;
        Date fecha = Calendar.getInstance().getTime();
        Pregunta pregunta = new Pregunta(id,etiquetas,titulo,contenido,fecha,this.getActivo());
        this.addPregunta(pregunta);
    }
    // answer
    /**
     * Método que ingresa una nueva respuesta
     * @param pregunta la pregunta que se contestará
     * @param contenido el contenido de la respuesta
     */
    public void answer(Pregunta pregunta,String contenido){
        Date fecha = Calendar.getInstance().getTime();
        Respuesta respuesta;
        if (pregunta.getRespuestas() == null){
            respuesta = new Respuesta(1, pregunta.getId(),this.getActivo(), contenido, fecha);
        }else {
            respuesta = new Respuesta(pregunta.getRespuestas().length + 1, pregunta.getId(), this.getActivo(), contenido, fecha);
        }
        this.addRespuesta(respuesta);
        pregunta.addRespuesta(respuesta);
    }
    // reward
    /**
     * Método que retiene la recompensa ofrecida por el usuario y la pone en la recompensa de la pregunta
     * @param pregunta pregunta a la que se le ofrece recompensa
     * @param recompensa cantidad que se ofrece de recompensa
     */
    public void reward(Pregunta pregunta,int recompensa){
        if (this.getActivo().getReputacion() >= recompensa){
            pregunta.agregarRecompensa(recompensa);
            this.getActivo().setReputacion(this.getActivo().getReputacion() - recompensa);
        }
    }
    // accept
    /**
     * Método que cierra la pregunta y entrega las recompensas al autor de la respuesta
     * y entrega una pequeña recompensa al autor de la pregunta
     * @param pregunta pregunta en la que se está aceptando una respuesta
     * @param respuesta respuesta que esta siendo aceptada
     */
    public void accept(Pregunta pregunta,Respuesta respuesta){
        pregunta.setEstado("cerrada");
        respuesta.setEstado("aceptada");
        respuesta.getAutor().setReputacion(respuesta.getAutor().getReputacion() + pregunta.getRecompensa() + 15);
        pregunta.getAutor().setReputacion(pregunta.getAutor().getReputacion() + 2);
    }
    // vote
    /**
     * Método que permite votar una pregunta ya sea a favor o en contra y otorga las reputaciones
     * @param pregunta pregunta que se quiere votar
     * @param tipo true si se quiere votar a favor, false si se quiere votar en contra
     */
    public void vote(Pregunta pregunta, boolean tipo){
        if (tipo){
            pregunta.getAutor().setReputacion(pregunta.getAutor().getReputacion() + 10);
            pregunta.setVf(pregunta.getVf() +1);
        }else{
            pregunta.getAutor().setReputacion(pregunta.getAutor().getReputacion() - 2);
            pregunta.setVc(pregunta.getVc() +1);
        }
    }
    /**
     * Método que permite votar una respuesta ya sea a favor o en contra y otorga las reputaciones
     * @param respuesta respuesta que se quiere votar
     * @param tipo true si se quiere votar a favor, false si se quiere votar en contra
     */
    public void vote(Respuesta respuesta, boolean tipo){
        if (tipo){
            respuesta.getAutor().setReputacion(respuesta.getAutor().getReputacion() + 10);
            respuesta.setVf(respuesta.getVf()+1);
        }else{
            respuesta.getAutor().setReputacion(respuesta.getAutor().getReputacion() - 2);
            this.getActivo().setReputacion(this.getActivo().getReputacion() - 1);
            respuesta.setVc(respuesta.getVc()+1);
        }
    }


    /**
     * Método que inicializa el Stack con preguntas, respuestas, usuarios y etiquetas definidas en el mismo método
     */
    public void inicial(){
        User u1 = new User("Meredith","Grey");
        User u2 = new User("Cristina","Yang");
        User u3 = new User("Alex","Karev");
        this.addUser(u1);
        this.addUser(u2);
        this.addUser(u3);

        Etiqueta e1 = new Etiqueta("McDreamy","Relacionado con Dereck Shepard");
        Etiqueta e2 = new Etiqueta("McSteamy","Relacionado con Mark Sloan");
        Etiqueta e3 = new Etiqueta("Interns","Relacionado con l@s intern@s");
        Etiqueta[] e = {e1,e2,e3};
        this.addEtiqueta(e1);
        this.addEtiqueta(e2);
        this.addEtiqueta(e3);

        Pregunta p1 = new Pregunta(1,e,"Episodio 1","¿En que casa esta Derek en el primer episodio?",Calendar.getInstance().getTime(),u2);
        Pregunta p2 = new Pregunta(2,e,"General","¿De quien es hija Meredith?",Calendar.getInstance().getTime(),u2);
        Pregunta p3 = new Pregunta(3,e,"Pregunta Episodio 1","¿Como se llaman las hermanas biológicas de Meredith?",Calendar.getInstance().getTime(),u1);
        this.addPregunta(p1);
        this.addPregunta(p2);
        this.addPregunta(p3);

        Respuesta r1 = new Respuesta(1,1,u3,"En casa de Meredith",Calendar.getInstance().getTime());
        Respuesta r2 = new Respuesta(1,2,u3,"De Ellis Grey y Thatcher Grey",Calendar.getInstance().getTime());
        Respuesta r3 = new Respuesta(1,3,u3,"Lexi",Calendar.getInstance().getTime());
        Respuesta r4 = new Respuesta(2,3,u2,"Maggie",Calendar.getInstance().getTime());
        Respuesta[] r = {r1};
        Respuesta[] rr = {r2};
        Respuesta[] rrr = {r3,r4};
        p1.setRespuestas(r);
        p2.setRespuestas(rr);
        p3.setRespuestas(rrr);
        this.addRespuesta(r1);
        this.addRespuesta(r2);
        this.addRespuesta(r3);
        this.addRespuesta(r4);
    }
}
