public class Etiqueta {
    private String nombre;
    private String descripcion;
    //constructor etiqueta
    Etiqueta(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    // Setters y Getters de atributos
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
}

