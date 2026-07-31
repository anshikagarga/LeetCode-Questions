import java.util.*;

public class MinimumPushes {

    public static void main(String[] args) {

        String word = "xyzxyzxyzxyz";

        HashMap<Character, Integer> map = new HashMap<>();

        // Count frequency of each character
        for (char ch : word.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Store frequencies in a list
        List<Integer> freq = new ArrayList<>(map.values());

        // Sort frequencies in descending order
        Collections.sort(freq, Collections.reverseOrder());

        int answer = 0;

        // Calculate minimum pushes
        for (int i = 0; i < freq.size(); i++) {
            int cost = (i / 8) + 1;
            answer += freq.get(i) * cost;
        }

        System.out.println("Minimum Pushes = " + answer);
    }
}