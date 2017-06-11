
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by ben on 01.06.2017.
 */
public class VariableInitiateGUI {
    private JComboBox comboBox1;
    private JTextField textField1;
    private JPanel panel1;
    private JButton bestätigenButton;
    private JFrame mainFrame;
    private GUI gui;

    public VariableInitiateGUI(GUI gui) {
        this.gui = gui;
        mainFrame = new JFrame("Variable initialisieren.");
        comboBox1.addItem("int");
        comboBox1.addItem("double");
        comboBox1.addItem("String");
        comboBox1.addItem("char");
        panel1.setPreferredSize(new Dimension(250,200));
        mainFrame.setContentPane(panel1);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(gui.getFrame());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                gui.setButton(true);
            }
        });
        bestätigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField1.getText().length()==0) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Der Name der Variable fehlt.",
                            "Missing entries",
                            JOptionPane.ERROR_MESSAGE);
                }  else {
                    boolean lettersOnly = true;
                    for (int i = 0; i < textField1.getText().length(); i++) {
                        char actualChar = textField1.getText().charAt(i);
                        if (!Character.isAlphabetic(actualChar)) {
                            lettersOnly = false;
                        }
                    }
                    if(lettersOnly) {
                        if(gui.initiateVariable(comboBox1.getSelectedItem().toString(), textField1.getText())) {
                            mainFrame.setVisible(false);
                            gui.setButton(true);
                            textField1.setText("");
                            comboBox1.setSelectedIndex(0);
                        } else {
                            JOptionPane.showMessageDialog(mainFrame,
                                    "Die Variable mit dem Namen existiert bereits.",
                                    "False entries",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainFrame,
                                "Der Name der Variable darf nur aus Buchstaben bestehen.",
                                "False entries",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        mainFrame.getRootPane().setDefaultButton(bestätigenButton);
    }

    public void start() {
        mainFrame.setVisible(true);
    }
}
