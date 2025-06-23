package studentresult.gui;

import studentresult.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddResultForm extends JFrame {
    private JTextField rollField, subjectField, marksField;

    public AddResultForm() {
        setTitle("Add Result");
        setSize(350, 250);
        setLayout(new GridLayout(4, 2, 10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        rollField = new JTextField();
        subjectField = new JTextField();
        marksField = new JTextField();
        JButton saveBtn = new JButton("Save");

        add(new JLabel("Roll No:")); add(rollField);
        add(new JLabel("Subject:")); add(subjectField);
        add(new JLabel("Marks:")); add(marksField);
        add(new JLabel("")); add(saveBtn);

        saveBtn.addActionListener(e -> saveResult());
        setVisible(true);
    }

    private void saveResult() {
        try {
            int roll = Integer.parseInt(rollField.getText());
            String subject = subjectField.getText();
            int marks = Integer.parseInt(marksField.getText());

            Connection conn = DBConnection.connect();
         // Before inserting result, check if student exists
            PreparedStatement check = conn.prepareStatement("SELECT * FROM students WHERE roll_no = ?");
            check.setInt(1, roll);
            ResultSet rs = check.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Roll No does not exist in student records.");
                return;
            }

            PreparedStatement ps = conn.prepareStatement("INSERT INTO results (roll_no, subject, marks) VALUES (?, ?, ?)");
            ps.setInt(1, roll);
            ps.setString(2, subject);
            ps.setInt(3, marks);

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "✅ Result added!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Failed to add result.");
            }

            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
