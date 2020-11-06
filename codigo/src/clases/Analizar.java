/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import interfaz.proyecto.Ventana;

/**
 *
 * @author johnn
 */
public class Analizar {
    Ventana inicia;

    public Analizar(String mensaje) throws JsonProcessingException {
        System.out.print("Se va a leer la jugada");
        System.out.print(mensaje);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.readValue(mensaje, Cartas.class));
        Cartas carta = mapper.readValue(mensaje, Cartas.class);
        inicia.AtaqueEnemigo(carta);
    }    
}