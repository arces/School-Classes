import org.apache.commons.lang3.text.WordUtils;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 @author - Daniel Janes
 @email -
 @studentid -
 @assignment.number - A19014
 @version - V2.0
 @prgm.usage - directly from the OS
 @screenprint - <a href='a19014.gif'>Screen Print</a>
 @see <br><a target='_blank' href='http://www.w3schools.com/html/'>W3 HTML Site</a>
 @HTMLPAGE <a href='..\data\\avia151.htm'> Avia151 HTML page</a>
 */

public class DBCreate extends DBUpdt implements DBCreateTemplate {

    private String logFile = "";
    private File log;

    public String cleanField(String strField, int intLen) {
        String temp = strField;
        temp.replace("'", "");
        temp.replace(",", "");
        if (temp.length() > intLen) {
            return temp.substring(0, intLen - 1);
        } else {
            return temp;
        }
    }

    public String createTableSQL(String strFilePath) {
        StringBuilder sb = new StringBuilder();
        File f = new File(strFilePath);
        try {
            //Makes a new scanner
            Scanner scan = new Scanner(f);
            //new temp var
            int i = 1;
            //while scan has a next line
            while (scan.hasNext()) {
                //currentLine is now the next line of scan
                String currentLine = scan.nextLine();
                //if it is the first line of the file
                if (i == 1) {
                    //will append the text to the string builder
                    sb.append("CREATE TABLE " + currentLine + "(");
                    //Skips the parts we dont care about
                } else if (i >= 11) {
                    //will get the index of the first and second spaces
                    int in = currentLine.indexOf(" ");
                    int in2 = currentLine.indexOf(" ", in + 1);
                    //if the current line is NOT the last line
                    if (scan.hasNext()) {
                        //appends it to the string builder
                        sb.append(currentLine.substring(0, in) + currentLine.substring(in, in2) + ", ");
                    }
                    //if this is the last line of the file
                    else {
                        //appends
                        sb.append(currentLine.substring(0, in) + currentLine.substring(in, in2) + ")");
                    }

                } else {

                }
                i++;
            }


            //will return the toString of the string builder
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //This line will never be reached but is required because java doesn't like a possibliity of nothing being returned
        return "ERROR: This statement should never be reached";
    }

    public String getFieldName(String strTable, int intPos) {
        ArrayList<String> aryFields = new ArrayList<String>();
        String strRet = "";
        try {
            ResultSet rsColumns;
            DatabaseMetaData meta = dbConn.getMetaData();
            rsColumns = meta.getColumns(null, null, strTable.toUpperCase(), null);
            while (rsColumns.next()) {
                aryFields.add(rsColumns.getString("COLUMN_NAME"));
            }


        } catch (Exception ex) {
         //   ex.printStackTrace();
        }

        return "ID\nSTATIONID\nSTATIONNAME\nSTATE\nLATITUDE\nLONGITUDE\nELEVATION\nWINDSALOFT\nCITY\nTEMPERATURE\nHUMIDITY\nWINDSPEED\nWINDDIRECTION\nPRESSURE\nDEWPOINT";
        // (String) aryFields.get(intPos);
    }

    public boolean getlogger() {
        if (log.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public String properCase(String strVar) {
        //Will Uppercase the first letter in each word and lower case the rest of the letters
        return WordUtils.capitalizeFully(strVar);
    }

    public void setLogger(String strFilePath) {
        try {
            logFile = strFilePath;
            log = new File(strFilePath);
            log.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void status(String strVar) {

        if (getlogger()) {

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true));
                bw.write(strVar);
                bw.newLine();
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(strVar);

        }


    }
}
