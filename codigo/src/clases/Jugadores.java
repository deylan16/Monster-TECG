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
                    ImagenFondo ventana = Ventana.getInstance().ImagenCarta1;
                    ventana.imagen = Mano.getForIndex(i).getImagen();
                    ventana.setCarta(Mano.getForIndex(i));
                    ventana.repaint();
                } else {
                    if (NumeroCarta == 1) {
                        NumeroCarta += 1;
                        ImagenFondo ventana = Ventana.getInstance().ImagenCarta2;
                        ventana.imagen = Mano.getForIndex(i).getImagen();
                        ventana.setCarta(Mano.getForIndex(i));
                        ventana.repaint();
                    } else {
                        if (NumeroCarta == 2) {
                            NumeroCarta += 1;
                            ImagenFondo ventana = Ventana.getInstance().ImagenCarta3;
                            ventana.imagen = Mano.getForIndex(i).getImagen();
                            ventana.setCarta(Mano.getForIndex(i));
                            ventana.repaint();
                        } else {
                            if (NumeroCarta == 3) {
                                NumeroCarta += 1;
                                ImagenFondo ventana = Ventana.getInstance().ImagenCarta4;
                                ventana.imagen = Mano.getForIndex(i).getImagen();
                                ventana.setCarta(Mano.getForIndex(i));
                                ventana.repaint();
                            } else {
                                NumeroCarta += 1;
                            }
                        }
                    }
                }
            }
            }
        }


}
