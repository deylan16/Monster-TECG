package clases;

import interfaz.proyecto.ImagenFondo;
import interfaz.proyecto.Ventana;

public class Jugadores {
    private static Jugadores Usuario = null;
    private static Jugadores Enemigo = null;
    private int vida = 1000,mana = 1000;
    public ListaCircular<Cartas> Mano = new ListaCircular<Cartas>();


    private Jugadores() {

    }


    public  static Jugadores getInstance(String quien) {
        if (quien == "Usuario"){
            if (Usuario == null){
                Usuario = new Jugadores();
            }

            return Usuario;
        }
        else{if (Enemigo == null){
            Enemigo = new Jugadores();
        }

            return Enemigo;

        }
    }
    public void agregaVacio(){
        Mano.add(Cartas.cartaVacia);}
    public int getVida(){
        return this.vida;
    }
    public int getMana(){
        return this.mana;
    }
    public void daño(int daño){
        this.vida -= daño;
    }
    public void gasto(int gasto){
        this.mana -= gasto;
    }
    public void creaMano(){
        Mano.add(Cartas.carta15);
        Mano.add(Cartas.carta7);
        Mano.add(Cartas.carta20);
        Mano.add(Cartas.carta9);
        Mano.add(Cartas.carta10);
        Mano.add(Cartas.carta11);
        Mano.add(Cartas.carta2);
        Mano.add(Cartas.carta3);
        Mano.add(Cartas.carta4);
        Mano.add(Cartas.carta5);
        Mano.add(Cartas.cartaVacia);

        actualizamano(0);
    }
    public void actualizamano(int empieza) {
        int NumeroCarta = 0;
        int i;
        for (i = empieza;i <= Mano.getSize() && NumeroCarta <= 4; i++) {
            if (i >= Mano.getSize()) {
                i = -1;
            } else {
                if (NumeroCarta == 0) {
                    NumeroCarta += 1;
                    ImagenFondo ventanai = Ventana.getInstance().ImagenCarta1;
                    ventanai.imagen = Mano.getForIndex(i).getImagen();
                    ventanai.setCarta(Mano.getForIndex(i));
                    ventanai.repaint();
                } else {
                    if (NumeroCarta == 1) {
                        NumeroCarta += 1;
                        ImagenFondo ventanai = Ventana.getInstance().ImagenCarta2;
                        ventanai.imagen = Mano.getForIndex(i).getImagen();
                        ventanai.setCarta(Mano.getForIndex(i));
                        ventanai.repaint();
                    } else {
                        if (NumeroCarta == 2) {
                            NumeroCarta += 1;
                            ImagenFondo ventanai = Ventana.getInstance().ImagenCarta3;
                            ventanai.imagen = Mano.getForIndex(i).getImagen();
                            ventanai.setCarta(Mano.getForIndex(i));
                            ventanai.repaint();
                        } else {
                            if (NumeroCarta == 3) {
                                NumeroCarta += 1;
                                ImagenFondo ventanai = Ventana.getInstance().ImagenCarta4;
                                ventanai.imagen = Mano.getForIndex(i).getImagen();
                                ventanai.setCarta(Mano.getForIndex(i));
                                ventanai.repaint();
                            } else {
                                NumeroCarta += 1;
                            }
                        }
                    }
                }
            }
            }
        Ventana ventanab = Ventana.getInstance();
        if( ventanab.ImagenCarta4.getCarta().getImagen() == "imagenes/vacio.jpg"){
            ventanab.Carta4.setEnabled(false);
        }else{
            ventanab.Carta4.setEnabled(true);
        }
        if( ventanab.ImagenCarta3.getCarta().getImagen() == "imagenes/vacio.jpg"){
            ventanab.Carta3.setEnabled(false);
        }else{
            ventanab.Carta3.setEnabled(true);
        }if( ventanab.ImagenCarta2.getCarta().getImagen() == "imagenes/vacio.jpg"){
            ventanab.Carta2.setEnabled(false);
        }else{
            ventanab.Carta2.setEnabled(true);
        }
        if( ventanab.ImagenCarta1.getCarta().getImagen() == "imagenes/vacio.jpg"){
            ventanab.Carta1.setEnabled(false);
        }else{
            ventanab.Carta1.setEnabled(true);
        }
        }


}
