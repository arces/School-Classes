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
public interface DBUpdtTemplate {
    void addRecord(String strTable, String strKeyName, String strKeyContents);
    void deleteAll(String strTable);
    void execute(String strSQL);
    void setField(String strTable, String strKeyName, String strKeyContents, String strFieldName, String strFieldContents);
}
