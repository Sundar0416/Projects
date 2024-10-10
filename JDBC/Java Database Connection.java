package RAM;

import java.sql.*;
import java.util.Scanner;

public class connection {
    private static String URL;                               
    private static String TABLE;
    private static String USER;
    private static String PASSWORD;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the database name,table name,  username, and password
        System.out.print("Enter database name in mysql: ");
        String databaseName = scanner.nextLine();
        URL = "jdbc:mysql://localhost:3306/" + databaseName;
        System.out.print("Enter table name in database: ");
        TABLE = scanner.nextLine();
        System.out.print("Enter database username: ");
        USER = scanner.nextLine();
        System.out.print("Enter database password: ");
        PASSWORD = scanner.nextLine();

        System.out.println("*Initial table present in database*");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE);
            while (rs.next()) {
                System.out.println(rs.getInt("bookid") + "\t" + rs.getString("title") + "\t" + rs.getString("author"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("-----------------------------------");

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            while (true) {
                System.out.println("***Choose an operation***");
                System.out.println("1. Insert\n2. Update\n3. Delete\n4. Exit");
                System.out.println("Select -> 1 or 2 or 3 or 4");
                int choice = scanner.nextInt();

                if (choice == 4) {
                    System.out.println("Exiting...");
                    break;
                }

                switch (choice) {
                    case 1:
                        performInsertOperation(scanner, connection);
                        break;
                    case 2:
                        performUpdateOperation(scanner, connection);
                        break;
                    case 3:
                        performDeleteOperation(scanner, connection);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid choice.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void performInsertOperation(Scanner scanner, Connection connection) throws SQLException {
        System.out.println("***Enter book data for insertion***");
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        if (isBookIdExists(connection, bookId)) {
            System.out.println("The book ID already exists, please enter a unique one.");
            return;
        }
        scanner.nextLine();  // Consume the newline left-over
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        insertBook(connection, bookId, title, author);
        displayAllBooks(connection);
    }

    private static boolean isBookIdExists(Connection connection, int bookId) throws SQLException {
        String cq = "SELECT COUNT(*) FROM book WHERE bookid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(cq)) {
            preparedStatement.setInt(1, bookId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void performUpdateOperation(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline left-over
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new author: ");
        String newAuthor = scanner.nextLine();

        updateBook(connection, bookId, newTitle, newAuthor);
        displayAllBooks(connection);
    }

    private static void performDeleteOperation(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter book ID to delete: ");
        int bookId = scanner.nextInt();

        deleteBook(connection, bookId);
        displayAllBooks(connection);
    }

    private static void insertBook(Connection connection, int bookId, String title, String author) throws SQLException {
        String insertQuery = "INSERT INTO book (bookid, title, author) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, bookId);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book inserted successfully.");
            } else {
                System.out.println("Failed to insert book.");
            }
        }
    }

    private static void updateBook(Connection connection, int bookId, String newTitle, String newAuthor) throws SQLException {
        String updateQuery = "UPDATE book SET title = ?, author = ? WHERE bookid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newTitle);
            preparedStatement.setString(2, newAuthor);
            preparedStatement.setInt(3, bookId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("Failed to update book. Book ID may not exist.");
            }
        }
    }

    private static void deleteBook(Connection connection, int bookId) throws SQLException {
        String deleteQuery = "DELETE FROM book WHERE bookid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, bookId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Failed to delete book. Book ID may not exist.");
            }
        }
    }

    private static void displayAllBooks(Connection connection) throws SQLException  {
        System.out.println("\n***Updated Book Table***");
        System.out.println("------------------------");
        String selectQuery = "SELECT * FROM book";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int bookId = rs.getInt("bookid");
                String title = rs.getString("title");
                String author = rs.getString("author");

                System.out.println(bookId + "\t" + title + "\t" + author);
                         }
            System.out.print("___________________________________");
            System.out.println();
            System.out.println();
        }
    }
}


