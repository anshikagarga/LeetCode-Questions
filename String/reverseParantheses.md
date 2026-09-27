# LeetCode 1190 — Reverse Substrings Between Each Pair of Parentheses

### Pattern

**Nested Parentheses → Stack**

### Approach

Use `Stack<StringBuilder>` to store the string built **before entering a new pair of parentheses**.

* `(` → push `curr` into stack and start a new `StringBuilder`.
* `)` → reverse `curr`, pop the previous string, append reversed `curr` to it, and make the combined string the new `curr`.
* Normal character → append to `curr`.
* At the end → `curr.toString()`.

### Why Stack?

Parentheses can be nested, so we need the **most recently saved outer string first**.

**LIFO → Stack**

### Example

```text
Input:  (u(love)i)

u
↓
curr = "u"

(
↓
stack = ["u"]
curr = ""

love
↓
curr = "love"

)
↓
reverse("love") = "evol"
pop "u"
"u" + "evol" = "uevol"

i
↓
curr = "uevoli"

)
↓
reverse("uevoli") = "iloveu"
```

### Code

```java
class Solution {
    public String reverseParentheses(String s) {
        Stack<StringBuilder> st = new Stack<>();
        StringBuilder curr = new StringBuilder();

        for(char ch : s.toCharArray()) {

            if(ch == '(') {
                st.push(curr);
                curr = new StringBuilder();

            } else if(ch == ')') {
                curr.reverse();

                StringBuilder previous = st.pop();
                previous.append(curr);
                curr = previous;

            } else {
                curr.append(ch);
            }
        }

        return curr.toString();
    }
}
```

### Complexity

* **Time:** `O(n²)` worst case
* **Space:** `O(n)`

### Key Takeaway

```text
'(' → SAVE OLD CONTEXT
')' → REVERSE + RESTORE OLD CONTEXT
```

**Recognition:**
When a problem has **nested parentheses** and the inner part must be processed before returning to the outer part → think **STACK**.
