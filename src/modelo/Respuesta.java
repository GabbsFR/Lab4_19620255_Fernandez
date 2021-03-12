package modelo;
/**
 * clase para representar una respuesta
 * @version 10/03/2021
 * @author Gabriela Fernández
 */

import java.util.Date;

public class Respuesta {
    private int id;
    private int idPregunta;
    private User autor;
    private String contenido;
    private Date fecha;
    private String estado;

    private int vf;
    private int vc;

    Respuesta(int id, int idPregunta, User autor, String contenido, Date fecha){
        this.id = id;
        this.idPregunta = idPregunta;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = fecha;
        this.estado = "";
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
    public int getIdPregunta() {
        return idPregunta;
    }
    public User getAutor() {
        return autor;
    }
    public String getContenido() {
        return contenido;
    }
    public int getVf() {
        return vf;
    }
    public void setVf(int vf) {
        this.vf = vf;
    }
    public int getVc() {
        return vc;
    }
    public void setVc(int vc) {
        this.vc = vc;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * Metodo que toma la información de la respuesta y genera un String con la información organizada
     * @return String con información de la respuesta
     */
    public String mostrarRespuesta(){
        String r = this.id + "\n" + this.getContenido() +
                "\n    Votos a favor: "+ this.vf +
                "\n    Votos en contra: "+ this.vc +
                "\n    Estado: "+ this.estado+
                "\n    Autor: " + this.autor.getName() + "\n    fecha: " + this.fecha;
        return r;
    }
}
