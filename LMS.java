import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String id;
    String title;
    String author;
    Boolean available;
    static int bookCount = 0;

    String getId() {
        return id;
    }

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.id = "b" + bookCount;
        this.available = true;
        bookCount++;
    }
}

class Member {
    String id, name;
    static int memberCount = 0;
    int borrowedCount = 0;

    public String getId() {
        return id;
    }

    public Member(String name) {
        this.id = "m" + memberCount;
        this.name = name;
        memberCount++;
    }
}

class MemberBook {
    String mid, bid;

    public MemberBook(String mid, String bid) {
        this.mid = mid;
        this.bid = bid;
    }
}

class Library {
    ArrayList<Book> books = new ArrayList();
    ArrayList<Member> memebers = new ArrayList();
    ArrayList<MemberBook> memberBook = new ArrayList<>();

    void showMembers() {
        for (Member m : memebers) {
            System.out.println("================================");
            System.out.println("Id: " + m.getId());
            System.out.println("Name: " + m.name);
            System.out.println("Borrow Count: " + m.borrowedCount);
            System.out.println("================================");

        }
    }

    void showBooks() {
        for (Book b : books) {
            System.out.println("================================");
            System.out.print("| Id: " + b.getId());
            System.out.print(" | Title: " + b.title);
            System.out.print(" | Author: " + b.author);
            System.out.println(" | Available: " + (b.available ? "Yes" : "No"));

            System.out.println("================================");

        }
    }

    void showMemberDetail(String mid) {
        Member member = null;
        for (Member m : memebers) {
            if (m.id.equals(mid)) {
                member = m;
            }
        }
        if (member == null) {
            System.out.println("No member exist with that id.");
            return;
        }

        System.out.println("================================");
        System.out.println("Id: " + member.getId());
        System.out.println("Name: " + member.name);
        System.out.println("Borrow Count: " + member.borrowedCount);
        System.out.println("================================");
        ArrayList<MemberBook> myMB = new ArrayList<>();
        for (MemberBook mb : memberBook) {
            if (mb.mid.equals(mid)) {
                myMB.add(mb);
            }
        }
        ArrayList<Book> myBooks = new ArrayList<>();
        for (Book b : books) {
            for (MemberBook mb : myMB) {
                if (mb.bid.equals(b.getId())) {
                    myBooks.add(b);
                }
            }
        }
        if (myBooks.size() == 0) {
            System.out.println("No book borrowed");
            return;
        }

        System.out.println("Borrowed Books");
        int c = 1;
        for (Book b : myBooks) {
            System.out.println("Book" + c + " Title: " + b.title);
            c++;
        }

    }

    void addBook(Book book) {
        books.add(book);
    }

    void addMember(Member m) {
        memebers.add(m);
    }

    ArrayList<Book> booksBorrowedByMember(String mid) {
        ArrayList<Book> myBooks = new ArrayList<>();

        for (Book b : books) {
            for (MemberBook mb : memberBook) {
                if (mb.bid.equals(b.id) && mb.mid.equals(mid)) {
                    myBooks.add(b);
                }
            }
        }

        for (Book b : myBooks) {
            System.out.println("================================");
            System.out.print("| Id: " + b.getId());
            System.out.print(" | Title: " + b.title);
            System.out.println(" | Author: " + b.author);
            System.out.println("================================");

        }

        return myBooks;
    }

    void borrow(String mid, String bid) {
        Boolean found = false;
        for (Book b : books) {
            for (Member m : memebers) {
                if (b.getId().equals(bid) && m.getId().equals(mid)) {
                    found = true;
                    if (!b.available) {
                        System.out.println("The book is not available now");
                        break;
                    } else if (m.borrowedCount > 3) {
                        System.out.println("The member has excided his/her limit");
                        break;
                    }
                    b.available = false;
                    m.borrowedCount++;
                    memberBook.add(new MemberBook(mid, bid));
                    System.out.println("Book borrowed successfully");
                }
            }
        }
        if (!found) {
            System.out.println("Book with that mid or bid isn't available");
        }
    }

    void giveBack(String mid, String bid) {
        Boolean found = false;
        for (Book b : books) {
            for (Member m : memebers) {
                if (b.getId().equals(bid) && m.getId().equals(mid)) {
                    found = true;
                    if (b.available && m.borrowedCount == 0)
                        break;
                    b.available = true;
                    m.borrowedCount--;
                    MemberBook memberB = null;
                    for (MemberBook mb : memberBook) {
                        if (mb.mid.equals(mid) && mb.bid.equals(bid)) {
                            memberB = mb;
                        }
                    }
                    if (memberB != null) {
                        memberBook.remove(memberB);
                        System.out.println("Book Returned successfully");
                        return;
                    }
                    System.out.println();
                }
            }
        }
        if (!found) {
            System.out.println("The info provided is invalid");
        }
    }

    void search() {

    }
}

public class LMS {

    public static void main(String[] args) {
        Library lib = new Library();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(" == LIBRARY ===\r\n" + //
                    "1) Add book \n" +
                    "2) Add member \r\n" + //
                    "3) Borrow \r\n" + //
                    "4) Return\r\n" + //
                    "5) Search \r\n" + //
                    "6) Show Books \r\n" + //
                    "7) Show Members \r\n" + //
                    "0) Exit");
            System.out.print("> ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.println("Add Book");
                String title, author;
                System.out.print("Book Title: ");
                title = scanner.nextLine();
                System.out.print("Book Author: ");
                author = scanner.nextLine();

                Book book = new Book(title, author);
                lib.addBook(book);
            } else if (choice.equals("2")) {
                System.out.println("Add Member");
                String name;
                System.out.print("Member Name: ");
                name = scanner.nextLine();

                Member member = new Member(name);
                lib.addMember(member);
            } else if (choice.equals("3")) {
                if (lib.memebers.size() == 0) {
                    System.out.println("No Members");
                    continue;
                }
                if (lib.books.size() == 0) {
                    System.out.println("No Books");
                    continue;
                }
                System.out.println("BORROW BOOK");
                lib.showMembers();
                System.out.print("Enter member id: ");
                String mid = scanner.nextLine();
                lib.showBooks();
                System.out.print("Enter book id: ");
                String bid = scanner.nextLine();
                lib.borrow(mid, bid);

            } else if (choice.equals("4")) {
                if (lib.memebers.size() == 0) {
                    System.out.println("No Members");
                    continue;
                }
                if (lib.books.size() == 0) {
                    System.out.println("No Books");
                    continue;
                }
                System.out.println("RETURN BOOK");
                lib.showMembers();
                System.out.print("Enter member id: ");
                String mid = scanner.nextLine();
                if (lib.booksBorrowedByMember(mid) == null) {
                    System.out.println("Thie member has not borrowed any book to return");
                }
                System.out.print("Enter book id: ");
                String bid = scanner.nextLine();
                lib.giveBack(mid, bid);
            } else if (choice.equals("5")) {
                System.out.println("Search Member");
                lib.showMembers();
                System.out.print("Enter Member Id: ");
                String mid = scanner.nextLine();
                lib.showMemberDetail(mid);
                System.out.print("Enter to continue ");
                scanner.nextLine();

            } else if (choice.equals("6")) {

                if (lib.books.size() == 0) {
                    System.out.println("No Books");
                    continue;
                }
                System.out.println("ALL BOOKS");
                lib.showBooks();
            } else if (choice.equals("7")) {

                if (lib.memebers.size() == 0) {
                    System.out.println("No Members");
                    continue;
                }
                System.out.println("ALL MEMBERS");
                lib.showMembers();
            } else if (choice.equals("0")) {
                break;
            }
        }
    }
}
