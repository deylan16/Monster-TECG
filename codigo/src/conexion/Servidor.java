/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import clases.Analizar;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author johnn
 */
public class Servidor implements Runnable{
    private int PUERTO;
    private String IP;
    private DataInputStream IN;
    
    public Servidor() throws UnknownHostException {
       InetAddress address = InetAddress.getLocalHost();
       this.IP = address.getHostAddress();
    }
    public void SetPort(){
        int min = 10000;
        int max = 60000;
        int port = (int) (Math.random() * (max - min + 1) + min);
        this.PUERTO = port;
    }
    public int GetPort(){
        return PUERTO;
    }
    public String GetIp(){
        return IP;
    }
    
    @Override
    public void run() {
        try{
        ServerSocket servidor = new ServerSocket(PUERTO);
        Socket cliente;
        while(true){
            cliente = servidor.accept();
            IN = new DataInputStream(cliente.getInputStream());
            String mensaje = IN.readUTF();
            Analizar jugada = new Analizar(mensaje);
            cliente.close();
        }
        
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
