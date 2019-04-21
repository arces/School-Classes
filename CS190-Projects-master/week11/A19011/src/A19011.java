import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

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
public class A19011 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JButton downloadSchemasButton;
    private JButton createTableButton;
    private JButton dropTableButton;
    private JLabel lblStatus;
    INET in = new INET();
    dbUpdt dbU = new dbUpdt();
    dbRead dbR = new dbRead();

    public A19011() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        //Test of the popup function
        //popup("HI");

        //adds the action listeners
        downloadSchemasButton.addActionListener(new downloadLisener());
        dropTableButton.addActionListener(new dropTableLisener());
        createTableButton.addActionListener(new createTableListener());
        //makes a new DB



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
     * default function
     */
    private void onOK() {
// add your code here
        dispose();
    }

    /**
     * default function
     */
    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    /**
     * Will Set the text of the lable to StrStatus
     * @param strStatus The text to be set as the lable
     */
    public void status(String strStatus) {
        //sets the lbl text to strStatus
        lblStatus.setText(strStatus);
    }

    /**
     * Will download the data file from jcouture.net/data/schema_NameInTextField
     */
    public void readSchema() {
        //gets the text from the textField
        String Name = textField1.getText();
        try {
            //Downloads the file from the URL
            String temp = in.getURLRaw("http://jcouture.net/data/schema_" + Name + ".txt");
            if (temp.equals("")) {
                //If there is nothing in the file (AKA there is no file) then it says it cant find the file
                status("ERROR Can't Find File");
            } else {
                //it saves the file to a file
                in.saveToFile("data/schema_" + Name + ".txt", in.getURLRaw("http://jcouture.net/data/schema_" + Name + ".txt"));
                status("Successful Download");
            }
        } catch (Exception e) {
            //alerts you that there was an error
            status("Error Downloading Function Crashed");
            e.printStackTrace();
        }
    }

    /**
     * Will drop the table that is in the text field
     */
    public void dropTable(String tableName) {
        //gets the text
        String temp = textField1.getText();
        //sets the text of the label to the return of the command of dropping the table
        try {
            lblStatus.setText(dbU.dropTables(temp));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //will append the command to commands.txt file
        in.appendToFile("data\\commands.txt", "DROP TABLE " + temp.toUpperCase());
    }

    /**
     * Will have a JOptionPane pop up and display text
     * @param strStatus The string to be displayed
     */
    public void popup(String strStatus) {
        //will pop up and alert you about the message of strStatus
        JOptionPane.showMessageDialog(null, strStatus);
    }

    /**
     * Will create a Table named @strName
     * @param strName The table name to be created
     */
    public void createTableSQL(String strName, String dataBaseName) {
        readSchema();
        String tempName = "data\\schema_" + strName + ".txt";
        dbR.openConnection("Weather");
        if (!in.fileExists(tempName)) {
            popup("File " + tempName + " does not exists");
        } else {
            dbU.execute(sqlFileParse(tempName));

        }
        dbR.close();

    }

    /**
     * Will parse the Text File and return one long SQL command
     * @param fileName The file name to parse
     * @return The SQL comand to create a table
     */
    public String sqlFileParse(String fileName) {
        StringBuilder sb = new StringBuilder();
        File f = new File(fileName);
        try {
            //Makes a new scanner
            Scanner scan = new Scanner(f);
            //new temp var
            int i = 1;
            //while scan has a next line
            while (scan.hasNext()) {
                //currentLine is now the next line of scan
                String currentLine = scan.nextLine();
                //if it is the first line of the file
                if (i == 1) {
                    //will append the text to the string builder
                    sb.append("CREATE TABLE " + currentLine + "(");
                    //Skips the parts we dont care about
                } else if (i >= 11) {
                    //will get the index of the first and second spaces
                    int in = currentLine.indexOf(" ");
                    int in2 = currentLine.indexOf(" ", in + 1);
                    //if the current line is NOT the last line
                    if (scan.hasNext()) {
                        //appends it to the string builder
                        sb.append(currentLine.substring(0, in) + currentLine.substring(in, in2) + ", ");
                    }
                    //if this is the last line of the file
                    else {
                        //appends
                        sb.append(currentLine.substring(0, in) + currentLine.substring(in, in2) + ")");
                    }

                } else {

                }
                i++;
            }
            //will append the command to the commands.txt file
            in.appendToFile("data\\commands.txt", sb.toString());
            //sets the label
            lblStatus.setText("Table Created");
            //will return the toString of the string builder
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //This line will never be reached but is required because java doesn't like a possibliity of nothing being returned
        return "ERROR: This statement should never be reached";
    }

    /**
     * The main Function
     * @param args Default input
     */
    public static void main(String[] args) {
        A19011 dialog = new A19011();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    /**
     * The Action Listener for the download button
     */
    public class downloadLisener implements ActionListener {
        /**
         * An action listener that will listen to the downloadbutton and preform an action once it knows the user has interacted with the button
         *
         * @param e The action
         */
        //The overide Action
        @Override
        public void actionPerformed(ActionEvent e) {
            //calls downloadSchemas()
            readSchema();
        }
    }
    /**
     * The Action Listener for the drop table button
     */
    public class dropTableLisener implements ActionListener {
        /**
         * An action listener that will listen to the dropTable Button and preform an action once it knows the user has interacted with the button
         *
         * @param e The action
         */
        //The override Action
        @Override
        public void actionPerformed(ActionEvent e) {
            //calls dropTable()
            dropTable("");
        }
    }

    /**
     * The Action Listener for the create table button
     */
    public class createTableListener implements ActionListener {
        /**
         * An action listener that will listen to the dropTable Button and preform an action once it knows the user has interacted with the button
         *
         * @param e The action
         */
        //The override Action
        @Override
        public void actionPerformed(ActionEvent e) {
            //calls createTableSQL() with the text from the text box
            createTableSQL(textField1.getText(),"");
        }
    }
}
