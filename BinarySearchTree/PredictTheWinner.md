# 486. Predict the Winner

## Problem Statement

You are given an integer array `nums`, where each element represents the score of a number.

Two players take turns picking a number from either the **left** or the **right** end of the array.

- Player 1 always starts first.
- Both players play optimally.
- Return `true` if Player 1 can win or tie; otherwise, return `false`.

---

# Pattern

- Dynamic Programming (DP)
- Recursion
- Memoization
- Game Theory
- Minimax

---

# Key Observation

Each player has only **two choices**:

1. Pick the leftmost number.
2. Pick the rightmost number.

Since the opponent also plays optimally, we must consider their best possible move.

Instead of calculating individual scores, we calculate the **score difference**.

```text
Score Difference = Player1 Score - Player2 Score
```

---

# State Definition

```java
solve(left, right)
```

Returns the **maximum score difference** the current player can achieve from the subarray:

```text
nums[left...right]
```

---

# Base Case

If only one element is left:

```text
left == right
```

Current player picks that number.

```java
return nums[left];
```

---

# Choices

## Choice 1 : Pick Left

Current player picks:

```text
nums[left]
```

Opponent now plays on:

```text
left + 1 ... right
```

Score Difference

```java
takeLeft = nums[left] - solve(left + 1, right)
```

---

## Choice 2 : Pick Right

Current player picks:

```text
nums[right]
```

Opponent now plays on:

```text
left ... right - 1
```

Score Difference

```java
takeRight = nums[right] - solve(left, right - 1)
```

---

# Transition

Choose the better option.

```java
return Math.max(takeLeft, takeRight);
```

---

# Memoization

DP State

```java
dp[left][right]
```

If already computed:

```java
if(dp[left][right] != null)
    return dp[left][right];
```

Otherwise:

```java
dp[left][right] = Math.max(takeLeft, takeRight);
```

---

# Algorithm

1. Create a DP table.
2. Start recursion from `(0, n-1)`.
3. If only one number remains, return its value.
4. Calculate both choices.
5. Store the best answer in DP.
6. Return whether the final score difference is greater than or equal to zero.

---

# Dry Run

```text
nums = [1,5,2]
```

Player 1

- Pick 1
- Pick 2

Both possibilities are explored recursively.

Each recursive call returns the best possible score difference.

Finally,

```text
solve(0,2)
```

returns

```text
Player1 Score - Player2 Score
```

If

```text
>= 0
```

Player 1 wins (or ties).

---

# Recursion Tree (Concept)

```text
solve(0,n-1)

            /                       \
 Pick Left                         Pick Right
     |                                 |
solve(1,n-1)                     solve(0,n-2)
     |                                 |
Opponent's Turn                  Opponent's Turn
```

Each player always chooses the move that maximizes their own advantage.

---

# Java Solution

```java
class Solution {

    Integer[][] dp;

    public boolean PredictTheWinner(int[] nums) {

        dp = new Integer[nums.length][nums.length];

        return solve(0, nums.length - 1, nums) >= 0;
    }

    private int solve(int left, int right, int[] nums) {

        if (left == right)
            return nums[left];

        if (dp[left][right] != null)
            return dp[left][right];

        int takeLeft = nums[left] - solve(left + 1, right, nums);

        int takeRight = nums[right] - solve(left, right - 1, nums);

        dp[left][right] = Math.max(takeLeft, takeRight);

        return dp[left][right];
    }
}
```

---

# Time Complexity

```text
O(N²)
```

There are `N × N` DP states.

Each state is solved only once.

---

# Space Complexity

```text
O(N²)
```

For DP table.

Recursion Stack

```text
O(N)
```

---

# Why Do We Subtract?

We calculate

```java
nums[left] - solve(...)
```

instead of

```java
nums[left] + solve(...)
```

because the recursive call returns the **opponent's best score difference**.

By subtracting it, we convert the opponent's advantage into the current player's perspective.

This is the core idea of **Game Theory DP**.

---

# Interview Questions

### Q1. Why do we subtract instead of add?

**Answer**

The recursive call represents the opponent's best advantage.

Subtracting converts the opponent's advantage into the current player's advantage.

---

### Q2. What does `solve(left,right)` return?

**Answer**

It returns the maximum score difference the current player can achieve from the subarray.

---

### Q3. Why do we use `Math.max()`?

**Answer**

The player always chooses the move that gives the maximum advantage.

---

### Q4. Why is DP needed?

**Answer**

Many subproblems repeat during recursion.

DP stores answers and avoids recomputation.

---

### Q5. Why use `Integer[][]` instead of `int[][]`?

**Answer**

`null` indicates that a state has not been computed yet.

Using `-1` may be ambiguous because `-1` can also be a valid score difference.

---

# Key Takeaways

- Think in terms of **score difference**, not individual scores.
- Every player makes the optimal move.
- State = `(left, right)`.
- Two choices: Pick Left or Pick Right.
- Use Memoization to optimize recursion.
- This is a classic **Game Theory + Dynamic Programming** problem.

---

# Related Problems

- 877. Stone Game
- 1140. Stone Game II
- 1406. Stone Game III
- 1690. Stone Game VII
- 1510. Stone Game IV

---