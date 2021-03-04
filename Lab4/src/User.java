public class User {
    private String name; // nombre del usuario
    private String pass; // contraseña del usuario
    private int reputacion; // reputacion del usuario
    //constructor
    User(String name, String pass){
        this.name = name;
        this.pass = pass;
        this.reputacion = 100; //se asigna reputacion inical de 100
    }
    // Setters y Getters de atributos
    public String getName() {
        return name;
    }
    public String getPass() {
        return pass;
    }
    public int getReputacion() {
        return reputacion;
    }
    public void setReputacion(int reputacion) {
        this.reputacion = reputacion;
    }
}
