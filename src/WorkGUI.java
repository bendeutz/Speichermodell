import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ben on 01.06.2017.
 */
public class WorkGUI {
    private JFrame mainFrame;
    private JPanel panel1;
    private JButton variableInitialisierenButton;
    private JButton variableÄndernButton;
    private VariableInitiateGUI variableInitiateGUI;
    private GUI gui;
    private VariableChangeGUI variableChangeGUI;

    public WorkGUI(GUI gui){
        this.gui = gui;
        mainFrame = new JFrame("Wähle aus");
        mainFrame.setContentPane(panel1);
        panel1.setPreferredSize(new Dimension(200,200));
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(gui.getFrame());
        variableInitiateGUI = new VariableInitiateGUI(gui);
        variableChangeGUI = new VariableChangeGUI(gui);
        variableInitialisierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                variableInitiateGUI.start();
                mainFrame.setVisible(false);
                gui.setButton(false);
            }
        });
        variableÄndernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                variableChangeGUI.start();
                mainFrame.setVisible(false);
                gui.setButton(false);
            }
        });
    }

    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainFrame.setVisible(true);
            }
        });
    }

    public int getX() {
        return mainFrame.getX();
    }

    public int getY() {
        return mainFrame.getY();
    }
}
