/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author johnn
 */
public class Pila<T> {
    private ListaDoble deck;
    
    public Pila() {
        deck = new ListaDoble();
    }
    
    public void Add(Object carta){
        if (deck.getSize() < 20) {
            deck.add(carta);
        }
    }
    public T get(){
        int last = deck.getSize()-1;
        Object card = deck.getForIndex(last);
        deck.deletePila();
        return (T) card;
    }
    public boolean isEmpty() {
        return deck.isEmpty();
    }

}