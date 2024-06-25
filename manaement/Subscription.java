package manaement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Subscription extends JFrame {
    private JPanel mainPanel, buttonPanel;
    private JLabel titleLabel;
    private int selectedMonths = 0;
    private String selectedPaymentMethod = "";
    private String subscriptionFeatures = "";
    private String username;
    private String email;
    private LocalDate currentDate;
    private LocalDate dueDate;

    public Subscription(String username, String email) {
        this.username = username;
        this.email = email;

        setTitle("Choose Subscription");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        titleLabel = new JLabel("Select Subscription Duration", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel welcomeLabel = new JLabel("Welcome, " + username + " (" + email + ")", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        welcomeLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30)); // Adding gaps between buttons
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBorder(new EmptyBorder(30, 20, 30, 20));

        addButton("1 Month");
        addButton("3 Months");
        addButton("5 Months");

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    private void addButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setPreferredSize(new Dimension(180, 50));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentDate = LocalDate.now();
                switch (text) {
                    case "1 Month":
                        selectedMonths = 1;
                        dueDate = currentDate.plusMonths(1);
                        subscriptionFeatures = "Price: $1000\nFree 1 month for new users";
                        break;
                    case "3 Months":
                        selectedMonths = 3;
                        dueDate = currentDate.plusMonths(3);
                        subscriptionFeatures = "Price: $3000\nUnlimited Sauna Use";
                        break;
                    case "5 Months":
                        selectedMonths = 5;
                        dueDate = currentDate.plusMonths(5);
                        subscriptionFeatures = "Price: $5000\nUnlimited Sauna Use\nFree Supplement";
                        break;
                }
                displayPaymentOptions();
            }
        });
        buttonPanel.add(button);
    }

    private void displayPaymentOptions() {
        mainPanel.removeAll();

        JLabel paymentLabel = new JLabel("Select Payment Method", JLabel.CENTER);
        paymentLabel.setFont(new Font("Arial", Font.BOLD, 20));
        paymentLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        mainPanel.add(paymentLabel, BorderLayout.NORTH);

        JPanel paymentButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        paymentButtonPanel.setBackground(Color.LIGHT_GRAY);
        paymentButtonPanel.setBorder(new EmptyBorder(30, 20, 30, 20));

        addPaymentButton("Gcash", paymentButtonPanel);
        addPaymentButton("Paypal", paymentButtonPanel);
        addPaymentButton("Credit/Debit", paymentButtonPanel);

        mainPanel.add(paymentButtonPanel, BorderLayout.CENTER);

        validate();
        repaint();
    }

    private void addPaymentButton(String text, JPanel panel) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setPreferredSize(new Dimension(180, 50));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedPaymentMethod = text;
                displaySummary();
            }
        });
        panel.add(button);
    }

    private void displaySummary() {
        mainPanel.removeAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String formattedCurrentDate = currentDate.format(formatter);
        String formattedDueDate = dueDate.format(formatter);

        JLabel summaryLabel = new JLabel("<html>You have selected a " + selectedMonths + " months subscription with " + selectedPaymentMethod + " payment method.<br><br>" + subscriptionFeatures.replace("\n", "<br>") + "<br><br>Start Date: " + formattedCurrentDate + "<br>Due Date: " + formattedDueDate + "</html>", JLabel.CENTER);
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        summaryLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.add(summaryLabel, BorderLayout.CENTER);

        JButton restartButton = new JButton("Back");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 18));
        restartButton.setPreferredSize(new Dimension(180, 50));
        restartButton.setBackground(Color.WHITE);
        restartButton.setFocusPainted(false);
        restartButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedMonths = 0;
                selectedPaymentMethod = "";
                subscriptionFeatures = "";
                mainPanel.removeAll();
                mainPanel.add(titleLabel, BorderLayout.NORTH);
                mainPanel.add(buttonPanel, BorderLayout.CENTER);
                validate();
                repaint();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        buttonPanel.add(restartButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        validate();
        repaint();
    }

    public static void main(String[] args) {

                new Subscription("User", "user@example.com");
            }

}
