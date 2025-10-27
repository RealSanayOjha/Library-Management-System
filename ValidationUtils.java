/**
 * Utility class for input validation with custom exceptions
 * Demonstrates: Static utility methods, Input validation, Exception throwing
 */
public class ValidationUtils {
    
    // Private constructor to prevent instantiation (utility class)
    private ValidationUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    /**
     * Validate email format
     */
    public static void validateEmail(String email) throws InvalidDataException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidDataException("email", email, "non-empty email address");
        }
        
        if (!email.contains("@") || !email.contains(".")) {
            throw new InvalidDataException("email", email, "valid email format (e.g., user@domain.com)");
        }
        
        // Check for basic email pattern
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(emailPattern)) {
            throw new InvalidDataException("email", email, "valid email format (e.g., user@domain.com)");
        }
    }
    
    /**
     * Validate ISBN format (basic validation)
     */
    public static void validateISBN(String isbn) throws InvalidDataException {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new InvalidDataException("ISBN", isbn, "non-empty ISBN");
        }
        
        // Remove hyphens for validation
        String cleanISBN = isbn.replace("-", "");
        
        // Check for ISBN-10 or ISBN-13 format
        if (cleanISBN.length() != 10 && cleanISBN.length() != 13) {
            throw new InvalidDataException("ISBN", isbn, "10 or 13 digit ISBN (e.g., 978-1234567890)");
        }
        
        // Check if all characters except the last one are digits
        for (int i = 0; i < cleanISBN.length() - 1; i++) {
            if (!Character.isDigit(cleanISBN.charAt(i))) {
                throw new InvalidDataException("ISBN", isbn, "numeric ISBN format");
            }
        }
        
        // Last character can be digit or X (for ISBN-10)
        char lastChar = cleanISBN.charAt(cleanISBN.length() - 1);
        if (!Character.isDigit(lastChar) && lastChar != 'X' && lastChar != 'x') {
            throw new InvalidDataException("ISBN", isbn, "valid ISBN ending (digit or X)");
        }
    }
    
    /**
     * Validate name (no numbers, not empty)
     */
    public static void validateName(String name, String fieldName) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException(fieldName, name, "non-empty name");
        }
        
        if (name.length() < 2) {
            throw new InvalidDataException(fieldName, name, "name with at least 2 characters");
        }
        
        if (name.length() > 100) {
            throw new InvalidDataException(fieldName, name, "name with maximum 100 characters");
        }
        
        // Check for numbers in name
        if (name.matches(".*\\d.*")) {
            throw new InvalidDataException(fieldName, name, "name without numbers");
        }
    }
    
    /**
     * Validate member ID format
     */
    public static void validateMemberID(String memberId) throws InvalidDataException {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new InvalidDataException("member ID", memberId, "non-empty ID");
        }
        
        // Check format: S001 for students, T001 for staff
        if (!memberId.matches("^[ST]\\d{3}$")) {
            throw new InvalidDataException("member ID", memberId, 
                "format S### for students or T### for staff (e.g., S001, T001)");
        }
    }
    
    /**
     * Validate year (for students)
     */
    public static void validateYear(int year) throws InvalidDataException {
        if (year < 1 || year > 4) {
            throw new InvalidDataException("year", String.valueOf(year), "year between 1 and 4");
        }
    }
    
    /**
     * Validate phone number
     */
    public static void validatePhoneNumber(String phone) throws InvalidDataException {
        if (phone == null || phone.trim().isEmpty()) {
            return; // Phone is optional
        }
        
        // Remove common separators
        String cleanPhone = phone.replaceAll("[\\s\\-\\(\\)\\+]", "");
        
        if (cleanPhone.length() < 10 || cleanPhone.length() > 15) {
            throw new InvalidDataException("phone number", phone, 
                "10-15 digits (e.g., +1-234-567-8900)");
        }
        
        // Check if all characters are digits
        if (!cleanPhone.matches("\\d+")) {
            throw new InvalidDataException("phone number", phone, "numeric phone number");
        }
    }
    
    /**
     * Validate book title
     */
    public static void validateBookTitle(String title) throws InvalidDataException {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidDataException("book title", title, "non-empty title");
        }
        
        if (title.length() < 1) {
            throw new InvalidDataException("book title", title, "title with at least 1 character");
        }
        
        if (title.length() > 200) {
            throw new InvalidDataException("book title", title, "title with maximum 200 characters");
        }
    }
    
    /**
     * Validate that a string is not null or empty
     */
    public static void validateNotEmpty(String value, String fieldName) throws InvalidDataException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidDataException(fieldName, value, "non-empty " + fieldName);
        }
    }
    
    /**
     * Validate all book data at once
     */
    public static void validateBookData(String title, String author, String isbn) 
            throws InvalidDataException {
        validateBookTitle(title);
        validateName(author, "author name");
        validateISBN(isbn);
    }
    
    /**
     * Validate all student data at once
     */
    public static void validateStudentData(String id, String name, String email, 
                                         String course, int year, String phone) 
            throws InvalidDataException {
        validateMemberID(id);
        validateName(name, "student name");
        validateEmail(email);
        if (course != null && !course.trim().isEmpty()) {
            validateName(course, "course name");
        }
        validateYear(year);
        validatePhoneNumber(phone);
    }
    
    /**
     * Validate all staff data at once
     */
    public static void validateStaffData(String id, String name, String email, 
                                       String department, String position, String phone) 
            throws InvalidDataException {
        validateMemberID(id);
        validateName(name, "staff name");
        validateEmail(email);
        if (department != null && !department.trim().isEmpty()) {
            validateName(department, "department name");
        }
        if (position != null && !position.trim().isEmpty()) {
            validateName(position, "position");
        }
        validatePhoneNumber(phone);
    }
}