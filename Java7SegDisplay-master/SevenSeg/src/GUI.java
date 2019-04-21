import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JButton btA;
    private JButton btB;
    private JButton btF;
    private JButton btC;
    private JButton btG;
    private JButton btE;
    private JButton btD;
    public boolean bA = false;
    public boolean bB = false;
    public boolean bC = false;
    public boolean bD = false;
    public boolean bE = false;
    public boolean bF = false;
    public boolean bG = false;
    public int binary = 0;
    public File file = new File("data\\out.txt");

    public GUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        btA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!bA) {
                    binary++;
                    bA = true;
                    btA.setBackground(Color.RED);
                } else {
                    bA = false;
                    binary--;
                    btA.setBackground(Color.white);
                }

                set();
            }
        });

        btB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!bB) {
                    binary += 10;
                    bB = true;
                    btB.setBackground(Color.RED);
                } else {
                    bB = false;
                    binary -= 10;
                    btB.setBackground(Color.white);
                }

                set();
            }
        });
        btC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!bC) {
                    binary += 100;
                    bC = true;
                    btC.setBackground(Color.RED);
                } else {
                    bC = false;
                    binary -= 100;
                    btC.setBackground(Color.white);
                }

                set();
            }
        });
        btD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!bD) {
                    binary += 1000;
                    bD = true;
                    btD.setBackground(Color.RED);
                } else {
                    bD = false;
                    binary -= 1000;
                    btD.setBackground(Color.white);
                }

                set();
            }
        });
        btE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!bE) {
                    binary += 10000;
                    bE = true;
                    btE.setBackground(Color.RED);
                } else {
                    bE = false;
                    binary -= 10000;
                    btE.setBackground(Color.white);
                }

                set();
            }
        });
        btF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!bF) {
                    binary += 100000;
                    bF = true;
                    btF.setBackground(Color.RED);
                } else {
                    bF = false;
                    binary -= 100000;
                    btF.setBackground(Color.white);
                }

                set();
            }
        });

        btG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!bG) {
                    binary += 1000000;
                    bG = true;
                    btG.setBackground(Color.RED);
                } else {
                    bG = false;
                    binary -= 1000000;
                    btG.setBackground(Color.white);
                }

                set();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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

    public void set() {
        if (binary <= 0) {
            textField1.setText("0000000");
        } else if (binary >= 1000000) {
            textField1.setText(String.valueOf(binary));
        } else if (binary >= 100000) {
            textField1.setText("0" + binary);
        } else if (binary >= 10000) {
            textField1.setText("00" + binary);
        } else if (binary >= 1000) {
            textField1.setText("000" + binary);
        } else if (binary >= 100) {
            textField1.setText("0000" + binary);
        } else if (binary >= 10) {
            textField1.setText("00000" + binary);
        } else if (binary >= 1) {
            textField1.setText("000000" + binary);
        } else {
            textField1.setText("ERROR");
        }

    }

    private void onOK() {
// add your code here
        try {
            PrintWriter pw = new PrintWriter("data\\out.txt");
            pw.println(textField1.getText());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        GUI dialog = new GUI();

        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
