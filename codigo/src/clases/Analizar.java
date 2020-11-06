/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import interfaz.proyecto.Ventana;

import java.io.IOException;

/**
 *
 * @author johnn
 */
public class Analizar {
    Ventana inicia;

    public Analizar(String mensaje) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode Nodecarta = mapper.readTree(mensaje);
  
        int daño = Nodecarta.get("daño").intValue();
        int coste = Nodecarta.get("coste").intValue();
        String imagen = Nodecarta.get("imagen").asText();
        String nombre = Nodecarta.get("nombre").asText();
        String siguiente = Nodecarta.get("siguiente").asText();
        int cuentapoderzote = Nodecarta.get("cuentapoderzote").intValue();
        String aquienMana = Nodecarta.get("aquienMana").asText();
        Cartas carta = new Cartas(daño, coste, imagen, nombre, siguiente, cuentapoderzote, aquienMana);
        Ventana ventana = Ventana.getInstance();
        ventana.AtaqueEnemigo(carta);     
    }    
}