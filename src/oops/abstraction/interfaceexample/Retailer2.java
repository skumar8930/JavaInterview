package oops.abstraction.interfaceexample;

public class Retailer2 implements WholeSaler{
    @Override
    public void textBooks() {
        System.out.println("Please provide all books of class VII");
    }

    @Override
    public void stationary() {
        System.out.println("Please provide 100  copy and 10 dozen pen");
    }
}
