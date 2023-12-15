import java.util.Collections;
import java.util.ArrayList;

public class Median {
    public static void main(String args[]) {
        ArrayList<Float> nums = new ArrayList<Float>();

        for (String num : args) {
            nums.add(Float.parseFloat(num));      
        }

        Collections.sort(nums);

        Integer middle = args.length/2;
        Float median = 0.0F;

        if (args.length % 2 == 0) {
            Float first = nums.get(middle);
            Float second = nums.get(middle-1);

            median = (first + second)/2;
        } else {
            median = nums.get(middle);
        }
        
        System.out.println("Median: " + Double.toString(median));
    }
}
