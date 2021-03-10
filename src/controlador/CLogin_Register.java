package controlador;

import modelo.*;
import vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CLogin_Register {

    public CLogin_Register(Stack stack, Login_Register vista) {

       vista.setVisible(true);

        vista.ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = vista.textUser.getText();
                String pass = vista.textPass.getText();
                boolean flag = stack.login(user,pass);

                if (flag){
                    vista.success.setText("Usuario y/o contraseña incorrectas");
                }else{
                    vista.dispose();
                    stack.login(user,pass);
                    Principal vPrincipal = new Principal();
                    new CPrincipal(stack,vPrincipal);
                }
            }
        });

        // si los campos de texto están rellenos y el nombre de usuario no ha sido ocupado, permite
        // el registro de un nuevo usuario
        vista.registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = vista.textUser.getText();
                String pass = vista.textPass.getText();
                boolean flag = stack.register(user,pass);

                if (flag){
                    vista.dispose();
                    stack.login(user,pass);
                    Principal vPrincipal = new Principal();
                    new CPrincipal(stack,vPrincipal);
                }else{
                    vista.success.setText("Usuario ya registrado, elija otro nombre de usuario");
                }
            }
        });

        // permite volver a la página principal sin iniciar o registrar sesión
        vista.volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                Principal vPrincipal = new Principal();
                new CPrincipal(stack,vPrincipal);
            }
        });
    }
}
