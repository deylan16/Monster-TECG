/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johnn
 */
public class Cliente implements Runnable{
    private int PUERTO;
    private String HOST;
    private String MENSAJE;
    private DataOutputStream OUT;

    public Cliente(int puerto, String host, String mensaje){
        this.PUERTO = puerto;
        this.HOST = host;
        this.MENSAJE = mensaje;
    }
    @Override
    public void run() {
        try {
            Socket cliente = new Socket(HOST, PUERTO);
            OUT = new DataOutputStream(cliente.getOutputStream());
            this.OUT.writeUTF(MENSAJE);
            cliente.close();
           
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
