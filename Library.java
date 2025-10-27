import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.TreeSet;
import java.io.*;
import java.time.LocalDateTime;

/**
 * Library class - Main management system for books and members
 * Demonstrates: Interface implementation, Collections (ArrayList, HashMap), 
 * Exception handling, CRUD operations
 * 
 * This class implements the Borrowable interface and manages all library operations
 */
public class Library implements Borrowable {
    
    // Collections to store library data
    private ArrayList<Book> books;              // Dynamic list of all books
    private ArrayList<Person> members;          // Dynamic list of all members
    private HashMap<String, Book> bookLookup;   // Fast lookup by book title
    private HashMap<String, Person> memberLookup; // Fast lookup by member ID
    private LinkedList<Transaction> transactionHistory; // Transaction history (FIFO)
    private TreeSet<Transaction> sortedTransactions;    // Sorted transactions (newest first)
    
    // Library information
    private String libraryName;
    
    // Constructor
    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.bookLookup = new HashMap<>();
        this.memberLookup = new HashMap<>();
        this.transactionHistory = new LinkedList<>();
        this.sortedTransactions = new TreeSet<>();
        
        System.out.println("Library '" + libraryName + "' initialized successfully!");
    }
    
    // Default constructor
    public Library() {
        this("Central Library");
    }
    
    // ==================== BOOK MANAGEMENT METHODS ====================
    
    /**
     * Add a new book to the library with validation
     */
    public boolean addBook(Book book) throws InvalidDataException {
        if (book == null) {
            throw new InvalidDataException("book", "null", "valid Book object");
        }
        
        try {
            // Validate book data
            ValidationUtils.validateBookData(book.getTitle(), book.getAuthor(), book.getIsbn());
            
            // Check if book already exists
            if (bookLookup.containsKey(book.getTitle())) {
                System.out.println("Warning: Book '" + book.getTitle() + "' already exists in library!");
                return false;
            }
            
            books.add(book);
            bookLookup.put(book.getTitle(), book);
            System.out.println("✓ Book '" + book.getTitle() + "' added to library successfully!");
            return true;
            
        } catch (InvalidDataException e) {
            System.out.println("Book validation failed: " + e.getValidationHelp());
            throw e; // Re-throw for caller to handle
        } catch (Exception e) {
            System.out.println("Unexpected error adding book: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Add a new book with individual parameters and validation
     */
    public boolean addBook(String title, String author, String isbn) {
        try {
            ValidationUtils.validateBookData(title, author, isbn);
            Book book = new Book(title, author, isbn);
            return addBook(book);
        } catch (InvalidDataException e) {
            System.out.println("Cannot add book: " + e.getValidationHelp());
            return false;
        }
    }
    
    /**
     * Remove a book from the library
     */
    public boolean removeBook(String title) throws BookNotFoundException {
        Book book = findBookByTitle(title);
        
        if (!book.isAvailable()) {
            System.out.println("Error: Cannot remove book '" + title + "' - currently borrowed!");
            return false;
        }
        
        books.remove(book);
        bookLookup.remove(title);
        System.out.println("✓ Book '" + title + "' removed from library successfully!");
        return true;
    }
    
    /**
     * Search for books by title (partial match)
     */
    public List<Book> searchBooksByTitle(String searchTerm) {
        List<Book> results = new ArrayList<>();
        String searchLower = searchTerm.toLowerCase();
        
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchLower)) {
                results.add(book);
            }
        }
        
        return results;
    }
    
    /**
     * Search for books by author (partial match)
     */
    public List<Book> searchBooksByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        String authorLower = author.toLowerCase();
        
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(authorLower)) {
                results.add(book);
            }
        }
        
        return results;
    }
    
    /**
     * Find exact book by title
     */
    public Book findBookByTitle(String title) throws BookNotFoundException {
        Book book = bookLookup.get(title);
        if (book == null) {
            throw new BookNotFoundException("Book '" + title + "' not found in library!");
        }
        return book;
    }
    
    // ==================== MEMBER MANAGEMENT METHODS ====================
    
    /**
     * Add a new member to the library with validation
     */
    public boolean addMember(Person member) throws InvalidDataException {
        if (member == null) {
            throw new InvalidDataException("member", "null", "valid Person object");
        }
        
        try {
            // Validate member data based on type
            if (member instanceof Student) {
                Student student = (Student) member;
                ValidationUtils.validateStudentData(
                    student.getId(), student.getName(), student.getEmail(),
                    student.getCourse(), student.getYear(), student.getPhoneNumber()
                );
            } else if (member instanceof Staff) {
                Staff staff = (Staff) member;
                ValidationUtils.validateStaffData(
                    staff.getId(), staff.getName(), staff.getEmail(),
                    staff.getDepartment(), staff.getPosition(), staff.getPhoneNumber()
                );
            }
            
            // Check if member already exists
            if (memberLookup.containsKey(member.getId())) {
                System.out.println("Warning: Member with ID '" + member.getId() + "' already exists!");
                return false;
            }
            
            members.add(member);
            memberLookup.put(member.getId(), member);
            System.out.println("✓ Member '" + member.getName() + "' added to library successfully!");
            return true;
            
        } catch (InvalidDataException e) {
            System.out.println("Member validation failed: " + e.getValidationHelp());
            throw e;
        } catch (Exception e) {
            System.out.println("Unexpected error adding member: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Remove a member from the library
     */
    public boolean removeMember(String memberId) throws MemberNotFoundException {
        Person member = findMemberById(memberId);
        
        // Check if member has borrowed books
        if (getBorrowedBooksCount(memberId) > 0) {
            System.out.println("Error: Cannot remove member '" + member.getName() + "' - has borrowed books!");
            return false;
        }
        
        members.remove(member);
        memberLookup.remove(memberId);
        System.out.println("✓ Member '" + member.getName() + "' removed from library successfully!");
        return true;
    }
    
    /**
     * Find member by ID
     */
    public Person findMemberById(String memberId) throws MemberNotFoundException {
        Person member = memberLookup.get(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member with ID '" + memberId + "' not found!");
        }
        return member;
    }
    
    // ==================== BORROWABLE INTERFACE IMPLEMENTATION ====================
    
    @Override
    public boolean borrowBook(String memberId, String bookTitle) {
        Transaction transaction = null;
        try {
            // Validate input
            ValidationUtils.validateNotEmpty(memberId, "member ID");
            ValidationUtils.validateNotEmpty(bookTitle, "book title");
            
            // Find member and book
            Person member = findMemberById(memberId);
            Book book = findBookByTitle(bookTitle);
            
            // Check if book is available
            if (!book.isAvailable()) {
                throw new BookAlreadyBorrowedException(bookTitle, book.getBorrowedBy());
            }
            
            // Check if member can borrow more books
            if (!canMemberBorrowMore(memberId)) {
                int currentBooks = getBorrowedBooksCount(memberId);
                int maxBooks = member.getMaxBooks();
                throw new MaxBooksExceededException(memberId, member.getName(), 
                    currentBooks, maxBooks, member.getPersonType());
            }
            
            // Perform borrowing operation
            book.borrowBook(memberId);
            
            // Update member's borrowed count
            if (member instanceof Student) {
                ((Student) member).borrowBook();
            } else if (member instanceof Staff) {
                ((Staff) member).borrowBook();
            }
            
            // Record transaction
            transaction = new Transaction(memberId, member.getName(), 
                bookTitle, book.getIsbn(), Transaction.TransactionType.BORROW);
            transactionHistory.add(transaction);
            sortedTransactions.add(transaction);
            
            System.out.println("✓ Book '" + bookTitle + "' borrowed by '" + member.getName() + "' successfully!");
            return true;
            
        } catch (BookNotFoundException e) {
            System.out.println("Borrowing failed: " + e.getMessage());
            return false;
        } catch (MemberNotFoundException e) {
            System.out.println("Borrowing failed: " + e.getMessage());
            return false;
        } catch (BookAlreadyBorrowedException e) {
            System.out.println("Borrowing failed: " + e.getDetailedMessage());
            return false;
        } catch (MaxBooksExceededException e) {
            System.out.println("Borrowing failed: " + e.getMessage());
            System.out.println(e.getSuggestion());
            return false;
        } catch (InvalidDataException e) {
            System.out.println("Borrowing failed: " + e.getValidationHelp());
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error during borrowing: " + e.getMessage());
            // Rollback transaction if it was created
            if (transaction != null) {
                transactionHistory.remove(transaction);
                sortedTransactions.remove(transaction);
            }
            return false;
        }
    }
    
    @Override
    public boolean returnBook(String memberId, String bookTitle) {
        try {
            // Find member and book
            Person member = findMemberById(memberId);
            Book book = findBookByTitle(bookTitle);
            
            // Check if this member actually borrowed this book
            if (book.isAvailable() || !memberId.equals(book.getBorrowedBy())) {
                System.out.println("Error: Member '" + member.getName() + "' did not borrow this book!");
                return false;
            }
            
            // Perform return operation
            book.returnBook();
            
            // Update member's borrowed count
            if (member instanceof Student) {
                ((Student) member).returnBook();
            } else if (member instanceof Staff) {
                ((Staff) member).returnBook();
            }
            
            // Record transaction
            Transaction transaction = new Transaction(memberId, member.getName(), 
                bookTitle, book.getIsbn(), Transaction.TransactionType.RETURN);
            transactionHistory.add(transaction);
            sortedTransactions.add(transaction);
            
            System.out.println("✓ Book '" + bookTitle + "' returned by '" + member.getName() + "' successfully!");
            return true;
            
        } catch (BookNotFoundException | MemberNotFoundException e) {
            System.out.println("Return failed: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean canMemberBorrowMore(String memberId) {
        try {
            Person member = findMemberById(memberId);
            
            if (member instanceof Student) {
                return ((Student) member).canBorrowMoreBooks();
            } else if (member instanceof Staff) {
                return ((Staff) member).canBorrowMoreBooks();
            }
            
            return false;
        } catch (MemberNotFoundException e) {
            return false;
        }
    }
    
    @Override
    public int getBorrowedBooksCount(String memberId) {
        try {
            Person member = findMemberById(memberId);
            
            if (member instanceof Student) {
                return ((Student) member).getCurrentBorrowedBooks();
            } else if (member instanceof Staff) {
                return ((Staff) member).getCurrentBorrowedBooks();
            }
            
            return 0;
        } catch (MemberNotFoundException e) {
            return 0;
        }
    }
    
    @Override
    public boolean isBookAvailable(String bookTitle) {
        try {
            Book book = findBookByTitle(bookTitle);
            return book.isAvailable();
        } catch (BookNotFoundException e) {
            return false;
        }
    }
    
    // ==================== DISPLAY METHODS ====================
    
    /**
     * Display all available books
     */
    public void displayAvailableBooks() {
        System.out.println("\n=== AVAILABLE BOOKS IN " + libraryName.toUpperCase() + " ===");
        
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        
        int count = 0;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.printf("%d. %s by %s [ISBN: %s]\n", 
                    ++count, book.getTitle(), book.getAuthor(), book.getIsbn());
            }
        }
        
        if (count == 0) {
            System.out.println("No books currently available.");
        }
        System.out.println("Total available books: " + count);
    }
    
    /**
     * Display all members
     */
    public void displayAllMembers() {
        System.out.println("\n=== ALL MEMBERS IN " + libraryName.toUpperCase() + " ===");
        
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        
        for (int i = 0; i < members.size(); i++) {
            Person member = members.get(i);
            System.out.printf("%d. %s (ID: %s) - %s - Books: %d/%d\n", 
                i + 1, member.getName(), member.getId(), member.getPersonType(),
                getBorrowedBooksCount(member.getId()), member.getMaxBooks());
        }
    }
    
    /**
     * Display library statistics
     */
    public void displayLibraryStats() {
        System.out.println("\n=== " + libraryName.toUpperCase() + " STATISTICS ===");
        System.out.println("Total Books: " + books.size());
        System.out.println("Total Members: " + members.size());
        
        int availableBooks = 0;
        int borrowedBooks = 0;
        
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks++;
            } else {
                borrowedBooks++;
            }
        }
        
        System.out.println("Available Books: " + availableBooks);
        System.out.println("Borrowed Books: " + borrowedBooks);
        System.out.println("Students: " + countStudents());
        System.out.println("Staff: " + countStaff());
    }
    
    // Helper methods
    private int countStudents() {
        int count = 0;
        for (Person member : members) {
            if (member instanceof Student) count++;
        }
        return count;
    }
    
    private int countStaff() {
        int count = 0;
        for (Person member : members) {
            if (member instanceof Staff) count++;
        }
        return count;
    }
    
    // Getters
    public String getLibraryName() {
        return libraryName;
    }
    
    public int getTotalBooks() {
        return books.size();
    }
    
    public int getTotalMembers() {
        return members.size();
    }
    
    // ==================== ADVANCED SEARCH METHODS ====================
    
    /**
     * Advanced book search with multiple criteria
     */
    public List<Book> advancedBookSearch(String title, String author, String isbn, Boolean available) {
        List<Book> results = new ArrayList<>();
        
        try {
            for (Book book : books) {
                boolean matches = true;
                
                // Check title (case-insensitive partial match)
                if (title != null && !title.trim().isEmpty()) {
                    if (!book.getTitle().toLowerCase().contains(title.toLowerCase().trim())) {
                        matches = false;
                    }
                }
                
                // Check author (case-insensitive partial match)
                if (author != null && !author.trim().isEmpty()) {
                    if (!book.getAuthor().toLowerCase().contains(author.toLowerCase().trim())) {
                        matches = false;
                    }
                }
                
                // Check ISBN (exact match)
                if (isbn != null && !isbn.trim().isEmpty()) {
                    if (!book.getIsbn().equals(isbn.trim())) {
                        matches = false;
                    }
                }
                
                // Check availability
                if (available != null) {
                    if (book.isAvailable() != available.booleanValue()) {
                        matches = false;
                    }
                }
                
                if (matches) {
                    results.add(book);
                }
            }
        } catch (Exception e) {
            System.out.println("Error during advanced search: " + e.getMessage());
        }
        
        return results;
    }
    
    /**
     * Search books by multiple keywords in title or author
     */
    public List<Book> searchBooksByKeywords(String... keywords) {
        List<Book> results = new ArrayList<>();
        
        if (keywords == null || keywords.length == 0) {
            return results;
        }
        
        try {
            for (Book book : books) {
                boolean allKeywordsFound = true;
                String searchText = (book.getTitle() + " " + book.getAuthor()).toLowerCase();
                
                for (String keyword : keywords) {
                    if (keyword != null && !keyword.trim().isEmpty()) {
                        if (!searchText.contains(keyword.toLowerCase().trim())) {
                            allKeywordsFound = false;
                            break;
                        }
                    }
                }
                
                if (allKeywordsFound) {
                    results.add(book);
                }
            }
        } catch (Exception e) {
            System.out.println("Error during keyword search: " + e.getMessage());
        }
        
        return results;
    }
    
    /**
     * Search members by type and other criteria
     */
    public List<Person> searchMembers(String memberType, String namePattern, Integer maxBooksFilter) {
        List<Person> results = new ArrayList<>();
        
        try {
            for (Person member : members) {
                boolean matches = true;
                
                // Check member type
                if (memberType != null && !memberType.trim().isEmpty()) {
                    if (!member.getPersonType().equalsIgnoreCase(memberType.trim())) {
                        matches = false;
                    }
                }
                
                // Check name pattern
                if (namePattern != null && !namePattern.trim().isEmpty()) {
                    if (!member.getName().toLowerCase().contains(namePattern.toLowerCase().trim())) {
                        matches = false;
                    }
                }
                
                // Check max books filter
                if (maxBooksFilter != null) {
                    if (member.getMaxBooks() != maxBooksFilter.intValue()) {
                        matches = false;
                    }
                }
                
                if (matches) {
                    results.add(member);
                }
            }
        } catch (Exception e) {
            System.out.println("Error during member search: " + e.getMessage());
        }
        
        return results;
    }
    
    /**
     * Find overdue members (for future enhancement - placeholder)
     */
    public List<Person> findMembersWithBorrowedBooks() {
        List<Person> results = new ArrayList<>();
        
        try {
            for (Person member : members) {
                if (getBorrowedBooksCount(member.getId()) > 0) {
                    results.add(member);
                }
            }
        } catch (Exception e) {
            System.out.println("Error finding members with borrowed books: " + e.getMessage());
        }
        
        return results;
    }
    
    // ==================== TRANSACTION HISTORY METHODS ====================
    
    /**
     * Display recent transactions
     */
    public void displayRecentTransactions(int count) {
        System.out.println("\n=== RECENT TRANSACTIONS ===");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions recorded.");
            return;
        }
        
        int displayed = 0;
        for (Transaction transaction : sortedTransactions) {
            if (displayed >= count) break;
            transaction.displayTransaction();
            displayed++;
        }
    }
    
    /**
     * Display all transactions for a specific member
     */
    public void displayMemberTransactions(String memberId) {
        System.out.println("\n=== TRANSACTIONS FOR MEMBER: " + memberId + " ===");
        boolean found = false;
        
        for (Transaction transaction : sortedTransactions) {
            if (transaction.getMemberId().equals(memberId)) {
                transaction.displayTransaction();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No transactions found for this member.");
        }
    }
    
    /**
     * Get transaction count
     */
    public int getTransactionCount() {
        return transactionHistory.size();
    }
    
    // ==================== FILE I/O METHODS ====================
    
    /**
     * Save all library data to files
     */
    public void saveToFiles() {
        try {
            saveBooksToFile();
            saveMembersToFile();
            saveTransactionsToFile();
            System.out.println("✓ All library data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving library data: " + e.getMessage());
        }
    }
    
    /**
     * Load all library data from files
     */
    public void loadFromFiles() {
        try {
            loadBooksFromFile();
            loadMembersFromFile();
            loadTransactionsFromFile();
            System.out.println("✓ All library data loaded successfully!");
        } catch (IOException e) {
            System.out.println("Error loading library data: " + e.getMessage());
        }
    }
    
    /**
     * Save books to file
     */
    private void saveBooksToFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/books.txt"))) {
            writer.println("# Library Books Data");
            writer.println("# Format: Title,Author,ISBN,Available,BorrowedBy");
            
            for (Book book : books) {
                String borrowedBy = book.getBorrowedBy() != null ? book.getBorrowedBy() : "";
                writer.printf("%s,%s,%s,%b,%s%n",
                    book.getTitle().replace(",", "&#44;"),
                    book.getAuthor().replace(",", "&#44;"),
                    book.getIsbn(),
                    book.isAvailable(),
                    borrowedBy
                );
            }
        }
    }
    
    /**
     * Save members to file
     */
    private void saveMembersToFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/members.txt"))) {
            writer.println("# Library Members Data");
            writer.println("# Format: Type,ID,Name,Email,Phone,ExtraInfo,BorrowedBooks");
            
            for (Person member : members) {
                String extraInfo = "";
                int borrowedBooks = 0;
                
                if (member instanceof Student) {
                    Student student = (Student) member;
                    extraInfo = (student.getCourse() != null ? student.getCourse() : "") + 
                               "|" + student.getYear();
                    borrowedBooks = student.getCurrentBorrowedBooks();
                } else if (member instanceof Staff) {
                    Staff staff = (Staff) member;
                    extraInfo = (staff.getDepartment() != null ? staff.getDepartment() : "") + 
                               "|" + (staff.getPosition() != null ? staff.getPosition() : "");
                    borrowedBooks = staff.getCurrentBorrowedBooks();
                }
                
                writer.printf("%s,%s,%s,%s,%s,%s,%d%n",
                    member.getPersonType(),
                    member.getId(),
                    member.getName().replace(",", "&#44;"),
                    member.getEmail(),
                    member.getPhoneNumber() != null ? member.getPhoneNumber() : "",
                    extraInfo.replace(",", "&#44;"),
                    borrowedBooks
                );
            }
        }
    }
    
    /**
     * Save transactions to file
     */
    private void saveTransactionsToFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/transactions.txt"))) {
            writer.println("# Library Transactions Data");
            writer.println("# Format: TransactionID,MemberID,MemberName,BookTitle,BookISBN,Type,Timestamp,Notes");
            
            for (Transaction transaction : transactionHistory) {
                writer.println(transaction.toFileFormat());
            }
        }
    }
    
    /**
     * Load books from file
     */
    private void loadBooksFromFile() throws IOException {
        File file = new File("data/books.txt");
        if (!file.exists()) {
            System.out.println("Books file not found. Starting with empty book collection.");
            return;
        }
        
        books.clear();
        bookLookup.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#") || line.trim().isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String title = parts[0].replace("&#44;", ",");
                    String author = parts[1].replace("&#44;", ",");
                    String isbn = parts[2];
                    boolean available = Boolean.parseBoolean(parts[3]);
                    String borrowedBy = parts.length > 4 && !parts[4].isEmpty() ? parts[4] : null;
                    
                    Book book = new Book(title, author, isbn);
                    if (!available && borrowedBy != null) {
                        book.borrowBook(borrowedBy);
                    }
                    
                    books.add(book);
                    bookLookup.put(title, book);
                }
            }
        }
    }
    
    /**
     * Load members from file
     */
    private void loadMembersFromFile() throws IOException {
        File file = new File("data/members.txt");
        if (!file.exists()) {
            System.out.println("Members file not found. Starting with empty member collection.");
            return;
        }
        
        members.clear();
        memberLookup.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#") || line.trim().isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String type = parts[0];
                    String id = parts[1];
                    String name = parts[2].replace("&#44;", ",");
                    String email = parts[3];
                    String phone = parts[4];
                    String extraInfo = parts[5].replace("&#44;", ",");
                    int borrowedBooks = parts.length > 6 ? Integer.parseInt(parts[6]) : 0;
                    
                    Person member = null;
                    
                    if ("Student".equals(type)) {
                        String[] extra = extraInfo.split("\\|");
                        String course = extra.length > 0 ? extra[0] : null;
                        int year = extra.length > 1 ? Integer.parseInt(extra[1]) : 0;
                        
                        member = new Student(id, name, email, course, year);
                        // Set borrowed books count
                        for (int i = 0; i < borrowedBooks; i++) {
                            ((Student) member).borrowBook();
                        }
                    } else if ("Staff".equals(type)) {
                        String[] extra = extraInfo.split("\\|");
                        String department = extra.length > 0 ? extra[0] : null;
                        String position = extra.length > 1 ? extra[1] : null;
                        
                        member = new Staff(id, name, email, department, position);
                        // Set borrowed books count
                        for (int i = 0; i < borrowedBooks; i++) {
                            ((Staff) member).borrowBook();
                        }
                    }
                    
                    if (member != null) {
                        if (phone != null && !phone.isEmpty()) {
                            member.setPhoneNumber(phone);
                        }
                        members.add(member);
                        memberLookup.put(id, member);
                    }
                }
            }
        }
    }
    
    /**
     * Load transactions from file
     */
    private void loadTransactionsFromFile() throws IOException {
        File file = new File("data/transactions.txt");
        if (!file.exists()) {
            System.out.println("Transactions file not found. Starting with empty transaction history.");
            return;
        }
        
        transactionHistory.clear();
        sortedTransactions.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#") || line.trim().isEmpty()) continue;
                
                Transaction transaction = Transaction.fromFileFormat(line);
                if (transaction != null) {
                    transactionHistory.add(transaction);
                    sortedTransactions.add(transaction);
                }
            }
        }
    }
    
    // ==================== REPORTING SYSTEM ====================
    
    /**
     * Generate and save comprehensive library report
     */
    public boolean generateLibraryReport(String reportFileName) {
        try {
            File reportsDir = new File("reports");
            if (!reportsDir.exists()) {
                reportsDir.mkdir();
            }
            
            try (PrintWriter writer = new PrintWriter(new FileWriter("reports/" + reportFileName))) {
                writer.println("=".repeat(60));
                writer.println("               LIBRARY MANAGEMENT REPORT");
                writer.println("=".repeat(60));
                writer.println("Generated: " + java.time.LocalDateTime.now());
                writer.println("Library: " + libraryName);
                writer.println();
                
                // Library Statistics
                writer.println("LIBRARY STATISTICS:");
                writer.println("-".repeat(30));
                writer.printf("Total Books: %d%n", getTotalBooks());
                writer.printf("Total Members: %d%n", getTotalMembers());
                writer.printf("Total Transactions: %d%n", getTransactionCount());
                
                int availableBooks = 0;
                int borrowedBooks = 0;
                for (Book book : books) {
                    if (book.isAvailable()) availableBooks++;
                    else borrowedBooks++;
                }
                writer.printf("Available Books: %d%n", availableBooks);
                writer.printf("Borrowed Books: %d%n", borrowedBooks);
                writer.printf("Students: %d%n", countStudents());
                writer.printf("Staff: %d%n", countStaff());
                writer.println();
                
                // Books Report
                writer.println("BOOKS INVENTORY:");
                writer.println("-".repeat(30));
                for (Book book : books) {
                    String status = book.isAvailable() ? "Available" : "Borrowed by " + book.getBorrowedBy();
                    writer.printf("• %s by %s [%s] - %s%n", 
                        book.getTitle(), book.getAuthor(), book.getIsbn(), status);
                }
                writer.println();
                
                // Members Report
                writer.println("MEMBERS DIRECTORY:");
                writer.println("-".repeat(30));
                for (Person member : members) {
                    writer.printf("• %s (%s) - %s - %s - Books: %d/%d%n",
                        member.getName(), member.getId(), member.getPersonType(),
                        member.getEmail(), getBorrowedBooksCount(member.getId()), member.getMaxBooks());
                }
                writer.println();
                
                // Recent Transactions
                writer.println("RECENT TRANSACTIONS:");
                writer.println("-".repeat(30));
                int transactionCount = 0;
                for (Transaction transaction : sortedTransactions) {
                    if (transactionCount >= 10) break; // Show only last 10
                    writer.printf("[%s] %s: %s %s '%s'%n",
                        transaction.getTransactionId(),
                        transaction.getFormattedTimestamp(),
                        transaction.getMemberName(),
                        transaction.getType().toString().toLowerCase() + "ed",
                        transaction.getBookTitle());
                    transactionCount++;
                }
                
                writer.println();
                writer.println("=".repeat(60));
                writer.println("             END OF REPORT");
                writer.println("=".repeat(60));
            }
            
            System.out.println("✓ Library report generated successfully: reports/" + reportFileName);
            return true;
            
        } catch (IOException e) {
            System.out.println("Error generating report: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error during report generation: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Generate borrowing activity report
     */
    public boolean generateBorrowingReport(String memberId, String reportFileName) {
        try {
            Person member = findMemberById(memberId);
            
            File reportsDir = new File("reports");
            if (!reportsDir.exists()) {
                reportsDir.mkdir();
            }
            
            try (PrintWriter writer = new PrintWriter(new FileWriter("reports/" + reportFileName))) {
                writer.println("=".repeat(60));
                writer.println("           MEMBER BORROWING ACTIVITY REPORT");
                writer.println("=".repeat(60));
                writer.println("Generated: " + java.time.LocalDateTime.now());
                writer.println("Member: " + member.getName() + " (" + member.getId() + ")");
                writer.println("Type: " + member.getPersonType());
                writer.println("Email: " + member.getEmail());
                writer.println();
                
                writer.println("BORROWING STATISTICS:");
                writer.println("-".repeat(30));
                writer.printf("Current Borrowed Books: %d/%d%n", 
                    getBorrowedBooksCount(memberId), member.getMaxBooks());
                
                // Count member's transactions
                int borrowCount = 0;
                int returnCount = 0;
                for (Transaction transaction : transactionHistory) {
                    if (transaction.getMemberId().equals(memberId)) {
                        if (transaction.isBorrowTransaction()) borrowCount++;
                        else returnCount++;
                    }
                }
                writer.printf("Total Books Borrowed: %d%n", borrowCount);
                writer.printf("Total Books Returned: %d%n", returnCount);
                writer.println();
                
                writer.println("TRANSACTION HISTORY:");
                writer.println("-".repeat(30));
                for (Transaction transaction : sortedTransactions) {
                    if (transaction.getMemberId().equals(memberId)) {
                        writer.printf("[%s] %s: %s '%s' (%s)%n",
                            transaction.getTransactionId(),
                            transaction.getFormattedTimestamp(),
                            transaction.getType().toString(),
                            transaction.getBookTitle(),
                            transaction.getBookIsbn());
                    }
                }
                
                writer.println();
                writer.println("=".repeat(60));
                writer.println("             END OF REPORT");
                writer.println("=".repeat(60));
            }
            
            System.out.println("✓ Borrowing report generated for " + member.getName() + 
                             ": reports/" + reportFileName);
            return true;
            
        } catch (MemberNotFoundException e) {
            System.out.println("Cannot generate report: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Error generating borrowing report: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error during borrowing report generation: " + e.getMessage());
            return false;
        }
    }
}