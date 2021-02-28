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
    public Etiqueta[] getEtiquetas() {
        return etiquetas;
    }
    public void setEtiquetas(Etiqueta[] etiquetas) {
        this.etiquetas = etiquetas;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
    public User getAutor() {
        return autor;
    }
    public void setAutor(User autor) {
        this.autor = autor;
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
    public void setRecompensa(int recompensa) {
        this.recompensa = recompensa;
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
    /*
    método que permite agregar una respuesta al array de respuestas
    dom : la respuesta que se quiere agregar
    rec : vacío, dentro del método se modifica el array de respuestas
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
    /*
    método que agrega recompensa a la pregunta
    dom: cantidad que se desea agregar
    rec: vacía, se modifica la recompensa dentro del método
     */
    public void agregarRecompensa(int n){
        this.recompensa = this.recompensa + n;
    }
    /*
    método que permite mostrar las respuestas a la pregunta
     */

    public String mostrarPregunta(){
        String p = this.id + ": "+ this.contenido +
                "\n    Autor/a: " + this.autor.getName() +
                "\n    Estado: " + this.estado +
                "\n    Votos a favor: " + this.vf +
                "\n    Votos en contra: " + this.vc +
                "\n    Recompensa: " + this.recompensa;
        return  p;
    }
}