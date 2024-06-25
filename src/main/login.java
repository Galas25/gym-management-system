package main;

import java.awt.Color;
import java.awt.Cursor;
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
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import static main.gym.gym;



public class login extends gym{

    login(){
        JFrame frame = new JFrame("Login");
        JLabel loginkey = new JLabel("Login");
        JPanel panel = new JPanel();
        JLabel welcome = new JLabel("Welcome to our Gym Management");
        welcome.setForeground(Color.white);
//        welcome.setHorizontalAlignment(JLabel.CENTER);
//        welcome.setVerticalAlignment(JLabel.CENTER);
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        JButton login = new JButton("Login");
//        JButton register = new JButton("Register");
        JLabel sign = new JLabel("<HTML><U>Don't have an account?</U></HTML>");
        JLabel user = new JLabel("Username: ");
        JLabel pass = new JLabel("Password: ");

        welcome.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        loginkey.setFont(new Font("Times new Roman", Font.PLAIN, 50));

        loginkey.setForeground(Color.BLUE);
        //keys
        loginkey.setBounds(500, 0 , 300 ,60);
        user.setBounds(450, 120, 400, 40);
        pass.setBounds(450, 170, 400, 40);
        sign.setBounds(475, 230, 300, 50);
        sign.setFont(new Font("Serif", Font.PLAIN, 16));
        sign.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sign.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 frame.dispose();
                 new Register();
             }
        });
        
        loginkey.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new adminLogin();
                
            }
        });


        //Fields
        username.setBounds(450, 150, 200, 30);
        password.setBounds(450, 200, 200, 30);

        //buttons
        login.setBounds(500, 280, 100, 30);
//        register.setBounds(610, 310, 100,30);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              String Username = username.getText();
              String Password = new String(password.getPassword());
              boolean found = false;
                      for (Members member : gym.getMembers()) {
                           if (member != null) {
                              if (member.getUsername().equals(Username) && member.getPassword().equals(Password)) {
                                 
                              found = true;
                              JOptionPane.showMessageDialog(frame, "Successfully logged in", "Information", JOptionPane.INFORMATION_MESSAGE);
                              frame.dispose();                               
                              new userPage(member.getUsername(), member); 
                              break;
                              }
                          }


                      }
                      if (username.getText().equals("") || username.getText() == null || password.getPassword().equals("") || password.getPassword() == null) {
                          JOptionPane.showMessageDialog(frame, "Please fill out the fields first", "Warning", JOptionPane.WARNING_MESSAGE);

                      } else if (!found){
                          JOptionPane.showMessageDialog(frame, "Account does not exist", "Warning", JOptionPane.WARNING_MESSAGE);
                      }


              }
          });
    

        panel.add(welcome);
        panel.setBackground(Color.BLUE);


        panel.setBounds(0,0 ,350,500);
        frame.setBounds(500,200,800, 500);


        frame.add(user);
        frame.add(pass);


        frame.add(loginkey);
        frame.add(username);
        frame.add(password);
        frame.add(sign);
        frame.add(login);
        
//        frame.add(register);
        frame.add(panel);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}