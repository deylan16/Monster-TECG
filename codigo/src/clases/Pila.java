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
public class Pila {
    private ListaDoble deck;
    
    public Pila() {
        deck = new ListaDoble();
    }
    
    public void Add(Object carta){
        if (deck.getSize() < 20) {
            deck.add(carta);
        }
    }
    public Object get(){
        int last = deck.getSize();
        Object card = deck.getForIndex(last);
        deck.deletePila();
        return card;
    }

}