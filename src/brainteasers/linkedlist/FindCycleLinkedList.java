package brainteasers.linkedlist;

import java.util.HashSet;

public class FindCycleLinkedList {

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    private static ListNode detectCycle(ListNode a) {
        HashSet<ListNode> s = new HashSet<>();
        while (a != null) {
            if (s.contains(a)) {
                return a;
            }
            s.add(a);
            a = a.next;
        }
        return null;
    }


    public static void main(String[] args) {
        ListNode start = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);

        start.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = third;

        ListNode x = detectCycle(start);
        System.out.println(x);
//
//        start = new ListNode(1);
//        second = new ListNode(2);
//        third = new ListNode(3);
//
//        start.next = second;
//        second.next = third;
//        third.next = null;
//        x = reverseBetween(start, 2, 3);
//        System.out.println(x);

    }


}
