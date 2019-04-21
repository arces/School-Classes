import java.sql.*;

/**
 @author - Daniel Janes
 @email - danieljanes@tuta.io
 @studentid - 5450666
 @assignment.number - A19014
 @version - V2.0
 @prgm.usage - directly from the OS
 @screenprint - <a href='a19014.gif'>Screen Print</a>
 @see <br><a target='_blank' href='http://www.w3schools.com/html/'>W3 HTML Site</a>
 @HTMLPAGE <a href='..\data\\avia151.htm'> Avia151 HTML page</a>
 */

public class DBUpdt extends DBRead implements DBUpdtTemplate{

    Connection dbConn;
    Statement dbCmdText;
    ResultSet dbRecordset;
    String dbTable;
    String dbKeyField;
    DBRead dbr = new DBRead();

    public void addRecord(String strTable, String strKeyName, String strKeyContents) {
        String strSQL = "";

        try {
            // check to see if the record exists
            dbCmdText = dbConn.createStatement();
            strSQL = "SELECT * FROM " + strTable + " WHERE " +
                    strKeyName + "='" + strKeyContents + "'";
            query(strSQL);
            if (!moreRecords()) {
                // the record does not exist, therefore needs to be added to the table
                strSQL = "INSERT INTO " + strTable + " (" + strKeyName +
                        ") VALUES ('" + strKeyContents + "')";
                execute(strSQL);
                status("Record added");

            } else {
                status("Record NOT added " + strSQL);
            }
        } catch (Exception e) {
            status("SELECT command failed " + strSQL);
        }


    }

    public void deleteAll(String strTable) {

    }

    public void execute(String strSQL) {
        try {
            //dbConn=DriverManager.getConnection("jdbc:derby:" + "Weather");
            Statement stmt = DBRead.dbConn.createStatement();
            stmt.execute(strSQL);
            stmt.close();
            //test call
            //System.out.println("WORKED");
        } catch (Exception ex) {
            ex.printStackTrace();
            status("Execute command failed " + strSQL);

        }

    }

    public void setField(String strTable, String strKeyName, String strKeyContents, String strFieldName, String strFieldContents) {

    }
}
