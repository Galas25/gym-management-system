package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import static main.gym.gym;
import static main.gym.workers;

public class dashboard extends gym {
    JFrame dh = new JFrame();
    Color inUse = Color.decode("#366f9a");
    Color notUsed = Color.decode("#4dbbdc");

    JLabel Overview = new JLabel("Overview", SwingConstants.CENTER);
    JLabel Members = new JLabel("Members", SwingConstants.CENTER);
    JLabel User_Logs = new JLabel("Employee Logs", SwingConstants.CENTER);
    JLabel Equipments = new JLabel("Equipments", SwingConstants.CENTER);
    JLabel panelInUse = Overview;

    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    static String username;
    public dashboard(String username1) {
        SwingUtilities.invokeLater(() -> {
            username = username1;
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

        JLabel nameLbl = new JLabel(username, JLabel.CENTER);
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
                if (choice == 0) {
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
        cardPanel.add(new EmployeeLogs(), "Employee Logs");
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

        JLabel[] panels = {
            Overview,
            Members,
            User_Logs,
            Equipments
        };
        Border bevel = BorderFactory.createBevelBorder(1);

        labelName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof JLabel) {
                    for (JLabel panel: panels) {
                        if (e.getComponent() == panel) {
                            reloadPanel(panel.getText()); // Call the reload method here
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
    private void reloadPanel(String panelName) {
        switch (panelName) {
        case "Overview":
            OverviewPanel overviewPanel = new OverviewPanel();
            cardPanel.add(overviewPanel, "Overview");
            overviewPanel.revalidate();
            overviewPanel.repaint();
            break;
        case "Members":
            MembersPanel membersPanel = new MembersPanel();
            cardPanel.add(membersPanel, "Members");
            membersPanel.revalidate();
            membersPanel.repaint();
            break;
        case "Employee Logs":
            EmployeeLogs userLogsPanel = new EmployeeLogs();
            cardPanel.add(userLogsPanel, "Employee Logs");
            userLogsPanel.revalidate();
            userLogsPanel.repaint();
            break;
        case "Equipments":
            EquipmentsPanel equipmentsPanel = new EquipmentsPanel();
            cardPanel.add(equipmentsPanel, "Equipments");
            equipmentsPanel.revalidate();
            equipmentsPanel.repaint();
            break;
        }
    }

    // Example panel classes
    public void infoPanel(JPanel panel, int margin, String message, String num) {
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(margin, 240, 200, 230);
        p2.setBackground(Color.WHITE);
        JLabel title = new JLabel(message);
        title.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        title.setBounds(10, -30, 200, 100);
        JLabel number = new JLabel(num);
        number.setFont(new Font("Open Sans", Font.BOLD, 80));
        number.setBounds(0, 0, 200, 230);
        number.setHorizontalAlignment(JLabel.CENTER);
        number.setVerticalAlignment(JLabel.CENTER);
        p2.add(number);
        p2.add(title);
        panel.add(p2);
    }
    class OverviewPanel extends JPanel {
        

        public OverviewPanel() {
            setBackground(Color.decode("#ebebeb"));
            setLayout(null);
            JPanel p1 = new JPanel();
            p1.setBounds(20, 20, 500, 200);
            p1.setBackground(Color.WHITE);
            p1.setLayout(null);
            JLabel welcome = new JLabel("Welcome,");
            welcome.setBounds(20, -20, 400, 100);
            welcome.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            p1.add(welcome);

            JLabel name = new JLabel(username);
            name.setBounds(20, 20, 400, 100);
            name.setFont(new Font("Segoe UI", Font.BOLD, 62));
            p1.add(name);

            JLabel admin = new JLabel("Admin");
            admin.setBounds(20, 65, 400, 100);
            admin.setFont(new Font("Segoe UI", Font.BOLD, 20));
            p1.add(admin);

            JLabel cogwheel = new JLabel();
            cogwheel.setBounds(250, 0, 307, 307);

            // Load ImageIcon
            ImageIcon scaledIcon = new ImageIcon(
                new ImageIcon(dashboard.class.getResource("cogs.png"))
                .getImage()
                .getScaledInstance(307, 307, Image.SCALE_SMOOTH)
            );
            cogwheel.setIcon(scaledIcon);
            p1.add(cogwheel);

            JLabel logout = new JLabel("Logout", JLabel.CENTER);
            logout.setForeground(Color.white);
            logout.setBounds(20, 170, 70, 15);
            Border line = BorderFactory.createLineBorder(Color.black);
            logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
            logout.setBackground(Color.decode("#366f9a"));
            logout.setOpaque(true);
            logout.setBorder(line);
            logout.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "LOG OUT", JOptionPane.YES_NO_OPTION);
                    if (choice == 0) {
                        dh.dispose();
                        login();
                    }
                }
            });

            ImageIcon logoutIcon = new ImageIcon(
                new ImageIcon(dashboard.class.getResource("exit.png"))
                .getImage()
                .getScaledInstance(15, 15, Image.SCALE_SMOOTH)
            );
            logout.setIcon(logoutIcon);
            p1.add(logout);

            JPanel p2 = new JPanel();
            p2.setLayout(null);
            p2.setBounds(540, 20, 225, 200);
            p2.setBackground(Color.white);
            JLabel title = new JLabel("Employees");
            title.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            title.setBounds(10, -30, 200, 100);
           
            JLabel number = new JLabel(String.valueOf(counter.getEmployeeCount()));
            
            number.setFont(new Font("Open Sans", Font.BOLD, 80));
            number.setBounds(0, 0, 225, 200);
            number.setHorizontalAlignment(JLabel.CENTER);
            number.setVerticalAlignment(JLabel.CENTER);
            p2.add(number);
            p2.add(title);
            add(p2);

            add(p1);
            infoPanel(this, 20, "Total Members", String.valueOf(gym.getCount()));
            infoPanel(this, 240, "Active Members", String.valueOf(gym.activeMembers()));
            infoPanel(this, 460, "Check-Ins Today", String.valueOf(gym.checkinToday()));

            
        }
    }
    static class PaddedCellRenderer extends DefaultTableCellRenderer {
        private int topPadding;
        private int bottomPadding;

        public PaddedCellRenderer(int topPadding, int bottomPadding) {
            this.topPadding = topPadding;
            this.bottomPadding = bottomPadding;
            setOpaque(true); // Must do this for background to show up.
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Set padding for top and bottom
            ((JComponent) c).setBorder(BorderFactory.createEmptyBorder(topPadding, 10, bottomPadding, 10));

            return c;
        }
    }

    class MembersPanel extends JPanel {
        DefaultTableModel dtm;

        private void updateTableData() {
            dtm.setRowCount(0); // Clear existing rows
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d, yy", Locale.ENGLISH);
            for (Members member: gym.getMembers()) {
                if (member != null) {
                    String formattedStartDate = member.getStartDate().format(dateTimeFormatter);
                    String formattedExpDate = member.getExpDate().format(dateTimeFormatter);
                    dtm.addRow(new Object[] {
                        member.getMembershipId(), member.getName(),  member.getMembershipType(), formattedStartDate, formattedExpDate, member.username, member.password, member.getContact()
                    });
                }
            }
        }

        public MembersPanel() {
            setBackground(Color.decode("#ebebeb"));
            setLayout(null);

            // Create the main panel to hold the table and its header
            JPanel mainPanel = new JPanel();
            mainPanel.setBounds(20, 20, 745, 250);
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setLayout(new BorderLayout());
            add(mainPanel);

            // Create the table model and the table

            dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // All cells are non-editable
                return false;
            }
            };
            
            dtm.addColumn("ID");
            dtm.addColumn("NAME");
            dtm.addColumn("MEMBERSHIP");
            dtm.addColumn("START");
            dtm.addColumn("EXPIRE");
            dtm.addColumn("USERNAME");
            dtm.addColumn("PASSWORD");
            dtm.addColumn("CONTACT");

            // Example data
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d, yy", Locale.ENGLISH);
            // Replace this with your actual data retrieval logic
            
            for (Members members: gym.getMembers()) {

                if (members != null) {
                    String formattedStartDate = members.getStartDate().format(dateTimeFormatter);
                    String formattedExpDate = members.getExpDate().format(dateTimeFormatter);
                    dtm.addRow(new Object[] {
                        members.getMembershipId(),  members.getName(), members.getMembershipType(), formattedStartDate, formattedExpDate, members.getUsername(), members.getPassword(), members.getContact()
                    });
                }

            }

            JTable table = new JTable(dtm);

            // Set the default cell renderer with padding
            TableCellRenderer cellRenderer = new PaddedCellRenderer(10, 10);
            table.setDefaultRenderer(Object.class, cellRenderer);

            // Customize the table header
            JTableHeader header = table.getTableHeader();
            header.setReorderingAllowed(false); // Disable column dragging
            Font headerFont = new Font("Segoe UI", Font.BOLD, 16);
            Color headerFontColor = Color.WHITE;
            header.setFont(headerFont);
            header.setForeground(headerFontColor);
            header.setBackground(Color.decode("#366f9a"));

            // Disable column resizing
            TableColumnModel columnModel = header.getColumnModel();
            for (int columnIndex = 0; columnIndex < columnModel.getColumnCount(); columnIndex++) {
                TableColumn column = columnModel.getColumn(columnIndex);
                column.setResizable(false);

            }

            // Add the table to a scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            JButton create, update, clear, delete;

            JPanel infoPanel = new JPanel();
            infoPanel.setBounds(20, 290, 625, 180);
            infoPanel.setLayout(new GridBagLayout());
            infoPanel.setBackground(Color.white);
            TitledBorder titledBorder = BorderFactory.createTitledBorder("Information");
            titledBorder.setTitleJustification(TitledBorder.CENTER);
            infoPanel.setBorder(titledBorder);
            add(infoPanel);

            JLabel id = new JLabel("MembershipId");
            JLabel name = new JLabel("Name");
            JLabel error = new JLabel("username or password is incorrect.");
            JLabel startDate = new JLabel("Start Date");
            JLabel expDate = new JLabel("Expire-Date");
            JLabel username = new JLabel("Username");
            JLabel password = new JLabel("Password");
            JLabel membershipTypeLbl = new JLabel("Membership Type");
            JLabel contactNoLbl = new JLabel("Contact Number");

            JTextField idField = new JTextField();
            JTextField nameField = new JTextField();
            JTextField startDateField = new JTextField();
            JTextField expDateField = new JTextField();
            JTextField usernameField = new JTextField();
            JTextField passwordField = new JTextField();
            JComboBox<String> membershipType = new JComboBox<>();
            JTextField contactNoField = new JTextField();

            membershipType.addItem("Regular");
            membershipType.addItem("Silver");
            membershipType.addItem("Gold");

            idField.setEditable(false);
            idField.setFocusable(false);
            idField.setForeground(Color.BLACK);

            error.setForeground(Color.decode("#721c24"));
            error.setBackground(Color.decode("#f8d7da"));
            error.setOpaque(true);
            error.setHorizontalAlignment(JLabel.CENTER);
            error.setVerticalTextPosition(JLabel.CENTER);
            error.setBorder(BorderFactory.createLineBorder(Color.decode("#f5c6cb")));
            error.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            error.setCursor(new Cursor(Cursor.HAND_CURSOR));
            error.setVisible(false);
            startDate.setToolTipText("Format [Year/Month/Day] (2024-06-21)");

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            // Row 1
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            infoPanel.add(id, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 1; // Adjusted to 1
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 0.5;
            infoPanel.add(idField, gbc);

            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            infoPanel.add(name, gbc);

            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.gridwidth = 2; // Adjusted to 2
            gbc.fill = GridBagConstraints.HORIZONTAL;
            infoPanel.add(nameField, gbc);

            // Row 2
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            infoPanel.add(startDate, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1; // Adjusted to 1
            gbc.fill = GridBagConstraints.HORIZONTAL;
            infoPanel.add(startDateField, gbc);

            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            infoPanel.add(expDate, gbc);

            gbc.gridx = 3;
            gbc.gridy = 1;
            gbc.gridwidth = 1; // Adjusted to 1
            gbc.fill = GridBagConstraints.HORIZONTAL;
            infoPanel.add(expDateField, gbc);

            // Row 3
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            infoPanel.add(username, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 1; // Adjusted to 1
            gbc.fill = GridBagConstraints.HORIZONTAL;
            infoPanel.add(usernameField, gbc);

            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            infoPanel.add(password, gbc);

            gbc.gridx = 3;
            gbc.gridy = 2;
            gbc.gridwidth = 1; // Adjusted to 1
            gbc.fill = GridBagConstraints.HORIZONTAL;
            infoPanel.add(passwordField, gbc);

            // Row 4
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            infoPanel.add(membershipTypeLbl, gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 1; // Adjusted to 1
            gbc.fill = GridBagConstraints.HORIZONTAL;
            infoPanel.add(membershipType, gbc);

            gbc.gridx = 2;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            infoPanel.add(contactNoLbl, gbc);

            gbc.gridx = 3;
            gbc.gridy = 3;
            gbc.gridwidth = 1; // Adjusted to 1
            gbc.fill = GridBagConstraints.HORIZONTAL;
            infoPanel.add(contactNoField, gbc);

            // Row 5
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 4; // Combined width for error message
            gbc.fill = GridBagConstraints.HORIZONTAL;
            infoPanel.add(error, gbc);

            membershipType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                String choice = membershipType.getSelectedItem().toString();
                LocalDate start;
                
                switch(choice){
                    case "Regular":
                        if(startDateField.getText().isEmpty()){      
                            startDateField.setText(LocalDate.now().toString());
                            expDateField.setText(LocalDate.now().plusMonths(1).toString());
                        }else{
                            start = parseDate(startDateField.getText());
                            expDateField.setText(start.plusMonths(1).toString());
                        }
                        break;
                    case "Silver":
                        if(startDateField.getText().isEmpty()){      
                            startDateField.setText(LocalDate.now().toString());
                            expDateField.setText(LocalDate.now().plusMonths(3).toString());
                        }else{
                            start = parseDate(startDateField.getText());
                            expDateField.setText(start.plusMonths(3).toString());
                        }
                            
                        break;
                    case "Gold":
                        if(startDateField.getText().isEmpty()){      
                            startDateField.setText(LocalDate.now().toString());
                            expDateField.setText(LocalDate.now().plusMonths(5).toString());
                        }else{
                            start = parseDate(startDateField.getText());
                            expDateField.setText(start.plusMonths(5).toString());
                        }
                        break;  
                    default:
                }   
            }
            });
            
            
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = table.rowAtPoint(e.getPoint());
                    int idfromTable = (int) table.getValueAt(row, 0);
                    Members member = gym.findMemberByID(idfromTable);
                    idField.setText(String.valueOf(member.getMembershipId()));
                    nameField.setText(member.getName());
                    startDateField.setText(String.valueOf(member.getStartDate()));
                    expDateField.setText(String.valueOf(member.getExpDate()));
                    usernameField.setText(member.getUsername());
                    passwordField.setText(member.getPassword());
                    contactNoField.setText(member.getContact());   
                }
            });

            error.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    error.setVisible(false);
                    nameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    startDateField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    expDate.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    usernameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    passwordField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }

            });
            create = new JButton("Create");
            create.setBounds(665, 285, 100, 40);
            create.setBackground(Color.green);
            create.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // use a constructor that allows us to specify a parent and modality
                    JDialog createForm = new JDialog(dh, true);
                    createForm.setSize(300, 300);
                    createForm.setResizable(false);

                    // Set layout manager
                    createForm.setTitle("CREATE FORM");
                    createForm.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.insets = new Insets(5, 5, 5, 5);

                    // Create labels and text fields
                    JLabel nameLbl = new JLabel("Name");
                    JLabel userNameLbl = new JLabel("Username");
                    JLabel passwordLbl = new JLabel("Password");
                    JLabel startDateLbl = new JLabel("Start Date");
                    JLabel expDateLbl = new JLabel("Exp Date");
                    JLabel membershipTypeLbl = new JLabel("Membership Type");
                    JLabel contactLbl = new JLabel("Contact No");
                    JTextField contactField = new JTextField(10);
                    JTextField cnameField = new JTextField(10);
                    JTextField cuserNameField = new JTextField(10);
                    JTextField cpasswordField = new JTextField(10);
                    JTextField cstartDateField = new JTextField(10);
                    JTextField cexpDateField = new JTextField(10);
                    JComboBox<String> membershipType = new JComboBox<>();
                    JButton createBtn = new JButton("Create");
                    JLabel error2 = new JLabel("username or password is incorrect.");

                    membershipType.addItem("Regular");
                    membershipType.addItem("Silver");
                    membershipType.addItem("Gold");
                    cstartDateField.setText(String.valueOf(LocalDate.now()));
                    cexpDateField.setText(String.valueOf(LocalDate.now().plusMonths(1)));
                    
                    membershipType.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String choice = (String) membershipType.getSelectedItem();
                            switch (choice) {
                                case "Regular":
                                    cstartDateField.setText(String.valueOf(LocalDate.now()));
                                    cexpDateField.setText(String.valueOf(LocalDate.now().plusMonths(1)));
                                    break;
                                case "Silver":
                                    cstartDateField.setText(String.valueOf(LocalDate.now()));
                                    cexpDateField.setText(String.valueOf(LocalDate.now().plusMonths(3)));
                                    break;
                                case "Gold":
                                    cstartDateField.setText(String.valueOf(LocalDate.now()));
                                    cexpDateField.setText(String.valueOf(LocalDate.now().plusMonths(5)));
                                    break;
                                default:
                                    cstartDateField.setText("");
                                    cexpDateField.setText("");
                                    break;
                            }
                        }
                    });
                    
                            
                    cstartDateField.setEditable(false);
                    cexpDateField.setEditable(false);
                    cstartDateField.setFocusable(false);
                    cexpDateField.setFocusable(false);

                    error2.setForeground(Color.decode("#721c24"));
                    error2.setBackground(Color.decode("#f8d7da"));
                    error2.setOpaque(true);
                    error2.setBorder(BorderFactory.createLineBorder(Color.decode("#f5c6cb"), 1));
                    error2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    error2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    error2.setVisible(false);
                    error2.setHorizontalAlignment(JLabel.CENTER);
                    error2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            error2.setVisible(false);
                            cnameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                            cuserNameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                            cpasswordField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                        }

                    });
                    cnameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    cuserNameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    cpasswordField.setBorder(BorderFactory.createLineBorder(Color.black, 1));


                    createBtn.setBackground(Color.green);

                    createBtn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            String name = cnameField.getText();
                            String start = String.valueOf(cstartDateField.getText());
                            String exp = String.valueOf(cexpDateField.getText());
                            String username = cuserNameField.getText();
                            String password = cpasswordField.getText();
                            String membership = membershipType.getSelectedItem().toString();
                            String contact = contactField.getText();

                            isnotEmpty(cnameField);
                            isnotEmpty(cuserNameField);
                            isnotEmpty(cpasswordField);
                            isnotEmpty(contactField);

                            if (isnotEmpty(cnameField) && isnotEmpty(cuserNameField) && isnotEmpty(cpasswordField) && isnotEmpty(contactField)) {

                                boolean sameUsername = false;
                                for (Members member: gym.getMembers()) {
                                    if (member != null) {
                                        if (member.getUsername().equals(username)) {
                                            sameUsername = true;
                                        }
                                    }
                                }

                                if (sameUsername) {
                                    error2.setText("existing username");
                                    error2.setVisible(true);
                                } else {
                                    gym.addMember(new Members(name, parseDate(start), parseDate(exp), username, password, membership, contact));
                                    JOptionPane.showMessageDialog(cardPanel, "Sucessfully created a member");
                                    updateTableData();
                                    JTextField[] fields = {
                                        cnameField,
                                        cuserNameField,
                                        usernameField,
                                        cpasswordField,
                                        contactField
                                    };
                                    JTextField[] field = {
                                        idField,
                                        nameField,
                                        startDateField,
                                        expDateField,
                                        usernameField,
                                        passwordField,
                                        contactNoField
                                    };
                                    clear(fields);
                                    membershipType.setSelectedIndex(0);
                                    clear(field);
                                    
                                    createForm.dispose();
                                    
                                }

                            } else {
                                error2.setText("cant be empty");
                                error2.setVisible(true);
                            }

                        }

                    });

                    // Add components to the form using GridBagLayout
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    createForm.add(nameLbl, gbc);

                    gbc.gridx = 1;
                    createForm.add(cnameField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    createForm.add(userNameLbl, gbc);

                    gbc.gridx = 1;
                    createForm.add(cuserNameField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    createForm.add(passwordLbl, gbc);

                    gbc.gridx = 1;
                    createForm.add(cpasswordField, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    createForm.add(contactLbl, gbc);

                    gbc.gridx = 1;
                    createForm.add(contactField, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    createForm.add(membershipTypeLbl, gbc);

                    gbc.gridx = 1;
                    createForm.add(membershipType, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 5;
                    createForm.add(startDateLbl, gbc);

                    gbc.gridx = 1;
                    createForm.add(cstartDateField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 6;
                    createForm.add(expDateLbl, gbc);

                    gbc.gridx = 1;
                    createForm.add(cexpDateField, gbc);

                    gbc.gridy = 7;
                    gbc.gridx = 1;
                    createForm.add(createBtn, gbc);
                    gbc.gridy = 8;
                    gbc.gridx = 1;
                    createForm.add(error2, gbc);
                    // Center the frame

                    createForm.setLocationRelativeTo(null);
                    createForm.setVisible(true);
                }
            });
            add(create);

            update = new JButton("Update");
            update.setBounds(665, 335, 100, 40);
            update.setBackground(Color.decode("#ff8b26"));
            add(update);
            update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String start = String.valueOf(startDateField.getText());
                    String exp = String.valueOf(expDateField.getText());
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    String membership = membershipType.getSelectedItem().toString();
                    String contact = contactNoField.getText();

                    isnotEmpty(nameField);
                    isnotEmpty(startDateField);
                    isnotEmpty(expDateField);
                    isnotEmpty(usernameField);
                    isnotEmpty(passwordField);
                    isnotEmpty(contactNoField);

                    if (isnotEmpty(nameField) && isnotEmpty(startDateField) && isnotEmpty(expDateField) && isnotEmpty(usernameField) && isnotEmpty(passwordField) && isnotEmpty(contactNoField)) {
                        Members member = gym.findMemberByID(Integer.parseInt(idField.getText()));

                        member.setName(name);
                        member.setStartDate(parseDate(start));
                        member.setExpDate(parseDate(exp));
                        member.setUsername(username);
                        member.setPassword(password);
                        member.setMembershipType(membership);
                        member.setContact(contact);
                        JOptionPane.showMessageDialog(cardPanel, "Sucessfully updated a member");
                        JTextField[] fields = {
                            idField,
                            nameField,
                            startDateField,
                            expDateField,
                            usernameField,
                            passwordField,
                            contactNoField
                        };
                        membershipType.setSelectedIndex(0);
                        clear(fields);
                        updateTableData();

                    } else {
                        error.setText("data fields must not be empty or null");
                        error.setVisible(true);
                    }

                }
            });

            delete = new JButton("Delete");
            delete.setBounds(665, 385, 100, 40);
            delete.setBackground(Color.red);
            add(delete);
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (isnotEmpty(idField)) {
                        int choice = JOptionPane.showConfirmDialog(cardPanel, "Are you sure you want to delete user id " + idField.getText() + " ?", "Alert", JOptionPane.YES_NO_OPTION);

                        switch (choice) {

                        case 0 -> {
                            gym.removeMember(Integer.parseInt(idField.getText()));updateTableData();
                            JTextField[] fields = {
                                idField,
                                nameField,
                                startDateField,
                                expDateField,
                                usernameField,
                                passwordField,
                                contactNoField
                                    
                            };
                            membershipType.setSelectedIndex(0);
                            clear(fields);
                        }

                        }
                    }

                }
            });

            clear = new JButton("Clear");
            clear.setBounds(665, 435, 100, 40);
            clear.setBackground(Color.decode("#94a0ff"));
            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField[] fields = {
                        idField,
                        nameField,
                        startDateField,
                        expDateField,
                        usernameField,
                        passwordField,
                        contactNoField
                    };
                    membershipType.setSelectedIndex(0);
                    clear(fields);
                    
                }
            });
            add(clear);
        }

        public static LocalDate parseDate(String dateString) {
            if (dateString == null || dateString.trim().isEmpty()) {
                throw new IllegalArgumentException("Date string cannot be null or empty");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format, expected yyyy-MM-dd: " + dateString);
            }
        }

    }
     private void clearTable(DefaultTableModel tableModel ) {
        // Clear the rows from the DefaultTableModel
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }
    public void clear(JTextField[] fields) {
        for (JTextField e: fields) {
            e.setText("");
        }
    }
    public boolean isnotEmpty(JTextField field) {

        if (field.getText().equals("") || field.getText() == null) {
            field.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            return false;
        }
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return true;
    }

//    public class WideComboBoxRenderer extends DefaultListCellRenderer {
//    @Override
//    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//        label.setPreferredSize(new Dimension(200, label.getPreferredSize().height)); // Set a wider preferred size
//        return label;
//    }
//    }
    
    class EmployeeLogs extends JPanel {
       DefaultTableModel dtm;
       DefaultTableModel model1;
       
       private JTextField nameTextField;
        private JTextField positionTextField;
        private JTextField contactTextField;
        private JTextField hoursTextField;
        private JTextField overtimeTextField;
        private JTextField payRateTextField;
        private JTextField totalTextField;

        private void updateTableData() {
            dtm.setRowCount(0); // Clear existing rows

            for (Employees worker: workers.getEmployee()) {
                if (worker != null) {

                    dtm.addRow(new Object[] {
                        worker.getEmployeeId(), worker.getName(), worker.getPosition(), worker.getContactNo()
                    });
                }
            }

        }
        
        

        public EmployeeLogs() {

            String[] jobTitles = {
            "General Manager",
            "Assistant Manager",
            "Front Desk Receptionist",
            "Fitness Trainer/Instructor",
            "Personal Trainer",
            "Membership Sales Representative",
            "Cleaning Staff/Janitor",
            "Maintenance Technician",
            "Group Fitness Coordinator",
            "Nutritionist/Dietitian",
            "Marketing Manager",
            "Administrative Assistant"
            };
                    
            setBackground(Color.decode("#ebebeb"));
            setLayout(null);

            JLabel idLbl = new JLabel("ID");
            JLabel nameLbl = new JLabel("Name");
            JLabel positionLbl = new JLabel("Position");
            JLabel contactNoLbl = new JLabel("Contact");
            JTextField idField = new JTextField(10);
            JTextField nameField = new JTextField(10);
            JComboBox positionBox = new JComboBox<>(jobTitles);
            JTextField contactNoField = new JTextField(10);
            JButton create = new JButton("Create");
            JButton update = new JButton("Update");
            JButton delete = new JButton("Delete");
            JButton clear = new JButton("Clear");
            JLabel error = new JLabel("must not be empty");

            Font font = new Font("Segoe UI", Font.PLAIN, 12);
            positionBox.setFont(font);

//            positionBox.setRenderer(new WideComboBoxRenderer());

            
            // Create the main panel to hold the table and its header
            JPanel mainPanel = new JPanel();
            mainPanel.setBounds(20, 20, 450, 200);
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setLayout(new BorderLayout());
            add(mainPanel);

            // Create the table model and the table
            
            dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // All cells are non-editable
                return false;
            }
            };
            
            model1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // All cells are non-editable
                return false;
            }
            };
            
            
            dtm.addColumn("ID");
            dtm.addColumn("NAME");
            dtm.addColumn("POSITION");
            dtm.addColumn("CONTACT");

            // Example data

            updateTableData();

            JTable table = new JTable(dtm);

            // Set the default cell renderer with padding
            TableCellRenderer cellRenderer = new PaddedCellRenderer(10, 10);
            table.setDefaultRenderer(Object.class, cellRenderer);

            // Customize the table header
            JTableHeader header = table.getTableHeader();
            header.setReorderingAllowed(false); // Disable column dragging
            Font headerFont = new Font("Segoe UI", Font.BOLD, 16);
            Color headerFontColor = Color.WHITE;
            header.setFont(headerFont);
            header.setForeground(headerFontColor);
            header.setBackground(Color.decode("#366f9a"));

            // Disable column resizing
            TableColumnModel columnModel = header.getColumnModel();
            for (int columnIndex = 0; columnIndex < columnModel.getColumnCount(); columnIndex++) {
                TableColumn column = columnModel.getColumn(columnIndex);
                column.setResizable(false);
            }

            // Add the table to a scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            // set info
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = table.rowAtPoint(e.getPoint());
                    int idfromTable = (int) table.getValueAt(row, 0);
                    Employees employee = workers.findEmployeeByID(idfromTable);

                    // Fetch the employee's attendance records
                    List<Employees.Attendance> attendanceRecords = employee.getAttendanceRecords();

                    // Calculate actual hours worked and overtime hours based on attendance records
                    int hoursWorked = 0;
                    int overtimeHours = 0;

                    for (Employees.Attendance record : attendanceRecords) {
                        if (record.getClockOut() != null) {
                            // Calculate duration between clock in and clock out
                            long durationInMinutes = java.time.Duration.between(record.getClockIn(), record.getClockOut()).toMinutes();
                            // Convert to hours
                            int hours = (int) (durationInMinutes / 60);

                            // Check if the hours fall within regular hours or overtime
                             if (hours <= 8) {
                                hoursWorked += hours;
                            } else {
                                hoursWorked += 8; // Regular hours capped at 8
                                overtimeHours += hours - 8; // Overtime hours start after 8 hours
                            }
                        }
                    }

                    // Set the fields in your UI components
                    idField.setText(String.valueOf(employee.getEmployeeId()));
                    nameField.setText(employee.getName());
                    positionBox.setSelectedItem(employee.getPosition());
                    contactNoField.setText(employee.getContactNo());

                    // Format the calculated values
                    String formattedHoursWorked = String.valueOf(hoursWorked);
                    String formattedOvertimeHours = String.valueOf(overtimeHours);
                    String payRate = String.format("%.2f", employee.calculateHourlyPayRate(employee.getPosition()));

                    // Example calculation of total earnings (you should implement your own logic based on your requirements)
                    double totalEarnings = calculateTotalEarnings(employee);
                    String formattedTotalEarnings = String.format("%.2f", totalEarnings);

                    // Update UI with the formatted values
                    setEmployeeFields(employee.getName(), employee.getPosition(), employee.getContactNo(), formattedHoursWorked, formattedOvertimeHours, payRate, formattedTotalEarnings);

                    // Update table data if needed
                    if (row != -1) {
                        updateTableData1(workers, idfromTable, model1);
                    }
                }
            });

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(null);
            infoPanel.setBounds(480, 20, 290, 200);
            infoPanel.setBackground(Color.WHITE);
            TitledBorder titledBorder = BorderFactory.createTitledBorder("Information");
            titledBorder.setTitlePosition(TitledBorder.TOP);
            titledBorder.setTitleJustification(TitledBorder.CENTER);
            infoPanel.setBorder(titledBorder);

            // fields, labels
            idField.setEditable(false);
            idField.setFocusable(false);
            idLbl.setBounds(20, 20, 100, 30);
            idField.setBounds(80, 20, 100, 30);
            nameLbl.setBounds(20, 60, 100, 30);
            nameField.setBounds(80, 60, 100, 30);
            positionLbl.setBounds(20, 100, 100, 30);
            positionBox.setBounds(80, 100, 100, 30);
            contactNoLbl.setBounds(20, 140, 100, 30);
            contactNoField.setBounds(80, 140, 100, 30);

            error.setBounds(0, 180, 290, 20);
            error.setForeground(Color.decode("#721c24"));
            error.setBackground(Color.decode("#f8d7da"));
            error.setOpaque(true);
            error.setHorizontalAlignment(JLabel.CENTER);
            error.setVerticalTextPosition(JLabel.CENTER);
            error.setBorder(BorderFactory.createLineBorder(Color.decode("#f5c6cb"), 1));
            error.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            error.setCursor(new Cursor(Cursor.HAND_CURSOR));
            error.setVisible(false);

            //buttons 
            create.setBounds(195, 20, 80, 30);
            create.setVisible(true);
            create.setBackground(Color.GREEN);

            update.setBounds(195, 60, 80, 30);
            update.setVisible(true);
            update.setBackground(Color.decode("#ff8b26"));

            delete.setBounds(195, 100, 80, 30);
            delete.setVisible(true);
            delete.setBackground(Color.RED);

            clear.setBounds(195, 140, 80, 30);
            clear.setVisible(true);
            clear.setBackground(Color.decode("#94a0ff"));

            infoPanel.add(idLbl);
            infoPanel.add(idField);
            infoPanel.add(nameLbl);
            infoPanel.add(nameField);
            infoPanel.add(positionLbl);
            infoPanel.add(positionBox);
            infoPanel.add(contactNoLbl);
            infoPanel.add(contactNoField);
            infoPanel.add(create);
            infoPanel.add(update);
            infoPanel.add(delete);
            infoPanel.add(clear);
            infoPanel.add(error);

            create.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // use a constructor that allows us to specify a parent and modality
                    JDialog createForm = new JDialog(dh, true);
                    createForm.setSize(400, 300);
                    createForm.setResizable(false);

                    // Set layout manager
                    createForm.setTitle("CREATE FORM");
                    createForm.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.insets = new Insets(5, 5, 5, 5);

                    String[] jobTitles = {
                    "General Manager",
                    "Assistant Manager",
                    "Front Desk Receptionist",
                    "Fitness Trainer/Instructor",
                    "Personal Trainer",
                    "Membership Sales Representative",
                    "Cleaning Staff/Janitor",
                    "Maintenance Technician",
                    "Group Fitness Coordinator",
                    "Nutritionist/Dietitian",
                    "Marketing Manager",
                    "Administrative Assistant"
                    };
                    
                    // Create labels and text fields
                    JLabel nameLbl = new JLabel("Name");
                    JLabel positionLbl = new JLabel("Position");
                    JLabel contactNoLbl = new JLabel("Contact No.");

                    JTextField cnameField = new JTextField(10);
//                  JTextField tpositionField = new JTextField(10);
                    JComboBox jobComboBox = new JComboBox<>(jobTitles);
                    JTextField ccontactField = new JTextField(10);
                    JButton createBtn = new JButton("Create");
                    JLabel error2 = new JLabel("username or password is incorrect.");

                    error2.setForeground(Color.decode("#721c24"));
                    error2.setBackground(Color.decode("#f8d7da"));
                    error2.setOpaque(true);
                    error2.setBorder(BorderFactory.createLineBorder(Color.decode("#f5c6cb"), 1));
                    error2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    error2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    error2.setVisible(false);
                    error2.setHorizontalAlignment(JLabel.CENTER);
                    error2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            error2.setVisible(false);
                            cnameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                            jobComboBox.setSelectedIndex(0);
                            ccontactField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                        }

                    });
                    cnameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    ccontactField.setBorder(BorderFactory.createLineBorder(Color.black, 1));

                    createBtn.setBackground(Color.green);
                    createBtn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            String name = cnameField.getText();
                            String position = (String) jobComboBox.getSelectedItem();
                            String contact = ccontactField.getText();

                            isnotEmpty(cnameField);
                            isnotEmpty(ccontactField);

                            if (isnotEmpty(cnameField) && !position.isEmpty() && isnotEmpty(ccontactField)) {

                                workers.addEmployee(new Employees(name, position, contact));
                                JOptionPane.showMessageDialog(cardPanel, "Sucessfully created a member");
                                updateTableData();
                                JTextField[] textFields = {nameTextField, positionTextField, contactTextField, hoursTextField, overtimeTextField, payRateTextField, totalTextField};
                                clear(textFields);
                                JTextField[] fields = {
                                    cnameField,
                                    ccontactField,
                                };
                                jobComboBox.setSelectedIndex(0);
                                clearTable(model1);
                                clear(fields);
                                createForm.dispose();
                            } else {
                                error2.setText("cant be empty");
                                error2.setVisible(true);
                            }

                        }

                    });

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    createForm.add(nameLbl, gbc);

                    // Adding cnameField at (1, 0)
                    gbc.gridx = 1;
                    createForm.add(cnameField, gbc);

                    // Adding positionLbl at (0, 1)
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    createForm.add(positionLbl, gbc);

                    // Adding positionField at (1, 1)
                    gbc.gridx = 1;
                    createForm.add(jobComboBox, gbc);

                    // Adding contactNoLbl at (0, 2)
                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    createForm.add(contactNoLbl, gbc);

                    // Adding ccontactField at (1, 2)
                    gbc.gridx = 1;
                    createForm.add(ccontactField, gbc);

                    // Adding createBtn at (1, 3)
                    gbc.gridx = 1;
                    gbc.gridy = 3;
                    createForm.add(createBtn, gbc); // 1,3
                    gbc.gridy = 4;
                    gbc.gridx = 1;
                    createForm.add(error2, gbc); //2,2
                    // Center the frame

                    
                    
                    
                    
                    
                    createForm.setLocationRelativeTo(null);
                    createForm.setVisible(true);
                }
            });

            update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    String name = nameField.getText();
                    String position = positionBox.getSelectedItem().toString();
                    String contactNo = contactNoField.getText();

                    isnotEmpty(idField);
                    isnotEmpty(nameField);
                    isnotEmpty(contactNoField);

                    if (isnotEmpty(nameField) && !position.isEmpty() && isnotEmpty(contactNoField)) {
                        Employees e = workers.findEmployeeByID(Integer.parseInt(idField.getText()));

                        e.setName(name);
                        e.setPosition(position);
                        e.setContactNo(contactNo);

                        JOptionPane.showMessageDialog(cardPanel, "Sucessfully updated a member");
                        JTextField[] textFields = {nameTextField, positionTextField, contactTextField, hoursTextField, overtimeTextField, payRateTextField, totalTextField};
                        clear(textFields);
                        positionBox.setSelectedItem(0);
                        JTextField[] fields = {
                            idField,
                            nameField,
                            contactNoField

                        };
                        clearTable(model1);
                        clear(fields);
                        updateTableData();

                    } else {
                        error.setText("data fields must not be empty or null");
                        error.setVisible(true);
                    }

                }
            });
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (isnotEmpty(idField)) {
                        int choice = JOptionPane.showConfirmDialog(cardPanel, "Are you sure you want to delete user id " + idField.getText() + " ?", "Alert", JOptionPane.YES_NO_OPTION);
                        switch (choice) {

                        case 0 -> {
                            workers.removeEmployee(Integer.parseInt(idField.getText()));
                            updateTableData();

                        }
                        }
                        JTextField[] fields = {
                            idField,
                            nameField,
                            contactNoField,
                        };
                        positionBox.setSelectedItem(0);
                        clearTable(model1);
                        JTextField[] textFields = {nameTextField, positionTextField, contactTextField, hoursTextField, overtimeTextField, payRateTextField, totalTextField};
                        clear(textFields);
                        clear(fields);
                    } else {
                        error.setText("please select from table");
                        error.setVisible(true);
                    }

                }

            });
            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField[] fields = {
                        idField,
                        nameField,
                        contactNoField,
                    };
                    positionBox.setSelectedItem(0);
                    JTextField[] textFields = {nameTextField, positionTextField, contactTextField, hoursTextField, overtimeTextField, payRateTextField, totalTextField};
                    clear(textFields);
                    clear(fields);
                    clearTable(model1);
                    error.setVisible(false);
                    nameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    positionBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    contactNoField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    idField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }
            });
            error.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    error.setVisible(false);
                    nameField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    positionBox.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    contactNoField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    idField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }

            });
            
            JPanel attendancePanel = new JPanel();
            attendancePanel.setBounds(370,240,400,230);
            attendancePanel.setBackground(Color.WHITE);
            attendancePanel.setLayout(new BorderLayout());
            
            
            model1.addColumn("CLOCK-IN");
            model1.addColumn("CLOCK-OUT");
            model1.addColumn("SHIFT");
            model1.addColumn("DATE");

            // Example data

            JTable table2 = new JTable(model1);

            // Set the default cell renderer with padding
            table2.setDefaultRenderer(Object.class, cellRenderer);

            // Customize the table header
            JTableHeader header2 = table2.getTableHeader();
            header2.setReorderingAllowed(false); // Disable column dragging
            Font headerFont2 = new Font("Segoe UI", Font.BOLD, 16);
            Color headerFontColor2 = Color.WHITE;
            header2.setFont(headerFont2);
            header2.setForeground(headerFontColor2);
            header2.setBackground(Color.decode("#366f9a"));

            // Disable column resizing
            TableColumnModel columnMode2 = header2.getColumnModel();
            for (int columnIndex = 0; columnIndex < columnMode2.getColumnCount(); columnIndex++) {
                TableColumn column = columnMode2.getColumn(columnIndex);
                column.setResizable(false);
            }

            // Add the table to a scroll pane
            JScrollPane scrollPane1 = new JScrollPane(table2);
            attendancePanel.add(scrollPane1, BorderLayout.CENTER);

            // set info
            
            JPanel payrollPanel = new JPanel(new GridBagLayout());
            payrollPanel.setBackground(Color.WHITE);
            TitledBorder titled = BorderFactory.createTitledBorder("Payroll Panel");
            titled.setTitlePosition(TitledBorder.TOP);
            titled.setTitleJustification(TitledBorder.CENTER);
            payrollPanel.setBorder(titled);
            payrollPanel.setBounds(20, 240, 330, 230);
            
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5); // Padding
            gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
            
            // Labels
            JLabel nameLabel = new JLabel("Name:");
            JLabel positionLabel = new JLabel("Position:");
            JLabel contactLabel = new JLabel("Contact No:");
            JLabel hoursLabel = new JLabel("Hours Worked:");
            JLabel overtimeLabel = new JLabel("Overtime Hours:");
            JLabel payRateLabel = new JLabel("Pay Rate:");
            JLabel totalLabel = new JLabel("Total:");
        
            // Text Fields
            nameTextField = new JTextField("", 15);
            positionTextField = new JTextField("", 15);
            contactTextField = new JTextField("", 15);
            hoursTextField = new JTextField("", 5);
            overtimeTextField = new JTextField("", 5);
            payRateTextField = new JTextField("", 10);
            totalTextField = new JTextField("", 10);

            nameTextField.setEditable(false);
            positionTextField.setEditable(false);
            contactTextField.setEditable(false);
            hoursTextField.setEditable(false);
            overtimeTextField.setEditable(false);
            payRateTextField.setEditable(false);
            totalTextField.setEditable(false);
            
            JTextField[] textFields = {nameTextField, positionTextField, contactTextField, hoursTextField, overtimeTextField, payRateTextField, totalTextField};

            for (JTextField textField : textFields) {
                textField.setEditable(false);
                textField.setFocusable(false);
                Dimension textFieldSize = new Dimension(150, 20);
                textField.setPreferredSize(textFieldSize);
                textField.setMinimumSize(textFieldSize);
                textField.setMaximumSize(textFieldSize);
            }

            // Adding components to the main panel
            gbc.gridx = 0;
            gbc.gridy = 0;
            payrollPanel.add(nameLabel, gbc);
            gbc.gridx = 1;
            payrollPanel.add(nameTextField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            payrollPanel.add(positionLabel, gbc);
            gbc.gridx = 1;
            payrollPanel.add(positionTextField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            payrollPanel.add(contactLabel, gbc);
            gbc.gridx = 1;
            payrollPanel.add(contactTextField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            payrollPanel.add(hoursLabel, gbc);
            gbc.gridx = 1;
            payrollPanel.add(hoursTextField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            payrollPanel.add(overtimeLabel, gbc);
            gbc.gridx = 1;
            payrollPanel.add(overtimeTextField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            payrollPanel.add(payRateLabel, gbc);
            gbc.gridx = 1;
            payrollPanel.add(payRateTextField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            payrollPanel.add(totalLabel, gbc);
            gbc.gridx = 1;
            payrollPanel.add(totalTextField, gbc);

                  
            add(payrollPanel);
            setVisible(true);
            add(attendancePanel);
            add(infoPanel);
            
            
        }
        private void updateTableData1(Employees workers, int employeeID,DefaultTableModel model) {
            model.setRowCount(0); // Clear existing rows

            Employees worker = workers.findEmployeeByID(employeeID);
            if (worker != null) {
                // Get the employee's attendance records
                List<Employees.Attendance> attendanceRecords = worker.getAttendanceRecords();

                // Add attendance records to the table model
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
                DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("MMMM dd");
                for (Employees.Attendance record : attendanceRecords) {
                    String clockOutStr = (record.getClockOut() != null) ? record.getClockOut().format(formatter) : "N/A";
                    String day = record.getClockIn().format(dayFormatter);
                    model.addRow(new Object[]{
                        record.getClockIn().format(formatter),
                        clockOutStr,
                        record.getShift(),
                        day
                    });
                }
            } else {
                System.out.println("Employee with ID " + employeeID + " not found.");
            }

        }
        private void setEmployeeFields(String name, String position, String contactNo, String hoursWorked, String overtimeHours, String payRate, String total) {
            nameTextField.setText(name);
            positionTextField.setText(position);
            contactTextField.setText(contactNo);
            hoursTextField.setText(hoursWorked);
            overtimeTextField.setText(overtimeHours);
            payRateTextField.setText("$ " + payRate + " / hr");
            totalTextField.setText("$ " + total);
        }
        
    private double calculateTotalEarnings(Employees employee) {
        // Initialize hours worked and overtime hours to 0
        int hoursWorked = 0;
        int overtimeHours = 0;

        // Retrieve the employee's attendance records
        List<Employees.Attendance> attendanceRecords = employee.getAttendanceRecords();

        // Loop through attendance records
            for (Employees.Attendance record : attendanceRecords) {
                    if (record.getClockOut() != null) {
                         // Calculate duration between clock in and clock out
                    long durationInMinutes = java.time.Duration.between(record.getClockIn(), record.getClockOut()).toMinutes();

                    // Convert to hours
                    int hours = (int) (durationInMinutes / 60);

                    // Check if the hours fall within regular hours or overtime
                    if (hours <= 8) {
                        hoursWorked += hours;
                    } else {
                        hoursWorked += 8;
                        overtimeHours += hours - 8;
                    }
                        
                }
            }

            // Get the employee's hourly pay rate
            double payRate = employee.calculateHourlyPayRate(employee.getPosition());

            // Calculate regular pay and overtime pay
            double regularPay = hoursWorked * payRate;
            double overtimePay = overtimeHours * 1.5 * payRate; // Assuming overtime rate is 1.5 times regular rate

            // Calculate total earnings
            double totalEarnings = regularPay + overtimePay;


            // Return total earnings if this is part of a method
return totalEarnings;
    }


        
    }
    

    class EquipmentsPanel extends JPanel {
        
        private JTable table;
        private DefaultTableModel tableModel;
        private JTextField nameField;
        private JTextField quantityField;
//        private JTextField statusField;
        JComboBox<String> statusComboBox;
        private JTextArea remarksField;
        private int selectedRow = -1;
        public EquipmentsPanel() {
            
            String[] statusOptions = {
                "Operational",
                "Under Maintenance",
                "Out of Order",
                "Reserved",
                "Available",
                "In Use",
                "Needs Repair",
                "Cleaning in Progress",
                "Lost",
                "Pending",
                "Out for Delivery",
                "Disposed",
                "Not in Stock",
                "On Hold",
                "Inactive",
                "Needs Maintenance",
                "Missing"
            };
            
 
            tableModel = new DefaultTableModel(new Object[]{"Equipment", "Quantity", "Status", "Remarks"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // All cells are non-editable
                return false;
            }
            };
  
            table = new JTable(tableModel);
            table.setRowHeight(25);
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.setSelectionBackground(new Color(184, 207, 229));
            table.setSelectionForeground(Color.BLACK);

            JTableHeader header = table.getTableHeader();
            header.setReorderingAllowed(false); // Disable column dragging
            Font headerFont = new Font("Segoe UI", Font.BOLD, 16);
            Color headerFontColor = Color.WHITE;
            header.setFont(headerFont);
            header.setForeground(headerFontColor);
            header.setBackground(Color.decode("#366f9a"));

            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);
            
            TableColumnModel columnModel = header.getColumnModel();
            for (int columnIndex = 0; columnIndex < columnModel.getColumnCount(); columnIndex++) {
                TableColumn column = columnModel.getColumn(columnIndex);
                column.setResizable(false);
            }
            tableModel.addRow(new Object[]{"Treadmill", "5", "Operational", "Recently Bought"});
            tableModel.addRow(new Object[]{"Barbell Set", "3", "Needs Maintenance", "Slightly Damaged"});
            tableModel.addRow(new Object[]{"Stationary Bike", "3", "Operational", "Needs Maintenance"});
            tableModel.addRow(new Object[]{"Dumbbell Rack", "4", "Missing", "Two Dumbbells are missing"});
            tableModel.addRow(new Object[]{"Elliptical Trainer", "5", "Available", "Good Condition"});
            
            
            JPanel inputPanel = new JPanel(new GridBagLayout());
            inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;


            gbc.gridx = 0;
            gbc.gridy = 0;
            inputPanel.add(new JLabel("Equipment Name:"), gbc);
            nameField = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 0;
            inputPanel.add(nameField, gbc);


            gbc.gridx = 0;
            gbc.gridy = 1;
            inputPanel.add(new JLabel("Quantity:"), gbc);
            quantityField = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 1;
            inputPanel.add(quantityField, gbc);


            gbc.gridx = 0;
            gbc.gridy = 2;
            inputPanel.add(new JLabel("Status:"), gbc);
            statusComboBox = new JComboBox<>(statusOptions);
            gbc.gridx = 1;
            gbc.gridy = 2;
            inputPanel.add(statusComboBox, gbc);


            gbc.gridx = 0;
            gbc.gridy = 3;
            inputPanel.add(new JLabel("Remarks:"), gbc);
            remarksField = new JTextArea();
            gbc.gridx = 1;
            gbc.gridy = 3;
            inputPanel.add(remarksField, gbc);


            JPanel buttonsPanel = new JPanel();
            JButton addButton = new JButton("Add/Update");
            addButton.setBackground(new Color(34, 139, 34));
            addButton.setForeground(Color.WHITE);
            addButton.setFont(new Font("Arial", Font.BOLD, 12));
            remarksField.setBorder(new LineBorder(Color.BLACK)); 
            nameField.setBorder(new LineBorder(Color.BLACK)); 
            quantityField.setBorder(new LineBorder(Color.BLACK)); 
            Dimension preferredSize = new Dimension(100, 100);
            remarksField.setPreferredSize(preferredSize);
            remarksField.setLineWrap(true); // Enable line wrapping
            remarksField.setWrapStyleWord(true); // Wrap at word boundaries

            addButton.setFocusPainted(false);
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addOrUpdateEquipment();
                }
            });

            JButton deleteButton = new JButton("Delete");
            deleteButton.setBackground(new Color(220, 20, 60));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
            deleteButton.setFocusPainted(false);
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteEquipment();
                }
            });

            buttonsPanel.add(addButton);
            buttonsPanel.add(deleteButton);


            add(inputPanel, BorderLayout.NORTH);
            add(buttonsPanel, BorderLayout.SOUTH);
            


                table.getSelectionModel().addListSelectionListener(e -> {
                    if (!e.getValueIsAdjusting()) {
                        selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            nameField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                            quantityField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                            statusComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 2).toString());
                            remarksField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                        }
                    }
                });
            }

            private void addOrUpdateEquipment() {
                String name = nameField.getText();
                String quantity = quantityField.getText();
                String status = statusComboBox.getSelectedItem().toString();
                String remarks = remarksField.getText();

                if (name.isEmpty() || quantity.isEmpty() || status.isEmpty() || remarks.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (selectedRow == -1) {
                    counter.setEquipmentCount(counter.getEquipmentCount() + 1);
                    tableModel.addRow(new Object[]{name, quantity, status, remarks});
                } else {
                    tableModel.setValueAt(name, selectedRow, 0);
                    tableModel.setValueAt(quantity, selectedRow, 1);
                    tableModel.setValueAt(status, selectedRow, 2);
                    tableModel.setValueAt(remarks, selectedRow, 3);
                    selectedRow = -1;
                }

                clearFields();
                setVisible(true);
            }

            private void deleteEquipment() {
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                    selectedRow = -1;
                    counter.setEquipmentCount(counter.getEquipmentCount() - 1);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a row to delete", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void clearFields() {
                nameField.setText("");
                quantityField.setText("");
                statusComboBox.setSelectedIndex(0);
                remarksField.setText("");
            }
            
           
            }

}
