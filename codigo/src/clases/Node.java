package clases;

import java.awt.*;

public class Node<T> {
    private T value;
    public Node next;
    public Node prev;

    public Node() {
        this.next = null;
    }

    public Node(T value) {
        this();
        this.value = value;
    }

    public Node(T value, Node next, Node prev) {
        this(value);
        this.next = next;
        this.prev = prev;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}
