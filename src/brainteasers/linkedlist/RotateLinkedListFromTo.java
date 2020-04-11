package brainteasers.linkedlist;

public class RotateLinkedListFromTo {

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


    private static ListNode reverseBetween(ListNode A, int m, int n) {
        ListNode headPrev = new ListNode(0);
        headPrev.next = A;

        ListNode prevStart = headPrev;
        int diff = n - m + 1;
        int count = 1;
        while (count < m) {
            prevStart = prevStart.next;
            count++;
        }

        ListNode start = prevStart.next;
        ListNode current = start;
        ListNode prev = null;
        ListNode next = current.next;

        while (current != null && diff > 0) {
            current.next = prev;
            prev = current;
            current = next;
            diff--;

            if (diff != 0) {
                next = next.next;
            }
        }

        prevStart.next = prev;
        start.next = next;

        return headPrev.next;
    }


    public static void main(String[] args) {
        ListNode start = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);

        start.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = null;

        ListNode x = reverseBetween(start, 2, 4);
        System.out.println(x);

        start = new ListNode(1);
        second = new ListNode(2);
        third = new ListNode(3);

        start.next = second;
        second.next = third;
        third.next = null;
        x = reverseBetween(start, 2, 3);
        System.out.println(x);

    }


}
