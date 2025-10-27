/**
 * Custom exception for when a member tries to borrow more books than their limit
 * Demonstrates: Custom Exception with business logic validation
 */
public class MaxBooksExceededException extends Exception {
    
    private String memberId;
    private String memberName;
    private int currentBooks;
    private int maxAllowed;
    private String memberType;
    
    // Constructor with member details
    public MaxBooksExceededException(String memberId, String memberName, 
                                   int currentBooks, int maxAllowed, String memberType) {
        super(String.format("Member '%s' (%s) has reached the maximum borrowing limit. " +
                          "Current: %d/%d books (Type: %s)", 
                          memberName, memberId, currentBooks, maxAllowed, memberType));
        this.memberId = memberId;
        this.memberName = memberName;
        this.currentBooks = currentBooks;
        this.maxAllowed = maxAllowed;
        this.memberType = memberType;
    }
    
    // Constructor with custom message
    public MaxBooksExceededException(String message) {
        super(message);
    }
    
    // Constructor with message and cause
    public MaxBooksExceededException(String message, Throwable cause) {
        super(message, cause);
    }
    
    // Getters for additional information
    public String getMemberId() {
        return memberId;
    }
    
    public String getMemberName() {
        return memberName;
    }
    
    public int getCurrentBooks() {
        return currentBooks;
    }
    
    public int getMaxAllowed() {
        return maxAllowed;
    }
    
    public String getMemberType() {
        return memberType;
    }
    
    // Method to get suggestions for the user
    public String getSuggestion() {
        return String.format("Suggestion: %s can return some of their current %d books to borrow new ones. " +
                           "%s members can borrow up to %d books simultaneously.", 
                           memberName, currentBooks, memberType, maxAllowed);
    }
    
    // Method to check how many books need to be returned
    public int getBooksToReturn() {
        return Math.max(0, currentBooks - maxAllowed + 1);
    }
}