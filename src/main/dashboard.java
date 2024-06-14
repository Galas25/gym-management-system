package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


public class dashboard {
  JFrame dh = new JFrame();

  public dashboard() {
    SwingUtilities.invokeLater(() -> {
      createUI();
    });
  }

  private void createUI() {
    JPanel p1 = new JPanel(), p2 = new JPanel(), p3 = new JPanel();
    dh.setSize(1000, 600);
    dh.setTitle("DASHBOARD");
    dh.setLocationRelativeTo(null);
    dh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    dh.setResizable(false);

    p1.setBounds(200, 0, 800, 70);
    p1.setBackground(Color.decode("#366f9a"));
    p1.setLayout(null);
    dh.add(p1);

    p2.setBounds(0, 0, 200, 600);
    p2.setBackground(Color.decode("#4dbbdc"));
    p2.setLayout(null);
    dh.add(p2);

    p3.setBounds(200, 70, 800, 600);
    p3.setBackground(new Color(231, 231, 231));
    dh.add(p3);
    p3.setLayout(null);

    // Define named colors

    // Create JLabel
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
    
    JLabel nameLbl = new JLabel("Admin",JLabel.CENTER);
    nameLbl.setBounds(50, 120, 100, 100);
    nameLbl.setFont(new java.awt.Font("Inter SemiBold", Font.BOLD, 20)); // NOI18N
    p2.add(nameLbl);
    
    JLabel logout = new JLabel("LOG-OUT", JLabel.CENTER);
    logout.setForeground(Color.white);
    logout.setBounds(60, 480, 90, 30);
    Border line = BorderFactory.createLineBorder(Color.black);
    logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
    logout.setBackground(Color.decode("#366f9a"));
    logout.setOpaque(true);
    // Load ImageIcon
    logout.setBorder(line);
    ImageIcon logoutIcon = new ImageIcon(
      new ImageIcon(dashboard.class.getResource("exit.png"))
      .getImage()
      .getScaledInstance(20, 20, Image.SCALE_SMOOTH)
    );
    logout.setIcon(logoutIcon);
    p2.add(logout);
    
    
    labelBtn("Members", p2, 200);
    labelBtn("User Logs", p2, 290);
    labelBtn("Equipments", p2, 380);

    dh.setVisible(true); // Set frame visible after all components are added
  }

  public void labelBtn(String Label, JPanel panel, int height) {

    JLabel l1 = new JLabel(Label, SwingConstants.CENTER);
    l1.setFont(new Font("Inter Medium", Font.BOLD, 14));
    l1.setBounds(10, height, 180, 60);
    l1.setBackground(Color.decode("#4daddc"));
    l1.setOpaque(true);
    l1.setFocusable(true);
    l1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    panel.add(l1);
  }

}