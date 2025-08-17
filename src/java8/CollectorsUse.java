package java8;

import list.EmployeeList;

import java.nio.file.attribute.UserPrincipal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Employee;

public class CollectorsUse {
    //https://www.amitph.com/java-8-stream-collectors/#:~:text=This%20tutorial%20covers%20examples%20of%20Java%208%20Stream,discuss%20various%20Collectors%20available%20in%20Java%20Streams%20API.

    public static void main(String ss[]) {
        //useOfToMap();
        //useOfToList();
        // useOfToSet();
        // useOfToCollection();
        //useOfGrouping();
        //useOfPartitionBy();
        //useofMaxBy();
        // useofMinBy();
        //useOfSummarizingDouble();
       //useOfSummingInt();

    }

    //TO-DO use of Comprator class methods
    static void useOfToMap() {
        //Build map using list using java 8 an sort in natural and reverse order

        EmployeeList.getEmployeeList().stream().collect(Collectors.toMap(Employee::getSalary, Employee::getName, (first, second) -> first))
                .entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
        //since map can not have duplicate key so (first,second)->first resolve the collision and pick any one key

        EmployeeList.getEmployeeList().stream().collect(Collectors.toMap(Employee::getName, Employee::getSalary))
                .entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);

        EmployeeList.getEmployeeList().stream().collect(Collectors.toMap(Employee::getName, Employee::getSalary))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);

        EmployeeList.getEmployeeList().stream().collect(Collectors.toMap(Employee::getName, Employee::getSalary))
                .entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);

        EmployeeList.getEmployeeList().stream().collect(Collectors.toMap(Employee::getName, Employee::getSalary))
                .entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(System.out::println);

    }

    static void useOfToList() {
        // EmployeeList.getEmployeeList().stream().toList().forEach(System.out::println);
        //OR
        EmployeeList.getEmployeeList().stream().collect(Collectors.toList()).forEach(System.out::println);

    }

    static void useOfToSet() {
        // get List of unique employee name in sorted order
        EmployeeList.getEmployeeList().stream()
                .map(Employee::getName)
                .collect(Collectors.toSet()).stream().sorted().forEach(System.out::println);

    }

    static void useOfToCollection() {
        EmployeeList.getEmployeeList().stream().map(Employee::getName)
                .collect(Collectors.toCollection(LinkedList::new)).forEach(System.out::println);
        //TreeSet
        //Sorted Order: Elements are stored in their natural order (ascending) or as defined by a custom comparator.
        //No Duplicates: TreeSet does not allow duplicate elements.
        System.out.println("------------Get Unique name in sorted order-------------------");
        EmployeeList.getEmployeeList().stream().map(Employee::getName)
                .collect(Collectors.toCollection(TreeSet::new)).forEach(System.out::println);

        EmployeeList.getEmployeeList().stream().map(Employee::getName)
                .collect(Collectors.toCollection(() -> new TreeSet<String>())).forEach(System.out::println);

        EmployeeList.getEmployeeList().stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(() -> new TreeSet<String>(Comparator.reverseOrder())))
                .forEach(System.out::println);
        //() -> new TreeSet<>() is a Supplier<TreeSet<String>>.
        //
        //It supplies a new TreeSet to collect the stream elements into.

        //A Supplier<T> is a functional interface in Java (from java.util.function) that represents a function
        // that supplies a value of type T,
        // without taking any input.
        //@FunctionalInterface
        //public interface Supplier<T> {
        //    T get();
        //}
        //eg.
        //Supplier<String> stringSupplier = () -> "Hello, World!";
        //System.out.println(stringSupplier.get());  // Output: Hello, World!

    }

    static void useOfGrouping() {
        //Find how many employee having same salary
        EmployeeList.getEmployeeList().stream().collect(Collectors.groupingBy(Employee::getSalary, Collectors.counting()))
                .entrySet().stream().filter(entry -> entry.getValue() > 1).forEach(System.out::println);
        //output
        //15000=2
        //25000=3
        //95000=4
        // hree salary is the key and counting how many times it is repeting

        // Find how many employee working in the same department

        System.out.println("-------Employee working in the same department----------");
        EmployeeList.getEmployeeList().stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
                .entrySet().forEach(System.out::println);

        System.out.println("-------Average salary of the Employee working in the same department----------");
        Map<String, Double> avgSalaryByDept = EmployeeList.getEmployeeList().stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        avgSalaryByDept.entrySet().forEach(System.out::println);
        //first argument of groupingBy method tell us by which parameter we have to make grouping
        // here make multiple group of Employee object with same department
        // so second parameter will do calculation what we want
        // if you want how many Employee object in each group then use counting
        //if you want average of salary of individual group then use averagingDouble method.

        // like the any operation we can perform on the all  groups


        // Get the senior employee by age of each group or department

        System.out.println("-------Get the senior employee by age of each group or department----------");
        Map<String, Optional<Employee>> seniorEmployeeOfEachDepartment =
                EmployeeList.getEmployeeList().stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparing(Employee::getAge))));

        seniorEmployeeOfEachDepartment.entrySet().stream().map(Map.Entry::getValue).map(employee -> employee.get().getId())
                .forEach(System.out::println);
    }

    static void useOfPartitionBy() {
        EmployeeList.getEmployeeList().stream().collect(Collectors.partitioningBy(employee -> employee.getAge() > 35)).entrySet()
                .forEach(System.out::println);
    }

    static void useOfMaxBy() {
        System.out.println(EmployeeList.getEmployeeList().stream().collect(Collectors.maxBy(Comparator.comparing(Employee::getAge))).get());
        //OR
        System.out.println(EmployeeList.getEmployeeList().stream().max(Comparator.comparing(Employee::getAge)).get());
    }

    static void useOfMinBy() {
        System.out.println(EmployeeList.getEmployeeList().stream().collect(Collectors.minBy(Comparator.comparing(Employee::getAge))).get());
        System.out.println(EmployeeList.getEmployeeList().stream().min(Comparator.comparing(Employee::getAge)).get());
    }

    static void useOfSummarizingDouble() {
        DoubleSummaryStatistics stats =
                EmployeeList.getEmployeeList().stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Average: " + stats.getAverage());
    }

    static void useOfSummingInt() {
        int totalAge = EmployeeList.getEmployeeList().stream()
                .collect(Collectors.summingInt(Employee::getAge));
        System.out.println(totalAge);

        //OR
        int totalAgee = EmployeeList.getEmployeeList().stream().mapToInt(Employee::getAge).sum();
        System.out.println(totalAgee);
    }

}
