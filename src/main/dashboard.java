package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class dashboard extends gym {
    JFrame dh = new JFrame();
    Color inUse = Color.decode("#366f9a");
    Color notUsed = Color.decode("#4dbbdc");

    JLabel Overview = new JLabel("Overview", SwingConstants.CENTER);
    JLabel Members = new JLabel("Members", SwingConstants.CENTER);
    JLabel User_Logs = new JLabel("User Logs", SwingConstants.CENTER);
    JLabel Equipments = new JLabel("Equipments", SwingConstants.CENTER);
    JLabel panelInUse = Overview;

    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel(cardLayout);

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
        dh.setLayout(null);
        dh.setResizable(false);

        p1.setBounds(200, 0, 800, 70);
        p1.setBackground(Color.decode("#366f9a"));
        p1.setLayout(null);
        dh.add(p1);

        p2.setBounds(0, 0, 200, 570);
        p2.setBackground(Color.decode("#4daddc"));
        p2.setLayout(null);
        dh.add(p2);

        p3.setLayout(new BorderLayout());
        p3.setBounds(200, 70, 800, 500);
        p3.setBackground(new Color(231, 231, 231));
        p3.add(cardPanel, BorderLayout.CENTER);
        dh.add(p3);

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

        JLabel nameLbl = new JLabel("Admin", JLabel.CENTER);
        nameLbl.setBounds(50, 120, 100, 100);
        nameLbl.setFont(new java.awt.Font("Inter SemiBold", Font.BOLD, 20));
        p2.add(nameLbl);

        
        // LOG OUT BUTTON
        
        JLabel logout = new JLabel("LOG-OUT", JLabel.CENTER);
        logout.setForeground(Color.white);
        logout.setBounds(60, 480, 90, 30);
        Border line = BorderFactory.createLineBorder(Color.black);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.setBackground(Color.decode("#366f9a"));
        logout.setOpaque(true);
        logout.setBorder(line);
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "LOG OUT", JOptionPane.YES_NO_OPTION);
                if (choice == 0){
                    dh.dispose();
                    login();
                }
            }
        });
        
        
        ImageIcon logoutIcon = new ImageIcon(
                new ImageIcon(dashboard.class.getResource("exit.png"))
                        .getImage()
                        .getScaledInstance(20, 20, Image.SCALE_SMOOTH)
        );
        logout.setIcon(logoutIcon);
        p2.add(logout);

        JLabel title = new JLabel("Gym Management System");
        title.setBounds(20, -15, 500, 100);
        title.setForeground(Color.white);
        title.setFont(new java.awt.Font("Inter Medium", Font.PLAIN, 20));
        p1.add(title);

        labelBtn(p2, 200, Overview);
        labelBtn(p2, 270, Members);
        labelBtn(p2, 340, User_Logs);
        labelBtn(p2, 410, Equipments);

        Overview.setBackground(inUse);
        Overview.setBorder(BorderFactory.createBevelBorder(1));
        Overview.setForeground(Color.WHITE);

        // Add cards to cardPanel
        cardPanel.add(new OverviewPanel(), "Overview");
        cardPanel.add(new MembersPanel(), "Members");
        cardPanel.add(new UserLogsPanel(), "User Logs");
        cardPanel.add(new EquipmentsPanel(), "Equipments");

        dh.setVisible(true); // Set frame visible after all components are added
    }

    public void labelBtn(JPanel panel, int height, JLabel labelName) {
        labelName.setName(labelName.getText());
        labelName.setFont(new Font("Inter Medium", Font.BOLD, 14));
        labelName.setBounds(20, height, 160, 40);
        labelName.setBackground(Color.decode("#4dbbdc"));
        labelName.setOpaque(true);
        labelName.setFocusable(true);
        labelName.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(labelName);

        JLabel[] panels = {Overview, Members, User_Logs, Equipments};
        Border bevel = BorderFactory.createBevelBorder(1);

        labelName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof JLabel) {
                    for (JLabel panel : panels) {
                        if (e.getComponent() == panel) {
                            panelInUse = panel;
                            panelInUse.setBackground(inUse);
                            panelInUse.setForeground(Color.WHITE);
                            panelInUse.setBorder(bevel);
                            cardLayout.show(cardPanel, panel.getText());
                        } else {
                            panel.setBackground(notUsed);
                            panel.setForeground(Color.BLACK);
                            panel.setBorder(null);
                        }
                    }
                }
            }
        });
    }

    // Example panel classes
    class OverviewPanel extends JPanel {
        public OverviewPanel() {
            setBackground(Color.decode("#E7E7E7"));
            add(new JLabel("Overview Content"));
        }
    }

    class MembersPanel extends JPanel {
        public MembersPanel() {
            setBackground(Color.decode("#E7E7E7"));
            add(new JLabel("Members Content"));
        }
    }

    class UserLogsPanel extends JPanel {
        public UserLogsPanel() {
            setBackground(Color.decode("#E7E7E7"));
            add(new JLabel("User Logs Content"));
        }
    }

    class EquipmentsPanel extends JPanel {
        public EquipmentsPanel() {
            setBackground(Color.decode("#E7E7E7"));
            add(new JLabel("Equipments Content"));
        }
    }
  
}
