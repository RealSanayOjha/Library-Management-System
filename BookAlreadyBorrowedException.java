/**
 * Custom exception for when someone tries to borrow a book that's already borrowed
 * Demonstrates: Custom Exception hierarchy, Specific error scenarios
 */
public class BookAlreadyBorrowedException extends Exception {
    
    private String bookTitle;
    private String currentBorrower;
    
    // Constructor with book details
    public BookAlreadyBorrowedException(String bookTitle, String currentBorrower) {
        super("Book '" + bookTitle + "' is already borrowed by: " + currentBorrower);
        this.bookTitle = bookTitle;
        this.currentBorrower = currentBorrower;
    }
    
    // Constructor with custom message
    public BookAlreadyBorrowedException(String message) {
        super(message);
    }
    
    // Constructor with message and cause
    public BookAlreadyBorrowedException(String message, Throwable cause) {
        super(message, cause);
    }
    
    // Getters for additional information
    public String getBookTitle() {
        return bookTitle;
    }
    
    public String getCurrentBorrower() {
        return currentBorrower;
    }
    
    // Method to get detailed error information
    public String getDetailedMessage() {
        return String.format("Borrowing Error: Book '%s' is currently borrowed by member '%s'. " +
                           "Please wait for the book to be returned or choose another book.", 
                           bookTitle, currentBorrower);
    }
}