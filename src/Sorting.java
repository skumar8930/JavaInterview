import java.util.*;

public class Sorting {
    public static void sortEmployee(){
        List<Employee> employeeList = EmployeeList.getEmployeeList();
        //First method
       // employeeList.sort((e1,e2)-> e1.getId().compareTo(e2.getId()));//Since sort method have argument functional interface Comprator type and we have pass the definition of compare(o1,o2) method
        // by using lambda expression

        // Second method
      //  Collections.sort(employeeList,(e1,e2)->e1.getId().compareTo(e2.getId()));


        //Third method

        //employeeList.stream().sorted((e1,e2)->e1.getId().compareTo(e2.getId())).forEach(e->System.out.println(e));
          // we can also print it by method refrences
        employeeList.stream().sorted((e1,e2)->e1.getId().compareTo(e2.getId())).forEach( System.out::println );


       // employeeList.stream().forEach(System.out::println);

        // similarly we can sort by other field

    }






    public static void sortEmployeeHashMap(){
      Map<Integer,Employee> map=  EmployeeList.getEmployeeMap();
    //  Set<Map.Entry<Integer,Employee>> mapEntry= map.entrySet();
        List<Map.Entry<Integer,Employee>> mapEntry = new ArrayList<>(map.entrySet());
        //sort by value
      //  mapEntry.sort((entry1,entry2)->entry1.getValue().getId().compareTo(entry2.getValue().getId()));

        // sort by key

       // mapEntry.sort((entry1,entry2)->entry1.getKey().compareTo(entry2.getKey()));

        // sort in reverse order

        mapEntry.sort((entry1,entry2)->entry2.getKey().compareTo(entry1.getKey()));
        // we can also sort it by using Collections class and stream as above
        mapEntry.stream().forEach(System.out::println);



    }

}
