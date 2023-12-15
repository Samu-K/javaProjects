public class Circle implements IShapeMetrics{
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public String toString() {
        return "Circle with radius: " + df.format(this.radius);
    }
    
    public String name() {
        return "circle";
    }

    public double area() {
        return PI * (this.radius) * (this.radius);
    }

    public double circumference() {
        return 2*PI*this.radius;
    }

}
