package clases;

/**
 * <p>Clase lista doble</p>
 * @author Deylan
 * @author Johnny
 * @param <T>
 */
public class ListaDoble<T> {
    private Node<T> first;
    private int size = 0;
    private Node<T> last;

    public ListaDoble() {
        this.first = null;
    }

    /**
     *<p>Averigua si la lista esta vacia</p>
     * @return boolean
     */
    public boolean isEmpty() {
        return this.first == null;
    }

    /**
     * <p>Añade un elemento al final de la lista</p>
     *
     * @param element the element to add
     */
    public void add(T element) {
        if (this.isEmpty()) {
            this.size += 1;
            Node<T> nuevo =  new Node<T>(element);
            this.first = nuevo;
            this.last = nuevo;
        } else {
            Node<T> ref = this.first;
            while (ref.getNext() != null) {
                ref = ref.getNext();
            }
            Node<T> nuevo =  new Node<T>(element);
            ref.setNext(nuevo);
            nuevo.setPrev(ref);
            this.last = nuevo;
            this.size += 1;
        }

    }

    /**
     *<p>Printea toda la lista</p>
     */
    public void print(){
        if (this.isEmpty()) {
            System.out.print("lista vacia");
        } else {
            Node<T> ref = this.first;
            while (ref.getNext() != null) {
                System.out.print(ref.getValue());
                ref = ref.getNext();
            }
            System.out.print(ref.getValue());
        }
    }

    /**
     *<p>Printea los valores de la lista</p>
     */
    public void printR(){
        if (this.isEmpty()) {
            System.out.print("lista vacia");
        } else {
            Node<T> ref = this.last;
            while (ref.getPrev() != null) {
                System.out.print(ref.getValue());
                ref = ref.getPrev();
            }
            System.out.print(ref.getValue());
        }
    }

    /**
     *<p>Borra la última carta de la lista</p>
     */
    public void deletePila() {
        if(this.first == this.last){
            this.first = null;
            this.last = null;
        }
        else{
            this.last = this.last.prev;
            this.last.next = null;
            this.size -= 1;
        }

    }
    
    public int getSize(){
        return this.size;
    }
    
    /**
     * <p>Obtiene el valor del nodo en la posicion deseada</p>
     * @param indice
     * @return valor del nodo
     */
    public T getForIndex(int indice){
        int i = 0;
        if (this.isEmpty()) {
            System.out.print("lista vacia");
        } else {
            if (indice == 0) {
                return this.first.getValue();
            } else {
                if (indice <= this.size) {
                    Node<T> ref = this.first;
                    while (i != indice) {
                        ref = ref.getNext();
                        i++;
                    }
                    return ref.getValue();
            }
        }
        return null;
    }
        return null;
    }
}
