package BinarySearchTree;

public class PredictTheWinner {

    static Integer[][] dp;
    public static void main(String[] args) {

        int[] nums = {1, 5, 233, 7};

        boolean result = predictTheWinner(nums);

        System.out.println("Player 1 Wins: " + result);
    }

    public static boolean predictTheWinner(int[] nums) {

        dp = new Integer[nums.length][nums.length];

        return solve(0, nums.length - 1, nums) >= 0;
    }

    private static int solve(int left, int right, int[] nums) {

        // Base Case
        if (left == right) {
            return nums[left];
        }

        // Memoization
        if (dp[left][right] != null) {
            return dp[left][right];
        }

        // Choose Left
        int takeLeft = nums[left] - solve(left + 1, right, nums);

        // Choose Right
        int takeRight = nums[right] - solve(left, right - 1, nums);

        // Store Answer
        dp[left][right] = Math.max(takeLeft, takeRight);

        return dp[left][right];
    }
}
