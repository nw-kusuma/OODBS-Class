package models;

/**
 * @author N. W. Kusuma
 */
public class Book {
    String ISBN, Title, Author, Publisher, Year, Genre, Description, cover;

    public Book (){}
    
    public Book (String isbn,
                 String title,
                 String author,
                 String pub,
                 String year, 
                 String genre, 
                 String desc, 
                 String cover)
    {
        this.ISBN = isbn;
        this.Title = title;
        this.Author = author;
        this.Publisher = pub;
        this.Year = year;
        this.Genre = genre;
        this.Description = desc;
        this.cover = cover;
    }
    
    public String getISBN()         { return this.ISBN; }
    public String getTitle()        { return this.Title; }
    public String getAuthor()       { return this.Author; }
    public String getPublisher()    { return this.Publisher; }
    public String getYear()         { return this.Year; }
    public String getGenre()        { return this.Genre; }
    public String getDescription()  { return this.Description; }
    public String getCover()        { return this.cover; }        

    public void setISBN(String isbn)        { this.ISBN = isbn; }
    public void setTitle(String title)      { this.Title = title; }
    public void setAuthor(String author)    { this.Author = author; }
    public void setPublisher(String pub)    { this.Publisher = pub; }
    public void setYear(String year)        { this.Year = year; }
    public void setGenre(String genre)      { this.Genre = genre; }
    public void setDescription(String desc) { this.Description = desc; }
    public void setCover(String cover)      { this.cover = cover; }        
}