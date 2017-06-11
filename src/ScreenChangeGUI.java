import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by ben on 06.06.2017.
 */
public class ScreenChangeGUI {
    private JButton variableAusgebenButton;
    private JComboBox comboBox1;
    private JButton textUndEingestellteVariableButton;
    private JPanel panel;
    private JTextField textField1;
    private GUI gui;
    private JFrame mainFrame;

    public ScreenChangeGUI(GUI gui) {
        this.gui = gui;
        this.mainFrame = new JFrame("Eine Bildschirmausgabe erstellen.");
        panel.setPreferredSize(new Dimension(480,200));
        mainFrame.setContentPane(panel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(gui.getFrame());

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if(comboBox1.getItemCount() > 0)
                    comboBox1.setSelectedIndex(0);
            }
        });
        textUndEingestellteVariableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField1.getText();
                if(comboBox1.getItemCount() > 0 ) {
                    text = text + " " + gui.getValueOf((String) comboBox1.getSelectedItem());
                }
                gui.setScreen(text);
                mainFrame.setVisible(false);
            }
        });
        variableAusgebenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getItemCount()>0) {
                    gui.setScreen(gui.getValueOf((String) comboBox1.getSelectedItem()));
                }
                mainFrame.setVisible(false);
            }
        });
    }

    public void start() {
        comboBox1.removeAllItems();
        for (Variable variable : gui.getVariables()) {
            comboBox1.addItem(variable.getName());
        }
        textField1.setText("");
        mainFrame.setVisible(true);
    }
}
