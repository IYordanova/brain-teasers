package brainteasers.linkedlist;

public class InsertionSortLinkedList {

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


    private static ListNode insertionSortList(ListNode A) {
        ListNode current = A;
        ListNode tail = null;
        while (current != null && tail != A) {
            ListNode next = current;
            for (; next.next != tail; next = next.next) {
                if (next.val >= next.next.val) {
                    int temp = next.val;
                    next.val = next.next.val;
                    next.next.val = temp;
                }
            }
            tail = next;
            current = A;
        }
        return A;
    }


    public static void main(String[] args) {
        ListNode start = new ListNode(4);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(5);
        ListNode fourth = new ListNode(3);
        ListNode fifth = new ListNode(1);

        start.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = null;

        ListNode x = insertionSortList(start);
        System.out.println(x);
    }


}
