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
