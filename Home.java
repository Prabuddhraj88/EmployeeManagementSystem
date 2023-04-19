import java.awt.*;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.*;
import java.sql.Connection;  
import java.sql.DatabaseMetaData;  
import java.sql.DriverManager;  
import java.sql.SQLException; 
import java.sql.Statement; 
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

// sqlite handler
class SQLiteHandler{
    public Connection create_database(String database_name){
        String databasepath="jdbc:sqlite:/D:\\ProjectJava\\database\\"+database_name+".db";

        try {  
            Connection conn = DriverManager.getConnection(databasepath);  
            if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();  
               System.out.println("The driver name is " + meta.getDriverName());  
             //   System.out.println("A new database has been created.");  
            }  
            return conn;
   
        } catch (SQLException e) {  
            System.out.println(e.getMessage()); 
            return null; 
        }   
        
    } 
        public ResultSet runStatement(Connection c1,String sqlcommand) {  
            // SQLite connection string  
            //String url = "jdbc:sqlite:C://sqlite/SSSIT.db";  
              
            // SQL statement for creating a new table  
            // String sql = "CREATE TABLE IF NOT EXISTS admin (\n"  
            //         + " id integer PRIMARY KEY,\n"  
            //         + " name text NOT NULL,\n"  
            //         + " capacity real\n"  
            //         + ");";  
              
            try{  
               // Connection conn = DriverManager.getConnection(url);  
                Statement stmt = c1.createStatement();  
                ResultSet rs=stmt.executeQuery(sqlcommand);  
                System.out.println("command executed Succesfully");
                return rs;
            } catch (SQLException e) {  
                System.out.println(e.getMessage()); 
                return null; 
            }  
        }
         
   
}
// SQLiteHandler sqLiteHandler = new SQLiteHandler();

// user login
class UserLogin extends JFrame {
    private JLabel userLabel;
    private JTextField userTextField;
    private JLabel passLabel;
    private JPasswordField passField;
    private JButton loginButton;
    private SQLiteHandler sqLiteHandler = new SQLiteHandler();
    Connection connection = sqLiteHandler.create_database("ems");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    public UserLogin() {
        super("Login Module");

        // Create the labels and text fields
        userLabel = new JLabel("Username:");
        userTextField = new JTextField(20);
        passLabel = new JLabel("Password:");
        passField = new JPasswordField(20);

        // Create the login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Compare the username and password to the database
                String username = userTextField.getText();
                String password = String.valueOf(passField.getPassword());
                String sql="select * from EmployeeDetail where name='" + username + "' and password='" + password + "';";
                ResultSet resultSet=sqLiteHandler.runStatement(connection,sql);
                if (resultSet != null) {
                    String tablename= ("t" + java.time.LocalDate.now()+"_attendance").replace("-", "");
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    sql = "CREATE TABLE IF NOT EXISTS " + tablename + "(name text, timestamp text);";
                    sqLiteHandler.runStatement(connection,sql);
                    sql = "INSERT INTO " + tablename + "(name, timestamp) values('" + username + "', '" + sdf.format(timestamp) + "'" + ");";
                    sqLiteHandler.runStatement(connection,sql);                    
                    JOptionPane.showMessageDialog(UserLogin.this, "Your attendance has been marked.");
                } else {
                    JOptionPane.showMessageDialog(UserLogin.this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create the panel to hold the components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 5, 10);
        panel.add(userLabel, c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(userTextField, c);
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        panel.add(passLabel, c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(loginButton, c);

        // Add the panel to the content pane
        Container contentPane = getContentPane();
        contentPane.add(panel);

        // Set the size and visibility of the JFrame
        setSize(400, 200);
        setLocationRelativeTo(null); // center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}


// job application : done
class JobApplication extends JFrame implements ActionListener {
    private JLabel nameLabel, surnameLabel, ageLabel, experienceLabel, emailLabel, linkedinLabel, contactLabel, addressLabel;
    private JTextField nameField, surnameField, experienceField, emailField, linkedinField, contactField, addressField;
    private JSlider ageSlider;
    private JButton submitButton;
    private SQLiteHandler sqLiteHandler = new SQLiteHandler();
    Connection connection = sqLiteHandler.create_database("ems");

    public JobApplication() {
        super("Job Application");

        nameLabel = new JLabel("Name:");
        surnameLabel = new JLabel("Surname:");
        ageLabel = new JLabel("Age:");
        experienceLabel = new JLabel("Experience:");
        emailLabel = new JLabel("Email:");
        linkedinLabel = new JLabel("LinkedIn:");
        contactLabel = new JLabel("Contact Number:");
        addressLabel = new JLabel("Address:");

        nameField = new JTextField(20);
        surnameField = new JTextField(20);
        experienceField = new JTextField(20);
        emailField = new JTextField(20);
        linkedinField = new JTextField(20);
        contactField = new JTextField(20);
        addressField = new JTextField(20);

        ageSlider = new JSlider(JSlider.HORIZONTAL, 23, 60, 23);
        ageSlider.setMajorTickSpacing(5);
        ageSlider.setMinorTickSpacing(1);
        ageSlider.setPaintTicks(true);
        ageSlider.setPaintLabels(true);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(nameLabel, constraints);

        constraints.gridx = 1;
        panel.add(nameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(surnameLabel, constraints);

        constraints.gridx = 1;
        panel.add(surnameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(ageLabel, constraints);

        constraints.gridx = 1;
        JPanel agePanel = new JPanel(new BorderLayout());
        agePanel.add(ageSlider, BorderLayout.CENTER);
        panel.add(agePanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(experienceLabel, constraints);

        constraints.gridx = 1;
        panel.add(experienceField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(emailLabel, constraints);

        constraints.gridx = 1;
        panel.add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(linkedinLabel, constraints);

        constraints.gridx = 1;
        panel.add(linkedinField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(contactLabel, constraints);

        constraints.gridx = 1;
        panel.add(contactField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(addressLabel, constraints);

        constraints.gridx = 1;
        panel.add(addressField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, constraints);

        add(panel);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Get user inputs
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            int age = ageSlider.getValue();
            String experience = experienceField.getText().trim();
            String email = emailField.getText().trim();
            String linkedin = linkedinField.getText().trim();
            String contact = contactField.getText().trim();
            String address = addressField.getText().trim();

            // Validate user inputs
            if (name.isEmpty() || surname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name and surname.");
            } else if (age < 23 || age > 60) {
                JOptionPane.showMessageDialog(this, "Please select an age between 23 and 60.");
            } else if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
            } else {
                // push data to database
                String sqlStatement1 = "CREATE TABLE IF NOT EXISTS JobApplications (name text, age int, experience text, email text, linkedin text, contact real, address text);";
                String sqlStatement2 = "INSERT INTO JobApplications (name, age, experience, email, linkedin, contact, address) VALUES ('" + name + "', " + age + ", '" + experience + "', '" + email + "', '" + linkedin + "', " + contact + ", '" + address + "');";
                sqLiteHandler.runStatement(connection, sqlStatement1);
                sqLiteHandler.runStatement(connection, sqlStatement2);
                JOptionPane.showMessageDialog(this, "Thank you for submitting your job application!");
            }
        }
    }


    private boolean isValidEmail(String email) {
        if (email.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    
}

// job scrutiny : done
class JobScrutiny extends JFrame {
    private SQLiteHandler sqLiteHandler = new SQLiteHandler();
    Connection connection = sqLiteHandler.create_database("ems");
    private List<String[]> jobApplications = new ArrayList<String[]>();

    // Initialize the list of job applications with sample data
    // private String[][] jobApplications = {
    //     {"RAJ", "PANDEY", "20", "COGNIZANT", "prabuddhraj88@gmail.com", "linkedin.com/in/prabuddhraj", "8601096596", "Uttar Pradesh"},
    //     {"SRISTI", "MAURYA", "20", "WIPRO", "sristimaurya55@gmail.com", "linkedin.com/in/sristimaurya", "7755689566", "Gujrat"},
    //     {"SURYA", "SINGH", "20", "INFOSIS", "suryabhansingh21@gmail.com", "linkedin.com/in/suryabhansingh", "8542639154", "Bihar"},
        
    // };
    
    
    private JList<String[]> jobApplicationsList;
    private DefaultListModel<String[]> jobApplicationsListModel;
    
    public JobScrutiny() {
        super("Job Application Approval");
        String sqlStatement1 = "SELECT * FROM JobApplications;";
        ResultSet resultSet = sqLiteHandler.runStatement(connection, sqlStatement1);
        try {            
            while(resultSet.next()) {
                jobApplications.add(new String[]{
                    // name, age, experience, email, linkedin, contact, address
                    resultSet.getString("name"),
                    resultSet.getString("age"),
                    resultSet.getString("experience"),
                    resultSet.getString("email"),
                    resultSet.getString("linkedin"),
                    resultSet.getString("contact"),
                    resultSet.getString("address"),
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Create the list model and add the job applications to it
        jobApplicationsListModel = new DefaultListModel<>();
        for (String[] jobApplication : jobApplications) {
            jobApplicationsListModel.addElement(jobApplication);
        }
        
        // Create the JList with the list model
        jobApplicationsList = new JList<>(jobApplicationsListModel);
        jobApplicationsList.setCellRenderer(new JobApplicationListRenderer());
        
        // Create the accept and reject buttons
        JButton acceptButton = new JButton("Accept");
        acceptButton.setForeground(Color.GREEN);
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Remove the selected job application from the list
                int selectedIndex = jobApplicationsList.getSelectedIndex();
                String[] selectedString = jobApplicationsList.getSelectedValue();
                if (selectedIndex != -1) {
                    jobApplicationsListModel.remove(selectedIndex);
                    String sqlStatement2;
                    try {
                        sqlStatement2 = "delete from JobApplications where name='" + selectedString[0] + "';";
                        sqLiteHandler.runStatement(connection, sqlStatement2);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
        
        JButton rejectButton = new JButton("Reject");
        rejectButton.setForeground(Color.RED);
        rejectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Remove the selected job application from the list
                int selectedIndex = jobApplicationsList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String sqlStatement2;
                    String[] selectedString = jobApplicationsList.getSelectedValue();
                    try {
                        System.out.print(selectedString[0]);
                        sqlStatement2 = "delete from JobApplications where name='" + selectedString[0] + "';";
                        sqLiteHandler.runStatement(connection, sqlStatement2);
                        jobApplicationsListModel.remove(selectedIndex);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        
        // Create the button panel with the accept and reject buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);
        
        // Add the components to the content pane
        Container contentPane = getContentPane();
        contentPane.add(new JScrollPane(jobApplicationsList), BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        // Set the size and visibility of the window
        setSize(600, 400);
        setVisible(true);
    }
    
    // Custom cell renderer for the job applications list
    private class JobApplicationListRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            String[] jobApplication = (String[]) value;
            String fullName = jobApplication[0] + " " + jobApplication[1];
            String age = jobApplication[2];
            String email = jobApplication[3];
            String linkedin = jobApplication[4];
            String contactNumber = jobApplication[5];
            String address = jobApplication[6];
            label.setText(fullName + ", " + age + " years old, " + ", " + email + ", " + linkedin + ", " + contactNumber + ", " + address);
            return label;
        }
    }
    
}

// add employee : done
class AddEmployee extends JFrame implements ActionListener {
    private JLabel nameLabel, ageLabel, genderLabel, phoneLabel, positionLabel, salaryLabel, passwordLabel, confirmPasswordLabel, emailLabel;
    private JTextField nameField, ageField, phoneField, positionField, salaryField, emailField;
    private JRadioButton maleRadio, femaleRadio;
    private JButton submitButton;
    private JPasswordField passwordField, confirmPasswordField;
    private SQLiteHandler sqLiteHandler = new SQLiteHandler();
    Connection connection = sqLiteHandler.create_database("ems");

    public AddEmployee() {
        super("Employee Management System");

        nameLabel = new JLabel("Name:");
        ageLabel = new JLabel("Age:");
        genderLabel = new JLabel("Gender:");
        phoneLabel = new JLabel("Phone Number:");
        positionLabel = new JLabel("Position:");
        salaryLabel = new JLabel("Salary:");
        passwordLabel = new JLabel("Password:");
        confirmPasswordLabel = new JLabel("Confirm Password:");
        emailLabel = new JLabel("Email Address:");

        nameField = new JTextField(20);
        ageField = new JTextField(20);
        phoneField = new JTextField(20);
        positionField = new JTextField(20);
        salaryField = new JTextField(20);
        emailField = new JTextField(20);

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(nameLabel, constraints);

        constraints.gridx = 1;
        panel.add(nameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(ageLabel, constraints);

        constraints.gridx = 1;
        panel.add(ageField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(genderLabel, constraints);

        constraints.gridx = 1;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        panel.add(genderPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(phoneLabel, constraints);

        constraints.gridx = 1;
        panel.add(phoneField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(positionLabel, constraints);

        constraints.gridx = 1;
        panel.add(positionField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(salaryLabel, constraints);

        constraints.gridx = 1;
        panel.add(salaryField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(emailLabel, constraints);

        constraints.gridx = 1;
        panel.add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        panel.add(confirmPasswordLabel, constraints);

        constraints.gridx = 1;
        panel.add(confirmPasswordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, constraints);
            // Add the panel to the frame
    this.add(panel);

    // Set frame properties
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton) {
        // name, age, gender, phone, position, salary, email, password
        String name = nameField.getText();
        String ageStr = ageField.getText();
        int age = 0;
        if (!ageStr.equals("")) {
            age = Integer.parseInt(ageStr);
        }
        String gender = "";
        if (maleRadio.isSelected()) {
            gender = "Male";
        } else if (femaleRadio.isSelected()) {
            gender = "Female";
        }
        String phone = phoneField.getText();
        String position = positionField.getText();
        String salaryStr = salaryField.getText();
        double salary = 0;
        if (!salaryStr.equals("")) {
            salary = Double.parseDouble(salaryStr);
        }
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
            JOptionPane.showMessageDialog(this, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String sqlStatement1 = "CREATE TABLE IF NOT EXISTS EmployeeDetail (name text, age int, gender text, phone text, position text, salary real, email text, password text);";
            String sqlStatement2 = "INSERT INTO EmployeeDetail (name, age, gender, phone, position, salary, email, password) VALUES ('" + name + "', " + age + ", '" + gender + "', '" + phone + "', '" + position + "', " + salary + ", '" + email + "', '" + password + "');";
            sqLiteHandler.runStatement(connection, sqlStatement1);
            sqLiteHandler.runStatement(connection, sqlStatement2);
            JOptionPane.showMessageDialog(this, "Employee added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch(Exception sqex) {
            sqex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to add employee.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        phoneField.setText("");
        positionField.setText("");
        salaryField.setText("");
        emailField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    
}

// remove employee : done
class RemoveEmployee extends JFrame {

    // Initialize the list of employees with sample data
    // private String[] employees = {"PRABUDDH", "SRISTI", "SURYA"};
    private List<String> employees = new ArrayList<String>();
    private JList<String> employeeList;
    private DefaultListModel<String> employeeListModel;
    private SQLiteHandler sqLiteHandler = new SQLiteHandler();
    Connection connection = sqLiteHandler.create_database("ems");
    
    public RemoveEmployee() {
        super("Employee Removal");
        String sqlStatement1 = "SELECT * FROM EmployeeDetail;";
        ResultSet resultSet = sqLiteHandler.runStatement(connection, sqlStatement1);
        // sqLiteHandler.runStatement(connection, sqlStatement2);
        try {            
            while(resultSet.next()) {
                employees.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Create the list model and add the employees to it
        employeeListModel = new DefaultListModel<>();
        for (String employee : employees) {
            employeeListModel.addElement(employee);
        }

        // Create the JList with the list model
        employeeList = new JList<>(employeeListModel);

        // Create the remove button
        JButton removeButton = new JButton("Remove");
        removeButton.setForeground(Color.RED);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Remove the selected employee from the list
                int selectedIndex = employeeList.getSelectedIndex();
                String selectedValue = employeeList.getSelectedValue();
                String sqlStatement2 = "delete from EmployeeDetail where name='" + selectedValue + "';";
                if (selectedIndex != -1) {
                    sqLiteHandler.runStatement(connection, sqlStatement2);
                    employeeListModel.remove(selectedIndex);
                }
            }
        });

        // Create the button panel with the remove button
        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
        buttonPanel.add(removeButton);

        // Create the detail panel with the employee details
        JPanel detailPanel = new JPanel(new GridLayout(1, 1));
        detailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel usernameLabel = new JLabel("Employees");
        detailPanel.add(usernameLabel);

        // Add the components to the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(employeeList), BorderLayout.CENTER);
        contentPane.add(detailPanel, BorderLayout.WEST);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Set the size and visibility of the JFrame
        setSize(400, 400);
        setVisible(true);
    }

}

// admin login : done
class AdminLogin extends JFrame {

    private JLabel userLabel;
    private JTextField userTextField;
    private JLabel passLabel;
    private JPasswordField passField;
    private JButton loginButton;

    public AdminLogin() {
        super("Admin Login");

        // Create the labels and text fields
        userLabel = new JLabel("Username:");
        userTextField = new JTextField(20);
        passLabel = new JLabel("Password:");
        passField = new JPasswordField(20);

        // Create the login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Compare the username and password to the database
                String username = userTextField.getText();
                String password = String.valueOf(passField.getPassword());
                if (username.equals("admin") && password.equals("password")) {
                    new AdminPanel();
                } else {
                    JOptionPane.showMessageDialog(AdminLogin.this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create the panel to hold the components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 5, 10);
        panel.add(userLabel, c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(userTextField, c);
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        panel.add(passLabel, c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(loginButton, c);

        // Add the panel to the content pane
        Container contentPane = getContentPane();
        contentPane.add(panel);

        // Set the size and visibility of the JFrame
        setSize(400, 200);
        setLocationRelativeTo(null); // center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}


// admin panel : done
class AdminPanel {
    JFrame frame;

    public AdminPanel() {
        frame = new JFrame("Admin Panel");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        frame.add(panel);

        JButton addEmployeeBtn = new JButton("Add Employee");
        addEmployeeBtn.setPreferredSize(new Dimension(150, 50));
        addEmployeeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddEmployee addEmployee = new AddEmployee();
                addEmployee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                addEmployee.setSize(500, 500);
                addEmployee.setVisible(true);
            }
        });
        panel.add(addEmployeeBtn);

        JButton removeEmployeeBtn = new JButton("Remove Employee");
        removeEmployeeBtn.setPreferredSize(new Dimension(150, 50));
        removeEmployeeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RemoveEmployee frame = new RemoveEmployee();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        panel.add(removeEmployeeBtn);

        JButton scrutinyJobApplicationsBtn = new JButton("Scrutiny Job Applications");
        scrutinyJobApplicationsBtn.setPreferredSize(new Dimension(200, 50));
        scrutinyJobApplicationsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JobScrutiny();
            }
        });
        panel.add(scrutinyJobApplicationsBtn);

        frame.setVisible(true);
    }

}

// home : done
public class Home {
    JFrame frame;
    JButton adminLoginButton, punchAttendanceButton, applyForJobButton;

    public Home() {
        frame = new JFrame("Home Page");
        adminLoginButton = new JButton("Admin Login");
        punchAttendanceButton = new JButton("Punch Attendance");
        applyForJobButton = new JButton("Apply for Job");

        // set font size for buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        adminLoginButton.setFont(buttonFont);
        punchAttendanceButton.setFont(buttonFont);
        applyForJobButton.setFont(buttonFont);

        // set button size
        Dimension buttonSize = new Dimension(200, 40);
        adminLoginButton.setPreferredSize(buttonSize);
        punchAttendanceButton.setPreferredSize(buttonSize);
        applyForJobButton.setPreferredSize(buttonSize);

        // add action listeners to buttons
        adminLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminLogin();
            }
        });
        punchAttendanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UserLogin();
            }
        });
        applyForJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JobApplication application = new JobApplication();
                application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                application.setVisible(true);
            }
        });

        // add buttons to panel
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.add(adminLoginButton);
        panel.add(punchAttendanceButton);
        panel.add(applyForJobButton);

        // add panel to frame
        frame.add(panel);

        // set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // create new instance of Home class
        new Home();
        // UserLogin frame = new UserLogin();
    }
}
