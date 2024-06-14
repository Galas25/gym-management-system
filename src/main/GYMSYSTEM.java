package main;
import javax.swing.JFrame;
public class GYMSYSTEM {
  String username = "admin";
  String password = "test123";

  public static void main(String[] args) {
    GYMSYSTEM g1 = new GYMSYSTEM();
    //       g1.login();
    //       g1.signUp();
    dashboard dash = new dashboard();

  }

  public void login() {
    JFrame f1 = new JFrame();
    f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f1.setSize(500, 540);
    f1.setTitle("Login");
    f1.setLocationRelativeTo(null);
    f1.toFront();
    f1.setVisible(true);
  }

  public void signUp() {

    JFrame f1 = new JFrame();
    f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f1.setSize(500, 540);
    f1.setTitle("Sign-up");
    f1.setLocationRelativeTo(null);
    f1.toFront();
    f1.setVisible(true);

  }

}