/**
 * Custom exception for when a member is not found in the library system
 * Demonstrates: Custom Exception handling, Exception inheritance
 */
public class MemberNotFoundException extends Exception {
    
    // Constructor with message
    public MemberNotFoundException(String message) {
        super(message);
    }
    
    // Constructor with message and cause
    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    // Default constructor
    public MemberNotFoundException() {
        super("Member not found in the library system");
    }
}