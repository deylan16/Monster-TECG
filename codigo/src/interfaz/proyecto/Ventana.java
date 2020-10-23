package interfaz.proyecto;


import javax.swing.*;
import java.awt.*;

public class Ventana extends JPanel {
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

    public void abrirVentana() throws InterruptedException {
        frame.setLayout(new GridBagLayout());
        panelLucha();
        panelUsuario();
        frame.setSize(900, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void panelLucha() {
        JPanel PanelLucha = new JPanel();
        PanelLucha.setLayout(new GridLayout(1, 1, 8, 8));
        ImagenFondo image = new ImagenFondo();
        image.setImage("imagenes/campo de batalla.jpg");
        PanelLucha.add(image);
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
            panelUsuario.add(btenviar2);
            GridBagConstraints usuarioDimension = dimensiones(0, 1, 2, 1, 1.0, 0.1);
            frame.add(panelUsuario,usuarioDimension);
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

}
