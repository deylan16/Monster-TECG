/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johnn
 */
public class Servidor extends Observable implements Runnable{
    public int PUERTO;
    public String HOST;
    private Socket CLIENTE;
    private DataInputStream ENTRADA;
    private ServerSocket SERVIDOR;
    
    public void setIP() throws Exception{
        //Para obtener la IP de la computadora
        InetAddress address = InetAddress.getLocalHost();
        this.HOST = address.getHostAddress();
    }
    public void setPORT(){
        //Para generar un puerto aleatorio
        int min = 10000;
        int max = 60000;
        int port = (int) (Math.random() * (max - min + 1) + min);
        this.PUERTO = port;
    }
    public void recMen() throws IOException{  //Lee los mensajes que vienen en entrada y resetea para nueva entrada
        int Mensaje = ENTRADA.readInt();
        System.out.println(Mensaje);
        ENTRADA.reset();
    }
    public void closeC() throws IOException{
        CLIENTE.close();
        SERVIDOR.close();
        ENTRADA.close();
    }
    @Override
    public void run() {       
        try{
            this.SERVIDOR = new ServerSocket(PUERTO, 1, InetAddress.getLocalHost());
            PanelHost.print("Se creo un servidor \n");
            PanelHost.print(HOST + "  en el puerto: " + PUERTO + "\n");
            while(true){
                this.CLIENTE = SERVIDOR.accept();
                PanelHost.print("Servidor: Jugador conectado \n");
                ENTRADA = new DataInputStream(CLIENTE.getInputStream()); //Carril de entrada para los datos
            }        
        }
        catch (IOException ex) {
            System.out.println("Hubo un error, servidor");
        }
    }
    
    
}
