import static etc.Modules.*;
import static etc.Constants.*;

import controllers.Tab01Panel;
import controllers.Tab02Panel;
import controllers.Tab03Panel;
import controllers.Tab04Panel;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import javax.swing.*;

/**
 * @author N. W. Kusuma
 */
public class Main 
        extends JFrame {
/* 
 * Variables (components) declaration 
 */           
    private JPanel      jplTop, jplCenter, jplBottom;
    private JLabel      jlbMe, jlbClass, jlbStsBar, jlbLogo;
    private JTabbedPane jtpTabs;
    private JPanel      jplTab01, jplTab02, jplTab03, jplTab04;
    // private JPanel[] jplTabs = new JPanel[4];
//------------------------------------- End of variables declaration                  
    
    // Class constructor
    public Main() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(450, 333));
        setIconImage(getImage("icons/UB-Logo-shield.png", 25, 25));
        setTitle("OODBS-Praktikum-02");
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        
        setLocationRelativeTo(null);
    }

/* 
 * Components initialization 
 */           
    private void initComponents() {
        final String[] tab_titles = {
            "Tugas No. 01", "Tugas No. 02", "Tugas No. 03", "Tugas No. 04"
        };
        
        final int[][] lbl_top_gbc = {
            { 0, 0, 1, 1, 18, 10, 10, 10, 0 },
            { 1, 0, 1, 1, 18, 10, 5, 10, 10 },
        };

        jplTop = new JPanel();
        setComponentSize(jplTop, 450, 75);
        jplTop.setLayout(new GridBagLayout());
        
        jlbMe = new JLabel(" Nanang Wijaya Kusuma");
        setComponentSize(jlbMe, 185, 55);
        jlbMe.setFont(FONT_14);
        jlbMe.setHorizontalAlignment(2);
        jlbMe.setBorder(createBorder("titled", 2, "1202922001"));
        jplTop.add(jlbMe, getGBConstraint(lbl_top_gbc[0]));

        jlbClass = new JLabel(" Sistem Basis Data Berorientasi Obyek");
        setComponentSize(jlbClass, 240, 55);
        jlbClass.setFont(FONT_12);
        jlbClass.setHorizontalAlignment(2);
        jlbClass.setBorder(createBorder("titled", 2, "SIF905"));
        jplTop.add(jlbClass, getGBConstraint(lbl_top_gbc[1]));

        getContentPane().add(jplTop, BorderLayout.NORTH);

        jplCenter = new JPanel();
        setComponentSize(jplCenter, 450, 233);
        jplCenter.setFont(FONT_12);
        jplCenter.setLayout(null);

        jtpTabs = new JTabbedPane();
        setComponentSize(jtpTabs, 450, 233);
        jtpTabs.setFont(FONT_12);
        jtpTabs.setTabPlacement(2);
        jtpTabs.setBorder(createBorder("line", 1));
        jtpTabs.setBounds(0, 0, 450, 233);
        
        jplTab01 = new Tab01Panel();
        setComponentSize(jplTab01, 350, 230);
        jtpTabs.addTab(tab_titles[0], jplTab01);

        jplTab02 = new Tab02Panel();
        setComponentSize(jplTab02, 350, 230);
        jtpTabs.addTab(tab_titles[1], jplTab02);

        jplTab03 = new Tab03Panel();
        setComponentSize(jplTab03, 350, 230);
        jtpTabs.addTab(tab_titles[2], jplTab03);

        jplTab04 = new Tab04Panel();
        setComponentSize(jplTab04, 350, 230);
        jtpTabs.addTab(tab_titles[3], jplTab04);
        
        JLabel jlbFiller = new JLabel();
        jtpTabs.add(jlbFiller, "");
        jtpTabs.setEnabledAt(4, false);

        jlbLogo = new JLabel();
        jtpTabs.add(jlbLogo);
        jtpTabs.setDisabledIconAt(5, new ImageIcon(
              getImage("Logo UB.png", 75, 55)));
        jtpTabs.setEnabledAt(5, false);

        jplCenter.add(jtpTabs);

        getContentPane().add(jplCenter, BorderLayout.CENTER);
        
        jplBottom = new JPanel();
        setComponentSize(jplBottom,450,25);
        jplBottom.setLayout(null);

        jlbStsBar = new JLabel("Universitas Bakrie");
        setComponentSize(jlbStsBar, 440, 25);
        jlbStsBar.setFont(FONT_12);
        jlbStsBar.setHorizontalAlignment(4);
        jlbStsBar.setBounds(0, 0, 440, 25);
        jplBottom.add(jlbStsBar);

        getContentPane().add(jplBottom, BorderLayout.SOUTH);

        pack();
    }    
//------------------------------------- End of components initialization                  

/*
 * Main executable routines
 */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
//------------------------------------- End of main executable routines                  
}