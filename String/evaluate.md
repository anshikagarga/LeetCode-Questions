# LeetCode 1807 — Evaluate the Bracket Pairs of a String

### Pattern

**String Traversal + HashMap**

### Problem Idea

String mein `(key)` format ke bracket pairs diye hote hain.

`knowledge` mein:

```text
key → value
```

mapping hoti hai.

Har `(key)` ko uske corresponding value se replace karna hai.

Agar key `knowledge` mein nahi hai → `"?"`

### Example

```text
s = "(name)is(age)yearsold"

knowledge:
name → bob
age  → two

Output:
"bobistwoyearsold"
```

---

## Approach

### 1. Build HashMap

`knowledge` ki har pair ko:

```java
map.put(key, value);
```

ke through store karo.

Example:

```text
knowledge = [["name","bob"], ["age","two"]]

map:
name → bob
age  → two
```

### 2. Traverse String

String ko left → right traverse karo.

#### Normal character

Agar:

```java
s.charAt(i) != '('
```

to directly result mein append karo.

#### Opening bracket `(`

* `i++` karo
* `)` tak characters read karo
* Un characters ko `key` mein build karo

Example:

```text
(name)

(
 ↓
name
 ↓
)
```

`key = "name"`

### 3. Lookup

Check:

```java
map.containsKey(key)
```

Agar key present:

```java
result.append(map.get(key));
```

Otherwise:

```java
result.append("?");
```

---

## Code

```java
class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        HashMap<String, String> map = new HashMap<>();

        // Build key-value mapping
        for(List<String> k : knowledge) {
            map.put(k.get(0), k.get(1));
        }

        StringBuilder result = new StringBuilder();

        // Traverse string
        for(int i = 0; i < s.length(); i++) {

            StringBuilder key = new StringBuilder();

            if(s.charAt(i) == '(') {

                i++;

                // Build key until ')'
                while(s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }

                // Replace key with value
                if(map.containsKey(key.toString())) {
                    result.append(map.get(key.toString()));
                } else {
                    result.append("?");
                }

            } else {
                result.append(s.charAt(i));
            }
        }

        return result.toString();
    }
}
```

---

## Dry Run

```text
s = "(name)is(age)yearsold"

knowledge:
name → bob
age  → two
```

### `(name)`

```text
key = "name"

map.containsKey("name") → true

result = "bob"
```

### `is`

```text
result = "bobis"
```

### `(age)`

```text
key = "age"

map.containsKey("age") → true

result = "bobistwo"
```

### `yearsold`

```text
result = "bobistwoyearsold"
```

### Final

```text
"bobistwoyearsold"
```

---

## Important Pointer Logic

When `(` is found:

```java
i++;
```

Then:

```java
while(s.charAt(i) != ')')
```

reads the complete key.

After the loop, `i` is already pointing at `)`.

Then the outer `for` loop increments `i` again and moves to the next character.

So **no separate `i++` is required after the `while`.**

---

## Complexity

Let:

* `N` = length of string `s`
* `K` = total characters in `knowledge`

### Time

**O(N + K)** average

* Building HashMap → `O(K)`
* Traversing string → `O(N)`
* HashMap lookup → `O(1)` average

### Space

**O(K + N)**

* HashMap → `O(K)`
* Result + temporary key → `O(N)`

---

## DSA Takeaway

### Recognition Pattern

**Need fast key → value lookup → HashMap**

**Need to scan a string and extract content between delimiters → String Traversal + StringBuilder**

### Mental Template

```text
knowledge
   ↓
HashMap
   ↓
Traverse string
   ↓
'(' → extract key until ')'
   ↓
HashMap lookup
   ↓
found → value
not found → ?
   ↓
StringBuilder result
```

### One-Line Revision

> **Build a HashMap for O(1) key lookup, scan the string, extract every `(key)`, and replace it with its mapped value or `?`.**
