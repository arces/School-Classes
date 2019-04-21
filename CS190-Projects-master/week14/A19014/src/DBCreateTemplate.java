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
public interface DBCreateTemplate {
    String cleanField(String strField, int intLen);
    String createTableSQL(String strFilePath);
    String getFieldName(String strTable, int intPos);
    boolean getlogger();
    String properCase(String strVar);
    void setLogger(String strFilePath);
    void status(String strVar);
}
