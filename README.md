📚 Library Management System (LMS)
A simple console-based Library Management System written in Java. This project was built as part of a Java lab assignment to practice Object-Oriented Programming (OOP) concepts, collections (ArrayList), and basic CRUD-style operations.

✨ Features
📖 Add Book – Register a new book with a title and author.

👤 Add Member – Register a new library member by name.

📥 Borrow Book – Members can borrow available books (with availability and borrow-limit checks).

📤 Return Book – Members can return books they have borrowed.

🔍 Search Member – View a member's details and the list of books they have borrowed.

📚 Show Books – Display all books with their ID, title, author, and availability.

👥 Show Members – Display all members with their ID, name, and borrow count.

🚪 Exit – Quit the application gracefully.

🧠 Concepts Demonstrated
Classes and Objects (Book, Member, MemberBook, Library)

Encapsulation and constructors

static counters for auto-generated IDs (b0, b1, ... for books; m0, m1, ... for members)

ArrayList collections and nested loops

Ternary operator and conditional logic

Menu-driven console interaction with Scanner

🏗️ Project Structure
Class	Responsibility
Book	Represents a book with ID, title, author, and availability.
Member	Represents a library member with ID, name, and borrow count.
MemberBook	Links a member to a borrowed book (junction record).
Library	Manages books, members, and borrowing/returning logic.
LMS	Entry point with the interactive menu loop.
🚀 How to Run
Clone the repository

bash
git clone https://github.com/Motiumeta/LibraryManagementSystem.git
cd LibraryManagementSystem
Compile the Java file

bash
javac LMS.java
Run the program

bash
java LMS
Requires Java 8 or higher.

🖥️ Sample Menu
text
 == LIBRARY ===
1) Add book
2) Add member
3) Borrow
4) Return
5) Search
6) Show Books
7) Show Members
0) Exit
>
📌 Notes & Limitations
Books and members are stored in memory only — data is lost when the program exits.

ID generation uses static counters, so IDs reset every run.

The borrow limit check uses borrowedCount > 3, meaning a member can borrow up to 4 books before being blocked.

The search() method inside Library is currently a placeholder and not used (searching is handled via showMemberDetail).

🔮 Possible Improvements
Add persistent storage (file or database).

Implement book search by title/author.

Add input validation for empty fields.

Allow removing books and members.

Add unit tests.

👤 Author
Motiumeta
GitHub: @Motiumeta
