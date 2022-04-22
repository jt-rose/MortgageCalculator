package com.mortgagecalculator;

public class Mortgage {

    // constants
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    // mortgage properties
    final private int principal;
    final private float annualInterestRate;
    final private byte loanPeriodInYears;


    public Mortgage(int principal, float annualInterestRate,
                    byte loanPeriodInYears) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.loanPeriodInYears = loanPeriodInYears;
    }

    // getters
    public int getPrincipal() {
        return principal;
    }

    public int getNumberOfPayments() {
        return loanPeriodInYears * MONTHS_IN_YEAR;
    }

    public double getMonthlyPayment() {
        var numberOfPayments = getNumberOfPayments();
        // calculate monthly interest rate
        float monthlyInterestRate =
                (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;

        // calculate monthly mortgage
        return principal * (monthlyInterestRate *
                (Math.pow((1 + monthlyInterestRate),
                        numberOfPayments))) /
                ((Math.pow((1 + monthlyInterestRate),
                        numberOfPayments) - 1));
    }

    public double getMonthlyBalance(
            short numberOfPaymentsMade) {

        var numberOfPayments = getNumberOfPayments();

        // calculate monthly interest rate
        float monthlyInterestRate =
                (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;

        return principal * (Math.pow(1 + monthlyInterestRate,
                numberOfPayments) - Math.pow(1 + monthlyInterestRate,
                numberOfPaymentsMade)) / (Math.pow(1 + monthlyInterestRate,
                numberOfPayments) - 1);
    }

    public double[] getMonthlyBalanceOverTime() {

        var numberOfPayments = getNumberOfPayments();
        double[] monthlyBalanceOverTime = new double[numberOfPayments];
        for (short monthsSoFar = 1;
             monthsSoFar <= numberOfPayments;
             monthsSoFar++) {
            double balance = getMonthlyBalance(monthsSoFar);
            monthlyBalanceOverTime[monthsSoFar - 1] = balance;
        }
        return monthlyBalanceOverTime;
    }
}
