package com.mortgagecalculator;

import java.util.Scanner;

public class UserInput {

    final private static Scanner scanner = new Scanner(System.in);

    public static double readInputs(String prompt, double min, double max) {
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
