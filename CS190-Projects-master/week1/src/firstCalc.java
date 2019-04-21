import javax.swing.*;
import java.awt.event.*;
//Coded by Daniel Janes for Comp190
//Finished on 8/28/15

public class firstCalc extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField PurchaseTextField;
    private JLabel StateTax;
    private JLabel CountyTax;
    private JLabel TotalSalesTax;
    private JLabel TotalAmount;
    private JLabel StateSalesTaxOutput;
    private JLabel CountySalesTaxOutput;
    private JLabel TotalSalesTaxOutput;
    private JLabel TotalAmountOutput;
    private JPanel leftPane;
    private JPanel rightPane;
    private JPanel test1;
    private JPanel test2;
    private JPanel test4;
    private double PurchaseDouble;


    public firstCalc() {
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

    private void caculateAndSet(){
        String temp = PurchaseTextField.getText();
    PurchaseDouble = Double.parseDouble(temp);

        Double StateSalesD = PurchaseDouble*0.04;
        StateSalesTaxOutput.setText("$"+String.valueOf(StateSalesD));

        //county

        Double CountySalesD = PurchaseDouble*.02;
        CountySalesTaxOutput.setText("$"+String.valueOf(CountySalesD));

        //county+state

        Double TotalTaxD = CountySalesD+StateSalesD;
        TotalSalesTaxOutput.setText("$"+String.valueOf(TotalTaxD));

        //total
        String TotalTax = String.valueOf(TotalTaxD+PurchaseDouble);
        TotalAmountOutput.setText("$"+String.valueOf(TotalTax));
    }
    private void clear(){
    CountySalesTaxOutput.setText("");
        TotalSalesTaxOutput.setText("");
        TotalAmountOutput.setText("");
        StateSalesTaxOutput.setText("");
    }

    private void onOK() {
// add your code here
       caculateAndSet();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        firstCalc dialog = new firstCalc();
        dialog.clear();
        dialog.pack();
        dialog.setVisible(true);

        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
