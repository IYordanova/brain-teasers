package brainteasers.heapMaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeKSortedLinkedLists {

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private static ListNode mergeKLists(List<ListNode> a) {
        List<ListNode> sortedNodes = new ArrayList<>();

        for (ListNode node : a) {
            ListNode n = node;
            while (n != null) {
                sortedNodes.add(n);
                n = n.next;
            }
        }

        sortedNodes.sort(Comparator.comparingInt(x -> x.val));

        ListNode node = sortedNodes.get(0);
        for (int i = 1; i < sortedNodes.size(); i++) {
            ListNode temp = sortedNodes.get(i);
            node.next = temp;
            node = temp;
        }
        return sortedNodes.get(0);
    }

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode ten = new ListNode(10);
        ListNode twenty = new ListNode(20);
        one.next = ten;
        ten.next = twenty;

        ListNode four = new ListNode(4);
        ListNode eleven = new ListNode(11);
        ListNode thirteen = new ListNode(13);
        four.next = eleven;
        eleven.next = thirteen;

        ListNode three = new ListNode(3);
        ListNode eight = new ListNode(8);
        ListNode nine = new ListNode(9);
        three.next = eight;
        eight.next = nine;

        ListNode x = mergeKLists(Arrays.asList(one, four, three));
        System.out.println(x);
    }

}
