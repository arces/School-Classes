import java.sql.*;

/**
 @author - Daniel Janes
 @email -
 @studentid -
 @assignment.number - A19013
 @version - V2.0
 @prgm.usage - directly from the OS
 @screenprint - <a href='A19013.gif'>Screen Print</a>
 @see <br><a target='_blank' href='http://www.w3schools.com/html/'>W3 HTML Site</a>
 @HTMLPAGE <a href='stations.htm'> Stations HTML page</a>
 */


public class DBRead implements DBReadTemplate {

    public static Connection dbConn;
    Statement dbCmdText;
    ResultSet dbRecordset;
    String dbTable;
    String dbKeyField;

    public void close() {
        try {
            dbConn.close();
        } catch (Exception e) {
            e.printStackTrace();
            status("!!!CAN NOT CLOSE!!!");
        }

    }

    public String getField(String strFieldName) {
        String strRet = "";
        try {
            strRet = dbRecordset.getString(strFieldName);
        } catch (Exception e) {
        }
        return strRet;
    }

    public int getRecordCount(String strSQL) {
        int i = 0;
        try {
            dbRecordset.last();
            i = dbRecordset.getRow();
            dbRecordset.first();
            return i;


        } catch (Exception e) {
            e.printStackTrace();
        }

        //Test generic return. If reached then there is an error
        return 33333333;
    }

    public boolean moreRecords() {
        boolean blnStatus = false;
        try {
            blnStatus = dbRecordset.next();
        } catch (Exception e) {
            blnStatus = false;
        }
        return blnStatus; // only one RETURN in each function!
    }

    public void openConnection(String strDBName) {

        try {
            dbConn = DriverManager.getConnection("jdbc:derby:" + strDBName + ";create=true");
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

    public void query(String strSQL) {
        try {
            dbRecordset = dbCmdText.executeQuery(strSQL);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void status(String strVar) {
        System.out.println(strVar);
    }
}
