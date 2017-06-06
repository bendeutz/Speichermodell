import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by ben on 04.06.2017.
 */
public class VariableChangeGUI {
    private JPanel panel;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton bestätigenButton;
    private GUI gui;
    private JFrame mainFrame;

    public VariableChangeGUI(GUI gui) {
        this.gui = gui;
        this.mainFrame = new JFrame();
        panel.setPreferredSize(new Dimension(250,200));
        mainFrame.setContentPane(panel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(gui.getFrame());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                gui.setButton(true);
                textField1.setText("");
                if(comboBox1.getItemCount() > 0)
                    comboBox1.setSelectedIndex(0);
            }
        });
        bestätigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getItemCount()==0) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Es sind keine Variablen im Speicher.",
                            "Missing variable",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if(textField1.getText().length() == 0) {
                        JOptionPane.showMessageDialog(mainFrame,
                                "Bitte gib einen Wert an.",
                                "Missing value",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        if(gui.changeVariable((String)comboBox1.getSelectedItem(), textField1.getText())) {
                            mainFrame.setVisible(false);
                            gui.setButton(true);
                            textField1.setText("");
                            comboBox1.setSelectedIndex(0);
                        } else {
                            JOptionPane.showMessageDialog(mainFrame,
                                    "Der eingegebene Wert passt nicht für diesen Variablentyp.",
                                    "Wrong value",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    public void start(){
        comboBox1.removeAllItems();
        for (Variable variable : gui.getVariables()) {
            comboBox1.addItem(variable.getName());
        }
        mainFrame.setVisible(true);
    }
}
