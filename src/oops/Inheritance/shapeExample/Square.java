package oops.Inheritance.shapeExample;

public class Square extends Shape {

    Square(Double length){
        super(length);
    }

    public void squareArea(){
        System.out.println("Area of Square is= "+length*length);// since length is protected so it will be available to all sub classes

    }

}
