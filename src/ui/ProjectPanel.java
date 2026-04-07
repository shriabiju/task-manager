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
        setPreferredSize(new Dimension(260, 0));
        setBackground(new Color(15, 23, 32));

        // ===== TOP TITLE =====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(new Color(15, 23, 32));
        topPanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 15, 20));

        JLabel title = new JLabel("PROJECTS");
        title.setForeground(new Color(120, 140, 160));
        title.setFont(new Font("SansSerif", Font.BOLD, 13));

        JLabel sub = new JLabel("Manage your workspaces");
        sub.setForeground(new Color(170, 185, 200));
        sub.setFont(new Font("SansSerif", Font.PLAIN, 12));

        topPanel.add(title);
        topPanel.add(Box.createVerticalStrut(5));
        topPanel.add(sub);

        add(topPanel, BorderLayout.NORTH);

        // ===== LIST =====
        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);

        projectList.setFont(new Font("SansSerif", Font.BOLD, 15));
        projectList.setBackground(new Color(20, 30, 40));
        projectList.setForeground(Color.WHITE);
        projectList.setSelectionBackground(new Color(52, 152, 219));
        projectList.setSelectionForeground(Color.WHITE);
        projectList.setFixedCellHeight(45);
        projectList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        projectList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                taskPanel.loadTasksForProject(projectList.getSelectedIndex());
            }
        });

        JScrollPane scroll = new JScrollPane(projectList);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);

        // ===== BUTTON AREA =====
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 1, 10, 10));
        bottomPanel.setBackground(new Color(15, 23, 32));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton addBtn = new JButton("+ Add Project");
        JButton deleteBtn = new JButton("− Delete Project");

        styleButton(addBtn, new Color(46, 204, 113));
        styleButton(deleteBtn, new Color(231, 76, 60));

        addBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter Project Name:");
            if (name != null && !name.trim().isEmpty()) {
                taskService.addProject(name);
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
        for (Project p : projects) {
            projectListModel.addElement(p);
        }
    }

    private void styleButton(JButton btn, Color color) {
        btn.setFocusPainted(false);
        btn.setBackground(color);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
    }
}