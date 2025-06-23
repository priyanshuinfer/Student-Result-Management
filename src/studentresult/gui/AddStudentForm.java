package studentresult.gui;

import studentresult.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddStudentForm extends JFrame {
    private JTextField rollField, nameField, courseField, branchField;

    public AddStudentForm() {
        setTitle("Add Student");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        rollField = new JTextField();
        nameField = new JTextField();
        courseField = new JTextField();
        branchField = new JTextField();
        JButton saveBtn = new JButton("Save");

        add(new JLabel("Roll No:")); add(rollField);
        add(new JLabel("Name:")); add(nameField);
        add(new JLabel("Course:")); add(courseField);
        add(new JLabel("Branch:")); add(branchField);
        add(new JLabel("")); add(saveBtn);

        saveBtn.addActionListener(e -> saveStudent());
        setVisible(true);
    }

    private void saveStudent() {
        try {
            int roll = Integer.parseInt(rollField.getText());
            String name = nameField.getText();
            String course = courseField.getText();
            String branch = branchField.getText();

            Connection conn = DBConnection.connect();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO students VALUES (?, ?, ?, ?)");
            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setString(3, course);
            ps.setString(4, branch);

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "✅ Student added!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Failed to add student.");
            }

            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
