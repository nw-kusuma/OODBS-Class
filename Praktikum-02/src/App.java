import com.formdev.flatlaf.FlatDarkLaf;

import controllers.Tab01Panel;
import controllers.Tab02Panel;
import controllers.Tab03Panel;
import controllers.Tab04Panel;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class App extends JFrame {

    public App() {
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        initComponents();
    }

// Variables declaration
    private JPanel jplTop;
    private JLabel jlbMe, jlbClass;

    private JPanel jplCenter;
    private JTabbedPane jtpTabs;
    private JPanel jplTab01, jplTab02, jplTab03, jplTab04;

    private Tab01Panel tab01pnl;
    private Tab02Panel tab02pnl;
    private Tab03Panel tab03pnl;
    private Tab04Panel tab04pnl;

    private JPanel jplBottom;
    private JLabel jlbStsBar;
// End of variables declaration                   

// Components instantiations
    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("OODBS Praktikum-02");
        setFont(new Font("Arial Unicode MS", 0, 12)); 
        setPreferredSize(new Dimension(450, 345));
        setResizable(false);

    /* Top panel */    
        jplTop = new JPanel();
        setComponentSize(jplTop, 450, 65);
        jplTop.setLayout(new FlowLayout());

        jlbMe = new JLabel("Nanang Wijaya Kusuma");
        jlbMe.setFont(new Font("Arial Unicode MS", 1, 12));
        jlbMe.setPreferredSize(new Dimension(180, 50));
        jlbMe.setHorizontalAlignment(SwingConstants.CENTER);
        jlbMe.setVerticalAlignment(SwingConstants.TOP);
        setTFBorder(jlbMe, "1202922001");
        jplTop.add(jlbMe);

        jlbClass = new JLabel("Sistem Basis Data Berorientasi Obyek");
        jlbClass.setFont(new Font("Arial Unicode MS", 1, 12));
        jlbClass.setPreferredSize(new Dimension(240, 50));
        jlbClass.setHorizontalAlignment(SwingConstants.CENTER);
        jlbClass.setVerticalAlignment(SwingConstants.TOP);
        setTFBorder(jlbClass, "SIF905");
        jplTop.add(jlbClass);

        getContentPane().add(jplTop, BorderLayout.NORTH);
    /* End of top panel */    

    /* Center panel */    
        jplCenter = new JPanel();
        jplCenter.setLayout(new GridLayout());

        jtpTabs = new JTabbedPane();
        jtpTabs.setBorder(BorderFactory.createLineBorder(new Color(80, 83, 85)));
        jtpTabs.setTabPlacement(JTabbedPane.LEFT);

        jplTab01 = new JPanel();
        jplTab01.setLayout(new GridLayout());

        tab01pnl = new Tab01Panel();
        jplTab01.add(tab01pnl);
        jtpTabs.addTab("Tugas No. 01", jplTab01);

        jplTab02 = new JPanel();
        jplTab02.setLayout(new GridLayout());

        tab02pnl = new Tab02Panel();
        jplTab02.add(tab02pnl);
        jtpTabs.addTab("Tugas No. 02", jplTab02);

        jplTab03 = new JPanel();
        jplTab03.setLayout(new GridLayout());

        tab03pnl = new Tab03Panel();
        jplTab03.add(tab03pnl);
        jtpTabs.addTab("Tugas No. 03", jplTab03);

        jplTab04 = new JPanel();
        jplTab04.setLayout(new GridLayout());

        tab04pnl = new Tab04Panel();
        jplTab04.add(tab04pnl);
        jtpTabs.addTab("Tugas No. 04", jplTab04);

        jplCenter.add(jtpTabs, BorderLayout.CENTER);

        getContentPane().add(jplCenter, BorderLayout.CENTER);
    /* End of center panel */    

    /* Bottom panel */    
        jplBottom = new JPanel();
        setComponentSize(jplBottom, 450, 25);
        jplBottom.setLayout(new GridLayout());

        jlbStsBar = new JLabel("::SIF - Universitas Bakrie    ");
        jlbStsBar.setFont(new Font("Arial Unicode MS", 0, 12)); 
        jlbStsBar.setHorizontalAlignment(SwingConstants.RIGHT);
        jplBottom.add(jlbStsBar);

        getContentPane().add(jplBottom, BorderLayout.SOUTH);
    /* End of bottom panel */    

        pack();
    }                       

    private void setTFBorder (JComponent comp, String title) {
        Border lineBorder = BorderFactory.createLineBorder(new Color(80, 83, 85));
        comp.setBorder(BorderFactory.createTitledBorder(
                            lineBorder, title, 
                            TitledBorder.DEFAULT_JUSTIFICATION, 
                            TitledBorder.DEFAULT_POSITION, 
                            new Font("Arial Unicode MS", 0, 12))); 
    }             

    private void setComponentSize(JComponent comp, int w, int h){
        comp.setMaximumSize(new Dimension(w, h));
        comp.setMinimumSize(new Dimension(w, h));
        comp.setPreferredSize(new Dimension(w, h));
    }
// End of components instantiations

    public static void main(String args[]) {

    /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }
}