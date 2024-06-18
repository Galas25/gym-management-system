package main;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class gym {
  String username = "admin";
  String password = "test123";
  static GymManagement gym = new GymManagement(5);
  public static void main(String[] args) {
    
    gym.addMember(new Members("Alex", LocalDate.now().minusMonths(1), LocalDate.now()));
    gym.addMember(new Members("esd", LocalDate.now().minusMonths(2), LocalDate.now().plusMonths(1)));
    gym.addMember(new Members("esd", LocalDate.now(), LocalDate.now().plusMonths(1)));
    gym.addMember(new Members("esd", LocalDate.now(), LocalDate.now().plusMonths(1)));
    gym.addMember(new Members("esd", LocalDate.now(), LocalDate.now().plusMonths(1)));
    counter.setCount(gym.printAllMembers());
    

    gym.checkInMember(1, LocalDateTime.now());
    gym.checkInMember(2, LocalDateTime.now());
    gym.checkInMember(3, LocalDateTime.now());
 
    
    
    new dashboard();
    
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
    login.setBounds(150, 250, 200, 50);
    frame.add(login);

    signup.setText("Sign up");
    signup.setBounds(150, 320, 200, 50);
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
        frame.dispose();
        new dashboard();

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
    JLabel label = new JLabel();

    
    frame.setTitle("Sign Up");
    frame.setLocationRelativeTo(null);
    frame.setLayout(null);

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
