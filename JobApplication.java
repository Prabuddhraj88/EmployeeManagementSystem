import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class JobApplication extends JFrame implements ActionListener {
    private JLabel nameLabel, surnameLabel, ageLabel, experienceLabel, emailLabel, linkedinLabel, contactLabel, addressLabel;
    private JTextField nameField, surnameField, experienceField, emailField, linkedinField, contactField, addressField;
    private JSlider ageSlider;
    private JButton submitButton;

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
                // Inputs are valid, process the job application
                // TODO: implement the job application processing logic
                JOptionPane.showMessageDialog(this, "Thank you for submitting your job application!");
            }
        }
    }

    /**
     * Checks if an email address is valid.
     * 
     * @param email the email address to check
     * @return true if the email address is valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        if (email.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        JobApplication application = new JobApplication();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setVisible(true);
    }
    
}
