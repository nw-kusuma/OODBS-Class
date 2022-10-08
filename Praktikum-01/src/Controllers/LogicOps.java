package Controllers;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogicOps extends JPanel implements ActionListener {

    public LogicOps() {
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        initComponents();
    }

    // Variables declaration                  
    private JPanel jpnlLogic;
    private JTextField jtfDispNum, jtfDisplay, jtfDispBin;

    private JButton jbtnClear;
    private JButton jbtnNOT, jbtnAND, jbtnOR;
    private JButton jbtnTRUE, jbtnFALSE, jbtnEqual;
    
    private JButton[] jbtnNums = new JButton[10];
    // private JButton[] jbtnOprt = {jbtnNOT, jbtnAND, jbtnOR};

    private String operator = "", result = "";
    public boolean firstStr = false, currStr = false;
    public int len = 0, firstNum = 0, tempNum = 0, currNum = 0; 

    // End of variables declaration                   

    private void btnProp(JButton btn, int w, Font f){
        btn.setFont(f);
        btn.setFocusPainted(false);
//        btn.setBorder(null);
        btn.setFocusable(false);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setMargin(new Insets(2, 5, 2, 5));
        btn.setPreferredSize(new Dimension(w, 33));        
        btn.setMaximumSize(new Dimension(w, 33));
        btn.setMinimumSize(new Dimension(w, 33));
        btn.addActionListener(this);
    }

    private void tfProp(JTextField tf, int h, Font f){
        tf.setFont(f);
        tf.setEditable(false);
        tf.setBorder(null);
        tf.setFocusable(false);
        tf.setHorizontalAlignment(JTextField.RIGHT);
        tf.setMaximumSize(new Dimension(280, h));
    }

    private Font myFonts(int style, int size) {
        return new Font("Arial Unicode MS", style, size);
    }

    private void initComponents() {
        Color numBtnBg = new Color(51, 51, 51);
        Color numBtnFg = new Color(255, 255, 255);

        jpnlLogic = new JPanel();
        jpnlLogic.setFocusable(false);
        jpnlLogic.setMaximumSize(new Dimension(294, 316));
        jpnlLogic.setMinimumSize(new Dimension(294, 316));

        jtfDisplay = new JTextField("0");
        tfProp(jtfDisplay, 33, myFonts(0, 36));

        jtfDispNum = new JTextField("");
        tfProp(jtfDispNum, 17, myFonts(0, 14));

        jtfDispBin = new JTextField("");
        tfProp(jtfDispBin, 17, myFonts(0, 14));

        
        jbtnClear = new JButton("CLEAR");
        btnProp(jbtnClear, 280, myFonts(0, 16));
        
        for (int i = 0; i < 10; i++){
            jbtnNums[i] = new JButton(String.valueOf(i));
            jbtnNums[i].setBackground(numBtnBg);
            jbtnNums[i].setForeground(numBtnFg);
            btnProp(jbtnNums[i], 66, myFonts(0, 16));
        }
        
        jbtnFALSE = new JButton("false");
        btnProp(jbtnFALSE, 66, myFonts(0, 16));

        jbtnTRUE = new JButton("true");
        btnProp(jbtnTRUE, 66, myFonts(0, 16));

        jbtnEqual = new JButton("=");
        jbtnEqual.setBackground(Color.orange.darker());
        jbtnEqual.setForeground(new Color(51, 51, 51));
        btnProp(jbtnEqual, 66, myFonts(1, 20));

        // String[] sOprt = {"!", "&", "|"};
        // for (int i = 0; i < 3; i++){
        //     jbtnOprt[i] = new JButton(sOprt[i]);
        //     btnProp(jbtnOprt[i], 66, myFonts(0, 16));
        // }
        jbtnNOT = new JButton("!");
        btnProp(jbtnNOT, 66, myFonts(0, 16));

        jbtnAND = new JButton("&");
        btnProp(jbtnAND, 66, myFonts(0, 14));

        jbtnOR = new JButton("|");
        btnProp(jbtnOR, 66, myFonts(0, 14));

        setComponentLayout();
    }

    private void setComponentLayout(){
        // Layout generated using SceneBuilder
        GroupLayout jpnlLogicLayout = new GroupLayout(jpnlLogic);
        jpnlLogic.setLayout(jpnlLogicLayout);
        jpnlLogicLayout.setHorizontalGroup(
            jpnlLogicLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpnlLogicLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlLogicLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jbtnClear, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpnlLogicLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jpnlLogicLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtfDisplay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.LEADING, jpnlLogicLayout.createSequentialGroup()
                                .addComponent(jbtnNums[7], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnNums[8], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnNums[9], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnNOT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.LEADING, jpnlLogicLayout.createSequentialGroup()
                                .addComponent(jbtnNums[4], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnNums[5], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnNums[6], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnAND, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.LEADING, jpnlLogicLayout.createSequentialGroup()
                                .addComponent(jbtnNums[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnNums[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnNums[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnOR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.LEADING, jpnlLogicLayout.createSequentialGroup()
                                .addComponent(jbtnTRUE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnNums[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnFALSE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnEqual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtfDispNum, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE))
                        .addComponent(jtfDispBin, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnlLogicLayout.setVerticalGroup(
            jpnlLogicLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpnlLogicLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtfDispNum, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfDisplay, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfDispBin, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnClear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlLogicLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnNums[7], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[8], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[9], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNOT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlLogicLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnNums[4], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[5], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[6], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnAND, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlLogicLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnNums[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNums[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnOR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlLogicLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlLogicLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtnNums[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtnTRUE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtnFALSE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEqual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpnlLogic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpnlLogic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
    }

    private void btnsDisEn(boolean isBool){
        for (int i = 0; i < 10; i++){
            jbtnNums[i].setEnabled(!isBool);
        }
        jbtnFALSE.setEnabled(isBool ? true : false);
        jbtnTRUE.setEnabled(isBool ? true : false);
    }

    private void enableAll(){
        for (int i = 0; i < 10; i++){
            jbtnNums[i].setEnabled(true);
        }
        jbtnFALSE.setEnabled(true);
        jbtnTRUE.setEnabled(true);
        jbtnNOT.setEnabled(true);
    }

    private String int2BinStr (int num) {
        return String.format("%4s", Integer.toBinaryString(num))
                     .replace(' ', '0');
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (jbtnClear.equals(source)){
            jtfDisplay.setText("0"); 
            jtfDispNum.setText("");
            jtfDispBin.setText("");
            firstNum = 0; currNum = 0; tempNum = 0;
            firstStr = false; currStr = false;
            operator = ""; 
            enableAll();

        } else if (jbtnFALSE.equals(source)){
            jtfDisplay.setText("false");
            jtfDispBin.setText("");
            jbtnNOT.setEnabled(true);
            if ((!jtfDispNum.getText().contains("AND") || 
                 !jtfDispNum.getText().contains("OR")) && 
                  jtfDispNum.getText().endsWith("="))
            {
                jtfDispNum.setText("(false)");
            } else if (jtfDispNum.getText().endsWith("AND") 
                    || jtfDispNum.getText().endsWith("OR"))
            {
                jtfDispNum.setText(jtfDispNum.getText() + " (false)");
            } else {
                len = jtfDispNum.getText().endsWith("(false)") ? 8 : 7;
                jtfDispNum.setText(jtfDispNum.getText()
                            .substring(0, jtfDispNum
                            .getText().length() - len) + " (true)");
            }
            
        } else if (jbtnTRUE.equals(source)){
            jtfDisplay.setText("true");
            jtfDispBin.setText("");
            jbtnNOT.setEnabled(true);
            if ((!jtfDispNum.getText().contains("AND") || 
                 !jtfDispNum.getText().contains("OR")) && 
                  jtfDispNum.getText().endsWith("="))
            {
                jtfDispNum.setText("(true)");
            } else if (jtfDispNum.getText().endsWith("AND") 
                    || jtfDispNum.getText().endsWith("OR"))
            {
                jtfDispNum.setText(jtfDispNum.getText() + " (true)");
            } else {
                len = jtfDispNum.getText().endsWith("(false)") ? 8 : 7;
                jtfDispNum.setText(jtfDispNum.getText()
                            .substring(0, jtfDispNum
                            .getText().length() - len) + " (true)");
            }
            
        } else if (jbtnOR.equals(source)){
            operator = "|";
            if ("false".equals(jtfDisplay.getText()) || 
                "true".equals(jtfDisplay.getText()))
            {
                firstStr = Boolean.parseBoolean(jtfDisplay.getText());
                jtfDispNum.setText("(" + jtfDisplay.getText() + ") OR");
                btnsDisEn(true);
            } else {
                firstNum = Integer.parseInt(jtfDisplay.getText());
                jtfDispNum.setText("(" + jtfDisplay.getText() + ") OR");
                jtfDispBin.setText(int2BinStr(firstNum) + " ||");
                btnsDisEn(false);
            } 

        } else if (jbtnAND.equals(source)){
            operator = "&";
            if ("false".equals(jtfDisplay.getText()) || 
                "true".equals(jtfDisplay.getText()))
            {
                firstStr = Boolean.parseBoolean(jtfDisplay.getText());
                jtfDispNum.setText("(" + jtfDisplay.getText() + ") AND");
                btnsDisEn(true);
            } else {
                firstNum = Integer.parseInt(jtfDisplay.getText());
                jtfDispNum.setText("(" + jtfDisplay.getText() + ") AND");
                jtfDispBin.setText(int2BinStr(firstNum) + " &&");
                btnsDisEn(false);
            } 
            
        } else if (jbtnNOT.equals(source)){
            operator = "!";
            if (!jtfDisplay.getText().isEmpty())
            {
                currStr = Boolean.parseBoolean(jtfDisplay.getText());
                jtfDisplay.setText(Boolean.toString(!currStr));
                jtfDispNum.setText("NOT(" + currStr + ") =");
            } btnsDisEn(true);
            
        } else if (jbtnEqual.equals(source)){
            switch (operator){
                case "!": 
                    currStr = Boolean.parseBoolean(jtfDisplay.getText());
                    result = Boolean.toString(!currStr); break;
                case "&": 
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
                        jtfDispBin.setText(jtfDispBin.getText() + " = " + 
                                        int2BinStr(Integer.parseInt(result)));
                    } break;
                case "|": 
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
                        jtfDispBin.setText(jtfDispBin.getText() + " = " + 
                                        int2BinStr(Integer.parseInt(result)));
                   } break;
                default : break;
            } 
            jtfDisplay.setText(result);
            firstNum = 0; currNum = 0; tempNum = 0;
            firstStr = false; currStr = false;
            operator = ""; 
            enableAll();

        } else {
            for (int i = 0; i <10; i++){
                if (jbtnNums[i].equals(source)){
                    tempNum = i; break;
                } 
            } 
            jtfDisplay.setText(String.valueOf(tempNum));
            if (!(jtfDispBin.getText().contains("||")) || 
                !(jtfDispBin.getText().contains("&&")) &&
                  jtfDispBin.getText().contains("="))
            {
                jtfDispBin.setText(int2BinStr(tempNum));
            } else if (jtfDispBin.getText().endsWith("||") 
                    || jtfDispBin.getText().endsWith("&&"))
            {
                jtfDispBin.setText(jtfDispBin.getText() + " " + int2BinStr(tempNum));
            } else {
                jtfDispBin.setText(jtfDispBin.getText()
                          .substring(0, 8) + int2BinStr(tempNum));
            }
            jbtnNOT.setEnabled(false);
        }
    }
}
