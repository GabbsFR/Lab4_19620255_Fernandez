import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

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
    // mostrar preguntas
    public void mostrarPreguntas(){
        System.out.println("Preguntas: ");
        for (int i = 0; i < preguntas.length; i++) {
            System.out.println(preguntas[i].getId() + ": "+ preguntas[i].getContenido());
            System.out.println("    Estado: "+preguntas[i].getEstado()+" recompensa: "+preguntas[i].getRecompensa());
            System.out.println("    Respuestas: ");
            if (preguntas[i].getRespuestas() != null){
                preguntas[i].mostrarRespuestas();
            }
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

}
