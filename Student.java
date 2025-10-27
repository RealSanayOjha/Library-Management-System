/**
 * Student class extending Person
 * Demonstrates: Inheritance, Method Overriding, Polymorphism
 * 
 * Students can borrow maximum 2 books from the library
 */
public class Student extends Person {
    // Student-specific fields
    private String studentId;
    private String course;
    private int year;
    private int currentBorrowedBooks;
    private static final int MAX_BOOKS = 2; // Students can borrow max 2 books
    
    // Constructor - calls parent constructor
    public Student(String id, String name, String email) {
        super(id, name, email); // Call Person constructor
        this.studentId = id;
        this.currentBorrowedBooks = 0;
    }
    
    // Overloaded constructor with additional student info
    public Student(String id, String name, String email, String course, int year) {
        super(id, name, email);
        this.studentId = id;
        this.course = course;
        this.year = year;
        this.currentBorrowedBooks = 0;
    }
    
    // Getter methods for student-specific fields
    public String getStudentId() {
        return studentId;
    }
    
    public String getCourse() {
        return course;
    }
    
    public int getYear() {
        return year;
    }
    
    public int getCurrentBorrowedBooks() {
        return currentBorrowedBooks;
    }
    
    // Setter methods with validation
    public void setCourse(String course) {
        if (course != null && !course.trim().isEmpty()) {
            this.course = course.trim();
        } else {
            System.out.println("Error: Course name cannot be empty!");
        }
    }
    
    public void setYear(int year) {
        if (year >= 1 && year <= 4) {
            this.year = year;
        } else {
            System.out.println("Error: Year must be between 1 and 4!");
        }
    }
    
    // Override abstract methods from Person class
    @Override
    public void displayInfo() {
        System.out.println("=== Student Information ===");
        System.out.println("Student ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + (getPhoneNumber() != null ? getPhoneNumber() : "Not provided"));
        System.out.println("Course: " + (course != null ? course : "Not specified"));
        System.out.println("Year: " + (year > 0 ? year : "Not specified"));
        System.out.println("Books Currently Borrowed: " + currentBorrowedBooks + "/" + MAX_BOOKS);
        System.out.println("===========================");
    }
    
    @Override
    public int getMaxBooks() {
        return MAX_BOOKS;
    }
    
    @Override
    public String getPersonType() {
        return "Student";
    }
    
    // Student-specific methods
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
    
    // Override updateContactInfo to add student-specific behavior
    @Override
    public void updateContactInfo(String email, String phone) {
        super.updateContactInfo(email, phone); // Call parent method
        System.out.println("Student contact information updated successfully!");
    }
    
    // Method to check if student can graduate (example business logic)
    public boolean canGraduate() {
        return year >= 4 && currentBorrowedBooks == 0;
    }
    
    // Override toString for better display
    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", course='" + (course != null ? course : "N/A") + '\'' +
                ", year=" + year +
                ", borrowedBooks=" + currentBorrowedBooks + "/" + MAX_BOOKS +
                '}';
    }
}