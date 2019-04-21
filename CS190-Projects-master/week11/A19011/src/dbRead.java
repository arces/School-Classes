import javax.xml.bind.annotation.XmlElementDecl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
public class dbRead implements dbReadTemplate {


    public Connection dbConn;
    Statement dbCmdText;
    ResultSet dbRecordset;
    String dbTable;
    String dbKeyField;

    public dbRead(){

    }

    /**
     * Will open a connection with the database
     * @param strDataSourceName The database name
     */
    public void openConnection(String strDataSourceName) {

        try {
            dbConn = DriverManager.getConnection("jdbc:derby:" + strDataSourceName + ";create=true");
            if (dbConn == null) {
                //System.out.println("Connection Failed");
            } else {
               // System.out.println("Connection Successful");
                // pg 1062 - to enable the getRecordCount method
                dbCmdText = dbConn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);

            }
        } catch (Exception e) {
            e.printStackTrace();
            //status("Problems opening connection");
        }

    }

    /**
     * Will return if there is any more records
     * @return true or false
     */
    public boolean moreRecords() {
        boolean blnStatus = false;
        try {
            blnStatus = dbRecordset.next();
        } catch (Exception e) {
            blnStatus = false;
        }
        return blnStatus; // only one RETURN in each function!
    }

    /**
     * Will query the database with strSQl
     * @param strSQL the command to query the database with
     */
    public void query(String strSQL) {

        try {
            dbRecordset = dbCmdText.executeQuery(strSQL);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Does nothing as of 11/8/15
     * @param strTable
     * @param strKeyName
     * @param strKeyContents
     * @param strFieldName
     * @return
     */
    public String getField(String strTable, String strKeyName, String strKeyContents, String strFieldName) {
        return null;
    }

    /**
     * Will close the database connection
     */
    public void close() {
        try {
            dbConn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!!CAN NOT CLOSE!!!");
        }
    }
}
