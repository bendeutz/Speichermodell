import javax.swing.*;
import java.awt.*;
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
                comboBox1.setSelectedIndex(0);
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
