import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/**
 @author - Daniel Janes
 @email -
 @studentid -
 @assignment.number -
 @version - V 2.0
 @prgm.usage - Called From the jar file
 @screenprint - <a href='A19009.gif'>Screen Print</a>
 @SubClass <br><a target='_blank' href='https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html'>Combo Box</a>
 */

public class A19009 extends JDialog {
    private JPanel contentPane;
    private JButton btnReport;
    private JComboBox cbLocation;
    private JComboBox cbAlt;
    private JLabel lbWindDir;
    private JLabel lbWindSpeed;
    private JLabel lbWindTemp;
    private JLabel lbLat;
    private JLabel lbLong;
    private JLabel lbAlt;
    private JButton buttonCancel;
    INET in = new INET();
    public static Sta sta;
    WorldStations ws;

    public Stations st = new Stations("data\\FBIN.txt");

    /**
     * Default Constructor
     */
    public A19009() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnReport);

        btnReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


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
     * Default Method
     */
    private void onOK() {
// add your code here
        dispose();
    }

    /**
     * Default Method
     */
    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    /**
     * Will check to see if a file exists and if It does then it will do nothing. If there is no file then It will download it from the URL and if blnextract is true then it will run it through the pre data
     * @param strFileName The File name to check or create
     * @param strURL The URL from which to download
     * @param blnExtract The boolean on weather or not to run it through getPREData()
     */
    public void getFile(String strFileName, String strURL, boolean blnExtract) {
        //if the file does NOT exist
        if (!in.fileExists(strFileName)) {
            //if the extract value is true
            if (blnExtract) {
                try {
                    //makes a new file
                    File f = new File(strFileName);
                    //makes a new printwriter
                    PrintWriter p = new PrintWriter(f);
                    //writes the getURLRaw data after it was run through getPREData
                    p.print(in.getPREData(in.getURLRaw(strURL)));
                    //closes the print
                    p.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    //makes a new file
                    File f = new File(strFileName);
                    //makes a new printwriter
                    PrintWriter p = new PrintWriter(f);
                    //prints the getURLRaw content to the printwriter
                    p.print(in.getURLRaw(strURL));
                    //closes the print writer
                    p.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Populates the Combo Boxes and adds the action listener
     */
    public void populateComboBox() {
        //Goes the full length of the Station ID arraylist
        for (int i = 0; i <= st.length(); i++) {
            //just a safty mech to make sure it doesnt go over the 175 entries
            if (i >= 175) {

            } else {
                //adds the Sta ID to the combo box
                cbLocation.addItem(st.getStationID(i)+" "+in.properCase(ws.getName(st.getStationID(i))));
            }
        }
        //add all the alts to the combo box
        cbAlt.addItem("3000");
        cbAlt.addItem("6000");
        cbAlt.addItem("9000");
        cbAlt.addItem("12000");
        cbAlt.addItem("18000");
        cbAlt.addItem("24000");
        cbAlt.addItem("30000");
        cbAlt.addItem("34000");
        cbAlt.addItem("39000");
        //Adds the ActionListener to both combo boxes
        cbAlt.addActionListener(new actionLisenerForComboBox());
        cbLocation.addActionListener(new actionLisenerForComboBox());
        btnReport.addActionListener(new btReportListener());
    }

    /**
     * Will update the Labels with the correct info from the Combo boxes
     */
    private void updateLabels() {
        NWSFB05 nwsfb05 = new NWSFB05("");
        //gets the Alt
        String staAlt = cbAlt.getSelectedItem().toString();
        //gets the Station ID
        String staID = cbLocation.getSelectedItem().toString().substring(0,3);
        //Updates the Station ID
        nwsfb05.getStationID(staID);
        //Updates the Latitude Label
        lbLat.setText(ws.getLatitude(staID));
        //Updates the Longitude Label
        lbLong.setText(ws.getLongitude(staID));
        //Updates the Wind Direction Label
        lbWindDir.setText(nwsfb05.getWindDir(convertAlt(staAlt)));
        //Updates the Wind Speed Label
        lbWindSpeed.setText(nwsfb05.getWindSpeed(convertAlt(staAlt)));
        //Updates the Wind Temp Label
        lbWindTemp.setText(nwsfb05.getWindTemp(convertAlt(staAlt)));
        //Updates the Altitude Label
        lbAlt.setText(ws.getAltiude(staID));
    }

    /**
     * Converts the Alt to a usable format for NWSFB05
     *
     * @param str The alt from the combo box
     * @return The Alt that can be used by NWSFB05
     */
    public String convertAlt(String str) {
        if (str.equals("3000")) {
            return "03";
        } else if (str.equals("6000")) {
            return "06";
        } else if (str.equals("9000")) {
            return "09";
        } else if (str.equals("12000")) {
            return "12";
        } else if (str.equals("18000")) {
            return "18";
        } else if (str.equals("24000")) {
            return "24";
        } else if (str.equals("30000")) {
            return "30";
        } else if (str.equals("34000")) {
            return "34";
        } else if (str.equals("39000")) {
            return "39";
        } else {
            return "";
        }
    }

    /**
     * Will Print Out the info to the FBOUT file
     */
    public void btnReportFunction(){
        int i=0;
        File f = new File("data\\FBOUT.txt");
        try {
            PrintWriter pw = new PrintWriter(f);

            while (i < st.length()) {
                boolean station = false;
                while (station == false) {
                    String staID = st.getStationID(i);
                    NWSFB05 nwsfb05 = new NWSFB05("");
                    nwsfb05.getStationID(staID);
                    pw.println("K" + staID + " - " + in.properCase(ws.getName(staID)) + "\n  Latitude: " + ws.getLatitude(staID) + "\n  Longitude: " + ws.getLongitude(staID) + "\n  Elevation: " + ws.getAltiude(staID) + " meters \n  Station Weather for " + st.staList.get(i) + "\n  At 03,000 Feet:    Dir= " + nwsfb05.getWindDir("03") + " Speed= " + nwsfb05.getWindSpeed("03") + " Temp= " + nwsfb05.getWindTemp("03"));
                    nwsfb05 = new NWSFB05("");
                    nwsfb05.getStationID(staID);
                    pw.println("  At 06,000 Feet:    Dir= " + nwsfb05.getWindDir("06") + " Speed= " + nwsfb05.getWindSpeed("06") + " Temp= " + nwsfb05.getWindTemp("06"));
                    nwsfb05 = new NWSFB05("");
                    nwsfb05.getStationID(staID);
                    pw.println("  At 09,000 Feet:    Dir= "+nwsfb05.getWindDir("09")+" Speed= "+nwsfb05.getWindSpeed("09")+" Temp= "+nwsfb05.getWindTemp("09"));
                    nwsfb05 = new NWSFB05("");
                    nwsfb05.getStationID(staID);
                    pw.println("  At 12,000 Feet:    Dir= "+nwsfb05.getWindDir("12")+" Speed= "+nwsfb05.getWindSpeed("12")+" Temp= "+nwsfb05.getWindTemp("12"));
                    nwsfb05 = new NWSFB05("");
                    nwsfb05.getStationID(staID);
                    pw.println("  At 18,000 Feet:    Dir= "+nwsfb05.getWindDir("18")+" Speed= "+nwsfb05.getWindSpeed("18")+" Temp= "+nwsfb05.getWindTemp("18"));
                    nwsfb05 = new NWSFB05("");
                    nwsfb05.getStationID(staID);
                    pw.println("  At 24,000 Feet:    Dir= "+nwsfb05.getWindDir("24")+" Speed= "+nwsfb05.getWindSpeed("24")+" Temp= "+nwsfb05.getWindTemp("24"));
                    nwsfb05 = new NWSFB05("");
                    nwsfb05.getStationID(staID);
                    pw.println("  At 30,000 Feet:    Dir= "+nwsfb05.getWindDir("30")+" Speed= "+nwsfb05.getWindSpeed("30")+" Temp= "+nwsfb05.getWindTemp("30"));
                    nwsfb05 = new NWSFB05("");
                    nwsfb05.getStationID(staID);
                    pw.println("  At 34,000 Feet:    Dir= "+nwsfb05.getWindDir("34")+" Speed= "+nwsfb05.getWindSpeed("34")+" Temp= "+nwsfb05.getWindTemp("34"));
                    nwsfb05 = new NWSFB05("");
                    nwsfb05.getStationID(staID);
                    pw.println("  At 39,000 Feet:    Dir= "+nwsfb05.getWindDir("39")+" Speed= "+nwsfb05.getWindSpeed("39")+" Temp= "+nwsfb05.getWindTemp("39")+"\n");
                    station=true;
                }

                i++;
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * The method called after the dialog has been created. It is meant to call all necessary methods before the GUI is presented
     */
    private void onStart() {
        ws = new WorldStations("data\\FBIN.txt", "data\\World.txt");
        populateComboBox();
        this.getFile("data\\FBIN.txt", "http://jcouture.net/CISC190/FBIN.txt", false);
        this.getFile("data\\World.txt", "http://weather.noaa.gov/data/nsd_bbsss.txt",false);
    }

    /**
     * Main default Method
     * @param args Default param
     */
    public static void main(String[] args) {
        A19009 dialog = new A19009();
        dialog.pack();

        dialog.onStart();
        dialog.setVisible(true);

        System.exit(0);
    }

    //An action Listener for both combo boxes
    public class actionLisenerForComboBox implements ActionListener {

        /**
         * An action listener that will listen to the combo boxes and preform an action once it knows the user has interacted with the box
         * @param e The action
         */
        //The overide Action
        @Override
        public void actionPerformed(ActionEvent e) {
            //calls SetLables()
            updateLabels();
        }
    }
    public class btReportListener implements ActionListener {

        /**
         * An action listener that will listen to the combo boxes and preform an action once it knows the user has interacted with the box
         * @param e The action
         */
        //The overide Action
        @Override
        public void actionPerformed(ActionEvent e) {
            //calls SetLables()
            btnReportFunction();
        }
    }
}
