package tevalcourse.theory.datastructures;

import edu.princeton.cs.algs4.StdOut;

public class Theory {

    /*  Stacks
        - basic operations required: insert, remove, iterate, test if empty
        - on remove, remove the last one inserted, FILO
     */


    /* Queue
        - basic operations required: insert, remove, iterate, test if empty
        - on remove, remove the first one inserted, FIFO
     */

    /* Bag
        - order doesn't matter
     */

    public static void main(String[] args) {
//        QueueAsArray queue = new QueueAsArray();
//        while (!StdIn.isEmpty()) {
//            String str = StdIn.readString();
//            if (str.equals("-")) {
//                StdOut.print(queue.pop());
//            } else {
//                queue.push(str);
//            }
//        }

        int n = 50;

        StackAsLinkedList<Integer> stack = new StackAsLinkedList<>();
        while (n > 0) {
            stack.push(n % 2);
            n = n / 2;
        }

        for (int digit : stack) {
            StdOut.print(digit);
        }

        StdOut.println();
    }
}
