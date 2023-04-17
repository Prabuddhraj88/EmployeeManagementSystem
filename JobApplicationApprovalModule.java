import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JobApplicationApprovalModule extends JFrame {
    
    // Initialize the list of job applications with sample data
    private String[][] jobApplications = {
        {"John", "Doe", "25", "ABC Inc.", "johndoe@gmail.com", "linkedin.com/in/johndoe", "555-1234", "123 Main St"},
        {"Jane", "Smith", "30", "XYZ Corp.", "janesmith@gmail.com", "linkedin.com/in/janesmith", "555-5678", "456 Oak Ave"},
        {"Bob", "Johnson", "40", "123 Co.", "bobjohnson@gmail.com", "linkedin.com/in/bobjohnson", "555-9012", "789 Elm St"},
        {"Alice", "Lee", "27", "DEF Corp.", "alicelee@gmail.com", "linkedin.com/in/alicelee", "555-2345", "234 Maple St"},
        {"Mike", "Chen", "35", "456 Co.", "mikechen@gmail.com", "linkedin.com/in/mikechen", "555-6789", "567 Pine St"}
    };
    
    
    private JList<String[]> jobApplicationsList;
    private DefaultListModel<String[]> jobApplicationsListModel;
    
    public JobApplicationApprovalModule() {
        super("Job Application Approval");
        
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
                if (selectedIndex != -1) {
                    jobApplicationsListModel.remove(selectedIndex);
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
                    jobApplicationsListModel.remove(selectedIndex);
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
            String company = jobApplication[3];
            String email = jobApplication[4];
            String linkedin = jobApplication[5];
            String contactNumber = jobApplication[6];
            String address = jobApplication[7];
            label.setText(fullName + ", " + age + " years old, " + company + ", " + email + ", " + linkedin + ", " + contactNumber + ", " + address);
            return label;
        }
    }
    
    public static void main(String[] args) {
        new JobApplicationApprovalModule();
    }
}
