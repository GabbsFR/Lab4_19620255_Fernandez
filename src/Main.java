/**
 * clase que inicia el programa
 */

import controlador.*;
import modelo.*;
import vista.*;

public class Main {
    public static void main(String[] args){
        Stack stack = new Stack(); //Stack inicial
        Principal vista = new Principal(); //Vista Principal
        new CPrincipal(stack,vista); //Controlador de la vista principal
        vista.setVisible(true);
    }
}