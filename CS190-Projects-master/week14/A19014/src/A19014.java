import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.SimpleBookmark;
import com.itextpdf.text.pdf.SimpleNamedDestination;
import java.text.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


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

public class A19014 extends JDialog {
    private JPanel contentPane;
    private JButton btnWebPage;
    private JButton btnLog;
    private JButton btnpdf;
    private JButton btndatabaseButton;
    private JButton btnExit;
    private DBCreate dbc = new DBCreate();
    protected String sqlcommand;

    /**
     * Main function
     */
    public A19014() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnWebPage);


        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        btnLog.addActionListener(new loggerbtn());
        btndatabaseButton.addActionListener(new createDBlistener() {});
        btnpdf.addActionListener(new pdfLisener());
        btnWebPage.addActionListener(new htmlPage());

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Is the main thread that is called
     */
    private void mainFunction(){
        dbc.setLogger("doc\\logger.txt");
        log("Log Started");
        dbc.openConnection("Aviation");

        System.out.println(getDate());

    }

    /**
     * Will download the schema file
     */
    private void downloadSchema(){
        INET in = new INET();
        try {
            in.saveToFile("data\\schema_aim.txt",in.getURLRaw("http://jcouture.net/data/schema_aim.txt"));
            log("Schema_aim.txt downloaded");
            dbc.createTableSQL("data\\schema_aim.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Will create the DB table
     */
    private void createDB(){
        downloadSchema();

        sqlcommand = dbc.createTableSQL("data\\schema_aim.txt");
        log("Your SQL command: " + sqlcommand);
        dbc.execute(sqlcommand);
        log("Book Table Created");


    }

    /**
     * A short cut to the status function
     * @param s The String to put in the status function
     */
    private void log(String s){
        dbc.status(s);
    }

    /**
     * Defult made by Intellij
     */
    private void onOK() {
// add your code here
        dispose();
    }

    /**
     * Defult made by Intellij
     */
    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    /**
     * Main call by java
     * @param args NA
     */
    public static void main(String[] args) {
        A19014 dialog = new A19014();
        dialog.pack();
        dialog.mainFunction();
        dialog.setVisible(true);
        System.exit(0);
    }

    /**
     * Creates an XML file with the bookmarks of a PDF file.
     * @param src the path to the PDF file with the bookmarks
     * @param dest the path to the XML file
     * @throws IOException
     */
    public void createXml(String src, String dest) throws IOException {
        PdfReader reader = new PdfReader(src);
        List<HashMap<String, Object>> list = SimpleBookmark.getBookmark(reader);
        SimpleBookmark.exportToXML(list,
                new FileOutputStream(dest), "ISO8859-1", true);
        reader.close();
    }


    /**
     * Will parse the XML and get all the data out of it that it needs to use to execute the commands
     * @param filepath the file path to the xml file
     */
    private void cleanXML(String filepath){
        File f = new File(filepath);
        try {
            Scanner scan = new Scanner(f);
            int i = 0;
            StringBuilder sb = new StringBuilder();
            String Chapter ="";
            String page = "";
            String Section = "";
            while(scan.hasNext()){
                String temp = scan.nextLine();
                if(i<17){
                    i++;
                }
                else{
                    if(temp.contains("Page")){
                        try {
                            page = String.valueOf(temp.substring(temp.indexOf("Page=\"")+6, temp.indexOf("Page=\"") + 9));
                           // System.out.println(page);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    temp = temp.replaceAll("<Title(.)* >", "");
                    temp = temp.replace("&#8194;", " ");
                    temp = temp.replace("</Title>", "");
                    temp = temp.replace("</Bookmark>", "");
                    temp = temp.replace("PILOT/CONTROLLER GLOSSARY", "");
                    temp = temp.replace(">", "");
                    //System.out.println(temp);
                   // temp = temp.replace("<Title Action=\"GoTo\" Page=\"43 XYZ null null null\" >", "");
                    if(temp.contains("Chapter ")){
                        try {
                            Chapter = String.valueOf(temp.substring(temp.indexOf(".") - 2, temp.indexOf(".")));
                           // System.out.println(Chapter);
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(temp.contains("Section")){
                        Section = String.valueOf(temp.substring(temp.indexOf(".")-1,temp.indexOf(".")));
                        //System.out.println(Section);
                        sb.append("INSERT INTO BOOK (timestamp, concept, page, chapter, section, course, book, module) VALUES(");
                        String tempz = "";
                        if(temp.substring(temp.indexOf(".") + 2).length() > 50){
                            tempz=temp.substring(temp.indexOf(".") + 2, temp.indexOf(".") + 50);
                        }else{
                            tempz=temp.substring(temp.indexOf(".") + 2);
                        }
                        sb.append("'" + getDate() + "'," + "'" + tempz + "'," + "'" + page + "'," + "'" + Chapter + "'," + "'" + Section + "', 'AVIA 151', 'AIM_150403'," + "'" + Chapter + "')");
                        //System.out.println(sb.toString());
                        try {
                            log(sb.toString());
                            dbc.execute(sb.toString());
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }
                sb.setLength(0);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    log("PDF file closed");
    }

    /**
     * gets the date and returns in
     * @return the date to return
     */
    public String getDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyyHH:ss");

        return ( sdf.format(cal.getTime()) );
    }


    /**
     * Will make the HTML page
     * @return the string to save as a html file
     */
    private String makeHTMLPage(){
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n<table border='1' cellpadding='5' cellspacing='0'>\n<tr><th>Concept</th><th>Chapter</th><th>Section</th><th>Page</th><th>Module</th><th>Course</th><th>Book</th></tr>");

        int i =0;
        //will query the database
        dbc.query("SELECT * FROM BOOK");
        //will keep going if there are anymore records
        XMLRead myXML = new XMLRead();
        while (dbc.moreRecords()) {

            try {
                //will add the stationid and stationname vars to the combo box
                sb.append("<tr>" +  "<td> " + dbc.getField("concept") + "</td>" + "<td> " + dbc.getField("chapter") + "</td>" + "<td> " + "Section "+dbc.getField("section") + "</td>" + "<td> " + dbc.getField("page") + "</td>" + "<td> " + dbc.getField("module") + "</td>" + "<td> " + dbc.getField("course") + "</td>" + "<td> " + dbc.getField("book") + "</td> </tr>");

            }
            catch (Exception e){
                e.printStackTrace();
            }
            i++;
        }

        sb.append("</table></html>");


        return sb.toString();

    }


    /**
     * Will make the PDFLisener
     */
    public class pdfLisener implements ActionListener {
        /**
         * An action listener that will listen to the dropTable Button and preform an action once it knows the user has interacted with the button
         *
         * @param e The action
         */
        //The override Action
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                createXml("data\\AIM.pdf", "data\\XML.txt");

            } catch (Exception ez) {
                ez.printStackTrace();
            }
            cleanXML("data\\XML.txt");

        }
    }

    /**
     * A Action listener for DBListen button
     */
    public class createDBlistener implements ActionListener {
        /**
         * An action listener that will listen to the dropTable Button and preform an action once it knows the user has interacted with the button
         *
         * @param e The action
         */
        //The override Action
        @Override
        public void actionPerformed(ActionEvent e) {
            dbc.execute("DROP TABLE BOOK");
            createDB();
            System.out.println("done");

        }
    }

    /**
     * The action listener for the HTML button
     */
    public class htmlPage implements ActionListener {
        /**
         * An action listener that will listen to the dropTable Button and preform an action once it knows the user has interacted with the button
         *
         * @param e The action
         */
        //The override Action
        @Override
        public void actionPerformed(ActionEvent e) {
            INET in = new INET();
            try {
                in.saveToFile("data\\avia151.htm", makeHTMLPage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }

    /**
     * The action listener for the logger button
     */
    public class loggerbtn implements ActionListener {
        /**
         * An action listener that will listen to the dropTable Button and preform an action once it knows the user has interacted with the button
         *
         * @param e The action
         */
        //The override Action
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Runtime.getRuntime().exec("explorer.exe /select," + "C:\\Users\\" + System.getProperty("user.name") + "\\IdeaProjects\\A19014\\doc\\logger.txt");
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

}
