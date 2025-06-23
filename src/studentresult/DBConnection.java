package studentresult;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8.x+ driver class
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/student_result_db", "root", "Pr@1106");
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
            return null;
        }
    }
}
