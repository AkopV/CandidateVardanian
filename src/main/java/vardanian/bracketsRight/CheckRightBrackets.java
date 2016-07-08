package vardanian.bracketsRight;

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

    public static void main(String[] args) throws IOException {

        CheckRightBrackets check = new CheckRightBrackets();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please input non negative integer of open brackets: ");
            String input = br.readLine();
            int inputNumber = Integer.parseInt(input);
            int rightBrackets = check.getCountRightBrackets(inputNumber);
            System.out.println("Count of right brackets is: " + rightBrackets);
        } catch (NumberFormatException e) {
            System.err.println("Input text does not integer, please try again.");
        }catch (NegativeArraySizeException e) {
            System.err.println("Input text negative, please try again.");
        } catch (IOException e) {
            System.err.println("Error to write input text: " + e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.err.println("Error, the thread doesn't close");
            }
        }
    }
}
