package com.mortgagecalculator;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    // code along for Mosh Hamedani's Learn Java course
    // https://www.youtube.com/watch?v=eIrMbAQSU34
    public static void main(String[] args) {

        // get user inputs
        int principal = (int) readInputs("Principal: ", 1000, 1_000_000);
        float annualInterestRate = (float) readInputs("Annual Interest Rate: ", 1, 30);
        byte loanPeriod = (byte) readInputs("Period (Years): ", 1, 30);

        // mortgage calculation:
        double monthlyPayment = calculateMonthlyMortgagePayment(principal, annualInterestRate, loanPeriod);

        System.out.print("Monthly Mortgage Payment: ");
        System.out.print(NumberFormat.getCurrencyInstance().format(monthlyPayment));
    }

    public static double calculateMonthlyMortgagePayment(int principal, float annualInterestRate, byte loanPeriod ) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        // calculate monthly interest rate
        float monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;

        // calculate total number of payments
        int numberOfPayments = loanPeriod * MONTHS_IN_YEAR;

        // calculate monthly mortgage
        return principal *
                (monthlyInterestRate * (Math.pow((1 + monthlyInterestRate), numberOfPayments)))
                / ((Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1));
    }

    public static double readInputs(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double userInput;
        while (true) {
            System.out.print(prompt);
            userInput = scanner.nextDouble();
            if (userInput >= min && userInput <= max) {
                return userInput;
            }
            System.out.println("Please enter a value between " + min + " and " + max);
        }
    }
}
