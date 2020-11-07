package interfaz.proyecto;

import clases.Cartas;
import clases.Jugadores;
import clases.ListaCircular;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import conexion.PanelCliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ventana extends JPanel {
    ImagenFondo PanelLucha,panelSuperior;
    JPanel panelUsuario;
    public JButton Carta1,Carta2,Carta3,Carta4,Movimientos,paso;
    public ImagenFondo CartaUsuario,vidausuario,manausuario,vidaenemigo,manaenemigo,ImagenCarta1,ImagenCarta2,ImagenCarta3,ImagenCarta4,CartaDerecha;
    private static Ventana Instancia = null;
    public int NumeroCartamuestra = 0,indicemovimiento = 0;
    public Boolean Movimientrosabierto = true,Bloqueo = false;
    public int cuenta = 0,cuentaE = 0;
    JFrame frame = new JFrame("Monster TECG");
    JFrame framemovimientos = new JFrame("Movimientos");

    private Ventana() {
    }
    
    //Singleton
    public  static Ventana getInstance() {
        if (Instancia == null){
            Instancia = new Ventana();
        }

        return Instancia;
    }
    //inicia el frame donde estara toda la interfaz
    public void abrirVentana(){
        ListaCircular.ListaVacia.add(Cartas.cartaVacia);
        ListaCircular.ListaVacia.add(Cartas.cartaVacia);
        ListaCircular.ListaVacia.add(Cartas.cartaVacia);
        ListaCircular.ListaVacia.add(Cartas.cartaVacia);
        frame.setLayout(new GridBagLayout());
        panelLucha();
        panelUsuario();
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void ventanavisible(boolean turno){

        frame.setVisible(true);
        new Thread(() -> {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (turno){
                turno("Enemigo");
            }
            }).start();
    }
    //metodo que pone panel donde estaran las cartas que se juegan y la vida de cada jugador
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
        CartaDerecha = new ImagenFondo("imagenes/Revez carta.jpg",false);
        PanelLucha.add(CartaDerecha,dimensiones(3, 1, 1, 1, 0.9, 1.0));
        //esquina inferior derecha
        PanelLucha.add(new ImagenFondo("imagenes/esquina inferior derecha.jpg",false),dimensiones(4, 2, 1, 1, 0.7, 1.7));
        GridBagConstraints luchaDimension = dimensiones(0, 0, 2, 1, 1.0, 1.0);
        frame.add(PanelLucha, luchaDimension);
    }
    //metodo que pone panel donde el usuario trandra sus cartas, el deck, el boton de pasar y el de movimientos
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
                try {

                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode json = mapper.valueToTree(ImagenCarta1.getCarta());
                    String mensaje = mapper.writeValueAsString(json);
                    PanelCliente.SetMensaje(mensaje);
                } catch (JsonProcessingException ex1) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Carta4.setEnabled(false);
                Carta3.setEnabled(false);
                Carta2.setEnabled(false);
                Carta1.setEnabled(false);
                Jugadores Usuario = Jugadores.getInstance("Usuario");
                Jugadores Enemigo1 = Jugadores.getInstance("Enemigo");
                Jugadores.Movimientos.add("Hecho por: Usuario" + "\n" + "Nombre:" + ImagenCarta1.getCarta().getNombre() + "\n" + "Daño:" + ImagenCarta1.getCarta().getCoste());
                if (ImagenCarta1.getCarta().getTipo() == "Secreto"){
                    new Thread(() -> {
                        try {
                            Animacion.Anima(CartaUsuario);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        CartaUsuario.imagen = ImagenCarta1.getCarta().getAQuienMana();
                        if(ImagenCarta1.getCarta().getNombre() == "InstaLoser"){
                            bajaVida(1000,"Usuario");
                        }
                        if(ImagenCarta1.getCarta().getNombre() == "Borretux"){
                            Usuario.Mano = ListaCircular.Borra();
                            Usuario.actualizamano(0);
                            turno("Enemigo");

                        }
                        if(ImagenCarta1.getCarta().getNombre() == "Borrtex"){ ;
                        }
                        if(ImagenCarta1.getCarta().getNombre() == "ManaXXX"){ ;
                            Usuario.setMana(0);
                            bajamana(0,"Usuario");
                        }
                        if(ImagenCarta1.getCarta().getNombre() == "ManaXX"){ ;
                            Usuario.setMana(500);
                            bajamana(0,"Usuario");
                        }
                        if(ImagenCarta1.getCarta().getNombre() == "ManaEXXX"){ ;
                            Enemigo1.setMana(0);
                            bajamana(0,"Enemigo");
                        }
                        if(ImagenCarta1.getCarta().getNombre() == "ManaEXX"){ ;
                            Enemigo1.setMana(500);
                            bajamana(0,"Enemigo");
                        }
                        if(ImagenCarta1.getCarta().getNombre() == "Adiosito"){ ;
                            Usuario.Mano.borrar(Usuario.Mano.getForIndex( Usuario.Mano.getSize()));
                            Usuario.Mano.verifica();
                            Usuario.actualizamano(NumeroCartamuestra);

                        }
                        Usuario.Mano.borrar(ImagenCarta1.getCarta());
                        Usuario.actualizamano(NumeroCartamuestra);
                        turno("Enemigo");
                    }).start();
                }else {
                    if (Usuario.getMana() >= ImagenCarta1.getCarta().getCoste()) {
                        new Thread(() -> {
                            try {
                                Animacion.Anima(CartaUsuario);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (ImagenCarta1.getCarta().getImagen() == "imagenes/Roboncito.png") {
                                Cartas.accionRoboncito();
                            }

                            if (ImagenCarta1.getCarta().getCuentapoderzote() != 0) {
                                CartaUsuario.imagen = ImagenCarta1.getCarta().getImagen();
                                this.cuenta += ImagenCarta1.getCarta().getCuentapoderzote();
                                turno(ImagenCarta1.getCarta().getSiguiente());
                            }
                            if (this.cuenta != 0) {
                                if (ImagenCarta1.getCarta().getNombre() == "Dragoncito") {
                                    Jugadores Enemigo = Jugadores.getInstance("Enemigo");
                                    bajaVida((int) (Enemigo.getVida() * 0.25), "Enemigo");
                                } else {
                                    bajaVida(ImagenCarta1.getCarta().getDaño(), "Enemigo");
                                }
                                CartaUsuario.imagen = ImagenCarta1.getCarta().getImagen();
                                turno("Usuario");
                                this.cuenta -= 1;
                            } else {
                                if (ImagenCarta1.getCarta().getNombre() == "Dragoncito") {
                                    Jugadores Enemigo = Jugadores.getInstance("Enemigo");
                                    bajaVida((int) (Enemigo.getVida() * 0.25), "Enemigo");
                                } else {
                                    bajaVida(ImagenCarta1.getCarta().getDaño(), "Enemigo");
                                }
                                if (ImagenCarta1.getCarta().getAQuienMana() == "Enemigo") {
                                    bajamana(ImagenCarta1.getCarta().getCoste(), "Enemigo");
                                } else {
                                    if (ImagenCarta1.getCarta().getAQuienMana() == "bloqueo") {
                                        this.Bloqueo = true;
                                    }
                                    bajamana(ImagenCarta1.getCarta().getCoste(), "Usuario");
                                }
                                CartaUsuario.imagen = ImagenCarta1.getCarta().getImagen();
                                if (Usuario.getMana() > 750) {
                                    int a = 1000 - Usuario.getMana();
                                    bajamana(-a, "Usuario");
                                } else {
                                    bajamana(-250, "Usuario");
                                }
                                turno(ImagenCarta1.getCarta().getSiguiente());
                            }
                            Usuario.Mano.borrar(ImagenCarta1.getCarta());
                            Usuario.Mano.verifica();
                            if (NumeroCartamuestra >= Usuario.Mano.getSize()) {
                                NumeroCartamuestra = 0;
                            }
                            Usuario.actualizamano(NumeroCartamuestra);
                            if (Usuario.Mano.getSize() < 10) {
                                deck.setEnabled(true);
                            }
                        }).start();
                    } else {
                        avisomana();
                        Carta4.setEnabled(true);
                        Carta3.setEnabled(true);
                        Carta2.setEnabled(true);
                        Carta1.setEnabled(true);
                    }
                }

            });
            panelUsuario.add(Carta1,dimensiones(2, 1, 1, 1, 1.0, 0.01));
            ImagenCarta2 = new ImagenFondo("imagenes/vacio.jpg",false);
            panelUsuario.add(ImagenCarta2,dimensiones(3, 0, 1, 1, 1.0, 1.0));
            Carta2 = new JButton("Usar");
            Carta2.addActionListener(ex -> {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode json = mapper.valueToTree(ImagenCarta2.getCarta());
                    String mensaje = mapper.writeValueAsString(json);
                    PanelCliente.SetMensaje(mensaje);
                } catch (JsonProcessingException ex1) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Carta4.setEnabled(false);
                Carta3.setEnabled(false);
                Carta2.setEnabled(false);
                Carta1.setEnabled(false);
                Jugadores Usuario = Jugadores.getInstance("Usuario");
                Jugadores Enemigo1 = Jugadores.getInstance("Enemigo");
                Jugadores.Movimientos.add("Hecho por: Usuario" + "\n" + "Nombre:" + ImagenCarta2.getCarta().getNombre() + "\n" + "Daño:" + ImagenCarta2.getCarta().getCoste());
                if (ImagenCarta2.getCarta().getTipo() == "Secreto"){
                    new Thread(() -> {
                        try {
                            Animacion.Anima(CartaUsuario);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        CartaUsuario.imagen = ImagenCarta2.getCarta().getAQuienMana();
                        if(ImagenCarta2.getCarta().getNombre() == "InstaLoser"){
                            bajaVida(1000,"Usuario");
                        }
                        if(ImagenCarta2.getCarta().getNombre() == "Borretux"){
                            Usuario.Mano = ListaCircular.Borra();
                            Usuario.actualizamano(0);
                            turno("enemigo");
                        }
                        if(ImagenCarta2.getCarta().getNombre() == "Borrtex"){ ;
                        }
                        if(ImagenCarta2.getCarta().getNombre() == "ManaXXX"){ ;
                            Usuario.setMana(0);
                            bajamana(0,"Usuario");
                        }
                        if(ImagenCarta2.getCarta().getNombre() == "ManaXX"){ ;
                            Usuario.setMana(500);
                            bajamana(0,"Usuario");
                        }
                        if(ImagenCarta2.getCarta().getNombre() == "ManaEXXX"){ ;
                            Enemigo1.setMana(0);
                            bajamana(0,"Enemigo");
                        }
                        if(ImagenCarta2.getCarta().getNombre() == "ManaEXX"){ ;
                            Enemigo1.setMana(500);
                            bajamana(0,"Enemigo");
                        }
                        if(ImagenCarta2.getCarta().getNombre() == "Adiosito"){ ;
                            Usuario.Mano.borrar(Usuario.Mano.getForIndex( Usuario.Mano.getSize()));
                            Usuario.Mano.verifica();
                            Usuario.actualizamano(NumeroCartamuestra);
                        }
                        Usuario.Mano.borrar(ImagenCarta2.getCarta());
                        Usuario.actualizamano(NumeroCartamuestra);
                        turno("Enemigo");
                    }).start();
                }else {
                if (Usuario.getMana() >=  ImagenCarta2.getCarta().getCoste()) {
                    new Thread(() -> {
                        try {
                            Animacion.Anima(CartaUsuario);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (ImagenCarta2.getCarta().getImagen() == "imagenes/Roboncito.png") {
                            Cartas.accionRoboncito();
                        }
                        if (ImagenCarta2.getCarta().getCuentapoderzote() != 0) {
                            CartaUsuario.imagen = ImagenCarta2.getCarta().getImagen();
                            this.cuenta += ImagenCarta2.getCarta().getCuentapoderzote();
                            turno(ImagenCarta2.getCarta().getSiguiente());
                        }
                        if (this.cuenta != 0) {
                            if (ImagenCarta2.getCarta().getNombre() == "Dragoncito") {
                                Jugadores Enemigo = Jugadores.getInstance("Enemigo");
                                bajaVida((int) (Enemigo.getVida() * 0.25), "Enemigo");
                            } else {
                                bajaVida(ImagenCarta2.getCarta().getDaño(), "Enemigo");
                            }
                            CartaUsuario.imagen = ImagenCarta2.getCarta().getImagen();
                            turno("Usuario");
                            this.cuenta -= 1;
                        } else {
                            if (ImagenCarta2.getCarta().getNombre() == "Dragoncito") {
                                Jugadores Enemigo = Jugadores.getInstance("Enemigo");
                                bajaVida((int) (Enemigo.getVida() * 0.25), "Enemigo");
                            } else {
                                bajaVida(ImagenCarta2.getCarta().getDaño(), "Enemigo");
                            }
                            if (ImagenCarta2.getCarta().getAQuienMana() == "Enemigo") {
                                bajamana(ImagenCarta2.getCarta().getCoste(), "Enemigo");
                            } else {
                                if (ImagenCarta2.getCarta().getAQuienMana() == "bloqueo") {
                                    this.Bloqueo = true;
                                }
                                bajamana(ImagenCarta2.getCarta().getCoste(), "Usuario");
                            }
                            CartaUsuario.imagen = ImagenCarta2.getCarta().getImagen();
                            if (Usuario.getMana() > 750) {
                                int a = 1000 - Usuario.getMana();
                                bajamana(-a, "Usuario");
                            } else {
                                bajamana(-250, "Usuario");
                            }
                            turno(ImagenCarta2.getCarta().getSiguiente());
                        }
                        Usuario.Mano.borrar(ImagenCarta2.getCarta());
                        Usuario.Mano.verifica();
                        if (NumeroCartamuestra >= Usuario.Mano.getSize()) {
                            NumeroCartamuestra = 0;
                        }
                        Usuario.actualizamano(NumeroCartamuestra);
                        if (Usuario.Mano.getSize() < 10) {
                            deck.setEnabled(true);
                        }

                    }).start();
                }else{
                    Carta4.setEnabled(true);
                    Carta3.setEnabled(true);
                    Carta2.setEnabled(true);
                    Carta1.setEnabled(true);
                    avisomana();
                }}

            });
            panelUsuario.add(Carta2,dimensiones(3, 1, 1, 1, 1.0, 0.01));
            ImagenCarta3 = new ImagenFondo("imagenes/vacio.jpg",false);
            panelUsuario.add(ImagenCarta3,dimensiones(4, 0, 1, 1, 1.0, 1.0));
            Carta3 = new JButton("Usar");
            Carta3.addActionListener(ex -> {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode json = mapper.valueToTree(ImagenCarta3.getCarta());
                    String mensaje = mapper.writeValueAsString(json);
                    PanelCliente.SetMensaje(mensaje);
                } catch (JsonProcessingException ex1) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Carta4.setEnabled(false);
                Carta3.setEnabled(false);
                Carta2.setEnabled(false);
                Carta1.setEnabled(false);
                Jugadores Usuario = Jugadores.getInstance("Usuario");
                Jugadores Enemigo1 = Jugadores.getInstance("Enemigo");
                Jugadores.Movimientos.add("Hecho por: Usuario" + "\n" + "Nombre:" + ImagenCarta3.getCarta().getNombre() + "\n" + "Daño:" + ImagenCarta3.getCarta().getCoste());
                if (ImagenCarta3.getCarta().getTipo() == "Secreto"){
                    new Thread(() -> {
                        try {
                            Animacion.Anima(CartaUsuario);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        CartaUsuario.imagen = ImagenCarta3.getCarta().getAQuienMana();
                        if(ImagenCarta3.getCarta().getNombre() == "InstaLoser"){
                            bajaVida(1000,"Usuario");
                        }
                        if(ImagenCarta3.getCarta().getNombre() == "Borretux"){
                            Usuario.Mano = ListaCircular.Borra();
                            Usuario.actualizamano(0);
                            turno("enemigo");
                        }
                        if(ImagenCarta3.getCarta().getNombre() == "Borrtex"){ ;
                        }
                        if(ImagenCarta3.getCarta().getNombre() == "ManaXXX"){ ;
                            Usuario.setMana(0);
                            bajamana(0,"Usuario");
                        }
                        if(ImagenCarta3.getCarta().getNombre() == "ManaXX"){ ;
                            Usuario.setMana(500);
                            bajamana(0,"Usuario");
                        }
                        if(ImagenCarta3.getCarta().getNombre() == "ManaEXXX"){ ;
                            Enemigo1.setMana(0);
                            bajamana(0,"Enemigo");
                        }
                        if(ImagenCarta3.getCarta().getNombre() == "ManaEXX"){ ;
                            Enemigo1.setMana(500);
                            bajamana(0,"Enemigo");
                        }
                        if(ImagenCarta3.getCarta().getNombre() == "Adiosito"){ ;
                            Usuario.Mano.borrar(Usuario.Mano.getForIndex( Usuario.Mano.getSize()));
                            Usuario.Mano.verifica();
                            Usuario.actualizamano(NumeroCartamuestra);
                        }
                        Usuario.Mano.borrar(ImagenCarta3.getCarta());
                        Usuario.actualizamano(NumeroCartamuestra);
                        turno("Enemigo");
                    }).start();
                }else {
                            if (Usuario.getMana() >= ImagenCarta3.getCarta().getCoste()) {
                                new Thread(() -> {
                                    try {
                                        Animacion.Anima(CartaUsuario);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                    Jugadores.Movimientos.add("Hecho por: Usuario" + "\n" + "Nombre:" + ImagenCarta3.getCarta().getNombre() + "\n" + "Daño:" + ImagenCarta3.getCarta().getCoste());
                                    if (ImagenCarta3.getCarta().getImagen() == "imagenes/Roboncito.png") {
                                        Cartas.accionRoboncito();
                                    }
                                    if (ImagenCarta3.getCarta().getCuentapoderzote() != 0) {
                                        CartaUsuario.imagen = ImagenCarta3.getCarta().getImagen();
                                        this.cuenta += ImagenCarta3.getCarta().getCuentapoderzote();
                                        turno(ImagenCarta3.getCarta().getSiguiente());
                                    }
                                    if (this.cuenta != 0) {
                                        if (ImagenCarta3.getCarta().getNombre() == "Dragoncito") {
                                            Jugadores Enemigo = Jugadores.getInstance("Enemigo");
                                            bajaVida((int) (Enemigo.getVida() * 0.25), "Enemigo");
                                        } else {
                                            bajaVida(ImagenCarta3.getCarta().getDaño(), "Enemigo");
                                        }
                                        CartaUsuario.imagen = ImagenCarta3.getCarta().getImagen();
                                        turno("Usuario");
                                        this.cuenta -= 1;
                                    } else {
                                        if (ImagenCarta3.getCarta().getNombre() == "Dragoncito") {
                                            Jugadores Enemigo = Jugadores.getInstance("Enemigo");
                                            bajaVida((int) (Enemigo.getVida() * 0.25), "Enemigo");
                                        } else {
                                            bajaVida(ImagenCarta3.getCarta().getDaño(), "Enemigo");
                                        }
                                        if (ImagenCarta3.getCarta().getAQuienMana() == "Enemigo") {
                                            bajamana(ImagenCarta3.getCarta().getCoste(), "Enemigo");
                                        } else {
                                            if (ImagenCarta3.getCarta().getAQuienMana() == "bloqueo") {
                                                this.Bloqueo = true;
                                            }
                                            bajamana(ImagenCarta3.getCarta().getCoste(), "Usuario");
                                        }
                                        CartaUsuario.imagen = ImagenCarta3.getCarta().getImagen();
                                        if (Usuario.getMana() > 750) {
                                            int a = 1000 - Usuario.getMana();
                                            bajamana(-a, "Usuario");
                                        } else {
                                            bajamana(-250, "Usuario");
                                        }
                                        turno(ImagenCarta3.getCarta().getSiguiente());
                                    }
                                    Usuario.Mano.borrar(ImagenCarta3.getCarta());
                                    Usuario.Mano.verifica();
                                    if (NumeroCartamuestra >= Usuario.Mano.getSize()) {
                                        NumeroCartamuestra = 0;
                                    }
                                    Usuario.actualizamano(NumeroCartamuestra);
                                    if (Usuario.Mano.getSize() < 10) {
                                        deck.setEnabled(true);
                                    }

                                }).start();
                            } else {
                                Carta4.setEnabled(true);
                                Carta3.setEnabled(true);
                                Carta2.setEnabled(true);
                                Carta1.setEnabled(true);
                                avisomana();
                            }
                        }
            });
            panelUsuario.add(Carta3,dimensiones(4, 1, 1, 1, 1.0, 0.01));
            ImagenCarta4 = new ImagenFondo("imagenes/vacio.jpg",false);
            panelUsuario.add(ImagenCarta4,dimensiones(5, 0, 1, 1, 1.0, 1.0));
            Carta4 = new JButton("Usar");
            Carta4.addActionListener(ex -> {            
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode json = mapper.valueToTree(ImagenCarta4.getCarta());
                    String mensaje = mapper.writeValueAsString(json);
                    PanelCliente.SetMensaje(mensaje);
                } catch (JsonProcessingException ex1) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Carta4.setEnabled(false);
                Carta3.setEnabled(false);
                Carta2.setEnabled(false);
                Carta1.setEnabled(false);
                Jugadores Usuario = Jugadores.getInstance("Usuario");
                Jugadores Enemigo1 = Jugadores.getInstance("Enemigo");
                Jugadores.Movimientos.add("Hecho por: Usuario" + "\n" + "Nombre:" + ImagenCarta4.getCarta().getNombre() + "\n" + "Daño:" + ImagenCarta4.getCarta().getCoste());
                if (ImagenCarta2.getCarta().getTipo() == "Secreto"){
                    new Thread(() -> {
                        try {
                            Animacion.Anima(CartaUsuario);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        CartaUsuario.imagen = ImagenCarta4.getCarta().getAQuienMana();
                        if(ImagenCarta4.getCarta().getNombre() == "InstaLoser"){
                            bajaVida(1000,"Usuario");
                        }
                        if(ImagenCarta4.getCarta().getNombre() == "Borretux"){ ;
                            Usuario.Mano = ListaCircular.Borra();
                            Usuario.actualizamano(0);
                            turno("enemigo");
                        }
                        if(ImagenCarta4.getCarta().getNombre() == "Borrtex"){ ;
                        }
                        if(ImagenCarta4.getCarta().getNombre() == "ManaXXX"){ ;
                            Usuario.setMana(0);
                            bajamana(0,"Usuario");
                        }
                        if(ImagenCarta4.getCarta().getNombre() == "ManaXX"){ ;
                            Usuario.setMana(500);
                            bajamana(0,"Usuario");
                        }
                        if(ImagenCarta4.getCarta().getNombre() == "ManaEXXX"){ ;
                            Enemigo1.setMana(0);
                            bajamana(0,"Enemigo");
                        }
                        if(ImagenCarta4.getCarta().getNombre() == "ManaEXX"){ ;
                            Enemigo1.setMana(500);
                            bajamana(0,"Enemigo");
                        }
                        if(ImagenCarta4.getCarta().getNombre() == "Adiosito"){ ;
                            Usuario.Mano.borrar(Usuario.Mano.getForIndex( Usuario.Mano.getSize()));
                            Usuario.Mano.verifica();
                            Usuario.actualizamano(NumeroCartamuestra);
                        }
                        Usuario.Mano.borrar(ImagenCarta4.getCarta());
                        Usuario.actualizamano(NumeroCartamuestra);
                        turno("Enemigo");
                    }).start();
                        }else {
                if (Usuario.getMana() >=  ImagenCarta4.getCarta().getCoste()) {
                    new Thread(() -> {
                        try {
                            Animacion.Anima(CartaUsuario);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(ImagenCarta4.getCarta().getImagen() == "imagenes/Roboncito.png"){
                            Cartas.accionRoboncito();
                        }
                        if(ImagenCarta4.getCarta().getCuentapoderzote() != 0){
                            CartaUsuario.imagen = ImagenCarta4.getCarta().getImagen();
                            this.cuenta += ImagenCarta4.getCarta().getCuentapoderzote();
                            turno(ImagenCarta4.getCarta().getSiguiente());
                        }
                        if (this.cuenta != 0){
                            if(ImagenCarta4.getCarta().getNombre() == "Dragoncito"){
                                Jugadores Enemigo = Jugadores.getInstance("Enemigo");
                                bajaVida((int) (Enemigo.getVida()*0.25),"Enemigo");
                            }else{
                                bajaVida(ImagenCarta4.getCarta().getDaño(),"Enemigo");}
                            CartaUsuario.imagen = ImagenCarta4.getCarta().getImagen();
                            turno("Usuario");
                            this.cuenta -= 1;
                        }
                        else{
                            if(ImagenCarta4.getCarta().getNombre() == "Dragoncito"){
                                Jugadores Enemigo = Jugadores.getInstance("Enemigo");
                                bajaVida((int) (Enemigo.getVida()*0.25),"Enemigo");
                            }else{
                            bajaVida(ImagenCarta4.getCarta().getDaño(),"Enemigo");}
                            if (ImagenCarta4.getCarta().getAQuienMana() == "Enemigo"){
                                bajamana(ImagenCarta4.getCarta().getCoste(), "Enemigo");
                            }else {
                                if(ImagenCarta4.getCarta().getAQuienMana() =="bloqueo"){
                                    this.Bloqueo = true;
                                }
                                bajamana(ImagenCarta4.getCarta().getCoste(), "Usuario");
                            }
                            CartaUsuario.imagen = ImagenCarta4.getCarta().getImagen();
                            if (Usuario.getMana() > 750){
                                int a = 1000 -Usuario.getMana();
                                bajamana(-a,"Usuario");
                            }
                            else{
                                bajamana(-250,"Usuario");
                            }
                            turno(ImagenCarta4.getCarta().getSiguiente());
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


                    }).start();
                }else{
                    Carta4.setEnabled(true);
                    Carta3.setEnabled(true);
                    Carta2.setEnabled(true);
                    Carta1.setEnabled(true);
                    avisomana();
                }}
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
            panelUsuario.add(Movimientos,dimensiones(7, 0, 1, 1, 1.0, 1.0));
            paso = new JButton("Paso");
            paso.addActionListener(ex -> {
                Jugadores Usuario = Jugadores.getInstance("Usuario");
                turno("Enemigo");
                if (Usuario.getMana() > 750){
                    int a = 1000 -Usuario.getMana();
                    bajamana(-a,"Usuario");
                }
                else{
                    bajamana(-250,"Usuario");
                }
            });
            panelUsuario.add(paso,dimensiones(7, 1, 1, 1, 1.0, 0.01));
            GridBagConstraints usuarioDimension = dimensiones(0, 1, 2, 2, 1.0, 0.3);
            frame.add(panelUsuario,usuarioDimension);
        }
        //metodo que pone frame donde el usuario vera los movimientos del juegos
    public void framemovimientos(boolean Movimientrosabierto){
        Movimientos.setEnabled(false);
        if (Movimientrosabierto) {
            framemovimientos.setLayout(new GridBagLayout());
            JTextArea Detalles = new JTextArea();
            Detalles.setFont( new Font("Arial",Font.BOLD, 16) );
            Detalles.setEnabled(false);
            JButton Anterior = new JButton("Anterior");
            Anterior.addActionListener(ex->{
                if(indicemovimiento > 0){
                    indicemovimiento -=1;
                    Detalles.setText(Jugadores.Movimientos.getForIndex(indicemovimiento));
                }
            });
            JButton Siguiente = new JButton("Siguiente");
            Siguiente.addActionListener(ex->{
                if(indicemovimiento < Jugadores.Movimientos.getSize()-1){
                    indicemovimiento +=1;
                    Detalles.setText(Jugadores.Movimientos.getForIndex(indicemovimiento));
                }
            });
            Detalles.setText(Jugadores.Movimientos.getForIndex(indicemovimiento));
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
    //metedo para pasar el turno de jugador a jugador
    public void turno(String quien){
        if (quien == "Enemigo"){
            frame.remove(panelUsuario);
            frame.repaint();
        }
        else{
            frame.add(panelUsuario, dimensiones(0, 1, 2, 2, 1.0, 0.3));
            frame.repaint();
        }
    }
    //metodo que pone panel dentro del panel lucha donde se vera la vida de cada jugador
    public void PanelSuperior(){
        panelSuperior = new ImagenFondo("imagenes/fondo superior.jpg",false);
        panelSuperior.setLayout(new GridBagLayout());
        vidausuario = new ImagenFondo("imagenes/vida 1000.jpg",false);
        panelSuperior.add(vidausuario,dimensiones(0, 0, 1, 1, 0.9, 1.0));
        manausuario = new ImagenFondo("imagenes/mana 200.jpg",false);
        panelSuperior.add(manausuario,dimensiones(0, 1, 1, 1, 0.9, 1.0));
        vidaenemigo = new ImagenFondo("imagenes/vida 1000.jpg",true);
        panelSuperior.add(vidaenemigo,dimensiones(2, 0, 1, 1, 0.9, 1.0));
        manaenemigo = new ImagenFondo("imagenes/mana 200.jpg",true);
        panelSuperior.add(manaenemigo,dimensiones(2, 1, 1, 1, 0.9, 1.0));

        PanelLucha.add(panelSuperior,dimensiones(0, 0, 5, 1, 0.7, 0.8));
    }
    //metodo que revisa si el mana es suficiente para lanzar una carta
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
    //metodo que recibe la carta del enemigo y la efectua
    public void AtaqueEnemigo(Cartas carta) {
        Jugadores Enemigo2 = Jugadores.getInstance("Enemigo");
        Jugadores Usuario= Jugadores.getInstance("Usuario");
        Jugadores.Movimientos.add("Hecho por: Enemigo" + "\n" + "Nombre:" + carta.getNombre() + "\n" + "Daño:" + carta.getCoste());
        if (carta.getTipo().equals("Secreto")){
            new Thread(() -> {
                try {
                    Animacion.Anima(CartaDerecha);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CartaDerecha.imagen = carta.getAQuienMana();
                if(ImagenCarta4.getCarta().getNombre().equals("InstaLoser")){
                    bajaVida(1000,"Enemigo");
                }
                if(carta.getNombre().equals("Borrtex")){
                    Usuario.Mano.Borra();
                    Usuario.actualizamano(0);
                }
                if(carta.getNombre().equals("ManaXXX")){ ;
                    Enemigo2.setMana(0);
                    bajamana(0,"Enemigo");
                }
                if(carta.getNombre().equals("ManaXX")){ ;
                    Enemigo2.setMana(500);
                    bajamana(0,"Enemigo");
                }
                if(carta.getNombre().equals("ManaEXXX")){ ;
                    Usuario.setMana(0);
                    bajamana(0,"Usuario");
                }
                if(carta.getNombre().equals("ManaEXX")){ ;
                    Usuario.setMana(500);
                    bajamana(0,"Usuario");
                }
                if(carta.getNombre().equals("Adiosito")){ ;
                }
                turno("Usuario");
            }).start();
        }else {
        new Thread(() -> {
        try {
            Animacion.Anima(CartaDerecha);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Jugadores.Movimientos.add("Hecho por: Enemigo" + "\n" + "Nombre:" + carta.getNombre() + "\n" + "Daño:" + carta.getCoste());
        if (carta.getImagen().equals("imagenes/Roboncito.png")) {
            Cartas.accionRoboncito();
        }
        if (carta.getCuentapoderzote() != 0) {
            CartaDerecha.imagen = carta.getImagen();
            this.cuentaE += carta.getCuentapoderzote();
            turno("Usuario");
        }
        if (this.cuentaE != 0) {
            if (carta.getAQuienMana().equals("bloqueo")) {
                this.Bloqueo = true;
            }
            if (carta.getNombre().equals("Dragoncito")) {
                bajaVida((int) (Usuario.getVida() * 0.25), "Usuario");
            } else {
                bajaVida(carta.getDaño(), "Usuario");
            }
            CartaDerecha.imagen = carta.getImagen();
            turno("Enemigo");
            this.cuenta -= 1;
        } else {
            if (carta.getNombre().equals("Dragoncito")) {
                Jugadores Enemigo = Jugadores.getInstance("Usuario");
                bajaVida((int) (Enemigo.getVida() * 0.25), "Usuario");
            } else {
                bajaVida(carta.getDaño(), "Usuario");
            }
            if (carta.getAQuienMana().equals("Usuario")) {
                bajamana(carta.getCoste(), "Usuario");
            } else {
                if (carta.getAQuienMana().equals("bloqueo")) {
                    this.Bloqueo = true;
                }
                bajamana(carta.getCoste(), "Enemigo");
            }
            CartaDerecha.imagen = carta.getImagen();
            if (Enemigo2.getMana() > 750) {
                int a = 1000 - Enemigo2.getMana();
                bajamana(-a, "Enemigo");
            } else {
                bajamana(-250, "Enemigo");
            }
            turno("Usuario");
        }}).start();}
    }
    //metodo que revisa si alguien gano
    public void revisa(){
        Jugadores Usuario = Jugadores.getInstance("Usuario");
        Jugadores Enemigo = Jugadores.getInstance("Enemigo");
        new Thread(() -> {
            while(true){
                if(Enemigo.getVida() <= 0){
                    PanelLucha.imagen = "imagenes/campo de batalla ganaste.jpg";
                    PanelLucha.removeAll();
                    frame.repaint();
                    break;
                }else{
                    if(Usuario.getVida() <= 0){
                        PanelLucha.imagen = "imagenes/campo de batalla perdiste.jpg";
                        PanelLucha.removeAll();
                        frame.repaint();
                        break;}
                }
            }

        }).start();

    }
    //metodo para ingresar la dimensiones de un componente
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
    //metodo para bajar vida ya sea al enemigo o al usuario
    public void bajaVida(int daño,String quien){
        Jugadores recibio;
        ImagenFondo interfaz;
        if(this.Bloqueo){
            daño = 0;
        }
        if (quien == "Enemigo"){
            recibio = Jugadores.getInstance("Enemigo");
            interfaz = this.vidaenemigo ;
        }
        else{
            recibio = Jugadores.getInstance("Usuario");
            interfaz = this.vidausuario ;
        }
        recibio.daño(daño);
        if (recibio.getVida() <= 950 && recibio.getVida() > 900) {
            interfaz.imagen = "imagenes/vida 950.jpg";
            interfaz.repaint();
        }
        else{
            if (recibio.getVida() <= 900 && recibio.getVida() > 850) {
                interfaz.imagen = "imagenes/vida 900.jpg";
                interfaz.repaint();
            }
            else{
                if (recibio.getVida() <= 850 && recibio.getVida() > 800) {
                    interfaz.imagen = "imagenes/vida 850.jpg";
                    interfaz.repaint();
                }
                else{
                    if (recibio.getVida() <= 800 && recibio.getVida() > 750) {
                        interfaz.imagen = "imagenes/vida 800.jpg";
                        interfaz.repaint();
                    }
                    else {
                if (recibio.getVida() <= 750 && recibio.getVida() > 700) {
                    interfaz.imagen = "imagenes/vida 750.jpg";
                    interfaz.repaint();
                }
                else {
                    if (recibio.getVida() <= 700 && recibio.getVida() > 650) {
                        interfaz.imagen = "imagenes/vida 700.jpg";
                        interfaz.repaint();
                    }
                    else {
                        if (recibio.getVida() <= 650 && recibio.getVida() > 600) {
                            interfaz.imagen = "imagenes/vida 650.jpg";
                            interfaz.repaint();
                        }
                        else {
                    if (recibio.getVida() <= 600 && recibio.getVida() > 550) {
                        interfaz.imagen = "imagenes/vida 600.jpg";
                        interfaz.repaint();
                    }
                    else {
                        if (recibio.getVida() <= 550 && recibio.getVida() > 500) {
                            interfaz.imagen = "imagenes/vida 550.jpg";
                            interfaz.repaint();
                        }
                        else {
                        if (recibio.getVida() <= 500 && recibio.getVida() > 450) {
                            interfaz.imagen = "imagenes/vida 500.jpg";
                            interfaz.repaint();
                        }
                        else {
                            if (recibio.getVida() <= 450 && recibio.getVida() > 400) {
                                interfaz.imagen = "imagenes/vida 450.jpg";
                                interfaz.repaint();
                            }
                            else {
                            if (recibio.getVida() <= 400 && recibio.getVida() > 350) {
                                interfaz.imagen = "imagenes/vida 400.jpg";
                                interfaz.repaint();
                            }
                            else {
                                if (recibio.getVida() <= 350 && recibio.getVida() > 300) {
                                    interfaz.imagen = "imagenes/vida 350.jpg";
                                    interfaz.repaint();
                                }
                                else {
                                if (recibio.getVida() <= 300 && recibio.getVida() > 250) {
                                    interfaz.imagen = "imagenes/vida 300.jpg";
                                    interfaz.repaint();
                                }
                                else {
                                    if (recibio.getVida() <= 250 && recibio.getVida() > 200) {
                                        interfaz.imagen = "imagenes/vida 250.jpg";
                                        interfaz.repaint();
                                    }
                                    else {
                                    if (recibio.getVida() <= 200 && recibio.getVida() > 150) {
                                        interfaz.imagen = "imagenes/vida 200.jpg";
                                        interfaz.repaint();
                                    }
                                    else {
                                        if (recibio.getVida() <= 150 && recibio.getVida() > 100) {
                                            interfaz.imagen = "imagenes/vida 150.jpg";
                                            interfaz.repaint();
                                        }
                                        else {
                                        if (recibio.getVida()<= 100 && recibio.getVida() > 50) {
                                            interfaz.imagen = "imagenes/vida 100.jpg";
                                            interfaz.repaint();
                                        }
                                        else {
                                            if (recibio.getVida() <= 50 && recibio.getVida() > 0) {
                                                interfaz.imagen = "imagenes/vida 50.jpg";
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
                }}}}}}}}}}}
            }
        }
        if (recibio.getVida() > 1000){
            recibio.setVida(1000);}
    }
    //metodo para bajar mana ya sea al enemigo o al usuario
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
}}}}}
        if (recibio.getMana() > 1000){
            recibio.setMana(1000);}
    }

}
