/**
 * Book class representing individual books in the library
 * Demonstrates: Encapsulation, Data validation, Object state management
 * 
 * Each book has a title, author, ISBN, and availability status
 */
public class Book {
    // Private fields - Encapsulation principle
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private String borrowedBy; // Track who borrowed this book
    
    // Constructor - Create a new book (initially available)
    public Book(String title, String author, String isbn) {
        setTitle(title);    // Use setter for validation
        setAuthor(author);  // Use setter for validation
        setIsbn(isbn);      // Use setter for validation
        this.isAvailable = true;  // New books are always available
        this.borrowedBy = null;   // Not borrowed initially
    }
    
    // Overloaded constructor for testing purposes
    public Book(String title, String author) {
        this(title, author, "Unknown ISBN");
    }
    
    // Getter methods - Controlled access to private data
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public String getBorrowedBy() {
        return borrowedBy;
    }
    
    // Setter methods with validation - Data protection
    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title.trim();
        } else {
            System.out.println("Error: Book title cannot be empty!");
        }
    }
    
    public void setAuthor(String author) {
        if (author != null && !author.trim().isEmpty()) {
            this.author = author.trim();
        } else {
            System.out.println("Error: Author name cannot be empty!");
        }
    }
    
    public void setIsbn(String isbn) {
        if (isbn != null && !isbn.trim().isEmpty()) {
            this.isbn = isbn.trim();
        } else {
            System.out.println("Error: ISBN cannot be empty!");
        }
    }
    
    // Business logic methods for book operations
    public boolean borrowBook(String memberId) {
        if (isAvailable) {
            this.isAvailable = false;
            this.borrowedBy = memberId;
            System.out.println("Book '" + title + "' borrowed by member: " + memberId);
            return true;
        } else {
            System.out.println("Book '" + title + "' is already borrowed by: " + borrowedBy);
            return false;
        }
    }
    
    public boolean returnBook() {
        if (!isAvailable) {
            String previousBorrower = this.borrowedBy;
            this.isAvailable = true;
            this.borrowedBy = null;
            System.out.println("Book '" + title + "' returned by member: " + previousBorrower);
            return true;
        } else {
            System.out.println("Book '" + title + "' is already available!");
            return false;
        }
    }
    
    // Display book information
    public void displayBookInfo() {
        System.out.println("=== Book Information ===");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Status: " + (isAvailable ? "Available" : "Borrowed by " + borrowedBy));
        System.out.println("========================");
    }
    
    // Override toString for easy printing and debugging
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", available=" + isAvailable +
                (borrowedBy != null ? ", borrowedBy='" + borrowedBy + '\'' : "") +
                '}';
    }
    
    // Override equals for comparing books (useful for searching)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Book book = (Book) obj;
        return isbn.equals(book.isbn); // Books are equal if ISBN matches
    }
    
    // Override hashCode (good practice when overriding equals)
    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}