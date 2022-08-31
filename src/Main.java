import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    // constants
    final static int MONTHS_IN_YEAR = 12;
    final static int PERCENT = 100;
    public static void main(String[] args){
        // create an instance of the scanner to receive an input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to my mortgage calculator");
        // must be between 1k and 1M
        int principal;
        double interestRate;
        int period;
        do {
            System.out.print("How much would you like to borrow? (between 1K and 1M): ");
            principal = scanner.nextInt();
        } while(principal < 1_000 || principal > 1_000_000 );
//        must be between 1 and 30
        do {
            System.out.print("Annual Interest Rate (1% - 30%): ");
            interestRate = scanner.nextDouble();
        } while (interestRate < 1 || interestRate > 30);
        // period must be 5 to 30 years
        do {
            System.out.print("Period (5 to 30Years): ");
            period = scanner.nextInt();
        } while(period < 5 || period > 30);
        calculate(principal, interestRate, period);

    }
    //function to calculate the mortgage
    private static void calculate(int principal,
                                  double interestRate,
                                  int period) {

        //calculate the monthly rate
        double monthlyRate = (interestRate / PERCENT) / MONTHS_IN_YEAR;
//        calculate the total number of payments throughout the course of the mortgage
        int numberOfPayments = period * MONTHS_IN_YEAR;
        // cleaner to write the whole calculation as a single statement
        double numerator = monthlyRate * (Math.pow((1 + monthlyRate), numberOfPayments));
        double denominator = Math.pow((1 + (monthlyRate)), numberOfPayments) - 1;
        double result = principal * (numerator / denominator);
        System.out.println("Monthly Payments");
        System.out.println("----------------");
//        display the final result
        System.out.println(asCurrency(result));
        // calculate balance for each month
        getBalance(principal, monthlyRate, numberOfPayments);
        {/*
            potential improvements:
            set constants for number of months in a year and percent
            always use constants to explain "magic" numbers
            final byte MONTHS_IN_YEAR = 12;
            use float instead of double for interest rate because it is a small number
            input validation
        */}
    }
    public static void getBalance(int principal,
                                  double monthlyRate,
                                  int numberOfPayments){
        System.out.println("Remaining Balance");
        System.out.println("-----------------");
        // repeat calculating the balance for each month
        for (int i = numberOfPayments; i >= 0; i--) {
            // calculate the numerator
            double numerator = Math.pow((1 + monthlyRate), numberOfPayments)
            - Math.pow((1 + monthlyRate), (numberOfPayments - i));
//           calculate the denominator
            double denominator = Math.pow((1 + monthlyRate), numberOfPayments) - 1;
            double balance = principal * (numerator / denominator);
            System.out.println(asCurrency(balance));
        }
    }
    public static String asCurrency(double amount){
        //convert the result to local currency
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(amount);
    }
}
