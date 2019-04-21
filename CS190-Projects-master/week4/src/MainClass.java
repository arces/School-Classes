/**
 * Created by star on 9/14/2015.
 */

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;
import java.io.*;
import java.awt.*;

public class MainClass {

    public static String strInputFile = "data\\fbin.txt";
    public static File textfile = new File(strInputFile);
    public static String strStationID;
    public static String strAlt;
    public static int intAlt;
    public static String strStationWeather;
    public static String temp;
    public static int tempInt;
    public static boolean foundStation = false;
    public static boolean breakLoop = false;
    public static int parseIntStart;
    public static int parseIntEnd;

   

    //Is the main statement
    public static void main(String[] args) {

        getStation();
        getAlt();
        getPos(strAlt);
        System.out.println("Daniel Janes Aviation Center \nWeather for " + strStationID + " at an altitude of " + strAlt + ",000 feet");
        getWindDir("Needs to be here");
        getWindSpeed("Needs to be here");
        getWindTemp("Needs to be here");
        System.out.println("Program by Dan Janes");

    }

    //Will get and print the wind direction

    /**
     *
     * @param winddir
     * @return The temmp string
     */
    public static String getWindDir(String winddir) {
        String temmp = "0";

        //checks to see if the first character is a 0
        if (temmp.equals(getAltitudeWeather(" ").substring(0, 1))) {
            //prints the 2nd character and a 0
            System.out.println("Wind Direction " + getAltitudeWeather("needsToBeHere").substring(1, 2) + "0 degrees");
        }
        //prints the 1 and 2nd character with a 0 at the end
        System.out.println("Wind Direction   " + getAltitudeWeather("needsToBeHere").substring(0, 2) + "0 degrees");
        return temmp;
    }

    /**
     *
     * @param windSpeed
     * @return The temp string
     */

    public static String getWindSpeed(String windSpeed) {
        String temmp = "0";
        // checks to see if the first character is a 0
        if (temmp.equals(getAltitudeWeather(" ").substring(2, 3))) {
            System.out.println("Wind Speed       " + getAltitudeWeather("needsToBeHere").substring(3, 4) + " knots");
        }
        //prints the characters and adds knots at the end
        System.out.println("Wind Speed       " + getAltitudeWeather("needsToBeHere").substring(2, 4) + " knots");
        return temmp;
    }

    /**
     *
     * @param windTemp
     * @return The temmp string
     */
    public static String getWindTemp(String windTemp){
        String temmp = "0";
        //prints out the temp plus C at the end
        System.out.println("Wind Temperature "+getAltitudeWeather("needsToBeHere").substring(getAltitudeWeather(" ").length()-3)+"C");
        return temmp;
    }


    /**
     *
     * @param strAltitude
     * @return The String that is relevent to the alt
     */

    public static String getAltitudeWeather(String strAltitude) {
        if (intAlt == 6 || intAlt == 9 || intAlt == 12 || intAlt == 18 || intAlt == 24) {
            return (strStationWeather.substring(parseIntStart, parseIntEnd));
        } else {
            System.out.println("Sorry this program will not work for your Current Alt");
            System.exit(1);
        }
        return "dan janes wrote this program ";
    }

    //will get the station and store the record of the station
    public static void getStation() {

        //will not end until it got a valid station id
        while (foundStation == false) {

            System.out.println("Please enter the Station ID");

            Scanner scanz = new Scanner(System.in);

            strStationID = scanz.nextLine();
            strStationID = strStationID.toUpperCase();
            strStationWeather = getStationData(strStationID, textfile);
            breakLoop = false;


        }

    }

    /**
     *
     * @param SID
     * @param FileName
     * @return The record that is corresponding with the station id
     */
    public static String getStationData(String SID, File FileName) {
        int x = 0;
        try {
            Scanner scan = new Scanner(FileName);
            while (breakLoop == false) {

                temp = scan.nextLine();
                if (scan.hasNext() && x > 6) {
                    x++;
                    if (temp.length() < 60) {
                        System.exit(1);
                    }

                    if (SID.equals(temp.substring(0, 3))) {
                        breakLoop = true;
                        foundStation = true;


                    } else {
                    }

                } else if (scan.hasNext() && x < 7) {
                    x++;
                } else {
                    breakLoop = true;
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return temp;
    }


    /**
     * Gets the alt from the user
     */
    public static void getAlt() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter in the Altitude");
        strAlt = scan.nextLine();
    }

    /**
     *
     * @param strAlt
     * @return Returns an int for the pos of the alt
     */
    public static int getPos(String strAlt) {
        intAlt = Integer.parseInt(strAlt);
        switch (intAlt) {
            case 3:
                parseIntStart = 4;
                parseIntEnd = 8;
                break;
            case 6:
                parseIntStart = 9;
                parseIntEnd = 16;
                break;
            case 9:
                parseIntStart = 16;
                parseIntEnd = 24;
                break;
            case 12:
                parseIntStart = 25;
                parseIntEnd = 32;
                break;
            case 18:
                parseIntStart = 33;
                parseIntEnd = 40;
                break;
            case 24:
                parseIntStart = 41;
                parseIntEnd = 48;
                break;
            case 30:
                parseIntStart = 50;
                parseIntEnd = 56;
                break;
            case 34:
                parseIntStart = 57;
                parseIntEnd = 63;
                break;
            case 39:
                parseIntStart = 6969;
                parseIntEnd = 420;
        }


        return 1;
    }


}
