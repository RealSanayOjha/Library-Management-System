/**
 * Custom exception for invalid input data validation
 * Demonstrates: Input validation with detailed error messages
 */
public class InvalidDataException extends Exception {
    
    private String fieldName;
    private String invalidValue;
    private String expectedFormat;
    
    // Constructor with field details
    public InvalidDataException(String fieldName, String invalidValue, String expectedFormat) {
        super(String.format("Invalid %s: '%s'. Expected format: %s", 
                          fieldName, invalidValue, expectedFormat));
        this.fieldName = fieldName;
        this.invalidValue = invalidValue;
        this.expectedFormat = expectedFormat;
    }
    
    // Constructor with custom message
    public InvalidDataException(String message) {
        super(message);
    }
    
    // Constructor with message and cause
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
    
    // Getters
    public String getFieldName() {
        return fieldName;
    }
    
    public String getInvalidValue() {
        return invalidValue;
    }
    
    public String getExpectedFormat() {
        return expectedFormat;
    }
    
    // Method to get validation help
    public String getValidationHelp() {
        return String.format("Please correct the %s field. " +
                           "You entered: '%s', but the system expects: %s", 
                           fieldName, invalidValue, expectedFormat);
    }
}