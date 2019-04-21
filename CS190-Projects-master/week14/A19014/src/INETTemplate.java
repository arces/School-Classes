import java.io.FileNotFoundException;

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

public interface INETTemplate {
    Boolean fileExists(String strFileName);
    String getFromFile(String strFileName) throws Exception;
    String getPREData(String strPage);
    String getRegEx(String strInput, String strPattern);
    String getURLRaw(String strURL) throws Exception;
    String properCase(String strInput);
    void saveToFile(String strFileName, String strContent) throws Exception;
    void appendToFile(String strFilename, String strContent);
}
