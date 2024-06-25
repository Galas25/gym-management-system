/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


public class userPage extends gym{
    JFrame mainFrame = new JFrame();
    
    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    private Members member;
    private String memberUsername;
    private String firstName;
    private String lastName;
    private String contact;
    private boolean checkedIn;
    
    public userPage(String memberUsername, Members members) {
        SwingUtilities.invokeLater(() -> {
            this.memberUsername = memberUsername;
            this.member = members;
            
            String fullNameofMember = member.getName();
            String[] nameParts = fullNameofMember.split(" ");
            this.firstName = capitalizeFirstLetter(nameParts[0]);
            this.lastName = capitalizeFirstLetter(nameParts[nameParts.length - 1]);;
            this.contact = members.getContact();
            
            
        createUI();
        });
    }
    
    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str; // Return null or empty string as is
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    public void createUI(){
        // Background design for Userpage
        JPanel p1 = new JPanel(), p2 = new JPanel(), p3 = new JPanel();
        mainFrame.setTitle("Userpage");
        mainFrame.setSize(1000,600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
    
        p1.setBounds(200, 0, 800, 70);
        p1.setBackground(Color.decode("#366f9a"));
        p1.setLayout(null);
        mainFrame.add(p1);

        p2.setBounds(0, 0, 200, 570);
        p2.setBackground(Color.decode("#4daddc"));
        p2.setLayout(null);
        mainFrame.add(p2);

        p3.setLayout(new BorderLayout());
        p3.setBounds(200, 70, 800, 500);
        p3.setBackground(new Color(231, 231, 231));
        p3.add(cardPanel, BorderLayout.CENTER);
        mainFrame.add(p3);

        JLabel profile = new JLabel();
        profile.setBounds(0, -20, 200, 200);

        // Load ImageIcon
        ImageIcon scaledIcon = new ImageIcon(
                new ImageIcon(dashboard.class.getResource("user.png"))
                        .getImage()
                        .getScaledInstance(200, 150, Image.SCALE_SMOOTH)
        );
        profile.setIcon(scaledIcon);
        p2.add(profile);

        JLabel nameLbl = new JLabel(firstName, JLabel.CENTER);
        nameLbl.setBounds(50, 120, 100, 100);
        nameLbl.setFont(new java.awt.Font("Inter SemiBold", Font.BOLD, 30));
        p2.add(nameLbl);

        JButton checkIn = new JButton("Check-In");
        checkIn.setBounds(50, 200, 100, 40);
        
        checkIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(checkedIn){
                
                    JOptionPane.showMessageDialog(mainFrame, "You already are checked in, please check out first.", "Warning", JOptionPane.WARNING_MESSAGE);
                
                }else{
            
                    member.checkInTime = LocalDateTime.now();
                    JOptionPane.showMessageDialog(mainFrame, "Successfully checked in", "Information", JOptionPane.INFORMATION_MESSAGE);
                    checkedIn = true;
                
                }
                
                
                
            }
        });
        p2.add(checkIn);
        
        JButton checkOut = new JButton("Check-Out");
        checkOut.setBounds(50, 250, 100, 40);
        p2.add(checkOut);

        checkOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(checkedIn){
                
                    JOptionPane.showMessageDialog(mainFrame, "Sucessfully checked out", "Information", JOptionPane.INFORMATION_MESSAGE);
                    member.checkOutTime = LocalDateTime.now();
                    checkedIn = false;
                
                }else{
                    JOptionPane.showMessageDialog(mainFrame, "You are not checked in, check in first.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                
                
                
            }
        });
        

        JLabel logout = new JLabel("LOG-OUT", JLabel.CENTER);
        logout.setForeground(Color.white);
        logout.setBounds(60, 480, 90, 30);
        Border line = BorderFactory.createLineBorder(Color.black);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.setBackground(Color.decode("#366f9a"));
        logout.setOpaque(true);
        logout.setBorder(line);
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "LOG OUT", JOptionPane.YES_NO_OPTION);
                if (choice == 0){
                    mainFrame.dispose();
                    login();
                }
            }
        });
        
        ImageIcon logoutIcon = new ImageIcon(
                new ImageIcon(dashboard.class.getResource("exit.png"))
                        .getImage()
                        .getScaledInstance(20, 20, Image.SCALE_SMOOTH)
        );
        logout.setIcon(logoutIcon);
        p2.add(logout);
    
        JLabel title = new JLabel("GYM NAME");
        title.setBounds(20, -15, 500, 100);
        title.setForeground(Color.white);
        title.setFont(new java.awt.Font("Inter Medium", Font.PLAIN, 20));
        p1.add(title);

        OverviewPanel mainUI = new OverviewPanel();
        cardPanel.add(mainUI);

        mainFrame.setVisible(true); // Set frame visible after all components are added
    }

    private String expDateCalculator(Members member){
    
        LocalDate expDate = member.getExpDate();
        
        // Current date
        LocalDate currentDate = member.getStartDate();
        
        // Calculate days left until the expiration date
        long daysLeft = ChronoUnit.DAYS.between(currentDate, expDate);
        
        // Calculate months and days left until the expiration date
        Period period = Period.between(currentDate, expDate);
        int monthsLeft = period.getMonths();
        int daysInMonthLeft = period.getDays();
        
        // Display the results
        // System.out.println("Days left until expiring: " + daysLeft);
        String message = monthsLeft + " months,  " + daysInMonthLeft + " days";
        return message;
    }
    
    class OverviewPanel extends JPanel {
        public OverviewPanel() {
            setBackground(Color.decode("#ebebeb"));
            setLayout(null);
            JPanel mainPanel = new JPanel();
            mainPanel.setBounds(20, 20, 742, 200);
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setLayout(null);

            JLabel dumbbell = new JLabel();
            dumbbell.setBounds(490, 0, 307, 307);

            ImageIcon scaledImg = new ImageIcon(
                new ImageIcon(userPage.class.getResource("dumbbell.png"))
                .getImage()
                .getScaledInstance(307, 307, Image.SCALE_SMOOTH)
            );

            dumbbell.setIcon(scaledImg);
            mainPanel.add(dumbbell);
            
            add(mainPanel);
            
            

            JLabel welcome = new JLabel("Welcome!");
            welcome.setBounds(20, -10, 400, 100);
            welcome.setFont(new Font("Segoe UI", Font.PLAIN, 30));
            mainPanel.add(welcome);

            // JLabel user = new JLabel("(Last Name)" + ", " + memberUsername);
            JLabel username = new JLabel(firstName);//Change "Last Name"
            username.setBounds(20, 40, 400, 100);
            username.setFont(new Font("Segoe UI", Font.BOLD, 55));
            mainPanel.add(username);

            JLabel firstname = new JLabel(lastName);
            firstname.setBounds(20, 100, 400, 100);
            firstname.setFont(new Font("Segoe UI", Font.BOLD, 45));
            mainPanel.add(firstname);

            JPanel bottomLeft = new JPanel();
            bottomLeft.setBounds(20, 240, 300, 230);
            bottomLeft.setBackground(Color.WHITE);
            bottomLeft.setLayout(null);

            JLabel subscription = new JLabel("Gym Subscription Status: ");
            subscription.setBounds(15, -20, 300, 90);
            subscription.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            bottomLeft.add(subscription);

            JLabel status = new JLabel("Status: ");
            status.setBounds(15, 10, 300, 90);
            status.setFont(new Font("Segoe UI", Font.BOLD, 20));
            bottomLeft.add(status);

            String membershipStatus = member.getStartDate().isBefore(member.getExpDate()) ? "Active" : "Expired";
            JLabel currentStatus = new JLabel(membershipStatus);
            currentStatus.setBounds(85, 10, 300, 90);
            currentStatus.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            bottomLeft.add(currentStatus);

            JLabel expiresIn = new JLabel("Expires In: " + "");
            expiresIn.setBounds(15, 40, 300, 90);
            expiresIn.setFont(new Font("Segoe UI", Font.BOLD, 20));
            bottomLeft.add(expiresIn);

            JLabel expireDate = new JLabel(expDateCalculator(member));
            expireDate.setBounds(120, 40, 300, 90);
            expireDate.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            bottomLeft.add(expireDate);

            add(bottomLeft);


            JPanel bottomRight = new JPanel();
            bottomRight.setBounds(340, 240, 420, 230);
            bottomRight.setBackground(Color.WHITE);
            bottomRight.setLayout(null);

            JLabel userInfo = new JLabel("User Information ");
            userInfo.setBounds(15, -20, 300, 90);
            userInfo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            bottomRight.add(userInfo);

            JLabel name = new JLabel("Name: ");
            name.setBounds(15, 10, 300, 90);
            name.setFont(new Font("Segoe UI", Font.BOLD, 20));
            bottomRight.add(name);

            JLabel fullName = new JLabel(lastName + ", " + firstName);
            fullName.setBounds(85, 10, 300, 90);
            fullName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            bottomRight.add(fullName);

            JLabel contactInfo = new JLabel("Contact: ");
            contactInfo.setBounds(15, 40, 300, 90);
            contactInfo.setFont(new Font("Segoe UI", Font.BOLD, 20));
            bottomRight.add(contactInfo);

            JLabel contactNumber = new JLabel(contact);
            contactNumber.setBounds(100, 40, 300, 90);
            contactNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            bottomRight.add(contactNumber);

            add(bottomRight);

            
        }
    }
}
