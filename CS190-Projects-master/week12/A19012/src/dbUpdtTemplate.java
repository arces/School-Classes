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
public interface dbUpdtTemplate {
    void setField(String strTable,
                  String strKeyName,
                  String strKeyContents,
                  String strFieldName,
                  String strFieldContents) throws Exception;

    void addRecord(String strTable,
                   String strKeyName,
                   String strKeyContents) throws Exception;

    void deleteAll(String strTable);
    void execute(String strSQL);
}
