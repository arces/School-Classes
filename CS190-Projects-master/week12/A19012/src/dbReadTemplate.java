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
public interface dbReadTemplate {

        void openConnection(String strFileName) throws Exception;

        boolean moreRecords();

        void query(String strSQL);

        String getField(String strFieldName);

        void close() throws Exception;
    }
