import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
                //TODO: add action for admin login button
            }
        });
        punchAttendanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO: add action for punch attendance button
            }
        });
        applyForJobButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO: add action for apply for job button
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
    }
}
