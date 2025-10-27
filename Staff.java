/**
 * Staff class extending Person
 * Demonstrates: Inheritance, Method Overriding, Polymorphism
 * 
 * Staff members can borrow maximum 5 books from the library
 */
public class Staff extends Person {
    // Staff-specific fields
    private String staffId;
    private String department;
    private String position;
    private double salary;
    private int currentBorrowedBooks;
    private static final int MAX_BOOKS = 5; // Staff can borrow max 5 books
    
    // Constructor - calls parent constructor
    public Staff(String id, String name, String email) {
        super(id, name, email); // Call Person constructor
        this.staffId = id;
        this.currentBorrowedBooks = 0;
    }
    
    // Overloaded constructor with additional staff info
    public Staff(String id, String name, String email, String department, String position) {
        super(id, name, email);
        this.staffId = id;
        this.department = department;
        this.position = position;
        this.currentBorrowedBooks = 0;
    }
    
    // Full constructor
    public Staff(String id, String name, String email, String department, String position, double salary) {
        super(id, name, email);
        this.staffId = id;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.currentBorrowedBooks = 0;
    }
    
    // Getter methods for staff-specific fields
    public String getStaffId() {
        return staffId;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public String getPosition() {
        return position;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public int getCurrentBorrowedBooks() {
        return currentBorrowedBooks;
    }
    
    // Setter methods with validation
    public void setDepartment(String department) {
        if (department != null && !department.trim().isEmpty()) {
            this.department = department.trim();
        } else {
            System.out.println("Error: Department name cannot be empty!");
        }
    }
    
    public void setPosition(String position) {
        if (position != null && !position.trim().isEmpty()) {
            this.position = position.trim();
        } else {
            System.out.println("Error: Position cannot be empty!");
        }
    }
    
    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Error: Salary cannot be negative!");
        }
    }
    
    // Override abstract methods from Person class
    @Override
    public void displayInfo() {
        System.out.println("=== Staff Information ===");
        System.out.println("Staff ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + (getPhoneNumber() != null ? getPhoneNumber() : "Not provided"));
        System.out.println("Department: " + (department != null ? department : "Not specified"));
        System.out.println("Position: " + (position != null ? position : "Not specified"));
        if (salary > 0) {
            System.out.println("Salary: $" + String.format("%.2f", salary));
        }
        System.out.println("Books Currently Borrowed: " + currentBorrowedBooks + "/" + MAX_BOOKS);
        System.out.println("=========================");
    }
    
    @Override
    public int getMaxBooks() {
        return MAX_BOOKS;
    }
    
    @Override
    public String getPersonType() {
        return "Staff";
    }
    
    // Staff-specific methods
    public boolean canBorrowMoreBooks() {
        return currentBorrowedBooks < MAX_BOOKS;
    }
    
    public boolean borrowBook() {
        if (canBorrowMoreBooks()) {
            currentBorrowedBooks++;
            System.out.println("Book borrowed successfully! " + 
                             "Current borrowed books: " + currentBorrowedBooks + "/" + MAX_BOOKS);
            return true;
        } else {
            System.out.println("Cannot borrow more books! Maximum limit (" + MAX_BOOKS + ") reached.");
            return false;
        }
    }
    
    public boolean returnBook() {
        if (currentBorrowedBooks > 0) {
            currentBorrowedBooks--;
            System.out.println("Book returned successfully! " + 
                             "Current borrowed books: " + currentBorrowedBooks + "/" + MAX_BOOKS);
            return true;
        } else {
            System.out.println("No books to return!");
            return false;
        }
    }
    
    // Override updateContactInfo to add staff-specific behavior
    @Override
    public void updateContactInfo(String email, String phone) {
        super.updateContactInfo(email, phone); // Call parent method
        System.out.println("Staff contact information updated successfully!");
        
        // Staff might need to update HR records too
        if (department != null) {
            System.out.println("Please also update your contact information with HR department: " + department);
        }
    }
    
    // Staff-specific method - calculate annual salary
    public double getAnnualSalary() {
        return salary * 12;
    }
    
    // Method to check if staff member is senior (example business logic)
    public boolean isSeniorStaff() {
        return position != null && 
               (position.toLowerCase().contains("senior") || 
                position.toLowerCase().contains("manager") || 
                position.toLowerCase().contains("director"));
    }
    
    // Staff get priority borrowing privileges
    public String getBorrowingPriority() {
        if (isSeniorStaff()) {
            return "High Priority";
        } else {
            return "Standard Priority";
        }
    }
    
    // Override toString for better display
    @Override
    public String toString() {
        return "Staff{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", department='" + (department != null ? department : "N/A") + '\'' +
                ", position='" + (position != null ? position : "N/A") + '\'' +
                ", borrowedBooks=" + currentBorrowedBooks + "/" + MAX_BOOKS +
                ", priority='" + getBorrowingPriority() + '\'' +
                '}';
    }
}