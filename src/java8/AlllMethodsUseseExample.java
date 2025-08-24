package java8;

import list.EmployeeList;
import model.Employee;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AlllMethodsUseseExample {


    public static void main(String ss[]) {
        // useofFilter();
        // useOfmap();
        // useofFlatMap();
        //allEvenNumbers();
        // useOfSorting();
        //useOfToDistinct();
        // useOfMax();
        //useOfMin();
        //useOfReduce();
        //useOfCount();
        //useOfFindAny();
        //useOfCount();
        //useOfAllMatch();
        //useOfDropWhile();
        //useOfPeek();
       // useOfSkipAndFindFirst();
        // useOfLimit();
        //useOfMapMulti();
    }


    // Intermediate opeation example
    // filter, map,flatMap, sorted, count,distinct, peek,limit,skip

    public static void useofFilter() {
        // Return list of employee whose salary greater than 40000

        // EmployeeList.getEmployeeList().stream().filter(e->e.getSalary()>40000).forEach(System.out::println);
        Predicate<Employee> p1 = e -> e.getAge() < 40;
        Predicate<Employee> p2 = p1.and(e -> e.getDepartment().equals("IT"));
        EmployeeList.getEmployeeList().stream().filter(p2).forEach(System.out::println);

    }

    public static void useOfmap() {
        // get the list of salary of all employee after increasing the salary 500 each
        // EmployeeList.getEmployeeList().stream().map(e-> e.getSalary()+500).forEach(System.out::println);
// get the list of name of employee in upper case
        EmployeeList.getEmployeeList().stream().map(e -> e.getName().toUpperCase()).forEach(System.out::println);
    }


    public static void useofFlatMap() {
        // it is used to convert multidimention array to one dimention array
        List<Integer[]> list = Arrays.asList(new Integer[]{1, 2, 3}, new Integer[]{4, 5, 6});
        list.stream().flatMap(x -> Arrays.stream(x)).forEach(System.out::println);

    }

    public static void allEvenNumbers() {
        List<Integer> list = Arrays.asList(112, 458, 123, 1237, 8, 4, 3, 3, 4);
        //Given a list of integers, find out all the even numbers that exist in the list using Stream functions?
        // Arrays.asList(1,4,6,7,8,4,3).stream().filter(x->x%2==0).forEach(System.out::println);

        // 2. Given a list of integers, find out all the numbers starting with 1 using Stream functions?
        // list.stream().filter(x->x.toString().startsWith("1")).forEach(System.out::println);

        //3. How to find duplicate elements in a given integers list in java using Stream functions?
        HashSet set = new HashSet();
        // list.stream().filter(x-> !set.add(x)).forEach(System.out::println);

        // 4. Given the list of integers, find the first element of the list using Stream functions?
        // System.out.println( list.stream().findFirst().get());


        //5.  Given a list of integers, find the total number of elements present in the list using Stream functions

        // System.out.println(list.stream().count());

        //  6. Given a list of integers, find the maximum value element present in it using Stream functions?
        //  System.out.println(list.stream().max(Integer::compare).get());

    }

    static void useOfSorting() {
        //get employeeName in sorted order
        EmployeeList.getEmployeeList().stream().map(Employee::getName).sorted().toList().forEach(System.out::println);
        //sort in reverse order
        System.out.println("..............Employee name in reverse order...........");
        EmployeeList.getEmployeeList().stream().map(Employee::getName).sorted(Comparator.reverseOrder()).toList().forEach(System.out::println);

        System.out.println("............Sort employee by id...........");
        EmployeeList.getEmployeeList().stream().sorted(Comparator.comparing(Employee::getId)).forEach(System.out::println);

        System.out.println("..............Sort employee by id in reverse order...........");

        EmployeeList.getEmployeeList().stream().sorted(Comparator.comparing(Employee::getId).reversed()).forEach(System.out::println);

    }

    static void useOfToDistinct() {
        // get list of employee name having unique name
        EmployeeList.getEmployeeList().stream().map(Employee::getName).distinct().toList().forEach(System.out::println);

    }

    static void useOfMax() {
        // Get Employee having highest salary in IT department
        System.out.println(EmployeeList.getEmployeeList().stream().filter(employee -> employee.getDepartment().equals("IT"))
                .max(Comparator.comparing(Employee::getSalary)).orElse(null));
// get the second highest salary employee
        Employee highestSalaryEmployee = EmployeeList.getEmployeeList().stream().filter(employee -> employee.getDepartment().equals("IT"))
                .max(Comparator.comparing(Employee::getSalary)).orElse(null);

        System.out.println("Second Highest salary employee " + EmployeeList.getEmployeeList().stream().filter(employee -> employee.getDepartment().equals("IT"))
                .filter(employee -> !employee.getSalary().equals(highestSalaryEmployee.getSalary()))
                .max(Comparator.comparing(Employee::getSalary)).orElse(null));
        // use of sorting sort employee by salary
        //Consider situation when employee having same salary
        System.out.println("-------Using map---------------");
        List<Employee> secondHighestEmployees =
                EmployeeList.getEmployeeList().stream()
                        .collect(Collectors.groupingBy(Employee::getSalary))
                        .entrySet().stream()
                        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))//by salary
                        .skip(1) // skip highest salary
                        .findFirst()
                        .map(Map.Entry::getValue)
                        .orElse(List.of());
        System.out.println(secondHighestEmployees);

       /* int n = 3; // 3rd highest

        List<Employee> nthHighestEmployees =
                EmployeeList.getEmployeeList().stream()
                        .collect(Collectors.groupingBy(Employee::getSalary))
                        .entrySet().stream()
                        // sort salaries descending
                        .sorted(Map.Entry.<Double, List<Employee>>comparingByKey(Comparator.reverseOrder()))
                        .skip(n - 1) // skip top (n-1) salaries
                        .findFirst()
                        .map(Map.Entry::getValue) // get employees for that salary
                        .orElse(List.of()); // empty if not enough salaries*/


    }

    static void useOfSkipAndFindFirst() {
        // Find the third highest paying employee in IT department
        //Solution sort the employee list in reverse order and skip starting 2 then first one is the 3rd highest paying employee
        EmployeeList.getEmployeeList().stream().filter(e -> e.getDepartment().equals("IT")).sorted(Comparator.comparing(Employee::getSalary).reversed())
                .forEach(System.out::println);
//use TreeSet because it will remove duplicate salary and maintain sorted order
        /*EmployeeList.getEmployeeList().stream().filter(e-> e.getDepartment().equals("IT")).map(Employee::getSalary)
                .collect(Collectors.toCollection(()-> new TreeSet<Integer>(Comparator.reverseOrder()))).forEach(System.out::println);*/

        Optional<Integer> thirdHighestSalary = EmployeeList.getEmployeeList().stream().filter(e -> e.getDepartment().equals("IT")).map(Employee::getSalary)
                .collect(Collectors.toCollection(() -> new TreeSet<Integer>(Comparator.reverseOrder())))
                .stream().skip(2).findFirst();

       /* EmployeeList.getEmployeeList().stream()
                .filter(e -> e.getDepartment().equals("IT") && e.getSalary().equals(thirdHighestSalary.get()))
                .toList()
                .forEach(System.out::println);*/


        thirdHighestSalary.ifPresent(salary -> EmployeeList.getEmployeeList().stream()
                .filter(e -> e.getDepartment().equals("IT") && Double.compare(e.getSalary(), salary) == 0)
                .toList()
                .forEach(System.out::println));


    }

    static void useOfLimit() {
        // get second highest salary employee using limit
        Integer secondHighestPayingEmployeeSalary = EmployeeList.getEmployeeList().stream().filter(e -> e.getDepartment().equals("IT")).map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .toList().get(1);
        EmployeeList.getEmployeeList().stream().filter(e -> e.getDepartment().equals("IT") && e.getSalary().equals(secondHighestPayingEmployeeSalary))
                .toList().forEach(System.out::println);
    }

    static void useOfCount() {
        // find the number of employee in IT department
        Long numberOfITEmployee = EmployeeList.getEmployeeList().stream().filter(e -> e.getDepartment().equals("IT"))
                .count();
        System.out.println(numberOfITEmployee);
    }

    static void useOfFindAny(){
        // get any IT department employee
       /* findAny() is a terminal operation on a stream.
                It returns an Optional<T> with any one element from the stream.
                It’s useful when you don’t care which element you get —just need one that matches.*/
        EmployeeList.getEmployeeList().stream().filter(e-> e.getDepartment().equals("IT")).findAny()
                .ifPresent(System.out::println);
    }

  static void useOfAllMatch(){
        //get Employee salary greater than 30000 using filter
      EmployeeList.getEmployeeList().stream().filter(e-> e.getSalary()>30000)
              .forEach(System.out::println);

      //1. Return type
      //
      //filter() → returns a Stream (possibly with fewer elements).
      //
      //allMatch() → returns a boolean (true/false).

    boolean condition = EmployeeList.getEmployeeList().stream().allMatch(e-> e.getSalary()> 30000);
     /* allMatch() → all elements must satisfy condition.

              anyMatch() → at least one element satisfies condition.

      noneMatch() → no elements satisfy condition.*/


    }

}
