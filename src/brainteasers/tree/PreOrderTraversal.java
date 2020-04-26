package brainteasers.tree;

import java.util.ArrayList;

public class PreOrderTraversal {
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

    private static ArrayList<Integer> preorderTraversal(TreeNode A) {
        ArrayList<Integer> list = new ArrayList<>();
        traversal(A, list);
        return list;
    }

    private static void traversal(TreeNode A, ArrayList<Integer> list) {
        if (A != null) {
            list.add(A.val);
            traversal(A.left, list);
            traversal(A.right, list);
        }
    }

    public static void main(String[] args) {
        TreeNode first = getTree(1);
        System.out.println(preorderTraversal(first));
    }

    private static TreeNode getTree(int rootVal) {
        TreeNode one = new TreeNode(rootVal);
        TreeNode two = new TreeNode(rootVal + 1);
        TreeNode three = new TreeNode(rootVal + 2);

        one.left = two;
        one.right = three;

        two.left = new TreeNode(rootVal + 3);
        two.right = new TreeNode(rootVal + 4);

        three.left = new TreeNode(rootVal + 5);
        three.right = new TreeNode(rootVal + 6);
        return one;
    }

}
