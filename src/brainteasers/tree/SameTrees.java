package brainteasers.tree;

public class SameTrees {
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

    private static int isSameTree(TreeNode A, TreeNode B) {
        if (A == null && B == null) {
            return 1;
        }
        if ((A == null && B != null) || (A != null && B == null)) {
            return 0;
        }
        if (A.val != B.val) {
            return 0;
        }

        return Math.min(isSameTree(A.left, B.left), isSameTree(A.right, B.right));
    }

    public static void main(String[] args) {
        TreeNode first = getTree(1);
        TreeNode second = getTree(1);
        TreeNode third = getTree(2);
        System.out.println(isSameTree(first, second));
        System.out.println(isSameTree(first, third));
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
