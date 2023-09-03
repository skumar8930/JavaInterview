package oops.Inheritance.superKeword;
public class Two extends One{
    int i;
    Two(int i, int j){
        super(i);
        this.i=j;
       System.out.println(" I am from Two Constructor Value of two is j ="+i);
    }
    public void show(){
        System.out.println(" I am from two's show method value of i="+super.i);
    }
}
