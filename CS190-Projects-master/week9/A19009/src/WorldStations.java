import java.util.*;
import java.lang.reflect.*;
import java.io.*;

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
public class WorldStations extends Stations {
    public String[] aryWorld;
    Stations st;
    INET in;
    Sta sta;

    /**
     * Will set up a scanner to read the file and add each station line into the arraylist staList as well as add just the station ID to arraylist staIndex
     *
     * @param strFbinPath The input file pathway for the scanner to read the FBIN
     */
    public WorldStations(String strFbinPath, String strWorldPath) {
        //calls the super
        super(strFbinPath);
        //makes a new stations object
        st = new Stations("data\\FBIN.txt");
        //test
        //System.out.println(sta.NAME);

        //makes a new String array for aryWorld
        aryWorld = new String[st.length()];
        //makes a new file called f
        File f = new File("data\\World.txt");
        try {
            //makes a scanner
            Scanner scan = new Scanner(f);
            //makes a temp string
            String tempz = "";
            //while scanner has a next line
            while (scan.hasNext()) {
                //if the scan has a next line, extra line of defense
                if (scan.hasNext()) {
                    //temp now = the next line of scan
                    tempz = scan.nextLine();
                    //If the first 2 numbers are 73 or 74 which is the code for the USA
                    if (tempz.substring(0, 2).equals("72") || tempz.substring(0, 2).equals("74")) {
                        //if the staIndex arraylist in the stations object contains the same station ID of the current line of the world.txt file.
                        if (st.staIndex.contains(tempz.substring(8, 11))) {
                            //adds the whole tempz string into the corrisponding index in the aryWorld array
                            aryWorld[st.staIndex.indexOf(tempz.substring(8, 11))] = tempz;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * Will get the position in the staIndex arraylist
     * @param strStaID The Station ID as a String
     * @return The position of the Station ID as an int
     */
    public int getPos(String strStaID) {
        //returns the index of the stationID in the staIndex arraylist
        return this.staIndex.indexOf(strStaID);
    }

    /**
     * Will get any field from the World.txt file as long as the Station ID is valid and intField Matches that of the order in which they are formated in
     * @param strStationID The station ID as a String
     * @param intField The intField that matches how the World.txt file is formated in
     * @return The requested Field from the requested Station ID
     */
    public String getStadata(String strStationID, int intField) {
        //makes a new INET object
        in = new INET();

        //Sets temp to the whole line of a station from the World.txt
        String temp = aryWorld[st.staIndex.indexOf(strStationID)];

        //Fills the array with the data from temp but broken up the ";" character
        String[] aryTemp = temp.split(";");


        //Test Method to see what the Array Contains
        //System.out.println(Arrays.toString(aryTemp));

        //returns what ever data is at intField
        return aryTemp[intField];
    }


    /**
     * Will return the Name of the given Station ID
     *
     * @param staID The Station ID as a String
     * @return The Name as a String
     */
    public String getName(String staID) {
        try {
            return this.getStadata(staID, sta.NAME.returnNum());
        } catch (Exception e) {
            return "Unknown Name";
        }
    }

    /**
     * Will return the Latitude of the given Station ID
     *
     * @param staID The Station ID as a String
     * @return The Latitude as a String
     */
    public String getLatitude(String staID) {
        try {
            return this.getStadata(staID, sta.Latitude.returnNum());
        } catch (Exception e) {
            return "Unknown Latitude";
        }
    }

    /**
     * Will return the Longitude of the given Station ID
     *
     * @param staID The Station ID as a String
     * @return The Longitude as a String
     */
    public String getLongitude(String staID) {
        try {
            return this.getStadata(staID, sta.Longitude.returnNum());
        } catch (Exception e) {
            return "Unknown Longitude";
        }
    }

    /**
     * Will return the Altiude of the given Station ID
     *
     * @param staID The Station ID as a String
     * @return The Altiude as a String
     */
    public String getAltiude(String staID) {
        try {
            return this.getStadata(staID, sta.Altitude.returnNum());
        } catch (Exception e) {
            return "Unknown Altiude";
        }
    }

    /**
     * Will return the State of the given Station ID
     *
     * @param staID The Station ID as a String
     * @return The State as a String
     */
    public String getState(String staID) {
        try {
            return this.getStadata(staID, sta.State.returnNum());
        } catch (Exception e) {
            return "Unknown State";
        }
    }

    /**
     * Will return the County of the given Station ID
     *
     * @param staID The Station ID as a String
     * @return The County as a String
     */
    public String getCounty(String staID) {
        try {
            return this.getStadata(staID, sta.County.returnNum());
        } catch (Exception e) {
            return "Unknown Country";
        }
    }

}
