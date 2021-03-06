package modelo;
/**
 * Una clase para representar
 * @version  10/03/2021
 * @author Gabriela Fernández
 */
import java.util.Date;

public class Pregunta {
    private int id;
    private Respuesta [] respuestas;
    private Etiqueta [] etiquetas;
    private String titulo;
    private String contenido;
    private Date fecha;
    private User autor;
    private String estado;
    private int vf;
    private int vc;

    private int recompensa;
    //constructor
    Pregunta(int id, Etiqueta [] etiquetas, String titulo, String contenido, Date fecha, User autor){
        this.id = id;
        this.etiquetas = etiquetas;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.autor = autor;
        this.estado = "abierta";
        this.recompensa = 0;
        this.vf = 0;
        this.vc = 0;
    }
    // Setters y Getters de atributos
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Respuesta[] getRespuestas() {
        return respuestas;
    }
    public void setRespuestas(Respuesta[] respuestas) {
        this.respuestas = respuestas;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getContenido() {
        return contenido;
    }
    public User getAutor() {
        return autor;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getRecompensa() {
        return recompensa;
    }
    public int getVc() {
        return vc;
    }
    public void setVc(int vc) {
        this.vc = vc;
    }
    public int getVf() {
        return vf;
    }
    public void setVf(int vf) {
        this.vf = vf;
    }
    /*
        Métodos
         */

    /**
     * método que permite agregar una respuesta al array de respuestas
     * @param respuesta la respuesta que se agregará
     */
    public void addRespuesta(Respuesta respuesta){
        if (this.respuestas == null){
            this.respuestas = new Respuesta[1];
            this.respuestas[0] = respuesta;
        }else{
            Respuesta[] aux = this.respuestas;
            this.respuestas = new Respuesta[aux.length +1];
            for (int i = 0; i < aux.length; i++) {
                this.respuestas[i] = aux[i];
            }
            this.respuestas[aux.length] = respuesta;
        }
    }

    /**
     * método que agrega recompensa a la pregunta
     * @param n cantidad de recompensa que se agregará
     */
    public void agregarRecompensa(int n){
        this.recompensa = this.recompensa + n;
    }

    /**
     * método que entrega un String con la información de la pregunta
     * @return string que contiene la información de la pregunta ordenad
     */
    public String mostrarPregunta(){
        String p = this.id + ": "+ this.contenido +
                "\n    Autor/a: " + this.autor.getName() +
                "\n    Estado: " + this.estado +
                "\n    Votos a favor: " + this.vf +
                "\n    Votos en contra: " + this.vc +
                "\n    Recompensa: " + this.recompensa +
                "\n    Etiquetas: ";
        for (Etiqueta etiqueta : this.etiquetas) {
            p = p + etiqueta.getNombre() + "; ";
        }
        return  p;
    }
}