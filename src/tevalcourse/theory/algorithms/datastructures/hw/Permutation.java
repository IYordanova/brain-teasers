package tevalcourse.theory.algorithms.datastructures.hw;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;


public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> strArray = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            strArray.enqueue(StdIn.readString());
        }

        int i = 0;
        Iterator<String> iterator = strArray.iterator();
        while (i < k && iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        System.out.println();
    }

}
