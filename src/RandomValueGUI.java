import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by ben on 06.06.2017.
 */
public class RandomValueGUI {
    private JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton erstellenButton;
    private JLabel randomLabel;
    private GUI gui;
    private JFrame mainFrame;


    public RandomValueGUI(GUI gui) {
        this.gui = gui;
        this.mainFrame = new JFrame();
        panel.setPreferredSize(new Dimension(450,200));
        mainFrame.setContentPane(panel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(gui.getFrame());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });
        erstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int min = Integer.parseInt(textField1.getText());
                    int max = Integer.parseInt(textField2.getText());
                    int diff = max-min;
                    if(diff > 0) {
                        int random = (int) (Math.random() * (diff+1))  + min;
                        randomLabel.setText(Integer.toString(random));
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException en) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Beide Felder müssen mit ganzen Zahlen ausgefüllt sein, wobei das Minimum kleiner als das Maximum sein sollte.",
                            "False entries",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mainFrame.getRootPane().setDefaultButton(erstellenButton);
    }

    public void start() {
        mainFrame.setVisible(true);
    }
}
