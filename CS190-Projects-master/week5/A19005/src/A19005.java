import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

/**
 * Created by star on 9/28/2015.
 */
public class A19005 {

    public static String[] AltArr = new String[9];
    public static void main(String[] args){
        AltArr[0]="03";
        AltArr[1]="06";
        AltArr[2]="09";
        AltArr[3]="12";
        AltArr[4]="18";
        AltArr[5]="24";
        AltArr[6]="30";
        AltArr[7]="34";
        AltArr[8]="39";
        Stations st = new Stations("data\\FBIN.txt");
        System.out.println(st.length());

    }
}
