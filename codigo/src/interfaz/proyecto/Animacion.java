package interfaz.proyecto;


import clases.ListaDoble;
/**
 * <p>Esta clase se creo para realizar la animacion de lanzar una carta
 * </p>
 * @author Deylan
 * @author johnn
 */
public class Animacion {
    public static ListaDoble<String> AnimacionBasica = new ListaDoble<String>();

    /**
     * <p>crea la lista con las imagnes de la animacion basica</p>
     */
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

    /**
     * <p>recibe la carta a la cual se le va realizar una animacion basita de lanzar una carta</p>
     * @param carta
     * @throws InterruptedException
     */
    public static void Anima(ImagenFondo carta) throws InterruptedException {
        int i;
        for (i = 0;i < AnimacionBasica.getSize();i++){
            carta.imagen = AnimacionBasica.getForIndex(i);
            Thread.sleep(150);
            carta.repaint();
        }

    }
}