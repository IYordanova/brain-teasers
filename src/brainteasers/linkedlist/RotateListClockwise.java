package brainteasers.linkedlist;

public class RotateListClockwise {

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


    private static ListNode rotateRight(ListNode A, int B) {
        if (A == null) {
            return null;
        }
        ListNode cursor = A;
        int listLength = 1;
        while (cursor.next != null) {
            cursor = cursor.next;
            listLength++;
        }

        if (B > listLength) {
            B = B % listLength;
        }
        B = listLength - B;

        if (B == 0) {
            return A;
        }

        ListNode current = A;
        int count = 1;
        while (count < B && current != null) {
            current = current.next;
            count++;
        }

        if (current == null) {
            return A;
        }

        ListNode kthNode = current;
        cursor.next = A;
        A = kthNode.next;
        kthNode.next = null;
        return A;
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

        ListNode x = rotateRight(start, 2);
        System.out.println(x);
        x = rotateRight(start, 7);
        System.out.println(x);
    }


}
