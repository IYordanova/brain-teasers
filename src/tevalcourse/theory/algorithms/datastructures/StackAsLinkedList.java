package tevalcourse.theory.algorithms.datastructures;


import java.util.Iterator;
import java.util.function.Consumer;

/**
  - every operation takes constant time in the worst case
  - uses extra time and space to deal with links
  - space - ~40N bytes for N items:
     -> 16B overhead
     -> 2 references x 8B
     -> 8B for an inner class
*/
public class StackAsLinkedList<T> implements Iterable<T>{
    private Node<T> first;

    void push(T item) {
        Node<T> oldFirst = first;
        first = new Node<T>(item, oldFirst);
    }

    T pop() {
        T item = first.item;
        first = first.next;
        return item;
    }

    boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    private class ListIterator implements Iterator<T> {
        private Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}
