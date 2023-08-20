package strings;

/*
* Immutable class in java means once object created we can not change its content ie. value of field can not be change/
* Rule to create immmutable class
*
* 1.Make class as final so that no one can inherit it.
* 2.Make variable as final and private, since final variable can not be changed once assigned, and private variable is not accessible
  in other class
3.No setter method.
4.Make deep copy of object, it means if we have some reference type of variable then to assign values in Reference type variable
  create new ,object of reference type and copy the value of object in it so that if any change in old reference there will be no effect in newely
  created object values.
* Shalllow means original object.

* */

public final class ImmutableClassCuboid {
    private  final Integer  height;
    private final Rectangle rectangle;

    public ImmutableClassCuboid(Integer height, Rectangle rectangle) {
        this.height = height;
       Rectangle rectangle1= new Rectangle(rectangle.getLength(),rectangle.getWidth());/*It is deep copy, now referenced changed if any one will change
       in rectangele reference then there will be no effect in rectangle1 reference*/

        /*this.rectangle= rectangle;  if we will use this then if any one will change the object property
        then we will get wrong output
         */
        this.rectangle = rectangle1;
    }

   public  Integer volumeOfCuboid(){
        return rectangle.rectangleArea()*height;
   }

public static void main(String ss[]){

    Rectangle rectangle= new Rectangle(10,30);
    ImmutableClassCuboid cuboid= new ImmutableClassCuboid(20,rectangle);
    rectangle.setLength(20);/* here we are trying to chang length property but it is not changing because we make deep copy in costructor
    of ImmutableClassCuboid*?
   /* cuboid.height=30; Cannot assign a value to final variable 'height' ,
    It has been assigned its value by constructor since it is final so now we can not change it value.*/
       System.out.println(cuboid.volumeOfCuboid());

}




}
