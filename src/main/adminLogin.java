/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static main.gym.password;
import static main.gym.username;

/**
 *
 * @author Server
 */
public class adminLogin {
    
    adminLogin(){
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
}
