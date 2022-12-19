package controllers;

import static etc.Constants.*;
import static etc.Routines.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author N. W. Kusuma
 */
public class Calculator 
        extends JPanel implements ActionListener {
/* Components declaration */
    private JPanel      jplCalc, jplDisplay, jplKeys;
    private JTextField  jtfDisplay, jtfSavedNum;
    private JButton     jbtNeg, jbtDot;
    
    private final JButton[] jbtNums = new JButton[10];
    private final JButton[] jbtFunc = new JButton[7];
    private final JButton[] jbtOptr = new JButton[5];
/* End of Components declaration */

    // Class constructor
    public Calculator() {
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

        setComponentSize(this, 302, 337);
        setLayout(null);

        jplCalc = new JPanel();
        setComponentSize(jplCalc, 302, 337);
        jplCalc.setFocusable(false);
        jplCalc.setLayout(new BorderLayout());

        // Panel for holding the display text fileds
        jplDisplay = new JPanel();
        setComponentSize(jplDisplay, 302, 94);
        jplDisplay.setLayout(new GridBagLayout());

        // Text fields grid-constrains for GridBagLayout
        final int[][] text_fld_gbc = new int[][]{
            { 0, 0, 1, 1, 18, 10, 10, 0, 10 }, 	// textfield saved-number
            { 0, 1, 1, 1, 18, 5, 10, 10, 10 } 	// textfield display
        };
        
        // Text field for displaying 
        // the operands and operators of the calculation 
        jtfSavedNum = new JTextField();
        setComponentSize(jtfSavedNum, 282, 18);
        setComponentProp(jtfSavedNum, FONT_14, clr_fg_bg[0], 4);
        jplDisplay.add(jtfSavedNum, getGBConstraint(text_fld_gbc[0]));

        // Text field for displaying 
        // the entered number or the calculation result
        jtfDisplay = new JTextField("0");
        setComponentSize(jtfDisplay, 282, 47);
        setComponentProp(jtfDisplay, FONT_24, clr_fg_bg[0], 4);
        jplDisplay.add(jtfDisplay, getGBConstraint(text_fld_gbc[1]));

        jplCalc.add(jplDisplay, BorderLayout.NORTH);

        // Panel for the calculator's keys placement
        jplKeys = new JPanel();
        setComponentSize(jplKeys, 302, 243);
        jplKeys.setFocusable(false);
        jplKeys.setLayout(new GridBagLayout());

        // Number buttons grid-constrains for GridBagLayout
        final int[][] nums_btn_gbc = new int[][]{
            { 1, 5, 1, 1, 18, 5, 6, 10, 0 },	// button zero
            { 0, 4, 1, 1, 18, 5, 10, 0, 0 },	// button one
            { 1, 4, 1, 1, 18, 5, 6, 0, 0  }, 	// button two
            { 2, 4, 1, 1, 18, 5, 6, 0, 0  }, 	// button three
            { 0, 3, 1, 1, 18, 5, 10, 0, 0 },	// button four
            { 1, 3, 1, 1, 18, 5, 6, 0, 0  }, 	// button five
            { 2, 3, 1, 1, 18, 5, 6, 0, 0  }, 	// button six
            { 0, 2, 1, 1, 18, 5, 10, 0, 0 }, 	// button seven
            { 1, 2, 1, 1, 18, 5, 6, 0, 0  }, 	// button eight
            { 2, 2, 1, 1, 18, 5, 6, 0, 0  }, 	// button nine

            { 2, 5, 1, 1, 18, 5, 6, 10, 0  },	// button dot (decimal)
            { 0, 5, 1, 1, 18, 5, 10, 10, 0 }	// button negative
        };

        // The number (0-9) buttons initialization
        for (int i = 0; i < jbtNums.length; i++){
            jbtNums[i] = new JButton(String.valueOf(i));
            setComponentSize(jbtNums[i], 66,  33);
            setComponentProp(jbtNums[i], FONT_16_BOLD, clr_fg_bg[1]);
            jbtNums[i].addActionListener(this);
            jplKeys.add(jbtNums[i], getGBConstraint(nums_btn_gbc[i]));
        }

        // The decimal (dot) button initialization
        jbtDot = new JButton("\u2219");
        setComponentSize(jbtDot, 66, 33);
        setComponentProp(jbtDot, FONT_24_BOLD, clr_fg_bg[2]);
        jbtDot.addActionListener(this);
        jplKeys.add(jbtDot, getGBConstraint(nums_btn_gbc[10]));

        // The positive/negative button initialization
        jbtNeg = new JButton("\u207A\u2044\u208B");
        setComponentSize(jbtNeg, 66, 33);
        setComponentProp(jbtNeg, FONT_24_BOLD, clr_fg_bg[2]);
        jbtNeg.addActionListener(this);
        jplKeys.add(jbtNeg, getGBConstraint(nums_btn_gbc[11]));

        // The function buttons grid-constrains for GridBagLayout
        final int[][] func_btn_gbc = new int[][]{
            { 3, 0, 1, 1, 18, 10, 6, 0, 10 }, 	// button backspace
            { 2, 0, 1, 1, 18, 10, 6, 0, 0  }, 	// button clear
            { 1, 0, 1, 1, 18, 10, 6, 0, 0  }, 	// button clear-entry

            { 0, 0, 1, 1, 18, 10, 10, 0, 0 }, 	// button percent
            { 0, 1, 1, 1, 18, 5, 10, 0, 0  }, 	// button fraction
            { 1, 1, 1, 1, 18, 5, 6, 0, 0   }, 	// button square
            { 2, 1, 1, 1, 18, 5, 6, 0, 0   }  	// button square-root
        };
    
        // The function buttons displayed text label
        final String[] funcTexts = { 
            "\u232B", "C", "CE", "%",
            "\u215F\u0078", "\u0078\u00B2", "\u221A\u0078" 
        };
        
        // The function buttons displayed text label
        final String[] funcTips = { 
            "Delete right-most digit", 
            "Clear all display", "Clear Entry (input) display", 
            "Percent", "Fraction numerator one", 
            "Square of x", "Square-root of x" 
        };
        
        // The function buttons initialization 
        // (backspace, clear, fraction, square, square-root, etc)
        for (int i = 0; i < jbtFunc.length; i++){
            jbtFunc[i] = new JButton(funcTexts[i]);
            setComponentSize(jbtFunc[i], 66, 33);
            setComponentProp(jbtFunc[i], i==0 ? FONT_14_SYM : FONT_14_BOLD, clr_fg_bg[2]);
            jbtFunc[i].setToolTipText(funcTips[i]);
            jbtFunc[i].addActionListener(this);
            jplKeys.add(jbtFunc[i], getGBConstraint(func_btn_gbc[i]));
        }

        // The operator buttons grid-constrains for GridBagLayout
        final int[][] optr_btn_gbc = new int[][]{
            { 3, 1, 1, 1, 18, 5, 6, 0, 10  }, 	// button division
            { 3, 2, 1, 1, 18, 5, 6, 0, 10  }, 	// button multiplication
            { 3, 3, 1, 1, 18, 5, 6, 0, 10  }, 	// button subtraction
            { 3, 4, 1, 1, 18, 5, 6, 0, 10  }, 	// button addition

            { 3, 5, 1, 1, 18, 5, 6, 10, 10 } 	// button equal
        };

        // The operator buttons displayed text label
        final String[] optrTexts = {"\u00F7", "\u0078", "\u002D", "\u002B", "="};
        
        // The arithmetic operator buttons initialization
        for (int i = 0; i < jbtOptr.length; i++){
            jbtOptr[i] = new JButton(optrTexts[i]);
            setComponentSize(jbtOptr[i], 66, 33);
            setComponentProp(jbtOptr[i], FONT_16_BOLD, i < 4 ? clr_fg_bg[2] : clr_fg_bg[3]);
            jbtOptr[i].addActionListener(this);
            jplKeys.add(jbtOptr[i], getGBConstraint(optr_btn_gbc[i]));
        }

        jplCalc.add(jplKeys, BorderLayout.CENTER);

        add(jplCalc);
        jplCalc.setBounds(0, 0, 302, 337);
    }
/* End of Components initialization */

/* Variables declaration */
    private String operator = "";
    private boolean isPreceedNum = false;
    private double firstNum, currNum, result = 0;

    private static final String[] optr = {"/", "*", "-", "+"};
/* End of variables declaration */
    
    // Trims the trailing zero of floating point number
    private String trimTrail0 (String t){
        return t.endsWith(".0") ? t.replace(".0", "") : t;
    }

    // Performs the arithmetic calculation
    private double calculate (double num1, double num2, String op){
        switch (op){
            case "/" -> result = num1 / num2;
            case "*" -> result = num1 * num2;
            case "-" -> result = num1 - num2;
            case "+" -> result = num1 + num2;
            default  -> {}
        } return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String dispNum = jtfDisplay.getText();
        String tempNum;

    /* Button Numbers actions  */ 
        for (int i = 0; i < jbtNums.length; i++){
            if (jbtNums[i].equals(source)){
                if ("0".equals(dispNum) || 
                        dispNum.isEmpty() || isPreceedNum == false)
                {
                    jtfDisplay.setText(String.valueOf(i));
                } else {
                    jtfDisplay.setText(dispNum + String.valueOf(i));
                } 
                isPreceedNum = true; 
                return;  
            } 
        }
        
    /* Operators button actions  */ 
        for (int i = 0; i < jbtOptr.length-1; i++){
            if (jbtOptr[i].equals(source)){
                isPreceedNum = false;
                currNum = Double.parseDouble(dispNum);
                if (!dispNum.isEmpty() && operator.isEmpty()){
                    firstNum = Double.parseDouble(dispNum);
                    operator = optr[i];
                    jtfSavedNum.setText(dispNum + " " + jbtOptr[i].getText());
                } else if (!operator.isEmpty() && firstNum == currNum){
                    operator = optr[i];
                    jtfSavedNum.setText(dispNum + " " + jbtOptr[i].getText());
                } else if (!operator.isEmpty() && firstNum != currNum){
                    firstNum = calculate(firstNum, currNum, operator);
                    operator = optr[i];
                    jtfSavedNum.setText(
                            trimTrail0(Double.toString(firstNum)) 
                                        + " " + jbtOptr[i].getText());
                } 
                return;  
            } 
        }
        
    /* Evaluation/equal button actions  */ 
        if (jbtOptr[4].equals(source)){
            currNum = Double.parseDouble(dispNum);
            if (!operator.isEmpty()){
                jtfSavedNum.setText(jtfSavedNum.getText() + " " + dispNum + " =");
                operator = ""; currNum = 0; firstNum = 0;
                tempNum = Double.toString(calculate(firstNum, currNum, operator));
                jtfDisplay.setText(trimTrail0(tempNum));
            } isPreceedNum = false;
        } 

    /* Functions button actions  */ 
        // Backspace (delete) button
        else if (jbtFunc[0].equals(source)){
            if (!"0".equals(dispNum) && !dispNum.isEmpty()){
                StringBuilder bsp = new StringBuilder(dispNum);
                bsp.deleteCharAt(dispNum.length() - 1);
                jtfDisplay.setText(bsp.toString());
            }
            if ("".equals(jtfDisplay.getText()) 
                    || "-".equals(jtfDisplay.getText()) 
                    || "-0".equals(jtfDisplay.getText())){
                jtfDisplay.setText("0");
            }
        } 

        // Clear (C) button
        else if (jbtFunc[1].equals(source)){
            jtfDisplay.setText("0"); 
            jtfSavedNum.setText("");
            operator = ""; currNum = 0; firstNum = 0;
        } 
        
        // Clear-entry (CE) button
        else if (jbtFunc[2].equals(source)){
            jtfDisplay.setText("0");
        } 

        // Percent (%) function button
        else if (jbtFunc[3].equals(source)){
        } 
           
        // Fraction (1/x) button
        else if (jbtFunc[4].equals(source)){
            if (!"0".equals(dispNum) && !dispNum.isEmpty()){
                jtfSavedNum.setText("1/("+ dispNum +") =");
                tempNum = String.valueOf(1 / Double.parseDouble(dispNum));
                jtfDisplay.setText(trimTrail0(tempNum));
            } isPreceedNum = false;
        } 

        // Square function button
        else if (jbtFunc[5].equals(source)){
            if (!"0".equals(dispNum) && !dispNum.isEmpty()){
                jtfSavedNum.setText("sqr("+ dispNum +") =");
                tempNum = String.valueOf(Math.pow(Double.parseDouble(dispNum), 2));
                jtfDisplay.setText(trimTrail0(tempNum));
            } isPreceedNum = false;
        } 
    
        // Square-root function button
        else if (jbtFunc[6].equals(source)){
            if (!"0".equals(dispNum) && !dispNum.isEmpty()){
                jtfSavedNum.setText("\u221A("+ dispNum +") =");
                tempNum = String.valueOf(Math.sqrt(Double.parseDouble(dispNum)));
                jtfDisplay.setText(trimTrail0(tempNum));
            } isPreceedNum = false;
        } 

    /* Decimal and positive/negative button actions  */ 
        else if (jbtDot.equals(source)){
            if (!dispNum.contains(".")){
                jtfDisplay.setText(dispNum + ".");
            } else if (!isPreceedNum) {
                jtfDisplay.setText("0.");
            } isPreceedNum = true;

        } else if (jbtNeg.equals(source)){
            if (!"0".equals(dispNum) && !dispNum.isEmpty()){
                tempNum = String.valueOf(Double.parseDouble(dispNum) * (-1));
                jtfDisplay.setText(trimTrail0(tempNum));
            } isPreceedNum = true;
        }
    }
}