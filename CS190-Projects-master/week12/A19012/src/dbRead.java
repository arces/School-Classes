import javax.xml.bind.annotation.XmlElementDecl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
public class dbRead implements dbReadTemplate {


    public static Connection dbConn;
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
     * @param strFieldName
     * @return
     */
    public String getField(String strFieldName)
    {
        String strRet="";
        try
        {
            strRet = dbRecordset.getString(strFieldName);
        }
        catch(Exception e)
        {
        }
        return strRet;
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
