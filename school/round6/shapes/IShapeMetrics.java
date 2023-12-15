import java.text.DecimalFormat;

interface IShapeMetrics {

    public double PI = 3.14159;
    public static DecimalFormat df = new DecimalFormat("0.00");
    
    public abstract String name();

    public abstract double area();

    public abstract double circumference();

}
