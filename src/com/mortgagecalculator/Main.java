package com.mortgagecalculator;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    // code along for Mosh Hamedani's Learn Java course
    // https://www.youtube.com/watch?v=eIrMbAQSU34

    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;


    public static void main(String[] args) {

        // get user inputs
        int principal = (int) readInputs("Principal: ", 1000, 1_000_000);
        float annualInterestRate = (float) readInputs("Annual Interest Rate:" +
                " ", 1, 30);
        byte loanPeriod = (byte) readInputs("Period (Years): ", 1, 30);

        displayMonthlyPayment(principal, annualInterestRate, loanPeriod);

        System.out.println();

        displayPaymentSchedule(principal, annualInterestRate, loanPeriod);
    }

    private static void displayMonthlyPayment(int principal,
                                              float annualInterestRate,
                                              byte loanPeriod) {
        // mortgage calculation:
        double monthlyPayment = calculateMonthlyMortgagePayment(principal,
                annualInterestRate, loanPeriod);
        String formattedMortgagePayment =
                NumberFormat.getCurrencyInstance().format(monthlyPayment);

        System.out.print("Monthly Mortgage Payment: ");
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.print("Monthly Payment: " + formattedMortgagePayment);

    }

    private static void displayPaymentSchedule(int principal,
                                               float annualInterestRate,
                                               byte loanPeriod) {

        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        System.out.println("Year 1 Month 1: " +
                NumberFormat.getCurrencyInstance().format(principal) +
                " remaining");
        for (short totalMonths = 1; totalMonths <= loanPeriod * MONTHS_IN_YEAR;
             totalMonths++) {
            double balance = calculateMonthlyBalance(principal,
                    annualInterestRate, loanPeriod, totalMonths);
            String currentBalance =
                    NumberFormat.getCurrencyInstance().format(balance);
            int currentYear = (totalMonths / MONTHS_IN_YEAR) + 1;
            int currentMonth = (totalMonths % MONTHS_IN_YEAR) + 1;

            System.out.println(
                    "Year " + currentYear + " Month " + currentMonth +
                            ":" +
                            " " + currentBalance + " " +
                            "remaining");
        }
    }

    public static double calculateMonthlyMortgagePayment(
            int principal,
            float annualInterestRate,
            byte loanPeriod) {


        // calculate monthly interest rate
        float monthlyInterestRate =
                (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;

        // calculate total number of payments
        int numberOfPayments = loanPeriod * MONTHS_IN_YEAR;

        // calculate monthly mortgage
        return principal * (monthlyInterestRate *
                (Math.pow((1 + monthlyInterestRate),
                        numberOfPayments))) /
                ((Math.pow((1 + monthlyInterestRate),
                        numberOfPayments) - 1));
    }

    public static double calculateMonthlyBalance(
            int principal,
            float annualInterestRate,
            byte loanPeriod,
            short numberOfPaymentsMade) {

        // calculate monthly interest rate
        float monthlyInterestRate =
                (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;

        // calculate total number of payments
        int numberOfPayments = loanPeriod * MONTHS_IN_YEAR;

        return principal * (Math.pow(1 + monthlyInterestRate,
                numberOfPayments) - Math.pow(1 + monthlyInterestRate,
                numberOfPaymentsMade)) / (Math.pow(1 + monthlyInterestRate,
                numberOfPayments) - 1);
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
            System.out.println("Please enter a value between " + min + " and" +
                    " " + max);
        }
    }
}
