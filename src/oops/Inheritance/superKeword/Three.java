package oops.Inheritance.superKeword;
public class Three extends Two{
    Three(int i, int j){
        super(i,j);
        System.out.println(" I am from Three Constructor");
    }
    public void show(){
        System.out.println(" I am from three show method");
        super.show();
    }
}
