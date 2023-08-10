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
    public static void main(String ss[]){
       // groupingExample();
        firstNonRepeated();

    }
//https://mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/
    //https://stackabuse.com/guide-to-java-8-collectors-groupingby/

    public static void firstNonRepeated(){

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


    public static  void groupingExample(){

        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

        Map<String, Long> result = items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

       // System.out.println(result);

        Map<String, List<Employee>> employeeByDepartment = EmployeeList.getEmployeeList().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(employeeByDepartment);

    }


}
