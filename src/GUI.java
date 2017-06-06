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
    private JButton bildschirmanzeigeÄndernButton;
    private JButton zufallszahlGenerierenButton;
    private JTextArea screen;
    private ArrayList<Variable> allVariables;
    private ArrayList<JLabel> allTypes;
    private ArrayList<JLabel> allNames;
    private ArrayList<JLabel> allValues;
    private ScreenChangeGUI screenChangeGUI;
    private RandomValueGUI randomValueGUI;

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

        screenChangeGUI = new ScreenChangeGUI(this);
        randomValueGUI = new RandomValueGUI(this);
        speicherBearbeitenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workgui.start();
            }
        });

        bildschirmanzeigeÄndernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenChangeGUI.start();
            }
        });
        zufallszahlGenerierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomValueGUI.start();
            }
        });
    }

    public void start() {
        mainFrame = new JFrame("Programmierspiel");
        mainFrame.setResizable(false);
        mainFrame.setContentPane(this.mainPanel);
        mainFrame.setVisible(true);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        workgui = new WorkGUI(this);
    }


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

    private void updateUI() { //anderes Aussehen von int, double, String und char
        for (int i = 0; i < 5; i++) {
            if(allVariables.size() > i) {
                switch(allVariables.get(i).getType()) {
                    case "String":
                        if(allVariables.get(i).getValue() != null)
                            allValues.get(i).setText("\"" + allVariables.get(i).getValue() + "\"");
                        break;
                    case "char":
                        if(allVariables.get(i).getValue() != null)
                            allValues.get(i).setText("'" + allVariables.get(i).getValue() + "'");
                        break;
                    case "double":
                        if(allVariables.get(i).getValue()!=null) {
                            double d = Double.parseDouble(allVariables.get(i).getValue());
                            allValues.get(i).setText(Double.toString(d));
                        }
                        break;
                    case "int":
                        allValues.get(i).setText(allVariables.get(i).getValue());

                }
                allTypes.get(i).setText(allVariables.get(i).getType());
                allNames.get(i).setText(allVariables.get(i).getName());
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
                switch(allVariable.getType()) {
                    case "int":
                        try{
                            Integer.parseInt(value);
                        } catch(NumberFormatException e) {
                            return false;
                        }
                        break;
                    case "double":
                        try{
                            Double.parseDouble(value);
                        } catch(NumberFormatException e) {
                            return false;
                        }
                        break;
                    case "String":
                        break;
                    case "char":
                        if(value != null) {
                            if(value.length()!=1) {
                                return false;
                            }
                        }
                        break;
                }
                allVariable.changeValue(value);
                updateUI();
                return true;
            }
        }
        return false;
    }

    public void setScreen(String text) {
        this.screen.setText(this.screen.getText() + "\n " + text);
    }

    public String getValueOf(String name) {
        for (Variable allVariable : allVariables) {
            if(allVariable.getName().equals(name)) {
                return allVariable.getValue();
            }
        }
        return "";
    }
}
