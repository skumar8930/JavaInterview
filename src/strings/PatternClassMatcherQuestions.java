package strings;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PatternClassMatcherQuestions {
    public static void main(String[] args) {
        /*
        Q1. write a program to find all occurance of given pattern(substring) in a string
         Q2.Given a text and a pattern, find the first occurrence of the pattern in the text.
         Example: text = "ABCDABCF", pattern = "ABC" → Output: 0.
         Q3.Count how many times a substring appears in a text.
          Example: text = "ABCABCABC", pattern = "ABC" → Output: 3.
         Q4.Find all starting indices of a substring in the text.
          Example: text = "ABCDABCFABC", pattern = "ABC" → Output: [0, 4, 8].
         Q5.Check if a string contains another string (without using String.contains).
         Basic Level

Q6.Find the last occurrence of a pattern in a text.
Example: text = "ABCDABCFABC", pattern = "ABC" → Output: 8.

Q7.Check if a string starts with a given pattern (without using startsWith).
Example: text = "HelloWorld", pattern = "Hello" → Output: true.

Q8.Check if a string ends with a given pattern (without using endsWith).
Example: text = "HelloWorld", pattern = "World" → Output: true.

Q9.Find the first non-overlapping occurrence of a pattern.
Example: text = "AAAAAA", pattern = "AA" → Output: 0.

10.Find all non-overlapping occurrences of a pattern.
Example: text = "AAAAAA", pattern = "AA" → Output: [0, 2, 4].

🔹 Intermediate Level

11.Find the longest prefix of a text which is also a suffix.
Example: text = "ABABAB" → Output: "ABAB".

12.Check if one string is a rotation of another using substring search.
Example: "ABCD", "CDAB" → Output: true.

13.Find the longest substring that is a palindrome.
Example: "babad" → Output: "bab" (or "aba").

14.Find the smallest window in text that contains all characters of the pattern.
Example: text = "ADOBECODEBANC", pattern = "ABC" → Output: "BANC".

15.Replace all occurrences of a substring with another substring (without using replace).
Example: text = "ABCABCABC", pattern = "ABC", replacement = "X" → Output: "XXX".

🔹 Advanced Level

16.Find all substrings of a text that are anagrams of a given pattern.
Two strings are called anagrams if they contain the same characters in the same(equal) frequency, but possibly in a different order.
"listen" and "silent" → ✅ anagrams
"race" and "care" → ✅ anagrams
"hello" and "ohlle" → ✅ anagrams
"abc" and "abbc" → ❌ not anagrams (different frequency of characters)
Example: text = "cbaebabacd", pattern = "abc" → Output: [0, 6].
tex= abbc and pattern = abbc is also anagram because frequecy of each char is equal

17.Find the longest repeated substring in a text.
Example: "banana" → Output: "ana".

18.Find the first index where two strings differ.
Example: "abcdef", "abcxef" → Output: 3.

19.Check if a pattern occurs in a circular version of the string.
Example: text = "ABCD", pattern = "DAB" → Output: true.

20.Find the number of distinct substrings in a text.
Example: "ABA" → Distinct substrings = {A, B, AB, BA, ABA} → Output: 5.


        * */


        String text = "ABCABCABC";
        String pattern = "ABC";
        // usingPatternClass(text,pattern);
        //usingPatternClassAndStream(text,pattern);
        //Q2FindFirstOccurance(text,pattern);
        // findAllOccurrenceNaive(text,pattern);
        //Q3CountHoumanyTimesAsubStringAppears(text, pattern);
        // Q5checkStringContainsSubStringWithoutusingContainMethod(text,pattern);
        // Q6FindLastoccuranceIndex(text,pattern);
        // Q7CheckIfStringStartWithGivenPatternWithoutUsigStartWithMethod(text, pattern);
        // Q9And10FindNonOverlapingOccurance();// NEXT Match after current match pattern length
        // Q11PrefixAndSuffixProblem();
        //printAllPossibleRotations();
        // printAllPossibleSubString();
        //printAllPossibleSubStringUsingJava8("ABC");
        //longestPalindromSubString();
        // longestPalindrome("babad");
        // replaceStringWithGivenPattern("ABCDABCDABCD", "AB","X");
        //replaceStringWithGivenPatternJava8Version("ABCDABCDABCD", "AB","X");
        //checkAllsubStringWhichAreAnagramOfAGivenString("cbaebabacd", "abc");
        findLongestRepetedSubString("banana"); //Example: "banana" → Output: "ana".
    }

    static void usingPatternClass(String text, String pattern) {
        //solution Q1
        // find the all auccurance of given pattenrn
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        while (matcher.find()) {
            System.out.println("Found at index: " + matcher.start() + " - " + matcher.group());
        }

    }

    static void usingPatternClassAndStream(String text, String pattern) {
        //solution Q1
        Matcher matcher = Pattern.compile(pattern).matcher(text);

        Stream.generate(() -> matcher.find() ? "Found at index: " + matcher.start() + " - " + matcher.group() : null)
                .limit(text.length()) // ensures termination
                .filter(Objects::nonNull) // equivalent to takeWhile((Objects::nonNull)
                .forEach(System.out::println);
        /*The problem

Stream.generate(...) produces an infinite stream.
But our supplier (() -> matcher.find() ? ... : null) will keep returning null forever after the last match.
Without a stopping condition, the stream would never terminate.
So we must cap it somehow.
🟢 Why .limit(text.length()) works
A string of length n can contain at most n matches (in the extreme case, if your pattern is 1 character long).
Example:
text = "AAAA" (length 4), pattern = "A"
Matches = 4 (equal to length).
Therefore, text.length() is a safe upper bound on the number of possible matches.
So even if Stream.generate keeps producing nulls, after n iterations the stream is forcibly stopped.
🔍 Example
Text: "ABCDABCFABC" (length = 11)
Pattern: "ABC"
First call → match at index 0 → "Found at index: 0 - ABC"
Second call → match at index 4 → "Found at index: 4 - ABC"
Third call → match at index 8 → "Found at index: 8 - ABC"
Next calls (up to 11) → return null
Now, .limit(11) ensures the stream stops after 11 iterations at most, instead of running infinitely.
.filter(Objects::nonNull) removes the nulls, leaving only the 3 actual matches.
⚖️ Alternative to .limit(text.length())

In Java 9+, you don’t need this trick because you can use takeWhile:

Stream.generate(() -> matcher.find() ? ... : null)
      .takeWhile(Objects::nonNull)
      .forEach(System.out::println);


But since you said Java 8, we don’t have takeWhile, so we need .limit(n) as a safe cutoff.

✅ So the role of .limit(text.length()) is:
👉 It’s just a safety cap so that our infinite stream doesn’t run forever after matches are exhausted.

Do you want me to show you a cleaner Java 8 workaround that avoids .limit(text.length()) altogether?*/
    }

    static void Q2FindFirstOccurance(String text, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        /*if (matcher.find()) {
            System.out.println("index" + matcher.start());
        }*/
        // using java 8
      /* String firstOccurance = Stream.generate(()-> matcher.find()? "Index= "+matcher.start(): null)
                .filter(Objects::nonNull)
                .findFirst().get();
        System.out.println(firstOccurance)*/
        ;

        //find second occurance
       /* String secondOccurance= Stream.generate(()-> matcher.find()? "Index= "+matcher.start(): null)
                .filter(Objects::nonNull)
                .skip(1)
                .findFirst().get();
        System.out.println(secondOccurance);*/

       /* String thirdOccurance= Stream.generate(()-> matcher.find()? "Index= "+matcher.start(): null)
                .filter(Objects::nonNull)
                .skip(2)
                .findFirst().get();
        System.out.println(thirdOccurance)*/
        ;
//All occurance
        Stream.generate(() -> matcher.find() ? "Index= " + matcher.start() + "=" + matcher.group() : null)
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    static void Q3CountHoumanyTimesAsubStringAppears(String text, String subString) {
        Matcher matcher = Pattern.compile(subString).matcher(text);
        int count = Stream.generate(() -> matcher.find() ? matcher.start() + "Index" : null)
                .takeWhile(Objects::nonNull).toList().size();
        System.out.println(count);
    }

    static void Q5checkStringContainsSubStringWithoutusingContainMethod(String text, String subString) {
        Matcher matcher = Pattern.compile(subString).matcher(text);
        if (matcher.find()) {
            System.out.println("contains");
        }
    }

    static void Q6FindLastoccuranceIndex(String text, String subString) {

        Matcher matcher = Pattern.compile(subString).matcher(text);
        int lastIndex = -1;
        while (matcher.find()) {
            System.out.println(matcher.start());
            lastIndex = matcher.start();
        }
        System.out.println("Last Index = " + lastIndex);
    }

    // 2. Without Regex (Naive Search)

    static int findLastOccurrenceIndexNaive(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int lastIndex = -1;
        for (int i = 0; i <= n - m; i++) {
            if (text.substring(i, i + m).equals(pattern)) {
                lastIndex = i; // update until the last match
            }
        }
        return lastIndex;
    }

    static void findAllOccurrenceNaive(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        ArrayList<Integer> allOccuranceIndex = new ArrayList<>();
        for (int i = 0; i <= n - m; i++) {
            if (text.substring(i, i + m).equals(pattern)) {
                allOccuranceIndex.add(i); // update until the last match
            }
        }
        allOccuranceIndex.forEach(System.out::println);
    }

    static void Q7CheckIfStringStartWithGivenPatternWithoutUsigStartWithMethod(String text, String pattern) {
        if (text.substring(0, pattern.length()).equals(pattern)) {
            System.out.println("start with " + pattern);
        }

        if (text.substring(text.length() - pattern.length()).equals(pattern)) {
            System.out.println("end with " + pattern);
        }
    }


    static void Q9And10FindNonOverlapingOccurance() {
        String text = "AAAAAA";
        String pattern = "AA";
        ArrayList<Integer> nonOverlapingIndex = new ArrayList<>();
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        while (matcher.find()) {
            nonOverlapingIndex.add(matcher.start());
            matcher.region(matcher.end(), text.length());
        }
        nonOverlapingIndex.forEach(System.out::println);
    }

    static void Q11PrefixAndSuffixProblem() {

        //Remark it is good question
        /*
 We are given a string.
We want to find the longest part of the beginning (prefix) of the string that is also present at the end (suffix) of the string.
⚠️ But it should not be the entire string itself — otherwise every string trivially matches itself.
🔹 Key Terms
Prefix = A substring that starts from index 0.
Example: "HELLO" → Prefixes: "H", "HE", "HEL", "HELL", "HELLO".
Suffix = A substring that ends at the last index.
Example: "HELLO" → Suffixes: "O", "LO", "LLO", "ELLO", "HELLO".
We need the longest string that appears in both lists.
🔹 Examples
Example 1
Text = "ABABAB"
Prefixes:
"A", "AB", "ABA", "ABAB", "ABABA", "ABABAB"
Suffixes:
"B", "AB", "BAB", "ABAB", "BABAB", "ABABAB"
Common: "AB", "ABAB", "ABABAB"
Longest (excluding full string): "ABAB"
✅ Output → "ABAB"
Example 2
Text = "AAAA"
Prefixes: "A", "AA", "AAA", "AAAA"
Suffixes: "A", "AA", "AAA", "AAAA"
Common: "A", "AA", "AAA", "AAAA"
Longest proper one: "AAA"
✅ Output → "AAA"
Example 3
Text = "ABC"
Prefixes: "A", "AB", "ABC"
Suffixes: "C", "BC", "ABC"
Common: "ABC" only.
But full string not allowed → ""
✅ Output → "" (no proper prefix-suffix)*/
        //String text="HELLO";
        String text = "ABABAB";
        List<String> prefixes = IntStream.range(0, text.length()).mapToObj(i -> text.substring(0, i + 1)).toList();
        //IntStream.range(start, end) goes upward only (from start to end-1).
        //IntStream.range(0,text.length()).mapToObj(i->text.substring(i)).forEach(System.out::println);
        // List<String> suffixes= IntStream.range(0,text.length()).mapToObj(text::substring).toList();
        // Suffixes in a Set for O(1) lookup searching is fast in HashSet than List because of hashing technique
        Set<String> suffixes = IntStream.range(0, text.length())
                .mapToObj(text::substring)
                .collect(Collectors.toSet());
        System.out.println(prefixes.stream().filter(str -> suffixes.contains(str) && str.length() < text.length())
                .max(Comparator.comparing(String::length)).orElse("No matches"));
    }
    //12.Check if one string is a rotation of another using substring search.
    //Example: "ABCD", "CDAB" → Output: true.
    /*You want to check if one string is a rotation of another.

Example: "ABCD" → rotate left by 2 → "CDAB". So "CDAB" is a rotation of "ABCD".
 ✅ Key Idea If s2 is a rotation of s1, then s2 must always be a substring of s1 + s1. Why? s1 = "ABCD". s1 + s1 = "ABCDABCD".
 All possible rotations of "ABCD" are hidden inside "ABCDABCD":
 "ABCD" (rotation by 0)
 "BCDA" (rotation by 1)
 "CDAB" (rotation by 2)
  "DABC" (rotation by 3)
  So just check: (s1.length() == s2.length()) && (s1 + s1).contains(s2)
  ✅ Java Code (Java 8 Style)
   public class Main { static boolean isRotation(String s1, String s2)
   { return s1.length() == s2.length() && (s1 + s1).contains(s2); }
   public static void main(String[] args) { System.out.println(isRotation("ABCD", "CDAB"));
   // true System.out.println(isRotation("ABCD", "ACBD")); // false } }*/

    static void printAllPossibleRotations() {
        String text = "ABCD";
        IntStream.range(0, text.length()).mapToObj(i -> text.substring(i) + text.substring(0, i)).forEach(System.out::println);
/*Output
* ABCD
BCDA
CDAB
DABC*/
    }

    /*// Find all possible sub string
    /*🔹 What is a Substring?
A substring is any continuous sequence of characters inside a string.
For a string of length n, the total number of substrings is = 𝑛 * (n+1) /2

Example:
"ABC" → substrings are:
A, B, C, AB, BC, ABC*/
    static void printAllPossibleSubString() {
        String text = "ABC";
        ArrayList<String> allSubString = new ArrayList<>();
        /*
        All possible combination with zero index(0,0), (0,1),(0,2),
        All possible  combination with one index (1,1) , (1, 2)
        All possible combinatio with two (2,2)
        * */
        int length = text.length(); // 3
        for (int i = 0; i < length; i++) // provides start index
        {
            for (int j = i + 1; j <= length; j++)// provides end index
            {
                allSubString.add(text.substring(i, j));//  reason of j<=length ? i included, j excluded (1,3) ->takes 1,to 2
            }
        }
        allSubString.forEach(System.out::println);


    }

    static void printAllPossibleSubStringUsingJava8(String text) {
        //String text = "ABC";
        /*
        All possible combination with zero index(0,0), (0,1),(0,2),
        All possible  combination with one index (1,1) , (1, 2)
        All possible combinatio with two (2,2)
        * */
        int length = text.length();
        IntStream.range(0, length)
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, length + 1).mapToObj(j -> text.substring(i, j))).forEach(System.out::println);

       /*✅ So, flatMap is necessary here because every i produces multiple substrings,
       and we want them all in one list*/
//boxed() converts primitive int values into Integer objects,
// which allows us to use the normal object-based Stream API (with flatMap, map, collect, etc.).
        //1. IntStream
        //
        //IntStream is a primitive stream specialized for int.
        //It has efficient operations but only works with int values, not objects.
        //
        //Example:
        //
        //IntStream.range(0, 3) → 0, 1, 2   // type: IntStream (int primitives) exclude end


    }

    static void longestPalindromSubString() {
        // Remark : very good question
        //13.Find the longest substring that is a palindrome.
        //Example: "babad" → Output: "bab" (or "aba").
        //1. steps find all sub string
        //2.step reverse all string
        // filter all palidrome string
        // find max length
        String text = "babad";
        String longestPalindromeSubString = allSubString(text).stream().filter(PatternClassMatcherQuestions::checkStringPalindrome)
                .max(Comparator.comparing(String::length)).orElse("Not Found");
        allSubString(text).stream().filter(PatternClassMatcherQuestions::checkStringPalindrome)
                .filter(s -> s.length() == longestPalindromeSubString.length()).forEach(System.out::println);
        //System.out.println(longestPalindromeSubString);

       /*That works ✅ but it’s not optimal because:
All substrings generation → O(n²) substrings.
Palindrome check for each → O(n) per substring.
Total complexity → O(n³) (too slow for big strings).*/
    }

    static Set<String> allSubString(String text) {
        int length = text.length();
        return IntStream.range(0, length).boxed().flatMap(i -> IntStream.range(i + 1, length + 1).mapToObj(j -> text.substring(i, j))).collect(Collectors.toSet());

    }


    static List<String> allSubStringWithDupicates(String text) {
        int length = text.length();
        return IntStream.range(0, length).boxed().flatMap(i -> IntStream.range(i + 1, length + 1).mapToObj(j -> text.substring(i, j))).toList();

    }

    static boolean checkStringPalindrome(String str) {
        return str.equals(Stream.of(str).map(s -> new StringBuilder(s).reverse().toString()).toList().get(0));
    }

/*    private static int[] expandAroundCenter(String text, int left, int right) {
    while (left >= 0 && right < text.length() && text.charAt(left) == text.charAt(right)) {
        left--;
        right++;
    }
    return new int[]{left + 1, right - 1};
}
🔹 What it does
This function finds the longest palindrome substring centered at (left, right) in the given text.
🔹 Step by Step
Input:
left and right define the "center" of a palindrome.
For odd length palindromes, left == right (a single char).
For even length palindromes, right = left + 1 (gap between two chars).
Expand outward:
java Copy code
while (left >= 0 && right < text.length() && text.charAt(left) == text.charAt(right))
Keep checking characters moving outward (left--, right++).
Stop when: Out of bounds, OR Characters don’t match anymore.
Return indices of palindrome:
At the end of the loop, we went one step too far (where palindrome broke).
So correct it by returning:-=   n       m mcjhjvvn  v                                            3363x of palindrome.
right - 1 → last index of palindrome.
🔹 Example 1: "abba" (even length)[[]]
/  ,b,=Call: java m,,model.Employee,mvm,vmvmvvc v v           vvcn mc nvccnnvfnmnvm ,mv,m,,m ,nmnmmnnvvnfguuu                                       bg pvfg;lt//- gCopy code'''''''''''''=["
expandAroundCenter("abba", 1, 2)
Start: left=1 ('b'), right=2 ('b') → match.

Expand: left=0 ('a'), right=3 ('a') → match.

Expand: left=-1 (out of bounds), stop.

Return: {0, 3} → substring "abba". ✅

🔹 Example 2: "racecar" (odd length)
Call:

java
Copy code
expandAroundCenter("racecar", 3, 3) // center at 'e'
left=3 ('e'), right=3 ('e') → match.

Expand: left=2 ('c'), right=4 ('c') → match.

Expand: left=1 ('a'), right=5 ('a') → match.

Expand: left=0 ('r'), right=6 ('r') → match.

Expand: left=-1 → stop.

Return: {0, 6} → substring "racecar". ✅*/

    static String expandAroundCenter(String text, int left, int right) {
        while (left >= 0 && right < text.length() && text.charAt(left) == text.charAt(right)) {
            left--;
            right++;
        }
        return text.substring(left + 1, right);

        /*🔹 Step-by-Step Expansion (babad)
        i = 0 → odd: "b", even: "" → longest "b".

        i = 1 → odd: "bab", even: "" → longest "bab".

        i = 2 → odd: "aba", even: "" → longest "bab" still.

        i = 3 → odd: "a", even: "" → longest "bab".

        i = 4 → odd: "d", even: "" → longest "bab".

        Output → "bab" (or "aba", depending on expansion order).

        ⚡ Key thing:

        Odd center → (i,i)

        Even center → (i,i+1)

        That’s the expand step 🚀*/
    }

    static String longestPalindrome(String text) {
     /*Alog
     * 1. check even centere of each char, compare left and right of while left and right chars are equal
     * if not equal then take the left +1  and right flag value and extract the subString(left,right); that is the palindrom string
     * 2. similarly for even palindrom here right = left+1
     * now compare odd and even palidromic string length which is greate take that in a current flag.
     * take string flag longest if current.length> longest.length then update longest= current;
     * return longest;
     *
     ie.
Iterate over each character in the string → this index will serve as the center.
For each center:
Odd-length palindrome:
* Set left = center, right = center.
Expand outward while left >= 0 && right < n && s[left] == s[right].
When expansion stops, the palindrome is s.substring(left+1, right).
Even-length palindrome:
Set left = center, right = center + 1.
Expand outward the same way.
When expansion stops, the palindrome is s.substring(left+1, right).
Compare the odd and even palindrome lengths for this center.
Let current = longer of the two.
Compare current with the longest palindrome found so far.
If current.length() > longest.length(), update longest = current.
After checking all centers, return longest.
     * */
        String longest = "";
        int length = text.length();
        for (int i = 0; i < length; i++) {
            String oddLengthPalidrom = expandAroundCenter(text, i, i);
            String evenLengthPalidrom = expandAroundCenter(text, i, i + 1);
            String current = oddLengthPalidrom.length() > evenLengthPalidrom.length() ? oddLengthPalidrom : evenLengthPalidrom;
            if (current.length() > longest.length()) {
                longest = current;
            }
        }
        System.out.println(longest);
        return longest;
    }


    //15.Replace all occurrences of a substring with another substring (without using replace).
    //Example: text = "ABCABCABC", pattern = "ABC", replacement = "X" → Output: "XXX".


    static String replaceStringWithGivenPattern(String text, String pattern, String replaceString) {
        String result = "";
        for (int i = 0; i < text.length(); ) {
            if (getAllOccuranceIndex(text, pattern).contains(i)) {
                result += replaceString;
                i = i + pattern.length();
            }
            result += text.charAt(i);
            i++;
        }
        System.out.println(result);
        return result;
    }

    static List<Integer> getAllOccuranceIndex(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        ArrayList<Integer> allOccuranceIndex = new ArrayList<>();
        for (int i = 0; i <= n - m; i++) {
            if (text.substring(i, i + m).equals(pattern)) {
                allOccuranceIndex.add(i); // update until the last match
            }
        }
        return allOccuranceIndex;
    }

    static String replaceStringWithGivenPatternJava8Version(String text, String pattern, String replaceString) {
        List<Integer> indices = getAllOccuranceIndex(text, pattern);
        int m = pattern.length();

        String result = IntStream.iterate(0, i -> i < text.length(), i -> {
                    // if current index is a match → jump pattern length
                    return indices.contains(i) ? i + m : i + 1;
                })
                .mapToObj(i -> indices.contains(i) ? replaceString : String.valueOf(text.charAt(i)))
                .collect(Collectors.joining());
        System.out.println(result);
        return result;
    }

    // ✅ Java 8 version of getAllOccuranceIndex
    static List<Integer> getAllOccuranceIndexJava8Version(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        return IntStream.rangeClosed(0, n - m)
                .filter(i -> text.substring(i, i + m).equals(pattern))
                .boxed()
                .collect(Collectors.toList());
    }

    static void checkAllsubStringWhichAreAnagramOfAGivenString(String text, String pattern) {
        allSubString(text).stream().filter(str -> str.length() == pattern.length()).map(str -> anagramString(str, pattern)).filter(Objects::nonNull).forEach(System.out::println);
        allSubString(text).stream().filter(str -> str.length() == pattern.length()).map(str -> anagramStringMethod2(str, pattern)).filter(Objects::nonNull).forEach(System.out::println);
        allSubString(text).stream().filter(str -> str.length() == pattern.length()).map(str -> anagramStringUsingSingleMapMethod(str, pattern)).filter(Objects::nonNull).forEach(System.out::println);
        // third one is more efficient
    }

    static String anagramString(String text, String pattern) {
        if (Stream.of(text.split("")).sorted().collect(Collectors.joining())
                .equals(Stream.of(pattern.split("")).sorted().collect(Collectors.joining()))) {
            return text;
        }
        return null;
    }

    static String anagramStringMethod2(String text, String pattern) {
        Map<String, Long> firstStringMap = Stream.of(text.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> secondStringMap = Stream.of(pattern.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (firstStringMap.equals(secondStringMap)) {
            return text;
        }
        return null;

       /*Complexity
This works fine, but note:
text.split("") + grouping creates a map for every substring.
If text is long, this can be costly (O(n^3 log n) worst case).
Faster methods exist (like sliding window frequency counts).*/
    }

    static String anagramStringUsingSingleMapMethod(String text, String pattern) {
        Map<Character, Integer> firstSringFrequencyMap = new HashMap<>();
        for (Character c : text.toCharArray()) {
            firstSringFrequencyMap.put(c, firstSringFrequencyMap.getOrDefault(c, 0) + 1);
        }
        for (Character c : pattern.toCharArray()) {
            if (!firstSringFrequencyMap.containsKey(c)) {
                return null;
                // if second string character is not found in first it means it is not anagram
            }
            // if character found the decrement its visit to ensure that frequency is same,  after last visit key value would be zero it means frequency is equal in both string
            firstSringFrequencyMap.put(c, firstSringFrequencyMap.get(c) - 1);
            // if key value becomes zero it means we have visited all frequency now remove it from map to ensure that map should empty after every frequency visited and decremented
            if (firstSringFrequencyMap.get(c) == 0) {
                firstSringFrequencyMap.remove(c);
            }
        }
        if (firstSringFrequencyMap.isEmpty()) {
            return text;
            // string are anagram
        }
        return null;// not anagram
    }

    static void findLongestRepetedSubString(String text) {//i.e. sub string which is repeting and having max length
        String maxLengthRepetingSubString = allSubStringWithDupicates(text).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey).max(Comparator.comparing(String::length)).get();
        System.out.println(maxLengthRepetingSubString);
    }
    // java 7
    /* static void findLongestRepeatedSubString(String text) {
        Map<String, Integer> freqMap = new HashMap<String, Integer>();

        // Generate all substrings
        for (int i = 0; i < text.length(); i++) {
            for (int j = i + 1; j <= text.length(); j++) {
                String sub = text.substring(i, j);
                Integer count = freqMap.get(sub);
                if (count == null) {
                    freqMap.put(sub, 1);
                } else {
                    freqMap.put(sub, count + 1);
                }
            }
        }

        String maxLengthRepeatingSubString = "";
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > 1) { // substring appears more than once
                String key = entry.getKey();
                if (key.length() > maxLengthRepeatingSubString.length()) {
                    maxLengthRepeatingSubString = key;
                }
            }
        }

        System.out.println(maxLengthRepeatingSubString);
    }*/


}


