import java.io.FileNotFoundException;

/**
 @author -
 @email -
 @studentid -
 @assignment.number -
 @version - V 2.0
 @outputFile <a href='..\data\\commands.txt'>Commands</a>
 @prgm.usage - Called directly from the operating system
 @screenprint - <a href='a19011.gif'>Screen Print</a>
 @see <br><a target='_blank' href='https://db.apache.org/derby/'>Derby Database</a>

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
