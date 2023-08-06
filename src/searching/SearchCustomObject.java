package searching;
import list.EmployeeList;
import model.Employee;
import java.util.List;


public class SearchCustomObject {

    public static  void searchCustomObjectInList(){

        /* to search custom object we need to override equals() method of Object class in model class
        *
        * */
      List<Employee> employeeList =  EmployeeList.getEmployeeList();

        System.out.println( employeeList.contains(new Employee(203,"Sandeep",30,25000,"CS")));
        System.out.println("It is available at index = "+ employeeList.indexOf(new Employee(203,"Sandeep",30,25000,"CS")));

        //similarly search other object
        System.out.println( employeeList.contains( new Employee(206,"Deepak",45,21000,"IT")));
        System.out.println("It is available at index = "+ employeeList.indexOf( new Employee(206,"Deepak",45,21000,"IT")));


        // by using java-8
        System.out.println( employeeList.stream().filter(e-> e.getName().equals("Sandeep")).findAny().orElse(null));


    }







}
