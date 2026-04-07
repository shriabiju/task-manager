package ui;

import javax.swing.*;
import java.awt.*;

public class ProjectPanel extends JPanel {

    public ProjectPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(220, 0));
        setBackground(new Color(44, 62, 80));

        // Title
        JLabel title = new JLabel("Projects");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(title, BorderLayout.NORTH);

        // Project List
        DefaultListModel<String> projectListModel = new DefaultListModel<>();
        projectListModel.addElement("Personal");
        projectListModel.addElement("Work");
        projectListModel.addElement("College");

        JList<String> projectList = new JList<>(projectListModel);
        projectList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        projectList.setBackground(new Color(52, 73, 94));
        projectList.setForeground(Color.WHITE);
        projectList.setSelectionBackground(new Color(26, 188, 156));
        projectList.setSelectionForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(projectList);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // Add Project Button
        JButton addBtn = new JButton("Add Project");
        addBtn.setFocusPainted(false);
        addBtn.setBackground(new Color(26, 188, 156));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("SansSerif", Font.BOLD, 14));

        addBtn.addActionListener(e -> {
            String projectName = JOptionPane.showInputDialog(this, "Enter Project Name:");
            if (projectName != null && !projectName.trim().isEmpty()) {
                projectListModel.addElement(projectName);
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(44, 62, 80));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(addBtn, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}