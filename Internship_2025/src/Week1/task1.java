package Week1;

import java.util.Scanner;

public class task1 {

	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
		
		// prompt for two integers
		System.out.println("Enter the first integer: ");
        int num1 = sc.nextInt();
        
        System.out.println("Enter the second integer: ");
        int num2 = sc.nextInt();
        
        //prompt for a floating-point number
        System.out.println("Enter the floating-point number: ");
        float fnum = sc.nextFloat();
        
        //prompt for a single character
        System.out.println("Enter a single character: ");
        char ch = sc.next().charAt(0);
        
        //prompt for boolean value true/false
        System.out.println("Enter a boolean value true: ");
        boolean boolvalue = sc.nextBoolean();
        
        sc.nextLine();
        //prompt for the user's name
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        
        // display the output
        System.out.println("Sum of " + num1 + "and" + num2 + "is: " + (num1 + num2));
        System.out.println("differnce of " + num1 + "and" + num2 + "is: " + (num1 - num2));
        System.out.println("Product of " + num1 + "and" + num2 + "is: " + (num1 * num2));
        System.out.println(fnum + " multiplied by 2 is: " + (fnum * 2));
        System.out.println("The next character aftern" + ch + "is: " + (char)(ch + 1));
        System.out.println("The opposite of " + boolvalue + "is: " + (!boolvalue));
        System.out.println("Hello, " + name + "!");
        
        sc.close();
        
        }

}
