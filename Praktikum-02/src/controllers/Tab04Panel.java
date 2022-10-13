package controllers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

/**
 * @author N. W. Kusuma
 */
public class Tab04Panel extends JPanel {
// Class constructor
    public Tab04Panel() {
        initComponents();
    }


// Variables declaration
    private JPanel jpnlNorth;
    private JComboBox<String> jcboISBN;
    private JLabel jlbInfo;

    private JPanel jpnlCenter;
    private JScrollPane jspTextArea;
    private JTextArea jtaDesc;

    private JLabel jlbPic, jlbTitle;
    private JLabel jlbAuthor, jlbPub;
    private JLabel jlbYear, jlbGenr;
// End of variables declaration                   

/*
 * Following are the "mock" data for the books.
 * Its better to use database for this.
 */
    // List<Book> myBooks = new ArrayList<Book>();
    Book book01 = new Book("978-1-47-516447-3", 
                          "The Worm Ouroboros", 
                         "E. R. Edison", 
                            "Jonathan Cape", 
                           "1922",
                          "Fantasy",
                           "The book that has inspired many fantasy writer including J. R. R. Tolkien, C. S. Lewis, and Ursula K. Le Guin",
                          "src/images/1475164475.jpg");

    Book book02 = new Book("978-0-04-823139-0", 
                          "The Silmarillion", 
                         "J. R. R. Tolkien", 
                            "Allen & Unwin", 
                           "1977",
                          "High Fantasy",
                           "The comprehensive lore and tales on Tolkien's immersive Middle Earth and its inhabitants",
                          "src/images/0048231398.jpg");

    Book book03 = new Book("978-0-34-077108-2", 
                          "The Blue Nowhere", 
                         "Jeffrey Deaver", 
                            "Hodder & Stoughton", 
                           "2001",
                          "Thriller",
                           "A great edge-of-your-seat cyberspace thriller that will forever change the way you feel about your computer",
                          "src/images/0340771089.jpg");
//.....................................................
// End of mock data

// Components instantiations
    private void initComponents() {
        GridBagConstraints gbc;

        setMaximumSize(new Dimension(325, 225));
        setMinimumSize(new Dimension(325, 225));
        setPreferredSize(new Dimension(325, 225));
        setLayout(new BorderLayout());

        jpnlNorth = new JPanel();
        setComponentSize(jpnlNorth, 325, 40);
        jpnlNorth.setLayout(new GridBagLayout());

        jcboISBN = new JComboBox<>();
        jcboISBN.setEditable(false);
        jcboISBN.setFont(new Font("Arial Unicode MS", 0, 12)); 
        jcboISBN.setModel(new DefaultComboBoxModel<>(new String[] {
            "ISBN code", 
            "978-1-47-516447-3", 
            "978-0-04-823139-0",
            "978-0-34-077108-2"
        }));
        setComponentSize(jcboISBN, 125, 25);
        jcboISBN.addActionListener(cboAction); // Adding action listener to combobox compnent 
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(8, 10, 8, 0);
        jpnlNorth.add(jcboISBN, gbc);

        jlbInfo = new JLabel("Book Informations");
        jlbInfo.setFont(new Font("Arial Unicode MS", 0, 13)); 
        setComponentSize(jlbInfo, 160, 25);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(8, 16, 8, 10);
        jpnlNorth.add(jlbInfo, gbc);

        add(jpnlNorth, BorderLayout.NORTH);

        jpnlCenter = new JPanel();
        jpnlCenter.setBorder(BorderFactory.createLineBorder(new Color(80, 83, 85)));
        setComponentSize(jpnlCenter, 325, 185);
        jpnlCenter.setLayout(new GridBagLayout());

        jspTextArea = new JScrollPane();
        jspTextArea.setBorder(null);
        jspTextArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setComponentSize(jspTextArea, 300, 45);

        jtaDesc = new JTextArea("Description");
        jtaDesc.setEditable(false);
        jtaDesc.setColumns(20);
        jtaDesc.setFont(new Font("Arial Unicode MS", 0, 12)); 
        jtaDesc.setLineWrap(true);
        jtaDesc.setRows(2);
        jtaDesc.setWrapStyleWord(true);
        jtaDesc.setAutoscrolls(false);
        jtaDesc.setBorder(null);
        jtaDesc.setFocusable(false);
        jspTextArea.setViewportView(jtaDesc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 10, 0, 10);
        jpnlCenter.add(jspTextArea, gbc);

        jlbPic = new JLabel("Cover");
        jlbPic.setHorizontalAlignment(SwingConstants.CENTER);
        jlbPic.setFocusable(false);
        setComponentSize(jlbPic, 80, 100);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 4;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 8, 0);
        jpnlCenter.add(jlbPic, gbc);

        jlbTitle = new JLabel("Title");
        jlbTitle.setFont(new Font("Arial Unicode MS", 0, 14)); 
        setComponentSize(jlbTitle, 210, 25);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(8, 10, 0, 10);
        jpnlCenter.add(jlbTitle, gbc);

        jlbAuthor = new JLabel("Author");
        jlbAuthor.setFont(new Font("Arial Unicode MS", 0, 13)); 
        setComponentSize(jlbAuthor, 210, 20);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(8, 10, 0, 10);
        jpnlCenter.add(jlbAuthor, gbc);

        jlbPub = new JLabel("Publisher");
        jlbPub.setFont(new Font("Arial Unicode MS", 0, 12)); 
        setComponentSize(jlbPub, 210, 17);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(8, 10, 0, 10);
        jpnlCenter.add(jlbPub, gbc);

        jlbYear = new JLabel("Year");
        jlbYear.setFont(new Font("Arial Unicode MS", 0, 12)); 
        setComponentSize(jlbYear, 40, 17);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(8, 10, 8, 0);
        jpnlCenter.add(jlbYear, gbc);

        jlbGenr = new JLabel("Genre");
        jlbGenr.setFont(new Font("Arial Unicode MS", 0, 12)); 
        setComponentSize(jlbGenr, 158, 17);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(8, 10, 8, 10);
        jpnlCenter.add(jlbGenr, gbc);

        add(jpnlCenter, BorderLayout.CENTER);
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
    private void displayBook (Book book) {
        jtaDesc.setText(book.Desc);
        jlbTitle.setText(book.Title);
        jlbAuthor.setText(book.Author);
        jlbPub.setText(book.Publisher);
        jlbYear.setText(book.Year);
        jlbGenr.setText(book.Genre);

        jlbPic.setText("");
        jlbPic.setIcon(new ImageIcon(getCover(book)));
    }

    private Image getCover(Book book){
        BufferedImage img = null;
        Image imgCover = null;
        try {
            img = ImageIO.read(new File(book.cover));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            imgCover = img.getScaledInstance(
                            jlbPic.getWidth(), 
                            jlbPic.getHeight(), 
                            Image.SCALE_SMOOTH);
        }
        return imgCover;
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
// ..............................

/*
 * Action Listeners for the combobox components
 * This also demonstrate another way of using Listeners
 */
    ActionListener cboAction = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            switch (jcboISBN.getSelectedIndex())
            {
                case 0 : clearAll();; break;
                case 1 : displayBook(book01); break;
                case 2 : displayBook(book02); break;
                case 3 : displayBook(book03); break;
                default: break;
            }
        }
    };
}

/*
 * Book class for structuring the mock data
 */
class Book {
    String ISBN, Title, Author, Publisher, Year, Genre, Desc, cover;

    public Book (String isbn,
                 String title,
                 String author,
                 String pub,
                 String year, 
                 String genre, 
                 String desc, 
                 String cover){
        this.ISBN = isbn;
        this.Year = year;
        this.Title = title;
        this.Author = author;
        this.Publisher = pub;
        this.Genre = genre;
        this.Desc = desc;
        this.cover = cover;
    }
}