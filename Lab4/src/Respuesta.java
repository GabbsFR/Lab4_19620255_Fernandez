import java.util.Date;

public class Respuesta {
    private int id;
    private int idPregunta;
    private User autor;
    private String contenido;
    private Date fecha;
    Respuesta(int id, int idPregunta, User autor, String contenido, Date fecha){
        this.id = id;
        this.idPregunta = idPregunta;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = fecha;
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

    public String mostrarRespuesta(){
        String r = this.id + "\n" + this.getContenido() + "\n"+
                "    Autor: " + this.autor + "    fecha: " + this.fecha + this.;
    }
}
