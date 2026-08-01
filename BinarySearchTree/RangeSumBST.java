package BinarySearchTree;

public class RangeSumBST {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

        /*
                 8
               /   \
              5     11
             / \      \
            3   6      20
        */

        TreeNode root = new TreeNode(8);

        root.left = new TreeNode(5);
        root.right = new TreeNode(11);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(6);

        root.right.right = new TreeNode(20);

        int low = 5;
        int high = 11;

        int sum = rangeSumBST(root, low, high);

        System.out.println("Range Sum = " + sum);
    }

    public static int rangeSumBST(TreeNode root, int low, int high) {

        return solve(root, low, high);
    }

    private static int solve(TreeNode root, int low, int high) {

        // Base Case
        if (root == null) {
            return 0;
        }

        // Skip Left Subtree
        if (root.val < low) {
            return solve(root.right, low, high);
        }

        // Skip Right Subtree
        if (root.val > high) {
            return solve(root.left, low, high);
        }

        // Current Node is in Range
        return root.val
                + solve(root.left, low, high)
                + solve(root.right, low, high);
    }
}