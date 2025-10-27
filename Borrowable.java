/**
 * Borrowable interface for Library Management System
 * Demonstrates: Interface implementation, Contract-based programming
 * 
 * This interface defines the contract for any class that handles
 * book borrowing and returning operations
 */
public interface Borrowable {
    
    /**
     * Borrow a book from the library
     * @param memberId The ID of the member borrowing the book
     * @param bookTitle The title of the book to borrow
     * @return true if book was successfully borrowed, false otherwise
     */
    boolean borrowBook(String memberId, String bookTitle);
    
    /**
     * Return a book to the library
     * @param memberId The ID of the member returning the book
     * @param bookTitle The title of the book to return
     * @return true if book was successfully returned, false otherwise
     */
    boolean returnBook(String memberId, String bookTitle);
    
    /**
     * Check if a member can borrow more books
     * @param memberId The ID of the member to check
     * @return true if member can borrow more books, false otherwise
     */
    boolean canMemberBorrowMore(String memberId);
    
    /**
     * Get the number of books currently borrowed by a member
     * @param memberId The ID of the member
     * @return number of books currently borrowed
     */
    int getBorrowedBooksCount(String memberId);
    
    /**
     * Check if a specific book is available for borrowing
     * @param bookTitle The title of the book to check
     * @return true if book is available, false otherwise
     */
    boolean isBookAvailable(String bookTitle);
}