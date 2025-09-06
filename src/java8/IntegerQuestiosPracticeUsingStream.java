package java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntegerQuestiosPracticeUsingStream {

    //https://javacodepoint.com/logical-programs/java-stream-api-coding-interview-questions-on-integers/


    public static void main(String ss[]) {
        // basicQuestions();
        //realQuestions();
        advanceQuestions();

    }


    static void basicQuestions() {
        /*
   1.Given a list of integers, find the maximum and minimum values.
    2.Find the second-highest and second-lowest numbers in a list.
    3.From a list, remove all duplicate numbers.
    4.Count how many times each number occurs in a list.
    5.Calculate the sum of all numbers in a collection.
    6.Find the average value from a list of numbers.
    7.Extract all numbers greater than 10 from a list.
    8.Sort a list of integers in both ascending and descending order.
    9.Merge two lists of integers into one, ensuring there are no duplicates.
    10.From a list of integers, separate even and odd numbers into two different results*/

        List<Integer> list = Arrays.asList(1, 5, 6, 9, 29, 47, 20, 50, 20);
        System.out.println("----- 1.Given a list of integers, find the maximum and minimum values. ------------");
        list.stream().max((a, b) -> a.compareTo(b));
        list.stream().max(Integer::compareTo);
        System.out.println("Max Intger = " + list.stream().max(Integer::compare).get() + " Min Integer = " + list.stream().min(Integer::compare).get());


        System.out.println("----- 2.Find the second-highest and second-lowest numbers in a list. ------------");
        Integer secondHighestNumber = list.stream().sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .get();

        Integer secondLowestNumber = list.stream().sorted()
                .skip(1)
                .findFirst()
                .get();
        System.out.println("SecondHighestNumber= " + secondHighestNumber + " SecondLowestNumber= " + secondLowestNumber);

        System.out.println("----- 3.From a list, remove all duplicate numbers. ------------");
        list.stream().distinct().forEach(System.out::println);

        System.out.println("----- 4.Count how many times each number occurs in a list. ------------");

        list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
                .stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

        System.out.println("-----  5.Calculate the sum of all numbers in a collection. ------------");
        // lambda to convert Integer → int
        System.out.println("Sum of all the numbers is = " + list.stream().mapToInt(n -> n).sum());
        System.out.println("Sum of all the numbers is = " + list.stream().reduce(0, Integer::sum));
        System.out.println("Sum of all the numbers is = " + list.stream().reduce(0, (a, b) -> a + b));
        System.out.println("Sum of all the numbers is = " + list.stream().collect(Collectors.summarizingInt(Integer::intValue)).getSum());
        System.out.println("Sum of all the numbers is = " + list.stream().collect(Collectors.summarizingInt(a -> a)).getSum());
        System.out.println("Sum of all the numbers is = " + list.stream().collect(Collectors.summingInt(Integer::intValue)));
        System.out.println("-----   6.Find the average value from a list of numbers. ------------");
        System.out.println(list.stream().collect(Collectors.summarizingInt(Integer::intValue)).getAverage());
        System.out.println(list.stream().collect(Collectors.averagingDouble(Integer::intValue)));
        System.out.println(list.stream().mapToInt(Integer::intValue).average().getAsDouble());

        System.out.println("-----     7.Extract all numbers greater than 10 from a list. ------------");
        list.stream().filter(n -> n > 10).forEach(System.out::println);
        System.out.println("-----     8.Sort a list of integers in both ascending and descending order. ------------");
        list.stream().sorted().forEach(System.out::println);
        list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        System.out.println("----- 9.Merge two lists of integers into one, ensuring there are no duplicates. ------------");

        List<Integer> list2 = Arrays.asList(1, 5, 6, 9, 29, 47, 20, 50, 20);

        Stream.of(list, list2).flatMap(Collection::stream).distinct().forEach(System.out::println);

        List<Integer> merged = Stream.concat(list.stream(), list2.stream())
                .distinct().toList();

        System.out.println("Merged List: " + merged);


        System.out.println("-----------10.From a list of integers, separate even and odd numbers into two different results----");

        List<Integer> listOfEvenNumber = list.stream().filter(n -> n % 2 == 0).toList();

        List<Integer> listOfOddNumber = list.stream().filter(n -> n % 2 != 0).toList();
        System.out.println("ListOfEvenNumber = " + listOfEvenNumber + "ListOfOddNumber= " + listOfOddNumber);

    }

    static void intermediateQuestions() {
      /* Questions Covered:

           11. From a list of integers, find all prime numbers.
           12.Extract the first 5 even numbers from a collection.
           13.Convert all integers in a list into their squared values.
           14.From a list, calculate the total of all even numbers.
           15. Check if every number in a list is positive.
           16.Verify whether any negative number exists in a collection.
           17.From a list, calculate the product of all numbers.
           18.Identify the number that occurs the most frequently in a collection.
           19.Divide a list of integers into two groups based on whether they are even or odd.
           20.From a list of integers, determine the nth largest element.*/


    }

    static void advanceQuestions() {
       /*
         21.From a list, remove all numbers greater than 20.
         22.Given two lists of integers, find the numbers that are present in both.
         23.From two lists, identify numbers that appear in one list but not in the other.
         24.Reverse the order of elements in a list of integers.
         25.Given a sequence of numbers from 1 to N with one missing, find the missing number.
         26.Determine whether a list contains any duplicate values.
         27.From a list of integers, identify the longest consecutive sequence of numbers.
         28.Find all pairs of numbers in a list that add up to a specific target value.*/

        System.out.println("===========21.From a list, remove all numbers greater than 20.==================");
        List<Integer> list= new ArrayList<>(List.of(1, 5, 1, 6, 5, 9, 29, 9, 47, 20, 50, 20));
        list.stream().filter(n-> n<=20).forEach(System.out::println);
        //OR
        list.removeIf(n -> n <= 20);
        //System.out.println(list);

        System.out.println("===========  22.Given two lists of integers, find the numbers that are present in both.==================");
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list3 = Arrays.asList(3, 4, 5, 6, 7);
       /* List<Integer> list4= new ArrayList<>(List.of(1, 5, 1, 6, 5, 9, 29, 9, 47, 20, 50, 20));
        List<Integer> list5= new ArrayList<>(List.of( 29, 9, 47, 20, 50, 20));
        list4.retainAll(list5);
        System.out.println(list4);*/

        List<Integer> intersection = list1.stream()
                .filter(list3::contains)   // keep only elements also in list2
                .toList();
        System.out.println(intersection); // [3, 4, 5]

        System.out.println("=========== 23.From two lists, identify numbers that appear in one list but not in the other.==================");
        //find the number which is not in second
        //We want the symmetric difference → numbers present in one list but not in the other.
        list1.stream().filter(x->!list3.contains(x)).forEach(System.out::println);

        System.out.println("=========== 24.Reverse the order of elements in a list of integers.==================");
        System.out.println("=========== 25.Given a sequence of numbers from 1 to N with one missing, find the missing number.==================");
        List<Integer> full = Arrays.asList(1,2,3,4,5,6,7,8,9,10);     // 1..N
        List<Integer> present = Arrays.asList(1,2,3,5,6,7,8,9,10);   // missing 4

        int missing = full.stream()
                .filter(n -> !present.contains(n))
                .findFirst()
                .get();

        System.out.println("Missing: " + missing); // 4

        //OR
      //  int expectedSum= N*(N+1)/2;
        int expectedSum= 10*11/2;
        int actualSum = present.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Missing number is = "+ (expectedSum-actualSum));

        System.out.println("=============  26.Determine whether a list contains any duplicate values.=============");

        boolean isDuplicate=Stream.of(1,2,3,3).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().allMatch(entry->entry.getValue()<2);
        System.out.println(isDuplicate ? "No Duplicates":"Has duplicates");

        /*

✅ 1. Using Set size comparison (simplest)
import java.util.*;

public class DuplicatesCheck {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 2, 3);

        boolean hasDuplicates = nums.size() != new HashSet<>(nums).size();

        System.out.println(hasDuplicates ? "Has duplicates" : "No duplicates");
    }
}
✅ Output:

Has duplicates

✅ 2. Using Stream + distinct
import java.util.*;

public class DuplicatesCheckStream {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 2, 3);
        boolean hasDuplicates = nums.stream().distinct().count() != nums.size();
        System.out.println(hasDuplicates ? "Has duplicates" : "No duplicates");
    }
}

✅ 3. Using groupingBy + counting
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicatesCheckGrouping {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 2, 3);
        boolean hasDuplicates = nums.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count > 1);

        System.out.println(hasDuplicates ? "Has duplicates" : "No duplicates");
    }
}*/

        System.out.println("=========27.From a list of integers, identify the longest consecutive sequence of numbers.=========");

        /*Example 1:
List = [100, 4, 200, 1, 3, 2]
Consecutive sequences are:
[1, 2, 3, 4] ✅ length = 4
[100] length = 1
[200] length = 1
Answer = [1, 2, 3, 4] (longest consecutive run)

👉 Example 2:
List = [10, 5, 6, 3, 4, 20, 21, 22]
Consecutive sequences:
[3, 4, 5, 6] ✅ length = 4
[20, 21, 22] length = 3
[10] length = 1
Answer = [3, 4, 5, 6]

👉 Example 3:
List = [8, 1, 9, 2, 10, 3, 11]
Consecutive sequences:
[1, 2, 3] length = 3
[8, 9, 10, 11] ✅ length = 4
Answer = [8, 9, 10, 11]*/
        /* Algorithm
        * What this loop does:
For each number in the sorted list:
If currentGroup is empty → start a new group.
If the current number is 1 more than the previous → it's consecutive → add to current group.
Otherwise → current number breaks the sequence:
Add the current group to groups.
Clear currentGroup and start over with the new number.
* After the loop ends, you must manually add the last group, since it wouldn’t be added inside the loop.
        * */


      List<Integer> sortedList = Stream.of(10, 5, 6, 3, 4, 20, 21, 22).collect(Collectors.toSet()).stream().sorted().toList();
        ArrayList<List<Integer>> groups =  new ArrayList<>();
        List<Integer> currentGroup = new ArrayList<>();

        sortedList.forEach(System.out::println);
      for(int i=0; i<sortedList.size(); i++){
          int current = sortedList.get(i);
          if(currentGroup.isEmpty()){
              currentGroup.add(current);
          }
          else{
              int prev= sortedList.get(i-1);
              if(current == prev+1){
                  //create group
                  currentGroup.add(current);
              }
              else {
                  // new group need to be creatd add the current group in the groups list and clear the current group
                  //start creating new group
                  groups.add(new ArrayList<>(currentGroup));
                  currentGroup.clear();
                  currentGroup.add(current);
              }
          }
      }
        if (!currentGroup.isEmpty()) {
            groups.add(currentGroup);
        }
         groups.forEach(System.out::println);
      }



    static void realQuestions() {
        /*
        29.From a list, find numbers that appear more than once.
        30.Group numbers into two categories: even and odd.
        31.From a collection, find the top three highest numbers.
        32.Convert a list of integers into a mapping where each number is associated with its square.
        33. Find the first number in a list that does not repeat.
        34.Verify whether a list of integers is sorted in ascending order.*/

        List<Integer> list = Arrays.asList(1, 5, 1, 6, 5, 9, 29, 9, 47, 20, 50, 20);

        //29.From a list, find numbers that appear more than once.
        list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .filter(entry -> entry.getValue() > 1).forEach(System.out::println);
        //30.Group numbers into two categories: even and odd.
        Function<Integer, String> key = n -> n % 2 == 0 ? "even" : "odd";

        list.stream().collect(Collectors.groupingBy(n -> key.apply(n))).entrySet().forEach(System.out::println);
        list.stream().collect(Collectors.groupingBy(key::apply)).entrySet().forEach(System.out::println);
        list.stream().collect(Collectors.groupingBy(key)).entrySet().forEach(System.out::println);
        list.stream().collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd")).entrySet().forEach(System.out::println);


        //31.From a collection, find the top three highest numbers.
        System.out.println("============31.From a collection, find the top three highest numbers.==================");
        list.stream().sorted(Comparator.reverseOrder())
                .limit(3).forEach(System.out::println);

        System.out.println("============32.Convert a list of integers into a mapping where each number is associated with its square.==================");
        list.stream().map(x -> x * x).forEach(System.out::println);

        System.out.println("============33. Find the first number in a list that does not repeat.==================");
        //first non-repeting
        /*.

🔎 Default Behavior
If you just write:
Collectors.groupingBy(Function.identity(), Collectors.counting())
It will use a HashMap by default.
A HashMap does not preserve insertion order of keys.
So when you later do .entrySet().stream(), the order of elements may be shuffled, and you won’t reliably get the first non-repeating element.
✅ Why LinkedHashMap
LinkedHashMap preserves insertion order of keys.
That means the keys in the map appear in the same order as the elements were first encountered in the stream.
This lets you correctly pick the “first” non-repeating number.*/
        System.out.println(list.stream().collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())).entrySet()
                .stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).findFirst().get());
        System.out.println("============ Find the second number in sequence in a list that does not repeat.==================");

        System.out.println(list.stream().collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())).entrySet()
                .stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).limit(2).skip(1).findFirst().get());
        System.out.println("============ 34.Verify whether a list of integers is sorted in ascending order.==================");
        System.out.println(list.equals(list.stream().sorted().toList()));
        boolean isSorted = IntStream.range(0, list.size() - 1).allMatch(i -> list.get(i) <= list.get(i + 1));
        System.out.println(isSorted);
    }

}

