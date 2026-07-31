# 3016. Minimum Number of Pushes to Type Word II

## Difficulty
Medium

## Problem Link
https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii/

---

# Problem Statement

You are given a string `word`.

You can assign lowercase English letters to the keys of a phone keypad. Each key can contain at most **8 letters**.

The cost to type a letter depends on its position on the assigned key:

- 1st position → 1 push
- 2nd position → 2 pushes
- 3rd position → 3 pushes
- 4th position → 4 pushes

Return the **minimum number of pushes** required to type the given word.

---

# Observation

- Characters with higher frequency should require fewer pushes.
- We are free to assign letters to any key.
- Therefore, assign the most frequent letters to the lowest push cost.

---

# Approach

1. Count the frequency of each character.
2. Store all frequencies in a list.
3. Sort the frequencies in descending order.
4. The first 8 frequencies get cost = 1.
5. The next 8 frequencies get cost = 2.
6. Continue until all frequencies are processed.
7. Return the total cost.

---

# Algorithm

1. Create a HashMap to count the frequency of every character.
2. Traverse the string and update the frequency.
3. Store all frequency values in a list.
4. Sort the list in descending order.
5. Initialize `answer = 0`.
6. Traverse the frequency list.
7. Calculate

   cost = (index / 8) + 1

8. Add

   frequency × cost

   to the answer.
9. Return the answer.

---

# Dry Run

Input

word = "xyzxyzxyzxyz"

Frequency

x → 4

y → 4

z → 4

Sorted Frequency

[4, 4, 4]

Calculation

| Index | Frequency | Cost | Total |
|------:|----------:|-----:|------:|
| 0 | 4 | 1 | 4 |
| 1 | 4 | 1 | 4 |
| 2 | 4 | 1 | 4 |

Answer = 12

---

# Java Solution

```java
import java.util.*;

public class MinimumPushes {

    public static void main(String[] args) {

        String word = "xyzxyzxyzxyz";

        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : word.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        List<Integer> freq = new ArrayList<>(map.values());

        Collections.sort(freq, Collections.reverseOrder());

        int answer = 0;

        for (int i = 0; i < freq.size(); i++) {
            int cost = (i / 8) + 1;
            answer += freq.get(i) * cost;
        }

        System.out.println(answer);
    }
}
```

---

# Time Complexity

- Frequency Count → O(n)
- Sorting → O(26 log 26)
- Overall → O(n)

---

# Space Complexity

O(26) ≈ O(1)

---

# Interview Notes

### Why do we sort the frequencies?

To assign the minimum push cost to the most frequently used characters.

### Why `(i / 8) + 1`?

Because each push level can contain a maximum of **8 letters**.

- Index 0–7 → 1 push
- Index 8–15 → 2 pushes
- Index 16–23 → 3 pushes
- Index 24–25 → 4 pushes

### Data Structures Used

- HashMap
- ArrayList
- Collections.sort()

---

# Key Takeaways

- Greedy Algorithm
- Frequency Counting
- Sorting
- HashMap
- Interview Favorite Problem