package asmt02Part03;

import java.util.Scanner;

/**
 * A class that determines whether or not a string is a palindrome;
 * that is, a string that's able to be spelled the same way from
 * left to right as it is right to left, ignoring punctuation,
 * spaces, and case.
 *
 * @author Frank M. Carrano
 * @author Joseph Erickson
 * @author Duc Ta
 * @version 4.0
 */
public class PalindromeChecker {


    /**
     * Tests whether a string is a palidrome, ignoring punctuation, spaces, and case.
     *
     * @param aString A string.
     * @return
     */
    public static boolean isPalindrome(String aString) {
        boolean isAPalindrome = true;
        StringBuilder temp = new StringBuilder("");
        StringBuilder tempString = new StringBuilder(cleanInput(aString));
        OurStack<String> stack = new OurStack<String>();

        try {

            for (int i = 0; i < tempString.length(); i++) {

                if (i + 1 >= tempString.length()) {

                    //Debug Print
                    //System.out.println(tempString.substring(i, i + 1));
                    //System.out.println(tempString.indexOf("Q"));

                    stack.push(tempString.substring(i));
                } else {
                    //Debug Print
                    //System.out.println(tempString.substring(i, i + 1));
                    //System.out.println(tempString.length());

                    stack.push(tempString.substring(i,i+1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; !stack.isEmpty(); i++) {
            if (stack.pop().equals(tempString.substring(i, i + 1))) {

            } else {
                isAPalindrome = false;
            }
        }
        /**
         while (!done && (continuingIndex < numChars)) {
         // Check whether the character at the top of the stack
         // is equal to the character at the current position in tempString.

         } // end while
         */
        if (isAPalindrome) {
            System.out.println(aString + " IS a palindrome");
        } else {
            System.out.println(aString + " is NOT a palindrome");
        }
        return isAPalindrome;
    } // end isPalindrome

    /**
     * Tests whether a character is a punctuation mark, such as a period.
     *
     * @param aCharacter The character to be tested.
     * @return True if the character is a punctuation mark, or false if not.
     */
    public static boolean isPunctuation(char aCharacter) {
        return false;
    } // end isPunctuation

    public static String cleanInput(String s) {
        String temp = s;
        temp = temp.replace(" ", "");
        temp = temp.replace(".", "");
        temp = temp.replace(",", "");
        temp = temp.replace("!", "");
        temp = temp.replace("?", "");
        return temp;
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean mainRunning = true;

        while (mainRunning) {
            System.out.println("Enter a string that you want to check (or enter ! to exit):");
            String userInput = keyboard.nextLine();

            if (userInput.equals("!")) {
                mainRunning = false;
                System.out.println("Done!");
            } else {
                isPalindrome(userInput);
            }


        }
    } // end main
} // end PalindromeChecker
