package interfaz.proyecto;


import clases.ListaDoble;
//clase para crear una animacion de las cartas lanzadas
public class Animacion {
    public static ListaDoble<String> AnimacionBasica = new ListaDoble<String>();
    //crea la lista con las imagenes de una animacion basica
    public static void CreaListas() {
        AnimacionBasica.add("imagenes/carta izquierda1.jpg");
        AnimacionBasica.add("imagenes/carta izquierda7.jpg");
        AnimacionBasica.add("imagenes/carta izquierda6.jpg");
        AnimacionBasica.add("imagenes/carta izquierda5.jpg");
        AnimacionBasica.add("imagenes/carta izquierda4.jpg");
        AnimacionBasica.add("imagenes/carta izquierda3.jpg");
        AnimacionBasica.add("imagenes/carta izquierda2.jpg");
        AnimacionBasica.add("imagenes/Revez carta.jpg");

    }
    //metodo para hacer cambiar la imagenes de un jpanel y crear la animacion de la carta
    public static void Anima(ImagenFondo carta) throws InterruptedException {
        int i;
        for (i = 0;i < AnimacionBasica.getSize();i++){
            carta.imagen = AnimacionBasica.getForIndex(i);
            Thread.sleep(150);
            carta.repaint();
        }

    }
}