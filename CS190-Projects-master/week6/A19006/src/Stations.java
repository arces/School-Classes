import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by star on 10/3/2015.
 */
public class Stations {

    public ArrayList<String> staList = new ArrayList<String>();
    public ArrayList<String> staIndex = new ArrayList<String>();
    public PrintWriter pw;
    public boolean breakLoop = false;
    public String SID;


    /**
     * Will set up a scanner to read the file and add each station line into the arraylist staList as well as add just the station ID to arraylist staIndex
     * @param temp The input file pathway for the scanner to read the FBIN
     */
    public Stations(String temp) {
        String strFilePath = temp;
        File textfile = new File(strFilePath);
        File outFile = new File("out\\FBOUT.txt");
        try {
            //makes a new printWriter
            pw = new PrintWriter(outFile);
            //makes a new var to count the loops
            int x = 0;
            try {
                Scanner scan = new Scanner(textfile);

                //will loop until there is no more lines to scan
                while (breakLoop == false) {
                    //adds the next scan line into String tempz
                    String tempz = scan.nextLine();
                    //checks to see if there is a next line and that it looped at least 7 times
                    if (scan.hasNext() && x > 6) {
                        x++;
                        //add the whole line into staList
                        staList.add(tempz);
                        //adds just the station ID to staIndex
                        staIndex.add(tempz.substring(0, 3));

                    }
                    //checks to see it is in the first 6 lines of the file and will skip them
                    else if (scan.hasNext() && x < 7) {
                        x++;
                    }
                    //ends the loop, there are no more lines in the file
                    else {
                        breakLoop = true;
                    }
                }

                //closes the file
                scan.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Checks to see if the Station ID is in the ArrayList and will return true or false
     * @param strID The station ID as a string
     * @return returns true or false depending on if the ID apperes or not
     */
    public boolean exists(String strID) {
        return staIndex.contains(strID);
    }

    /**
     * Gets the length of the ArrayList staIndex
     * @return Returns the length of the StaIndex in an Int
     */
    public int length() {
        return staIndex.size();
    }

    /**
     * Takes in a string of the Station ID and returns the full string
     * @param strSationID The station ID as a String
     * @return returns the whole Station data for later processing
     */

    public String getStaWea(String strSationID) {

        if (exists(strSationID)) {
            //gets the index from staindex and gets the string in stalist of the corresponding index
            return staList.get(staIndex.indexOf(strSationID));
        } else {
            //If the station ID is not valid then it returns Station Not Found
            return "Station Not Found";
        }
    }

    /**
     * Will write what ever it is passed to the Print Write file
     * @param s Any input that will be written to the PW file as a String
     */
    public void writeToFile(String s) {
        //adds S to the pw file on a new line
        pw.println(s);
    }

    /**
     * Will close the Print Write file
     */
    public void closePW() {
        //closes the pw file
        pw.close();
    }

}
