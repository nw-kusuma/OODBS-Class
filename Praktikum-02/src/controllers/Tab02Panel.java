package controllers;

import static etc.Modules.*;
import static etc.Constants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author N. W. Kusuma
 */
public class Tab02Panel 
        extends JPanel implements FocusListener {
/* 
 * Variables (components) declaration 
 */           
    private JPanel jplNorth, jplCenter;
    private ButtonGroup optButtons;
    private JRadioButton jrbCSV, jrbEach;
    private JTextField jtfCSV, jtfEach01, jtfEach02, jtfEach03;
    private JScrollPane jspTextArea;
    private JTextArea jtaDisplay;
    private JButton jbtCheckClear;

    // regex matches only numbers as well as 
    // minus sign (-) and dot (.) for decimals. 
    private static final String TF_REGEX = 
            "^(([\\-]{0,1}[0-9]+$)|([\\-]{0,1}[0-9]+[.][0-9]+$))";
//------------------------------------- End of variables declaration                  
    
    // Class constructor
    public Tab02Panel() {
        initComponents();
    }

/* 
 * Components initialization 
 */           
    private void initComponents() {
        final int[][] comp_gbc = {
            { 0, 0, 1, 1, 18, 10, 10, 5, 0   },
            { 1, 0, 1, 1, 18, 10, 10, 5, 10  },
            
            { 0, 0, 1, 1, 18, 10, 10, 0, 0   },
            { 1, 0, 1, 1, 18, 10, 10, 0, 10  },
            { 0, 1, 1, 3, 18, 15, 10, 10, 0  },
            { 1, 1, 1, 1, 18, 15, 10, 0, 10  },
            { 1, 2, 1, 1, 18, 20, 10, 0, 10  },
            { 1, 3, 1, 1, 18, 20, 10, 10, 10 }
        };

        setSize(new Dimension(350, 230));
        setLayout(new BorderLayout());

        jplNorth = new JPanel();
        setComponentSize(jplNorth, 350, 40);
        jplNorth.setLayout(new GridBagLayout());

        optButtons = new ButtonGroup();

        jrbCSV = new JRadioButton("Comma separated numbers");
        setComponentSize(jrbCSV, 180, 25);
        jrbCSV.setSelected(true);
        jrbCSV.setFont(FONT_12);
        jrbCSV.setFocusable(false);
        jrbCSV.setMargin(new Insets(2, 5, 2, 5));
        jrbCSV.addItemListener((ItemEvent e) -> {
            boolean isSelect = jrbCSV.isSelected();
            
            jtfCSV.setEnabled(isSelect);
            jtfEach01.setEnabled(!isSelect);
            jtfEach02.setEnabled(!isSelect);
            jtfEach03.setEnabled(!isSelect);
            
            jbtCheckClear.setText(isSelect ? "Check" : "Clear");
            
            clearAll();
        });
        optButtons.add(jrbCSV);
        jplNorth.add(jrbCSV, getGBConstraint(comp_gbc[0]));

        jrbEach = new JRadioButton("On separated fields");
        setComponentSize(jrbEach, 140, 25);
        jrbEach.setFont(FONT_12);
        jrbEach.setFocusable(false);
        jrbEach.setHorizontalAlignment(11);
        jrbEach.setMargin(new Insets(2, 5, 2, 5));
        optButtons.add(jrbEach);
        jplNorth.add(jrbEach, getGBConstraint(comp_gbc[1]));

        add(jplNorth, BorderLayout.NORTH);

        jplCenter = new JPanel();
        setComponentSize(jplCenter, 350, 190);
        jplCenter.setLayout(new GridBagLayout());

        jtfCSV = new JTextField();
        setComponentSize(jtfCSV, 255, 25);
        jtfCSV.setToolTipText("0, 1, 2, 3, ...");
        jplCenter.add(jtfCSV, getGBConstraint(comp_gbc[2]));

        jbtCheckClear = new JButton("Check");
        setComponentSize(jbtCheckClear, 65, 25);
        jbtCheckClear.setFont(FONT_12_BOLD);
        jbtCheckClear.setMargin(new Insets(2, 5, 2, 5));
        jbtCheckClear.addActionListener((ActionEvent e) -> {
            String sDisplay = "";
            if (jrbCSV.isSelected()){
                if ((!jtfCSV.getText().isEmpty()
                   || jtfCSV.getText().matches(TF_REGEX)))
                {
                    String[] sTemps = jtfCSV.getText().split(",");
                    for (String sTemp : sTemps) {
                        sDisplay += checkNumber(sTemp.strip()).concat("\n");
                    }
                    jtaDisplay.setText(sDisplay);
                }
            } else clearAll();
        });
        jplCenter.add(jbtCheckClear, getGBConstraint(comp_gbc[3]));

        jspTextArea = new JScrollPane();
        setComponentSize(jspTextArea, 255, 130);
        jspTextArea.setHorizontalScrollBarPolicy(31);

        jtaDisplay = new JTextArea();
        setComponentSize(jtaDisplay, 255, 130);
        jtaDisplay.setEditable(false);
        jtaDisplay.setLineWrap(true);
        jtaDisplay.setFocusable(false);
        jspTextArea.setViewportView(jtaDisplay);
        jplCenter.add(jspTextArea, getGBConstraint(comp_gbc[4]));

        jtfEach01 = new JTextField("0");
        setComponentSize(jtfEach01, 65, 30);
        jtfEach01.setHorizontalAlignment(0);
        jtfEach01.setEnabled(false);
        jtfEach01.addFocusListener(this);
        jtfEach01.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e){
                displayEachResult(e.getSource());
            }
        });
        jplCenter.add(jtfEach01, getGBConstraint(comp_gbc[5]));

        jtfEach02 = new JTextField("0");
        setComponentSize(jtfEach02, 65, 30);
        jtfEach02.setHorizontalAlignment(0);
        jtfEach02.setEnabled(false);
        jtfEach02.addFocusListener(this);
        jtfEach02.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e){
                displayEachResult(e.getSource());
            }
        });
        jplCenter.add(jtfEach02, getGBConstraint(comp_gbc[6]));

        jtfEach03 = new JTextField("0");
        setComponentSize(jtfEach03, 65, 30);
        jtfEach03.setHorizontalAlignment(0);
        jtfEach03.setEnabled(false);
        jtfEach03.addFocusListener(this);
        jtfEach03.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e){
                displayEachResult(e.getSource());
            }
        });
        jplCenter.add(jtfEach03, getGBConstraint(comp_gbc[7]));

        add(jplCenter, BorderLayout.CENTER);
    }    
//------------------------------------- End of components initialization                  

/*
 * Some subroutines, the names should sufficiently self explanatory
 */
    private String checkNumber(String sNum){
        double num = Double.parseDouble(sNum);
        String res;
        if ( num < 0){
            res = "is a NEGATIVE number.";
        } else if (num > 0){
            res = "is a POSITIVE number.";
        } else res = "is a ZERO.";
        return ("( " + sNum + " )\t" + res);
    } 

    private void displayEachResult(Object obj){
        JTextField tf = (JTextField) obj;

        if (tf.getText().equals("-")) return;

        if ((!tf.getText().isEmpty() 
           || tf.getText().matches(TF_REGEX)))
        {
            jtaDisplay.setText(
                checkNumber(jtfEach01.getText()) + "\n\n\n" +
                checkNumber(jtfEach02.getText()) + "\n\n\n" +
                checkNumber(jtfEach03.getText()));
        }
    }

    private void clearAll(){
        jtaDisplay.setText("");
        jtfEach03.setText("0");
        jtfEach02.setText("0");
        jtfEach01.setText("0");
        jtfCSV.setText("");

        (jrbCSV.isSelected() ? jtfCSV : jtfEach01).grabFocus();
    }
//------------------------------------- End of subroutines                  

/*
 * Focus event Listener declaration for the textfield component.
 */
    @Override
    public void focusGained(FocusEvent e){
        ((JTextField) e.getSource()).setText("");
    }
    
    @Override
    public void focusLost(FocusEvent e){
        JTextField source = (JTextField) e.getSource();
        source.setText(source.getText().isBlank() ||
                       source.getText().equals("-") 
               ? "0" : source.getText());
    }
//------------------------------------- End of event listener                  
}