import clases.Jugadores;
import conexion.PanelConexion;
import interfaz.proyecto.Animacion;
import interfaz.proyecto.Ventana;

//Se utiliza para iniciar el programa y crear la instancias necesarias para ejecutar el juego y la logica del mismo.
public class Main {
    public Ventana inicia;
    
    public static void main(String[] args) throws Exception { 
        PanelConexion panel = new PanelConexion(); //Panel de conexion
        panel.setVisible(true);
        Ventana inicia = Ventana.getInstance(); //Se crea la ventana para cada jugador
        Animacion.CreaListas();
        inicia.abrirVentana();
        Jugadores Usuiario = Jugadores.getInstance("Usuario"); //Jugador base
        Usuiario.creaMano();
        Jugadores Enemigo = Jugadores.getInstance("Enemigo"); //El jugador que se conecta
        inicia.revisa();
        }
}
