/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johnn
 */
public class PanelHost extends javax.swing.JFrame{
    public static int port;
    public static String ip;
    private static Servidor SjugadorH;
    /**
     * Creates new form PanelHost
     */
    public PanelHost() {
        initComponents();
        PanelHost.print("Vamos a iniciar una conexion \n");
        SjugadorH = new Servidor();
        try {
            SjugadorH.setIP();
        } catch (Exception ex) {
            Logger.getLogger(PanelHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        SjugadorH.setPORT();
        Thread tSH = new Thread(SjugadorH);
        tSH.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Texto = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Texto.setColumns(20);
        Texto.setRows(5);
        jScrollPane1.setViewportView(Texto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextArea Texto;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public static void print(String texto){
        PanelHost.Texto.append(texto);
    }
    public static void iniciar() throws Exception{
        //this.setVisible(false);
        port = SjugadorH.PUERTO + 1;
        ip = SjugadorH.CLIP;
        Conexion panel = new Conexion();
        Conexion.setCinvi(ip, port);
        Conexion.Chost();
    }
}