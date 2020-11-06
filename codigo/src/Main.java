import clases.Jugadores;
import conexion.PanelConexion;
import interfaz.proyecto.Animacion;
import interfaz.proyecto.Ventana;

public class Main {
    public static void main(String[] args) throws Exception {
        Ventana inicia = Ventana.getInstance();
        Animacion.CreaListas();
        inicia.abrirVentana();
        Jugadores Usuiario = Jugadores.getInstance("Usuario");
        Usuiario.creaMano();
        Jugadores Enemigo = Jugadores.getInstance("Enemigo");
        Enemigo.creaManoe();
        inicia.revisa();
    }
    public static void main2(String[] args) throws Exception {
        PanelConexion conecc = new PanelConexion();
        conecc.setVisible(true);
        if (1==2) {
            Ventana inicia = Ventana.getInstance();
            Animacion.CreaListas();
            inicia.abrirVentana();
            Jugadores Usuiario = Jugadores.getInstance("Usuario");
            Usuiario.creaMano();
            Jugadores Enemigo = Jugadores.getInstance("Enemigo");
        }
    }
}
