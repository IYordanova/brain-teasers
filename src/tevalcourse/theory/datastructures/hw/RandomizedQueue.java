package tevalcourse.theory.datastructures.hw;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int index = 0;
    private int size = 0;

    public RandomizedQueue() {
        this.array = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == array.length) {
            resize(2 * array.length);
        }
        array[index++] = item;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = null;
        int i = 0;
        while (item == null) {
            i = getIndex();
            item = array[i];
        }
        array[i] = null;
        if (size == array.length / 4) {
            resize(array.length / 2);
        }
        size--;
        index--;
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = null;
        while (item == null) {
            item = array[getIndex()];
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new RandomIterator(size);
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int i = 0;
        for (int j = 0; j < array.length; j++) {
            Item item = array[j];
            if (item != null) {
                copy[i] = item;
                i++;
            }
        }
        array = copy;
    }

    private int getIndex() {
        return StdRandom.uniform(array.length);
    }

    private class RandomIterator implements Iterator<Item> {
        int c = 1013904223;
        int a = 1664525;
        long m;
        long seed;
        long n;
        long next;
        boolean hasNext = true;

        public RandomIterator(int n) {
            this.n = n;
            this.m = (long) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
            this.next = StdRandom.uniform(Math.min(n, Integer.MAX_VALUE));
            this.seed = next;
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            next = ((a * next + c) % m);
            while (next >= n) {
                next = (int) ((a * next + c) % m);
            }
            if (next == seed) {
                hasNext = false;
            }
            return array[(int) next];
        }
    }


    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        tessAdd(queue);
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());

        testIterator(queue); // UnsupportedOperationException, iterate, NoSuchElementException

        for (int i = 0; i < 10; i++) {
            queue.dequeue();
        }
        testRemoveOnEmptyQueue(queue); // NoSuchElementException, NoSuchElementException
    }

    private static void tessAdd(RandomizedQueue<Integer> queue) {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
    }

    private static void testRemoveOnEmptyQueue(RandomizedQueue<Integer> queue) {
        // dequeue() on empty deque - NoSuchElementException
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println(e.getClass().getName());
        }
    }

    private static void testIterator(RandomizedQueue<Integer> queue) {
        Iterator<Integer> it = queue.iterator();

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
