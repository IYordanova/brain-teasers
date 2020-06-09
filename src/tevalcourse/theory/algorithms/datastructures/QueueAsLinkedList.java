package tevalcourse.theory.algorithms.datastructures;

public class QueueAsLinkedList<T> {
    private Node<T> first;
    private Node<T> last;

    void enqueue(T item) {
        Node<T> oldLast = last;
        last = new Node<T>(item, null);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    T dequeue() {
        T item = first.item;
        first = first.next;
        if(isEmpty()) {
            last = null;
        }
        return item;
    }

    boolean isEmpty() {
        return first == null;
    }
}
