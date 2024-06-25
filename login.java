package logn;
import javax.swing.*;
import java.awt.*;


public class login {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Login");
        JLabel loginkey = new JLabel("Login");
        JPanel panel = new JPanel();
        JLabel welcome = new JLabel("Welcome to our Gym Management");
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        JButton login = new JButton("Login");
        JButton register = new JButton("Register");
        JLabel sign = new JLabel("Don't have an account?? ");
        JLabel user = new JLabel("Username: ");
        JLabel pass = new JLabel("Password: ");

        welcome.setFont(new Font("Times new Roman", Font.PLAIN, 20));
        loginkey.setFont(new Font("Times new Roman", Font.PLAIN, 50));

        loginkey.setForeground(Color.BLUE);
        //keys
        loginkey.setBounds(500, 0 , 300 ,60);
        user.setBounds(450, 120, 400, 40);
        pass.setBounds(450, 170, 400, 40);
        sign.setBounds(450, 300, 300, 50);


        //Fields
        username.setBounds(450, 150, 200, 30);
        password.setBounds(450, 200, 200, 30);

        //buttons
        login.setBounds(450, 250, 100, 30);
        register.setBounds(610, 310, 100,30);

        panel.add(welcome);
        panel.setBackground(Color.BLUE);


        panel.setBounds(0,0 ,350,500);
        frame.setBounds(500,200,800, 500);


        frame.add(user);
        frame.add(pass);


        frame.add(loginkey);
        frame.add(username);
        frame.add(password);
        frame.add(login);
        frame.add(sign);
        frame.add(register);
        frame.add(panel);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
