package com.vardanian.brackets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckRightBrackets {

    /**
     * Finds the count of right brackets
     *
     * @param number count of open brackets, must be non-negative integer
     *               example: number = 3
     *               the count of right brackets expression is 5 («((()))», «(()())», «(())()», «()(())», «()()()»)
     */
    public int getCountRightBrackets(int number) {
        //create array for save count of right brackets res[i]
        int[] res = new int[number + 1];
        res[0] = 1;
        // find right brackets res[i] for i = 1.... n
         for (int i = 1; i <= number; ++i) {
            res[i] = 0;
            for (int j = 0; j < i; ++j) {
                res[i] += res[j] * res[i - 1 - j];
            }
        }
        return res[number];
    }

    public void inputNonNegativeInteger() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input = br.readLine();
            int inputNumber = Integer.parseInt(input);
            int rightBrackets = getCountRightBrackets(inputNumber);
            System.out.println("Count of right brackets is: " + rightBrackets);
        } catch (Exception e) {
            System.err.println("Input text does not non negative integer, please try again: ");
        }
    }

    public static void main(String[] args) {

        CheckRightBrackets check = new CheckRightBrackets();
        System.out.println("Please input non negative integer of open brackets: ");
        check.inputNonNegativeInteger();
    }
}
