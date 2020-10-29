package interfaz.proyecto;


import clases.ListaDoble;

public class Animacion {
    public static ListaDoble<String> Cartaizquierda = new ListaDoble<String>();
    public static void CreaListas() {
        Cartaizquierda.add("imagenes/carta izquierda1.jpg");
        Cartaizquierda.add("imagenes/carta izquierda7.jpg");
        Cartaizquierda.add("imagenes/carta izquierda6.jpg");
        Cartaizquierda.add("imagenes/carta izquierda5.jpg");
        Cartaizquierda.add("imagenes/carta izquierda4.jpg");
        Cartaizquierda.add("imagenes/carta izquierda3.jpg");
        Cartaizquierda.add("imagenes/carta izquierda2.jpg");
        Cartaizquierda.add("imagenes/Revez carta.jpg");

    }
    public static void Anima(ImagenFondo carta) throws InterruptedException {
        int i;
        for (i = 0;i < Cartaizquierda.getSize();i++){
            carta.imagen = Cartaizquierda.getForIndex(i);
            Thread.sleep(150);
            carta.repaint();
        }

    }
}