package clases;

/**
 * <p> Clase lista circular</p>
 * @author Deylan
 * @author Johnny
 * @param <T>
 */
public class ListaCircular<T>  {
    private Node<T> first;
    private int size = 0;
    private Node<T> last;
    public static ListaCircular<Cartas> ListaVacia = new ListaCircular<Cartas>();

    public ListaCircular() {
        this.first = null;
    }

    public boolean isEmpty() {
        return this.first == null;
    }
    public int getSize(){
        return this.size;
    }

    /**
     *<p> Printea toda la lista circular</p>
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
     * <p> Añade un nuevo elemento a la lista </p>
     * @param element
     */
    public void add(T element) {
        if (this.isEmpty()) {
            this.size += 1;
            Node<T> nuevo =  new Node<T>(element);
            nuevo.setNext(nuevo);
            nuevo.setPrev(nuevo);
            this.first = nuevo;
            this.last = nuevo;
        } else {
            Node<T> ref = this.last;
            Node<T> nuevo =  new Node<T>(element);
            ref.setNext(nuevo);
            ref.setPrev(nuevo);
            nuevo.setPrev(ref);
            nuevo.setNext(ref);
            this.last = nuevo;
            this.size += 1;
        }

    }

    /**
     * <p> Metodo para obtener el valor del nodo en la posicion indicada </p>
     * @param indice
     * @return el valor del nodo deseado
     */
    public T getForIndex(int indice) {
        int i = 0;
        if (this.isEmpty()) {
            System.out.print("lista vacia");
        }
        else {
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
        }
        return null;
    }
    /**
     *<p> Obtiene todo el nodo de la posicion deseada</p>
     * @param indice
     * @return retorna todo el nodo
     */
    public Node<T> getForIndex2(int indice) {
        int i = 0;
        if (this.isEmpty()) {
            System.out.print("lista vacia");
        }
        else {
            if (indice == 0) {
                return this.first;
            } else {
                if (indice <= this.size) {
                    Node<T> ref = this.first;
                    while (i != indice) {
                        ref = ref.getNext();
                        i++;
                    }
                    return ref;
                }
            }
        }
        return null;
    }

    /**
     * <p> Borra una carta</p>
     * @param carta
     */
    public void borrar(Cartas carta){
        Node<T> ref = this.first;
        T i = ref.getValue();
        int cuenta = 0;
        if (this.isEmpty()) {
            System.out.print("lista vacia");
        }else{if(carta == this.first.getValue()){
                this.first = this.first.getNext();
                this.first.setPrev(this.last);
                this.last.setNext(this.first);
                this.size -= 1;
            } else {if(carta == this.last.getValue()){
                    this.last = this.last.getPrev();
                    this.first.setPrev(this.last);
                    this.last.setNext(this.first);
                    this.size -= 1;
                } else {
                        while (i != carta) {
                            ref = ref.getNext();
                            i = ref.getValue();
                            cuenta += 1;
                        }
                        Jugadores Usuario = Jugadores.getInstance("Usuario");
                        Usuario.Mano.getForIndex2(cuenta-1).setNext(Usuario.Mano.getForIndex2(cuenta+1));
                        Usuario.Mano.getForIndex2(cuenta+1).setPrev(Usuario.Mano.getForIndex2(cuenta-1));
                        this.size -= 1;
                    }}
                    }
        }
        
    /**
     * <p>Pone la lista de la mano en vacia</p>
     * @return lista vacia
     */
    public static ListaCircular Borra(){
        return ListaVacia;}

    /**
     *<p> verifica si la lista tiene un valor menor a cuatro, se una en la mano para que simpre tenga cuantro cartas, cuando tiene menos se agrega una carta vacia </p>
     */
    public void verifica() {
        if (getSize() < 4) {
            Jugadores Usuario = Jugadores.getInstance("Usuario");
            Usuario.agregaVacio();
        }
    }
}

