package ps.anu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Library implements Stock {

    public Book insert(Book book) {
        PreparedStatement pst = MySQLConnection.getPreparedStatement("insert into books value(?, ?, ?, ?, ?, ?)");
        try {
            pst.setString(1, book.getIsbn());
            pst.setString(2, book.getTitle());
            pst.setString(3, book.getAuthor());
            pst.setInt(4, book.getGenre());
            pst.setFloat(5, book.getPrice());
            pst.setString(6, book.getDate());
        } catch (SQLException s) {
            System.out.println("Failure in entering the fields, Check your input.");
            System.out.println("You entered" + book);
            s.printStackTrace();
        }

        try {
            int ret = pst.executeUpdate();
//            if (ret == 0) throw
        } catch (SQLException s) {

            s.printStackTrace();
        }
        return book;
    }

    public void update(Book book) {
        String query = "UPDATE books " +
                "SET ";

        if (!book.getTitle().isEmpty()) query += "title = ?,";
        if (!book.getAuthor().isEmpty()) query += "author = ?,";
        if (book.getPrice() != -1) query += "price = ?,";
        if (book.getGenre() != -1) query += "genre = ?,";
        if (book.getDate() != null) query += "date = ?,";
        query = query.replaceAll(",$", "");
        query += " WHERE isbn = ?";

        PreparedStatement pst = MySQLConnection.getPreparedStatement(query);

        int rs = 0;

        int pIndex = 1;

        try {
            if (!book.getTitle().isEmpty()) pst.setString(pIndex++, book.getTitle());
            if (!book.getAuthor().isEmpty()) pst.setString(pIndex++, book.getAuthor());
            if (book.getPrice() != -1) pst.setFloat(pIndex++, book.getPrice());
            if (book.getGenre() != -1) pst.setInt(pIndex++, book.getGenre());
            if (book.getDate() != null) pst.setString(pIndex++, book.getDate());
            pst.setString(pIndex, book.getIsbn());
            rs = pst.executeUpdate();
        } catch (SQLException s) {
            s.printStackTrace();
        }

        if (rs > 0) {
            System.out.println("The entry was updated in the database");
        }
    }

    public void delete(Book book) {
        String query = "DELETE from books WHERE isbn = ?";
        PreparedStatement pst = MySQLConnection.getPreparedStatement(query);
        try {
            pst.setString(1, book.getIsbn());
            pst.executeUpdate();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public Book findBookByISBN(String isbn) {
        String query = " SELECT books.*, genre.genreName " +
                " FROM books " +
                "LEFT JOIN genre ON books.genre = genre.genreId " +
                "WHERE books.isbn = ?";

        PreparedStatement pst = MySQLConnection.getPreparedStatement(query);

        ResultSet rs = null;

        try {
            pst.setString(1, isbn);
            rs = pst.executeQuery();
        }  catch (SQLException s) {
            s.printStackTrace();
        }

        Book book = null; String genreName = null;

        try {
            if (rs.next()) {
                book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getString(6));
                genreName = rs.getString(7);
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }

        if (book != null)
            book.printBook(genreName);
        else System.out.println("No book found with ISBN: " + isbn);

        return book;
    }

    public Book findBookByAuthor(String author) {

        String temp = "SELECT books.*, genre.genreName " +
                "FROM books " +
                "LEFT JOIN genre ON books.genre = genre.genreId " +
                "WHERE books.author = ? LIMIT 1";

        PreparedStatement pst = MySQLConnection.getPreparedStatement(temp);

        ResultSet rs = null;

        try {
            pst.setString(1, author);
            rs = pst.executeQuery();

        } catch (SQLException s) {
            System.out.println("Failure in entering the fields, Check your input.");
            System.out.println("You entered" + author);
            s.printStackTrace();
        }

        Book book = null; String genreName = null;
        try {
            while(rs.next()) {
                book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getString(6));
                genreName = rs.getString(7);
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }

        book.printBook(genreName);
        return book;
    }

    public ArrayList<Book> findBooksByAuthor(String author) {
        String temp = "SELECT books.*, genre.genreName " +
                "FROM books " +
                "LEFT JOIN genre ON books.genre = genre.genreId " +
                "WHERE books.author = ?";

        PreparedStatement pst = MySQLConnection.getPreparedStatement(temp);

        ResultSet rs = null;

        try {
            pst.setString(1,  author);
            rs = pst.executeQuery();

        } catch (SQLException s) {
            System.out.println("Failure in entering the fields, Check your input.");
            System.out.println("You entered" + author);
            s.printStackTrace();
        }

        ArrayList<Book> books = new ArrayList<>(); String genreName = null;
        Book book = null;
        try {
            while(rs.next()) {
                book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getString(6));
                genreName = rs.getString(7);
                book.printBook(genreName);
                System.out.println(" -- -- -- -- -- -- -- -- -- ");
                books.add(book);
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }

        if (books.size() == 0) System.out.println("No books in DB for author: " + author);

        return books;
    }

    public ArrayList<Book> findBooksByGenre(int genre) {
        String temp = "SELECT books.*, genre.genreName " +
                "FROM books " +
                "LEFT JOIN genre ON books.genre = genre.genreId " +
                "WHERE books.genre = ?";

        PreparedStatement pst = MySQLConnection.getPreparedStatement(temp);

        ResultSet rs = null;

        try {
            pst.setInt(1, genre);
            rs = pst.executeQuery();

        } catch (SQLException s) {
            System.out.println("Failure in entering the fields, Check your input.");
            s.printStackTrace();
        }

        ArrayList<Book> books = new ArrayList<>(); String genreName = null;
        Book book = null;
        try {
            while(rs.next()) {
                book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getString(6));
                genreName = rs.getString(7);
                book.printBook(genreName);
                System.out.println(" -- -- -- -- -- -- -- -- -- ");
                books.add(book);
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }

        if (books.size() == 0) System.out.println("No books in DB for said genre.");

        return books;
    }

    public Book findBookByTitle(String title) {
        String temp = "SELECT books.*, genre.genreName " +
                "FROM books " +
                "LEFT JOIN genre ON books.genre = genre.genreId " +
                "WHERE books.title = ? LIMIT 1";

        PreparedStatement pst = MySQLConnection.getPreparedStatement(temp);

        ResultSet rs = null;

        try {
            pst.setString(1, title);
            rs = pst.executeQuery();

        } catch (SQLException s) {
            System.out.println("Failure in entering the fields, Check your input.");
            System.out.println("You entered" + title);
            s.printStackTrace();
        }

        Book book = null; String genreName = null;
        try {
            while(rs.next()) {
                book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getString(6));
                genreName = rs.getString(7);
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }
        if (book != null)
            book.printBook(genreName);
        else System.out.println("No book for title: " + title);

        return book;
    }

    public ArrayList<Book> findBooksByPrice(Range range) {
        String temp = "SELECT books.*, genre.genreName " +
                "FROM books " +
                "LEFT JOIN genre ON books.genre = genre.genreId " +
                "WHERE books.price >= ? AND books.price <= ?";


        PreparedStatement pst = MySQLConnection.getPreparedStatement(temp);

        ResultSet rs = null;

        try {
            pst.setFloat(1, range.getLow());
            pst.setFloat(2, range.getHigh());
            rs = pst.executeQuery();

        } catch (SQLException s) {
            System.out.println("Failure in entering the fields, Check your input.");
            s.printStackTrace();
        }

        ArrayList<Book> books = new ArrayList<>(); String genreName = null;
        Book book = null;
        try {
            while(rs.next()) {
                book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getString(6));
                genreName = rs.getString(7);
                book.printBook(genreName);
                System.out.println(" -- -- -- -- -- -- -- -- -- ");
                books.add(book);
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }

        if (books.size() == 0) System.out.println("No books in DB with price range: " + range.getLow() + ", " + range.getHigh());

        return books;
    }

    public void leastAndMostExpensiveBooks() {
        String query = "SELECT books.*, genre.genreName\n" +
                "FROM books " +
                "LEFT JOIN genre ON books.genre = genre.genreId " +
                "WHERE price = (SELECT MAX(price) FROM books)\n" +
                "UNION\n" +
                "SELECT books.*, genre.genreName\n" +
                "FROM books " +
                "LEFT JOIN genre ON books.genre = genre.genreId " +
                "WHERE price = (SELECT MIN(price) FROM books);\n";

        Statement st = MySQLConnection.getStatement();
        ResultSet rs = null;
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<String> genreNames = new ArrayList<>();

        try {
            rs = st.executeQuery(query);

            while (rs.next()) {
                books.add(new Book(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getFloat(5),
                        rs.getString(6)
                        ));
                genreNames.add(rs.getString(7));
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }

        System.out.println("The cheapest book: ");
        books.get(1).printBook(genreNames.get(1));
        System.out.println(" -- x -- x -- x -- x -- x -- ");
        System.out.println("The most expensive book: ");
        books.get(0).printBook(genreNames.get(0));
    }

    public void deleteOldBooks() {
        Statement st = MySQLConnection.getStatement();
        int cnt = 0;
        try {
            cnt = st.executeUpdate("DELETE from books " +
                    "WHERE (CURDATE() - date) >= 730");
        } catch (SQLException s) {
            s.printStackTrace();
        }
        if (cnt > 0) System.out.println("The old entries are deleted.");
    }
}
