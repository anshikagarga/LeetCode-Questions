# 📌 LeetCode Notes — 1614. Maximum Nesting Depth of the Parentheses

### 🔹 Pattern

**Parentheses / Balance / Counter**

### 🔹 Recognition

Jab question mein **nested parentheses ki maximum depth** poochhi ho:

```text
(       → +1 depth
)       → -1 depth
```

Maximum `depth` = answer.

---

### 🔹 Core Idea

```text
Current depth = kitne '(' abhi close nahi hue

'(' → depth++
')' → depth--

maxDepth = maximum depth reached
```

### 🔹 Important Observation

Initially stack ka thought aa sakta hai:

```text
( → push
( → push
( → push
```

But hume stack ke elements ki information nahi chahiye.

Sirf:

```text
stack.size()
```

chahiye.

Therefore:

```text
Stack → Counter
```

---

### 🔹 Example

```text
(1+(2*3)+((8)/4))+1
```

```text
(       → 1
(       → 2
)       → 1
(       → 2
(       → 3  ⭐ MAX
)       → 2
)       → 1
)       → 0
```

**Answer = 3**

---

### 🔹 Invariant ⭐

Har character process karne ke baad:

> `depth` = currently open/unmatched parentheses ki count.

Aur:

> `maxDepth` = ab tak dekhi gayi maximum `depth`.

---

### 🔹 Edge Cases

```text
"()"       → 1
"(())"     → 2
"((()))"   → 3
"1+2"      → 0
```

---

### 🔹 Complexity

```text
Time  → O(n)
Space → O(1)
```

---

### 🔹 Interview Takeaway 🧠

**Question:** Stack use karoge?

**Better answer:**

> A stack can solve it, but since we only need the current stack size and not the stored elements, we can optimize it to a counter and achieve O(1) extra space.

### 🔥 One-line Revision

```text
'(' → ++
')' → --
maximum depth → answer
```

**DSA Pattern:** `Stack → Counter Optimization`
