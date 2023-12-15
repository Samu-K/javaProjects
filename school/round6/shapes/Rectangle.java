public class Rectangle implements IShapeMetrics {
    double height;
    double width;

    public Rectangle(double height, double width) { 
        this.height = height;
        this.width = width;
    }

    public String toString() {
        return "Rectangle with height " + df.format(this.height) + " and width " + df.format(this.width);
    }

    public String name() {
        return "rectangle";
    }

    public double area() {
        return this.height * this.width;
    }

    public double circumference() {
       return this.height*2 + this.width*2;
    }
}
