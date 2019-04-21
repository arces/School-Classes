import javax.swing.*;
import java.awt.event.*;

public class A19007 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox cbLocation;
    private JComboBox cbAlt;
    private JLabel lbWindDir;
    private JLabel lbWindSpeed;
    private JLabel lbWindTemp;
    private static Stations st = new Stations("data\\FBIN.txt");

    /**
     * @author - Daniel Janes
     @email -
     @studentid -
     @assignment.number - A19007
     @version - V 2.0
     @prgm.usage - Called From the jar file
     @screenprint - <a href='pic.gif'>Screen Print</a>
     @see <br><a target='_blank' href='https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html#listeners'>Combo Box</a>

     */

    //The Main constructor
    public A19007() {
        setContentPane(contentPane);

        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


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

    //default function
    private void onOK() {
// add your code here
        dispose();
    }

    //default function
    private void onCancel() {
// add your code here if necessary
        dispose();
    }



    /**
     *Populates the Combo Boxes and adds the action listener
     */
    public void populateComboBox() {
        //Goes the full length of the Station ID arraylist
        for (int i = 0; i <= st.length(); i++) {
            //just a safty mech to make sure it doesnt go over the 175 entries
            if (i >= 175) {

            } else {
                //adds the Sta ID to the combo box
                cbLocation.addItem(st.getStationID(i));
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
     * Sets the Lables based on info from the combo boxes and FBIN.txt
     */
    public void setLables() {
        //Gets the Station ID from the Combo Box
        String sta = String.valueOf(cbLocation.getSelectedItem());
        //Makes a new NWSFB05 object
        NWSFB05 nw = new NWSFB05(st.getStaWea(sta));

        //gets the alt from the combo box
        String alt = convertAlt(String.valueOf(cbAlt.getSelectedItem()));

        //Makes temp vars to be used
        String WindTemp = "";
        String Celsius = "C";

        //Sees if the Temp is N/A and if it is then it does not add the "C" at the end
        if ("N/A".equals(nw.getWindTemp(alt))) {
            WindTemp = nw.getWindTemp(alt);
        } else {
            WindTemp = nw.getWindTemp(alt) + Celsius;
        }

        //sets the labels to the correct info
        lbWindDir.setText(nw.getWindDir(alt));
        lbWindSpeed.setText(nw.getWindSpeed(alt));
        lbWindTemp.setText(WindTemp);
    }

    /**
     * Default main
     *
     * @param args Default main
     */
    public static void main(String[] args) {
        A19007 dialog = new A19007();
        dialog.setTitle("A19007 - Daniel Janes");
        dialog.populateComboBox();

        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    //default meathod
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    //An action Listener for both combo boxes
    public class actionLisenerForComboBox implements ActionListener {

        //The overide Action
        @Override
        public void actionPerformed(ActionEvent e) {
            //calls SetLables()
            setLables();
        }
    }

}