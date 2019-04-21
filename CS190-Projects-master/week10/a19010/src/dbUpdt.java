import java.sql.*;
import java.lang.*;

/**
 @author -
 @email -
 @studentid -
 @assignment.number - A19010
 @version - V 2.0
 @outputFile <a href='..\data\\commands.txt'>Output File</a>
 @prgm.usage - Called directly from the operating system
 @screenprint - <a href='a19010.gif'>Screen Print</a>
 @see <br><a target='_blank' href='https://db.apache.org/derby/'>Derby Database</a>

 */
public class dbUpdt {
    Connection dbConn;
    Statement dbCmdText;
    ResultSet dbRecordset;
    String dbTable;
    String dbKeyField;

    public dbUpdt() {

    }


    public Boolean openConnection(String strDataSourceName) {
        Boolean blnStatus = false;
        try {
            dbConn = DriverManager.getConnection("jdbc:derby:" + strDataSourceName + ";create=true");
            if (dbConn == null) {
                //status("Connection Failed");
            } else {
                //status("Connection Successful");
                // pg 1062 - to enable the getRecordCount method
                dbCmdText = dbConn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                blnStatus = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //status("Problems opening connection");
        }
        return blnStatus;
    }

    public String dropTables(String TableName) {


        try {
            // Get a Statement object.
            Statement stmt = dbConn.createStatement();

            try {
                // Drop the UnpaidOrder table.
                stmt.execute("DROP TABLE " + TableName);
                return "Tabled dropped";
            } catch (SQLException ex) {

                // No need to report an error.
                // The table simply did not exist.
            }

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
        }
        return"Tabled dropped";
    }

    public Boolean execute(String strSQL) {
        Boolean blnStatus = false;

        try {
            dbCmdText.execute(strSQL);
            blnStatus = true;
        } catch (Exception ex) {
            status("Execute command failed " + strSQL);

        }
        return blnStatus;
    }

    public Boolean query(String strSQL) {
        Boolean blnStatus = false;
        try {
            dbRecordset = dbCmdText.executeQuery(strSQL);
            blnStatus = true;
        } catch (Exception ex) {
            status("Query Failed " + strSQL);
        }
        return blnStatus;
    }

    public Boolean addRecord(String strTable, String strKeyName, String strKeyContents) {
        String strSQL = "";
        Boolean blnStatus = false;
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
                blnStatus = true;
            } else {
                status("Record NOT added " + strSQL);
            }
        } catch (Exception e) {
            status("SELECT command failed " + strSQL);
        }
        return blnStatus;
    }

    public Boolean moreRecords() {
        Boolean blnStatus = false;
        try {
            blnStatus = dbRecordset.next();
        } catch (Exception e) {
            blnStatus = false;
        }
        return blnStatus; // only one RETURN in each function!
    }

    public String getField(String strFieldName) {
        String strRet = "";
        try {
            strRet = dbRecordset.getString(strFieldName);
        } catch (Exception e) {
        }
        return strRet;
    }

    public void close() {
        try {
            dbRecordset.close();
        } catch (Exception e) {
        }
    }

    public int getRecordCount(String strTable) {
        int intRows = 0;
        try {
            dbRecordset.last();
            intRows = dbRecordset.getRow();
            dbRecordset.first();
        } catch (Exception e) {
            status("problem using getRecordCount");
        }
        return intRows;
    }

    public void status(String strMsg) {
        System.out.println(strMsg);
    }

}
