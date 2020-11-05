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
public class Cliente implements Runnable {
    private final int PUERTO;
    private final String HOST;
    private Socket CLIENTE;
    private DataOutputStream SALIDA;

    public Cliente(String host, int puerto) {
        this.PUERTO = puerto;
        this.HOST = host;
    }    
    public void envMen() throws IOException{
        SALIDA.writeInt(12);
    }
    public void closeC() throws IOException{
        CLIENTE.close();
        SALIDA.close();
    }
    public String getIP(){
        String host = CLIENTE.getLocalAddress().toString();
        String host1 = host.substring(1);
        return host1;
    }
    @Override
    public void run() {
        System.out.println("Cliente: Conectando al servidor");
        try {
            this.CLIENTE = new Socket(HOST, PUERTO);
            System.out.println("Cliente: Conexion completa");
            SALIDA = new DataOutputStream(CLIENTE.getOutputStream()); //Se crea carril de salida de datos
        }       
        catch (IOException ex) {
            System.out.println("Hubo un error, cliente");
        }
    }
    
}
