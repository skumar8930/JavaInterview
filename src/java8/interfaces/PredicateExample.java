package java8.interfaces;


import model.Employee;
import list.EmployeeList;

import java.util.List;
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String ss[]){
        predicateExample();
        Predicate<Integer> p1= n-> n>10;// it is the definition of test() method of Predicate Functional interface it will return true or false
        Predicate<Integer> p2 = n-> n<20;
        Predicate<Integer> numberBetween10And20= p1.and(p2);
        //displayNumberBetween10And20(9 , n-> n>10 && n<20);
        //OR
        displayNumberBetween10And20(11, numberBetween10And20);


    }

    public static void predicateExample(){
        Predicate<Employee> p1= e-> e.getSalary()>20000;
        Predicate<Employee> p2= e-> e.getSalary()<40000;
        Predicate<Employee> p3= p1.and(p2);
        List<Employee> employeeList=  EmployeeList.getEmployeeList();
         employeeList.stream().filter(p3).forEach(System.out::println);//  fetching employees whose salary> 20000 and less than 30000
         System.out.println( p3.test( new Employee(203,"Sandeep",30,30000,"CS")));//use of test(), method

    }

    //Q1 display a number if number lies between 10 and 20 by using predicate

    public  static void displayNumberBetween10And20(Integer number, Predicate<Integer> predicate){
        if(predicate.test(number)){
            System.out.println(number);
        }
    }

    // Q2 OR Example, display string id length 5 or start With A.

    

}
