import clases.Jugadores;
import conexion.PanelConexion;
import interfaz.proyecto.Animacion;
import interfaz.proyecto.Ventana;

public class Main {
    public int iniciar;
    
    public static void main(String[] args) throws Exception {
        PanelConexion panel = new PanelConexion();
        panel.setVisible(true);
        Ventana inicia = Ventana.getInstance();
        Animacion.CreaListas();
        inicia.abrirVentana();
        Jugadores Usuiario = Jugadores.getInstance("Usuario");
        Usuiario.creaMano();
        Jugadores Enemigo = Jugadores.getInstance("Enemigo");
        inicia.revisa();
        }
}
