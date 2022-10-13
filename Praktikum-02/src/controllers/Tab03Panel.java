package controllers;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
// import javax.swing.border.*;

/**
 * @author N. W. Kusuma
 */
public class Tab03Panel extends JPanel implements KeyListener {

    public Tab03Panel() {
        initComponents();
    }

// Variables declaration
    private JPanel jpnlNorth;
    private ButtonGroup optButtons;
    private JRadioButton jrboCSV, jrboEach;

    private JPanel jpnlCenter;
    private JButton jbtnCheckClear;
    private JScrollPane jspTextArea;
    private JTextArea jtaDisplay;
    private JTextField jtfCSV, jtfEach01, jtfEach02, jtfEach03;

    /*
     * regex matches only numbers as well as minus sign (-) and dot (.) for decimals. 
     */
    private String regex = "^(([\\-]{0,1}[0-9]+$)|([\\-]{0,1}[0-9]+[.][0-9]+$))";
    private String[] sTemp;
// End of variables declaration

// Components instantiations
    private void initComponents() {
        GridBagConstraints gbc;

        setMaximumSize(new Dimension(325, 225));
        setMinimumSize(new Dimension(325, 225));
        setPreferredSize(new Dimension(325, 225));
        setLayout(new BorderLayout());

        jpnlNorth = new JPanel();
        optButtons = new ButtonGroup();
        jrboCSV = new JRadioButton("Comma separated numbers");
        jrboEach = new JRadioButton("On separated fields");

        jpnlCenter = new JPanel();
        jtfCSV = new JTextField();
        jbtnCheckClear = new JButton("Check");
        jspTextArea = new JScrollPane();
        jtaDisplay = new JTextArea();
        jtfEach01 = new JTextField("0");
        jtfEach02 = new JTextField("0");
        jtfEach03 = new JTextField("0");

        jpnlNorth.setMaximumSize(new Dimension(325, 35));
        jpnlNorth.setMinimumSize(new Dimension(325, 35));
        jpnlNorth.setPreferredSize(new Dimension(325, 35));

        jrboCSV.setFont(new Font("Arial Unicode MS", 0, 12));
        jrboCSV.setMargin(new Insets(8, 2, 3, 2));
        jrboCSV.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                csvStateChanged(e);
            }
        });
        jrboCSV.setSelected(true);
        
        optButtons.add(jrboCSV);
        jpnlNorth.add(jrboCSV);

        jrboEach.setMargin(new Insets(8, 5, 3, 2));
        jrboEach.setFont(new Font("Arial Unicode MS", 0, 12)); 
        // jrboEach.addItemListener(this);
        
        optButtons.add(jrboEach);
        jpnlNorth.add(jrboEach);

        add(jpnlNorth, BorderLayout.NORTH);

        jpnlCenter.setLayout(new GridBagLayout());

        jtfCSV.setToolTipText("0, 1, 2, 3, ...");
        jtfCSV.setPreferredSize(new Dimension(232, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 209;
        gbc.ipady = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 0, 0);
        jpnlCenter.add(jtfCSV, gbc);

        jbtnCheckClear.setMinimumSize(new Dimension(50, 23));
        jbtnCheckClear.setPreferredSize(new Dimension(55, 25));
        jbtnCheckClear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jbtnAction(e);
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 16;
        gbc.ipady = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 14, 0, 10);
        jpnlCenter.add(jbtnCheckClear, gbc);

        jtaDisplay.setEditable(false);
        jtaDisplay.setColumns(20);
        jtaDisplay.setLineWrap(true);
        jtaDisplay.setRows(5);
        jtaDisplay.setAutoscrolls(false);
        // jtaDisplay.setTabSize(5);
        jtaDisplay.setFocusable(false);
        jspTextArea.setViewportView(jtaDisplay);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 209;
        gbc.ipady = 114;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(14, 10, 10, 0);
        jpnlCenter.add(jspTextArea, gbc);

        jtfEach01.setHorizontalAlignment(JTextField.CENTER);
        jtfEach01.setPreferredSize(new Dimension(55, 30));
        jtfEach01.addKeyListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 2;
        gbc.ipady = 8;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(14, 14, 0, 10);
        jpnlCenter.add(jtfEach01, gbc);

        jtfEach02.setHorizontalAlignment(JTextField.CENTER);
        jtfEach02.setPreferredSize(new Dimension(55, 30));
        jtfEach02.addKeyListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 2;
        gbc.ipady = 8;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(16, 14, 0, 10);
        jpnlCenter.add(jtfEach02, gbc);

        jtfEach03.setHorizontalAlignment(JTextField.CENTER);
        jtfEach03.setPreferredSize(new Dimension(55, 30));
        jtfEach03.addKeyListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.ipadx = 2;
        gbc.ipady = 8;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(16, 14, 10, 10);
        jpnlCenter.add(jtfEach03, gbc);

        add(jpnlCenter, BorderLayout.CENTER);
    }
// End of components instantiations

/*
 * Just some subroutines, 
 * the names should sufficiently self explanatory
 */
    private String checkNumber(String sNum){
        double num = Double.parseDouble(sNum);
        return "( " + sNum + " )" 
                    + (num % 2 == 0 ? "\tis an EVEN number."
                                    : "\tis an ODD number."); 
    } 

    private void clearAll(){
        jtaDisplay.setText("");
        jtfEach03.setText("0");
        jtfEach02.setText("0");
        jtfEach01.setText("0");
        jtfCSV.setText("");

        (jrboCSV.isSelected() ? jtfCSV : jtfEach01).grabFocus();
    }
//........................................

/*
 * Key Listeners for the textfields components
 * We only needed keyReleased event listener,
 * but super implementation requires all overrides
 * of the key event mthods to be declared as well
 */
    @Override
    public void keyReleased(KeyEvent e) {
        JTextField source = (JTextField) e.getSource();
        if (source.getText().equals("-")) return;

        if ((!source.getText().isEmpty() 
            || source.getText().matches(regex)))
        {
            jtaDisplay.setText(
                checkNumber(jtfEach01.getText()) + "\n\n\n" +
                checkNumber(jtfEach02.getText()) + "\n\n\n" +
                checkNumber(jtfEach03.getText()));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}


/*
 * Item state event Listeners for the radio buttons components
 */
    private void csvStateChanged(ItemEvent e) {
        boolean isSelect = jrboCSV.isSelected();

        jtfCSV.setEnabled(isSelect);
        jtfEach01.setEnabled(!isSelect);
        jtfEach02.setEnabled(!isSelect);
        jtfEach03.setEnabled(!isSelect);

        jbtnCheckClear.setText(isSelect ? "Check" : "Clear");

        clearAll();
    }

/*
* Action event Listeners for the button components
*/
    private void jbtnAction(ActionEvent e){
        String sDisplay = "";
        if (jrboCSV.isSelected()){
            if ((!jtfCSV.getText().isEmpty() 
                || jtfCSV.getText().matches(regex)))
            {
                sTemp = jtfCSV.getText().split(",");
                for (int i = 0; i < sTemp.length; i++){
                    sDisplay += checkNumber(sTemp[i]
                                .strip())
                                .concat("\n");
                }
                jtaDisplay.setText(sDisplay);
            } 
        } else clearAll();
        return;
    }
}