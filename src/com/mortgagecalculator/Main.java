package com.mortgagecalculator;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    // code along for Mosh Hamedani's Learn Java course
    // https://www.youtube.com/watch?v=eIrMbAQSU34
    public static void main(String[] args) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        // init scanner
        Scanner scanner = new Scanner(System.in);

        // get principal
        System.out.print("Principal: ");
        int principal = scanner.nextInt();

        // get annual interest rate
        System.out.print("Annual Interest Rate: ");
        float annualInterestRate = scanner.nextFloat();

        // get loan period in years
        System.out.print("Period (Years): ");
        int loanPeriod = scanner.nextInt();

        // calculate monthly interest rate
        float monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;

        // calculate total number of payments
        int numberOfPayments = loanPeriod * 12;

        // mortgage calculation:
        double monthlyPayment = principal *
                (monthlyInterestRate * (Math.pow((1 + monthlyInterestRate), numberOfPayments)))
                        / ((Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1));

        System.out.print("Monthly Mortgage Payment: ");
        System.out.print(NumberFormat.getCurrencyInstance().format(monthlyPayment));
    }
}
