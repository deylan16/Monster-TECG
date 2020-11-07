package clases;
//clase con las cartas del juego
public class Cartas {
    static Cartas carta1 = new Cartas(25,75,"imagenes/Bolitas.png","Bolitas","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta2 = new Cartas(25,75,"imagenes/Steve.png","Steve","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta3 = new Cartas(50,100,"imagenes/Vegguetto.png","Vegguetto","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta4 = new Cartas(50,100,"imagenes/Narutito.png","Narutito","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta5 = new Cartas(75,125,"imagenes/Conejitox.png","Conejitox","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta6 = new Cartas(75,125,"imagenes/Rositok.png","Rositok","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta7 = new Cartas(100,225,"imagenes/Mariotico.png","Mariotico","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta8 = new Cartas(100,225,"imagenes/Luigitico.png","Luigitico","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta9 = new Cartas(150,250,"imagenes/Sonidito.png","Sonidito","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta10 = new Cartas(150,250,"imagenes/Colidito.png","Colidito","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta11 = new Cartas(175,250,"imagenes/Bananakill.png","Bananakill","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta12 = new Cartas(175,250,"imagenes/Naranjadance.png","Naranjadance","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta13 = new Cartas(200,300,"imagenes/Espodradix.png","Espodradix","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta14 = new Cartas(200,300,"imagenes/Cometrella.png","Cometrella","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta15 = new Cartas(150,150,"imagenes/Gatilloro.png","Gatilloro","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta16 = new Cartas(150,150,"imagenes/Piocalin.png","Piocalin","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta17 = new Cartas(250,400,"imagenes/Yelloblow.png","Yelloblow","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta18 = new Cartas(250,400,"imagenes/Huevonado.png","Huevonado","Enemigo",0,"Usuario","Esbirro");
    static Cartas carta19 = new Cartas(350,700,"imagenes/Phineas.png","Phineas","Enemigo",0,"Usuario","Esbirro");
    public static Cartas carta20 = new Cartas(350,700,"imagenes/Ferb.png","Ferb","Enemigo",0,"Usuario","Esbirro");
    public static Cartas cartaVacia = new Cartas(0,0,"imagenes/vacio.jpg","vacio","Enemigo",0,"Usuario","Hechizo");
    static Cartas cartaFriito = new Cartas(0,200,"imagenes/Friito.png","Friito","Usuario",0,"Usuario","Hechizo");
    static Cartas cartaCurita = new Cartas(-50*(int) (Math.random()*20+1),300,"imagenes/Curita.png","Curita","Enemigo",0,"Usuario","Hechizo");
    public static Cartas cartaPoderzote = new Cartas(0,600,"imagenes/Poderzote.png","Poderzote","Enemigo",3,"Usuario","Hechizo");
    static Cartas cartaInstaKill = new Cartas(1000,700,"imagenes/InstaKill.png","InstaKill","Enemigo",0,"Usuario","Hechizo");
    static Cartas cartaManamita = new Cartas(0,-500,"imagenes/Manamita.png","Manamita","Enemigo",0,"Usuario","Hechizo");
    static Cartas cartaPodercito = new Cartas(0,400,"imagenes/Podercito.png","Podercito","Enemigo",2,"Usuario","Hechizo");
    static Cartas cartaRoboncito = new Cartas(0,400,"imagenes/Roboncito.png","Roboncito","Enemigo",0,"Usuario","Hechizo");
    public static Cartas cartaBlocito = new Cartas(0,0,"imagenes/Blocito.png","cartaBlocito","Enemigo",0,"bloqueo","Hechizo");
    static Cartas cartaManacito = new Cartas(0,500,"imagenes/Manacito.png","Manamita","Enemigo",0,"Enemigo","Hechizo");
    static Cartas cartaDragoncito = new Cartas(0,0,"imagenes/Dragoncito.png","Dragoncito","Enemigo",0,"Usuario","Hechizo");
    static Cartas cartaTimadito = new Cartas(0,0,"imagenes/Secreto.png","Timadito","Enemigo",0,"imagenes/Timadito.png","Secreto");
    static Cartas cartaInstaloser = new Cartas(1000,0,"imagenes/Secreto.png","InstaLoser","Enemigo",0,"imagenes/InstaLoser.png","Secreto");
    static Cartas cartaManaXXX = new Cartas(0,0,"imagenes/Secreto.png","ManaXXX","Enemigo",0,"imagenes/ManaXXX.png","Secreto");
    static Cartas cartaManaXX = new Cartas(0,0,"imagenes/Secreto.png","ManaXX","Enemigo",0,"imagenes/ManaXX.png","Secreto");
    static Cartas cartaManaEXXX = new Cartas(0,0,"imagenes/Secreto.png","ManaEXXX","Enemigo",0,"imagenes/ManaEXXX.png","Secreto");
    static Cartas cartaManaEXX = new Cartas(0,0,"imagenes/Secreto.png","ManaEXX","Enemigo",0,"imagenes/ManaEXX.png","Secreto");
    static Cartas cartaAdiosito = new Cartas(0,0,"imagenes/Secreto.png","Adiosito","Enemigo",0,"imagenes/Adiosito.png","Secreto");
    static Cartas cartaDesmotivacion = new Cartas(0,0,"imagenes/Secreto.png","Desmotivacion","Enemigo",0,"imagenes/Desmotivacion.png","Secreto");
    static Cartas cartaMotivacion = new Cartas(0,0,"imagenes/Secreto.png","Motivacion","Enemigo",0,"imagenes/Motivacion.png","Secreto");
    static Cartas cartaBorretux = new Cartas(0,0,"imagenes/Secreto.png","Borretux","Enemigo",0,"imagenes/Borretux.png","Secreto");
    private int daño;
    private int coste;
    private String imagen;
    private String nombre;
    private String siguiente;
    private String tipo;
    int Cuentapoderzote;
    String aQuienMana = null;
    static ListaCircular<Cartas> Todas = new ListaCircular<Cartas>();

    //metodo constructor para crear una instancia carta pero preguntando a quien baja mana
    public Cartas (int daño,int coste,String imagen,String nombre,String siguiente,int cuentapoderzote,String aQuienMana,String tipo){
        this.daño = daño;
        this.coste = coste;
        this.imagen = imagen;
        this.nombre = nombre;
        this.siguiente = siguiente;
        this.Cuentapoderzote = cuentapoderzote;
        this.aQuienMana = aQuienMana;
        this.tipo = tipo;

    }
    public int getDaño(){
        return this.daño;
    }
    public String getTipo(){
        return this.tipo;
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
    //crea una lista con todos los tipos de cartas
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
        Todas.add(cartaInstaKill);
        Todas.add(cartaManacito);
        Todas.add(cartaBlocito);
        Todas.add(cartaDragoncito);
        Todas.add(cartaTimadito);
        Todas.add(cartaInstaloser);
        Todas.add(cartaBorretux);
        Todas.add(cartaMotivacion);
        Todas.add(cartaManaXXX);
        Todas.add(cartaManaXX);
        Todas.add(cartaManaEXXX);
        Todas.add(cartaManaEXX);
        Todas.add(cartaAdiosito);
        Todas.add(cartaDesmotivacion);

    }
    //metodo para realizar la accion de la roboncito
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
