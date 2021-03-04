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
    }
    // Setters y Getters de atributos
    public Pregunta[] getPreguntas() {
        return preguntas;
    }
    public void setPreguntas(Pregunta[] preguntas) {
        this.preguntas = preguntas;
    }
    public Respuesta[] getRespuestas() {
        return respuestas;
    }
    public void setRespuestas(Respuesta[] respuestas) {
        this.respuestas = respuestas;
    }
    public User[] getUsers() {
        return users;
    }
    public void setUsers(User[] users) {
        this.users = users;
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
    public void setEtiquetas(Etiqueta[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    // Métodos
    /*
    método que permite agregar una pregunta al array de preguntas
    dom : Pregunta pregunta
    rec : void
    */
    public void addPregunta(Pregunta pregunta){
        Pregunta[] auxP = this.preguntas;
        this.preguntas = new Pregunta[auxP.length +1];

        for (int i = 0; i < auxP.length; i++) {
            this.preguntas[i] = auxP[i];
        }
        this.preguntas[auxP.length] = pregunta;
    }
    /*
    método que permite agregar una respuesta al array de respuestas
    dom : la respuesta que se quiere agregar
    rec : vacío, dentro del método se modifica el array de respuestas
     */
    public void addRespuesta(Respuesta respuesta){
        Respuesta[] auxR = this.respuestas;
        this.respuestas = new Respuesta[auxR.length +1];

        for (int i = 0; i < auxR.length; i++) {
            this.respuestas[i] = auxR[i];
        }
        this.respuestas[auxR.length] = respuesta;
    }
    /*
    método que permite agregar un user al array de users
    dom : el user que se quiere agregar
    rec : vacío, dentro del método se modifica el array de users
     */
    public void addUser(User user){
        User[] auxU = this.users;
        this.users = new User[auxU.length +1];

        for (int i = 0; i < auxU.length; i++) {
            this.users[i] = auxU[i];
        }
        this.users[auxU.length] = user;
    }
    /*
    método que permite agregar una etiqueta al array de users
    dom : la etiqueta que se quiere agregar
    rec : vacío, dentro del método se modifica el array de etiquetas
     */
    public void addEtiqueta(Etiqueta etiqueta){
        Etiqueta[] auxE = this.etiquetas;
        this.etiquetas = new Etiqueta[auxE.length +1];

        for (int i = 0; i < auxE.length; i++) {
            this.etiquetas[i] = auxE[i];
        }
        this.etiquetas[auxE.length] = etiqueta;
    }

    // mostrar Etiquetas
    public void mostrarEtiquetas(){
        System.out.println("Etiquetas: ");
        String e;
        for (int i = 0; i < etiquetas.length; i++) {
            e = String.valueOf(i+1) + ".- "+ etiquetas[i].getNombre() + " : " + etiquetas[i].getDescripcion();
            System.out.println(e);
        }
    }
    // mostrar preguntas de usuario y que esten abiertas
    public void mostrarParaAccept(){
        System.out.println("Preguntas: ");
        for (int i = 0; i < preguntas.length; i++) {
            if (preguntas[i].getAutor().getName().equals(activo.getName()) && preguntas[i].getEstado().equals("abierta")) {
                System.out.println(preguntas[i].getId() + ": " + preguntas[i].getContenido());
                System.out.println("    Estado: " + preguntas[i].getEstado() + " recompensa: " + preguntas[i].getRecompensa());
                System.out.println("    Respuestas:");
                for (Respuesta r : respuestas) {
                    if (r.getIdPregunta() == preguntas[i].getId()){
                        System.out.println("        "+r.getId()+": "+r.getContenido());
                    }
                }
            }
        }
    }
    //
    // Métodos pedidos
    // register
    /*
    Método que registra a un usuario con su nombre y contraseña
    Dom: Stack stack, String name, String pass
    Rec: Void
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
    /*
    Método que ingresa a un usuario con su nombre y contraseña si estas coinciden con algun usuario registrado
    Dom: Stack stack, String name, String pass
    Rec: Void
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
    /*
    Método que cierra la sesión al usuario activo
    Dom: Stack stack
    Rec: Void
    */
    public void logout(){
        if (!this.getActivo().getName().equals("")){
            User vacio = new User("","");
            this.setActivo(vacio);
        }
    }
    // ask
    /*
    Método que ingresa una nueva pregunta
    Dom: Stack stack, String titulo, String contenido, Etiqueta[] etiquetas
    Rec: Void
    */
    public void ask(String titulo, String contenido, Etiqueta[] etiquetas){
        int id = this.getPreguntas().length + 1;
        Date fecha = Calendar.getInstance().getTime();
        Pregunta pregunta = new Pregunta(id,etiquetas,titulo,contenido,fecha,this.getActivo());
        this.addPregunta(pregunta);
    }
    // answer
    /*
    Método que ingresa una nueva respuesta
    Dom: Stack stack,Pregunta pregunta,String contenido
    Rec: Void
    */
    public void answer(Stack stack,Pregunta pregunta,String contenido){
        Date fecha = Calendar.getInstance().getTime();
        Respuesta respuesta;
        if (pregunta.getRespuestas() == null){
            respuesta = new Respuesta(1, pregunta.getId(), stack.getActivo(), contenido, fecha);
        }else {
            respuesta = new Respuesta(pregunta.getRespuestas().length + 1, pregunta.getId(), stack.getActivo(), contenido, fecha);
        }
        stack.addRespuesta(respuesta);
        pregunta.addRespuesta(respuesta);
    }
    // reward
    /*
    Método que retiene la recompensa ofrecida por el usuario y la pone en la recompensa de la pregunta
    Dom: Stack stack,Pregunta pregunta,int recompensa
    Rec: Void
    */
    public void reward(Pregunta pregunta,int recompensa){
        if (this.getActivo().getReputacion() >= recompensa){
            pregunta.agregarRecompensa(recompensa);
            this.getActivo().setReputacion(this.getActivo().getReputacion() - recompensa);
        }
    }
    // accept
    /*
    Método que cierra la pregunta y entrega las recompensas al autor de la respuesta y entrega una pequeña recompensa al autor de la pregunta
    Dom: Pregunta pregunta,Respuesta respuesta
    Rec: Void
    */
    public void accept(Pregunta pregunta,Respuesta respuesta){
        pregunta.setEstado("cerrada");
        respuesta.getAutor().setReputacion(respuesta.getAutor().getReputacion() + pregunta.getRecompensa() + 15);
        pregunta.getAutor().setReputacion(pregunta.getAutor().getReputacion() + 2);
    }
    // vote
    /*
    Método que permite votar una pregunta ya sea a favor o en contra y otorga las reputaciones
    Dom: Pregunta pregunta,Respuesta respuesta
    Rec: Void
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
    /*
    Método que permite votar una respuesta ya sea a favor o en contra y otorga las reputaciones
    Dom: Pregunta pregunta,Respuesta respuesta
    Rec: Void
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
