package GUI;

import commonconstant.commonconstant;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Library extends JFrame {
    private JTextField bookField;
    private JButton borrowButton;
    private JList<String> resultList;
    private DefaultListModel<String> listModel;
    private String currentUsername;

    public Library() {
        super("Library Book Showcase"); // Set the title of the JFrame
        setSize(1300, 690); // Set the size of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the close operation
        setLayout(null); // Use null layout for absolute positioning
        addGuiComponent(this); // Pass the current JFrame to the method
        setVisible(true); // Make the frame visible
    }

    private void addGuiComponent(JFrame frame) {
        // Set up the image icon as a background
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/user.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 720);
        frame.add(losLabel);

<<<<<<< HEAD
        // Search result field
        JTextField searchField = new JTextField();
        searchField.setBounds(400, 20, 400, 30);
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(searchField);

        // Borrow book button
        JButton borrowButton = new JButton("Borrow Book");
        borrowButton.setBounds(820, 20, 120, 30);
        borrowButton.setFont(new Font("Arial", Font.BOLD, 14));
        borrowButton.setBackground(new Color(200, 200, 200)); // Light gray background
        borrowButton.setOpaque(true);
        borrowButton.setContentAreaFilled(true);
        borrowButton.setBorderPainted(true);
        borrowButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Borrow Book functionality coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE));
        frame.add(borrowButton);

        // Add buttons to the frame
=======
        // Add text field for search with a visible background
        JTextField searchField = new JTextField();
        searchField.setBounds(620, 45, 250, 30); // Set position and size of text field
        searchField.setBackground(Color.WHITE); // Set background color to white (or any color you prefer)
        searchField.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border around the text field
        searchField.setCaretColor(Color.BLACK); // Ensure caret is visible
        searchField.setFocusable(true); // Make sure it can be focused
        frame.add(searchField);

        JLabel bookTextLabel = new JLabel();
        bookTextLabel.setBounds(620, 45, 250, 30);
        bookTextLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bookTextLabel.setForeground(Color.BLACK);
        bookTextLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(bookTextLabel);

        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultList.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(resultList);
        scrollPane.setBounds(620, 45, 250, 30);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        resultList.setBackground(new Color(0, 0, 0, 0));
        resultList.setVisible(true);
        add(scrollPane);



        borrowButton = new JButton("Borrow Book");
        borrowButton.setBounds(917, 45, 150, 30);
        borrowButton.setOpaque(false); // Make the button non-opaque
        borrowButton.setContentAreaFilled(false); // Disable background filling
        borrowButton.setBorderPainted(false); // Remove the border
        borrowButton.setForeground(Color.BLACK); // Set the text color
        borrowButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font
        add(borrowButton);


        // Add buttons to the frame for each book
>>>>>>> upstream/main
        frame.add(createBookButton("Show Book Details", 173, 100,
                "Title: The Badboy and The Tomboy\n"
                        + "Author: B. N. Toler\n"
                        + "Rating: 4.5/5\n"
                        + "Description: This story follows the journey of Jace, a rebellious bad boy, and Riley, a tomboyish girl.\n"
                        + "They navigate friendship and love amidst unexpected challenges."));
        frame.add(createBookButton("Show Book Details", 173, 300,
                "Title: My Wattpad Love\n"
                        + "Author: Ariana Godoy\n"
                        + "Rating: 4.8/5\n"
                        + "Description: Diana and Andrés meet through Wattpad, a popular writing platform.\n"
                        + "Their connection grows as they explore both online and offline relationships."));
        // Add more book buttons as needed, following the same structure
    }

    private JButton createBookButton(String text, int x, int y, String bookDetails) {
        JButton bookButton = new JButton(text);
        bookButton.setBounds(x, y, 111, 165);
        bookButton.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        bookButton.setOpaque(false);
        bookButton.setContentAreaFilled(false);
        bookButton.setBorderPainted(false);
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        bookButton.addActionListener(e -> JOptionPane.showMessageDialog(null, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE));
        return bookButton;
    }
    private void handleSearchBooks() {
        String query = bookField.getText().trim();
        listModel.clear();

        if (query.isEmpty()) {
            resultList.setVisible(false);
            borrowButton.setVisible(false);
            return;
        }

        java.util.List<String> matches = BookSearch.searchBooks(query);

        if (matches.isEmpty()) {
            listModel.addElement("No matching books found.");
            resultList.setVisible(true);
            borrowButton.setVisible(false);
        } else {
            for (String book : matches) {
                listModel.addElement(book);
            }
            resultList.setVisible(true);
            borrowButton.setVisible(true);
        }
    }

    private void handleBorrowBook() {
        String selectedBook = resultList.getSelectedValue();

        if (selectedBook == null || selectedBook.equals("No matching books found.")) {
            JOptionPane.showMessageDialog(this, "Please select a valid book to borrow.");
            return;
        }

        boolean success = BookSearch.requestBorrow(currentUsername, selectedBook);
        if (success) {
            JOptionPane.showMessageDialog(this, "Borrow request added for: " + selectedBook);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to request borrow. Book might already be in your queue.");
        }
    }

    private void showMyBooks() {
        java.util.List<String> borrowedBooks = BookSearch.getBorrowedBooks(currentUsername);
        List<String> queuedBooks = BookSearch.getBorrowQueue(currentUsername);

        StringBuilder message = new StringBuilder();
        message.append("Your Borrowed Books:\n");
        if (borrowedBooks.isEmpty()) {
            message.append("- No books currently borrowed\n");
        } else {
            borrowedBooks.forEach(book -> message.append("- ").append(book).append("\n"));
        }

        message.append("\nYour Requested Books:\n");
        if (queuedBooks.isEmpty()) {
            message.append("- No pending requests\n");
        } else {
            queuedBooks.forEach(book -> message.append("- ").append(book).append("\n"));
        }

        JTextArea textArea = new JTextArea(message.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "My Books", JOptionPane.INFORMATION_MESSAGE);
    }


    public static void main(String[] args) {
        new Library();

    }
}
