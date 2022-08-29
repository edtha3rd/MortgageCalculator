import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        // create an instance of the scanner to receive an input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to my mortgage calculator");
        System.out.print("How much would you like to borrow?: ");
        int principal = scanner.nextInt();
        System.out.print("Annual Interest Rate (%): ");
        double interestRate = scanner.nextDouble();
        System.out.print("Period (Years): ");
        int period = scanner.nextInt();
        calculate(principal, interestRate, period);

    }
    //function to calculate the mortgage
    private static void calculate(int principal, double interestRate, int period) {
        //calculate the monthly rate
        double monthlyRate = (interestRate / 100) / 12;
//        calculate the total number of payments throughout the course of the mortgage
        double numberOfPayments = period * 12;
        double numerator = monthlyRate * (Math.pow((1 + monthlyRate), numberOfPayments));
        double denominator = Math.pow((1 + (monthlyRate)), numberOfPayments) - 1;
        double result = principal * (numerator / denominator);
        //convert the result to local currency
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String asCurrency = currency.format(result);
//        display the final result
        System.out.println(asCurrency);

        {/*
            potential improvements:
            set constants for number of months in a year and percent
            always use constants to explain "magic" numbers
            final byte MONTHS_IN_YEAR = 12;
            use float instead of double for interest rate because its a small number
            input validation
        */}
    }
}
