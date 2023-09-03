package oops.Inheritance.TeacherStudentExample;

public class DriverClass {

    public static void main(String ss[]){
        Student student = new Student();// all accessible sutdent and Teacher both member
        Teacher student2 = new Student();// only Teacher member accessible , marks not available
        Teacher teacher  =new Teacher();// only teacher member accessible

    }


}
