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
        System.out.println("Se va a leer la jugada");
        System.out.println(mensaje);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode Nodecarta = mapper.readTree(mensaje);
        System.out.println(Nodecarta);
        Cartas carta = mapper.treeToValue(Nodecarta, Cartas.class);
        System.out.println(carta);
        //inicia.AtaqueEnemigo(carta);
    }    
}