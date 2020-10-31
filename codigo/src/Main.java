import clases.Jugadores;
import clases.ListaDoble;
import interfaz.proyecto.Animacion;
import interfaz.proyecto.Ventana;

public class Main {
    public static void main(String[] args) {
        Ventana inicia = Ventana.getInstance();
        Animacion.CreaListas();
        inicia.abrirVentana();
        Jugadores Usuiario = Jugadores.getInstance("Usuario");
        Usuiario.creaMano();
        Jugadores Enemigo = Jugadores.getInstance("Enemigo");
    }
}
