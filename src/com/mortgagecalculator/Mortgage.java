package com.mortgagecalculator;

public class Mortgage {

    // constants
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public int getPrincipal() {
        return principal;
    }

    public byte getLoanPeriodInYears() {
        return loanPeriodInYears;
    }

    // mortgage properties
    final private int principal;
    final private float annualInterestRate;
    final private byte loanPeriodInYears;
    final private double monthlyPayment;
    private final MortgageReport mortgageReport = new MortgageReport(this);
    // final private double[] monthlyBalanceOverTime;


    public Mortgage(int principal, float annualInterestRate,
                    byte loanPeriodInYears) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.loanPeriodInYears = loanPeriodInYears;
        this.monthlyPayment = calculateMonthlyMortgagePayment();
    }

    public double calculateMonthlyMortgagePayment() {


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

    public double calculateMonthlyBalance(
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

    public double getMonthlyPayment() {
        return monthlyPayment;
    }
}
