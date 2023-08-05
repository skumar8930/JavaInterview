import java.util.*;

public class Sorting {
    public static void sortEmployeeList(){
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

    public static void sortArray(){
        /*
        * we can sort any Collection by 4 way
        * 1. by using List sort(Comprator c) method
        * 2.by using Collections class sort(List list, Comprator c) method
        * 3. by using stream sorted(Comprator c) method or sorted()
        * 4 . by writng won logic
        */

        Integer arr[] =  {1,4,6,8,0,45,78};
       // String arr[]  = {"Sandeep","Vivek", "Neetu","Mukesh"  };
        //Arrays.stream(arr).forEach(System.out::println);

        // Arrays.stream(arr).sorted().forEach(System.out::println);
       // Arrays.stream(arr).sorted(Comparator.reverseOrder()).forEach(System.out::println);

        //stream does not sort the actual arr , it just fetching all arr element and giving new array of sorted order

        // to sort element of arr reference use Collections
        Arrays.asList(arr).sort((a,b)->a.compareTo(b));
       // Collections.sort(Arrays.asList(arr),(a,b)->a.compareTo(b));
       // Collections.sort(Arrays.asList(arr),(a,b)->b.compareTo(a));
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void sortingWithoutUsingInbuildFunction(){
        Integer arr[] =  {1,4,6,8,0,45,78};
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    Integer temp= arr[i];
                    arr[i]= arr[j];
                    arr[j]= temp;
                }
            }

        }
        Arrays.stream(arr).forEach(System.out::println);

/*
* 1.Pikup first value i=0 , compare it to all next value i.e. j=0+1= 1 to it lenght-1
* perform swapping based on condition less than or greate than
*
* similarly for next i= 1 then j=1+1=2 to its length-1
*
*similarly for all i value.
* finally array will be sorted

 */
    }


}
