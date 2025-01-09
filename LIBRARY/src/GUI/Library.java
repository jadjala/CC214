package GUI;

import javax.swing.*;
import java.awt.*;

public class Library extends JFrame {

    public Library() {
        super("Borrow Book"); // Set the title of the JFrame
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
        bookButton.setBackground(new Color(0, 0, 0, 0)); // Set transparent background
        bookButton.setOpaque(false); // Make the button fully transparent
        bookButton.setContentAreaFilled(false); // Remove background color
        bookButton.setBorderPainted(false); // Remove the border
        bookButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        bookButton.addActionListener(e -> JOptionPane.showMessageDialog(null, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE));
        return bookButton;
    }

    public static void main(String[] args) {
        new Library(); // Run the Library GUI
    }
}
