package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
public class gym {
  static String username = "admin";
  static String password = "test123";
  static GymManagement gym = new GymManagement(5);
  static Employees workers = new Employees(5);
  public static void main(String[] args) {
    
    gym.addMember(new Members("Alex", LocalDate.now().minusMonths(1), LocalDate.now(),"Alex","123"));
    gym.addMember(new Members("esd", LocalDate.now(), LocalDate.now().plusMonths(1),"Galas","2555"));
    
    gym.checkInMember(1, LocalDateTime.now());
    gym.checkInMember(2, LocalDateTime.now());
    
    workers.addEmployee(new Employees("Kulash Mithril","General Manager","0932-328-232"));
    workers.addEmployee(new Employees("Smithrianite Jade","Assistant Manager","0932-328-232"));
    
    Employees emp1 = workers.findEmployeeByID(1);
    Employees emp2 = workers.findEmployeeByID(2);
        if (emp1 != null || emp2 != null) {
            emp1.addAttendanceRecord(LocalDateTime.of(2024, 6, 24, 8, 0), LocalDateTime.of(2024, 6, 24, 18, 0), "Morning");
            emp1.addAttendanceRecord(LocalDateTime.of(2024, 6, 25, 8, 0), LocalDateTime.of(2024, 6, 25, 19, 0), "Morning");
            emp2.addAttendanceRecord(LocalDateTime.of(2024, 6, 24, 16, 0), LocalDateTime.of(2024, 6, 25, 2, 0), "Evening");
            emp2.addAttendanceRecord(LocalDateTime.of(2024, 6, 25, 16, 0), LocalDateTime.of(2024, 6, 26, 1, 0), "Evening");
        }

        // Display attendance records for the first employee
        if (emp1 != null || emp2 != null) {
            emp1.displayAttendanceRecords();
        }
    
    new dashboard(username);
    
  }
  
  public static void login() {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JButton login = new JButton();
    JButton signup = new JButton();
    JTextField username = new JTextField();
    JLabel user = new JLabel("Username");
    JPasswordField password = new JPasswordField();
    JLabel user_password = new JLabel("Password");
    user.setBounds(150, 60, 200, 40);
    username.setBounds(150, 100, 200, 40);
    user_password.setBounds(150, 150, 200, 40);
    password.setBounds(150, 200, 200, 40);
    frame.setTitle("Gym Management System");
    frame.setLayout(null);
    frame.add(username);
    frame.add(panel);
    frame.add(user);
    frame.add(user_password);
    frame.add(password);
    label.setText("Welcome to our Gym ");
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setVerticalTextPosition(JLabel.TOP);
    label.setBounds(0, 0, 200, 100);
    label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    panel.setBounds(0, 0, 500, 50);
    panel.add(label);
    login.setText("Login");
    login.setBounds(150, 300, 200, 50);
    frame.add(login);
    
    label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                adminLogin();
                
            }
        });

    JLabel errorLabel = new JLabel();

    signup.setText("Sign up");
    signup.setBounds(150, 370, 200, 50);
    frame.add(signup);
    signup.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        signUp();

      }
    });
    login.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        
        errorLabel.setForeground(Color.red);
        errorLabel.setVisible(false);
        String Username = username.getText();
        String Password = new String(password.getPassword());
        frame.add(errorLabel);
        errorLabel.setBounds(160, 240, 200, 40);
        boolean found = false;
                for (Members member : gym.getMembers()) {
                     if (member != null) {
                        if (member.getUsername().equals(Username) && member.getPassword().equals(Password)) {
                        found = true;
                        frame.dispose();
                        new userPage(member.getMembershipId()); 
                        break;
                        }
                    }
                    

                }
                if (username.getText().equals("") || username.getText() == null || password.getPassword().equals("") || password.getPassword() == null) {
                    errorLabel.setVisible(false);
                    errorLabel.setText("Username or Password is empty");
                    errorLabel.setVisible(true);
                    
                } else if (!found){
                    errorLabel.setText("Wrong password or Username");
                    errorLabel.setVisible(true);
                }
                      
 
        }
    });

    

    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(450, 180, 500, 540);
    frame.setLocationRelativeTo(null);
    frame.toFront();
  }
  
  public static void adminLogin() {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JButton login = new JButton();
    JTextField usernameField = new JTextField();
    JLabel user = new JLabel("Username");
    JPasswordField passwordField = new JPasswordField();
    JLabel user_password = new JLabel("Password");
    user.setBounds(150, 60, 200, 40);
    usernameField.setBounds(150, 100, 200, 40);
    user_password.setBounds(150, 150, 200, 40);
    passwordField.setBounds(150, 200, 200, 40);
    frame.setTitle("Gym Management System");
    frame.setLayout(null);
    frame.add(usernameField);
    frame.add(panel);
    frame.add(user);
    frame.add(user_password);
    frame.add(passwordField);
    label.setText("ADMIN LOGIN");
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setVerticalTextPosition(JLabel.TOP);
    label.setBounds(0, 0, 200, 100);
    label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    panel.setBounds(0, 0, 500, 50);
    panel.add(label);
    login.setText("Login");
    login.setBounds(150, 300, 200, 50);
    frame.add(login);
    
    JLabel errorLabel = new JLabel();

      login.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        
        errorLabel.setForeground(Color.red);
        errorLabel.setVisible(false);
        frame.add(errorLabel);
        errorLabel.setBounds(160, 240, 200, 40);
        String userInput = usernameField.getText();
        String passwordInput = String.valueOf(passwordField.getPassword());
       
        
            if (userInput.equals("") || userInput == null || passwordInput.equals("") || passwordInput == null) {
                errorLabel.setVisible(false);
                errorLabel.setText("Username or Password is empty");
                errorLabel.setVisible(true);
            }
            
            if(userInput.equals(username) && passwordInput.equals(password)){
                JOptionPane.showMessageDialog(frame, "Login Successfully");
                frame.dispose();
                new dashboard(username);
            }
      
      }
    });

    

    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(450, 180, 500, 540);
    frame.setLocationRelativeTo(null);
    frame.toFront();
  }
  public static void signUp() {
    JFrame frame = new JFrame();
    frame.setTitle("Sign Up");
    frame.setLocationRelativeTo(null);
    frame.setLayout(null);
    JLabel label = new JLabel("Register as a Member");
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setVerticalTextPosition(JLabel.TOP);
    label.setBounds(160, -20 , 200, 100);
    label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    frame.add(label);
    
    JButton register = new JButton("Register");

    JPasswordField password = new JPasswordField();

    JLabel user = new JLabel("Username");

    JLabel user_password = new JLabel("Password");

    JTextField username = new JTextField();

    //  user's username
    user.setBounds(150, 60, 200, 40);
    username.setBounds(150, 100, 200, 40);

    //user's password
    user_password.setBounds(150, 150, 200, 40);
    password.setBounds(150, 200, 200, 40);

    register.setBounds(150, 250, 200, 40);

    register.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        login();
      }
    });
    // Add WindowListener to trigger function on close
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                login();
            }
        });
    frame.add(register);
    frame.add(user);
    frame.add(username);
    frame.add(user_password);
    frame.add(password);
    frame.setBounds(450, 180, 500, 540);
    frame.setLocationRelativeTo(null);
    frame.toFront();
    frame.setVisible(true);

  }
  
   
}
