package ps.anu;

import java.sql.Date;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private int genre;
    private float price;



    private String date;

    public Book(String isbn, String title, String author, int genre, float price, String date) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.price = price;
        this.date = date;
    }

    public Book(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        return String.format("Author: %s\nTitle: %s\nISBN: %s\nPrice: ₹%.2f\n", this.author, this.title, this.isbn, this.price);
    }

    public void printBook(String genreName) {
        System.out.println(this + "Genre: " + genreName);
    }






}
