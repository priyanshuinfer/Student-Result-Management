# Student Result Management System

A desktop application built with Java Swing and MySQL (via JDBC) for admins to manage student records and results.

## Features
- **Admin login authentication**
- **Add/View students** (roll no, name, course, branch)
- **Add/View student results** (subject-wise marks)
- **Referential integrity:** results only for existing students
- **Modern GUI** with sidebar navigation and styled buttons
- **Logout functionality**

## Prerequisites
- JDK 11+ installed
- MySQL server running
- MySQL Connector/J (JAR) added to project classpath
- An IDE (Eclipse/IntelliJ) or ability to compile/run Java code

## Setup

1. Clone or copy the project folder into your workspace.

2. In MySQL, create a database and tables. For example, in MySQL CLI or Workbench:
   ```sql
   CREATE DATABASE studentdb;
   USE studentdb;

   CREATE TABLE admin (
     id INT AUTO_INCREMENT PRIMARY KEY,
     username VARCHAR(50) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL
   );

   CREATE TABLE students (
     roll_no INT PRIMARY KEY,
     name VARCHAR(100) NOT NULL,
     course VARCHAR(50),
     branch VARCHAR(50)
   );

   CREATE TABLE results (
     id INT AUTO_INCREMENT PRIMARY KEY,
     roll_no INT,
     subject VARCHAR(100),
     marks INT,
     FOREIGN KEY (roll_no) REFERENCES students(roll_no)
       ON DELETE CASCADE ON UPDATE CASCADE
   );

   INSERT INTO admin (username, password) VALUES ('admin', 'admin123');
   ```

3. Update `DBConnection.java` with your JDBC URL, database name, user, and password. Example:
   ```java
   String url = "jdbc:mysql://localhost:3306/studentdb";
   String user = "your_db_user";
   String pass = "your_db_password";
   ```

4. Add the MySQL Connector/J JAR to the project’s build path:
   - In Eclipse: Right-click project → Build Path → Add External Archives → select `mysql-connector-java-*.jar`
   - In IntelliJ: File → Project Structure → Libraries → + → select the JAR

## Usage

1. Run `LoginForm.java` (it contains `main` and uses `SwingUtilities.invokeLater`).
2. In the login window, enter admin credentials (e.g., `admin` / `admin123`).
3. After login, use the sidebar to:
   - 🏠 **Dashboard** (welcome or stats)
   - ➕ **Add Student** (opens form to add a new student)
   - 📝 **Add Result** (opens form to insert marks; validates roll exists)
   - 📊 **View Results** (opens a table of all student results)
   - 🚪 **Logout** (returns to login screen)

## Directory Structure

\`\`\`
studentresult/
├── gui/
│   ├── LoginForm.java
│   ├── AdminDashboard.java
│   ├── AddStudentForm.java
│   ├── AddResultForm.java
│   └── ViewResults.java
├── DBConnection.java
└── (optional) Main.java
\`\`\`

## Screenshots (optional)

Add screenshots under a folder like \`docs/screenshots/\` and reference them here:

- **Login Screen**: \`docs/screenshots/login.png\`
- **Dashboard**: \`docs/screenshots/dashboard.png\`
- **Add Student Form**: \`docs/screenshots/add_student.png\`
- **View Results Table**: \`docs/screenshots/view_results.png\`

## Roadmap

- Password hashing (e.g., BCrypt) instead of plaintext
- Search/filter in View Results by roll number or subject
- Update/Delete student and result records
- Dashboard analytics (total students, average marks, highest/lowest, pass rate)
- Export to CSV/PDF and print report cards
- Dark mode toggle
- Student login portal to view personal results
- Packaging as executable JAR with dependencies

## Contributing

1. Fork the repository.
2. Create a new branch:
   \`\`\`bash
   git checkout -b feature/YourFeature
   \`\`\`
3. Make changes, commit with clear messages.
4. Push to your fork:
   \`\`\`bash
   git push origin feature/YourFeature
   \`\`\`
5. Open a Pull Request, describing your improvements.

Ensure code remains modular, UI consistent with modern theme, and includes necessary documentation.

## License

This project is licensed under the MIT License. See \`LICENSE\` file for full text.

## Author & Contact

Priyanshu Chaturvedi  
- GitHub: https://github.com/priyanshuinfer
- Email: iampriyanshu1102@gmail.com

Feel free to reach out for feedback or suggestions.

## Acknowledgements

- Java Swing and JDBC documentation
- MySQL Connector/J resources
- Inspiration from modern desktop and web dashboards

[Back to Top](#student-result-management-system)
