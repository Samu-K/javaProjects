/*
 * This program completes calculations given when executing the program
 * Supports addition, subtraction, multiplication and division
 * For simplicity all calculations should follow num1 method num2 format
 *      e.g 1 * 2, 2 ** 2, 2 / 2
 *      Logarithims should be written 5 log 2
 */

 public class Calc  {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Error: Invalid number of params");
        }

        float num1 = Float.parseFloat(args[0]);
        float num2 = Float.parseFloat(args[2]);
        String method = args[1];
        
        if (method.strip().equals("+")) {
            addition(num1,num2);
        } else if (method.strip().equals("-")) {
            subtraction(num1, num2);
        } else if (method.strip().equals("*")) {
            multiplication(num1, num2);
        } else if (method.strip().equals("/")) {
            division(num1, num2);
        }
    }

    public static void addition(float num1, float num2) {
        float total = num1 + num2;
        System.out.println("The sum of " + num1 + " and " + num2 + " is " + Float.toString(total));
    }
    
    private static void subtraction(float num1, float num2) {
        float total = num1 - num2;
        System.out.println("The subtraction of " + num1 + " and " + num2 + " is " + Float.toString(total));
    }

    private static void multiplication(float num1, float num2) {
        float total = num1 * num2;
        System.out.println("The multiple of " + num1 + " and " + num2 + " is " + Float.toString(total));
    }
    
    private static void division(float num1, float num2) {
        float total = num1 / num2;
        System.out.println("The division of " + num1 + " and " + num2 + " is " + Float.toString(total));
    }
}
