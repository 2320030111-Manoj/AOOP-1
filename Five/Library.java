package Five;

import java.util.ArrayList;
import java.util.List;

// 1. SRP: The Book class only handles book details.
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        this.isBorrowed = true;
    }

    public void returnBook() {
        this.isBorrowed = false;
    }

    @Override
    public String toString() {
        return "Book[Title=" + title + ", Author=" + author + ", ISBN=" + isbn + "]";
    }
}

// 2. SRP: The Member class handles member-related details.
abstract class Member {
    protected String memberId;
    protected String name;
    protected List<Book> borrowedBooks;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public abstract int getMaxBooksAllowed();

    public boolean canBorrowMoreBooks() {
        return borrowedBooks.size() < getMaxBooksAllowed();
    }

    public void borrowBook(Book book) {
        if (canBorrowMoreBooks() && !book.isBorrowed()) {
            borrowedBooks.add(book);
            book.borrowBook();
            System.out.println(name + " borrowed: " + book.getTitle());
        } else {
            System.out.println("Cannot borrow more books or book already borrowed.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returnBook();
            System.out.println(name + " returned: " + book.getTitle());
        } else {
            System.out.println("Book not found in borrowed list.");
        }
    }
}

// 3. Inheritance and LSP: Subclasses for different types of members.
class RegularMember extends Member {
    private static final int MAX_BOOKS = 5;

    public RegularMember(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public int getMaxBooksAllowed() {
        return MAX_BOOKS;
    }
}

class PremiumMember extends Member {
    private static final int MAX_BOOKS = 10;

    public PremiumMember(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public int getMaxBooksAllowed() {
        return MAX_BOOKS;
    }
}

// 4. ISP: Separate borrowing functionality through the Borrowable interface.
interface Borrowable {
    void borrowBook(Member member, Book book);
    void returnBook(Member member, Book book);
}

// 5. DIP and OCP: LibraryManager depends on abstractions (Borrowable).
class LibraryManager implements Borrowable {
    private List<Book> books;
    private List<Member> members;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    // Managing books
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public void removeBook(Book book) {
        books.remove(book);
        System.out.println("Book removed: " + book);
    }

    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    // Managing members
    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member added: " + member.getName());
    }

    public void removeMember(Member member) {
        members.remove(member);
        System.out.println("Member removed: " + member.getName());
    }

    public Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    // Implementing Borrowable interface
    @Override
    public void borrowBook(Member member, Book book) {
        member.borrowBook(book);
    }

    @Override
    public void returnBook(Member member, Book book) {
        member.returnBook(book);
    }
}

// Main class to test the system.
public class Library {
    public static void main(String[] args) {
        // Create LibraryManager instance
        LibraryManager libraryManager = new LibraryManager();

        // Add books
        Book book1 = new Book("1984", "George Orwell", "12345");
        Book book2 = new Book("Brave New World", "Aldous Huxley", "67890");
        libraryManager.addBook(book1);
        libraryManager.addBook(book2);

        // Add members
        Member regularMember = new RegularMember("RM001", "John Doe");
        Member premiumMember = new PremiumMember("PM001", "Jane Smith");
        libraryManager.addMember(regularMember);
        libraryManager.addMember(premiumMember);

        // Borrow and return books
        libraryManager.borrowBook(regularMember, book1);
        libraryManager.borrowBook(premiumMember, book2);
        
        regularMember.borrowBook(book1); // Book already borrowed test
        
        // Return books
        libraryManager.returnBook(regularMember, book1);
        libraryManager.returnBook(premiumMember, book2);
    }
}

