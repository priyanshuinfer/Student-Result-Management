package studentresult.gui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    private JPanel sidebar, topbar, mainPanel;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initTopbar();
        initSidebar();
        initMainPanel();

        setVisible(true);
    }

    private void initTopbar() {
        topbar = new JPanel();
        topbar.setBackground(new Color(45, 118, 232));
        topbar.setPreferredSize(new Dimension(100, 60));
        topbar.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));

        JLabel title = new JLabel("Student Result Management System");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        topbar.add(title);
        add(topbar, BorderLayout.NORTH);
    }

    private void initSidebar() {
        sidebar = new JPanel();
        sidebar.setBackground(new Color(230, 230, 250));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        // Sidebar buttons
        JButton dashboardBtn = new JButton("\uD83C\uDFE0 Dashboard");
        JButton addStudentBtn = new JButton("\u2795 Add Student");
        JButton addResultBtn = new JButton("\uD83D\uDCDD Add Result");
        JButton viewResultsBtn = new JButton("\uD83D\uDCCA View Results");
        JButton logoutBtn = new JButton("\uD83D\uDEAA Logout");


        // Actions
        addStudentBtn.addActionListener(e -> new AddStudentForm());
        addResultBtn.addActionListener(e -> new AddResultForm());
        viewResultsBtn.addActionListener(e -> new ViewResults());
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginForm();
        });

        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(dashboardBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(addStudentBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(addResultBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(viewResultsBtn);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(logoutBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        add(sidebar, BorderLayout.WEST);
    }

    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(180, 40));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(255, 255, 255));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void initMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 1));
        mainPanel.setBackground(Color.WHITE);

        JLabel welcome = new JLabel("Welcome, Admin!", SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcome.setForeground(new Color(45, 45, 45));

        mainPanel.add(welcome);
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminDashboard::new);
    }
}
