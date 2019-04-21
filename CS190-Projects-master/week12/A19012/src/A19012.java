import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

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

public class A19012 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JLabel lblName;
    private JLabel lblState;
    private JLabel lblLat;
    private JLabel lblLong;
    private JLabel lblElevation;
    private JLabel lblWD;
    private JLabel lblWS;
    private JLabel lblWT;
    private JLabel lblHum;
    private JLabel lblDew;
    private JLabel lblStationID;
    private String[] ar = {"Surface", "3000", "6000", "9000", "12000", "18000", "24000", "30000", "34000", "39000"};
    private JList<Object> list1;
    private JLabel lblpic;
    String id = "";
    INET in = new INET();
    dbUpdt dbU = new dbUpdt();
    dbRead dbR = new dbRead();
    NWSFB05 nw = new NWSFB05("");
    XMLRead xmlr = new XMLRead();
    int i = 0;

    /**
     * Default function
     */
    public A19012() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });


        list1.addListSelectionListener(new actionLisenerForJList());
        comboBox1.addActionListener(new actionLisenerForComboBox());
    }

    /**
     * Default function
     */
    private void onOK() {
// add your code here
        dispose();
    }


    /**
     * Is the main function that is called which will set up the program
     */
    public void formLoad() {

        try {
            //opens a connection to the database Weather
            dbR.openConnection("Weather");
            //Will drop the table called Stations
            dbU.dropTables("STATIONS");
            //Will date the stations schema to disk
            in.saveToFile("data\\schema_stations.txt", (in.getURLRaw("http://jcouture.net/data/schema_stations.txt")));
            //will parse the schema file and send that command to be executed in the db update object
            dbU.execute(sqlFileParse("data\\schema_stations.txt"));
            //will dave the current FBIN to disk
            in.saveToFile("data\\FBIN.txt", in.getPREData(in.getURLRaw("http://aviationweather.gov/products/nws/all?fint=06&lvl=lo")));
            //test
            //System.out.println(in.getPREData(in.getURLRaw("http://aviationweather.gov/products/nws/all?fint=06&lvl=lo")));

            //Will save the USA stations file to disk
            in.saveToFile("data\\USAStations.txt", (in.getURLRaw("http://jcouture.net/data/USAStations.txt")));
            //Will call the USA stations function
            USAStationsRead();
            //
            combobox();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Will parse the File and return a SQL statement to run
     * @param fileName The file name to parse
     * @return The SQL to create a new table
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

            //will return the toString of the string builder
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //This line will never be reached but is required because java doesn't like a possibliity of nothing being returned
        return "ERROR: This statement should never be reached";
    }

    /**
     * Will read the USAStations text file and execute the insert commands
     */
    public void USAStationsRead() {
        //makes a new file out of the USA stations
        File f = new File("data\\USAStations.txt");
        try {
            //makes a scanner
            Scanner scan = new Scanner(f);
            //makes a temp string
            String tempz = "";
            StringBuilder sb = new StringBuilder();
            //while scanner has a next line
            while (scan.hasNext()) {
                //if the scan has a next line, extra line of defense
                if (scan.hasNext()) {
                    //temp now = the next line of scan
                    tempz = scan.nextLine();
                    //If the first 2 numbers are 73 or 74 which is the code for the USA
                    if (tempz.substring(7, 8).equals("K")) {
                        //makes a new int for each ;

                        //PLEASE NOTE there is a better and faster way of using .split(";") but I originally wrote it this way and
                        //if its not broken then don't fix it.

                        int one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve;
                        //Will get the index of each ; in the string
                        one = tempz.indexOf(";");
                        two = tempz.indexOf(";", one + 1);
                        three = tempz.indexOf(";", two + 1);
                        four = tempz.indexOf(";", three + 1);
                        five = tempz.indexOf(";", four + 1);
                        six = tempz.indexOf(";", five + 1);
                        seven = tempz.indexOf(";", six + 1);
                        eight = tempz.indexOf(";", seven + 1);
                        nine = tempz.indexOf(";", eight + 1);
                        ten = tempz.indexOf(";", nine + 1);
                        eleven = tempz.indexOf(";", ten + 1);
                        twelve = tempz.indexOf(";", eleven + 1);

                        //will append the base SQL command to the String builder
                        sb.append("INSERT INTO STATIONS (stationid, stationname, state, latitude, longitude, elevation) VALUES (");
                        //Station ID
                        sb.append("'" + tempz.substring(two + 2, three) + "',");
                        //if the name is longer than 50 characters
                        if (tempz.substring(three + 1, four).length() > 50) {
                            //will remove the ' from the name since SQL doesn't like ' in the commands
                            String add = "'" + tempz.substring(three + 1, three + 48).replace("'", "") + "',";
                            //will replace the , in the string with ""
                            add = add.replace(",", "");
                            //adds a , at the end since if you add it before it will get removed my the previs command
                            add = add + ",";
                            //appends the command to the string builder
                            sb.append(add);
                        } else {
                            //will remove the ' from the name since SQL doesn't like ' in the commands
                            String add = "'" + tempz.substring(three + 1, four).replace("'", "") + "', ";
                            //will replace the , in the string with ""
                            add = add.replace(",", "");

                            //adds a , at the end since if you add it before it will get removed my the previs command
                            add = add + ",";
                            //appends the command to the string builder
                            sb.append(add);
                        }
                        //adds the state
                        sb.append("'" + tempz.substring(four + 1, five) + "', ");
                        //adds the lat
                        sb.append("'" + tempz.substring(seven + 1, eight) + "', ");
                        //adds the long
                        sb.append("'" + tempz.substring(eight + 1, nine) + "', ");
                        //adds the elevation AND a ) at the end
                        sb.append("'" + tempz.substring(eleven + 1, twelve) + "')");
                        // will execute the string builder command
                        dbU.execute(sb.toString());
                        //will reset the string builder to nothing so it can start fresh
                        sb.setLength(0);
                    }
                } else {
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Will populate the combobox with the data from the database
     */

    public void combobox() {
        //will remove all the items first
        comboBox1.removeAllItems();
        //will query the database
        dbR.query("SELECT * FROM STATIONS");
        //will keep going if there are anymore records
        while (dbR.moreRecords()) {
            //will add the stationid and stationname vars to the combo box
            comboBox1.addItem(dbR.getField("stationid") + " - " + in.properCase(dbR.getField("stationname")));
        }
    }

    /**
     * Will close the program, also a default function
     */
    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    /**
     * Will update all of the labes and picture with the data
     */
    public void updateLables() {
        //if the selection of the jlist is nothing it will do nothing
        if (!list1.isSelectionEmpty()) {
            //will get the selected jlist item and cast it into a string
            String alt = (String) list1.getSelectedValue();
            //Test
            //System.out.println(alt);

            //If the item is 4 long (3000, 6000, 9000) then it will treat it special
            if (alt.length() == 4) {
                //Will update the wind dir
                lblWD.setText(nw.getWindDir(alt.substring(0, 1)));
                //Will update the wind speed
                lblWS.setText(nw.getWindSpeed(alt.substring(0, 1)));
                //Will update the wind temp
                lblWT.setText(nw.getWindTemp(alt.substring(0, 1)));
                //Will call updateFromXML with a special of nothing
                updateFromXML("K" + id, 0);
                //if it is 5 characters long aka 12000 and what not
            } else if (alt.length() == 5) {
                //Will update the wind dir
                lblWD.setText(nw.getWindDir(alt.substring(0, 2)));
                //Will update the wind speed
                lblWS.setText(nw.getWindSpeed(alt.substring(0, 2)));
                //Will update the wind temp
                lblWT.setText(nw.getWindTemp(alt.substring(0, 2)));
                //Will call updateFromXML with a special of nothing
                updateFromXML("K" + id, 0);
                //Will is when the user selects surface
            } else {
                //test
                //System.out.println("other");
                //Will call updateFromXML with a special of 1
                updateFromXML("K" + id, 1);
            }


        }

    }

    /**
     * Will update different lbl that the updateLable() function can't
     * @param stationID The station ID
     * @param more 0 for nothing and 1 for updating all the lbl's with the xml info (only used if the user selects surface as an alt)
     */
    private void updateFromXML(String stationID, int more) {
        //makes a new MyXML
        XMLRead myXML = new XMLRead();
        try {
            //Will load the xml webpage
            myXML.loadPage("http://w1.weather.gov/xml/current_obs/" + stationID + ".xml");
            //Will set the table to current_observation
            myXML.setTable("current_observation");
            //will set the dew point
            lblDew.setText(myXML.getField("dewpoint_c"));
            //will set the relative humidity
            lblHum.setText(myXML.getField("relative_humidity"));
            //will get the icon url name and call the updatepic() with it
            updatePic(myXML.getField("icon_url_name"));
            //if more is 1
            if (more == 1) {
                //sets the wind dir
                lblWD.setText(myXML.getField("wind_degrees"));
                //sets the wind speed
                lblWS.setText(myXML.getField("wind_mph"));
                //sets the wind temp
                lblWT.setText(myXML.getField("temp_c"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Will update the icon picture
     * @param PicURL the url name for the picture, the base url is already provided
     */
    private void updatePic(String PicURL) {
        //makes a new image
        Image image = null;
        //makes the url for the image
        PicURL = "http://forecast.weather.gov/images/wtf/large/" + PicURL;
        try {
            //will make a new URL out of the string url
            URL url = new URL(PicURL);
            //will set the image to the url image
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //makes an ImageIcon out of the image
        ImageIcon imgThisImg = new ImageIcon(image);
        //Clears the lbl
        lblpic.setText("");
        //adds the image to the label
        lblpic.setIcon(imgThisImg);
    }

    /**
     * The main default constructor
     * @param args default
     */
    public static void main(String[] args) {
        A19012 dialog = new A19012();
        dialog.pack();
        dialog.formLoad();
        dialog.setVisible(true);
        System.exit(0);
    }

    /**
     * The action listener for the jList
     */
    public class actionLisenerForJList implements ListSelectionListener {

        /**
         * An action listener that will listen to the jlist and preform an action once it knows the user has interacted with the jlist
         *
         * @param e The action
         */
        //The overide Action
        @Override
        public void valueChanged(ListSelectionEvent e) {
            //test
            //System.out.println("2");

            //calls updatelabes
            updateLables();
        }
    }

    public class actionLisenerForComboBox implements ActionListener {

        /**
         * An action listener that will listen to the combo boxes and preform an action once it knows the user has interacted with the box
         *
         * @param e The action
         */
        //The overide Action
        @Override
        public void actionPerformed(ActionEvent e) {
            //checks to see if the user has clicked the combo box before,
            //PREVENTS CRASHING WHEN THE PROGRAM STARTS UP
            if (i == 0) {
                //will set the int to 1 which makes this if never reached again
                i++;
            } else {


                try {
                    //gets the string from the combo box
                    String temp = (String) comboBox1.getSelectedItem();
                    //test/old function
                    //downloadXML("K" + temp.substring(0, 3));
                    //will update the strStationWeather string in the nw class to be used later in this class
                    nw.getStationData(temp.substring(0, 3));
                    //queries the database and gets everything where the stationID matches the one selected in the combo box
                    dbR.query("SELECT * FROM STATIONS WHERE stationid='" + temp.substring(0, 3) + "'");

                    //while there are more records
                    while (dbR.moreRecords()) {
                        //sets the state
                        lblState.setText(dbR.getField("state"));
                        //sets the station id
                        lblStationID.setText(temp.substring(0, 3));
                        //sets the name
                        lblName.setText(temp.substring(6));
                        //sets the latitude
                        lblLat.setText(dbR.getField("latitude"));
                        //sets the longitude
                        lblLong.setText(dbR.getField("longitude"));
                        //sets the elevation
                        lblElevation.setText(dbR.getField("elevation"));
                    }
                    //calls updatelabes to update the rest of the labes based on the alt
                    updateLables();
                    //sets the station ID var to be called from other parts of the class
                    id = temp.substring(0, 3);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }


}
