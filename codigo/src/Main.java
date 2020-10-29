import interfaz.proyecto.Animacion;
import interfaz.proyecto.Ventana;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Ventana inicia = Ventana.getInstance();
        Animacion.CreaListas();
        inicia.abrirVentana();



    }
}
