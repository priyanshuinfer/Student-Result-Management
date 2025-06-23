package studentresult.gui;

import studentresult.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewResults extends JFrame {
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public ViewResults() {
        setTitle("View Student Results");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table setup
        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);

        // Define table columns
        tableModel.addColumn("Roll No");
        tableModel.addColumn("Name");
        tableModel.addColumn("Subject");
        tableModel.addColumn("Marks");

        // Load data
        loadResults();

        // Add table to scroll pane and then to frame
        JScrollPane scrollPane = new JScrollPane(resultTable);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void loadResults() {
        try {
            Connection conn = DBConnection.connect();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed!");
                return;
            }

            String query = "SELECT s.roll_no, s.name, r.subject, r.marks " +
                           "FROM students s JOIN results r ON s.roll_no = r.roll_no";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("roll_no"),
                    rs.getString("name"),
                    rs.getString("subject"),
                    rs.getInt("marks")
                };
                tableModel.addRow(row);
            }

            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading results: " + e.getMessage());
        }
    }
}
