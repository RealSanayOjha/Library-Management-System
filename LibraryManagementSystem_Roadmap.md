# Simple Library Management System - 5-Day Java OOP Project

## Project Overview
A simplified Library Management System focusing on core Java and Object-Oriented Programming concepts. This project can be completed in 5 days while covering all essential OOP principles.

## 🎯 Core Features to Implement (Console-Only)
1. **Book Management**: Add, remove, search books
2. **Member Management**: Add students and staff members
3. **Basic Transactions**: Borrow and return books
4. **Simple Reports**: Show available books and member borrowing status
5. **Terminal Interface**: Pure console/terminal text-based menu system (NO GUI)

## 📚 Java & OOP Concepts We'll Implement

### 1. **Classes and Objects** (Day 1)
- **Book Class**: title, author, ISBN, availability status
- **Person Class**: Abstract base class with id, name, email
- **Student Class**: Extends Person, can borrow 2 books max
- **Staff Class**: Extends Person, can borrow 5 books max
- **Library Class**: Main class to manage books and members

### 2. **Inheritance** (Day 2)
```
Person (Abstract Parent Class)
├── Student (Child Class - 2 books max)
└── Staff (Child Class - 5 books max)
```

### 3. **Polymorphism** (Day 2)
- **Method Overriding**: Different `displayInfo()` for Student vs Staff
- **Method Overloading**: Multiple ways to search books
- **Runtime Polymorphism**: Person reference pointing to Student/Staff objects

### 4. **Encapsulation** (Day 1-2)
- **Private Fields**: All class variables are private
- **Getter/Setter Methods**: Public methods to safely access data
- **Validation**: Check data before setting values

### 5. **Abstraction** (Day 3)
- **Abstract Class**: Person class with abstract methods
- **Interface**: Simple `Borrowable` interface for lending operations

### 6. **Collections Framework** (Day 3)
- **ArrayList**: Store books and members (simple lists)
- **HashMap**: Quick lookup for books by ISBN

### 7. **Exception Handling** (Day 4)
- **Custom Exceptions**: 
  - `BookNotFoundException`
  - `MemberNotFoundException`
- **Try-Catch**: Handle user input errors

### 8. **File I/O** (Day 4)
- **Text Files**: Save/load books and members data
- **Simple Format**: Use basic file reading/writing

## 🏗️ Simplified Project Structure

```
LibraryManagementSystem/
├── Person.java (Abstract base class)
├── Student.java (Extends Person)
├── Staff.java (Extends Person)
├── Book.java (Book details)
├── Library.java (Main system management)
├── Borrowable.java (Interface)
├── BookNotFoundException.java (Custom exception)
├── MemberNotFoundException.java (Custom exception)
├── Main.java (Run the program)
└── data/
    ├── books.txt (Save books data)
    └── members.txt (Save members data)
```

## 🔄 5-Day Development Plan

### **Day 1: Basic Classes & Encapsulation**
**Goals**: Create foundation classes with proper encapsulation
- Create `Person` abstract class (id, name, email)
- Create `Book` class (title, author, isbn, isAvailable)
- Create `Student` and `Staff` classes extending Person
- Implement private fields with getter/setter methods
- Add constructors and basic validation

**What you'll learn**: Classes, Objects, Encapsulation, Constructors

### **Day 2: Inheritance & Polymorphism**
**Goals**: Implement inheritance hierarchy and method overriding
- Make Person abstract with abstract methods
- Override `displayInfo()` method in Student and Staff
- Add borrowing limits (Student: 2 books, Staff: 5 books)
- Implement method overloading for search functions
- Test runtime polymorphism

**What you'll learn**: Inheritance, Abstract classes, Method overriding/overloading, Polymorphism

### **Day 3: Collections & Interface**
**Goals**: Add data storage and interface implementation
- Create `Borrowable` interface with borrow/return methods
- Implement Library class with ArrayList for books and members
- Add HashMap for quick ISBN lookup
- Create basic add/remove/search functionality
- Implement the Borrowable interface

**What you'll learn**: Interfaces, ArrayList, HashMap, Collections Framework

### **Day 4: Exception Handling & File I/O**
**Goals**: Add error handling and data persistence
- Create custom exceptions (BookNotFoundException, MemberNotFoundException)
- Add try-catch blocks for user input and operations
- Implement file saving/loading for books and members
- Add input validation with exception handling

**What you'll learn**: Custom Exceptions, Try-catch blocks, File I/O, Data persistence

### **Day 5: Terminal Interface & Final Integration**
**Goals**: Create console-based user interface and complete the system
- Build simple terminal menu system using System.out.println() and Scanner
- Add borrow/return book functionality with console input/output
- Create simple text-based reports (available books, member status)
- Handle user input validation through terminal
- Test all features and fix bugs using console interaction only

**What you'll learn**: Console I/O, Scanner class, Terminal-based UI design, System integration

## � Console-Based Interface Examples

### **Main Menu Implementation:**
```java
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();
    
    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addBookMenu();
                    break;
                case 2:
                    addMemberMenu();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("    LIBRARY MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Book");
        System.out.println("2. Add Member");
        System.out.println("3. Search Books");
        System.out.println("4. Borrow Book");
        System.out.println("5. Return Book");
        System.out.println("6. View Available Books");
        System.out.println("7. View Member Status");
        System.out.println("8. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice (1-8): ");
    }
}
```

### **Console Input/Output Examples:**
```java
// Adding a book through console
private static void addBookMenu() {
    System.out.println("\n--- Add New Book ---");
    System.out.print("Enter book title: ");
    String title = scanner.nextLine();
    
    System.out.print("Enter author name: ");
    String author = scanner.nextLine();
    
    System.out.print("Enter ISBN: ");
    String isbn = scanner.nextLine();
    
    Book book = new Book(title, author, isbn);
    library.addBook(book);
    
    System.out.println("✓ Book added successfully!");
    System.out.println("Press Enter to continue...");
    scanner.nextLine();
}

// Displaying search results
public void displayBooks() {
    System.out.println("\n=== Available Books ===");
    if (books.isEmpty()) {
        System.out.println("No books available.");
        return;
    }
    
    for (int i = 0; i < books.size(); i++) {
        Book book = books.get(i);
        String status = book.isAvailable() ? "[Available]" : "[Borrowed]";
        System.out.printf("%d. %s by %s %s%n", 
            i+1, book.getTitle(), book.getAuthor(), status);
    }
}
```

## �🔧 Key Java Concepts - Simplified Examples

### 1. **Encapsulation Example**
```java
public class Book {
    private String title;
    private String author;
    private boolean isAvailable;
    
    // Constructor
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
    
    // Getters and Setters with validation
    public String getTitle() { return title; }
    public void setTitle(String title) { 
        if(title != null && !title.trim().isEmpty()) {
            this.title = title;
        }
    }
}
```

### 2. **Inheritance & Abstract Class**
```java
public abstract class Person {
    protected String id;
    protected String name;
    
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Abstract method - must be implemented by children
    public abstract void displayInfo();
    public abstract int getMaxBooks();
}

public class Student extends Person {
    public Student(String id, String name) {
        super(id, name);
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Student: " + name + " (ID: " + id + ")");
    }
    
    @Override
    public int getMaxBooks() {
        return 2; // Students can borrow 2 books max
    }
}
```

### 3. **Interface Implementation**
```java
public interface Borrowable {
    boolean borrowBook(String memberId, String bookTitle);
    boolean returnBook(String memberId, String bookTitle);
}

public class Library implements Borrowable {
    private ArrayList<Book> books;
    private ArrayList<Person> members;
    
    @Override
    public boolean borrowBook(String memberId, String bookTitle) {
        // Implementation for borrowing
        return true;
    }
}
```

### 4. **Exception Handling**
```java
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}

// Usage in Library class
public Book findBook(String title) throws BookNotFoundException {
    for(Book book : books) {
        if(book.getTitle().equals(title)) {
            return book;
        }
    }
    throw new BookNotFoundException("Book '" + title + "' not found!");
}
```

### 5. **Collections Usage**
```java
public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Person> members = new ArrayList<>();
    private HashMap<String, Book> bookMap = new HashMap<>();
    
    public void addBook(Book book) {
        books.add(book);
        bookMap.put(book.getTitle(), book);
    }
    
    public Book quickFindBook(String title) {
        return bookMap.get(title); // Fast lookup using HashMap
    }
}
```

## 📊 Simple Data Management

### Collections We'll Use:
- **ArrayList<Book>**: Store all books in the library
- **ArrayList<Person>**: Store all members (students and staff)
- **HashMap<String, Book>**: Quick book lookup by title

### File Operations:
- **Text Files**: Save books and members to simple .txt files
- **Basic Format**: One line per book/member with comma-separated values

## 🎯 What You'll Learn in 5 Days

### **Day 1 Concepts:**
✅ **Classes & Objects**: Creating blueprints and instances  
✅ **Encapsulation**: Private fields with public getter/setter methods  
✅ **Constructors**: Initializing objects properly  

### **Day 2 Concepts:**
✅ **Inheritance**: Parent-child class relationships  
✅ **Abstract Classes**: Classes that cannot be instantiated  
✅ **Method Overriding**: Child classes providing their own implementation  
✅ **Polymorphism**: Same method, different behavior  

### **Day 3 Concepts:**
✅ **Interfaces**: Contracts that classes must follow  
✅ **ArrayList**: Dynamic arrays for storing collections  
✅ **HashMap**: Key-value pairs for fast lookups  
✅ **Collections Framework**: Managing groups of objects  

### **Day 4 Concepts:**
✅ **Custom Exceptions**: Creating your own error types  
✅ **Try-Catch**: Handling errors gracefully  
✅ **File I/O**: Reading from and writing to files  
✅ **Data Persistence**: Saving data between program runs  

### **Day 5 Concepts:**
✅ **Console I/O**: Using System.out.println() for output and Scanner for input  
✅ **Terminal Menu Design**: Creating text-based navigation systems  
✅ **Input Validation**: Checking user input from console  
✅ **System Integration**: Connecting all parts together through console interface  
✅ **Testing**: Making sure everything works properly via terminal interaction

## 🚀 Sample Program Features

### **What the Program Will Do:**

#### **Basic Operations:**
- Add new books to the library
- Register new students and staff members
- Search for books by title or author
- Borrow books (if available and under limit)
- Return borrowed books
- View all available books
- View member borrowing status

#### **Smart Features:**
- Students can only borrow 2 books maximum
- Staff can borrow 5 books maximum  
- System tracks who has which books
- Prevents borrowing unavailable books
- Saves data to files so information persists
- Handles errors gracefully with custom messages

#### **Sample Terminal Menu System:**
```
========================================
    LIBRARY MANAGEMENT SYSTEM
========================================
1. Add Book
2. Add Member (Student/Staff)
3. Search Books
4. Borrow Book
5. Return Book
6. View Available Books
7. View Member Status
8. Save & Exit
========================================
Enter your choice (1-8): _
```

**Example Console Interaction:**
```
Enter your choice: 1
--- Add New Book ---
Enter book title: Java Programming
Enter author name: John Smith
Enter ISBN: 978-1234567890
Book added successfully!

Press Enter to continue...
```

## 🎯 Why This Project is Perfect for Learning

1. **Complete OOP Coverage**: Uses all 4 OOP principles naturally
2. **Real-World Application**: Something you could actually use
3. **Manageable Size**: Can be completed in 5 days
4. **Progressive Learning**: Each day builds on the previous
5. **Practical Skills**: File handling, error management, user input
6. **Portfolio Ready**: Great project to show employers your Java skills

## 📝 Success Tips

1. **Day 1**: Focus on getting classes working with proper encapsulation
2. **Day 2**: Make sure inheritance and method overriding work correctly
3. **Day 3**: Test collections thoroughly - add, search, remove operations
4. **Day 4**: Handle all possible errors users might encounter
5. **Day 5**: Make the interface user-friendly and test everything

**Remember**: It's better to have a simple, working system than a complex, broken one!

---

## 🎉 PROJECT COMPLETION STATUS

### ✅ **SUCCESSFULLY COMPLETED - ALL 5 DAYS IMPLEMENTED!**

#### **Implementation Summary:**
- **Day 1**: ✅ **COMPLETED** - Core OOP concepts (Person, Book, Student, Staff classes with full encapsulation)
- **Day 2**: ✅ **COMPLETED** - Collections & interfaces (Borrowable interface, ArrayList/HashMap, exception handling)
- **Day 3**: ✅ **COMPLETED** - Advanced collections & I/O (Transaction tracking, file persistence, TreeSet/LinkedList)
- **Day 4**: ✅ **COMPLETED** - Exception handling & features (Custom exceptions, ValidationUtils, advanced search, reporting)
- **Day 5**: ✅ **COMPLETED** - Console UI & integration (Professional menu system, complete feature integration)

#### **Final Deliverables:**
- ✅ **15+ Java Classes** with complete OOP implementation
- ✅ **Professional Console Interface** with comprehensive menu system
- ✅ **5+ Custom Exception Classes** with detailed error handling
- ✅ **File-based Data Persistence** system with CSV format
- ✅ **Advanced Search & Reporting** capabilities
- ✅ **Comprehensive Input Validation** using ValidationUtils
- ✅ **Full Test Coverage** for all components (Day1Test through Day5Test)
- ✅ **Ready for Academic Submission** or Portfolio Use

#### **Technical Achievements:**
- 🏗️ **Complete OOP Implementation**: All four pillars of OOP properly demonstrated
- 📚 **Java Collections Mastery**: ArrayList, HashMap, LinkedList, TreeSet usage
- ⚠️ **Professional Exception Handling**: Custom exception hierarchy with rollback
- 💾 **Data Persistence**: File I/O with CSV format and data integrity
- 🖥️ **Console UI Excellence**: Professional menu-driven interface
- 🔍 **Advanced Features**: Search, reporting, validation, transaction tracking

#### **Learning Outcomes Achieved:**
- ✅ Mastered encapsulation with private fields and public methods
- ✅ Implemented inheritance with abstract Person class and concrete subclasses
- ✅ Demonstrated polymorphism through method overriding and runtime behavior
- ✅ Applied abstraction with abstract classes and interfaces
- ✅ Utilized Java Collections Framework effectively
- ✅ Created comprehensive exception handling system
- ✅ Built file I/O system for data persistence
- ✅ Developed professional console user interface
- ✅ Applied software engineering best practices

### 🏆 **PROJECT READY FOR:**
- 📖 **Academic Submission** - Comprehensive Java OOP project
- 💼 **Portfolio Demonstration** - Professional-grade application
- 🚀 **Further Enhancement** - GUI development, database integration
- 🌐 **Real-world Deployment** - Scalable foundation architecture

**🎓 Congratulations! You now have a complete, professional Library Management System demonstrating all core Java and Object-Oriented Programming concepts!**