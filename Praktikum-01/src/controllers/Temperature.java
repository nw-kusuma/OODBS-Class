package controllers;

import static etc.Constants.*;
import static etc.Routines.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * @author N. W. Kusuma
 */
public class Temperature 
        extends JPanel implements ActionListener {
/* Component declarations */
    private JPanel      jplTemper, jplDisplay, jplKeys;
    private JTextField  jtfInput, jtfOutput;
    private JButton     jbtNeg, jbtDot;

    private JComboBox<String> jcbInput, jcbOutput;

    private final JButton[] jbtNums = new JButton[10];
    private final JButton[] jbtFunc = new JButton[7];
    
/* End of Components declarations */
    
    // Class constructor
    public Temperature() {
        initComponents();
    }

/* Components initialization */
    private void initComponents() {
        final String[] cbo_items;
        cbo_items = new String[]{
            "Choose unit...",
            "Celsius (°C)", "Fahrenheit (°F)", "Kelvin (K)", "Rankine (°R)", 
            "Delisle (°De)", "Newton (°N)", "Réaumur (°Ré)", "Rømer (°Rø)" 
        };
        
        final Color[][] clr_fg_bg = new Color[][]{
            { COLOR_WHITE, COLOR_BG_DEF  },
            { COLOR_FG_DEF, COLOR_BG_DEF },
            { COLOR_WHITE, COLOR_BLACK   },
            { COLOR_FG_DEF, COLOR_BG_BTN }
        };

        setComponentSize(this, 302, 337);
        setLayout(null);

        jplTemper = new JPanel();
        setComponentSize(jplTemper, 302, 337);
        jplTemper.setFocusable(false);
        jplTemper.setLayout(new BorderLayout());

        jplDisplay = new JPanel();
        setComponentSize(jplDisplay, 302, 130);
        jplDisplay.setFocusable(false);
        jplDisplay.setLayout(new GridBagLayout());

        // Textfields and combobox grid-constrains for GridBagLayout
        final int[][] disp_gbc = new int[][]{
            { 0, 0, 2, 1, 18, 10, 10, 0, 10 }, 	//---- textfield input
            { 0, 1, 1, 1, 18, 0, 10, 0, 0   }, 	//---- combobox input

            { 0, 2, 2, 1, 18, 10, 10, 0, 10 }, 	//---- textfield output
            { 0, 3, 1, 1, 18, 0, 10, 15, 0  } 	//---- combobox output
        };
        
        // The textfield initialization
        // for displaying inputed temperature value 
        jtfInput = new JTextField("0");
        setComponentSize(jtfInput, 282, 30);
        setComponentProp(jtfInput, FONT_20, clr_fg_bg[0],2);
        jplDisplay.add(jtfInput, getGBConstraint(disp_gbc[0]));

        // The combobox initialization
        // for choosing temperature units of the inputed value 
        jcbInput = new JComboBox<>(cbo_items);
        setComponentSize(jcbInput, 144, 18);
        setComponentProp(jcbInput, FONT_14, clr_fg_bg[0]);
        jcbInput.addActionListener(this);
        jplDisplay.add(jcbInput, getGBConstraint(disp_gbc[1]));

        // The textfield initialization
        // for displaying converted temperature value 
        jtfOutput = new JTextField("0");
        setComponentSize(jtfOutput, 282, 30);
        setComponentProp(jtfOutput, FONT_20, clr_fg_bg[1],2);
        jplDisplay.add(jtfOutput, getGBConstraint(disp_gbc[2]));

        // The combobox initialization
        // for choosing the target convertion temperature units 
        jcbOutput = new JComboBox<>(cbo_items);
        setComponentSize(jcbOutput, 144, 18);
        setComponentProp(jcbOutput, FONT_14, clr_fg_bg[1]);
        jcbOutput.addActionListener(this);
        jplDisplay.add(jcbOutput, getGBConstraint(disp_gbc[3]));

        jplTemper.add(jplDisplay, BorderLayout.NORTH);

        jplKeys = new JPanel();
        setComponentSize(jplKeys, 302, 207);
        jplKeys.setFocusable(false);
        jplKeys.setLayout(new GridBagLayout());

        // The number buttons grid-constrains for GridBagLayout
        final int[][] nums_btn_gbc = new int[][]{
            { 1, 4, 1, 1, 18, 5, 6, 10, 0 },	//--- button zero
            { 0, 3, 1, 1, 18, 5, 10, 0, 0 },	//--- button one
            { 1, 3, 1, 1, 18, 5, 6, 0, 0  }, 	//--- button two
            { 2, 3, 1, 1, 18, 5, 6, 0, 0  }, 	//--- button three
            { 0, 2, 1, 1, 18, 5, 10, 0, 0 },	//--- button four
            { 1, 2, 1, 1, 18, 5, 6, 0, 0  }, 	//--- button five
            { 2, 2, 1, 1, 18, 5, 6, 0, 0  }, 	//--- button six
            { 0, 1, 1, 1, 18, 5, 10, 0, 0 }, 	//--- button seven
            { 1, 1, 1, 1, 18, 5, 6, 0, 0  }, 	//--- button eight
            { 2, 1, 1, 1, 18, 5, 6, 0, 0  }, 	//--- button nine

            { 0, 4, 1, 1, 18, 5, 10, 10, 0 },	//--- button negative
            { 2, 4, 1, 1, 18, 5, 6, 10, 0  }	//--- button decimal
        };
        
        for (int i = 0; i < jbtNums.length; i++){
            jbtNums[i] = new JButton(String.valueOf(i));
            setComponentSize(jbtNums[i], 66, 33);
            setComponentProp(jbtNums[i], FONT_16_BOLD, clr_fg_bg[2]);
            jbtNums[i].addActionListener(this);
            jplKeys.add(jbtNums[i], getGBConstraint(nums_btn_gbc[i]));
        }

        // The positive/negative button initialization
        jbtNeg = new JButton("\u207A\u2044\u208B");
        setComponentSize(jbtNeg, 66, 33);
        setComponentProp(jbtNeg, FONT_24_BOLD, clr_fg_bg[3]);
        jbtNeg.addActionListener(this);
        jplKeys.add(jbtNeg, getGBConstraint(nums_btn_gbc[10]));

        // The decimal (dot) button initialization
        jbtDot = new JButton("\u2219");
        setComponentSize(jbtDot, 66, 33);
        setComponentProp(jbtDot, FONT_24_BOLD, clr_fg_bg[3]);
        jbtDot.addActionListener(this);
        jplKeys.add(jbtDot, getGBConstraint(nums_btn_gbc[11]));

        // The function buttons grid-constrains for GridBagLayout
        final int[][] func_btn_gbc = new int[][]{
            { 0, 0, 2, 1, 18, 10, 10, 0, 0 }, 	//--- button swap
            { 2, 0, 1, 1, 18, 10, 6, 0, 0  }, 	//--- button clear
            { 3, 0, 1, 1, 18, 10, 6, 0, 10 }, 	//--- button backspace
            
            { 3, 1, 1, 1, 18, 5, 6, 0, 10  },	//--- button absolute zero
            { 3, 2, 1, 1, 18, 5, 6, 0, 10  },	//--- button freezing point
            { 3, 3, 1, 1, 18, 5, 6, 0, 10  },	//--- button boiling point
            { 3, 4, 1, 1, 18, 5, 6, 10, 10 }, 	//--- button body temperature
        };
    
        // The function buttons displayed text label
        final String[] btnTexts = {
            "SWAP", "CLEAR", "\u232B", "ABZ", "FP", "BP", "AHB"
        };

        // The function buttons displayed tooltip text
        final String[] btnTips = {
            "SWAP selected units measurement", 
            "CLEAR the display fields", 
            "Delete last inputed digit", 
            "ABSOLUTE ZERO point temperature", 
            "Freezing Point of water", 
            "Boiling Point of water", 
            "Average Human Body temperature"
        };

        // The temperature unit buttons initialization
        for (int i = 0; i < jbtFunc.length; i++){
            jbtFunc[i] = new JButton(btnTexts[i]);
            setComponentSize(jbtFunc[i], i==0 ? 138 : 66, 33);
            setComponentProp(jbtFunc[i], i==2 ? FONT_14_SYM : FONT_14_BOLD, clr_fg_bg[3]);
            jbtFunc[i].setToolTipText(btnTips[i]);
            jbtFunc[i].addActionListener(this);
            jplKeys.add(jbtFunc[i], getGBConstraint(func_btn_gbc[i]));
        }

        jplTemper.add(jplKeys, BorderLayout.CENTER);

        add(jplTemper);
        jplTemper.setBounds(0, 0, 302, 336);
    }
/* End of Components initialization */

/* Variables declaration */
    private double result = 0;
    private static final DecimalFormat df
            = new DecimalFormat("#.##################");
/* End of variables declaration */
    
    private double toCelsius(int unit, double num){
        double cels;
        switch (unit){
            case 1  -> cels = num; 
            case 2  -> cels = (num - 32) * 5 / 9; 
            case 3  -> cels = num - 273.15;
            case 4  -> cels = (num - 491.67) * 5 / 9;
            case 5  -> cels = 100 - num * 2 / 3;
            case 6  -> cels = num * 100 / 33;
            case 7  -> cels = num * 5 / 4;
            case 8  -> cels = (num - 7.5) * 40 / 21;
            default -> cels = 0.0;
        } return cels;
    }
    
    private double convert(double num, int from, int to){
        double cels = toCelsius(from, num);
        double res;
        switch (to){
            case 1  -> res = cels;
            case 2  -> res = cels  * 9 / 5 + 32;
            case 3  -> res = cels  + 273.15;
            case 4  -> res = (cels + 273.15) * 9 / 5;
            case 5  -> res = (100  - cels) * 3 / 2;
            case 6  -> res = cels  * 33 / 100;
            case 7  -> res = cels  * 4 / 5;
            case 8  -> res = cels  * 21 / 40 + 7.5;
            default -> res = 0.0;
        } return res;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object  source = e.getSource();
        String  inputNum = jtfInput.getText();
        int     input, output = jcbOutput.getSelectedIndex();
        
    /* Button Numbers actions  */ 
        for (int i = 0; i < jbtNums.length; i++){
            if (jbtNums[i].equals(source)){
                if ("0".equals(inputNum) || inputNum.isEmpty())
                {
                    jtfInput.setText(String.valueOf(i));
                } else {
                    jtfInput.setText(inputNum + String.valueOf(i));
                } 
                break;  
            } 
        }
        
    /* Functions button actions  */ 
        // Swap button
        if (jbtFunc[0].equals(source)){
//            String tempStr = jtfInput.getText();
//            jtfInput.setText(jtfOutput.getText()); 
//            jtfOutput.setText(tempStr);
            
            int tempIdx = jcbInput.getSelectedIndex();
            jcbInput.setSelectedIndex(jcbOutput.getSelectedIndex());
            jcbOutput.setSelectedIndex(tempIdx);
        } 
        
        // Clear button
        else if (jbtFunc[1].equals(source)){
            jtfInput.setText("0"); 
            jcbInput.setSelectedIndex(0);
            jtfOutput.setText("0");
            jcbOutput.setSelectedIndex(0);
        } 
        
        // Backspace (delete) button
        else if (jbtFunc[2].equals(source)){
            if (!"0".equals(inputNum) && !inputNum.isEmpty()){
                StringBuilder bsp = new StringBuilder(inputNum);
                bsp.deleteCharAt(inputNum.length() - 1);
                jtfInput.setText(bsp.toString());
            }
            if ("".equals(jtfInput.getText()) 
                    || "-".equals(jtfInput.getText()) 
                    || "-0".equals(jtfInput.getText())){
                jtfInput.setText("0");
            }
        } 

        // ABZ (absolute zero point) function button
        else if (jbtFunc[3].equals(source)){
            jcbInput.setSelectedIndex(3);
            jcbOutput.setSelectedIndex(output == 0 || output == 3 ? 1 : output);
            jtfInput.setText("0");
        } 
           
        // FP (water freezing point) function button
        else if (jbtFunc[4].equals(source)){
            jcbInput.setSelectedIndex(1);
            jcbOutput.setSelectedIndex(output <= 1 ? 2 : output);
            jtfInput.setText("0");
        } 

        // BP (water boiling point) function button
        else if (jbtFunc[5].equals(source)){
            jcbInput.setSelectedIndex(1);
            jcbOutput.setSelectedIndex(output <= 1 ? 2 : output);
            jtfInput.setText("100");
        } 
    
        // Body (average human body temperature) function button
        else if (jbtFunc[6].equals(source)){
            jcbInput.setSelectedIndex(1);
            jcbOutput.setSelectedIndex(output <= 1 ? 2 : output);
            jtfInput.setText("36.8");
        } 

    /* Decimal and positive/negative button actions  */ 
        else if (jbtDot.equals(source)){
            if (!inputNum.contains(".")){
                jtfInput.setText(inputNum + ".");
            } else if ("0".equals(inputNum) && inputNum.isEmpty()){
                jtfInput.setText("0.");
            }

        } else if (jbtNeg.equals(source)){
            if (!"0".equals(inputNum) && !inputNum.isEmpty()){
                jtfInput.setText(df.format(Double.parseDouble(inputNum) * (-1)));
            }
        }
        
        inputNum = jtfInput.getText();
        input = jcbInput.getSelectedIndex();
        output = jcbOutput.getSelectedIndex();
        
        result = convert(Double.parseDouble(inputNum),input,output);
        jtfOutput.setText(df.format(result));
    }
}