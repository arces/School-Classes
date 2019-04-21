/**
 * Created by star on 9/9/2015.
 * @author - put your name here
 * @email - put your email address here
 * @studentid - put your studentid number here
 * @assignment.number - for example A19002
 * @version - I use a program version number and the date
 * @prgm.usage - for example, called directly from the operating system
 * @screenprint - <a href='pic.gif'>Screen Pring</a>
 * @link - https://sites.google.com/a/miramarsd.net/cisc190jc/course-overview/module-03/module-03---assignment
 * @link - http://pastebin.com/TasazZah

 *
 */




//imports

import java.io.*;
import java.util.*;

public class Assignment3 {

    //Declare Variables
    public static String srtInputFileName = "data\\fbin.txt";
    public static String srtOutputDirName = "data\\OUTPUT.txt";
    public static File textfile = new File(srtInputFileName);

    public static void main(String[] args) {
        //Checks to see if the file exists
        if (textfile.exists()) {
            //Tries the "risky" code here
            try {
                //opens and reads the file file
                Scanner scan = new Scanner(textfile);
                //Displays headings
                String strRecord;

                //Makes two vars outside the loop to be used inside the while()
                String A = "A";
                int temp = 0;

                //Makes a new PrintWriter so it can write to the OUTPUT file
                PrintWriter pw = new PrintWriter(srtOutputDirName);

                //Checks to see if there is a next line in the scan file
                while (scan.hasNext()) {

                    //Reads the next line
                    strRecord = scan.nextLine();


                    //checks the line number if it is 7 or greater
                    if (temp > 6) {

                        //Checks the second character at index 1 if it is "A"
                        if (A.equals(strRecord.substring(1, 2))) {

                            //Prints a new line into the OUTPUT file
                            pw.println(strRecord);
                        }
                        //Does nothing
                        else {
                        }
                    }
                    //Keeps trace on how many times the while() ran which = what line it is on
                    temp++;
                }
                //Closes files
                pw.close();
                scan.close();

                //Catches Exception
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    }
}
