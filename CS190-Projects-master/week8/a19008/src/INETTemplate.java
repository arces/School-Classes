import java.io.FileNotFoundException;

/**
 * Created by star on 10/12/2015.
 */
public interface INETTemplate {
    Boolean fileExists(String strFileName);
    String getFromFile(String strFileName) throws Exception;
    String getPREData(String strPage);
    String getRegEx(String strInput, String strPattern);
    String getURLRaw(String strURL) throws Exception;
    String properCase(String strInput);
    void saveToFile(String strFileName, String strContent) throws Exception;
}
