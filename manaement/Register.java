package manaement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Register extends JFrame {
    private JLabel titleLabel, nameLabel, emailLabel, passwordLabel;
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton signupButton;


    private final Color LIGHT_BLUE = new Color(173, 216, 230);
    private final Color BLUE = new Color(100, 149, 237);
    private final Color DARK_BLUE = new Color(0, 0, 139);
    private final Color WHITE = Color.WHITE;

    public Register() {
        setTitle("Signup Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null); 


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));


        titleLabel = new JLabel("Signup Form", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(DARK_BLUE);
        titleLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);


        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(LIGHT_BLUE);
        formPanel.setBorder(new LineBorder(BLUE, 2, true));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Padding

        // Name field
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setForeground(DARK_BLUE);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        formPanel.add(nameLabel, constraints);

        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setBorder(new LineBorder(BLUE, 1, true));
        constraints.gridx = 1;
        constraints.gridy = 0;
        formPanel.add(nameField, constraints);

        // Email field
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLabel.setForeground(DARK_BLUE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        formPanel.add(emailLabel, constraints);

        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setBorder(new LineBorder(BLUE, 1, true));
        constraints.gridx = 1;
        constraints.gridy = 1;
        formPanel.add(emailField, constraints);

        // Password field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(DARK_BLUE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        formPanel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(new LineBorder(BLUE, 1, true));
        constraints.gridx = 1;
        constraints.gridy = 2;
        formPanel.add(passwordField, constraints);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Signup button at the bottom
        signupButton = new JButton("Signup");
        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
        signupButton.setBackground(BLUE);
        signupButton.setForeground(WHITE);
        signupButton.setBorder(new LineBorder(DARK_BLUE, 2, true));
        signupButton.setPreferredSize(new Dimension(150, 40));
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                new Subscription(name, email);
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(WHITE);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        buttonPanel.add(signupButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
                new Register();

    }
}
