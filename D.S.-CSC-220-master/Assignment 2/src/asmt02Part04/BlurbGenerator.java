package asmt02Part04;

import java.util.Random;

/**
 * @author JavaF
 */
public class BlurbGenerator {

    /**
     * Rules
     * Needs one X/Q pair
     * Must end on an X
     * <p>
     * X with 0-n y's
     * Q with z or d followed by an X
     */

    private final Random gen = new Random();
    private boolean firstRun = true;

    /**
     * Instantiates a random number generator needed for blurb creation.
     */
    public BlurbGenerator() {

    }

    //Also could do gen.nextInt(n) for a result of 0-(n-1)
    public int ranInt(int min, int max) {
        //Will make a number between the given ranges of the max and min
        return (int) (Math.random() * ((max - min) + 1) + min);
    }


    /**
     * Generates and returns a random Blurb. A Blurb is a Whoozit followed by
     * one or more Whatzits.
     *
     * @return
     */
    //The main method of the program
    public String makeBlurb() {

        //resets the first run var to true
        firstRun = true;

        //Will return the final Whoozit
        return makeWhoozit();


    }

    //If i = 10 then it has a 1 in 10 chance of being true (Theoretically)
    private boolean precentChance(int i) {
        //nextInt will return a number between 0-(n-1)
        //Checks to see if the int is equaled to 0;
        return gen.nextInt(i) == 0;
    }

    /**
     * Generates a random Whoozit. A Whoozit is the character 'x' followed by
     * zero or more 'y's.
     */
    private String makeWhoozit() {
        try {
            //Checks to see if this is the first run of the program
            if (firstRun) {

                //Sets the boolean to false so this will not be called again
                firstRun = false;

                //Will make sure to have at least 1 Whoozit and a Whatzit/Whoozit pair
                return "x" + makeYString() + makeWhatzit();

            } else {
                //A 50% chance that it will continue on and make a whatzit
                if (precentChance(2)) {
                    //Returns a whoozit and whatzit pair
                    return "x" + makeYString() + makeWhatzit();

                } else {
                    //Recursion will end and all the returns will run
                    //Will return one last Whoozit
                    return "x" + makeYString();
                }

            }

        } catch (StackOverflowError e) {

            //Will catch any StackOverFlow Errors
            e.printStackTrace();
            return " Stack overflow :'( ";
        } catch (Exception e) {

            //Will catch any other errors that might occur
            e.printStackTrace();
            return "R.I.P. Error";
        }

    }

    /**
     * Recursively generates a string of zero or more 'y's.
     */
    private String makeYString() {
        //Makes a new String to return and be appended too
        String result = "";

        //Makes a new int for looping
        int numLoops = ranInt(0, 6);

        //Adds 0-6 y's to the string
        while (numLoops > 0) {

            //Appends a y to the end of the String
            //A string builder would be more effective here
            result += "y";

            //Counts
            numLoops--;
        }

        //Returns the result of the while loop which is 0-6 y's
        return result;
    }


    /**
     * Recursively generates a string of one or more Whatzits.
     */
    //Never actually used. No need to use it.
    private String makeMultiWhatzits() {

        //Will just call makeWhatzit
        return makeWhatzit();
    }

    /**
     * Generates a random Whatzit. A Whatzit is a 'q' followed by either a 'z'
     * or a 'd', followed by a Whoozit.
     */
    private String makeWhatzit() {

        //50% chance of getting a true back
        if (precentChance(2)) {

            //Will return a qd and add on a whoozit
            return "qd" + makeWhoozit();
        }

        //else
        //Will return a qz and add on a whoozit
        return "qz" + makeWhoozit();
    }
}
