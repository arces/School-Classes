import java.sql.*;
import java.lang.*;

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
public class dbUpdt extends dbRead implements dbUpdtTemplate {
    Connection dbConn;
    Statement dbCmdText;
    ResultSet dbRecordset;
    String dbTable;
    String dbKeyField;
    dbRead dbr = new dbRead();

    public dbUpdt() {


    }


    /**
     * Will drop the table of TableName
     * @param TableName Table to be dropped
     * @return Will return if it dropped the table or not
     * @throws SQLException the exption if the connection is not succesfull
     */
    public String dropTables(String TableName) throws SQLException {



        dbConn=DriverManager.getConnection("jdbc:derby:" + "Weather");

        try {

            // Get a Statement object.
            Statement stmt = dbConn.createStatement();

            try {
                // Drop the UnpaidOrder table.
                stmt.execute("DROP TABLE " + TableName);
                return "Tabled dropped";
            } catch (SQLException ex) {
                ex.printStackTrace();
                // No need to report an error.
                // The table simply did not exist.
            }

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
        }
        dbConn.close();
        return"Tabled droppedd";
    }

    /**
     * Will execute the command
     * @param strSQL The command to execute
     */
    public void execute(String strSQL) {


        try {
            dbConn=DriverManager.getConnection("jdbc:derby:" + "Weather");
            Statement stmt = dbConn.createStatement();
            stmt.execute(strSQL);
        } catch (Exception ex) {
            status("Execute command failed " + strSQL);

        }
        try {
            dbConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * Does nothing as of 11/8/2015
     * @param strTable
     * @param strKeyName
     * @param strKeyContents
     * @param strFieldName
     * @param strFieldContents
     * @throws Exception
     */
    public void setField(String strTable, String strKeyName, String strKeyContents, String strFieldName, String strFieldContents) throws Exception {

    }

    /**
     * Will return if there are more records left
     * @return True or false
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
     * Will add a record to the DB
     * @param strTable The table name
     * @param strKeyName The Keyname
     * @param strKeyContents The key contents
     */
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

    /**
     * Will deleteAll
     * @param strTable the table
     */
    public void deleteAll(String strTable) {

    }


    /**
     * Will get the field
     * @param strFieldName
     * @return
     */
    public String getField(String strFieldName) {
        String strRet = "";
        try {
            strRet = dbRecordset.getString(strFieldName);
        } catch (Exception e) {
        }
        return strRet;
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
