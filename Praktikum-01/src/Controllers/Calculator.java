package Controllers;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JPanel implements ActionListener {

    public Calculator() {
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        initComponents();
    }

    // Variables declaration                   
    private JPanel jpnlCalc;
    private JTextField jtfSavedNum, jtfDisplay;

    private JButton jbtnClear, jbtnClrEntry, jbtnBackspace;
    private JButton jbtnPercent, jbtnFraction, jbtnSqrt, jbtnSqr;
    private JButton jbtnDiv, jbtnMulti, jbtnSub, jbtnAdd;
    private JButton jbtnNeg, jbtnDec, jbtnEqual;

    private JButton[] jbtnNums = new JButton[10];
    private JButton[] jbtnOptr = {jbtnDiv, jbtnMulti, jbtnSub, jbtnAdd};

    private String operator = "";
    private double firstNum, currNum, result = 0;
    // End of variables declaration                   

    private void btnProp(JButton btn, Font f){
        btn.setFont(f);
        btn.setFocusPainted(false);
//        btn.setBorder(null);
        btn.setFocusable(false);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setMargin(new Insets(2, 5, 2, 5));
        btn.setPreferredSize(new Dimension(66, 33));        
        btn.setMaximumSize(new Dimension(66, 33));
        btn.setMinimumSize(new Dimension(66, 33));
        btn.addActionListener(this);
    }

    private void tfProp(JTextField tf, int h, Font f, String t){
        tf.setFont(f);
        tf.setText(t);
        tf.setEditable(false);
        tf.setBorder(null);
        tf.setFocusable(false);
        tf.setHorizontalAlignment(JTextField.RIGHT);
        tf.setMaximumSize(new Dimension(64, h));
    }

    private Font myFonts(int n) {
        return new Font("Arial Unicode MS", 0, n);
    }

    // Components initialization
    private void initComponents() {
        Color numBtnBg = new Color(51, 51, 51);
        Color numBtnFg = new Color(255, 255, 255);

        setFont(myFonts(12));

        jpnlCalc = new JPanel();
        jpnlCalc.setFocusable(false);
        jpnlCalc.setMaximumSize(new Dimension(294, 316));
        jpnlCalc.setMinimumSize(new Dimension(294, 316));

        jtfDisplay = new JTextField();
        tfProp(jtfDisplay, 33, myFonts(24), "0");

        jtfSavedNum = new JTextField();
        tfProp(jtfSavedNum, 17, myFonts(12), "");

        for (int i = 0; i < 10; i++){
            jbtnNums[i] = new JButton(String.valueOf(i));
            jbtnNums[i].setBackground(numBtnBg);
            jbtnNums[i].setForeground(numBtnFg);
            btnProp(jbtnNums[i], myFonts(16));
        }

        jbtnDec = new JButton(".");
        btnProp(jbtnDec, myFonts(24));

        jbtnNeg = new JButton("\u207A\u2044\u208B");
        btnProp(jbtnNeg, myFonts(16));

        jbtnClear = new JButton("C");
        btnProp(jbtnClear, myFonts(16));

        jbtnClrEntry = new JButton("CE");
        btnProp(jbtnClrEntry, myFonts(16));

        jbtnBackspace = new JButton("\u232B");
        btnProp(jbtnBackspace, myFonts(16));

        jbtnPercent = new JButton("%");
        btnProp(jbtnPercent, myFonts(16));

        jbtnFraction = new JButton("\u00B9\u2044\u03F0");
        btnProp(jbtnFraction, myFonts(16));

        jbtnSqrt = new JButton("\u221A\u03F0");
        btnProp(jbtnSqrt, myFonts(16));

        jbtnSqr = new JButton("\u03F0\u00B2");
        btnProp(jbtnSqr, myFonts(16));

        String[] optrBtn = {"\u00F7", "\u00D7", "\u2212", "+"};
        for (int i = 0; i < 4; i++){
            jbtnOptr[i] = new JButton(optrBtn[i]);
            btnProp(jbtnOptr[i], myFonts(20));
        }

        jbtnEqual = new JButton("=");
        jbtnEqual.setBackground(Color.orange.darker());
        jbtnEqual.setForeground(new Color(51, 51, 51));
        btnProp(jbtnEqual, myFonts(20));

        setComponentLayout();
    }                   
    // End of Components initialization

    private void setComponentLayout (){

        GroupLayout jpnlCalcLayout = new GroupLayout(jpnlCalc);
        jpnlCalc.setLayout(jpnlCalcLayout);
        jpnlCalcLayout.setHorizontalGroup(
            jpnlCalcLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpnlCalcLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlCalcLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jtfDisplay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, jpnlCalcLayout.createSequentialGroup()
                        .addComponent(jbtnFraction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnSqrt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnSqr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnOptr[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.LEADING, jpnlCalcLayout.createSequentialGroup()
                        .addComponent(jbtnPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnClear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnClrEntry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnBackspace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.LEADING, jpnlCalcLayout.createSequentialGroup()
                        .addComponent(jbtnNums[7], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnNums[8], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnNums[9], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnOptr[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.LEADING, jpnlCalcLayout.createSequentialGroup()
                        .addComponent(jbtnNums[4], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnNums[5], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnNums[6], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnOptr[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.LEADING, jpnlCalcLayout.createSequentialGroup()
                        .addComponent(jbtnNums[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnNums[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnNums[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnOptr[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.LEADING, jpnlCalcLayout.createSequentialGroup()
                        .addComponent(jbtnNeg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnNums[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnDec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnEqual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtfSavedNum, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        jpnlCalcLayout.setVerticalGroup(
            jpnlCalcLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpnlCalcLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtfSavedNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jtfDisplay, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlCalcLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnClear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnClrEntry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnBackspace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlCalcLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnFraction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSqrt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnOptr[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSqr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlCalcLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnNums[7], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[8], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[9], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnOptr[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlCalcLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnNums[4], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[5], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[6], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnOptr[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlCalcLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnNums[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnOptr[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlCalcLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnNeg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnDec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEqual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpnlCalc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpnlCalc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
    }

    private String trimTrail0 (String t){
        return t.endsWith(".0") ? t.replace(".0", "") : t;
    }

    private boolean isPreceedNum = false;
    private String[] opr = {"/", "*", "-", "+"};
    private double calculate (double num1, double num2, String op){
        switch (op){
            case "/"   : result = num1 / num2; break;
            case "*"   : result = num1 * num2; break;
            case "-"   : result = num1 - num2; break;
            case "+"        : result = num1 + num2; break;
            default         : break;
        } return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String dispNum = jtfDisplay.getText();
        String tempNum;

    /* Button Numbers actions  */ 
        for (int i = 0; i < 10; i++){
            if (jbtnNums[i].equals(source)){
                if ("0".equals(dispNum) || dispNum.isEmpty() || isPreceedNum == false){
                    jtfDisplay.setText(String.valueOf(i));
                } else {
                    jtfDisplay.setText(dispNum + String.valueOf(i));
                } isPreceedNum = true; 
            } 
        }

    /* Operators button actions  */ 
        for (int i = 0; i < 4; i++){
            if (jbtnOptr[i].equals(source)){
                isPreceedNum = false;
                currNum = Double.parseDouble(dispNum);
                if (!dispNum.isEmpty() && operator.isEmpty()){
                    firstNum = Double.parseDouble(dispNum);
                    operator = opr[i];
                    jtfSavedNum.setText(dispNum + " " + jbtnOptr[i].getText());
                } else if (!operator.isEmpty() && firstNum == currNum){
                    operator = opr[i];
                    jtfSavedNum.setText(dispNum + " " + jbtnOptr[i].getText());
                } else if (!operator.isEmpty() && firstNum != currNum){
                    firstNum = calculate(firstNum, currNum, operator);
                    operator = opr[i];
                    jtfSavedNum.setText(trimTrail0(Double.toString(firstNum)) + " " + 
                                                   jbtnOptr[i].getText());
                }   
            } 
        }
        
    /* Button Function actions  */ 
        if (jbtnClear.equals(source)){
            jtfDisplay.setText("0"); 
            jtfSavedNum.setText("");
            operator = ""; currNum = 0; firstNum = 0;

        } else if (jbtnClrEntry.equals(source)){
            jtfDisplay.setText("0");

        } else if (jbtnBackspace.equals(source)){
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

        } else if (jbtnPercent.equals(source)){
           
        } else if (jbtnFraction.equals(source)){
            if (!"0".equals(dispNum) && !dispNum.isEmpty()){
                jtfSavedNum.setText("1/("+ dispNum +")");
                tempNum = String.valueOf(1 / Double.parseDouble(dispNum));
                jtfDisplay.setText(trimTrail0(tempNum));
            } isPreceedNum = false;

        } else if (jbtnSqrt.equals(source)){
            if (!"0".equals(dispNum) && !dispNum.isEmpty()){
                jtfSavedNum.setText("\u221A("+ dispNum +")");
                tempNum = String.valueOf(Math.sqrt(Double.parseDouble(dispNum)));
                jtfDisplay.setText(trimTrail0(tempNum));
            } isPreceedNum = false;

        } else if (jbtnSqr.equals(source)){
            if (!"0".equals(dispNum) && !dispNum.isEmpty()){
                jtfSavedNum.setText("sqr("+ dispNum +")");
                tempNum = String.valueOf(Math.pow(Double.parseDouble(dispNum), 2));
                jtfDisplay.setText(trimTrail0(tempNum));
            } isPreceedNum = false;
            
    /* Evaluation/equal button actions  */ 
        } else if (jbtnEqual.equals(source)){
            currNum = Double.parseDouble(dispNum);
            if (!operator.isEmpty()){
                jtfSavedNum.setText(jtfSavedNum.getText() + " " + dispNum + " =");
                tempNum = Double.toString(calculate(firstNum, currNum, operator));
                operator = ""; currNum = 0; firstNum = 0;
                jtfDisplay.setText(trimTrail0(tempNum));;
            } isPreceedNum = false;

        } else if (jbtnDec.equals(source)){
            if (!dispNum.contains(".")){
                jtfDisplay.setText(dispNum + ".");
            } else if (!isPreceedNum) {
                jtfDisplay.setText("0.");
            } isPreceedNum = true;

        } else if (jbtnNeg.equals(source)){
            if (!"0".equals(dispNum) && !dispNum.isEmpty()){
                tempNum = String.valueOf(Double.parseDouble(dispNum) * (-1));
                jtfDisplay.setText(trimTrail0(tempNum));
            } isPreceedNum = true;
        }
    }                                       
}
