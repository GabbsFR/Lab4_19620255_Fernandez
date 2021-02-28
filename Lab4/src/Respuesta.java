import java.util.Date;

public class Respuesta {
    private int id;
    private int idPregunta;
    private User autor;
    private String contenido;
    private Date fecha;
    private int vf;
    private int vc;

    Respuesta(int id, int idPregunta, User autor, String contenido, Date fecha){
        this.id = id;
        this.idPregunta = idPregunta;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = fecha;
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
    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }
    public User getAutor() {
        return autor;
    }
    public void setAutor(User autor) {
        this.autor = autor;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String mostrarRespuesta(){
        String r = this.id + "\n" + this.getContenido() +
                "\n    Votos a favor: "+ this.vf +
                "\n    Votos en contra: "+ this.vc +
                "\n    Autor: " + this.autor.getName() + "\n    fecha: " + this.fecha;
        return r;
    }
}
