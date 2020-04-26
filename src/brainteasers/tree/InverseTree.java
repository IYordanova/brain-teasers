package brainteasers.tree;

public class InverseTree {
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

    private static TreeNode invertTree(TreeNode A) {
        if (A == null) {
            return A;
        }
        inverse(A);
        return A;
    }

    private static void inverse(TreeNode node) {
        if (node.left == null && node.right == null) {
            return;
        }
        TreeNode tempNode = node.left;
        node.left = node.right;
        node.right = tempNode;

        if (node.left != null) {
            inverse(node.left);
        }
        if (node.right != null) {
            inverse(node.right);
        }
    }

    private static void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.val);
            traverseInOrder(node.right);
        }
    }

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        one.right = three;
        two.left = new TreeNode(4);
        two.right = new TreeNode(5);

        three.left = new TreeNode(6);
        three.right = new TreeNode(7);

        traverseInOrder(one);
        System.out.println();
        traverseInOrder(invertTree(one));
    }

}
