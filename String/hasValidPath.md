# LeetCode 2267 — Check if There Is a Valid Parentheses String Path

## Intuition

Grid mein `(0,0)` se `(m-1,n-1)` tak sirf **Right** aur **Down** move kar sakte hain.

Har path ek parentheses string banata hai.

Valid parentheses string ke liye:

* `(` → balance `+1`
* `)` → balance `-1`
* Balance kabhi negative nahi hona chahiye.
* End par balance `0` hona chahiye.

Sirf `dp[i][j]` store karna enough nahi hai, kyunki same cell par different balances ke saath pahunch sakte hain.

Therefore, DP state mein **position + balance** store karna padega.

---

## DP State

```java
boolean[][][] dp = new boolean[m][n][m + n];
```

```text
dp[i][j][balance]
```

means:

> `(0,0)` se `(i,j)` tak koi valid path exist karta hai jiska current balance `balance` hai.

---

## Important Observations

### 1. Starting cell

Starting character `(` hona compulsory hai.

```java
if (grid[0][0] == ')') {
    return false;
}
```

Starting `(` ke baad:

```java
dp[0][0][1] = true;
```

---

### 2. Ending cell

Last character `(` nahi ho sakta.

```java
if (grid[m - 1][n - 1] == '(') {
    return false;
}
```

---

### 3. Path length

Path mein total cells:

```text
m + n - 1
```

Valid parentheses string ki length even honi chahiye.

Therefore:

```java
if ((m + n - 1) % 2 != 0) {
    return false;
}
```

---

# Tabulation Approach

Hum grid ko:

```text
Top
 ↓
Current
 ↑
Left
```

direction mein process karte hain.

Current cell `(i,j)` tak sirf do previous cells se aa sakte hain:

```text
(i-1, j)   // Top
(i, j-1)   // Left
```

---

## Transition

### Current character = `(`

`(` balance ko `+1` karta hai.

Agar current balance `balance` hai:

```text
previousBalance + 1 = balance
```

Therefore:

```text
previousBalance = balance - 1
```

Check:

```java
int prevBalance = balance - 1;
```

Then top/left:

```java
dp[i - 1][j][prevBalance]
dp[i][j - 1][prevBalance]
```

Agar koi true hai:

```java
dp[i][j][balance] = true;
```

---

### Current character = `)`

`)` balance ko `-1` karta hai.

Therefore:

```text
previousBalance - 1 = balance
```

So:

```text
previousBalance = balance + 1
```

Then:

```java
int prevBalance = balance + 1;
```

Check top/left.

---

## Balance Validity

Balance negative nahi ho sakta.

For `(` case:

```java
if (prevBalance < 0) {
    continue;
}
```

For `)` case:

```java
if (prevBalance >= m + n) {
    continue;
}
```

This prevents:

```text
ArrayIndexOutOfBoundsException
```

---

# Final Answer

End cell par balance exactly `0` hona chahiye:

```java
return dp[m - 1][n - 1][0];
```

---

# Complete Code

```java
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

        boolean[][][] dp = new boolean[m][n][m + n];

        // Starting '(' gives balance = 1
        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                for (int balance = 0; balance < m + n; balance++) {

                    if (grid[i][j] == '(') {

                        int prevBalance = balance - 1;

                        if (prevBalance < 0) {
                            continue;
                        }

                        // From top
                        if (i > 0 && dp[i - 1][j][prevBalance]) {
                            dp[i][j][balance] = true;
                        }

                        // From left
                        if (j > 0 && dp[i][j - 1][prevBalance]) {
                            dp[i][j][balance] = true;
                        }

                    } else {

                        int prevBalance = balance + 1;

                        if (prevBalance >= m + n) {
                            continue;
                        }

                        // From top
                        if (i > 0 && dp[i - 1][j][prevBalance]) {
                            dp[i][j][balance] = true;
                        }

                        // From left
                        if (j > 0 && dp[i][j - 1][prevBalance]) {
                            dp[i][j][balance] = true;
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}
```

---

# Complexity

There are:

```text
m × n
```

cells and up to:

```text
m + n
```

possible balance values.

### Time Complexity

```text
O(m × n × (m + n))
```

### Space Complexity

```text
O(m × n × (m + n))
```

---

# Pattern Recognition

When you see:

> Grid path + some changing property/state along the path

Think:

```text
Position + State
```

Here:

```text
Position = (i, j)
State = balance
```

Therefore:

```text
dp[i][j][balance]
```

---

# Key Takeaway

**The most important thing I learned from this problem:**

`dp[i][j]` is not always enough.

If reaching the same cell with different values of some variable can affect future decisions, that variable becomes part of the DP state.

Here:

```text
same cell + balance 1
same cell + balance 2
```

are different states.

So:

```text
2D Grid DP
      ↓
3D DP
      ↓
position + balance
```
