package strings;
import javax.swing.border.MatteBorder;
import java.util.regex.*;

public class PatternMatchingAlgorithms {

    /*The most common types of string matching algorithms include:

Naive String Matching: A straightforward approach that checks every position in the text to see if the pattern matches.

Time complexity: O(|T| * |P|) where |T| is the length of the text and |P| is the length of the pattern.

Knuth-Morris-Pratt (KMP) Algorithm: Uses a prefix table (also known as a partial match table) to avoid unnecessary comparisons and improves the efficiency of the search.

Time complexity: O(|T| + |P|).

Rabin-Karp Algorithm: Uses a hashing technique to hash the pattern and substrings of the text for a fast comparison.

Time complexity: Average O(|T| + |P|), but can degrade to O(|T| * |P|) in the worst case.

Boyer-Moore Algorithm: A heuristic approach that compares the pattern from right to left and skips over sections of the text based on mismatches.

Time complexity: O(|T| / |P|) in the best case.

Aho-Corasick Algorithm: Used for matching multiple patterns simultaneously by building an automaton from the patterns.

Time complexity: O(|T| + ∑m) where m is the total length of the patterns.*/
    public static void main(String[] args) {
        String text = "ABCABCABC";
        String pattern = "ABC";

        System.out.println("Searching for pattern '" + pattern + "' in text: '" + text + "'");

        // Call the naive string matching method
        naiveStringMatch(text, pattern);
        usingPatternClass(text,pattern);
    }

    static void usingPatternClass(String text, String pattern){
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        while (matcher.find()) {
            System.out.println("Found at index: " + matcher.start() + " - " + matcher.group());
        }

    }
    public static void naiveStringMatch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        // Loop through the text to check for matches
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            // Check if the pattern matches at position i in the text
            while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }

            // If the whole pattern is matched
            if (j == m) {
                System.out.println("Pattern found at index " + i);
            }
        }
    }

}
