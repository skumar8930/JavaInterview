package oops.Inheritance.shapeExample;

public class Rectangle extends Square {
    private  Double width;
    Rectangle(Double length,Double width){
        super(length);
        this.width=width;
    }
    public void rectangleArea(){
        System.out.println("Area of rectangle is= "+length*width);
    }

}
