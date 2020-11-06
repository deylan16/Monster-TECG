package clases;

import interfaz.proyecto.ImagenFondo;
import interfaz.proyecto.Ventana;

public class Jugadores {
    private static Jugadores Usuario = null;
    private static Jugadores Enemigo = null;
    private int vida = 1000,mana = 1000;
    public ListaCircular<Cartas> Mano = new ListaCircular<Cartas>();
    public Pila<Cartas> deck = new Pila<Cartas>();
    public static ListaDoble<String> Movimientos = new ListaDoble<String>();


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
    public void setVida(int vida){
        this.vida = vida;
    }
    public void setMana(int mana){
        this.mana = mana;
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
    public void creaManoe(){
        Cartas.crealistas();
        Mano.add(Cartas.cartaCurita);
    }
    public void creaMano(){
        Movimientos.add("Movimientos");
        Cartas.crealistas();
        Mano.add(Cartas.cartaInstaKill);
        Mano.add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        Mano.add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        Mano.add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        Mano.add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));
        deck.Add(Cartas.Todas.getForIndex((int) (Math.random()*Cartas.Todas.getSize()+1)));




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
