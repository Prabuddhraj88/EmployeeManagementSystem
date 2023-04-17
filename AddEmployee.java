import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class AddEmployee extends JFrame implements ActionListener {
    private JLabel nameLabel, ageLabel, genderLabel, phoneLabel, positionLabel, salaryLabel, passwordLabel, confirmPasswordLabel, emailLabel;
    private JTextField nameField, ageField, phoneField, positionField, salaryField, emailField;
    private JRadioButton maleRadio, femaleRadio;
    private JButton submitButton;
    private JPasswordField passwordField, confirmPasswordField;

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

        // Create a new employee object
        // Employee employee = new Employee(name, age, gender, phone, position, salary, email, password);

        // Save the employee to the database
        // boolean success = EmployeeDatabase.addEmployee(employee);
        boolean success = true;
        if (success) {
            JOptionPane.showMessageDialog(this, "Employee added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else {
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
    public static void main(String[] args) {
        AddEmployee addEmployee = new AddEmployee();
        addEmployee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addEmployee.setSize(500, 500);
        addEmployee.setVisible(true);
    }
    
}
