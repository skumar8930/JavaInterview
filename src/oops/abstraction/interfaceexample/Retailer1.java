package oops.abstraction.interfaceexample;

public class Retailer1 implements WholeSaler{
    @Override
    public void textBooks() {
        System.out.println("Please provide all books of class XI and XII");
    }

    @Override
    public void stationary() {
        System.out.println("Please provide 200 copy and 20 dozen pen and india maps");
    }
}
