/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 * <p>Clase pila para crear el deck</p>
 * @author Deylan
 * @author Johnny
 */
public class Pila<T> {
    private ListaDoble deck;
    
    public Pila() {
        deck = new ListaDoble();
    }
    
    /**
     * <p>Añade una carta al final de la lista</p>
     * @param carta
     */
    public void Add(Object carta){
        if (deck.getSize() < 20) {
            deck.add(carta);
        }
    }

    /**
     * <p>Obtiene la última carta de la pila</p>
     * @return carta
     */
    public T get(){
        int last = deck.getSize()-1;
        Object card = deck.getForIndex(last);
        deck.deletePila();
        return (T) card;
    }

    /**
     *<p> Verifica si la pila esta vacia</p>
     * @return boolean
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }

}