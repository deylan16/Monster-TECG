package interfaz.proyecto;

import clases.Cartas;
import clases.Jugadores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.lang.Thread.sleep;

public class Ventana extends JPanel {
    JPanel PanelLucha,panelSuperior,panelUsuario;
    public JButton Carta1,Carta2,Carta3,Carta4,Movimientos;
    public ImagenFondo CartaUsuario,vidausuario,manausuario,vidaenemigo,manaenemigo,ImagenCarta1,ImagenCarta2,ImagenCarta3,ImagenCarta4;
    private static Ventana Instancia = null;
    public int NumeroCartamuestra =0;
    public Boolean Movimientrosabierto = true;

    JFrame frame = new JFrame("Monster TECG");
    JFrame framemovimientos = new JFrame("Movimientos");

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
        CartaUsuario = new ImagenFondo("imagenes/Revez carta.jpg",false);
        PanelLucha.add(CartaUsuario,dimensiones(1, 1, 1, 1, 0.9, 1.0));
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
            panelUsuario = new JPanel();
            panelUsuario.setLayout(new GridBagLayout());
            ImagenFondo ImagenDeck = new ImagenFondo("imagenes/Revez carta.jpg",false);
            panelUsuario.add(ImagenDeck,dimensiones(0, 0, 1, 1, 1.0, 1.0));
            JButton deck = new JButton("Recoger");
            deck.addActionListener(ex -> {
                Jugadores Usuario = Jugadores.getInstance("Usuario");
                Cartas nueva = Usuario.deck.get();
                Usuario.Mano.add(nueva);
                if(Usuario.deck.isEmpty()){
                    deck.setEnabled(false);
                    ImagenDeck.imagen = "imagenes/vacio.jpg";
                    ImagenDeck.repaint();
                }
                if (Usuario.Mano.getSize() >= 10){
                    deck.setEnabled(false);
                }
                Usuario.actualizamano(NumeroCartamuestra);
            });
            panelUsuario.add(deck,dimensiones(0, 1, 1, 1, 1.0, 0.01));
            JButton rotarizquierda = new JButton("<");
            rotarizquierda.addActionListener(ex -> {
                new Thread(() -> {
                    NumeroCartamuestra -=1;
                    Jugadores Usuario = Jugadores.getInstance("Usuario");
                    if(NumeroCartamuestra <= 0){
                        NumeroCartamuestra = Usuario.Mano.getSize()-1;
                    }
                    Usuario.actualizamano(NumeroCartamuestra);
                }).start();
            });
            panelUsuario.add(rotarizquierda,dimensiones(1, 0, 1, 2, 1.0, 1.0));
            ImagenCarta1 = new ImagenFondo("imagenes/vacio.jpg",false);
            panelUsuario.add(ImagenCarta1,dimensiones(2, 0, 1, 1, 1.0, 1.0));
            Carta1 = new JButton("Usar");
            Carta1.addActionListener(ex -> {
                Jugadores Usuario = Jugadores.getInstance("Usuario");
                if (Usuario.getMana() >=  ImagenCarta1.getCarta().getCoste()) {
                    new Thread(() -> {
                        try {
                            Animacion.Anima(CartaUsuario);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        bajaVida(ImagenCarta1.getCarta().getDaño(), "Enemigo");
                        bajamana(ImagenCarta1.getCarta().getCoste(), "Usuario");
                        CartaUsuario.imagen = ImagenCarta1.getCarta().getImagen();
                        if (Usuario.getMana() > 750){
                            int a = 1000 -Usuario.getMana();
                            bajamana(-a,"Usuario");
                        }
                        else{
                            bajamana(-250,"Usuario");
                        }
                        Usuario.Mano.borrar(ImagenCarta1.getCarta());
                        Usuario.Mano.verifica();
                        if(NumeroCartamuestra >= Usuario.Mano.getSize()){
                            NumeroCartamuestra = 0;
                        }
                        Usuario.actualizamano(NumeroCartamuestra);
                        if (Usuario.Mano.getSize() < 10){
                            deck.setEnabled(true);
                        }
                        turno("Enemigo");
                    }).start();
                }else{
                    avisomana();
                }

            });
            panelUsuario.add(Carta1,dimensiones(2, 1, 1, 1, 1.0, 0.01));
            ImagenCarta2 = new ImagenFondo("imagenes/vacio.jpg",false);
            panelUsuario.add(ImagenCarta2,dimensiones(3, 0, 1, 1, 1.0, 1.0));
            Carta2 = new JButton("Usar");
            Carta2.addActionListener(ex -> {
                Jugadores Usuario = Jugadores.getInstance("Usuario");
                if (Usuario.getMana() >=  ImagenCarta2.getCarta().getCoste()) {
                    new Thread(() -> {
                        try {
                            Animacion.Anima(CartaUsuario);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        bajaVida(ImagenCarta2.getCarta().getDaño(),"Enemigo");
                        bajamana(ImagenCarta2.getCarta().getCoste(),"Usuario");
                        CartaUsuario.imagen = ImagenCarta2.getCarta().getImagen();
                        if (Usuario.getMana() > 750){
                            int a = 1000 -Usuario.getMana();
                            bajamana(-a,"Usuario");
                        }
                        else{
                            bajamana(-250,"Usuario");
                        }
                        Usuario.Mano.borrar(ImagenCarta2.getCarta());
                        Usuario.Mano.verifica();
                        if(NumeroCartamuestra >= Usuario.Mano.getSize()){
                            NumeroCartamuestra = 0;
                        }
                        Usuario.actualizamano(NumeroCartamuestra);
                        if (Usuario.Mano.getSize() < 10){
                            deck.setEnabled(true);
                        }
                        turno("Enemigo");
                    }).start();
                }else{
                    avisomana();
                }

            });
            panelUsuario.add(Carta2,dimensiones(3, 1, 1, 1, 1.0, 0.01));
            ImagenCarta3 = new ImagenFondo("imagenes/vacio.jpg",false);
            panelUsuario.add(ImagenCarta3,dimensiones(4, 0, 1, 1, 1.0, 1.0));
            Carta3 = new JButton("Usar");
            Carta3.addActionListener(ex -> {
                Jugadores Usuario = Jugadores.getInstance("Usuario");
                if (Usuario.getMana() >=  ImagenCarta3.getCarta().getCoste()) {
                    new Thread(() -> {
                        try {
                            Animacion.Anima(CartaUsuario);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        bajaVida(ImagenCarta3.getCarta().getDaño(),"Enemigo");
                        bajamana(ImagenCarta3.getCarta().getCoste(),"Usuario");
                        if (Usuario.getMana() > 750){
                            int a = 1000 -Usuario.getMana();
                            bajamana(-a,"Usuario");
                        }
                        else{
                            bajamana(-250,"Usuario");
                        }
                        CartaUsuario.imagen = ImagenCarta3.getCarta().getImagen();
                        Usuario.Mano.borrar(ImagenCarta3.getCarta());
                        Usuario.Mano.verifica();
                        if(NumeroCartamuestra >= Usuario.Mano.getSize()){
                            NumeroCartamuestra = 0;
                        }
                        Usuario.actualizamano(NumeroCartamuestra);
                        if (Usuario.Mano.getSize() < 10){
                            deck.setEnabled(true);
                        }
                        turno("Enemigo");
                    }).start();
                }else{
                    avisomana();
                }

            });
            panelUsuario.add(Carta3,dimensiones(4, 1, 1, 1, 1.0, 0.01));
            ImagenCarta4 = new ImagenFondo("imagenes/vacio.jpg",false);
            panelUsuario.add(ImagenCarta4,dimensiones(5, 0, 1, 1, 1.0, 1.0));
            Carta4 = new JButton("Usar");
            Carta4.addActionListener(ex -> {
                Jugadores Usuario = Jugadores.getInstance("Usuario");
                if (Usuario.getMana() >=  ImagenCarta4.getCarta().getCoste()) {
                    new Thread(() -> {
                        try {
                            Animacion.Anima(CartaUsuario);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        bajaVida(ImagenCarta4.getCarta().getDaño(),"Enemigo");
                        bajamana(ImagenCarta4.getCarta().getCoste(),"Usuario");
                        CartaUsuario.imagen = ImagenCarta4.getCarta().getImagen();
                        if (Usuario.getMana() > 750){
                            int a = 1000 -Usuario.getMana();
                            bajamana(-a,"Usuario");
                            }
                        else{
                            bajamana(-250,"Usuario");
                        }
                        Usuario.Mano.borrar(ImagenCarta4.getCarta());
                        Usuario.Mano.verifica();
                        if(NumeroCartamuestra >= Usuario.Mano.getSize()){
                            NumeroCartamuestra = 0;
                        }
                        Usuario.actualizamano(NumeroCartamuestra);
                        if (Usuario.Mano.getSize() < 10){
                            deck.setEnabled(true);
                        }
                        turno("Enemigo");
                    }).start();
                }else{
                    avisomana();
                }
            });
            panelUsuario.add(Carta4,dimensiones(5, 1, 1, 1, 1.0, 0.01));
            JButton rotarderecha = new JButton(">");
            rotarderecha.addActionListener(ex -> {
                new Thread(() -> {
                    NumeroCartamuestra +=1;
                    Jugadores Usuario = Jugadores.getInstance("Usuario");
                    if(NumeroCartamuestra >= Usuario.Mano.getSize()){
                        NumeroCartamuestra = 0;
                    }

                    Usuario.actualizamano(NumeroCartamuestra);
                }).start();
            });
            panelUsuario.add(rotarderecha,dimensiones(6, 0, 1, 2, 1.0, 1.0));
            Movimientos = new JButton("Movimientos");
            Movimientos.addActionListener(ex -> {
                framemovimientos(Movimientrosabierto);
            });
            panelUsuario.add(Movimientos,dimensiones(7, 0, 1, 2, 1.0, 1.0));
            GridBagConstraints usuarioDimension = dimensiones(0, 1, 2, 2, 1.0, 0.3);
            frame.add(panelUsuario,usuarioDimension);
        }
    public void framemovimientos(boolean Movimientrosabierto){
        Movimientos.setEnabled(false);
        if (Movimientrosabierto) {
            framemovimientos.setLayout(new GridBagLayout());
            JButton Anterior = new JButton("Anterior");
            JButton Siguiente = new JButton("Siguiente");
            JTextArea Detalles = new JTextArea();
            Detalles.setEnabled(false);
            framemovimientos.add(Detalles, dimensiones(0, 1, 1, 1, 1.0, 1.0));
            framemovimientos.add(Anterior, dimensiones(0, 0, 1, 1, 1.0, 0.3));
            framemovimientos.add(Siguiente, dimensiones(0, 2, 1, 1, 1.0, 0.3));
            framemovimientos.setSize(200, 300);
        }
        this.Movimientrosabierto = false;
        framemovimientos.setVisible(true);
        framemovimientos.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        framemovimientos.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                framemovimientos.setVisible(false);
                Movimientos.setEnabled(true);
            }
        });
    }
    public void turno(String quien){
        if (quien == "Enemigo"){
            frame.remove(panelUsuario);
            frame.repaint();
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            turno("enemigo");
        }
        else{
            frame.add(panelUsuario);
            frame.repaint();
        }
    }
    public void PanelSuperior(){
        panelSuperior = new ImagenFondo("imagenes/fondo superior.jpg",false);
        panelSuperior.setLayout(new GridBagLayout());
        vidausuario = new ImagenFondo("imagenes/vida 1000.jpg",false);
        panelSuperior.add(vidausuario,dimensiones(0, 0, 1, 1, 0.9, 1.0));
        manausuario = new ImagenFondo("imagenes/mana 1000.jpg",false);
        panelSuperior.add(manausuario,dimensiones(0, 1, 1, 1, 0.9, 1.0));
        vidaenemigo = new ImagenFondo("imagenes/vida 1000.jpg",true);
        panelSuperior.add(vidaenemigo,dimensiones(2, 0, 1, 1, 0.9, 1.0));
        manaenemigo = new ImagenFondo("imagenes/mana 1000.jpg",true);
        panelSuperior.add(manaenemigo,dimensiones(2, 1, 1, 1, 0.9, 1.0));

        PanelLucha.add(panelSuperior,dimensiones(0, 0, 5, 1, 0.7, 0.8));
    }
    public void avisomana(){
        new Thread(() -> {
            Jugadores recibio = Jugadores.getInstance("Usuario");
            ImagenFondo interfaz = this.manausuario;
            if (recibio.getMana() <= 1000 && recibio.getMana() > 950) {
                interfaz.imagen = "imagenes/mana 1000 aviso.jpg";
                interfaz.repaint();
            } else {
                if (recibio.getMana() <= 950 && recibio.getMana() > 900) {
                    interfaz.imagen = "imagenes/mana 950 aviso.jpg";
                    interfaz.repaint();
                } else {
                    if (recibio.getMana() <= 900 && recibio.getMana() > 850) {
                        interfaz.imagen = "imagenes/mana 900 aviso.jpg";
                        interfaz.repaint();
                    } else {
                        if (recibio.getMana() <= 850 && recibio.getMana() > 800) {
                            interfaz.imagen = "imagenes/mana 850 aviso.jpg";
                            interfaz.repaint();
                        } else {
                            if (recibio.getMana() <= 800 && recibio.getMana() > 750) {
                                interfaz.imagen = "imagenes/mana 800 aviso.jpg";
                                interfaz.repaint();
                            } else {
                                if (recibio.getMana() <= 750 && recibio.getMana() > 700) {
                                    interfaz.imagen = "imagenes/mana 750 aviso.jpg";
                                    interfaz.repaint();
                                } else {
                                    if (recibio.getMana() <= 700 && recibio.getMana() > 650) {
                                        interfaz.imagen = "imagenes/mana 700 aviso.jpg";
                                        interfaz.repaint();
                                    } else {
                                        if (recibio.getMana() <= 650 && recibio.getMana() > 600) {
                                            interfaz.imagen = "imagenes/mana 650 aviso.jpg";
                                            interfaz.repaint();
                                        } else {
                                            if (recibio.getMana() <= 600 && recibio.getMana() > 550) {
                                                interfaz.imagen = "imagenes/mana 600 aviso.jpg";
                                                interfaz.repaint();
                                            } else {
                                                if (recibio.getMana() <= 550 && recibio.getMana() > 500) {
                                                    interfaz.imagen = "imagenes/mana 550 aviso.jpg";
                                                    interfaz.repaint();
                                                } else {
                                                    if (recibio.getMana() <= 500 && recibio.getMana() > 450) {
                                                        interfaz.imagen = "imagenes/mana 500 aviso.jpg";
                                                        interfaz.repaint();
                                                    } else {
                                                        if (recibio.getMana() <= 450 && recibio.getMana() > 400) {
                                                            interfaz.imagen = "imagenes/mana 450 aviso.jpg";
                                                            interfaz.repaint();
                                                        } else {
                                                            if (recibio.getMana() <= 400 && recibio.getMana() > 350) {
                                                                interfaz.imagen = "imagenes/mana 400 aviso.jpg";
                                                                interfaz.repaint();
                                                            } else {
                                                                if (recibio.getMana() <= 350 && recibio.getMana() > 300) {
                                                                    interfaz.imagen = "imagenes/mana 350 aviso.jpg";
                                                                    interfaz.repaint();
                                                                } else {
                                                                    if (recibio.getMana() <= 300 && recibio.getMana() > 250) {
                                                                        interfaz.imagen = "imagenes/mana 300 aviso.jpg";
                                                                        interfaz.repaint();
                                                                    } else {
                                                                        if (recibio.getMana() <= 250 && recibio.getMana() > 200) {
                                                                            interfaz.imagen = "imagenes/mana 250 aviso.jpg";
                                                                            interfaz.repaint();
                                                                        } else {
                                                                            if (recibio.getMana() <= 200 && recibio.getMana() > 150) {
                                                                                interfaz.imagen = "imagenes/mana 200 aviso.jpg";
                                                                                interfaz.repaint();
                                                                            } else {
                                                                                if (recibio.getMana() <= 150 && recibio.getMana() > 100) {
                                                                                    interfaz.imagen = "imagenes/mana 150 aviso.jpg";
                                                                                    interfaz.repaint();
                                                                                } else {
                                                                                    if (recibio.getMana() <= 100 && recibio.getMana() > 50) {
                                                                                        interfaz.imagen = "imagenes/mana 100 aviso.jpg";
                                                                                        interfaz.repaint();
                                                                                    } else {
                                                                                        if (recibio.getMana() <= 50 && recibio.getMana() > 0) {
                                                                                            interfaz.imagen = "imagenes/mana 40 aviso.jpg";
                                                                                            interfaz.repaint();
                                                                                        } else {
                                                                                            if (recibio.getMana() <= 0) {
                                                                                                interfaz.imagen = "imagenes/mana  aviso.jpg";
                                                                                                interfaz.repaint();
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
                                            }
                                        }
                                    }
                                }
                            }}}}
            }
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bajamana(0,"Usuario");
        }).start();
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
    public void bajaVida(int daño,String quien){
        Jugadores recibio;
        ImagenFondo interfaz;

        if (quien == "Enemigo"){
            recibio = Jugadores.getInstance("Enemigo");
            interfaz = this.vidaenemigo ;
        }
        else{
            recibio = Jugadores.getInstance("Usuario");
            interfaz = this.vidausuario ;
        }
        recibio.daño(daño);
        if (recibio.getVida() <= 900 && recibio.getVida() > 800) {
            interfaz.imagen = "imagenes/vida 900.jpg";
            interfaz.repaint();
        }
        else{
            if (recibio.getVida() <= 800 && recibio.getVida() > 700) {
                interfaz.imagen = "imagenes/vida 800.jpg";
                interfaz.repaint();
            }
            else {
                if (recibio.getVida() <= 700 && recibio.getVida() > 600) {
                    interfaz.imagen = "imagenes/vida 700.jpg";
                    interfaz.repaint();
                }
                else {
                    if (recibio.getVida() <= 600 && recibio.getVida() > 500) {
                        interfaz.imagen = "imagenes/vida 600.jpg";
                        interfaz.repaint();
                    }
                    else {
                        if (recibio.getVida() <= 500 && recibio.getVida() > 400) {
                            interfaz.imagen = "imagenes/vida 500.jpg";
                            interfaz.repaint();
                        }
                        else {
                            if (recibio.getVida() <= 400 && recibio.getVida() > 300) {
                                interfaz.imagen = "imagenes/vida 400.jpg";
                                interfaz.repaint();
                            }
                            else {
                                if (recibio.getVida() <= 300 && recibio.getVida() > 200) {
                                    interfaz.imagen = "imagenes/vida 300.jpg";
                                    interfaz.repaint();
                                }
                                else {
                                    if (recibio.getVida() <= 200 && recibio.getVida() > 100) {
                                        interfaz.imagen = "imagenes/vida 200.jpg";
                                        interfaz.repaint();
                                    }
                                    else {
                                        if (recibio.getVida()<= 100 && recibio.getVida() > 0) {
                                            interfaz.imagen = "imagenes/vida 100.jpg";
                                            interfaz.repaint();
                                        }
                                        else {
                                            if (recibio.getVida() <= 0) {
                                                interfaz.imagen = "imagenes/vida 0.jpg";
                                                interfaz.repaint();
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
    public void bajamana(int gasto,String quien) {
        Jugadores recibio;
        ImagenFondo interfaz;

        if (quien == "Enemigo") {
            recibio = Jugadores.getInstance("Enemigo");
            interfaz = this.manaenemigo;
        } else {
            recibio = Jugadores.getInstance("Usuario");
            interfaz = this.manausuario;
        }
        recibio.gasto(gasto);
        if (recibio.getMana() <= 1000 && recibio.getMana() > 950) {
            interfaz.imagen = "imagenes/mana 1000.jpg";
            interfaz.repaint();
        } else {
            if (recibio.getMana() <= 950 && recibio.getMana() > 900) {
                interfaz.imagen = "imagenes/mana 950.jpg";
                interfaz.repaint();
            } else {
                if (recibio.getMana() <= 900 && recibio.getMana() > 850) {
                    interfaz.imagen = "imagenes/mana 900.jpg";
                    interfaz.repaint();
                } else {
                    if (recibio.getMana() <= 850 && recibio.getMana() > 800) {
                        interfaz.imagen = "imagenes/mana 850.jpg";
                        interfaz.repaint();
                    } else {
                        if (recibio.getMana() <= 800 && recibio.getMana() > 750) {
                            interfaz.imagen = "imagenes/mana 800.jpg";
                            interfaz.repaint();
                        } else {
                            if (recibio.getMana() <= 750 && recibio.getMana() > 700) {
                                interfaz.imagen = "imagenes/mana 750.jpg";
                                interfaz.repaint();
                            } else {
                                if (recibio.getMana() <= 700 && recibio.getMana() > 650) {
                                    interfaz.imagen = "imagenes/mana 700.jpg";
                                    interfaz.repaint();
                                } else {
                                    if (recibio.getMana() <= 650 && recibio.getMana() > 600) {
                                        interfaz.imagen = "imagenes/mana 650.jpg";
                                        interfaz.repaint();
                                    } else {
                                    if (recibio.getMana() <= 600 && recibio.getMana() > 550) {
                                        interfaz.imagen = "imagenes/mana 600.jpg";
                                        interfaz.repaint();
                                    } else {
                                        if (recibio.getMana() <= 550 && recibio.getMana() > 500) {
                                            interfaz.imagen = "imagenes/mana 550.jpg";
                                            interfaz.repaint();
                                        } else {
                                        if (recibio.getMana() <= 500 && recibio.getMana() > 450) {
                                            interfaz.imagen = "imagenes/mana 500.jpg";
                                            interfaz.repaint();
                                        } else {
                                            if (recibio.getMana() <= 450 && recibio.getMana() > 400) {
                                                interfaz.imagen = "imagenes/mana 450.jpg";
                                                interfaz.repaint();
                                            } else {
                                            if (recibio.getMana() <= 400 && recibio.getMana() > 350) {
                                                interfaz.imagen = "imagenes/mana 400.jpg";
                                                interfaz.repaint();
                                            } else {
                                                if (recibio.getMana() <= 350 && recibio.getMana() > 300) {
                                                    interfaz.imagen = "imagenes/mana 350.jpg";
                                                    interfaz.repaint();
                                                } else {
                                                if (recibio.getMana() <= 300 && recibio.getMana() > 250) {
                                                    interfaz.imagen = "imagenes/mana 300.jpg";
                                                    interfaz.repaint();
                                                } else {
                                                    if (recibio.getMana() <= 250 && recibio.getMana() > 200) {
                                                        interfaz.imagen = "imagenes/mana 250.jpg";
                                                        interfaz.repaint();
                                                    } else {
                                                    if (recibio.getMana() <= 200 && recibio.getMana() > 150) {
                                                        interfaz.imagen = "imagenes/mana 200.jpg";
                                                        interfaz.repaint();
                                                    } else {
                                                        if (recibio.getMana() <= 150 && recibio.getMana() > 100) {
                                                            interfaz.imagen = "imagenes/mana 150.jpg";
                                                            interfaz.repaint();
                                                        } else {
                                                        if (recibio.getMana() <= 100 && recibio.getMana() > 50) {
                                                            interfaz.imagen = "imagenes/mana 100.jpg";
                                                            interfaz.repaint();
                                                        } else {
                                                            if (recibio.getMana() <= 50 && recibio.getMana() > 0) {
                                                                interfaz.imagen = "imagenes/mana 40.jpg";
                                                                interfaz.repaint();
                                                            } else {
                                                            if (recibio.getMana() <= 0) {
                                                                interfaz.imagen = "imagenes/mana 0.jpg";
                                                                interfaz.repaint();
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
            }
        }
    }
}
}}}}}}}
