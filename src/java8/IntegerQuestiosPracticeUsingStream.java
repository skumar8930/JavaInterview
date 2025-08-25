package java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegerQuestiosPracticeUsingStream {
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

    public static void main(String ss[]) {
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


}

