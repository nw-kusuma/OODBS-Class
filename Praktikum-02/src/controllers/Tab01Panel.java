package controllers;

import static etc.Modules.*;
import static etc.Constants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author N. W. Kusuma
 */
public class Tab01Panel 
        extends JPanel implements KeyListener {
/* 
 * Variables (components) declaration 
 */           
    private JButton     jbtClear;
    private JTextField  jtfScore, jtfGrade;

    // regex matches only positive (including floating) numbers...
    // it is either up to 3 digits, i.e. 76 or 456
    // ...OR...
    // up to 5 digits including a dot (.) for floating numbers 
    // i.e. 99.99 or 76.0 or 1.1 
    private static final String TF_REGEX = 
            "^(([0-9]{1,3}$)|([0-9]{1,2}[.][0-9]{0,}$))";

    private Double score = 0.0;
//------------------------------------- End of variables declaration                  
    
    // Class constructor
    public Tab01Panel() {
        initComponents();
    }

/* 
 * Components initialization 
 */           
    private void initComponents() {
        final int[][] comp_gbc = {
            { 0, 0, 1, 2, 18, 10, 10, 0, 0   },
            { 1, 1, 1, 1, 18, 28, 10, 0, 10  },
            { 0, 2, 2, 1, 18, 15, 10, 10, 10 }
        };

        setSize(new Dimension(350, 230));
        setLayout(new GridBagLayout());

        jtfScore = new JTextField("0");
        setComponentSize(jtfScore, 245, 50);
        jtfScore.setFont(FONT_24);
        jtfScore.setBackground(COLOR_BG_DEF);
        jtfScore.setHorizontalAlignment(0);
        jtfScore.setBorder(createBorder("titled", 1,
                                    "Input your score:"));
        jtfScore.addKeyListener(this);
        add(jtfScore, getGBConstraint(comp_gbc[0]));

        jbtClear = new JButton("Clear");
        setComponentSize(jbtClear, 75, 30);
        jbtClear.setFont(FONT_14);
        jbtClear.setFocusable(false);
        jbtClear.addActionListener((ActionEvent e) ->{ resetAll(); });
        add(jbtClear, getGBConstraint(comp_gbc[1]));

        jtfGrade = new JTextField("?");
        setComponentSize(jtfGrade, 330, 150);
        jtfGrade.setEditable(false);
        jtfGrade.setFont(FONT_BIG_BOLD);
        jtfGrade.setHorizontalAlignment(0);
        jtfGrade.setBorder(createBorder("titled", 3,
                                    "Your grade will be shown below..."));
        add(jtfGrade, getGBConstraint(comp_gbc[2]));
    }
//------------------------------------- End of components initialization                  

/*
 * Some subroutines, the names should sufficiently self explanatory
 */
    private void convertScore(Double num){
        String sGrade = "!", sComment;

        if ( num < 0.0 ){
            sComment = "Your score is invalid, only (positive) real numbers allowed.";
        } else if ( num <= 50 ){
            sGrade = "E";
            sComment = "Don't despair, change your perspective and try again.";
        } else if ( num <= 60 ){
            sGrade = "D";
            sComment = "Well, as they say, it's just another delay to success.";
        } else if ( num <= 70 ){
            sGrade = "C";
            sComment = "Not bad. You really need to step up the efforts.";
        } else if ( num <= 84 ){
            sGrade = "B";
            sComment = "Great! Now just keep up the good works.";
        } else if ( num <= 100 ){
            sGrade = "A";
            sComment = "Excellent!! Keep it going.";
        } else sComment = "Your score is out of bound, only numbers [0-100] are allowed.";

        jtfGrade.setText(sGrade);
        jtfGrade.setBorder(createBorder("titled",3,sComment));
    }

    private void resetAll(){
        jtfScore.grabFocus();
        jtfScore.setText("");
        jtfGrade.setText("?");
        jtfGrade.setBorder(createBorder("titled", 3,
                                    "Your grade will be shown below..."));
    }    
//------------------------------------- End of subroutines                  

/*
 * Key(boards) event Listener declaration for the textfield component.
 * Super (override) implementation is the common way of programatically 
 * declaring event listerner especially when using editors. 
 * -----------------------------------------------------------------------
 * We only needed keyReleased event, but super implementation
 * requires all overrides of the key event methods to be declared as well
 */
    @Override
    public void keyReleased (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
            && jtfScore.getText().isEmpty()) 
        {
            resetAll(); return;
        } else if (jtfScore.getText().matches(TF_REGEX)){
             score = Double.valueOf(jtfScore.getText());
        } else score = -1.0;
        convertScore(score);  
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}
//------------------------------------- End of event listener                  
}