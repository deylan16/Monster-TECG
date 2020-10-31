package interfaz.proyecto;
import clases.Cartas;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagenFondo extends JPanel{
    private Image fondo=null;
    public String imagen;
    boolean voltear;
    Cartas carta = null;
    public ImagenFondo(String imagen, boolean voltear){
        this.imagen = imagen;
        this.voltear = voltear;

    }
    public void setCarta(Cartas carta){
        this.carta = carta;
    }
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