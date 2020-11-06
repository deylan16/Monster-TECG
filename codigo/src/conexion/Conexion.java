/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import clases.Jugadores;
import interfaz.proyecto.Animacion;
import interfaz.proyecto.Ventana;

/**
 *
 * @author johnn
 */
public final class Conexion {
    private static Cliente CjugadorH;
    private static String HIP;
    private static int HPU;
    private static Cliente CjugadorI;
    private static String IIP;
    private static int IPU;
    
    public Conexion() throws Exception {
        Ventana inicia = Ventana.getInstance();
        Animacion.CreaListas();
        inicia.abrirVentana();
        Jugadores Usuiario = Jugadores.getInstance("Usuario");
        Usuiario.creaMano();
        Jugadores Enemigo = Jugadores.getInstance("Enemigo");
    }

    /**
     *
     * @param ip
     * @param puerto
     */
    public static void setChost(String ip, int puerto){
        System.out.println("hola1");
        Conexion.HIP = ip;
        Conexion.HPU = puerto;
    }
    public static void Chost(){
        System.out.println("hola2");
        System.out.println(IIP + " PUTA " + IPU);
        CjugadorH = new Cliente(IIP, IPU);
        Thread tCH = new Thread(CjugadorH);
        tCH.start();
    }
    public void EChost(Object accion){
        //CjugadorH.envMen(accion);
    }
    /**
     *
     * @param ip
     * @param puerto
     */
    public static void setCinvi(String ip, int puerto){
        System.out.println("hola3");
        Conexion.IIP = ip;
        Conexion.IPU = puerto;
    }
    public static void Cinvi(){
        System.out.println("hola4");
        System.out.println(HIP + " PUTA " + HPU);
        CjugadorI = new Cliente(HIP, HPU);
        Thread tCI = new Thread(CjugadorI);
        tCI.start();
    }
}
