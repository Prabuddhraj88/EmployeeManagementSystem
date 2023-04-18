import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RemoveEmployee extends JFrame {

    // Initialize the list of employees with sample data
    private String[] employees = {"JohnDoe", "JaneSmith", "BobJohnson"};

    private JList<String> employeeList;
    private DefaultListModel<String> employeeListModel;

    public RemoveEmployee() {
        super("Employee Removal");

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
                if (selectedIndex != -1) {
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

    public static void main(String[] args) {
        // Create and display the RemoveEmployee JFrame
        RemoveEmployee frame = new RemoveEmployee();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
