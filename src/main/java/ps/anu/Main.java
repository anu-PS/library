package ps.anu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    InputStreamReader isr;
    BufferedReader br;


    public Main() {
        if (isr == null)
            isr = new InputStreamReader(System.in);
        if (br == null)
            br = new BufferedReader(isr);
    }

    public static void main(String[] args) {
        Main obj = new Main();
        Stock lib = new Library();
        System.out.println("Welcome to Book Store");
        switch (obj.selectOperation()) {
            case 1: {
                Book b = obj.createBook();
                lib.insert(b);
                break;
            }
            case 2: {
                int t = obj.takeIntInput("Select a field from below\n1. ISBN\n2. Title\n3. Author\n4. Genre\n5. Price\n> ");
                switch (t) {
                    case 1: {
                        String isbn = obj.takeStringInput("Enter the ISBN: ");
                        lib.findBookByISBN(isbn);
                        break;
                    }
                    case 2: {
                        String title = obj.takeStringInput("Enter the title: ");
                        lib.findBookByTitle(title);
                        break;
                    }
                    case 3: {
                        String author = obj.takeStringInput("Enter the author's name: ");
                        lib.findBooksByAuthor(author);
                        break;
                    }
                    case 4: {
                        int genre = obj.takeGenreInput();
                        lib.findBooksByGenre(genre);
                        break;
                    }
                    case 5: {
                        Range range = obj.takeRangeInput();
                        lib.findBooksByPrice(range);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 3: {
                String isbn = obj.takeStringInput("Enter the ISBN for the book you wish to update: ");
                System.out.println("Enter the details to be updated, leave the fields blank if not to be updated");
                Book book = new Book(isbn, "", "", -1, -1, null);
                int t = 0;
                t = obj.takeIntInput("Do you wish to change title\n1. Yes\n2. No\n> ");
                if (t == 1) {
                    book.setTitle(obj.takeStringInput("Enter the title: "));
                }
                t = obj.takeIntInput("Do you wish to change author\n1. Yes\n2. No\n> ");
                if (t == 1) {
                    book.setAuthor(obj.takeStringInput("Enter the name of the author: "));
                }
                t = obj.takeIntInput("Do you wish to change price\n1. Yes\n2. No\n> ");
                if (t == 1) {
                    book.setPrice(obj.takeFloatInput("Enter the price of the book: "));
                }
                t = obj.takeIntInput("Do you wish to change genre\n1. Yes\n2. No\n> ");
                if (t == 1) {
                    book.setGenre(obj.takeGenreInput());
                }
                t = obj.takeIntInput("Do you wish to change date published\n1. Yes\n2. No\n> ");
                if (t == 1) {
                    book.setDate(obj.takeDateInput("Enter the date to be updated\n"));
                }
                lib.update(book);
                break;
            }
            case 4: {
                String isbn = obj.takeStringInput("Enter the isbn for the book you want to delete: ");
                lib.delete(new Book(isbn));
                break;
            }
            case 5: {
                lib.leastAndMostExpensiveBooks();
                break;
            }
            case 6: {
                lib.deleteOldBooks();
                break;
            }
            default: {
                System.exit(0);
            }
        }

    }





    public int selectOperation() {
        return takeIntInput("Select one of the following\n1. Add Book\n2. Search for a book\n3. Update book " +
                "details\n4. Delete a book\n5. Get least and most expensive books\n6. Delete old books\n> ");
    }

    public Book createBook() {
        String author, title, isbn;
        float price;
//        Genre genre;
        int genre;
        String date;

        title = takeStringInput("Enter the title of the book: ");
        author = takeStringInput("Enter the authors name: ");
        isbn = takeStringInput("Enter the isbn of book: ");
        price = takeFloatInput("Enter the price of the book: ");
        genre = takeGenreInput();
        date = takeDateInput("Enter the date of publishing\n");
        return new Book(isbn, title, author, genre, price, date);
    }

//    public Genre takeGenreInput() {
//        int t = takeIntInput("Select the genre of the book\n1. Fiction\n2. SciFiction\n3. Philosophy\n4. Mystery\n5. " +
//                "Thriller\n6. Romance\n7. Westerns\n8. Dystopian\n9. Others\n> ");
//
//    }

    public Range takeRangeInput() {
        System.out.println("Select the range for search.");
        float ll = takeFloatInput("Enter the lower value: ");
        float hh = takeFloatInput("Enter the higher value: ");
//        if (hh > ll) System.out.println("Invalid Input");
        return new Range(ll, hh);
    }

    public int takeGenreInput() {
        return takeIntInput("Select the genre of the book\n1. Fiction\n2. SciFiction\n3. Philosophy\n4. Mystery\n5. " +
                "Thriller\n6. Romance\n7. Westerns\n8. Dystopian\n9. Others\n> ");

    }

    public String takeStringInput(String msg) {
        System.out.print(msg);
        String ret = "";
        try {
            ret = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public int takeIntInput(String msg) {
        System.out.print(msg);
        int ret = 0;
        try {
            ret = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public float takeFloatInput(String msg) {
        float ret = 0.0f;
        System.out.print(msg);
        try {
            ret = Float.parseFloat(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public String takeDateInput(String msg) {
        System.out.print(msg);
        return takeStringInput("Enter the date in YYYY-MM-DD: ");
    }


}