package controllers;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author N. W. Kusuma
 */
public class Tab01Panel extends JPanel implements ActionListener {

    public Tab01Panel() {
        initComponents();

    }

// Variables declaration               
    private JTextField jtfScore;
    private JButton jbtnClear;
    private JTextField jtfGrade;

    /*
     * regex matches only positive (including floating) numbers...
     * it is either up to 3 digits, i.e. 76 or 456
    ...OR...
     * up to 5 digits including a dot (.) for floating numbers 
     * i.e. 99.99 or 76.0 or 1.1 
     */
    private String regex = "^(([0-9]{1,3}$)|([0-9]{1,2}[.][0-9]{0,}$))";
    private Double score = 0.0;
// End of variables declaration                   

// Components instantiations
    private void initComponents() {
        GridBagConstraints gbc;

        setFont(new Font("Arial Unicode MS", 0, 12)); 
        setComponentSize(this, 325, 225);
        // setMaximumSize(new Dimension(360, 200));
        // setMinimumSize(new Dimension(360, 200));
        // setPreferredSize(new Dimension(360, 200));
        setLayout(new GridBagLayout());

        jtfScore = new JTextField("");
        jtfScore.setHorizontalAlignment(JTextField.CENTER);
        jtfScore.setBackground(new Color(60, 63, 65));
        jtfScore.setFont(new Font("Arial Unicode MS", 0, 20)); 
        setTFBorder(jtfScore, TitledBorder.ABOVE_TOP, 14, "Input your score:");
        setComponentSize(jtfScore, 210, 45);
        jtfScore.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
                jtfScoreKeyReleased(e);
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        // gbc.ipadx = -5;
        gbc.ipady = 10;
        // gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 0, 0);
        add(jtfScore, gbc);

        jbtnClear = new JButton("Clear");
        jbtnClear.setFont(new Font("Arial Unicode MS", 0, 18));
        setComponentSize(jbtnClear, 60, 30);
        jbtnClear.addActionListener(this); 
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = -15;
        gbc.ipady = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(30, 10, 0, 12);
        add(jbtnClear, gbc);

        jtfGrade = new JTextField("?");
        jtfGrade.setEditable(false);
        jtfGrade.setHorizontalAlignment(JTextField.CENTER);
        jtfGrade.setBackground(new Color(60, 63, 65));
        jtfGrade.setFont(new Font("Arial Unicode MS", 1, 100)); 
        setTFBorder(jtfGrade, TitledBorder.BELOW_TOP, 11, "Your grade will be shown bellow...");;
        setComponentSize(jtfGrade, 305, 135);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.ipadx = 20;
        // gbc.ipady = 5;
        // gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(12, 10, 10, 10);
        add(jtfGrade, gbc);
    }   

    private void setTFBorder (JComponent comp, int pos, int sz, String title) {
        Border lineBorder = BorderFactory.createLineBorder(new Color(80, 83, 85));
        comp.setBorder(BorderFactory.createTitledBorder(
                            lineBorder, title, 
                            TitledBorder.LEFT, pos, 
                            new Font("Arial Unicode MS", 0, sz))); 
    }  
    
    private void setComponentSize(JComponent comp, int w, int h){
        comp.setMaximumSize(new Dimension(w, h));
        comp.setMinimumSize(new Dimension(w, h));
        comp.setPreferredSize(new Dimension(w, h));
    }
// End of components instantiations

/*
 * Just some subroutines, 
 * the names should sufficiently self explanatory
 */
    private void convertScore(Double num){
        String sGrade = "!", sComment = "";

        if (num == -1.0){
            sComment = "Your score is invalid, only numbers and dot [.] are allowed.";
        } else if (num <= 50){
            sGrade = "E";
            sComment = "Don't despair, change your perspective and try again.";
        } else if (num <= 60){
            sGrade = "D";
            sComment = "Well, as they say, it's just another delay to success.";
        } else if (num <= 70){
            sGrade = "C";
            sComment = "Not bad. You really need to step up the efforts.";
        } else if (num <= 84){
            sGrade = "B";
            sComment = "Great! Now just keep up the good works.";
        } else if (num <= 100){
            sGrade = "A";
            sComment = "Excellent!! Keep it going.";
        } else 
            sComment = "Your score is out of bound, only [0-100] ranges are allowed.";

        jtfGrade.setText(sGrade);
        setTFBorder(jtfGrade, TitledBorder.BELOW_TOP, 11, sComment); 
    }

    private void resetAll(){
        String sGrade = "?", sComment = "Your score will be shown below...";
        setTFBorder(jtfGrade, TitledBorder.BELOW_TOP, 11, sComment);
        jtfGrade.setText(sGrade);
        jtfScore.setText("");
        jtfScore.grabFocus();
    }    
// ..............................

/*
 * Action event Listener for the button component.
 * Super implementation is the common way of programatically 
 * declaring listerner especially when using editors. 
 */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (jbtnClear.equals(e.getSource())) resetAll();
    }

/*
 * Key event Listeners for the textfield components
 * This is the common way of implementing Listeners 
 * found in GUI IDE such as Netbeans or Eclipse
 */
    private void jtfScoreKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
            && jtfScore.getText().isEmpty()) 
        {
            resetAll(); return;
        } 
        else if (jtfScore.getText().matches(regex)){
        // try {
             score = Double.parseDouble(jtfScore.getText());
        // } catch (NumberFormatException ex){
        } else        
            score = -1.0;
        // } finally {
            convertScore(score);  
        // }  
        return;
    }
}