package controlador;

import modelo.*;
import vista.*;

public class Main {
    public static void main(String[] args){
        Stack stack = new Stack();
        Principal vista = new Principal();
        new CPrincipal(stack,vista);
        vista.setVisible(true);
    }
}