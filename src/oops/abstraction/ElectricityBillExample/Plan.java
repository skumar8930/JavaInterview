package oops.abstraction.ElectricityBillExample;

public abstract class Plan {
    protected Integer rate;
    public abstract void setRate();// impementing class will set the rate because commercial and domestic rate of unit is different


    public void calculateElectricityBill(Integer unit){
        // It is Concrete class because it is common to both commercial and domestic plan.
         System.out.println("Your Electricity Bill is "+rate*unit+" Rs.");
    }
}
