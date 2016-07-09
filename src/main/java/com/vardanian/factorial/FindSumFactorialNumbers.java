package com.vardanian.factorial;

import java.math.BigInteger;

public class FindSumFactorialNumbers {

    /**
     * It find the factorial number
     * according to algorithm  n! = n × (n − 1) × ... × 3 × 2 × 1
     * @param number type BigInteger
     */
    public BigInteger factorial(BigInteger number) {
        if (number.compareTo(BigInteger.valueOf(1)) == 0) {
            return number;
        } else {
            return number.multiply(factorial(number.subtract(BigInteger.valueOf(1))));
        }
    }

    /**
     * It calculates the sum of the numbers from 1 to n inclusive.
     *
     * @param num limit
     * @return sum
     */
    public long sum(BigInteger num) {
        String digits = num.toString();
        long sum = 0;
        for (int i = 0; i < digits.length(); i++) {
            sum += Integer.parseInt("" + digits.charAt(i));
        }
        return sum;
    }

    public static void main(String[] args) {
        FindSumFactorialNumbers findSumFactorialNumbers = new FindSumFactorialNumbers();
        System.out.println("Factorial number is: " + findSumFactorialNumbers.factorial(BigInteger.valueOf(100)));
        System.out.println("The sum of factorials number is: " + findSumFactorialNumbers.sum(findSumFactorialNumbers.factorial(BigInteger.valueOf(100))));
    }
}