package ps.anu;

import java.util.ArrayList;

public interface Stock {
    Book insert(Book book);
    void update(Book book);
    void delete(Book book);
    Book findBookByISBN(String isbn);
    Book findBookByAuthor(String author);
    ArrayList<Book> findBooksByAuthor(String author);
    ArrayList<Book> findBooksByGenre(int genre);
    Book findBookByTitle(String title);
    ArrayList<Book> findBooksByPrice(Range range);
    public void leastAndMostExpensiveBooks();
    public void deleteOldBooks();
}
