package ui;

import model.Task;
import service.TaskService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TaskPanel extends JPanel {

    private TaskService taskService;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JTextField deadlineField;
    private JComboBox<String> priorityBox;
    private int currentProjectIndex = -1;

    public TaskPanel(TaskService taskService) {
        this.taskService = taskService;

        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 247, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Form Panel
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Task Dashboard");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(44, 62, 80));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        topPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        taskField = new JTextField();
        deadlineField = new JTextField();
        priorityBox = new JComboBox<>(new String[]{"Low", "Medium", "High"});

        taskField.setPreferredSize(new Dimension(220, 35));
        deadlineField.setPreferredSize(new Dimension(150, 35));
        priorityBox.setPreferredSize(new Dimension(120, 35));

        gbc.gridy = 1;
        gbc.gridx = 0;
        topPanel.add(new JLabel("Task Name"), gbc);
        gbc.gridx = 1;
        topPanel.add(new JLabel("Deadline"), gbc);
        gbc.gridx = 2;
        topPanel.add(new JLabel("Priority"), gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        topPanel.add(taskField, gbc);
        gbc.gridx = 1;
        topPanel.add(deadlineField, gbc);
        gbc.gridx = 2;
        topPanel.add(priorityBox, gbc);

        JButton addButton = new JButton("Add Task");
        styleButton(addButton, new Color(52, 152, 219));
        gbc.gridx = 3;
        topPanel.add(addButton, gbc);

        add(topPanel, BorderLayout.NORTH);

        // Task List
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("SansSerif", Font.PLAIN, 15));
        taskList.setSelectionBackground(new Color(220, 235, 250));
        taskList.setFixedCellHeight(45);

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Tasks"));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(new Color(245, 247, 250));

        JButton deleteBtn = new JButton("Delete Task");
        styleButton(deleteBtn, new Color(231, 76, 60));

        JButton completeBtn = new JButton("Mark Complete");
        styleButton(completeBtn, new Color(46, 204, 113));

        bottomPanel.add(deleteBtn);
        bottomPanel.add(completeBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // Actions
        addButton.addActionListener(e -> {
            if (currentProjectIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a project first.");
                return;
            }

            String title = taskField.getText().trim();
            String deadline = deadlineField.getText().trim();
            String priority = (String) priorityBox.getSelectedItem();

            if (!title.isEmpty() && !deadline.isEmpty()) {
                taskService.addTaskToProject(currentProjectIndex, title, deadline, priority);
                loadTasksForProject(currentProjectIndex);
                taskField.setText("");
                deadlineField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            }
        });

        deleteBtn.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1 && currentProjectIndex != -1) {
                taskService.deleteTaskFromProject(currentProjectIndex, selected);
                loadTasksForProject(currentProjectIndex);
            }
        });

        completeBtn.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1 && currentProjectIndex != -1) {
                taskService.markTaskComplete(currentProjectIndex, selected);
                loadTasksForProject(currentProjectIndex);
            }
        });
    }

    public void loadTasksForProject(int projectIndex) {
        currentProjectIndex = projectIndex;
        taskListModel.clear();

        List<Task> tasks = taskService.getTasksOfProject(projectIndex);
        for (Task task : tasks) {
            taskListModel.addElement(task.toString());
        }
    }

    public void clearTasks() {
        currentProjectIndex = -1;
        taskListModel.clear();
    }

    private void styleButton(JButton button, Color bg) {
        button.setFocusPainted(false);
        button.setBackground(bg);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 40));
    }
}