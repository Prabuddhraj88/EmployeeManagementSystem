import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminPanel {
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
                // TODO: Implement add employee functionality
            }
        });
        panel.add(addEmployeeBtn);

        JButton removeEmployeeBtn = new JButton("Remove Employee");
        removeEmployeeBtn.setPreferredSize(new Dimension(150, 50));
        removeEmployeeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement remove employee functionality
            }
        });
        panel.add(removeEmployeeBtn);

        JButton scrutinyJobApplicationsBtn = new JButton("Scrutiny Job Applications");
        scrutinyJobApplicationsBtn.setPreferredSize(new Dimension(200, 50));
        scrutinyJobApplicationsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement job applications scrutiny functionality
            }
        });
        panel.add(scrutinyJobApplicationsBtn);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AdminPanel();
    }
}
