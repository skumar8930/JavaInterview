package strings;

public class Rectangle {
    private Integer length;
    private Integer width;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
    public Rectangle(Integer length, Integer width) {
        this.length = length;
        this.width = width;
    }

    public Integer rectangleArea(){
        return length*width;
    }


}
