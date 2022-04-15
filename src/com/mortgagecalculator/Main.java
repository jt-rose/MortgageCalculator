package com.mortgagecalculator;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    // code along for Mosh Hamedani's Learn Java course
    // https://www.youtube.com/watch?v=eIrMbAQSU34
    public static void main(String[] args) {



        // init scanner
        Scanner scanner = new Scanner(System.in);

        // set up default values
        int principal;
        float annualInterestRate;
        byte loanPeriod;

        // get principal
        while (true) {
            System.out.print("Principal: ");
            principal = scanner.nextInt();
            if (principal >= 1000 && principal <= 1_000_000) {
                break;
            }
            System.out.println("Please enter a value between 1,000 and 1,000,000");
        }


        // get annual interest rate
        while (true) {
            System.out.print("Annual Interest Rate: ");
            annualInterestRate = scanner.nextFloat();
            if (annualInterestRate >= 1 && annualInterestRate <= 30) {
                break;
            }
            System.out.println("Please enter an annual interest rate between 1 and 30");
        }


        // get loan period in years
        while (true) {
            System.out.print("Period (Years): ");
            loanPeriod = scanner.nextByte();
            if (loanPeriod >= 1 && loanPeriod <= 30) {
                break;
            }
            System.out.println("Please enter a loan period between 1 and 30 years");
        }

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
}
