import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        // create an instance of the scanner to receive an input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to my mortgage calculator");
        // must be between 1k and 1M
        int principal = 0;
        double interestRate = 0.0;
        int period = 0;
        do {
            System.out.print("How much would you like to borrow? (between 1K and 1M): ");
            principal = scanner.nextInt();
        } while(principal < 1_000 || principal > 1_000_000 );
//        must be between 1 and 30
        do {
            System.out.print("Annual Interest Rate (1% - 30%): ");
            interestRate = scanner.nextDouble();
        } while (interestRate < 1 || interestRate > 30);
        do {
            System.out.print("Period (5 to 30Years): ");
            period = scanner.nextInt();
        } while(period < 5 || period > 30);
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
