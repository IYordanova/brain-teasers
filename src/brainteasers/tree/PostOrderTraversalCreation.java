package brainteasers.tree;

import java.util.ArrayList;
import java.util.Arrays;

public class PostOrderTraversalCreation {
    private static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        @Override
        public String toString() {
            return val + " -> " + (parent == null ? "null" : String.valueOf(parent.val));
        }
    }

    private static TreeNode createTree(int depth, TreeNode root) {
        if (depth <= 1) {
            return root;
        }
        root.left = new TreeNode();
        root.left.parent = root;
        root.right = new TreeNode();
        root.right.parent = root;

        createTree(depth - 1, root.left);
        createTree(depth - 1, root.right);

        return root;
    }

    private static int counter = 1;

    private static ArrayList<TreeNode> postorderTraversal(TreeNode A) {
        ArrayList<TreeNode> list = new ArrayList<>();
        counter = 1;
        traversal(A, list);
        return list;
    }

    private static void traversal(TreeNode A, ArrayList<TreeNode> list) {
        if (A != null) {
            traversal(A.left, list);
            traversal(A.right, list);
            A.val = counter;
            counter++;
            list.add(A);
        }
    }

    private static int[] solve(int depth, int[] indexes) {
        ArrayList<TreeNode> traversed = postorderTraversal(createTree(depth, new TreeNode()));
        ArrayList<Integer> results = new ArrayList<>();
        for (int i : indexes) {
            if (i <= 0 || i >= traversed.size()) {
                results.add(-1);
                continue;
            }
            results.add(traversed.get(i - 1).parent.val);
        }
        return results.stream().mapToInt(Integer::intValue).toArray();
    }



    public static void main(String[] args) {
        System.out.println(Arrays.toString(solve(3, new int[]{7, 3, 5, 1})));
        System.out.println(Arrays.toString(solve(5, new int[]{19, 14, 28})));
    }

}
