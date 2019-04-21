import java.io.*;
import java.util.*;

/**
 * Created by star on 9/14/2015.
 */
public class test {
    public static File f = new File("data\\d.txt");
    public static String temp;
    public static boolean breakLoop = false;
    public static int tempInt;

    public static void main(String[] args) {
printFile();
        Printlastof();
    }

    public static void printFile() {
        try {
            Scanner scan = new Scanner(f);
            while (breakLoop == false) {
                if (scan.hasNext()) {
                    temp = scan.nextLine();

                    System.out.println(temp);
                } else {
                    breakLoop = true;
                }
            }
            breakLoop=false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void Printlastof() {
        try {
            Scanner scan =  new Scanner(f);
            while(breakLoop==false){
                if(scan.hasNext()){
                    temp = scan.nextLine();
                    tempInt = findSem(temp);
                    System.out.println(temp.substring(tempInt+1));
                }

                else{breakLoop=true;}
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int findSem(String str){
           int pos=str.lastIndexOf(";");
        return pos;
        }

    }


