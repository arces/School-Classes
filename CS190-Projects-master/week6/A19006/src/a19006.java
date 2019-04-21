import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.ArrayList;

/**
 * @author - Dan Janes
 * @version - V.23
 * @email - 
 * @studentid - 
 * @assignment.number - A19006
 * @prgm.usage - Called from the Jar File
 * @screenprint - <a href='pic.gif'>Screen Pring</a>
 * @link - https://sites.google.com/a/miramarsd.net/cisc190jc/course-overview/module-06/module-06---assignment
 * @link - https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 * @sampleoutput <a href='FBOUT.txt'>Sample Output</a>
 **/


public class a19006 {
    public static String strStaID;

    public static String[] AltArr = new String[9];
    private static boolean breakloop = false;

    //Main program
    public static void main(String[] args) {
        //Adds data into the Array AltArr
        AltArr[0] = "03";
        AltArr[1] = "06";
        AltArr[2] = "09";
        AltArr[3] = "12";
        AltArr[4] = "18";
        AltArr[5] = "24";
        AltArr[6] = "30";
        AltArr[7] = "34";
        AltArr[8] = "39";
        //Creates a new Stations and NWSFB05 Object
        Stations db = new Stations("data\\FBIN.txt");
        NWSFB05 fb = new NWSFB05("");
        //Calls the getname() Function
        getName();
        //Will loop untill the name is valid
        while (!breakloop) {
            //Checks to see if the name is a valid ID
            if (db.exists(strStaID)) {
                db.SID = strStaID;
                fb.strStationWeather = db.getStaWea(strStaID);
                db.writeToFile("A19006 - by Daniel Janes\nThe number of stations in the file " + String.valueOf(db.length()));
                db.writeToFile("Station Weather for " + db.getStaWea(strStaID));
                db.writeToFile("At " + AltArr[0] + ",000 feet:    Dir= " + fb.getWindDir(AltArr[0]) + " Speed= " + fb.getWindSpeed(AltArr[0]) + " Temp=" + fb.getWindTemp(AltArr[0]));
                db.writeToFile("At " + AltArr[1] + ",000 feet:    Dir= " + fb.getWindDir(AltArr[1]) + " Speed= " + fb.getWindSpeed(AltArr[1]) + " Temp=" + fb.getWindTemp(AltArr[1]));
                db.writeToFile("At " + AltArr[2] + ",000 feet:    Dir= " + fb.getWindDir(AltArr[2]) + " Speed= " + fb.getWindSpeed(AltArr[2]) + " Temp=" + fb.getWindTemp(AltArr[2]));
                db.writeToFile("At " + AltArr[3] + ",000 feet:    Dir= " + fb.getWindDir(AltArr[3]) + " Speed= " + fb.getWindSpeed(AltArr[3]) + " Temp=" + fb.getWindTemp(AltArr[3]));
                db.writeToFile("At " + AltArr[4] + ",000 feet:    Dir= " + fb.getWindDir(AltArr[4]) + " Speed= " + fb.getWindSpeed(AltArr[4]) + " Temp=" + fb.getWindTemp(AltArr[4]));
                db.writeToFile("At " + AltArr[5] + ",000 feet:    Dir= " + fb.getWindDir(AltArr[5]) + " Speed= " + fb.getWindSpeed(AltArr[5]) + " Temp=" + fb.getWindTemp(AltArr[5]));
                db.writeToFile("At " + AltArr[6] + ",000 feet:    Dir= " + fb.getWindDir(AltArr[6]) + " Speed= " + fb.getWindSpeed(AltArr[6]) + " Temp=" + fb.getWindTemp(AltArr[6]));
                db.writeToFile("At " + AltArr[7] + ",000 feet:    Dir= " + fb.getWindDir(AltArr[7]) + " Speed= " + fb.getWindSpeed(AltArr[7]) + " Temp=" + fb.getWindTemp(AltArr[7]));
                db.writeToFile("At " + AltArr[8] + ",000 feet:    Dir= " + fb.getWindDir(AltArr[8]) + " Speed= " + fb.getWindSpeed(AltArr[8]) + " Temp=" + fb.getWindTemp(AltArr[8]));
                db.closePW();
                breakloop=true;
            } else {
                getName();
            }
        }
    }

    /**
     * Displays a text box for the user to input the Station ID
     */
    private static void getName() {
        strStaID = JOptionPane.showInputDialog("What is the Station ID");
    }
}
