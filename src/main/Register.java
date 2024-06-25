package main;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Register extends JFrame {
    private JLabel titleLabel, firstNameLabel, lastNameLabel, emailLabel, passwordLabel, usernameLabel, confirmPasswordLabel;
    private JTextField firstNameField, lastNameField, emailField, usernameField;
    private JPasswordField passwordField, confirmPasswordField;
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

        // Panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(LIGHT_BLUE);
        formPanel.setBorder(new LineBorder(BLUE, 2, true));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Padding

        // Title Label
        titleLabel = new JLabel("Signup Form", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(DARK_BLUE);
        titleLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // First Name Field
        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        firstNameLabel.setForeground(DARK_BLUE);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        formPanel.add(firstNameLabel, constraints);

        firstNameField = new JTextField(20);
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        firstNameField.setBorder(new LineBorder(BLUE, 1, true));
        constraints.gridx = 1;
        constraints.gridy = 0;
        formPanel.add(firstNameField, constraints);

        // Last Name Field
        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        lastNameLabel.setForeground(DARK_BLUE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        formPanel.add(lastNameLabel, constraints);

        lastNameField = new JTextField(20);
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        lastNameField.setBorder(new LineBorder(BLUE, 1, true));
        constraints.gridx = 1;
        constraints.gridy = 1;
        formPanel.add(lastNameField, constraints);

        // Email Field
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLabel.setForeground(DARK_BLUE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        formPanel.add(emailLabel, constraints);

        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setBorder(new LineBorder(BLUE, 1, true));
        constraints.gridx = 1;
        constraints.gridy = 2;
        formPanel.add(emailField, constraints);

        // Username Field
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(DARK_BLUE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        formPanel.add(usernameLabel, constraints);

        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBorder(new LineBorder(BLUE, 1, true));
        constraints.gridx = 1;
        constraints.gridy = 3;
        formPanel.add(usernameField, constraints);

        // Password Field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(DARK_BLUE);
        constraints.gridx = 0;
        constraints.gridy = 4;
        formPanel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(new LineBorder(BLUE, 1, true));
        constraints.gridx = 1;
        constraints.gridy = 4;
        formPanel.add(passwordField, constraints);

        // Confirm Password Field
        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        confirmPasswordLabel.setForeground(DARK_BLUE);
        constraints.gridx = 0;
        constraints.gridy = 5;
        formPanel.add(confirmPasswordLabel, constraints);

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 16));
        confirmPasswordField.setBorder(new LineBorder(BLUE, 1, true));
        constraints.gridx = 1;
        constraints.gridy = 5;
        formPanel.add(confirmPasswordField, constraints);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Signup Button
        signupButton = new JButton("Signup");
        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
        signupButton.setBackground(BLUE);
        signupButton.setForeground(WHITE);
        signupButton.setBorder(new LineBorder(DARK_BLUE, 2, true));
        signupButton.setPreferredSize(new Dimension(150, 40));
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() &&
                    !username.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
                    
                    // Perform validation (e.g., check if passwords match)
                    if (!password.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(Register.this,
                                "Passwords do not match!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    new Subscription(firstName+" "+lastName, email, username, password);
                } else {
                    JOptionPane.showMessageDialog(Register.this,
                            "Please fill in all fields!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
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

}
