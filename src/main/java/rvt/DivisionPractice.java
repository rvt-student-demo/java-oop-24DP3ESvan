package rvt;

import java.util.Scanner;

public class DivisionPractice {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter the numerator: ");
                String numeratorInput = input.nextLine();

                // Iziet no programmas, ja ievada q vai Q
                if (numeratorInput.charAt(0) == 'q' ||
                    numeratorInput.charAt(0) == 'Q') {
                    break;
                }

                int numerator = Integer.parseInt(numeratorInput);

                System.out.print("Enter the divisor: ");
                int divisor = Integer.parseInt(input.nextLine());

                int result = numerator / divisor;

                System.out.println(numerator + " / " + divisor + " is " + result);
                System.out.println();

            } catch (ArithmeticException e) {
                System.out.println("You can't divide by 0");
                System.out.println();

            } catch (NumberFormatException e) {
                System.out.println("You entered bad data.");
                System.out.println("Please try again.");
                System.out.println();
            }
        }

        input.close();
    }
}