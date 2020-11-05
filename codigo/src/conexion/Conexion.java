/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.net.InetAddress;
import javax.swing.JOptionPane;

/**
 *
 * @author johnn
 */
public final class Conexion {
    Thread tCI;
    Thread tCH;
    public boolean enlazado;
    private int puerto;
    private static String HOST;
    private final int seleccion = 2; // (1) para ser host
    
    public Conexion() throws Exception {
        if (seleccion == 1) {
        System.out.println("Vamos a iniciar una conexion");
        Servidor SjugadorH = new Servidor();
        SjugadorH.setIP();
        SjugadorH.setPORT();
        Thread tSH = new Thread(SjugadorH);
        tSH.start();   
        }
        
        else {
        System.out.println("Creando un cliente...");    
        String HOST = JOptionPane.showInputDialog(null, "Ingrese IP del Servidor");
        String PORT = JOptionPane.showInputDialog(null, "Ingrese el puerto a conectar");
        puerto = Integer.parseInt(PORT);
        Cliente CjugadorI = new Cliente(HOST, puerto);
        tCI = new Thread(CjugadorI);
        tCI.start();
        
        System.out.println("Creando un servidor a su ip...");
        Servidor SjugadorI = new Servidor();
        SjugadorI.setIP();
        SjugadorI.PUERTO = puerto + 1;
        Thread tSI = new Thread(SjugadorI);
        tSI.start();
        
        this.HOST = CjugadorI.getIP();
        CH();
        }
    }

    /**
     *
     * @param HOST
     */
    public void CH(){
        System.out.println("Creando el otro cliente");
        Cliente CjugadorH = new Cliente(HOST, puerto + 1);
        tCH = new Thread(CjugadorH);
        tCH.start();
    }
}
