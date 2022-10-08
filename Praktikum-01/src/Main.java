import Controllers.Calculator;
import Controllers.LogicOps;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

    public Main() {
    // FlatLaf Dark theme using FlatLaf library
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        compSize(this, 308, 352);
        setResizable(false);

        jMenuBar = new JMenuBar();
        compSize(jMenuBar, 294, 32);

        jmnUtility = new JMenu("\u2630");
        jmnUtility.setFont(myFonts(18));

        jmniCalc = new JMenuItem("Calculator");
        jmniCalc.setFont(myFonts(12));
        jmniCalc.addActionListener(this);

        // jmniTemper = new JMenuItem();
        // jmniTemper.setFont(myFonts(12));
        // jmniTemper.setText("Temperature");

        jmniLogic = new JMenuItem("Logical operation");
        jmniLogic.setFont(myFonts(12));
        jmniLogic.addActionListener(this);

        jmnUtility.add(jmniCalc);
        jmnUtility.add(jmniLogic);

        jmnLabel = new JMenu();
        jmnLabel.setFont(myFonts(16));
        jmnLabel.setText(jmniCalc.getText());
        jmnLabel.setFocusable(false);
        jmnLabel.setEnabled(false);

        jMenuBar.add(jmnUtility);
        jMenuBar.add(jmnLabel);

        setJMenuBar(jMenuBar);
        initComponents(content);
    }

    // Variables declaration                  
    private JMenuBar jMenuBar;
    private JMenu jmnUtility, jmnLabel;
    private JMenuItem jmniCalc, jmniLogic;

    private static Calculator jpnlCalc = new Calculator();
    private static LogicOps jpnlLogic = new LogicOps();

    public static Component content = jpnlCalc;
    public static Main mainFrame = new Main();
    // End of variables declaration                   

    private void compSize(Component comp, int w, int h){
        comp.setMaximumSize(new Dimension(w, h));
        comp.setMinimumSize(new Dimension(w, h));
        comp.setPreferredSize(new Dimension(w, h));
    }

    private Font myFonts(int i){
        return new Font("Arial Unicode MS", 0, i);
    }

    // Components initialization
    private void initComponents(Component comp) {
   
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(comp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(comp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        jmnLabel.setText(jpnlCalc.equals(content) ? jmniCalc.getText() : jmniLogic.getText());
        if (content.equals(jpnlCalc) && e.getSource() == jmniLogic){
            content = jpnlLogic;
            jpnlLogic.setVisible(true);
            jpnlCalc.setVisible(false);
            jmnLabel.setText(jmniLogic.getText());
            mainFrame.remove(jpnlCalc);
        } else if (content.equals(jpnlLogic) && e.getSource() == jmniCalc){
            content = jpnlCalc;
            jpnlCalc.setVisible(true);
            jpnlLogic.setVisible(false);
            jmnLabel.setText(jmniCalc.getText());
            mainFrame.remove(jpnlLogic);
        }
        mainFrame.initComponents(content);
        mainFrame.setVisible(true);
    }
     
    public static void main(String args[]) {
        // Run the app
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainFrame.setVisible(true);
            }
        });
    }

}
