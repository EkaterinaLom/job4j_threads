package ru.job4j.linked;

public class Node<T> {

    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext (Node<T> next) {
        throw new IllegalStateException(
                String.format("Node cannot be changed!")
        );
    }

    public T getValue() {
        return value;
    }

    public void setValue (final T value) {
        throw new IllegalStateException(
                String.format("Value cannot be changed!")
        );
    }
}