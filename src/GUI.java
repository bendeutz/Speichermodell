import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ben on 31.05.2017.
 */
public class GUI {
    private WorkGUI workgui;
    private JFrame mainFrame, workFrame;
    private JPanel mainPanel, workPanel;
    private JLabel typeOne;
    private JLabel nameOne;
    private JLabel valueOne;
    private JLabel typeTwo;
    private JLabel typeThree;
    private JLabel typeFour;
    private JLabel typeFive;
    private JLabel nameTwo;
    private JLabel nameThree;
    private JLabel nameFour;
    private JLabel nameFive;
    private JLabel valueTwo;
    private JLabel valueThree;
    private JLabel valueFour;
    private JLabel valueFive;
    private JButton speicherBearbeitenButton;
    private ArrayList<Variable> allVariables;
    private ArrayList<JLabel> allTypes;
    private ArrayList<JLabel> allNames;
    private ArrayList<JLabel> allValues;

    public GUI() {
        mainPanel.setPreferredSize(new Dimension(600, 600));
        allVariables = new ArrayList<>();
        allTypes = new ArrayList<>();
        allTypes.add(typeOne);
        allTypes.add(typeTwo);
        allTypes.add(typeThree);
        allTypes.add(typeFour);
        allTypes.add(typeFive);

        allNames = new ArrayList<>();
        allNames.add(nameOne);
        allNames.add(nameTwo);
        allNames.add(nameThree);
        allNames.add(nameFour);
        allNames.add(nameFive);

        allValues = new ArrayList<>();
        allValues.add(valueOne);
        allValues.add(valueTwo);
        allValues.add(valueThree);
        allValues.add(valueFour);
        allValues.add(valueFive);

        speicherBearbeitenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workgui.start();
            }
        });

    }

    public void start() {
        mainFrame = new JFrame("Speichermodell");
        mainFrame.setResizable(false);
        mainFrame.setContentPane(this.mainPanel);
        mainFrame.setVisible(true);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        workgui = new WorkGUI(this);
    }

//    public static void main(String... args) {
//        mainFrame = new JFrame("Speichermodell");
//        mainFrame.setResizable(false);
//        gui = new GUI();
//        mainFrame.setContentPane(gui.mainPanel);
//        mainFrame.setVisible(true);
//        mainFrame.pack();
//        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        mainFrame.setLocationRelativeTo(null);
//        workgui = new WorkGUI();
//    }

    public boolean initiateVariable(String type, String name) {
        Variable variable = new Variable(type, name, null);
        if(allVariables.contains(variable)) {
            return false;
        } else {
            allVariables.add(variable);
            updateUI();
            return true;
        }
    }

    private void updateUI() {
        for (int i = 0; i < 5; i++) {
            if(allVariables.size() > i) {
                allTypes.get(i).setText(allVariables.get(i).getType());
                allNames.get(i).setText(allVariables.get(i).getName());
                allValues.get(i).setText(allVariables.get(i).getValue());
            }
        }
    }

    public Component getFrame() {
        return mainFrame;
    }

    public void setButton(boolean button) {
        speicherBearbeitenButton.setEnabled(button);
    }

    public ArrayList<Variable> getVariables() {
        return allVariables;
    }

    public boolean changeVariable(String name, String value) {
        for (Variable allVariable : allVariables) {
            if(allVariable.getName().equals(name)) {
                //Variablentypen usw. checken
                allVariable.changeValue(value);
                updateUI();
                return true;
            }
        }
        return false;
    }
}
