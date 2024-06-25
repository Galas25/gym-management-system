package noo;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class signup extends JFrame {


    signup() {

        JLabel label = new JLabel();

        this.setTitle("Sign Up");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

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
                new frame();
            }
        });


        this.add(register);
        this.add(user);
        this.add(username);
        this.add(user_password);
        this.add(password);
        this.setBounds(450, 180, 500, 540);
        this.setVisible(true);

    }
}
