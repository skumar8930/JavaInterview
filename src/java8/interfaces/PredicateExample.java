package java8.interfaces;


import model.Employee;
import list.EmployeeList;

import java.util.List;
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String ss[]){
        predicateExample();

    }

    public static void predicateExample(){
        Predicate<Employee> p1= e-> e.getSalary()>20000;
        Predicate<Employee> p2= e-> e.getSalary()<40000;
        Predicate<Employee> p3= p1.and(p2);
        List<Employee> employeeList=  EmployeeList.getEmployeeList();
         employeeList.stream().filter(p3).forEach(System.out::println);//  fetching employees whose salary> 20000 and less than 30000
         System.out.println( p3.test( new Employee(203,"Sandeep",30,30000,"CS")));//use of test(), method

    }

}
