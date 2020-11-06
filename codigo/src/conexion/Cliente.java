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
    private int accion = 1;

    public Cliente(String host, int puerto) {
        this.PUERTO = puerto;
        this.HOST = host;
    }    
    public void envMen() throws IOException, Exception{
        if (accion == 1){
            accion = 2;
            PanelCliente.iniciar();
        }
        else{
         //SALIDA.writeInt(12);   
        }
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
            System.out.println("Cliente: Conectado");
            SALIDA = new DataOutputStream(CLIENTE.getOutputStream()); //Se crea carril de salida de datos
            this.envMen();
        }       
        catch (IOException ex) {
            System.out.println("Hubo un error, cliente");
        } catch (Exception ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
