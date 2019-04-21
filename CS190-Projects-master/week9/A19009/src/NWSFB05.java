import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 @author - Daniel Janes
 @email -
 @studentid -
 @assignment.number -
 @version - V 2.0
 @prgm.usage - Called From the jar file
 @screenprint - <a href='A19009.gif'>Screen Print</a>
 @SubClass <br><a target='_blank' href='https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html'>Combo Box</a>
 */

public class NWSFB05 {
    //Usage is Make a new NWSFB05 object
    //Use getWindDir(String Alt) getWindSpeed(String Alt) getWindTemp(String Alt)

    public boolean foundStation = false;
    public String strInputFile = "data\\FBIN.txt";
    public String strOutputFile = "out\\FBOUT.txt";

    public File textfile = new File(strInputFile);
    public String strStationID;
    public String strAlt;
    public String temp;

    private boolean breakLoop = false;


    private String strWeather = "";
    public String strStationWeather = "";

    /**
     * Constructor to be called in Main class
     *
     * @param strWeather - string to be placed in the Main Class
     */
    public NWSFB05(String strWeather) //Constructor
    {
        strStationWeather = strWeather;
    }

    /**
     * Is used to get the Sation ID from the user and also calls getStationData() In the function
     *
     * @return - Returns the station ID for use in the program
     */
    public String getStationID(String strStationID) {

        while (foundStation == false) {

            // System.out.println("Please enter the Station ID");

            Scanner scanz = new Scanner(System.in);

            //strStationID = scanz.nextLine();
            //strStationID = strStationID.toUpperCase();
            strStationWeather = getStationData(strStationID, textfile);
            breakLoop = false;


        }
        return strStationID;
    }

    /**
     * @param SID      - The file name for the Station ID
     * @param FileName - The file name for the file that will be used to be scanned
     * @return the String that returns
     */

    public String getStationData(String SID, File FileName) {
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
     * The purpose of the function is to take in the altitude and find the character position of the
     * information relating to that specific altitude
     *
     * @param strAlt - the Altitude
     * @return the character position of the specific altitude
     */
    public static int getPos(String strAlt) {
        //gets the position of the character in the altitude
        int returner = 0;
        int intAlt = Integer.parseInt(strAlt);
        switch (intAlt) {
            case 3:
                returner = 4;
                break;

            case 6:
                returner = 9;
                break;

            case 9:
                returner = 17;
                break;

            case 12:
                returner = 25;
                break;

            case 18:
                returner = 33;
                break;

            case 24:
                returner = 41;
                break;

            case 30:
                returner = 49;
                break;

            case 34:
                returner = 56;
                break;

            case 39:
                returner = 63;
                break;

        }

        return returner;
    }

    /**
     * Gets the alt from the user and is stored
     */

    public void getAlt() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter in the Altitude");
        strAlt = scan.nextLine();
    }

    /**
     * The purpose of this method is to get the Altitude weather
     *
     * @param strAlt - the Altitude
     * @return Altitude weather
     */
    private String getAltitudeWeather(String strAlt) {

        int pos = getPos(strAlt);

        String strRet = "";

        if (pos == 63) {
            strRet = strStationWeather.substring(pos, pos + 6);
        } else {
            strRet = strStationWeather.substring(pos, pos + 7);
        }

        return strRet;
    }

    /**
     * The purpose of this method is to get the wind direction from the information in the
     * Altitude Weather
     *
     * @param strAlt - The Altitude
     * @return the wind direction
     */
    public String getWindDir(String strAlt) {
        String strRet = getAltitudeWeather(strAlt).substring(0, 2);


        if (getAltitudeWeather(strAlt).substring(0, 2).equals("  ")) {
            strRet = "N/A";
        } else if (getAltitudeWeather(strAlt).substring(0, 4).equals("9900")) {
            strRet = "Calm";
        } else {
            strRet = strRet + "0";
            int intAltitude = Integer.parseInt(getAltitudeWeather(strAlt).substring(1, 2));
            if (intAltitude > 36) {
                intAltitude = intAltitude + 100;
                strRet = "" + intAltitude;
            }
        }


        //System.out.println(returner);
        return strRet;
    }

    /**
     * The purpose of this method is to get the wind speed from the information in the Altitude
     * Weather
     *
     * @param strAlt - The Altitude
     * @return the wind speed
     */
    public String getWindSpeed(String strAlt) {
        String strRet = getAltitudeWeather(strAlt).substring(2, 4);
        //int intAltitude = Integer.parseInt(getAltitudeWeather(strAlt).substring(0, 2));
        //strAlt = strAlt + "000";

        if (getAltitudeWeather(strAlt).substring(2, 4).equals("  ")) {
            strRet = "N/A";
        } else if (getAltitudeWeather(strAlt).substring(0, 4).equals("9900")) {
            strRet = "Calm";
        } else {

            int intAltitude = Integer.parseInt(getAltitudeWeather(strAlt).substring(1, 2));
            if (intAltitude > 36) {
                intAltitude = intAltitude + 100;
                strRet = "" + intAltitude;
            }

        }

        return strRet;
    }

    /**
     * The purpose of this method is to get the wind temperature from the information in the
     * Altitude Weather
     *
     * @param strAlt - The Altitude
     * @return the wind temperature
     */
    public String getWindTemp(String strAlt) {
        String strRet = "";
        int intAlt = Integer.parseInt(strAlt);
        //int intAltitude = Integer.parseInt(getAltitudeWeather(strAlt).substring(0, 2));


        if (getAltitudeWeather(strAlt).substring(4, 5).equals(" ")) {
            strRet = "N/A";
        } else if (intAlt == 39) {
            strRet = "-" + getAltitudeWeather(strAlt).substring(4, 6);

        } else if (intAlt == 30) {
            strRet = "-" + getAltitudeWeather(strAlt).substring(4, 6);

        } else if (intAlt > 24) {
            strRet = "-" + getAltitudeWeather(strAlt).substring(4, 6);
        } else {
            strRet = getAltitudeWeather(strAlt).substring(4, 7);
        }
        return strRet;
    }

    /**
     * The purpose of this method is to format the Altitude Weather, Wind Speed, Wind Direction,
     * and Wind temperature into a nice organizes string
     *
     * @return formatted weather report
     * @throws IOException for Scanner
     */
    public String fmtWeatherReport() throws IOException {
        //declare varibless
        String strInputFileName = "data/FBIN.txt";

        String strRecord = "";
        int counter = 0;

        File myFile = new File(strInputFileName);

        Scanner scanFile = new Scanner(myFile);
        if (counter == 0) {
            for (int i = 0; i < 7; i++) {
                scanFile.nextLine();
                //System.out.println("checker");
            }
            counter++;
        }

        while (scanFile.hasNext()) {

            strStationWeather = scanFile.nextLine();

            String strAlt = "3";
            String strStationID = "Station ID: " + strStationWeather.substring(0, 3);
            String altWeather = "The Altitude Weather for " + strAlt + ",000 is " + getAltitudeWeather(strAlt);
            String windDir = "Wind Direction: " + getWindDir(strAlt) + " degrees";
            String windSpeed = "Wind Speed: " + getWindSpeed(strAlt) + " knots";
            String windTemp = "Wind Temp: " + getWindTemp(strAlt) + "C";

            strRecord = strRecord + strStationID + "\n" + altWeather + "\n" + windDir + "\n" + windSpeed + "\n" + windTemp + "\n" + "\n";

            strAlt = "6";

            strStationID = "Station ID: " + strStationWeather.substring(0, 3);
            altWeather = "The Altitude Weather for " + strAlt + ",000 is " + getAltitudeWeather(strAlt);
            windDir = "Wind Direction: " + getWindDir(strAlt) + " degrees";
            windSpeed = "Wind Speed: " + getWindSpeed(strAlt) + " knots";
            windTemp = "Wind Temp: " + getWindTemp(strAlt) + "C";

            strRecord = strRecord + strStationID + "\n" + altWeather + "\n" + windDir + "\n" + windSpeed + "\n" + windTemp + "\n" + "\n";

            strAlt = "9";

            strStationID = "Station ID: " + strStationWeather.substring(0, 3);
            altWeather = "The Altitude Weather for " + strAlt + ",000 is " + getAltitudeWeather(strAlt);
            windDir = "Wind Direction: " + getWindDir(strAlt) + " degrees";
            windSpeed = "Wind Speed: " + getWindSpeed(strAlt) + " knots";
            windTemp = "Wind Temp: " + getWindTemp(strAlt) + "C";

            strRecord = strRecord + strStationID + "\n" + altWeather + "\n" + windDir + "\n" + windSpeed + "\n" + windTemp + "\n" + "\n";

            strAlt = "12";

            strStationID = "Station ID: " + strStationWeather.substring(0, 3);
            altWeather = "The Altitude Weather for " + strAlt + ",000 is " + getAltitudeWeather(strAlt);
            windDir = "Wind Direction: " + getWindDir(strAlt) + " degrees";
            windSpeed = "Wind Speed: " + getWindSpeed(strAlt) + " knots";
            windTemp = "Wind Temp: " + getWindTemp(strAlt) + "C";

            strRecord = strRecord + strStationID + "\n" + altWeather + "\n" + windDir + "\n" + windSpeed + "\n" + windTemp + "\n" + "\n";

            strAlt = "18";

            strStationID = "Station ID: " + strStationWeather.substring(0, 3);
            altWeather = "The Altitude Weather for " + strAlt + ",000 is " + getAltitudeWeather(strAlt);
            windDir = "Wind Direction: " + getWindDir(strAlt) + " degrees";
            windSpeed = "Wind Speed: " + getWindSpeed(strAlt) + " knots";
            windTemp = "Wind Temp: " + getWindTemp(strAlt) + "C";

            strRecord = strRecord + strStationID + "\n" + altWeather + "\n" + windDir + "\n" + windSpeed + "\n" + windTemp + "\n" + "\n";

            strAlt = "24";

            strStationID = "Station ID: " + strStationWeather.substring(0, 3);
            altWeather = "The Altitude Weather for " + strAlt + ",000 is " + getAltitudeWeather(strAlt);
            windDir = "Wind Direction: " + getWindDir(strAlt) + " degrees";
            windSpeed = "Wind Speed: " + getWindSpeed(strAlt) + " knots";
            windTemp = "Wind Temp: " + getWindTemp(strAlt) + "C";

            strRecord = strRecord + strStationID + "\n" + altWeather + "\n" + windDir + "\n" + windSpeed + "\n" + windTemp + "\n" + "\n";

            strAlt = "30";

            strStationID = "Station ID: " + strStationWeather.substring(0, 3);
            altWeather = "The Altitude Weather for " + strAlt + ",000 is " + getAltitudeWeather(strAlt);
            windDir = "Wind Direction: " + getWindDir(strAlt) + " degrees";
            windSpeed = "Wind Speed: " + getWindSpeed(strAlt) + " knots";
            windTemp = "Wind Temp: " + getWindTemp(strAlt) + "C";

            strRecord = strRecord + strStationID + "\n" + altWeather + "\n" + windDir + "\n" + windSpeed + "\n" + windTemp + "\n" + "\n";

            strAlt = "34";

            strStationID = "Station ID: " + strStationWeather.substring(0, 3);
            altWeather = "The Altitude Weather for " + strAlt + ",000 is " + getAltitudeWeather(strAlt);
            windDir = "Wind Direction: " + getWindDir(strAlt) + " degrees";
            windSpeed = "Wind Speed: " + getWindSpeed(strAlt) + " knots";
            windTemp = "Wind Temp: " + getWindTemp(strAlt) + "C";

            strRecord = strRecord + strStationID + "\n" + altWeather + "\n" + windDir + "\n" + windSpeed + "\n" + windTemp + "\n" + "\n";

            strAlt = "39";

            strStationID = "Station ID: " + strStationWeather.substring(0, 3);
            altWeather = "The Altitude Weather for " + strAlt + ",000 is " + getAltitudeWeather(strAlt);
            windDir = "Wind Direction: " + getWindDir(strAlt) + " degrees";
            windSpeed = "Wind Speed: " + getWindSpeed(strAlt) + " knots";
            windTemp = "Wind Temp: " + getWindTemp(strAlt) + "C";

            strRecord = strRecord + strStationID + "\n" + altWeather + "\n" + windDir + "\n" + windSpeed + "\n" + windTemp + "\n" + "\n";

        }

        scanFile.close();
        return strRecord;

    }
}