import java.util.Scanner;

/**
 * Main Console Interface for Library Management System - Simplified Version
 * 
 * Features:
 * - Interactive menu-driven console interface
 * - Core library functionality integration
 * - User input validation and error handling
 * - Data persistence between sessions
 * 
 * This class demonstrates:
 * - Scanner class for user input
 * - Switch statements for menu navigation
 * - Exception handling in user interface
 * - Integration of all OOP concepts
 */
public class LibraryManagementSystem {
    private static Library library;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        // Initialize system
        scanner = new Scanner(System.in);
        library = new Library("University Central Library");
        
        // Load existing data if available
        System.out.println("Starting Library Management System...");
        library.loadFromFiles();
        
        // Display welcome message
        displayWelcome();
        
        // Main application loop
        boolean running = true;
        while (running) {
            try {
                displayMainMenu();
                int choice = getMenuChoice(0, 7);
                
                switch (choice) {
                    case 1:
                        bookManagementMenu();
                        break;
                    case 2:
                        memberManagementMenu();
                        break;
                    case 3:
                        borrowingMenu();
                        break;
                    case 4:
                        searchMenu();
                        break;
                    case 5:
                        reportsMenu();
                        break;
                    case 6:
                        displayLibraryStatus();
                        break;
                    case 7:
                        dataManagementMenu();
                        break;
                    case 0:
                        running = exitSystem();
                        break;
                }
            } catch (Exception e) {
                System.out.println("\n⚠️  An unexpected error occurred: " + e.getMessage());
                System.out.println("Please try again or contact system administrator.");
                waitForEnter();
            }
        }
    }
    
    private static void displayWelcome() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              LIBRARY MANAGEMENT SYSTEM                  ║");
        System.out.println("║                   Version 1.0                           ║");
        System.out.println("║          Professional Console Interface                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println("\n🏛️  Welcome to " + library.getLibraryName() + "!");
        System.out.println("📚 Current Status: " + library.getTotalBooks() + " books, " + 
                         library.getTotalMembers() + " members");
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private static void displayMainMenu() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                     MAIN MENU                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║  1. 📚 Book Management                                  ║");
        System.out.println("║  2. 👥 Member Management                                ║");
        System.out.println("║  3. 🔄 Borrowing & Returns                              ║");
        System.out.println("║  4. 🔍 Search Books                                     ║");
        System.out.println("║  5. 📊 Reports                                          ║");
        System.out.println("║  6. 📈 Library Status                                   ║");
        System.out.println("║  7. 💾 Data Management                                  ║");
        System.out.println("║  0. 🚪 Exit System                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.print("\nSelect an option (0-7): ");
    }
    
    private static void bookManagementMenu() {
        while (true) {
            clearScreen();
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println("║                  BOOK MANAGEMENT                        ║");
            System.out.println("╚══════════════════════════════════════════════════════════╝");
            System.out.println("  1. ➕ Add New Book");
            System.out.println("  2. 📋 List All Books");
            System.out.println("  0. ⬅️  Back to Main Menu");
            
            int choice = getMenuChoice(0, 2);
            
            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    listAllBooks();
                    break;
                case 0:
                    return;
            }
        }
    }
    
    private static void memberManagementMenu() {
        while (true) {
            clearScreen();
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println("║                 MEMBER MANAGEMENT                       ║");
            System.out.println("╚══════════════════════════════════════════════════════════╝");
            System.out.println("  1. ➕ Add New Member");
            System.out.println("  2. 📋 List All Members");
            System.out.println("  0. ⬅️  Back to Main Menu");
            
            int choice = getMenuChoice(0, 2);
            
            switch (choice) {
                case 1:
                    addNewMember();
                    break;
                case 2:
                    listAllMembers();
                    break;
                case 0:
                    return;
            }
        }
    }
    
    private static void borrowingMenu() {
        while (true) {
            clearScreen();
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println("║                BORROWING & RETURNS                       ║");
            System.out.println("╚══════════════════════════════════════════════════════════╝");
            System.out.println("  1. 📚 Borrow Book");
            System.out.println("  2. 📥 Return Book");
            System.out.println("  3. 📈 Transaction Log");
            System.out.println("  0. ⬅️  Back to Main Menu");
            
            int choice = getMenuChoice(0, 3);
            
            switch (choice) {
                case 1:
                    borrowBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    viewTransactionLog();
                    break;
                case 0:
                    return;
            }
        }
    }
    
    private static void searchMenu() {
        while (true) {
            clearScreen();
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println("║                    SEARCH BOOKS                         ║");
            System.out.println("╚══════════════════════════════════════════════════════════╝");
            System.out.println("  1. 🔍 Search Books by Title");
            System.out.println("  2. 👨‍💼 Search Books by Author");
            System.out.println("  0. ⬅️  Back to Main Menu");
            
            int choice = getMenuChoice(0, 2);
            
            switch (choice) {
                case 1:
                    searchBooksByTitle();
                    break;
                case 2:
                    searchBooksByAuthor();
                    break;
                case 0:
                    return;
            }
        }
    }
    
    private static void reportsMenu() {
        while (true) {
            clearScreen();
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println("║                       REPORTS                            ║");
            System.out.println("╚══════════════════════════════════════════════════════════╝");
            System.out.println("  1. 📊 Generate Library Report");
            System.out.println("  2. 👤 Generate Member Report");
            System.out.println("  0. ⬅️  Back to Main Menu");
            
            int choice = getMenuChoice(0, 2);
            
            switch (choice) {
                case 1:
                    generateLibraryReport();
                    break;
                case 2:
                    generateMemberReport();
                    break;
                case 0:
                    return;
            }
        }
    }
    
    private static void dataManagementMenu() {
        while (true) {
            clearScreen();
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.println("║                  DATA MANAGEMENT                        ║");
            System.out.println("╚══════════════════════════════════════════════════════════╝");
            System.out.println("  1. 💾 Save All Data");
            System.out.println("  2. 📂 Load Data");
            System.out.println("  0. ⬅️  Back to Main Menu");
            
            int choice = getMenuChoice(0, 2);
            
            switch (choice) {
                case 1:
                    saveAllData();
                    break;
                case 2:
                    loadData();
                    break;
                case 0:
                    return;
            }
        }
    }
    
    // Implementation of core functionality methods
    private static void addNewBook() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    ADD NEW BOOK                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        try {
            System.out.print("📖 Enter book title: ");
            String title = scanner.nextLine().trim();
            
            System.out.print("👨‍💼 Enter author name: ");
            String author = scanner.nextLine().trim();
            
            System.out.print("🔢 Enter ISBN (10 or 13 digits): ");
            String isbn = scanner.nextLine().trim();
            
            if (library.addBook(title, author, isbn)) {
                System.out.println("✅ Book added successfully!");
            } else {
                System.out.println("❌ Failed to add book. Please check the details and try again.");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error adding book: " + e.getMessage());
        }
        
        waitForEnter();
    }
    
    private static void addNewMember() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                   ADD NEW MEMBER                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        try {
            System.out.println("Select member type:");
            System.out.println("  1. 🎓 Student");
            System.out.println("  2. 👨‍🏫 Staff");
            
            int memberType = getMenuChoice(1, 2);
            
            System.out.print("🆔 Enter member ID: ");
            String id = scanner.nextLine().trim();
            
            System.out.print("👤 Enter full name: ");
            String name = scanner.nextLine().trim();
            
            System.out.print("📧 Enter email address: ");
            String email = scanner.nextLine().trim();
            
            if (memberType == 1) {
                // Student
                System.out.print("🏫 Enter department: ");
                String department = scanner.nextLine().trim();
                
                System.out.print("📊 Enter year of study (1-4): ");
                int year = getIntInput(1, 4);
                
                Student student = new Student(id, name, email, department, year);
                if (library.addMember(student)) {
                    System.out.println("✅ Student added successfully!");
                } else {
                    System.out.println("❌ Failed to add student. Please check the details.");
                }
                
            } else {
                // Staff
                System.out.print("🏫 Enter department: ");
                String department = scanner.nextLine().trim();
                
                System.out.print("💼 Enter position: ");
                String position = scanner.nextLine().trim();
                
                Staff staff = new Staff(id, name, email, department, position);
                if (library.addMember(staff)) {
                    System.out.println("✅ Staff member added successfully!");
                } else {
                    System.out.println("❌ Failed to add staff member. Please check the details.");
                }
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error adding member: " + e.getMessage());
        }
        
        waitForEnter();
    }
    
    private static void borrowBook() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    BORROW BOOK                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        try {
            System.out.print("🆔 Enter member ID: ");
            String memberId = scanner.nextLine().trim();
            
            System.out.print("📖 Enter book title: ");
            String bookTitle = scanner.nextLine().trim();
            
            if (library.borrowBook(memberId, bookTitle)) {
                System.out.println("✅ Book borrowed successfully!");
            } else {
                System.out.println("❌ Failed to borrow book. Please check the details.");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error during borrowing: " + e.getMessage());
        }
        
        waitForEnter();
    }
    
    private static void returnBook() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    RETURN BOOK                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        try {
            System.out.print("🆔 Enter member ID: ");
            String memberId = scanner.nextLine().trim();
            
            System.out.print("📖 Enter book title: ");
            String bookTitle = scanner.nextLine().trim();
            
            if (library.returnBook(memberId, bookTitle)) {
                System.out.println("✅ Book returned successfully!");
            } else {
                System.out.println("❌ Failed to return book. Please check the details.");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error during return: " + e.getMessage());
        }
        
        waitForEnter();
    }
    
    private static void displayLibraryStatus() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                   LIBRARY STATUS                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        library.displayLibraryStats();
        
        System.out.println("\n📊 Quick Overview:");
        System.out.println("   👥 Active Members: " + library.getTotalMembers());
        System.out.println("   🔄 Total Transactions: " + library.getTransactionCount());
        
        waitForEnter();
    }
    
    private static void viewTransactionLog() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                  TRANSACTION LOG                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        System.out.println("📊 Total Transactions: " + library.getTransactionCount());
        System.out.println("🚧 Detailed transaction log feature coming soon!");
        waitForEnter();
    }
    
    private static void listAllBooks() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                     ALL BOOKS                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        library.displayAvailableBooks();
        waitForEnter();
    }
    
    private static void listAllMembers() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    ALL MEMBERS                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        library.displayAllMembers();
        waitForEnter();
    }
    
    private static void searchBooksByTitle() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                SEARCH BOOKS BY TITLE                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        System.out.print("🔍 Enter title to search: ");
        String title = scanner.nextLine().trim();
        
        var results = library.searchBooksByTitle(title);
        if (results.isEmpty()) {
            System.out.println("❌ No books found with title containing: " + title);
        } else {
            System.out.println("✅ Found " + results.size() + " book(s):");
            for (Book book : results) {
                System.out.println("  📖 " + book.toString());
            }
        }
        
        waitForEnter();
    }
    
    private static void searchBooksByAuthor() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║               SEARCH BOOKS BY AUTHOR                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        System.out.print("🔍 Enter author name to search: ");
        String author = scanner.nextLine().trim();
        
        var results = library.searchBooksByAuthor(author);
        if (results.isEmpty()) {
            System.out.println("❌ No books found by author containing: " + author);
        } else {
            System.out.println("✅ Found " + results.size() + " book(s):");
            for (Book book : results) {
                System.out.println("  📖 " + book.toString());
            }
        }
        
        waitForEnter();
    }
    
    private static void generateLibraryReport() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              GENERATE LIBRARY REPORT                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        String filename = "library_report_" + java.time.LocalDate.now() + ".txt";
        
        if (library.generateLibraryReport(filename)) {
            System.out.println("✅ Library report generated successfully!");
            System.out.println("📁 Report saved as: reports/" + filename);
        } else {
            System.out.println("❌ Failed to generate library report.");
        }
        
        waitForEnter();
    }
    
    private static void generateMemberReport() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║               GENERATE MEMBER REPORT                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        System.out.print("🆔 Enter member ID: ");
        String memberId = scanner.nextLine().trim();
        
        String filename = "member_report_" + memberId + "_" + java.time.LocalDate.now() + ".txt";
        
        if (library.generateBorrowingReport(memberId, filename)) {
            System.out.println("✅ Member report generated successfully!");
            System.out.println("📁 Report saved as: reports/" + filename);
        } else {
            System.out.println("❌ Failed to generate member report. Please check the member ID.");
        }
        
        waitForEnter();
    }
    
    private static void saveAllData() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                   SAVE ALL DATA                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        library.saveToFiles();
        System.out.println("✅ All data saved successfully!");
        
        waitForEnter();
    }
    
    private static void loadData() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                     LOAD DATA                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        library.loadFromFiles();
        System.out.println("✅ Data loaded successfully!");
        
        waitForEnter();
    }
    
    // Helper methods
    private static int getMenuChoice(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("❌ Invalid choice. Please enter a number between " + 
                                   min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid input. Please enter a valid number: ");
            }
        }
    }
    
    private static int getIntInput(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.print("❌ Invalid value. Please enter a number between " + 
                                   min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid input. Please enter a valid number: ");
            }
        }
    }
    
    private static void clearScreen() {
        // Clear screen for better user experience
        System.out.print("\\033[2J\\033[H");
        System.out.flush();
    }
    
    private static void waitForEnter() {
        System.out.println("\\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private static boolean exitSystem() {
        clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                     EXIT SYSTEM                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        System.out.print("💾 Do you want to save all data before exiting? (y/n): ");
        String saveChoice = scanner.nextLine().trim().toLowerCase();
        
        if (saveChoice.equals("y") || saveChoice.equals("yes")) {
            library.saveToFiles();
            System.out.println("✅ Data saved successfully!");
        }
        
        System.out.println("\\n👋 Thank you for using the Library Management System!");
        System.out.println("🏛️  " + library.getLibraryName() + " - Have a great day!");
        
        scanner.close();
        return false; // Exit the main loop
    }
}