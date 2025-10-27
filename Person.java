/**
 * Abstract base class for all persons in the Library Management System
 * Demonstrates: Abstraction, Encapsulation, and inheritance foundation
 * 
 * This class cannot be instantiated directly - it serves as a template
 * for Student and Staff classes
 */
public abstract class Person {
    // Private fields - Encapsulation principle
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    
    // Constructor - Initialize person with required fields
    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    // Getter methods - Controlled access to private fields
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    // Setter methods with validation - Data protection
    public void setId(String id) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id;
        } else {
            System.out.println("Error: ID cannot be empty!");
        }
    }
    
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: Name cannot be empty!");
        }
    }
    
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Error: Invalid email format!");
        }
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    // Abstract methods - Must be implemented by child classes
    public abstract void displayInfo();
    public abstract int getMaxBooks();
    public abstract String getPersonType();
    
    // Common method that can be overridden (Polymorphism preparation)
    public void updateContactInfo(String email, String phone) {
        setEmail(email);
        setPhoneNumber(phone);
        System.out.println("Contact information updated for: " + name);
    }
    
    // Override toString for debugging and display purposes
    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type='" + getPersonType() + '\'' +
                '}';
    }
}