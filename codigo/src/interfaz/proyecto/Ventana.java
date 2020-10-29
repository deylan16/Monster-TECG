package interfaz.proyecto;


import javax.swing.*;
import java.awt.*;

public class Ventana extends JPanel {
    JPanel PanelLucha,panelSuperior;
    ImagenFondo CartaIzquierda,vidaizquierda,manaizquierda;
    int vida = 1000,mana = 1000;
    private static Ventana Instancia = null;

    JFrame frame = new JFrame("Monster TECG");

    private Ventana() {

    }
    

    public  static Ventana getInstance() {
        if (Instancia == null){
            Instancia = new Ventana();
        }

        return Instancia;
    }

    public void abrirVentana(){
        frame.setLayout(new GridBagLayout());
        panelLucha();
        panelUsuario();
        frame.setSize(900, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void panelLucha(){
        PanelLucha = new ImagenFondo("imagenes/campo de batalla.jpg",false);
        PanelLucha.setLayout(new GridBagLayout());
        //panel superior
        PanelSuperior();
        //Carta principal izquierda
        CartaIzquierda = new ImagenFondo("imagenes/Revez carta.jpg",false);
        PanelLucha.add(CartaIzquierda,dimensiones(1, 1, 1, 1, 0.9, 1.0));
        //esquina inferior izquierda
        PanelLucha.add(new ImagenFondo("imagenes/esquina inferior izquierda.jpg",false),dimensiones(0, 2, 1, 1, 0.7, 0.9));
        //centro
        PanelLucha.add(new ImagenFondo("imagenes/centro.jpg",false),dimensiones(2, 1, 1, 1, 2.0, 2.0));
        //Carta principal Derecha
        ImagenFondo CartaDerecha = new ImagenFondo("imagenes/Revez carta.jpg",false);
        PanelLucha.add(CartaDerecha,dimensiones(3, 1, 1, 1, 0.9, 1.0));
        //esquina inferior derecha
        PanelLucha.add(new ImagenFondo("imagenes/esquina inferior derecha.jpg",false),dimensiones(4, 2, 1, 1, 0.7, 1.7));
        GridBagConstraints luchaDimension = dimensiones(0, 0, 2, 1, 1.0, 1.0);
        frame.add(PanelLucha, luchaDimension);
    }
        public void panelUsuario(){
            JPanel panelUsuario = new JPanel();
            panelUsuario.setLayout(new GridBagLayout());
            JLabel LabelVida = new JLabel("Vida:");
            LabelVida.setFont(new Font("Arial", Font.PLAIN, 30));
            GridBagConstraints LabelVidaDimension = dimensiones(0, 0, 1, 1, 0.0, 0.01);
            panelUsuario.add(LabelVida,LabelVidaDimension);
            JLabel LabelMana = new JLabel("Mana:");
            LabelMana.setFont(new Font("Arial", Font.PLAIN, 30));
            GridBagConstraints LabelManaDimension = dimensiones(0, 1, 1, 1, 0.0, 0.0);
            panelUsuario.add(LabelMana,LabelManaDimension);
            JButton btenviar2 = new JButton("Enviar");
            btenviar2.addActionListener(ex -> {
                new Thread(() -> {
                    try {
                        Animacion.Anima(CartaIzquierda);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bajaVida(100);
                    bajamana(100);
                }).start();

            });
            panelUsuario.add(btenviar2);
            GridBagConstraints usuarioDimension = dimensiones(0, 1, 2, 1, 1.0, 0.1);
            frame.add(panelUsuario,usuarioDimension);
        }
    public void PanelSuperior(){
        panelSuperior = new ImagenFondo("imagenes/fondo superior.jpg",false);
        panelSuperior.setLayout(new GridBagLayout());
        vidaizquierda = new ImagenFondo("imagenes/vida 1000.jpg",false);
        panelSuperior.add(vidaizquierda,dimensiones(0, 0, 1, 1, 0.9, 1.0));
        manaizquierda = new ImagenFondo("imagenes/mana 1000.jpg",false);
        panelSuperior.add(manaizquierda,dimensiones(0, 1, 1, 1, 0.9, 1.0));
        ImagenFondo vidaderecha = new ImagenFondo("imagenes/vida 1000.jpg",true);
        panelSuperior.add(vidaderecha,dimensiones(2, 0, 1, 1, 0.9, 1.0));
        ImagenFondo manaderecha = new ImagenFondo("imagenes/mana 1000.jpg",true);
        panelSuperior.add(manaderecha,dimensiones(2, 1, 1, 1, 0.9, 1.0));

        PanelLucha.add(panelSuperior,dimensiones(0, 0, 5, 1, 0.7, 0.8));
    }

    public GridBagConstraints dimensiones(int empiezaC, int empiezaF, int ocupaC, int ocupaF,Double estirax,Double estiray){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = empiezaC; // El área de texto empieza en la columna cero.
        constraints.gridy = empiezaF; // El área de texto empieza en la fila cero
        constraints.gridwidth = ocupaC; // El área de texto ocupa dos columnas.
        constraints.gridheight = ocupaF; // El área de texto ocupa 2 filas.
        constraints.weighty = estiray; // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.weightx = estirax; // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.fill = GridBagConstraints.BOTH;
        return constraints;
    }
    public void bajaVida(int daño){
        this.vida -= daño;
        if (this.vida <= 900 && this.vida > 800) {
            vidaizquierda.imagen = "imagenes/vida 900.jpg";
            vidaizquierda.repaint();
        }
        else{
            if (this.vida <= 800 && this.vida > 700) {
                vidaizquierda.imagen = "imagenes/vida 800.jpg";
                vidaizquierda.repaint();
            }
            else {
                if (this.vida <= 700 && this.vida > 600) {
                    vidaizquierda.imagen = "imagenes/vida 700.jpg";
                    vidaizquierda.repaint();
                }
                else {
                    if (this.vida <= 600 && this.vida > 500) {
                        vidaizquierda.imagen = "imagenes/vida 600.jpg";
                        vidaizquierda.repaint();
                    }
                    else {
                        if (this.vida <= 500 && this.vida > 400) {
                            vidaizquierda.imagen = "imagenes/vida 500.jpg";
                            vidaizquierda.repaint();
                        }
                        else {
                            if (this.vida <= 400 && this.vida > 300) {
                                vidaizquierda.imagen = "imagenes/vida 400.jpg";
                                vidaizquierda.repaint();
                            }
                            else {
                                if (this.vida <= 300 && this.vida > 200) {
                                    vidaizquierda.imagen = "imagenes/vida 300.jpg";
                                    vidaizquierda.repaint();
                                }
                                else {
                                    if (this.vida <= 200 && this.vida > 100) {
                                        vidaizquierda.imagen = "imagenes/vida 200.jpg";
                                        vidaizquierda.repaint();
                                    }
                                    else {
                                        if (this.vida <= 100 && this.vida > 0) {
                                            vidaizquierda.imagen = "imagenes/vida 100.jpg";
                                            vidaizquierda.repaint();
                                        }
                                        else {
                                            if (this.vida <= 0) {
                                                vidaizquierda.imagen = "imagenes/vida 0.jpg";
                                                vidaizquierda.repaint();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void bajamana(int gasto){
        this.mana-= gasto;
        if (this.mana <= 900 && this.mana > 800) {
            manaizquierda.imagen = "imagenes/mana 900.jpg";
            manaizquierda.repaint();
        }
        else{
            if (this.mana <= 800 && this.mana > 700) {
                manaizquierda.imagen = "imagenes/mana 800.jpg";
                manaizquierda.repaint();
            }
            else {
                if (this.mana <= 700 && this.mana > 600) {
                    manaizquierda.imagen = "imagenes/mana 700.jpg";
                    manaizquierda.repaint();
                }
                else {
                    if (this.mana <= 600 && this.mana > 500) {
                        manaizquierda.imagen = "imagenes/mana 600.jpg";
                        manaizquierda.repaint();
                    }
                    else {
                        if (this.mana <= 500 && this.vida > 400) {
                            manaizquierda.imagen = "imagenes/mana 500.jpg";
                            manaizquierda.repaint();
                        }
                        else {
                            if (this.mana <= 400 && this.mana > 300) {
                                manaizquierda.imagen = "imagenes/mana 400.jpg";
                                manaizquierda.repaint();
                            }
                            else {
                                if (this.mana <= 300 && this.mana > 200) {
                                    manaizquierda.imagen = "imagenes/mana 300.jpg";
                                    manaizquierda.repaint();
                                }
                                else {
                                    if (this.mana <= 200 && this.mana > 100) {
                                        manaizquierda.imagen = "imagenes/mana 200.jpg";
                                        manaizquierda.repaint();
                                    }
                                    else {
                                        if (this.mana <= 100 && this.mana > 0) {
                                            manaizquierda.imagen = "imagenes/mana 100.jpg";
                                            manaizquierda.repaint();
                                        }
                                        else {
                                            if (this.mana <= 0) {
                                                manaizquierda.imagen = "imagenes/mana 0.jpg";
                                                manaizquierda.repaint();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
