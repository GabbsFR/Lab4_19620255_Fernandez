/*
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        //código
        Stack stack = new Stack();
        inicial(stack);
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        int e;
        int e2;
        do{
            e = menu(stack);
            switch (e){
                case 0: //Register, Login
                    System.out.println("Ingrese 1 si desea registrarse\nIngrese 2 si ya esta registrad@\nelección: ");
                    e2 = scan.nextInt();
                    scan.nextLine();
                    String name, pass;
                    System.out.println("Ingrese nombre de usuario: ");
                    name = scan.nextLine();
                    System.out.println("Ingrese contraseña: ");
                    pass = scan.nextLine();
                    if (e2 == 1){
                        register(stack,name,pass);
                        System.out.println(stack.getActivo().getName());
                    }else{
                        System.out.println("login");
                        login(stack,name,pass);
                    }
                    break;
                case 1: // Ask
                    String titulo, contenido;
                    Etiqueta[] etiquetas;
                    System.out.println("Ingrese el titulo de su pregunta: ");
                    titulo = scan.nextLine();
                    System.out.println("Ingrese el contenido de su pregunta: ");
                    contenido = scan.nextLine();
                    System.out.println("¿Cuantas etiquetas desea poner?");
                    int cant = scan.nextInt();
                    scan.nextLine();
                    etiquetas = new Etiqueta[cant];
                    for (int i = 1; i <= cant; i++) {
                        etiquetas[i-1] = etiqueta(stack);
                    }
                    ask(stack,titulo,contenido,etiquetas);
                    break;
                case 2: // Answer
                    stack.mostrarPreguntas();
                    System.out.println("Ingrese número de la pregunta que desea responder: ");
                    int p = scan.nextInt();
                    scan.nextLine();
                    int idP = p -1;
                    System.out.println("Ingrese el contenido de su respuesta");
                    contenido = scan.nextLine();
                    answer(stack,stack.getPreguntas()[idP],contenido);
                    break;
                case 3: // Reward
                    stack.mostrarPreguntas();
                    System.out.println("Ingrese número de la pregunta que desea recompensar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    idP = r -1;
                    System.out.println("Ingrese la recompensa que quiere ofrecer: ");
                    int recompensa = scan.nextInt();
                    reward(stack,stack.getPreguntas()[idP],recompensa);
                    break;
                case 4: // Accept
                    stack.mostrarParaAccept();
                    System.out.println("Ingrese número de la pregunta que quiere cerrar: ");
                    int aP = scan.nextInt();
                    scan.nextLine();
                    idP = aP-1;
                    System.out.println("Ingrese número de la respuesta que quiere aceptar en esa pregunta: ");
                    int aR = scan.nextInt();
                    int idR = aR-1;
                    scan.nextLine();
                    accept(stack.getPreguntas()[idP],stack.getPreguntas()[idP].getRespuestas()[idR]);
                    break;
                case 5: // Vote
                    stack.mostrarPreguntas();
                    System.out.println("Si quieres votar una pregunta ingresa P, si quieres votar una respuesta ingresa R, para volver al menu ingresa cualquier carácter distinto :");
                    char opc = scan.next().charAt(0);
                    scan.nextLine();
                    System.out.println("Si su voto es a favor ingrese F, si su voto es en contra ingrese C, para volver al menu ingrese un carácter distinto :");
                    char bool = scan.next().charAt(0);
                    scan.nextLine();
                    if(opc == 'P'){
                        System.out.println("Ingrese el número de su pregunta");
                        idP = scan.nextInt();
                        scan.nextLine();
                        for (int i = 0; i < stack.getPreguntas().length; i++) {
                            if (bool == 'F'){
                                vote(stack,stack.getPreguntas()[i],true);
                            }else if(bool == 'C'){
                                vote(stack,stack.getPreguntas()[i], false);
                            }
                        }
                    }else if (opc == 'R'){
                        System.out.println("Ingrese el número de la pregunta en la que desea votar una respuesta");
                        idP = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Ingrese el número de la respuesta");
                        idR = scan.nextInt();
                        scan.nextLine();
                        for (int i = 0; i < stack.getRespuestas().length; i++) {
                            if (stack.getRespuestas()[i].getIdPregunta() == idP &&
                                    stack.getRespuestas()[i].getId() == idR) {
                                if (bool == 'F'){
                                    vote(stack,stack.getRespuestas()[i],true);
                                }else if(bool == 'C'){
                                    vote(stack,stack.getRespuestas()[i], false);
                                }
                            }
                        }
                    }
                    //stack.vote();
                    break;
                case 6: // Logout
                    logout(stack);
                    break;
                case 7: // Salir
                    flag = false;
                    break;
                default:
                    break;
            }
        }while(flag);
    }

 */
    /*
    Método que muestra la lista de etiquetas y pregunta si toma la elegida por usuario o pide a usuario que cree una nueva
    Dom: Stack
    Rec: Etiqueta
    */
/*
    public static Etiqueta etiqueta(Stack stack){
        Scanner scan = new Scanner(System.in);
        stack.mostrarEtiquetas();
        System.out.println("Ingresa 0 si deseas agregar una etiqueta o el número de la etiqueta que quieres elegir");
        int et = scan.nextInt();
        scan.nextLine();
        if (et == 0){
            System.out.println("Ingrese nombre : ");
            String nom = scan.nextLine();
            System.out.println("Ingrese descripción : ");
            String des = scan.nextLine();
            Etiqueta etiqueta = new Etiqueta(nom,des);
            stack.addEtiqueta(etiqueta);
            return etiqueta;
        }else{
            return stack.getEtiquetas()[et-1];
        }
    }

 */
    /*
    Método que inicializa un stack con 3 usuarios, 3 preguntas,4 respuestas y 3 etiquetas
    Dom: Stack
    Rec: void
    */
/*
    public static void inicial(Stack stack){
        User u1 = new User("Meredith","Grey");
        User u2 = new User("Cristina","Yang");
        User u3 = new User("Alex","Karev");
        stack.addUser(u1);
        stack.addUser(u2);
        stack.addUser(u3);

        Etiqueta e1 = new Etiqueta("McDreamy","Relacionado con Dereck Shepard");
        Etiqueta e2 = new Etiqueta("McSteamy","Relacionado con Mark Sloan");
        Etiqueta e3 = new Etiqueta("Interns","Relacionado con l@s intern@s");
        Etiqueta[] e = {e1,e2,e3};
        stack.addEtiqueta(e1);
        stack.addEtiqueta(e2);
        stack.addEtiqueta(e3);

        Pregunta p1 = new Pregunta(1,e,"Episodio 1","¿En que casa esta Derek en el primer episodio?",Calendar.getInstance().getTime(),u2);
        Pregunta p2 = new Pregunta(2,e,"General","¿De quien es hija Meredith?",Calendar.getInstance().getTime(),u2);
        Pregunta p3 = new Pregunta(3,e,"Pregunta Episodio 1","¿Como se llaman las hermanas biológicas de Meredith?",Calendar.getInstance().getTime(),u1);
        stack.addPregunta(p1);
        stack.addPregunta(p2);
        stack.addPregunta(p3);

        Respuesta r1 = new Respuesta(1,1,u3,"En casa de Meredith",Calendar.getInstance().getTime());
        Respuesta r2 = new Respuesta(1,2,u3,"De Ellis Grey y Thatcher Grey",Calendar.getInstance().getTime());
        Respuesta r3 = new Respuesta(1,3,u3,"Lexi",Calendar.getInstance().getTime());
        Respuesta r4 = new Respuesta(2,3,u2,"Maggie",Calendar.getInstance().getTime());
        Respuesta[] r = {r1};
        Respuesta[] rr = {r2};
        Respuesta[] rrr = {r3,r4};
        p1.setRespuestas(r);
        p2.setRespuestas(rr);
        p3.setRespuestas(rrr);
        stack.addRespuesta(r1);
        stack.addRespuesta(r2);
        stack.addRespuesta(r3);
        stack.addRespuesta(r4);

    }


 */
   /*
    Método que muestra el menu y retorna la elección del usuario
    Dom: Stack
    Rec: Int
    */
/*
    public static int menu(Stack stack){
        int e;
        Scanner scan = new Scanner(System.in);
        if (stack.getActivo().getName().equals("")){
            e = 0;
        }else{
            System.out.println("### SISTEMA DE PREGUNTAS Y RESPUESTAS ###");
            System.out.println("## Registrado como: "+ stack.getActivo().getName() +" ##");
            System.out.println("## Reputación: "+ String.valueOf(stack.getActivo().getReputacion()));
            System.out.println("Escoja su opción:");
            System.out.println("1. Agregar nueva pregunta\n" +
                    "2. Responder pregunta\n" +
                    "3. Dar recompensa\n" +
                    "4. Aceptar respuesta\n" +
                    "5. Votar\n" +
                    "6. Cerrar sesión\n" +
                    "7. Salir del programa");
            System.out.println("INTRODUZCA SU OPCIÓN: ");
            e = scan.nextInt();
            scan.nextLine();
        }
        return e;
    }

}
*/