package java8.Lambda;

import java8.interfaces.CalculateSquare;
import java8.interfaces.CompareTwoNumber;
import java8.interfaces.MyInterface;

public class LambdaUese {
    //Implements CalculateSquare functional Interface
    public static void implementsCalculateSquare(){
       // CalculateSquare  c1= (x)->{ return x*x ;};
       // CalculateSquare c2 = (x)->  x*x;
        CalculateSquare c3 = x->  x*x;// here x is the value which we will pass in square method
        System.out.println(c3.square(2.0));
    }
    // Implements CompareTwoNumber functional Interface
    public static void implementcompareTwoNumber() {
        CompareTwoNumber compareTwoNumber =( a, b)->{
        if (a > b) {
            return a;
        } else
            return b;
    };//semicolon required because lambda expression is inline expression
        System.out.println(compareTwoNumber.compareTwoNumber(2,5));
    }


    public static void  useOfMyInterface() {

        // declare a reference to MyInterface
        // assign a lambda expression to the reference
        //Lambda expression is used to define the body of functional interface method , here not required to create new class
        MyInterface ref = str -> {
            String result = "";
            for (int i = str.length()-1; i >= 0 ; i--)
                result += str.charAt(i);
            return result;
        };

        // call the method of the interface
        System.out.println("Lambda reversed = " + ref.reverse("Lambda"));
    }
}
