# 📚 Library Management System

A comprehensive **Java Object-Oriented Programming (OOP)** project demonstrating all core Java concepts through a professional console-based library management application.

## 🎯 Project Overview

This Library Management System is designed as a **5-day learning project** that progressively implements all major Java and OOP concepts. It serves as an excellent demonstration of professional Java development practices and can be used for academic purposes, portfolio projects, or as a foundation for more advanced systems.

## ✨ Features

### 📚 **Book Management**
- Add new books with validation
- List all books with availability status
- Search books by title and author
- ISBN validation and duplicate checking

### 👥 **Member Management**
- Register students and staff members
- Different borrowing limits (Students: 2 books, Staff: 5 books)
- Email validation and member ID management
- List all members with detailed information

### 🔄 **Borrowing & Returns**
- Borrow books with limit enforcement
- Return books with transaction tracking
- Real-time availability updates
- Transaction history logging

### 🔍 **Search Functionality**
- Search books by title (partial matching)
- Search books by author (partial matching)
- Case-insensitive search implementation
- Advanced search capabilities

### 📊 **Reports & Analytics**
- Generate comprehensive library reports
- Create individual member borrowing reports
- Export reports to text files
- Transaction count and statistics

### 💾 **Data Persistence**
- Save/load data to/from CSV files
- Automatic data backup on exit
- Data integrity validation
- Cross-session data persistence

## 🏗️ **Technical Architecture**

### **Object-Oriented Design**
```
Person (Abstract Class)
├── Student (Concrete Class)
└── Staff (Concrete Class)

Book (Entity Class)
Library (Main System Class)
Transaction (Tracking Class)
ValidationUtils (Utility Class)
```

### **Design Patterns Implemented**
- **Abstract Factory Pattern**: Person class hierarchy
- **Strategy Pattern**: Different borrowing limits for member types
- **Observer Pattern**: Transaction tracking system
- **Singleton Pattern**: Library configuration

### **Java Collections Used**
- **ArrayList**: Dynamic storage for books and members
- **HashMap**: Fast lookups for members and books
- **LinkedList**: Transaction history management
- **TreeSet**: Sorted collections for reporting

## 🎓 **Java & OOP Concepts Demonstrated**

### **Core OOP Principles**
- ✅ **Encapsulation**: Private fields with public getters/setters
- ✅ **Inheritance**: Person → Student/Staff hierarchy
- ✅ **Polymorphism**: Method overriding and runtime behavior
- ✅ **Abstraction**: Abstract classes and interfaces

### **Advanced Java Features**
- ✅ **Custom Exception Handling**: 5+ custom exception classes
- ✅ **Interface Implementation**: Borrowable interface
- ✅ **File I/O Operations**: CSV-based persistence
- ✅ **Input Validation**: Comprehensive validation system
- ✅ **Collections Framework**: Multiple collection types
- ✅ **Date/Time API**: LocalDateTime for timestamps

### **Software Engineering Practices**
- ✅ **Clean Code**: Meaningful names and documentation
- ✅ **Error Handling**: Graceful error recovery
- ✅ **Data Validation**: Input sanitization and validation
- ✅ **Modular Design**: Separation of concerns
- ✅ **Professional UI**: Menu-driven console interface

## 📋 **Project Structure**

```
LibraryManagementSystem/
├── 📄 Core Classes
│   ├── Person.java                     # Abstract base class
│   ├── Student.java                    # Student implementation
│   ├── Staff.java                      # Staff implementation
│   └── Book.java                       # Book entity class
├── 🏛️ System Classes
│   ├── Library.java                    # Main system logic
│   ├── LibraryManagementSystem.java    # Console interface
│   ├── Transaction.java                # Transaction tracking
│   └── ValidationUtils.java            # Input validation
├── 🔗 Interfaces
│   └── Borrowable.java                 # Borrowing interface
├── ⚠️ Exception Classes
│   ├── BookNotFoundException.java
│   ├── BookAlreadyBorrowedException.java
│   ├── MemberNotFoundException.java
│   ├── MaxBooksExceededException.java
│   └── InvalidDataException.java
├── 💾 Data Directories
│   ├── data/                           # CSV persistence files
│   └── reports/                        # Generated reports
└── 📖 Documentation
    ├── README.md                       # This file
    └── LibraryManagementSystem_Roadmap.md
```

## 🚀 **Getting Started**

### **Prerequisites**
- Java 8 or higher
- Command line/terminal access
- Text editor or IDE (optional)

### **Installation & Running**

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/library-management-system.git
   cd library-management-system
   ```

2. **Compile the project**
   ```bash
   javac *.java
   ```

3. **Run the application**
   ```bash
   java LibraryManagementSystem
   ```

4. **Follow the interactive menu**
   - Use number keys to navigate menus
   - Follow on-screen prompts for data entry
   - Data is automatically saved between sessions

## 🎮 **Usage Examples**

### **Adding a New Book**
```
Main Menu → 1 (Book Management) → 1 (Add New Book)
📖 Enter book title: Java Programming
👨‍💼 Enter author name: James Gosling
🔢 Enter ISBN: 978-1234567890
✅ Book added successfully!
```

### **Adding a New Member**
```
Main Menu → 2 (Member Management) → 1 (Add New Member) → 1 (Student)
🆔 Enter member ID: S001
👤 Enter full name: Alice Johnson
📧 Enter email address: alice@email.com
🏫 Enter department: Computer Science
📊 Enter year of study: 2
✅ Student added successfully!
```

### **Borrowing a Book**
```
Main Menu → 3 (Borrowing & Returns) → 1 (Borrow Book)
🆔 Enter member ID: S001
📖 Enter book title: Java Programming
✅ Book borrowed successfully!
```

## 🧪 **Testing the System**

### **Manual Test Cases**

1. **Basic Operations Test**
   - Add 3-4 books with different authors
   - Add 2 students and 1 staff member
   - Test borrowing with different member types

2. **Limit Testing**
   - Students: Try borrowing more than 2 books
   - Staff: Try borrowing up to 5 books
   - Verify limit enforcement

3. **Search Functionality**
   - Search by partial title matches
   - Search by author names
   - Test case-insensitive searches

4. **Data Persistence**
   - Add data, save, and exit
   - Restart application and verify data loads
   - Test report generation

5. **Error Handling**
   - Try invalid inputs (empty names, invalid emails)
   - Attempt to borrow non-existent books
   - Test duplicate member/book addition

## 📊 **Project Statistics**

- **📁 Files**: 15+ Java source files
- **📏 Lines of Code**: 1,500+ lines
- **⚙️ Classes**: 15+ classes with full OOP implementation
- **🔧 Methods**: 50+ methods demonstrating various Java concepts
- **📚 Features**: 20+ implemented features
- **⚠️ Exception Handling**: 5 custom exception classes

## 🎯 **Learning Outcomes**

By studying and running this project, you will understand:

- Complete **Object-Oriented Programming** implementation
- **Java Collections Framework** usage
- **Exception handling** best practices
- **File I/O** and data persistence
- **Console application** development
- **Input validation** and error recovery
- **Professional code structure** and documentation

## 🚀 **Future Enhancements**

This project serves as a foundation for advanced features:

- **📱 GUI Development**: JavaFX or Swing interface
- **🗄️ Database Integration**: MySQL/PostgreSQL connectivity
- **🌐 Web Interface**: Spring Boot web application
- **📱 REST API**: JSON-based web services
- **🔐 User Authentication**: Login and role management
- **📧 Email Notifications**: Overdue book reminders
- **📈 Advanced Analytics**: Data visualization and reporting

## 🤝 **Contributing**

This is a learning project, but contributions are welcome:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 **License**

This project is created for educational purposes. Feel free to use it for learning, academic projects, or as a foundation for your own applications.

## 🙏 **Acknowledgments**

- Java documentation and tutorials
- Object-oriented programming principles
- Console application design patterns
- Open source Java community

---

**⭐ If this project helped you learn Java and OOP concepts, please give it a star!**

**🎓 Perfect for: Academic projects, Portfolio demonstrations, Java learning, OOP concept implementation**
