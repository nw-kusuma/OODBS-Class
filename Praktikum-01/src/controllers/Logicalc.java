package controllers;

import static etc.Constants.*;
import static etc.Routines.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import static oodbs.etc.Routines.setComponentSize;

/**
 * @author N. W. Kusuma
 */
public class Logicalc 
        extends JPanel implements ActionListener {
/* Component declarations */
    private JPanel      jplLogic, jplDisplay, jplKeys;
    private JTextField  jtfDispNum, jtfDisplay, jtfDispBin;
    private JButton     jbtClear, jbtEqual;

    private final JButton[] jbtNums = new JButton[10];
    private final JButton[] jbtBins = new JButton[5];
/* End of Components declarations */

    // Class constructor
    public Logicalc() {
        initComponents();
    }

/* Components initialization */
    private void initComponents() {
        Color[][] clr_fg_bg = new Color[][]{
            { COLOR_FG_DEF, COLOR_BG_DEF },
            { COLOR_WHITE, COLOR_BLACK },
            { COLOR_FG_DEF, COLOR_BG_BTN },
            { COLOR_BLACK, Color.orange.darker() }
        };

        setComponentSize(this, 302,  337);
        setLayout(null);

        jplLogic = new JPanel();
        setComponentSize(jplLogic, 302,  337);
        jplLogic.setFocusable(false);
        jplLogic.setLayout(new BorderLayout());

        // Panel for holding the display text fileds
        jplDisplay = new JPanel();
        setComponentSize(jplDisplay, 302, 130);
        jplDisplay.setFocusable(false);
        jplDisplay.setLayout(new GridBagLayout());

        // Text fields grid-constrains for GridBagLayout
        final int[][] txt_fld_gbc = new int[][]{
            { 0, 0, 1, 1, 18, 10, 10, 0, 10  },  //---- textfield DispNum
            { 0, 1, 1, 1, 18, 10, 10, 0, 10  },  //---- textfield Display
            { 0, 2, 1, 1, 18, 10, 10, 10, 10 }   //---- textfield DispBin
        };

        // The text field for displaying the calculation's
        // operands in decimal form and operators in semantic word
        jtfDispNum = new JTextField("0");
        setComponentSize(jtfDispNum, 282, 18);
        setComponentProp(jtfDispNum, FONT_14, clr_fg_bg[0], 4);
        jplDisplay.add(jtfDispNum, getGBConstraint(txt_fld_gbc[0]));

        // Text field for displaying the entered number 
        // or the calculation result in decimal form
        jtfDisplay = new JTextField("0");
        setComponentSize(jtfDisplay, 282, 49);
        setComponentProp(jtfDisplay, FONT_36, clr_fg_bg[0], 4);
        jplDisplay.add(jtfDisplay, getGBConstraint(txt_fld_gbc[1]));

        // Text field for displaying the binary format of
        // the operands and operators of the calculation and its result
        jtfDispBin = new JTextField("0000");
        setComponentSize(jtfDispBin, 282, 18);
        setComponentProp(jtfDispBin, FONT_14, clr_fg_bg[0], 4);
        jplDisplay.add(jtfDispBin, getGBConstraint(txt_fld_gbc[2]));

        jplLogic.add(jplDisplay, BorderLayout.NORTH);

        // Panel for the calculator's keys placement
        jplKeys = new JPanel();
        setComponentSize(jplKeys, 302, 207);
        jplKeys.setFocusable(false);
        jplKeys.setLayout(new GridBagLayout());

        // The number buttons grid-constrains for GridBagLayout
        final int[][] num_btn_gbc = new int[][]{
            { 1, 4, 1, 1, 18, 5, 6, 10, 0 },	//--- button zero
            { 0, 3, 1, 1, 18, 5, 10, 0, 0 },	//--- button one
            { 1, 3, 1, 1, 18, 5, 6, 0, 0  }, 	//--- button two
            { 2, 3, 1, 1, 18, 5, 6, 0, 0  }, 	//--- button three
            { 0, 2, 1, 1, 18, 5, 10, 0, 0 },	//--- button four
            { 1, 2, 1, 1, 18, 5, 6, 0, 0  }, 	//--- button five
            { 2, 2, 1, 1, 18, 5, 6, 0, 0  }, 	//--- button six
            { 0, 1, 1, 1, 18, 5, 10, 0, 0 }, 	//--- button seven
            { 1, 1, 1, 1, 18, 5, 6, 0, 0  }, 	//--- button eight
            { 2, 1, 1, 1, 18, 5, 6, 0, 0  } 	//--- button nine
        };

        // The number (0-9) buttons initialization
        for (int i = 0; i < jbtNums.length; i++){
            jbtNums[i] = new JButton(String.valueOf(i));
            setComponentSize(jbtNums[i], 66, 33);
            setComponentProp(jbtNums[i], FONT_16_BOLD, clr_fg_bg[1]);
            jbtNums[i].addActionListener(this);
            jplKeys.add(jbtNums[i], getGBConstraint(num_btn_gbc[i]));
        }

        // The binary/logic buttons grid-constrains for GridBagLayout
        final int[][] bin_btn_gbc = new int[][]{
            { 3, 2, 1, 1, 18, 5, 6, 0, 10  },	//--- button and
            { 3, 3, 1, 1, 18, 5, 6, 0, 10  },	//--- button or
            { 3, 1, 1, 1, 18, 5, 6, 0, 10  },	//--- button not

            { 0, 4, 1, 1, 18, 5, 10, 10, 0 },	//--- button true
            { 2, 4, 1, 1, 18, 5, 6, 10, 0  },	//--- button false
        };
    
        // The binary/logic buttons displayed text label (NOT/AND/OR/true/false) 
        final String[] binTexts = {" & ", " | ", " ! ", "true", "false"};

        // The binary/logic buttons displayed tooltips text
        final String[] binTips = {
            "AND operator", "OR operator", "NOT operator", 
            "TRUE boolean value", "FALSE boolean value"};

        // The binary/logic buttons initialization
        for (int i = 0; i < jbtBins.length; i++){
            jbtBins[i] = new JButton(binTexts[i]);
            setComponentSize(jbtBins[i], 66, 33);
            setComponentProp(jbtBins[i], FONT_16_BOLD, clr_fg_bg[2]);
            jbtBins[i].setToolTipText(binTips[i]);
            jbtBins[i].addActionListener(this);
            jplKeys.add(jbtBins[i], getGBConstraint(bin_btn_gbc[i]));
        }

        // The function buttons grid-constrains for GridBagLayout
        final int[][] func_btn_gbc = new int[][]{
            { 0, 0, 4, 1, 18, 10, 10, 0, 10 }, 	//--- button clear
            { 3, 4, 1, 1, 18, 5, 6, 10, 10  }, 	//--- button equal
        };
    
        // The clear button initialization
        jbtClear = new JButton("CLEAR");
        setComponentSize(jbtClear, 282, 33);
        setComponentProp(jbtClear, FONT_16_BOLD, clr_fg_bg[2]);
        jbtClear.addActionListener(this);
        jplKeys.add(jbtClear, getGBConstraint(func_btn_gbc[0]));

        // The equal button initialization
        jbtEqual = new JButton("=");
        setComponentSize(jbtEqual, 66, 33);
        setComponentProp(jbtEqual, FONT_20_BOLD, clr_fg_bg[3]);
        jbtEqual.addActionListener(this);
        jplKeys.add(jbtEqual, getGBConstraint(func_btn_gbc[1]));

        jplLogic.add(jplKeys, BorderLayout.CENTER);

        add(jplLogic);
        jplLogic.setBounds(0, 0, 302, 337);
    }    
/* End of Components initialization */

/* Variables declaration  */                 
    private String  operator = "", result = "";
    private boolean firstStr = false, currStr = false;
    private int     len = 0, firstNum = 0, tempNum = 0, currNum = 0; 
/* End of variables declaration  */                 

    private void enableButtons(String actType){
        boolean isNum, isBin;
        switch (actType){
            // Clear or reset back all displays and variables
            case "CLR" -> {
                firstStr = false; currStr = false;
                firstNum = 0; currNum = 0; tempNum = 0;
                len = 0; operator = ""; result = "";
                isNum = true; isBin = true;
            }
            // Only binary (true|false) operation available
            case "BIN" -> { isNum = false; isBin = true; }
            // Disable the binary (true|false) values and the NOT operator
            case "NUM" -> { isNum = true;  isBin = false; }
            default    -> { isNum = false; isBin = false; }
        }
    /* Disable or enable the corresponding buttons */
        for (JButton jbtNum : jbtNums) {
            jbtNum.setEnabled(isNum);
        }
        for (int i = 2; i < jbtBins.length; i++){
            jbtBins[i].setEnabled(isBin);
        }
    }

    // Converts integer into four digit binary string
    private String int2BinStr (int num) {
        return String.format("%4s", Integer.toBinaryString(num))
                     .replace(' ', '0');
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

    /* The CLEAR button action ---------------------------------------- */ 
        if (jbtClear.equals(source)){
            jtfDisplay.setText("0"); 
            jtfDispNum.setText("");
            jtfDispBin.setText("");
            enableButtons("CLR");
        }

    /* The FALSE button action ---------------------------------------- */ 
        else if (jbtBins[4].equals(source)){
            jtfDisplay.setText("false");
            jtfDispBin.setText("");
            enableButtons("BIN");
            
            if (jtfDispNum.getText().endsWith("=") || 
                jtfDispNum.getText().isEmpty())
            {
                jtfDispNum.setText("(false)");
            } 
            else if (jtfDispNum.getText().endsWith("OR") || 
                     jtfDispNum.getText().endsWith("AND"))
            {
                jtfDispNum.setText(jtfDispNum.getText() + " (false)");
            } 
            else if (jtfDispNum.getText().endsWith("NOT"))
            {
                jtfDispNum.setText(jtfDispNum.getText() + "(false)");
                jtfDispBin.setText("Press '=' to get the result...");
                // jtfDisplay.setText(Boolean.toString(!false));
            } 
            else if (jtfDispNum.getText().contains("AND") || 
                       jtfDispNum.getText().contains("OR")) 
            {
                len = jtfDispNum.getText().endsWith("(false)") ? 8 : 7;
                jtfDispNum.setText(jtfDispNum.getText()
                            .substring(0, jtfDispNum
                            .getText().length() - len) + " (false)");
            } 
            else jtfDispNum.setText("(false)");
        }
        
    /* The TRUE button action ---------------------------------------- */ 
        else if (jbtBins[3].equals(source)){
            jtfDisplay.setText("true");
            jtfDispBin.setText("");
            enableButtons("BIN");
            
            if (jtfDispNum.getText().endsWith("=") || 
                jtfDispNum.getText().isEmpty())
            {
                jtfDispNum.setText("(true)");
            } 
            else if (jtfDispNum.getText().endsWith("OR") || 
                     jtfDispNum.getText().endsWith("AND"))
            {
                jtfDispNum.setText(jtfDispNum.getText() + " (true)");
            } 
            else if (jtfDispNum.getText().endsWith("NOT"))
            {
                jtfDispNum.setText(jtfDispNum.getText() + "(true)");
                jtfDispBin.setText("Press '=' to get the result...");
                // jtfDisplay.setText(Boolean.toString(!true));
            } 
            else if (jtfDispNum.getText().contains("AND") || 
                     jtfDispNum.getText().contains("OR"))
            {
                len = jtfDispNum.getText().endsWith("(true)") ? 7 : 8;
                jtfDispNum.setText(jtfDispNum.getText()
                          .substring(0, jtfDispNum
                          .getText().length() - len) + " (true)");
            } 
            else jtfDispNum.setText("(true)");
        }
        
    /* The OR (|) button action ---------------------------------------- */ 
        else if (jbtBins[1].equals(source)){
            operator = "|";
            if ("false".equals(jtfDisplay.getText()) || 
                 "true".equals(jtfDisplay.getText()))
            {
                firstStr = Boolean.parseBoolean(jtfDisplay.getText());
                jtfDispNum.setText("(" + jtfDisplay.getText() + ") OR");
                enableButtons("BIN");
            } else {
                firstNum = Integer.parseInt(jtfDisplay.getText());
                jtfDispNum.setText("(" + jtfDisplay.getText() + ") OR");
                jtfDispBin.setText(int2BinStr(firstNum) + " ||");
                enableButtons("NUM");
            } 
        }
    /* The AND (&) button action ---------------------------------------- */ 
        else if (jbtBins[0].equals(source)){
            operator = "&";
            if ("false".equals(jtfDisplay.getText()) || 
                 "true".equals(jtfDisplay.getText()))
            {
                firstStr = Boolean.parseBoolean(jtfDisplay.getText());
                jtfDispNum.setText("(" + jtfDisplay.getText() + ") AND");
                enableButtons("BIN");
            } else {
                firstNum = Integer.parseInt(jtfDisplay.getText());
                jtfDispNum.setText("(" + jtfDisplay.getText() + ") AND");
                jtfDispBin.setText(int2BinStr(firstNum) + " &&");
                enableButtons("NUM");
            } 
        }
        
    /* The NOT (!) button action ---------------------------------------- */ 
        else if (jbtBins[2].equals(source)){
            if ("false".equals(jtfDisplay.getText()) || 
                 "true".equals(jtfDisplay.getText()))
            {
                currStr = Boolean.parseBoolean(jtfDisplay.getText());
                jtfDisplay.setText(Boolean.toString(!currStr));
                jtfDispNum.setText("NOT (" + currStr + ") =");
            } else {
                operator = "!";
                jtfDispNum.setText("NOT");
            } enableButtons("BIN");
        }
        
    /* The EQUAL (evaluation) button action ----------------------------- */ 
        else if (jbtEqual.equals(source)){
            switch (operator){
                // Evaluate NOT operation
                case "!" -> { 
                    // result = jtfDisplay.getText();
                    currStr = Boolean.parseBoolean(jtfDisplay.getText());
                    result = Boolean.toString(!currStr);
                    
                    jtfDispNum.setText(jtfDispNum.getText() + " =");
                    jtfDispBin.setText("");
                }
                // Evaluate AND operation
                case "&" -> {
                    if ("false".equals(jtfDisplay.getText()) ||
                         "true".equals(jtfDisplay.getText())) 
                    {
                        currStr = Boolean.parseBoolean(jtfDisplay.getText());
                         result = Boolean.toString(firstStr & currStr);

                        jtfDispNum.setText(jtfDispNum.getText() + " =");
                    } else {
                        currNum = Integer.parseInt(jtfDisplay.getText());
                         result = Integer.toString(firstNum & currNum);
                        
                        jtfDispNum.setText(jtfDispNum.getText() +
                                " (" + Integer.toString(currNum) + ") =");
                        if (jtfDispBin.getText().endsWith("||") || 
                            jtfDispBin.getText().endsWith("&&"))
                        {
                            jtfDispBin.setText(jtfDispBin.getText() + 
                                    " " + int2BinStr(currNum) +
                                    " = " + int2BinStr(Integer.parseInt(result)));
                        } else 
                            jtfDispBin.setText(jtfDispBin.getText() + 
                                    " = " + int2BinStr(Integer.parseInt(result)));
                    }
                }
                // Evaluate OR operation
                case "|" -> {
                    if ("false".equals(jtfDisplay.getText()) || 
                         "true".equals(jtfDisplay.getText()))
                    {
                        currStr = Boolean.parseBoolean(jtfDisplay.getText());
                         result = Boolean.toString(firstStr | currStr);

                        jtfDispNum.setText(jtfDispNum.getText() + " =");
                    } else {
                        currNum = Integer.parseInt(jtfDisplay.getText());
                         result = Integer.toString(firstNum | currNum);
                        
                        jtfDispNum.setText(jtfDispNum.getText() +
                                " (" + Integer.toString(currNum) + ") =");
                        if (jtfDispBin.getText().endsWith("||") || 
                            jtfDispBin.getText().endsWith("&&"))
                        {
                            jtfDispBin.setText(jtfDispBin.getText() + 
                                    " " + int2BinStr(currNum) +
                                    " = " + int2BinStr(Integer.parseInt(result)));
                        } else 
                            jtfDispBin.setText(jtfDispBin.getText() + 
                                    " = " + int2BinStr(Integer.parseInt(result)));
                    }
                }
                default -> { 
                    result = jtfDisplay.getText(); 
                    jtfDispNum.setText(result);
                    jtfDispBin.setText(int2BinStr(Integer.parseInt(result)));
                }
            }
            jtfDisplay.setText(result);
            enableButtons("CLR");
        }
        
    /* The NUMBER buttons action ---------------------------------------- */ 
        else {
            for (int i = 0; i <10; i++){
                if (jbtNums[i].equals(source)){
                    tempNum = i; break;
                } 
            }
            
            jtfDisplay.setText(String.valueOf(tempNum));
//            if (jtfDispBin.getText().contains("=") || 
//                jtfDispBin.getText().isEmpty())
//            {
//                jtfDispBin.setText(jtfDisplay.getText());
//            } 
            if (jtfDispBin.getText().endsWith("||") || 
                jtfDispBin.getText().endsWith("&&"))
            {
                jtfDispBin.setText(jtfDispBin.getText() + " " + int2BinStr(tempNum));
            } 
            else if (jtfDispBin.getText().contains("||") || 
                     jtfDispBin.getText().contains("&&")) 
            {
                jtfDispBin.setText(jtfDispBin.getText()
                          .substring(0, 8) + int2BinStr(tempNum));
            } 
            else jtfDispBin.setText(int2BinStr(tempNum));
            enableButtons("NUM");
        }
    }
}