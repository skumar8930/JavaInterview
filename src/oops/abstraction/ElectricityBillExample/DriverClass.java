package oops.abstraction.ElectricityBillExample;

public class DriverClass {

    public static void main(String ss[]){

      Plan domePlan=  new DomesticPlan();
      domePlan.setRate();
      System.out.println("Domestic Electricity bill");
      domePlan.calculateElectricityBill(100);
        // output :- Your Electricity Bill is 500 Rs.

      Plan commercialPlan =new CommercialPlan();
      commercialPlan.setRate();
      System.out.println("Commercial Electricity bill");
      commercialPlan.calculateElectricityBill(100);
      // output :- Your Electricity Bill is 1000 Rs.

      /*
      * here we are calling the same  method calculateElectricityBill(100) for commercial  and domestic
         and passing same unit=100.
      * but for domestic getting 500 bill and for commercial getting 1000 bill
      * how its happening it is because of abstraction, we have hide the implementation of setRate() method  from the driver class
      * driver class does not know its implementation  it is just creating object and passing units and getting output.
      *
      *
      * */

    }
}
