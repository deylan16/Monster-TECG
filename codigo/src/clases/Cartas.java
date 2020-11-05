package clases;

public class Cartas {
    static Cartas carta1 = new Cartas(25,75,"imagenes/Bolitas.png");
    static Cartas carta2 = new Cartas(25,75,"imagenes/Steve.png");
    static Cartas carta3 = new Cartas(50,100,"imagenes/Vegguetto.png");
    static Cartas carta4 = new Cartas(50,100,"imagenes/Narutito.png");
    static Cartas carta5 = new Cartas(75,125,"imagenes/Conejitox.png");
    static Cartas carta6 = new Cartas(75,125,"imagenes/Rositok.png");
    static Cartas carta7 = new Cartas(100,225,"imagenes/Mariotico.png");
    static Cartas carta8 = new Cartas(100,225,"imagenes/Luigitico.png");
    static Cartas carta9 = new Cartas(150,250,"imagenes/Sonidito.png");
    static Cartas carta10 = new Cartas(150,250,"imagenes/Colidito.png");
    static Cartas carta11 = new Cartas(175,250,"imagenes/Bananakill.png");
    static Cartas carta12 = new Cartas(175,250,"imagenes/Naranjadance.png");
    static Cartas carta13 = new Cartas(200,300,"imagenes/Espodradix.png");
    static Cartas carta14 = new Cartas(200,300,"imagenes/Cometrella.png");
    static Cartas carta15 = new Cartas(150,150,"imagenes/Gatilloro.png");
    static Cartas carta16 = new Cartas(150,150,"imagenes/Piocalin.png");
    static Cartas carta17 = new Cartas(250,400,"imagenes/Yelloblow.png");
    static Cartas carta18 = new Cartas(250,400,"imagenes/Huevonado.png");
    static Cartas carta19 = new Cartas(350,700,"imagenes/Phineas.png");
    static Cartas carta20 = new Cartas(350,700,"imagenes/Ferb.png");
    static Cartas cartaVacia = new Cartas(0,0,"imagenes/vacio.jpg");
    private int daño;
    private int coste;
    private String imagen;
    static ListaCircular<Cartas> Todas = new ListaCircular<Cartas>();


    public Cartas (int daño,int coste,String imagen){
        this.daño = daño;
        this.coste = coste;
        this.imagen = imagen;

    }
    public int getDaño(){
        return this.daño;
    }
    public int getCoste(){
        return this.coste;
    }
    public String getImagen(){
        return this.imagen;
    }
    static void crealistas(){
        Todas.add(carta1);
        Todas.add(carta2);
        Todas.add(carta3);
        Todas.add(carta4);
        Todas.add(carta5);
        Todas.add(carta6);
        Todas.add(carta7);
        Todas.add(carta8);
        Todas.add(carta9);
        Todas.add(carta10);
        Todas.add(carta11);
        Todas.add(carta12);
        Todas.add(carta13);
        Todas.add(carta14);
        Todas.add(carta15);
        Todas.add(carta16);
        Todas.add(carta17);
        Todas.add(carta18);
        Todas.add(carta19);
        Todas.add(carta20);
    }


    
}
