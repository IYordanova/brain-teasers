package tevalcourse.theory.algorithms.datastructures.hw;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Item[] array;
    private int size = 0;
    private int head = -1;
    private int tail = -1;

    public Deque() {
        this.array = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == array.length) {
            sizeUp(2 * array.length, true);
        } else {
            if (head == -1) head = 0;
            else head = (head == 0 ? array.length - 1 : head - 1);
        }
        array[head] = item;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == array.length) {
            sizeUp(2 * array.length, false);
        } else {
            tail = (size == 0 || tail == array.length - 1 ? 0 : tail + 1);
        }
        array[tail] = item;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        head = head == -1 ? 0 : head;
        Item item = array[head];
        array[head] = null;

        if (size == array.length / 4) {
            sizeDown(array.length / 2, true);
        } else {
            head = (head == array.length - 1 ? 0 : head + 1);
        }

        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        tail = tail == -1 ? 0 : tail;
        Item item = array[tail];
        array[tail] = null;

        if (size == array.length / 4) {
            sizeDown(array.length / 2, false);
        } else {
            tail = (tail == 0 ? array.length - 1 : tail - 1);
        }

        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private void sizeUp(int capacity, boolean front) {
        Item[] copy = (Item[]) new Object[capacity];
        int from = head == -1 ? 0 : head;
        int i = front ? 1 : 0;
        int to = front ? size : size - 1;
        while (i <= to) {
            copy[i] = array[from];
            from = (from == size - 1 && tail < head) ? 0 : from + 1;
            i++;
        }
        tail = front ? i - 1 : i;
        head = 0;
        array = copy;
    }

    private void sizeDown(int capacity, boolean front) {
        Item[] copy = (Item[]) new Object[capacity];
        int from = front ? head + 1 : head;
        int to = front ? tail : tail - 1;
        int i = 0;
        while (from <= to) {
            copy[i] = array[from];
            if (from == array.length - 1 && size > 1) {
                from = 0;
            } else {
                from++;
            }
            i++;
        }
        tail = size - 2;
        head = 0;
        array = copy;
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = -1;

        @Override
        public boolean hasNext() {
            return i != tail;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (i == -1) {
                i = head;
            } else if (i == array.length - 1) {
                i = 0;
            } else {
                i++;
            }
            Item item = array[i];
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        for(int i = 0; i < 50; i ++) {
            d.addLast(1);
        }
        Iterator<Integer> it = d.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        for(int i = 0; i < 500; i ++) {
            d.addLast(1);
        }
        it = d.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        for(int i = 0; i < 1000; i ++) {
            d.addLast(1);
        }
        it = d.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }

        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for(int i = 0; i < 500; i ++) {
            rq.isEmpty();
            rq.enqueue(245);
            rq.enqueue(457);
            rq.enqueue(474);
            rq.dequeue();
            rq.isEmpty();
            rq.enqueue(209);
            rq.enqueue(439);
        }

        Deque<Integer> deque = new Deque<>();

        tessAddFirst(deque);
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());

        testIterator(deque); // UnsupportedOperationException, iterate, NoSuchElementException

        for (int i = 0; i < 10; i++) {
            System.out.println(deque.removeFirst());
        }
        testRemoveOnEmptyDeque(deque); // NoSuchElementException, NoSuchElementException

        testAddLast(deque);
        testIterator(deque); // UnsupportedOperationException, iterate, NoSuchElementException
        // test removeLast()
        for (int i = 0; i < 10; i++) {
            System.out.println(deque.removeFirst());
        }
        testRemoveOnEmptyDeque(deque); // NoSuchElementException, NoSuchElementException

        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            deque.addLast(i);
        }
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());
        testIterator(deque); // UnsupportedOperationException, iterate, NoSuchElementException
        for (int i = 0; i < 10; i++) {
            deque.removeFirst();
            deque.removeLast();
        }
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());
    }

    private static void testAddLast(Deque<Integer> deque) {
        for (int i = 0; i < 10; i++) {
            deque.addLast(i);
        }
    }

    private static void tessAddFirst(Deque<Integer> deque) {
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
        }
    }

    private static void testRemoveOnEmptyDeque(Deque<Integer> deque) {
        // removeFirst() on empty deque - NoSuchElementException
        try {
            deque.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println(e.getClass().getName());
        }

        // removeLast() on empty deque - NoSuchElementException
        try {
            deque.removeLast();
        } catch (NoSuchElementException e) {
            System.out.println(e.getClass().getName());
        }
    }

    private static void testIterator(Deque<Integer> deque) {
        Iterator<Integer> it = deque.iterator();

        // iterator.remove() - UnsupportedOperationException
        try {
            it.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getClass().getName());
        }

        // test iterate
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // iterator.next() on empty deque - NoSuchElementException
        try {
            it.next();
        } catch (NoSuchElementException e) {
            System.out.println(e.getClass().getName());
        }
    }
}
