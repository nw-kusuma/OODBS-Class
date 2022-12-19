import components.ImagePanel;
import controllers.Logicalc;
import controllers.Calculator;
import controllers.Temperature;
import static etc.Constants.*;
import static etc.Routines.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.formdev.flatlaf.*;

/**
 * @author N. W. Kusuma
 */
public class Main 
        extends JFrame implements ActionListener {
/* Component declarations */
    private JMenuBar jMenuBar;
    private JMenu    jmnLabel, jmnUtility;
    private JPanel   jpnlMain; 
    private JButton  jbtOnTop, jbtExit; 
    
    private JPopupMenu.Separator  jSeparator;

    private final JMenuItem[]     jMenuItems = new JMenuItem[6];
    private static final String[] jmniTexts = {
        "Standard Calculator", "Logical Calculator", "Temperature", 
        "Initial (home) view", "Always on top", "Exit"
    };
    
    private boolean onTopState = false;
    static Point mouseDownCompCoords = null;
/* End of Components declarations */
    
    //--- Class constructor
    public Main() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(302, 369));
        setIconImage(getImage("icons/UB-Logo-shield.png", 25, 25));
        setLocationRelativeTo(null);   //--- open the app at the center
        setUndecorated(true);   //--- this will hide the title bar
        setResizable(false);

        initComponents();
    }
    
/* Components initialization */
    private void initComponents() {
        jpnlMain = new ImagePanel("Logo UB.png", 275,200);
        setComponentSize(jpnlMain, 302, 337);
        jpnlMain.setLayout(null);
        
        getContentPane().add(jpnlMain, BorderLayout.CENTER);

        jMenuBar = new JMenuBar();
        setComponentSize(jMenuBar, 302, 32);
        jMenuBar.setLayout(new GridBagLayout());

        //--- Menu bar items grid-constrains for GridBagLayout
        final int[][] mnu_gbc = new int[][]{
            { 0, 0, 1, 1, 18, 0, 0, 0, 0 },  //---- Menu
            { 1, 0, 2, 1, 18, 0, 5, 0, 0 },  //---- Title
            { 3, 0, 1, 1, 18, 0, 5, 1, 0 },   //---- Close Button
            { 4, 0, 1, 1, 18, 0, 0, 1, 0 }   //---- Close Button
        };

        final String[] mnu_ico = {
            "icons/iconsCalculator.png",   //---- Calculator
            "icons/iconsBit-shift.png",    //---- Logical bit-shift
            "icons/iconsTemperature.png",  //---- Temperature
            "icons/iconsHome.png",  //---- Home
        };

        jmnUtility = new JMenu("\uE700");
        jmnUtility.setFont(FONT_18_ICO); 
        jmnUtility.setIconTextGap(0);
        jmnUtility.setHorizontalTextPosition(10);
        jmnUtility.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setComponentSize(jmnUtility, 32, 32);
        for (int i = 0; i < jMenuItems.length; i++){
            if ( i == 3 || i == 4 ){
                jSeparator = new JPopupMenu.Separator();
                jmnUtility.add(jSeparator);
            }
            jMenuItems[i] = new JMenuItem(jmniTexts[i]);
            jMenuItems[i].setFont(FONT_14); 
            jMenuItems[i].setIconTextGap(5);
            if ( i < 4 ){
                jMenuItems[i].setIcon(new ImageIcon(
                        getImage(mnu_ico[i], 18, 15)));
            }
            jMenuItems[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            jMenuItems[i].addActionListener(this);
            jmnUtility.add(jMenuItems[i]);
        }
        jMenuBar.add(jmnUtility, getGBConstraint(mnu_gbc[0]));

        jmnLabel = new JMenu("OODBS-Praktikum-01");
        jmnLabel.setEnabled(false);
        jmnLabel.setFocusable(false);
        jmnLabel.setFont(FONT_14); 
        jmnLabel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        setComponentSize(jmnLabel, 206, 32);
        //--- Since the title bar was hid, so we need to do the folowing...
        //--- mouse event listeners to allows drag/move on the app.
        jmnLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                mouseDownCompCoords = e.getPoint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                mouseDownCompCoords = null;
            }
        });
        jmnLabel.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - mouseDownCompCoords.x, 
                            currCoords.y - mouseDownCompCoords.y);
            }
        });
        jMenuBar.add(jmnLabel, getGBConstraint(mnu_gbc[1]));
        
        final Color[] btn_colors = { COLOR_FG_DEF, jMenuBar.getBackground() };
        
        //--- Always on top button
        jbtOnTop = new JButton("\uE840");
        jbtOnTop.setBorder(null);
        jbtOnTop.setToolTipText("Always on top");
        jbtOnTop.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setComponentProp(jbtOnTop, FONT_12_ICO, btn_colors);
        setComponentSize(jbtOnTop, 27, 31);
        jbtOnTop.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                jbtOnTop.setBackground(COLOR_BG_BTN);
            }
            @Override
            public void mouseExited(MouseEvent e){
                jbtOnTop.setBackground(btn_colors[1]);
            }
        });
        jbtOnTop.addActionListener(this);
        jMenuBar.add(jbtOnTop, getGBConstraint(mnu_gbc[2]));

        //--- Same reasons as the drag/move thing, this close button also
        //--- needed since the default 'x' close button is gone along with
        //--- the title bar...
        jbtExit = new JButton("\uEDAE");
        jbtExit.setBorder(null);
        jbtExit.setToolTipText("Close");
        jbtExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setComponentProp(jbtExit, FONT_12_ICO, btn_colors);
        setComponentSize(jbtExit, 27, 31);
        jbtExit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                jbtExit.setBackground(Color.RED.darker());
            }
            @Override
            public void mouseExited(MouseEvent e){
                jbtExit.setBackground(btn_colors[1]);
            }
        });
        jbtExit.addActionListener(this);
        jMenuBar.add(jbtExit, getGBConstraint(mnu_gbc[3]));

        setJMenuBar(jMenuBar);

        pack();
    }    
/* End of Components initialization */

    //--- Action event for the menu items
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(jMenuItems[5]) ||
            e.getSource().equals(jbtExit))
        {
            dispose();
            System.exit(0);
        } else if (e.getSource().equals(jMenuItems[4]) ||
                   e.getSource().equals(jbtOnTop)) {
            onTopState = !onTopState;
            setAlwaysOnTop(onTopState);
            jbtOnTop.setText(onTopState ? "\uE77A" : "\uE840");
            jbtOnTop.setToolTipText(onTopState ? "Disable always on top" 
                                               : "Enable always on top");
        } else {
            jpnlMain.setVisible(false);
            if (e.getSource().equals(jMenuItems[0])){
                jmnLabel.setText(jmniTexts[0]);
                jpnlMain = new Calculator();
            } else if (e.getSource().equals(jMenuItems[1])){
                jmnLabel.setText(jmniTexts[1]);
                jpnlMain = new Logicalc();
            } else if (e.getSource().equals(jMenuItems[2])){
                jmnLabel.setText(jmniTexts[2]);
                jpnlMain = new Temperature();
            } else if (e.getSource().equals(jMenuItems[3])){
                jmnLabel.setText("OODBS-Praktikum-01");
                jpnlMain = new ImagePanel("Logo UB.png", 275, 200);
            }
            jpnlMain.setVisible(true);
            this.add(jpnlMain);
        }
    }

    public static void main(String args[]) {
        //--- applying the dark flatlaf theme
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        
        EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}