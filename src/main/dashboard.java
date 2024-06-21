package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
    public void addBorder(JPanel panel,int margin,String message, String num){
         JPanel p2 = new JPanel();
         p2.setLayout(null);
         p2.setBounds(margin, 240, 200, 230);
         p2.setBackground(Color.WHITE);
         JLabel title = new JLabel(message);
         title.setFont(new Font("Segoe UI", Font.PLAIN, 14));
         title.setBounds(10, -30, 200, 100);
         JLabel number = new JLabel(num);
         number.setFont(new Font("Open Sans", Font.BOLD, 80));
         number.setBounds(0,0,200,230);
         number.setHorizontalAlignment(JLabel.CENTER);
         number.setVerticalAlignment(JLabel.CENTER);
         p2.add(number);
         p2.add(title);
         panel.add(p2);
    }
    class OverviewPanel extends JPanel{
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
            
            JLabel name = new JLabel("User");
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
                 if (choice == 0){
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
            p2.setBounds(540,20,225,200);
            p2.setBackground(Color.white);
            JLabel title = new JLabel("Equipments");
            title.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            title.setBounds(10, -30, 200, 100);
            JLabel number = new JLabel("323");
            number.setFont(new Font("Open Sans", Font.BOLD, 80));
            number.setBounds(0,0,225,200);
            number.setHorizontalAlignment(JLabel.CENTER);
            number.setVerticalAlignment(JLabel.CENTER);
            p2.add(number);
            p2.add(title);
            add(p2);


            add(p1);
            addBorder(this,20,"Total Members", String.valueOf(gym.getCount()));
            addBorder(this,240,"Active Members", String.valueOf(gym.activeMembers()));
            addBorder(this,460,"Check-Ins Today",String.valueOf(gym.checkinToday()));
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
        DefaultTableModel dtm = new DefaultTableModel();

        private void updateTableData() {
        dtm.setRowCount(0); // Clear existing rows
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
        for (Members member : gym.getMembers()) {
           if (member != null) {
            String formattedStartDate = member.getStartDate().format(dateTimeFormatter);
            String formattedExpDate = member.getExpDate().format(dateTimeFormatter);
            dtm.addRow(new Object[]{member.getMembershipId(), member.getName(), formattedStartDate, formattedExpDate, member.username, member.password});
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
        
        dtm.addColumn("ID");
        dtm.addColumn("NAME");
        dtm.addColumn("STARTDATE");
        dtm.addColumn("EXPIREDATE");
        dtm.addColumn("USERNAME");
        dtm.addColumn("PASSWORD");

        // Example data
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
        // Replace this with your actual data retrieval logic
        for (Members members : gym.getMembers()) {
            
            if (members != null){
                String formattedStartDate = members.getStartDate().format(dateTimeFormatter);
                String formattedExpDate = members.getExpDate().format(dateTimeFormatter);
                dtm.addRow(new Object[]{members.getMembershipId(), members.getName(), formattedStartDate, formattedExpDate, members.getUsername(), members.getPassword()});
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
        infoPanel.setLayout(null);
        infoPanel.setBackground(Color.white);
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Information");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        infoPanel.setBorder(titledBorder);
        add(infoPanel);
        
        JLabel id = new JLabel("MembershipId");
        id.setBounds(20,20,150,30);
        infoPanel.add(id);
        
        JTextField idField = new JTextField();
        idField.setEditable(false);
        idField.setFocusable(false);
        idField.setForeground(Color.BLACK);
        idField.setBounds(20, 50, 150, 30);
        infoPanel.add(idField);
        
        JLabel name = new JLabel("Name");
        name.setBounds(20,80,150,30);
        infoPanel.add(name);
        
        JTextField nameField = new JTextField();
        nameField.setBounds(20, 110, 150, 30);
        infoPanel.add(nameField);
        
        JLabel error = new JLabel("username or password is incorrect.");
        error.setBounds(20,150,585,20);
        error.setForeground(Color.decode("#721c24"));
        error.setBackground(Color.decode("#f8d7da"));
        error.setOpaque(true);
        error.setHorizontalAlignment(JLabel.CENTER);
        error.setVerticalTextPosition(JLabel.CENTER);
        error.setBorder(BorderFactory.createLineBorder(Color.decode("#f5c6cb"), 1));
        error.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        error.setCursor(new Cursor(Cursor.HAND_CURSOR));
        error.setVisible(false);
        
        
        
        
        infoPanel.add(error);
        
        JLabel startDate = new JLabel("Start Date");
        startDate.setToolTipText("Format [Year/Month/Day] (2024-06-21)");
        startDate.setBounds(190,20,150,30);
        infoPanel.add(startDate);
        
        JTextField startDateField = new JTextField();
        startDateField.setBounds(190, 50, 150, 30);
        infoPanel.add(startDateField);
        
        JLabel ExpDate = new JLabel("Expire-Date");
        ExpDate.setBounds(190,80,150,30);
        infoPanel.add(ExpDate);
        
        JTextField ExpDateField = new JTextField();
        ExpDateField.setBounds(190, 110, 150, 30);
        infoPanel.add(ExpDateField);
        
        JLabel username = new JLabel("Username");
        username.setBounds(360,20,150,30);
        infoPanel.add(username);
        
        JTextField usernameField = new JTextField();
        usernameField.setBounds(360, 50, 150, 30);
        infoPanel.add(usernameField);
        
        JLabel password = new JLabel("Password");
        password.setBounds(360,80,150,30);
        infoPanel.add(password);
        
        JTextField passwordField = new JTextField();
        passwordField.setBounds(360, 110, 150, 30);
        infoPanel.add(passwordField);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int idfromTable = (int) table.getValueAt(row, 0);
                Members member = gym.findMemberByID(idfromTable);
                idField.setText(String.valueOf(member.getMembershipId()));
                nameField.setText(member.getName());
                startDateField.setText(String.valueOf(member.getStartDate()));
                ExpDateField.setText(String.valueOf(member.getExpDate()));
                usernameField.setText(member.getUsername());
                passwordField.setText(member.getPassword());
                
            }
        });
        
        error.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    error.setVisible(false);
                    nameField.setBorder(BorderFactory.createLineBorder(Color.black,1));
                    startDateField.setBorder(BorderFactory.createLineBorder(Color.black,1));
                    ExpDateField.setBorder(BorderFactory.createLineBorder(Color.black,1));
                    usernameField.setBorder(BorderFactory.createLineBorder(Color.black,1));
                    passwordField.setBorder(BorderFactory.createLineBorder(Color.black,1));
                }
        
        
        });
        create = new JButton("Create");
        create.setBounds(665,285,100,40);
        create.setBackground(Color.green);
        create.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    String name= nameField.getText();
                    String start = String.valueOf(startDateField.getText());
                    String exp =  String.valueOf(ExpDateField.getText());
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    
                    if(isnotEmpty(nameField)){
                        // just checking if empty
                    }
                    if(isnotEmpty(startDateField)){
                        // just checking if empty
                    }
                    if(isnotEmpty(ExpDateField)){
                        // just checking if empty
                    }
                    if(isnotEmpty(usernameField)){
                        // just checking if empty
                    }
                    if(isnotEmpty(passwordField)){
                        // just checking if empty
                    }
                        
                        
                    if(isnotEmpty(nameField) && isnotEmpty(startDateField) && isnotEmpty(ExpDateField) && isnotEmpty(usernameField) && isnotEmpty(passwordField)){
                        gym.addMember(new Members(name,parseDate(start),parseDate(exp),username,password));
                        JOptionPane.showMessageDialog(cardPanel, "Sucessfully created a member");
                        updateTableData();
                        JTextField[] fields = {idField,nameField,startDateField,ExpDateField,usernameField,passwordField}; 
                        clear(fields);  
                    }else{
                        error.setText("data fields must not be empty or null");
                        error.setVisible(true);
                    }
                    
                }  
        });
        add(create);
        
    
        update = new JButton("Update");
        update.setBounds(665,335,100,40);
        update.setBackground(Color.decode("#ff8b26"));
        add(update);
        update.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name= nameField.getText();
                    String start = String.valueOf(startDateField.getText());
                    String exp =  String.valueOf(ExpDateField.getText());
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    
                    if(isnotEmpty(nameField)){
                        // just checking if empty
                    }
                    if(isnotEmpty(startDateField)){
                        // just checking if empty
                    }
                    if(isnotEmpty(ExpDateField)){
                        // just checking if empty
                    }
                    if(isnotEmpty(usernameField)){
                        // just checking if empty
                    }
                    if(isnotEmpty(passwordField)){
                        // just checking if empty
                    }
                           
                    if(isnotEmpty(nameField) && isnotEmpty(startDateField) && isnotEmpty(ExpDateField) && isnotEmpty(usernameField) && isnotEmpty(passwordField)){
                        Members member = gym.findMemberByID(Integer.parseInt(idField.getText()));
                        
                        member.setName(name);
                        member.setStartDate(parseDate(start));
                        member.setExpDate(parseDate(exp));
                        member.setUsername(username);
                        member.setPassword(password);
                        JOptionPane.showMessageDialog(cardPanel, "Sucessfully updated a member");
                        JTextField[] fields = {idField,nameField,startDateField,ExpDateField,usernameField,passwordField}; 
                        clear(fields);
                        updateTableData();
                        
                    }else{
                        error.setText("data fields must not be empty or null");
                        error.setVisible(true);
                    }
                    
                  
                }
        });
        
        delete = new JButton("Delete");
        delete.setBounds(665,385,100,40);
        delete.setBackground(Color.red);
        add(delete);
        delete.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(isnotEmpty(idField)){
                        int choice = JOptionPane.showConfirmDialog(cardPanel, "Are you sure you want to delete user id " +idField.getText()+ " ?","Alert",JOptionPane.YES_NO_OPTION);
                        
                        switch(choice){
                    
                            case 0 -> { gym.removeMember(Integer.parseInt(idField.getText()));  updateTableData(); JTextField[] fields = {idField,nameField,startDateField,ExpDateField,usernameField,passwordField}; clear(fields);}
                            
                        }
                    }
                   
                }
        });
        
        clear = new JButton("Clear");
        clear.setBounds(665,435,100,40);
        clear.setBackground(Color.decode("#94a0ff"));
        clear.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField[] fields = {idField,nameField,startDateField,ExpDateField,usernameField,passwordField};
                    clear(fields);
                }
        });
        add(clear);    
    }
        public  void clear(JTextField[] fields){
            for(JTextField e:fields){
                    e.setText("");           
            }
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
        public boolean isnotEmpty(JTextField field){
            
            if(field.getText().equals("") || field.getText() == null){
               field.setBorder(BorderFactory.createLineBorder(Color.red, 1));
               return false;
            }
            field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            return true;
        }
        
        
        
    }
    

    class UserLogsPanel extends JPanel {
        public UserLogsPanel() {
            setBackground(Color.decode("#ebebeb"));
            add(new JLabel("User Logs Content"));
            JPanel p1 = new JPanel();
            setLayout(null);
            p1.setBounds(20, 20, 745, 200);
            p1.setBackground(Color.WHITE);
            JPanel p1_2 = new JPanel();
            p1_2.setBackground(Color.decode("#4daddc"));
            int thickness = 5; // Adjust the thickness as desired
            p1_2.setBorder(BorderFactory.createEmptyBorder(thickness, 0, thickness, 0));

            p1.setLayout(new BorderLayout());
            
            p1.add(p1_2,BorderLayout.NORTH);
            add(p1);
        }
    }

    class EquipmentsPanel extends JPanel {
        public EquipmentsPanel() {
            setBackground(Color.decode("#ebebeb"));
            add(new JLabel("Equipments Content"));
            JPanel p1 = new JPanel();
            setLayout(null);
            p1.setBounds(20, 20, 745, 200);
            p1.setBackground(Color.WHITE);
            JPanel p1_2 = new JPanel();
            p1_2.setBackground(Color.decode("#4daddc"));
            int thickness = 5; // Adjust the thickness as desired
            p1_2.setBorder(BorderFactory.createEmptyBorder(thickness, 0, thickness, 0));

            p1.setLayout(new BorderLayout());
            
            p1.add(p1_2,BorderLayout.NORTH);
            add(p1);
        }
    
    }
  
}

