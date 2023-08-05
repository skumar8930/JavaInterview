import java.util.Arrays;
import java.util.List;

public class EmployeeList {

    public List<Employee> getEmployeeList(){

        return Arrays.asList(

                new Employee(203,"Sandeep",30,25000,"CS"),
                new Employee(206,"Deepak",45,21000,"IT"),
                new Employee(207,"Amar",23,15000,"ME"),
                new Employee(208,"Preeti",34,25000,"IT"),
                new Employee(202,"Nitu",29,25000,"CS"),
                new Employee(200,"Rakesh",45,53000,"ME"),
                new Employee(301,"Ramesh",54,73000,"IT"),
                new Employee(305,"Vikash",25,45000,"CS"),
                new Employee(307,"Balgoving",21,55000,"ME"),
                new Employee(401,"Govind",43,65000,"IT"),
                new Employee(402,"Teena",23,85000,"CS"),
                new Employee(495,"Reena",34,95000,"ME"),
                new Employee(502,"Hany",54,15000,"EC"),
                new Employee(505,"Ravi",35,95000,"EC"),
                new Employee(950,"Umesh",37,95000,"IT"),
                new Employee(906,"Kiran",39,95000,"EC")

        );
    }
}
