package java8;

import list.EmployeeList;
import model.Employee;

import java.util.*;
import java.util.function.Predicate;

public class AlllMethodsUseseExample {


    public static void main(String ss[]){
       // useofFilter();
       // useOfmap();
       // useofFlatMap();
        allEvenNumbers();

    }






    // Intermediate opeation example
   // filter, map,flatMap, sorted, count,distinct, peek,limit,skip

    public  static void useofFilter(){
        // Return list of employee whose salary greater than 40000

       // EmployeeList.getEmployeeList().stream().filter(e->e.getSalary()>40000).forEach(System.out::println);
        Predicate<Employee> p1= e-> e.getAge()<40;
        Predicate<Employee> p2= p1.and(e->e.getDepartment().equals("IT"));
        EmployeeList.getEmployeeList().stream().filter(p2).forEach(System.out::println);

    }

    public static void useOfmap(){
       // get the list of salary of all employee after increasing the salary 500 each
        // EmployeeList.getEmployeeList().stream().map(e-> e.getSalary()+500).forEach(System.out::println);
// get the list of name of employee in upper case
        EmployeeList.getEmployeeList().stream().map(e-> e.getName().toUpperCase()).forEach(System.out::println);
    }


    public static void useofFlatMap(){
       // it is used to convert multidimention array to one dimention array
    List<Integer[]> list =    Arrays.asList(new Integer[]{1, 2, 3}, new Integer[]{4, 5, 6});
               list .stream().flatMap(x-> Arrays.stream(x)).forEach(System.out::println);

    }

public static void allEvenNumbers(){
   List<Integer> list= Arrays.asList(112,458,123,1237,8,4,3,3,4);
   //Given a list of integers, find out all the even numbers that exist in the list using Stream functions?
   // Arrays.asList(1,4,6,7,8,4,3).stream().filter(x->x%2==0).forEach(System.out::println);

   // 2. Given a list of integers, find out all the numbers starting with 1 using Stream functions?
   // list.stream().filter(x->x.toString().startsWith("1")).forEach(System.out::println);

    //3. How to find duplicate elements in a given integers list in java using Stream functions?
    HashSet set= new HashSet();
   // list.stream().filter(x-> !set.add(x)).forEach(System.out::println);

   // 4. Given the list of integers, find the first element of the list using Stream functions?
  // System.out.println( list.stream().findFirst().get());


  //5.  Given a list of integers, find the total number of elements present in the list using Stream functions

 // System.out.println(list.stream().count());

  //  6. Given a list of integers, find the maximum value element present in it using Stream functions?
    //  System.out.println(list.stream().max(Integer::compare).get());









}


}
