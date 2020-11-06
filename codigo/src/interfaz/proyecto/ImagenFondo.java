package interfaz.proyecto;
import clases.Cartas;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
//clase para crear un jpanel con una imagen de fondo
public class ImagenFondo extends JPanel{
    private Image fondo=null;
    public String imagen;
    boolean voltear;
    Cartas carta = null;
    public ImagenFondo(String imagen, boolean voltear){
        this.imagen = imagen;
        this.voltear = voltear;

    }
    //metodo para cambiar la carta que se le asigno a un jpanel
    public void setCarta(Cartas carta){
        this.carta = carta;
    }
    //metodo para obtener la carta que se le asigno a un jpanel
    public Cartas getCarta(){
        return carta;
    }

    @Override
    public void paint(Graphics g){
        fondo=new ImageIcon(getClass().getResource(this.imagen)).getImage();
        if (this.voltear == true){
            g.drawImage(fondo,0+getWidth() ,0,-getWidth(),getHeight(),this);

        }
        else {
            g.drawImage(fondo,0 ,0,getWidth(),getHeight(),this);

        }
        setOpaque(false);
        super.paint(g);
    }
}