package oops.abstraction.retailerexample;

public class AbstractClassDriver {
    public static void main(String []ss){
        WholeSaler retailer1= new Retailer1();
        retailer1.textBooks();
        retailer1.stationary();
        /*
        output-:
        Class X books
        Pens
         */


        // second Retailer

        WholeSaler retailer2= new Retailers2();
        retailer2.textBooks();
        retailer2.stationary();

        /*
           Output:-

          Class X books
          Note Books

        */


    }
}
