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
    public boolean breakLoop=false;

    public Stations(String temp){
        String strFilePath = temp;
        File textfile = new File(strFilePath);
        try {
            //makes a new printWriter
            pw = new PrintWriter(textfile);

            int x = 0;
            try {
                Scanner scan = new Scanner(textfile);


                while (breakLoop == false) {

                   String tempz = scan.nextLine();
                    if (scan.hasNext() && x > 6) {
                        x++;
                        staList.add(tempz);
                        staIndex.add(tempz.substring(0,3));

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


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public boolean exists(String strID){
            return staIndex.contains(strID);
    }

public int length(){
    return staIndex.size();
}
    //Takes in a string of the Station ID and returns the full string
    public String getStaWea(String strSationID){

        if(exists(strSationID)){
            //gets the index from staindex and gets the string in stalist of the corresponding index
            return staList.get(staIndex.indexOf(strSationID));
        }
        else {
            //If the station ID is not valid then it returns Station Not Found
            return "Station Not Found";
        }
    }

}
