class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Path length must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // First character must be '('
        if (grid[0][0] == ')') {
            return false;
        }

        // Last character must be ')'
        if (grid[m - 1][n - 1] == '(') {
            return false;
        }

        // balance can be from 0 to m+n
        boolean[][][] dp = new boolean[m][n][m + n];

        // Starting '(' gives balance = 1
        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Already initialized
                if (i == 0 && j == 0) {
                    continue;
                }

                for (int balance = 0; balance < m + n; balance++) {

                    // Current cell '('
                    if (grid[i][j] == '(') {

                        int prevBalance = balance - 1;

                        if (prevBalance < 0) {
                            continue;
                        }

                        // Come from TOP
                        if (i > 0 && dp[i - 1][j][prevBalance]) {
                            dp[i][j][balance] = true;
                        }

                        // Come from LEFT
                        if (j > 0 && dp[i][j - 1][prevBalance]) {
                            dp[i][j][balance] = true;
                        }
                    }

                    // Current cell ')'
                    else {

                       int prevBalance = balance + 1;

                       if (prevBalance >= m + n ){ 
                        continue;
                       }
                       // TOP
                       if (i > 0 && dp[i - 1][j][prevBalance]) {
                        dp[i][j][balance] = true;
                        }
                        // LEFT
                        if (j > 0 && dp[i][j - 1][prevBalance]) {
                         dp[i][j][balance] = true;
                         }
                         }
                }
            }
        }

        // At the end, balance must be 0
        return dp[m - 1][n - 1][0];
    }
}