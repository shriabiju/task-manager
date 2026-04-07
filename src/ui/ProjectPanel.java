package ui;

import model.Project;
import service.TaskService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProjectPanel extends JPanel {

    private JList<Project> projectList;
    private DefaultListModel<Project> projectListModel;
    private TaskService taskService;
    private TaskPanel taskPanel;

    public ProjectPanel(TaskService taskService, TaskPanel taskPanel) {
        this.taskService = taskService;
        this.taskPanel = taskPanel;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 0));
        setBackground(new Color(28, 37, 47));

        JLabel title = new JLabel("Projects");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 15, 20));
        add(title, BorderLayout.NORTH);

        projectListModel = new DefaultListModel<>();

        projectList = new JList<>(projectListModel);
        projectList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        projectList.setBackground(new Color(40, 55, 71));
        projectList.setForeground(Color.WHITE);
        projectList.setSelectionBackground(new Color(52, 152, 219));
        projectList.setSelectionForeground(Color.WHITE);
        projectList.setFixedCellHeight(40);

        projectList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = projectList.getSelectedIndex();
                taskPanel.loadTasksForProject(selectedIndex);
            }
        });

        JScrollPane scrollPane = new JScrollPane(projectList);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        bottomPanel.setBackground(new Color(28, 37, 47));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton addBtn = new JButton("Add Project");
        styleButton(addBtn, new Color(46, 204, 113));

        JButton deleteBtn = new JButton("Delete Project");
        styleButton(deleteBtn, new Color(231, 76, 60));

        addBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter Project Name:");
            if (name != null && !name.trim().isEmpty()) {
                taskService.addProject(name.trim());
                refreshProjectList();
            }
        });

        deleteBtn.addActionListener(e -> {
            int selected = projectList.getSelectedIndex();
            if (selected != -1) {
                taskService.deleteProject(selected);
                refreshProjectList();
                taskPanel.clearTasks();
            }
        });

        bottomPanel.add(addBtn);
        bottomPanel.add(deleteBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        refreshProjectList();
    }

    public void refreshProjectList() {
        projectListModel.clear();
        List<Project> projects = taskService.getProjects();
        for (Project project : projects) {
            projectListModel.addElement(project);
        }
    }

    private void styleButton(JButton button, Color bg) {
        button.setFocusPainted(false);
        button.setBackground(bg);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
    }
}