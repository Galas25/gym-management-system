package noo;

import Gym.GymManagement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class frame {

    frame() {
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
        login.setBounds(200, 250, 90, 30);
        frame.add(login);


        signup.setText("Sign up");
        signup.setBounds(200, 300, 90, 30);
        frame.add(signup);
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new signup();

            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
new GymManagement(500);

            }
        });


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(450, 180, 500, 540);

    }

}
