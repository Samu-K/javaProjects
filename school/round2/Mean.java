public class Mean {
    public static void main(String args[]) {
        Double sum = 0.0;
        for (String num : args) {
            sum += Double.parseDouble(num);
        }
        Double avg = sum/args.length;
        System.out.print("Mean: " + avg);
    }
}
