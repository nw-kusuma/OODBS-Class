package controllers;

import static etc.Modules.*;
import static etc.Constants.*;
import models.Book;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/**
 * @author N. W. Kusuma
 */
public class Tab04Panel 
        extends JPanel {
/* 
 * Variables (components) declaration 
 */           
    private JPanel      jplNorth, jplCenter;
    private JLabel      jlbInfo, jlbPic;
    private JScrollPane jspTextArea;
    private JTextArea   jtaDesc;
    private JLabel      jlbTitle, jlbAuthor, jlbPub, jlbYear, jlbGenr;
    
    private JComboBox<String> jcbISBN;

    List<Book> books = new ArrayList<>();
//------------------------------------- End of variables declaration                  
   
    // Class constructor
    public Tab04Panel() {
        initComponents();

        books.add(book01);
        books.add(book02);
        books.add(book03);
    }

/* 
 * Components initialization 
 */           
    private void initComponents() {
        final String[] cbo_item_list;
        cbo_item_list = new String[] { 
            "ISBN code", 
            "978-1-47-516447-3", 
            "978-0-04-823139-0",
            "978-0-34-077108-2"
        };

        final int[][] comp_gbc = {
            { 0, 0, 1, 1, 18, 10, 10, 10, 0  },      //--- JComboBox
            { 1, 0, 1, 1, 18, 10, 15, 10, 10 },     //--- JLabel
            
            { 0, 0, 3, 1, 18, 10, 10, 0, 10 },
            { 0, 1, 1, 4, 18, 5, 10, 10, 0  },
            { 1, 1, 2, 1, 18, 5, 15, 0, 10  },
            { 1, 2, 2, 1, 18, 5, 15, 0, 10  },
            { 1, 3, 2, 1, 18, 5, 15, 0, 10  },
            { 1, 4, 1, 1, 18, 5, 15, 10, 0  },
            { 2, 4, 1, 1, 18, 5, 10, 10, 10 }
        };

        setSize(new Dimension(350, 230));
        setLayout(new BorderLayout());

        jplNorth = new JPanel();
        setComponentSize(jplNorth, 350, 45);
        jplNorth.setLayout(new GridBagLayout());

        jcbISBN = new JComboBox<>(cbo_item_list);
        setComponentSize(jcbISBN, 125, 25);
        jcbISBN.setFont(FONT_12);
        jcbISBN.setEditable(false);
        jcbISBN.addActionListener(cboAction);
        jplNorth.add(jcbISBN,getGBConstraint(comp_gbc[0]));

        jlbInfo = new JLabel("Books Information");
        setComponentSize(jlbInfo, 190, 25);
        jlbInfo.setFont(FONT_12);
        jplNorth.add(jlbInfo, getGBConstraint(comp_gbc[1]));

        add(jplNorth, BorderLayout.NORTH);

        jplCenter = new JPanel();
        setComponentSize(jplCenter, 350, 185);
        jplCenter.setBorder(createBorder("line", 1));
        jplCenter.setLayout(new GridBagLayout());

        jspTextArea = new JScrollPane();
        setComponentSize(jspTextArea, 330, 50);
        jspTextArea.setBorder(null);
        jspTextArea.setHorizontalScrollBarPolicy(31);

        jtaDesc = new JTextArea();
        setComponentSize(jtaDesc, 330, 50);
        jtaDesc.setFont(FONT_12);
        jtaDesc.setBorder(null);
        jtaDesc.setAutoscrolls(false);
        jtaDesc.setWrapStyleWord(true);
        jtaDesc.setLineWrap(true);
        jtaDesc.setEditable(false);
        jtaDesc.setEnabled(false);
        jtaDesc.setFocusable(false);
        jspTextArea.setViewportView(jtaDesc);
        jplCenter.add(jspTextArea, getGBConstraint(comp_gbc[2]));

        jlbPic = new JLabel();
        setComponentSize(jlbPic, 85, 110);
        jlbPic.setHorizontalAlignment(0);
        jlbPic.setIcon(null);
        jlbPic.setFocusable(false);
        jplCenter.add(jlbPic, getGBConstraint(comp_gbc[3]));

        jlbTitle = new JLabel("Title");
        setComponentSize(jlbTitle, 230, 25);
        jlbTitle.setFont(FONT_14);
        jplCenter.add(jlbTitle, getGBConstraint(comp_gbc[4]));

        jlbAuthor = new JLabel("Author");
        setComponentSize(jlbAuthor, 230, 25);
        jlbAuthor.setFont(FONT_14);
        jplCenter.add(jlbAuthor, getGBConstraint(comp_gbc[5]));

        jlbPub = new JLabel("Publisher");
        setComponentSize(jlbPub, 230, 25);
        jlbPub.setFont(FONT_12);
        jplCenter.add(jlbPub, getGBConstraint(comp_gbc[6]));

        jlbYear = new JLabel("Year");
        setComponentSize(jlbYear, 50, 20);
        jlbYear.setFont(FONT_12);
        jplCenter.add(jlbYear, getGBConstraint(comp_gbc[7]));

        jlbGenr = new JLabel("Genre");
        setComponentSize(jlbGenr, 170, 20);
        jlbGenr.setFont(FONT_12);
        jplCenter.add(jlbGenr, getGBConstraint(comp_gbc[8]));

        add(jplCenter, BorderLayout.CENTER);
    }
//------------------------------------- End of components initialization                  

/*
 * Some subroutines, the names should sufficiently self explanatory
 */
    private void displayBook (Book book) {
        jtaDesc.setText(book.getDescription());
        jlbTitle.setText(book.getTitle());
        jlbAuthor.setText(book.getAuthor());
        jlbPub.setText(book.getPublisher());
        jlbYear.setText(book.getYear());
        jlbGenr.setText(book.getGenre());

        jlbPic.setText("");
        jlbPic.setIcon(new ImageIcon(getImage(
                book.getCover(), jlbPic.getWidth(), jlbPic.getHeight())));
    }

  
    private void clearAll(){
        jtaDesc.setText("Description");
        jlbPic.setIcon(null);
        jlbPic.setText("Cover");
        jlbTitle.setText("Title");
        jlbAuthor.setText("Author");
        jlbPub.setText("Publisher");
        jlbYear.setText("Year");
        jlbGenr.setText("Genre");
    }
//------------------------------------- End of subroutines                  

/*
 * Action Listeners for the combobox components
 * This also demonstrate another way of using event listeners
 */
    ActionListener cboAction = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            switch (jcbISBN.getSelectedIndex())
            {
                case 0 -> { clearAll(); }
                case 1 -> displayBook(books.get(0));
                case 2 -> displayBook(books.get(1));
                case 3 -> displayBook(books.get(2));
                default -> {}
            }
        }
    };

/*
 * Following are the simple "mock" data for the books list. 
 * Its better to use Entity Class with database for this.
 */
    Book book01 = new Book(
            "978-1-47-516447-3",
            "The Worm Ouroboros",
            "E. R. Edison",
            "Jonathan Cape",
            "1922",
            "Fantasy",
            "The book that has inspired many fantasy writer including J. R. R. Tolkien, C. S. Lewis, and Ursula K. Le Guin",
            "thumbs/1475164475.jpg");

    Book book02 = new Book(
            "978-0-04-823139-0",
            "The Silmarillion",
            "J. R. R. Tolkien",
            "Allen & Unwin",
            "1977",
            "High Fantasy",
            "The comprehensive lore and tales on Tolkien's immersive Middle Earth and its inhabitants",
            "thumbs/0048231398.jpg");

    Book book03 = new Book( 
            "978-0-34-077108-2",
            "The Blue Nowhere",
            "Jeffrey Deaver",
            "Hodder & Stoughton",
            "2001",
            "Thriller",
            "A great edge-of-your-seat cyberspace thriller that will forever change the way you feel about your computer",
            "thumbs/0340771089.jpg");
//------------------------------------- End of book data
}