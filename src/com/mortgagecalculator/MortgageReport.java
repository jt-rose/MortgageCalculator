package com.mortgagecalculator;

import java.text.NumberFormat;

public class MortgageReport {
    private final Mortgage mortgage;
    private final NumberFormat currency;

    public MortgageReport(Mortgage mortgage) {
        this.mortgage = mortgage;
        this.currency = NumberFormat.getCurrencyInstance();
    }

    public void displayMonthlyPayment() {
        // mortgage calculation
        var formattedMortgagePayment =
                currency.format(mortgage.getMonthlyPayment());

        System.out.print("Monthly Mortgage Payment: ");
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.print("Monthly Payment: " + formattedMortgagePayment);

    }

    public void displayPaymentSchedule() {

        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        System.out.println("Year 1 Month 1: " +
                currency.format(mortgage.getPrincipal()) +
                " remaining");

        var monthlyBalanceOverTime = mortgage.getMonthlyBalanceOverTime();
        for (short totalMonths = 1;
             totalMonths <=
                     mortgage.getNumberOfPayments();
             totalMonths++) {
            double balance = monthlyBalanceOverTime[totalMonths - 1];
            String formattedBalance = currency.format(balance);
            int currentYear = (totalMonths / Mortgage.MONTHS_IN_YEAR) + 1;
            int currentMonth = (totalMonths % Mortgage.MONTHS_IN_YEAR) + 1;

            System.out.println(
                    "Year " + currentYear + " Month " + currentMonth +
                            ":" +
                            " " + formattedBalance + " " +
                            "remaining");
        }
    }
}