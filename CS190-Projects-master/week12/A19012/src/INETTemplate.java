import java.io.FileNotFoundException;

/**
 @author - Dan Janes
 @email -
 @studentid -
 @assignment.number - A19012
 @version - V.2
 @prgm.usage - Called directly from the operating system
 @screenprint - <a href='A19012.GIF'>Screen Print</a>
 @see <br><a target='_blank' href='https://docs.oracle.com/javase/7/docs/api/javax/swing/JList.html'>JList</a>
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
