package com.mortgagecalculator;

import java.text.NumberFormat;

public class Mortgage {

    // constants
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    // mortgage properties
    final private int principal;
    final private float annualInterestRate;
    final private byte loanPeriodInYears;
    final private double monthlyPayment;


    public Mortgage(int principal, float annualInterestRate,
                    byte loanPeriodInYears) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.loanPeriodInYears = loanPeriodInYears;
        this.monthlyPayment = calculateMonthlyMortgagePayment();
    }

    private double calculateMonthlyMortgagePayment() {


        // calculate monthly interest rate
        float monthlyInterestRate =
                (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;

        // calculate total number of payments
        int numberOfPayments = loanPeriodInYears * MONTHS_IN_YEAR;

        // calculate monthly mortgage
        return principal * (monthlyInterestRate *
                (Math.pow((1 + monthlyInterestRate),
                        numberOfPayments))) /
                ((Math.pow((1 + monthlyInterestRate),
                        numberOfPayments) - 1));
    }

    private double calculateMonthlyBalance(
            short numberOfPaymentsMade) {

        // calculate monthly interest rate
        float monthlyInterestRate =
                (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;

        // calculate total number of payments
        int numberOfPayments = loanPeriodInYears * MONTHS_IN_YEAR;

        return principal * (Math.pow(1 + monthlyInterestRate,
                numberOfPayments) - Math.pow(1 + monthlyInterestRate,
                numberOfPaymentsMade)) / (Math.pow(1 + monthlyInterestRate,
                numberOfPayments) - 1);
    }

    public void displayMonthlyPayment() {
        // mortgage calculation
        String formattedMortgagePayment =
                NumberFormat.getCurrencyInstance().format(monthlyPayment);

        System.out.print("Monthly Mortgage Payment: ");
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.print("Monthly Payment: " + formattedMortgagePayment);

    }

    public void displayPaymentSchedule() {

        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        System.out.println("Year 1 Month 1: " +
                NumberFormat.getCurrencyInstance().format(principal) +
                " remaining");
        for (short totalMonths = 1;
             totalMonths <= loanPeriodInYears * MONTHS_IN_YEAR;
             totalMonths++) {
            double balance = calculateMonthlyBalance(totalMonths);
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
}
