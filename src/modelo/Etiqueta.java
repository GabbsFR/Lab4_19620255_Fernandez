package modelo;
/**
 * clase para representar una etiqueta
 * @version 10/03/2021
 * @author Gabriela Fernández
 */
public class Etiqueta {
    private String nombre;
    private String descripcion;
    //constructor etiqueta
    public Etiqueta(String nombre, String descripcion){
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

