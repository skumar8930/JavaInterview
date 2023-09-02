package oops.abstraction.interfaceexample;

public class Main {
    public static void main(String ss[]){
      WholeSaler retailer1=  new Retailer1();
      System.out.println("I am Retailer first and my requirement is given below:- ");
      retailer1.textBooks();
      retailer1.stationary();
      System.out.println();
/*
            output:
            Please provide all books of class XI and XII
            Please provide 200 copy and 20 dozen pen and india maps
*/

        WholeSaler retailer2=  new Retailer2();
        System.out.println("I am Retailer second and my requirement is given below:- ");
        retailer2.textBooks();
        retailer2.stationary();
       /*
            output:
              Please provide all books of class VII
              Please provide 100  copy and 10 dozen pen
       */

    }
}
