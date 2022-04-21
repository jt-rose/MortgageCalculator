package com.mortgagecalculator;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    // code along for Mosh Hamedani's Learn Java course
    // https://www.youtube.com/watch?v=eIrMbAQSU34


    public static void main(String[] args) {

        // get user inputs
        var principal = (int) UserInput.readInputs("Principal: ", 1000,
                1_000_000);
        var annualInterestRate = (float) UserInput.readInputs("Annual " +
                "Interest Rate:" +
                " ", 1, 30);
        var loanPeriod = (byte) UserInput.readInputs("Period (Years): ", 1,
                30);

        Mortgage.displayMonthlyPayment(principal, annualInterestRate,
                loanPeriod);
        Mortgage.displayPaymentSchedule(principal, annualInterestRate,
                loanPeriod);
    }


}
