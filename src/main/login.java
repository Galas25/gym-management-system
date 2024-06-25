package main;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import static main.gym.adminLogin;
import static main.gym.gym;
import static main.gym.signUp;



public class login extends gym{

    login(){
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
                        new userPage(member.getUsername()); 
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

}