package brainteasers.tree;

import java.util.Stack;

public class KthSmallestNode {
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

    private static int kthSmallest(TreeNode A, int B) {
        Stack<TreeNode> stack = new Stack<>();

        while (A != null) {
            stack.push(A);
            A = A.left;
        }

        while (B != 0) {
            TreeNode n = stack.pop();
            B--;
            if (B == 0) {
                return n.val;
            }

            TreeNode right = n.right;
            while (right != null) {
                stack.push(right);
                right = right.left;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        TreeNode first = getTree(1);
        System.out.println(kthSmallest(first, 2));
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
