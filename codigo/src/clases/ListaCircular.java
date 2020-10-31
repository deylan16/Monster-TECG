package clases;

public class ListaCircular<T>  {
    private Node<T> first;
    private int size = 0;
    private Node<T> last;

    public ListaCircular() {
        this.first = null;
    }

    public boolean isEmpty() {
        return this.first == null;
    }
    public int getSize(){
        return this.size;
    }
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
}

