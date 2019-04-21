import org.apache.commons.lang3.text.WordUtils;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.net.*;
import java.util.regex.*;


/**
 * Created by star on 10/14/2015.
 */


public class INET implements INETTemplate {
    public File worldfile;
        PrintWriter outputFile;


    /**
     * Will check if the file Exists or not. If it does then it will return true, If it does not exist it will return false
     * @param strFileName The file path/name to check if it exists or not
     * @return True or False depending on if the file Exists or not
     */
    public Boolean fileExists(String strFileName) {
        //will try risky code here
        try {
            //Makes a new file with the file path name
            worldfile = new File(strFileName);
            //if the file is a correct and valid file then it returns true
            if (worldfile.isFile()) {

                return true;
                //If for some reason it is not a file then it returns false
            } else {

                return false;
            }
            //catches exceptions and prints the error out
        } catch (Exception e) {

            e.printStackTrace();
            //returns false if it crashes from an error
            return false;
        }

    }

    /**
     * Will return the full contents of the File as one string to be worked on later
     * @param strFileName The pathway to the file
     * @return the full contents of the file as one string
     * @throws Exception file not found
     */
    public String getFromFile(String strFileName) throws Exception {
        //checks to see if the file exists
        if (fileExists(strFileName)) {
            //make a new file from the file name given
            worldfile = new File(strFileName);
            //trys the code here
            try {
                //makes a new scanner
                Scanner scan = new Scanner(worldfile);
                //makes a temp string
                String temp;
                //makes a new string builder which will be returned at the end if all goes well
                StringBuilder sb = new StringBuilder();
                //As long as the scan file has a next line
                while (scan.hasNext()) {
                    //assigns the scan line in temp
                    temp = scan.nextLine();
                    //adds the scan line to the string builder
                    sb.append(temp);
                }
                //returns the string builder whish is all lines of the scan file
                return sb.toString();
                //catches the file not found error
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        //Will return nothing if there is no file
        return null;
    }

    /**
     * Uses the getRegEx but adds the expression to get PRE
     * @param strPage The Webpage to parse
     * @return The data inside the Pre tags
     */
    public String getPREData(String strPage) {
        return getRegEx(strPage,"<pre>(.*)</pre>");
    }

    /**
     * Will make a pattern and will try to find a matching part in the StrInput
     * @param strInput The text to search in
     * @param strPattern The pattern to try to find
     * @return Returns the match Regular Exprestion or null if it does not find anything
     */
    public String getRegEx(String strInput, String strPattern) {
        //Makes a new pattern
        Pattern p = Pattern.compile(strPattern);
        //Finds a match to the pattern
        Matcher matcher = p.matcher(strInput);
        //If it matches it will return the group
        if(matcher.matches()){
            return matcher.group();
        }
        else{
            return null;
        }
    }

    /**
     * Gets a webpage from the internet from the given URL
     * @param strURL The url which will be downloaded
     * @return the webpage as a string
     * @throws Exception Malformed URL exception
     */
    public String getURLRaw(String strURL) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            URL webAdd = new URL(strURL);
            URLConnection myConn = webAdd.openConnection();
            myConn.setConnectTimeout(1000);
            InputStream myStrIn = myConn.getInputStream();
            BufferedReader inputFile = new BufferedReader(new InputStreamReader(myStrIn));

            while (inputFile.ready()) {
                String strRecord = inputFile.readLine()+ "\r\n";
                sb.append(strRecord);
            }
            //Will return the webpage back as a string keeping the lines breaks
            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //For debugging. Should never be reached
        return "ERROR IN GET URL RAW";
    }

    /**
     * Will properly cap each word and lowercase non starting letters in words
     * @param strInput The text to be corrected
     * @return The corrected text
     */
    public String properCase(String strInput) {
        //Will Uppercase the first letter in each word and lower case the rest of the letters
        return WordUtils.capitalizeFully(strInput);
        }

    /**
     * Will add text to the file specified in strFileName
     * @param strFileName The file name to be written too
     * @param strContent  The content to be written on a new line
     * @throws Exception File Not Found
     */
    public void saveToFile(String strFileName, String strContent) throws Exception {
        //makes a printwriter from the file directory EX: data\\Worlds.txt
        outputFile = new PrintWriter(new BufferedWriter (new FileWriter( strFileName, true)));
        //Appends the content to the file
        outputFile.println(strContent);
        outputFile.close();

    }
}
