package java8;

import list.EmployeeList;
import model.Employee;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupingExample {
    public static void main(String ss[]) {
        groupingBasic();
        // groupingExample();
        firstNonRepeated();

    }
//https://mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/
    //https://stackabuse.com/guide-to-java-8-collectors-groupingby/



/*
    1. Basic form
          Collectors.groupingBy(classifier)
    classifier → a function that takes an element and returns the "group key".
    Result: Map<K, List<T>>
👉 Example:
    Map<String, List<Integer>> grouped =
            nums.stream()
                    .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd"));
    Here:
    classifier = n -> n % 2 == 0 ? "even" : "odd"
    Output: Map<String, List<Integer>>

2. With a downstream collector
Collectors.groupingBy(classifier, downstream)
    classifier → grouping key
    downstream → another collector to apply on each group instead of just collecting into a List.
            👉 Example (counting how many numbers are in each group):
    Map<String, Long> groupedCount =
            nums.stream()
                    .collect(Collectors.groupingBy(
                            n -> n % 2 == 0 ? "even" : "odd",
                            Collectors.counting()
                    ));
    Result: {"even"=5, "odd"=5}

3. With map type + downstream collector
Collectors.groupingBy(classifier, mapFactory, downstream)
    classifier → key function
    mapFactory → which Map implementation to use (e.g., HashMap, TreeMap, LinkedHashMap).
    downstream → what to collect inside each group.
            👉 Example (store in a TreeMap and collect into a Set):
    Map<String, Set<Integer>> groupedSet =
            nums.stream()
                    .collect(Collectors.groupingBy(
                            n -> n % 2 == 0 ? "even" : "odd",
                            TreeMap::new,
                            Collectors.toSet()
                    ));


✅ Summary
    groupingBy(classifier) → groups into Map<K, List<T>>
    groupingBy(classifier, downstream) → groups and applies collector (e.g., counting, summing).
    groupingBy(classifier, mapFactory, downstream) → full control over map type + downstream collection.*/

    /*
    General Type Signature
The main method looks like this:
public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier)
Now let’s break it:
<T, K> → generic types
T = type of elements in the stream
K = type of the grouping key
Function<? super T, ? extends K> classifier
function that takes an element (T) and produces a key (K)
Return type:
Collector<T, ?, Map<K, List<T>>>
When used with collect(...), it produces a Map<K, List<T>>
Overloads and Their Types

1. Basic form
<T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier)
✅ Groups elements into a Map<K, List<T>>
2. With downstream collector
<T, K, A, D>
Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier,
                                      Collector<? super T, A, D> downstream)
D = result type of the downstream collector (e.g., Long for counting())
Result: Map<K, D>
👉 Example:

Map<String, Long> counts =
    nums.stream()
        .collect(Collectors.groupingBy(
            n -> n % 2 == 0 ? "even" : "odd",
            Collectors.counting()
        ));

Type → Map<String, Long>

3. With map type + downstream collector
<T, K, D, A, M extends Map<K, D>>
Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier,
                              Supplier<M> mapFactory,
                              Collector<? super T, A, D> downstream)

M = type of the resulting Map (HashMap, TreeMap, LinkedHashMap…)

D = type of the downstream result

👉 Example:

Map<String, Set<Integer>> result =
    nums.stream()
        .collect(Collectors.groupingBy(
            n -> n % 2 == 0 ? "even" : "odd",
            LinkedHashMap::new,
            Collectors.toSet()
        ));

Type → LinkedHashMap<String, Set<Integer>>
📌 Quick Comparison with partitioningBy

groupingBy → General, groups into many categories (K can be anything).
partitioningBy → Special case, only true/false keys.
Type: Map<Boolean, List<T>> or Map<Boolean, D>

⚡So in short:

groupingBy(f) → Map<K, List<T>>
groupingBy(f, downstream) → Map<K, D>
groupingBy(f, mapFactory, downstream) → M extends Map<K, D>
     */

    public static void firstNonRepeated() {

        String input = "Java articles are Awesome";

        Character result = input.chars() // Stream of String
                .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s))) // First convert to Character object and then to lowercase
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //Store the chars in map with count
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println(result);

    }


    public static void groupingExample() {

        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

        Map<String, Long> result = items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // System.out.println(result);

        Map<String, List<Employee>> employeeByDepartment = EmployeeList.getEmployeeList().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(employeeByDepartment);

    }

  static void  groupingBasic(){
        List<Integer> list = Arrays.asList(1, 5, 6, 9, 29, 47, 20, 50, 20);

        //29.From a list, find numbers that appear more than once.
        list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .filter(entry-> entry.getValue()>1).forEach(System.out::println);
        //30.Group numbers into two categories: even and odd.
        Function<Integer, String> key = n-> n%2==0?"even": "odd";

        list.stream().collect(Collectors.groupingBy(n->key.apply(n))).entrySet().forEach(System.out::println);
        list.stream().collect(Collectors.groupingBy(key::apply)).entrySet().forEach(System.out::println);
        list.stream().collect(Collectors.groupingBy(key)).entrySet().forEach(System.out::println);

    }


}
