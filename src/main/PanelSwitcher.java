package main;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class PanelSwitcher extends JFrame {
    private JLabel[] panels = {new JLabel("Overview"), new JLabel("Members"), new JLabel("User Logs"), new JLabel("Equipments")};
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JLabel panelInUse;
    private Color inUse = Color.BLUE;
    private Color notUsed = Color.LIGHT_GRAY;
    private Border bevel = BorderFactory.createBevelBorder(1);

    public PanelSwitcher() {
        setTitle("Panel Switcher");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1, panels.length));
        for (JLabel label : panels) {
            labelPanel.add(label);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    switchPanel((JLabel) e.getSource());
                }
            });
        }

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(new OverviewPanel(), "Overview");
        cardPanel.add(new MembersPanel(), "Members");
        cardPanel.add(new UserLogsPanel(), "User Logs");
        cardPanel.add(new EquipmentsPanel(), "Equipments");

        add(labelPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    private void switchPanel(JLabel selectedLabel) {
        for (JLabel label : panels) {
            if (label == selectedLabel) {
                label.setBackground(inUse);
                label.setForeground(Color.WHITE);
                label.setBorder(bevel);
                cardLayout.show(cardPanel, label.getText());
                panelInUse = label;
            } else {
                label.setBackground(notUsed);
                label.setForeground(Color.BLACK);
                label.setBorder(null);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PanelSwitcher ps = new PanelSwitcher();
            ps.setVisible(true);
        });
    }
}

class OverviewPanel extends JPanel {
    public OverviewPanel() {
        add(new JLabel("Overview Content"));
    }
}

class MembersPanel extends JPanel {
    public MembersPanel() {
        add(new JLabel("Members Content"));
    }
}

class UserLogsPanel extends JPanel {
    public UserLogsPanel() {
        add(new JLabel("User Logs Content"));
    }
}

class EquipmentsPanel extends JPanel {
    public EquipmentsPanel() {
        add(new JLabel("Equipments Content"));
    }
}
