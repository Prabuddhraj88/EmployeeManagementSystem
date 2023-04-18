import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserLogin extends JFrame {

    private JLabel userLabel;
    private JTextField userTextField;
    private JLabel passLabel;
    private JPasswordField passField;
    private JButton loginButton;

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
                if (username.equals("admin") && password.equals("password")) {
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

    public static void main(String[] args) {
        // Create and display the UserLogin JFrame
        UserLogin frame = new UserLogin();
    }
}
