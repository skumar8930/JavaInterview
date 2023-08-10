package java8;

import jdk.nio.mapmode.ExtendedMapMode;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PracticeOnEmployeeObject {
    public static void main(String ss[]){
        //countOfMaleAndFemaleEmployee();
        //departmentNames();
      //averageAgeOfMaleAndfemaleEmployee();
       //nameOfEmployeeAfter2015();
       // numberOfEmployeeIneachDepartment();
        //averageSalaryOfEmployeesInEachDepartment();
        //oldestEmployeesInOrganization();
       // averageAndTotalSalaryOfOrganization();
       // employeeListOfEachDeartment();
        seniorEmp();
    }

 //1. Find out the count of male and female employees present in the organization?

    public static void countOfMaleAndFemaleEmployee(){
          Predicate<Employee> malePredicat = e->e.getGender().equals("Male");
         Predicate<Employee> femalePredicat = e->e.getGender().equals("Female");
         System.out.println("Number of Male Employee= "+getEmployeeList().stream().filter(malePredicat).count());
         System.out.println("Numbder of female Employee= "+getEmployeeList().stream().filter(femalePredicat).count());

         //Second way by grouping
        //System.out.println(getEmployeeList().stream().collect(Collectors.groupingBy(Employee::getGender)));
        System.out.println(getEmployeeList().stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())));
    }


    //2. Write a program to print the names of all departments in the organization.
    public static void departmentNames(){
       // getEmployeeList().stream().map(e->e.department).forEach(System.out::println);
        //second way
        getEmployeeList().stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

    }

    //3. Find the average age of Male and Female Employees.

    public static void averageAgeOfMaleAndfemaleEmployee(){
     Long nummberOfMaleEmployee=   getEmployeeList().stream().filter(e->e.getGender().equals("Male")).count();
       // System.out.println("Average age of Male Employee= "+getEmployeeList().stream().filter(e->e.getGender().equals("Male")).map(Employee::getAge).reduce(0,(a,b)->a+b)/nummberOfMaleEmployee);
       //OR
       System.out.println("Average age of Male Employee= "+getEmployeeList().stream().filter(e->e.getGender().equals("Male")).map(Employee::getAge).reduce(0,Integer::sum)/nummberOfMaleEmployee);
    //similarly of female average
      //Second Method by grouping
       System.out.println(getEmployeeList().stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingLong(Employee::getAge))));

    }
//4. Get the Names of employees who joined after 2015.
    public static void nameOfEmployeeAfter2015(){

        getEmployeeList().stream().filter(e->e.yearOfJoining>2015).map(Employee::getName).forEach(System.out::println);
    }


//5. Count the number of employees in each department in sorted order.
    public static void numberOfEmployeeIneachDepartment(){

       getEmployeeList().stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting())).entrySet()
               .stream().sorted((a,b)->a.getValue().compareTo(b.getValue())).forEach(System.out::println);
    }

    //6. Find out the average salary of each department.

    public static void averageSalaryOfEmployeesInEachDepartment(){

       getEmployeeList().stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary))).entrySet().stream()
               .forEach(entry->System.out.println(entry.getKey()+" = "+entry.getValue()));

    }


//7. Find out the oldest employee, his/her age and department?
public static void oldestEmployeesInOrganization(){
  // Employee employee = getEmployeeList().stream().filter(emp->emp.age==getEmployeeList().stream().map(e->e.age).max((a,b)->a.compareTo(b)).get()).findAny().get();
  // System.out.println(employee.name+" "+employee.age+"  "+employee.department);

    //Second way

   Employee employee2= getEmployeeList().stream().max(Comparator.comparingInt(Employee::getAge)).get();
    System.out.println(employee2.name+" "+employee2.age+"  "+employee2.department);
}



//8. Find out the average and total salary of the organization.

    public static void  averageAndTotalSalaryOfOrganization(){
       Double totalSalary = getEmployeeList().stream().map(Employee::getSalary).reduce(Double::sum).get();
       //Double averageSalary= getEmployeeList().stream().collect(Collectors.averagingDouble(Employee::getSalary));
       //System.out.println("Total Salary= "+totalSalary+"  Average Salary = "+averageSalary);

       //Second method

        DoubleSummaryStatistics empSalary = getEmployeeList().stream().collect(Collectors.summarizingDouble(Employee::getSalary));
       /*empSalary.getAverage();
       empSalary.getCount();
       empSalary.getMax();
       empSalary.getMin();
       empSalary.getSum();*/
        System.out.println("Average Salary = "+empSalary.getAverage());
        System.out.println("Total Salary = "+empSalary.getSum());

    }

    //9. List down the employees of each department.

    public static void  employeeListOfEachDeartment(){
        getEmployeeList().stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.toList())).entrySet().stream().forEach(entry-> System.out.println("Department "+entry.getKey()+" Employees"+entry.getValue()));


    }

    //10. Find out the height of experienced employees in the organization

    public static void seniorEmp() {
        Optional<Employee> seniorEmp = getEmployeeList().stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();
          System.out.println(seniorEmp.get());
    }

    public static List<Employee> getEmployeeList(){

        List<Employee> employeeList = new ArrayList<Employee>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

   return employeeList;
    }

//References https://blog.devgenius.io/java-8-real-time-coding-interview-questions-and-answers-fce01f3c4080
}
