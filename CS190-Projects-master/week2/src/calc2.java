import javax.swing.*;
import java.awt.event.*;
/**
@author - Dan Janes
@email -
@studentid - 
@assignment.number -  A19002
@version - 1.1
@prgm.usage - for example, called directly from the operating system
@screenprint - <a href='pic.GIF'>Screen Pring</a>
@link - https://sites.google.com/a/miramarsd.net/cisc190jc/course-overview/module-02
@link - <br><a href='http://docs.oracle.com/javase/7/docs/
technotes/guides/Javadoc/index.html'>Javadoc Documentation</a>
**/

public class calc2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField AircraftEmptyWeight;
    private JTextField BaggageWeight;
    private JTextField GallonsFuel;
    private JTextField NumPassFront;
    private JTextField NumPassBack;
    private JLabel RampWeightAircraft;
    final double WT_PERSON = 190.0;
    final double WT_AVGAS = 6.02;

    public calc2() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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

    private void calcForm(){

        double temp,aircraftempty, weightPass, FuelWeight, baggageWeight;
        aircraftempty = Double.parseDouble(AircraftEmptyWeight.getText());
        weightPass = (Double.parseDouble(NumPassBack.getText()) + Double.parseDouble(NumPassFront.getText())+1)*WT_PERSON;
        FuelWeight = (Double.parseDouble(GallonsFuel.getText())* WT_AVGAS);
        baggageWeight= Double.parseDouble(BaggageWeight.getText());
        RampWeightAircraft.setText(String.valueOf(aircraftempty+weightPass+FuelWeight+baggageWeight)+"pounds");


    }



    private void onOK() {
// add your code here
        calcForm();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        calc2 dialog = new calc2();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
