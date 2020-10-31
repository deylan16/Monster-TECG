package clases;

public class Cartas {
    static Cartas carta1 = new Cartas(100,200,"imagenes/2.jpg");
    static Cartas carta2 = new Cartas(100,200,"imagenes/3.jpg");
    static Cartas carta3 = new Cartas(100,200,"imagenes/4.jpg");
    static Cartas carta4 = new Cartas(100,2000,"imagenes/5.jpg");
    static Cartas carta5 = new Cartas(100,200,"imagenes/6.jpg");
    private int daño;
    private int coste;
    private String imagen;



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



}
