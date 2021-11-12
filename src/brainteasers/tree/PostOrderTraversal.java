package brainteasers.tree;

import java.util.ArrayList;

public class PostOrderTraversal {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    private static ArrayList<Integer> postorderTraversal(TreeNode A) {
        ArrayList<Integer> list = new ArrayList<>();
        traversal(A, list);
        return list;
    }

    private static void traversal(TreeNode A, ArrayList<Integer> list) {
        if (A != null) {
            traversal(A.left, list);
            traversal(A.right, list);
            list.add(A.val);
        }
    }

    public static void main(String[] args) {
        TreeNode first = getTree(31);
        System.out.println(postorderTraversal(first));
    }

    private static TreeNode getTree(int rootVal) {
        TreeNode four = new TreeNode(4);
        TreeNode twelve = new TreeNode(12);
        TreeNode ten = new TreeNode(10);

        TreeNode eighteen = new TreeNode(18);
        TreeNode twentyfour = new TreeNode(24);
        TreeNode twentytwo = new TreeNode(22);

        TreeNode fifteen = new TreeNode(15);

        TreeNode thirthyone = new TreeNode(31);
        TreeNode fourthyfour = new TreeNode(44);
        TreeNode thirthyfive = new TreeNode(35);

        TreeNode sixtysix = new TreeNode(66);
        TreeNode ninty = new TreeNode(90);
        TreeNode seventy = new TreeNode(70);

        TreeNode fifty = new TreeNode(50);

        TreeNode twentyfive = new TreeNode(25);

        twentyfive.left = fifteen;
        twentyfive.right = fifty;

        fifteen.left = ten;
        fifteen.right = twentytwo;

        fifty.left = thirthyfive;
        fifty.right = seventy;

        ten.left = four;
        ten.right = twelve;

        twentytwo.left = eighteen;
        twentytwo.right = twentyfour;

        thirthyfive.left = thirthyone;
        thirthyfive.right = fourthyfour;

        seventy.left = sixtysix;
        seventy.right = ninty;

        four.left = new TreeNode(100);
        four.right = new TreeNode(110);

        twelve.left = new TreeNode(200);
        twelve.right = new TreeNode(210);

        eighteen.left = new TreeNode(300);
        eighteen.right = new TreeNode(310);

        twentyfour.left = new TreeNode(400);
        twentyfour.right = new TreeNode(410);

        thirthyone.left = new TreeNode(500);
        thirthyone.right = new TreeNode(510);

        fourthyfour.left = new TreeNode(600);
        fourthyfour.right = new TreeNode(610);

        sixtysix.left = new TreeNode(700);
        sixtysix.right = new TreeNode(710);

        ninty.left = new TreeNode(800);
        ninty.right = new TreeNode(810);

        return twentyfive;
    }

}
