import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Transaction class to track borrowing and returning operations
 * Demonstrates: Date/Time handling, Object state tracking, toString override
 * 
 * This class maintains a complete history of library transactions
 */
public class Transaction implements Comparable<Transaction> {
    
    // Transaction types
    public enum TransactionType {
        BORROW, RETURN
    }
    
    // Private fields for encapsulation
    private String transactionId;
    private String memberId;
    private String memberName;
    private String bookTitle;
    private String bookIsbn;
    private TransactionType type;
    private LocalDateTime timestamp;
    private String notes;
    
    // Static counter for unique transaction IDs
    private static int transactionCounter = 1000;
    
    // Constructor for borrowing/returning
    public Transaction(String memberId, String memberName, String bookTitle, 
                      String bookIsbn, TransactionType type) {
        this.transactionId = "T" + (++transactionCounter);
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookTitle = bookTitle;
        this.bookIsbn = bookIsbn;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.notes = "";
    }
    
    // Constructor with notes
    public Transaction(String memberId, String memberName, String bookTitle, 
                      String bookIsbn, TransactionType type, String notes) {
        this(memberId, memberName, bookTitle, bookIsbn, type);
        this.notes = notes;
    }
    
    // Getter methods
    public String getTransactionId() {
        return transactionId;
    }
    
    public String getMemberId() {
        return memberId;
    }
    
    public String getMemberName() {
        return memberName;
    }
    
    public String getBookTitle() {
        return bookTitle;
    }
    
    public String getBookIsbn() {
        return bookIsbn;
    }
    
    public TransactionType getType() {
        return type;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getNotes() {
        return notes;
    }
    
    // Setter methods
    public void setNotes(String notes) {
        this.notes = notes != null ? notes : "";
    }
    
    // Business methods
    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }
    
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return timestamp.format(formatter);
    }
    
    public boolean isFromToday() {
        return timestamp.toLocalDate().equals(LocalDateTime.now().toLocalDate());
    }
    
    public boolean isBorrowTransaction() {
        return type == TransactionType.BORROW;
    }
    
    public boolean isReturnTransaction() {
        return type == TransactionType.RETURN;
    }
    
    // Display transaction information
    public void displayTransaction() {
        System.out.printf("[%s] %s: %s %s '%s' (%s)%n", 
            transactionId, 
            getFormattedTimestamp(),
            memberName + " (" + memberId + ")",
            type.toString().toLowerCase() + "ed",
            bookTitle,
            bookIsbn);
        if (notes != null && !notes.isEmpty()) {
            System.out.println("   Notes: " + notes);
        }
    }
    
    // Convert to file format for saving
    public String toFileFormat() {
        return String.join(",",
            transactionId,
            memberId,
            memberName,
            bookTitle,
            bookIsbn,
            type.toString(),
            timestamp.toString(),
            notes.replace(",", "&#44;") // Escape commas in notes
        );
    }
    
    // Create transaction from file format
    public static Transaction fromFileFormat(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 7) {
            Transaction transaction = new Transaction(
                parts[1], // memberId
                parts[2], // memberName
                parts[3], // bookTitle
                parts[4], // bookIsbn
                TransactionType.valueOf(parts[5]) // type
            );
            
            transaction.transactionId = parts[0];
            transaction.timestamp = LocalDateTime.parse(parts[6]);
            if (parts.length > 7) {
                transaction.notes = parts[7].replace("&#44;", ","); // Unescape commas
            }
            
            return transaction;
        }
        return null;
    }
    
    // Implement Comparable for sorting (newest first)
    @Override
    public int compareTo(Transaction other) {
        return other.timestamp.compareTo(this.timestamp); // Reverse chronological order
    }
    
    // Override equals for comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Transaction that = (Transaction) obj;
        return transactionId.equals(that.transactionId);
    }
    
    // Override hashCode
    @Override
    public int hashCode() {
        return transactionId.hashCode();
    }
    
    // Override toString for easy display
    @Override
    public String toString() {
        return String.format("Transaction{id='%s', member='%s', book='%s', type=%s, time='%s'}", 
            transactionId, memberName, bookTitle, type, getFormattedTimestamp());
    }
}