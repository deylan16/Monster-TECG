package clases;

public class Cartas {
    static Cartas carta1 = new Cartas(25,75,"imagenes/Bolitas.png","Bolitas","Enemigo",0);
    static Cartas carta2 = new Cartas(25,75,"imagenes/Steve.png","Steve","Enemigo",0);
    static Cartas carta3 = new Cartas(50,100,"imagenes/Vegguetto.png","Vegguetto","Enemigo",0);
    static Cartas carta4 = new Cartas(50,100,"imagenes/Narutito.png","Narutito","Enemigo",0);
    static Cartas carta5 = new Cartas(75,125,"imagenes/Conejitox.png","Conejitox","Enemigo",0);
    static Cartas carta6 = new Cartas(75,125,"imagenes/Rositok.png","Rositok","Enemigo",0);
    static Cartas carta7 = new Cartas(100,225,"imagenes/Mariotico.png","Mariotico","Enemigo",0);
    static Cartas carta8 = new Cartas(100,225,"imagenes/Luigitico.png","Luigitico","Enemigo",0);
    static Cartas carta9 = new Cartas(150,250,"imagenes/Sonidito.png","Sonidito","Enemigo",0);
    static Cartas carta10 = new Cartas(150,250,"imagenes/Colidito.png","Colidito","Enemigo",0);
    static Cartas carta11 = new Cartas(175,250,"imagenes/Bananakill.png","Bananakill","Enemigo",0);
    static Cartas carta12 = new Cartas(175,250,"imagenes/Naranjadance.png","Naranjadance","Enemigo",0);
    static Cartas carta13 = new Cartas(200,300,"imagenes/Espodradix.png","Espodradix","Enemigo",0);
    static Cartas carta14 = new Cartas(200,300,"imagenes/Cometrella.png","Cometrella","Enemigo",0);
    static Cartas carta15 = new Cartas(150,150,"imagenes/Gatilloro.png","Gatilloro","Enemigo",0);
    static Cartas carta16 = new Cartas(150,150,"imagenes/Piocalin.png","Piocalin","Enemigo",0);
    static Cartas carta17 = new Cartas(250,400,"imagenes/Yelloblow.png","Yelloblow","Enemigo",0);
    static Cartas carta18 = new Cartas(250,400,"imagenes/Huevonado.png","Huevonado","Enemigo",0);
    static Cartas carta19 = new Cartas(350,700,"imagenes/Phineas.png","Phineas","Enemigo",0);
    public static Cartas carta20 = new Cartas(350,700,"imagenes/Ferb.png","Ferb","Enemigo",0);
    static Cartas cartaVacia = new Cartas(0,0,"imagenes/vacio.jpg","vacio","Enemigo",0);
    static Cartas cartaFriito = new Cartas(0,200,"imagenes/Friito.png","Friito","Usuario",0);
    static Cartas cartaCurita = new Cartas(-50*(int) (Math.random()*20+1),300,"imagenes/Curita.png","Curita","Enemigo",0);
    public static Cartas cartaPoderzote = new Cartas(0,600,"imagenes/Poderzote.png","Poderzote","Enemigo",3);
    static Cartas cartaInstaKill = new Cartas(1000,700,"imagenes/InstaKill.png","InstaKill","Enemigo",0);
    static Cartas cartaManamita = new Cartas(0,-500,"imagenes/Manamita.png","Manamita","Enemigo",0);
    static Cartas cartaPodercito = new Cartas(0,400,"imagenes/Podercito.png","Podercito","Enemigo",2);
    static Cartas cartaRoboncito = new Cartas(0,400,"imagenes/Roboncito.png","Roboncito","Enemigo",0);
    public static Cartas cartaBlocito = new Cartas(0,0,"imagenes/Blocito.png","cartaBlocito","Enemigo",0,"bloqueo");
    static Cartas cartaManacito = new Cartas(0,500,"imagenes/Manacito.png","Manamita","Enemigo",0,"Enemigo");
    static Cartas cartaDragoncito = new Cartas(0,0,"imagenes/Dragoncito.png","Dragoncito","Enemigo",0);
    private int daño;
    private int coste;
    private String imagen;
    private String nombre;
    private String siguiente;
    int Cuentapoderzote;
    String aQuienMana = null;
    static ListaCircular<Cartas> Todas = new ListaCircular<Cartas>();


    public Cartas (int daño,int coste,String imagen,String nombre,String siguiente,int cuentapoderzote){
        this.daño = daño;
        this.coste = coste;
        this.imagen = imagen;
        this.nombre = nombre;
        this.siguiente = siguiente;
        this.Cuentapoderzote = cuentapoderzote;

    }
    public Cartas (int daño,int coste,String imagen,String nombre,String siguiente,int cuentapoderzote,String aQuienMana){
        this.daño = daño;
        this.coste = coste;
        this.imagen = imagen;
        this.nombre = nombre;
        this.siguiente = siguiente;
        this.Cuentapoderzote = cuentapoderzote;
        this.aQuienMana = aQuienMana;

    }
    public int getDaño(){
        return this.daño;
    }
    public String getAQuienMana(){
        return this.aQuienMana;
    }
    public int getCoste(){
        return this.coste;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getSiguiente(){
        return this.siguiente;
    }
    public void setCuentapoderzote(int cuentapoderzote){
        this.Cuentapoderzote = cuentapoderzote;
    }
    public int getCuentapoderzote(){
        return this.Cuentapoderzote;
    }
    public void setSiguiente(String siguiente){
        this.siguiente = siguiente;
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
        Todas.add(cartaFriito);
        Todas.add(cartaCurita);
        Todas.add(cartaPoderzote);
        Todas.add(cartaManamita);
        Todas.add(cartaPodercito);
        Todas.add(cartaRoboncito);
        Todas.add(cartaInstaKill);
        Todas.add(cartaManacito);
        Todas.add(cartaBlocito);
        Todas.add(cartaDragoncito);

    }
    public static void accionRoboncito(){
        Jugadores Enemigo = Jugadores.getInstance("Enemigo");
        if(Enemigo.Mano.isEmpty() == false) {
            Jugadores Usuario = Jugadores.getInstance("Usuario");
            int cual = (int) (Math.random() * Enemigo.Mano.getSize() + 1);
            Enemigo.Mano.getForIndex(cual);
            Usuario.Mano.add(Enemigo.Mano.getForIndex(cual));
            Enemigo.Mano.borrar(Enemigo.Mano.getForIndex(cual));
        }
    }


    
}
