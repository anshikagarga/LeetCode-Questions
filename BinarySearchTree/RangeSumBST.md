# 938. Range Sum of BST

## Problem Statement

Given the `root` of a Binary Search Tree (BST) and two integers `low` and `high`, return the sum of all node values in the range `[low, high]` (inclusive).

---

# Pattern

- Binary Search Tree (BST)
- Recursion
- Depth First Search (DFS)
- Tree Pruning

---

# Key Observation

A Binary Search Tree follows this property:

```text
        Root
       /    \
 Smaller    Greater
```

- Left subtree contains smaller values.
- Right subtree contains greater values.

Using this property, we can skip unnecessary subtrees.

---

# Cases

## Case 1

```text
root.val < low
```

Current node and all nodes in the left subtree are smaller than `low`.

So, ignore the left subtree.

```text
Go Right
```

---

## Case 2

```text
root.val > high
```

Current node and all nodes in the right subtree are greater than `high`.

So, ignore the right subtree.

```text
Go Left
```

---

## Case 3

```text
low <= root.val <= high
```

The current node belongs to the required range.

So,

- Add current node value.
- Traverse left subtree.
- Traverse right subtree.

---

# Algorithm

1. If root is null, return 0.
2. If root value is less than low, visit only the right subtree.
3. If root value is greater than high, visit only the left subtree.
4. Otherwise:
   - Add current node value.
   - Visit left subtree.
   - Visit right subtree.
5. Return the total sum.

---

# Dry Run

Tree

```text
        8
      /   \
     5     11
    / \      \
   3   6      20
```

```text
low = 5
high = 11
```

Traversal

```text
8  -> Included
5  -> Included
3  -> Not Included
6  -> Included
11 -> Included
20 -> Not Included
```

Answer

```text
8 + 5 + 6 + 11 = 30
```

---

# Recursion Flow

```text
solve(root)

│
├── root == null
│       ↓
│    return 0
│
├── root.val < low
│       ↓
│   return solve(root.right)
│
├── root.val > high
│       ↓
│   return solve(root.left)
│
└── Otherwise
        ↓
return root.val
     + solve(root.left)
     + solve(root.right)
```

---

# Java Solution

```java
class Solution {

    public int rangeSumBST(TreeNode root, int low, int high) {
        return solve(root, low, high);
    }

    private int solve(TreeNode root, int low, int high) {

        if (root == null) {
            return 0;
        }

        if (root.val < low) {
            return solve(root.right, low, high);
        }

        if (root.val > high) {
            return solve(root.left, low, high);
        }

        return root.val
                + solve(root.left, low, high)
                + solve(root.right, low, high);
    }
}
```

---

# Time Complexity

### Worst Case

```text
O(N)
```

Every node is visited once.

### Best Case

```text
O(log N)
```

When many subtrees are skipped due to BST pruning.

---

# Space Complexity

```text
O(H)
```

Where `H` is the height of the tree.

- Balanced BST → O(log N)
- Skewed BST → O(N)

---

# Why Does This Work?

We use the BST property to avoid visiting unnecessary nodes.

- If the current node is smaller than `low`, then its left subtree is also smaller.
- If the current node is greater than `high`, then its right subtree is also greater.

This optimization is called **Tree Pruning**.

---

# Interview Questions

### Q1. Which traversal is used?

**Answer:**
Depth First Search (DFS) using recursion.

---

### Q2. Why don't we visit the left subtree when `root.val < low`?

**Answer:**
Because every node in the left subtree is smaller than the current node, so none of them can fall inside the required range.

---

### Q3. Why don't we visit the right subtree when `root.val > high`?

**Answer:**
Because every node in the right subtree is greater than the current node, so none of them can fall inside the required range.

---

### Q4. What if the tree is a normal Binary Tree instead of a BST?

**Answer:**
We cannot skip any subtree.

We must visit every node.

Time Complexity becomes **O(N)**.

---

# Key Takeaways

- Use BST property effectively.
- Skip unnecessary recursion.
- Apply DFS using recursion.
- Tree pruning improves efficiency.
- Always think before traversing both subtrees.

---

# Related Problems

- 700. Search in a Binary Search Tree
- 530. Minimum Absolute Difference in BST
- 235. Lowest Common Ancestor of a BST
- 98. Validate Binary Search Tree
- 230. Kth Smallest Element in a BST

---