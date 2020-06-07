package tevalcourse.theory.algorithms.datastructures;

public class QueueAsArray<T> {
    private T[] array;
    private int head = 0;
    private int tail = 0;

    public QueueAsArray() {
        this.array = (T[]) new Object[1];
    }

    void push(T item) {
        if (tail == array.length) {
            resize(2 * array.length);
        }
        array[tail++] = item;
    }

    private void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        int j = 0;
        for (int i = head; i < tail; i++) {
            copy[j] = array[i];
            j++;
        }
        tail -= head;
        head = 0;
        array = copy;
    }

    T pop() {
        T item = array[head];
        array[head] = null;
        head++;
        if ((tail - head) <= array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }

    boolean isEmpty() {
        return tail == 0;
    }
}
