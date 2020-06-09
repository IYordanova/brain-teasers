package tevalcourse.theory.algorithms.datastructures;


import java.util.Iterator;

/**
 * - every operation takes constant amortized time (resizing)
 * - less wasted space
 */
public class StackAsArray<T> implements Iterable<T> {
    private T[] array;
    private int N = 0;


    public StackAsArray() {
        this.array = (T[]) new Object[1];
    }

    void push(T item) {
        if (N == array.length) {
            resize(2 * array.length);
        }
        array[N++] = item;
    }

    private void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = array[i];
        }
        array = copy;
    }

    T pop() {
        T item = array[--N];
        array[N] = null;
        if (N > 0 && N == array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }

    boolean isEmpty() {
        return N == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return array[--i];
        }
    }
}
