package ui;

import service.TaskService;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TaskPanel extends JPanel {

    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private TaskService taskService;

    public TaskPanel() {
        taskService = new TaskService();

        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 241));

        // Top Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        topPanel.setBackground(new Color(236, 240, 241));

        taskField = new JTextField(20);
        taskField.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JButton addButton = new JButton("Add Task");
        addButton.setFocusPainted(false);
        addButton.setBackground(new Color(52, 152, 219));
        addButton.setForeground(Color.BLACK);
        addButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        topPanel.add(taskField);
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);

        // Task List
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        taskList.setSelectionBackground(new Color(174, 214, 241));

        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(new Color(236, 240, 241));

        JButton deleteBtn = new JButton("Delete Task");
        deleteBtn.setFocusPainted(false);
        deleteBtn.setBackground(new Color(231, 76, 60));
        deleteBtn.setForeground(Color.BLACK);
        deleteBtn.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton completeBtn = new JButton("Mark Complete");
        completeBtn.setFocusPainted(false);
        completeBtn.setBackground(new Color(46, 204, 113));
        completeBtn.setForeground(Color.BLACK);
        completeBtn.setFont(new Font("SansSerif", Font.BOLD, 14));

        bottomPanel.add(deleteBtn);
        bottomPanel.add(completeBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> {
            String text = taskField.getText().trim();
            if (!text.isEmpty()) {
                taskService.addTask(text);
                refreshList();
                taskField.setText("");
            }
        });

        deleteBtn.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                taskService.deleteTask(selected);
                refreshList();
            }
        });

        completeBtn.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                taskService.markComplete(selected);
                refreshList();
            }
        });
    }

    private void refreshList() {
        taskListModel.clear();
        List<Task> tasks = taskService.getTasks();
        for (Task task : tasks) {
            taskListModel.addElement(task.toString());
        }
    }
}