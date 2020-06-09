package tevalcourse.theory.algorithms.datastructures;

public class Node<T> {
    final T item;
    Node<T> next;

    public Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
    }
}
