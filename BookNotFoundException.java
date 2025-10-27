/**
 * Custom exception for when a book is not found in the library
 * Demonstrates: Custom Exception handling, Exception inheritance
 */
public class BookNotFoundException extends Exception {
    
    // Constructor with message
    public BookNotFoundException(String message) {
        super(message);
    }
    
    // Constructor with message and cause
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    // Default constructor
    public BookNotFoundException() {
        super("Book not found in the library");
    }
}