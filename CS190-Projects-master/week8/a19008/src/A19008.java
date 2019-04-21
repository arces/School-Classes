import java.io.*;
import java.util.*;

/**
 @author - Dan Janes
 @email -
 @studentid -
 @assignment.number - A19008
 @version - V. 3.14
 @prgm.usage - Called from the Jar File
 @screenprint - <a href='A19008.gif'>Screen Print</a>
 @SB <br><a target='_blank' href='http://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html'>Java String Builder</a>
 @NewFeature - <br><a target='_blank' href='http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/text/WordUtils.html'>Apache WordUtils</a>
 */


public class A19008 {
    public static String worldPath = "data\\World.txt";
    public static String USAFile = "data\\UnitedStates.txt";
    public static File worldfile;
    private static INET in;

    /**
     * Is the main function that will call the other functions and create a new INET object
     *
     * @param args Default param
     */
    public static void main(String[] args) {
        //Makes a new INET object
        in = new INET();
        //checks to see if the World.txt file exitsts. If not then it will make one and download the lastest off the web
        if (!in.fileExists(worldPath)) {
            //makes a new file
            File fTemp = new File(worldPath);
            //calls the getData function
            getData();

        }
        //Calls the process Records which will get the nessicary data from the records
        ProcessRecords(USAFile, worldPath);

    }

    /**
     * This will update the World.txt file from the Internet. It is simple and easy to call
     */
    public static void getData() {
        //Will try to save to file the updated World.txt file from the internet
        try {
            in.saveToFile(worldPath, in.getURLRaw("http://weather.noaa.gov/data/nsd_bbsss.txt"));
        }
        //Catches Exceptions
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Will process the World.txt file and get the nessicary data from it and save it to the UnitedStates.txt file
     *
     * @param FileWrite The path for the file to write to
     * @param FileRead  The path for the file to read from
     */
    public static void ProcessRecords(String FileWrite, String FileRead) {
        //Makes a new file to write to
        File USA = new File(FileWrite);
        //Makes a new String Builder
        StringBuilder sb = new StringBuilder();
        //Makes a new file to read from
        File read = new File(FileRead);
        //Trys risky code
        try {
            //Makes a new scanner
            Scanner scan = new Scanner(read);
            //Makes a new PrintWriter
            PrintWriter pw = new PrintWriter(USA);
            //Makes two temp ints to keep track of the ;
            int temp1, temp2;
            //Test method
            //System.out.println("TEST");

            //As Long as Scan has a next line the while loop will run
            while (scan.hasNext()) {
                //Puts the next line of scan into temp
                String temp = scan.nextLine();

                //checks to see if the station ID starts with K
                if ("K".equals(temp.substring(7, 8))) {
                    //Will append the Station ID to the string builder
                    sb.append(in.properCase(temp.substring(7, 11)) + "\t");
                    //Will get the index of the following ; and get the needed contents inside
                    temp1 = temp.indexOf(";");
                    temp1 = temp.indexOf(";", temp1 + 1);
                    temp1 = temp.indexOf(";", temp1 + 1);
                    temp2 = temp.indexOf(";", temp1 + 1);
                    //Will append the Name of the station to the String builder
                    sb.append(in.properCase(temp.substring(temp1 + 1, temp2)) + "\t");
                    temp1 = temp.indexOf(";", temp2 + 1);
                    //Will append the State Name to the string builder
                    sb.append(temp.substring(temp2 + 1, temp1) + "\t");
                    temp2 = temp.indexOf(";", temp1 + 1);
                    //This will add USA to the string if needed
                    //sb.append(in.properCase(temp.substring(temp1+1,temp2))+"\t");
                    temp1 = temp.indexOf(";", temp2 + 1);
                    temp2 = temp.indexOf(";", temp1 + 1);
                    //Will append the Station Latitude
                    sb.append(temp.substring(temp1 + 1, temp2) + "\t");
                    temp1 = temp.indexOf(";", temp2 + 1);
                    //Will append the Station Longitude
                    sb.append(temp.substring(temp2 + 1, temp1) + "\t");

                    //Will save the string builder to the UnitedStates.txt file
                    in.saveToFile(FileWrite, sb.toString());
                    //Will clear out the String builder so it can be appended to when the loop finds the next station that starts with a K
                    sb.delete(0, sb.length());
                }
            }
            //closes the scan file and printwriter
            scan.close();
            pw.close();
            //catches the Exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
