package ui;

import model.Task;
import service.TaskService;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskPanel extends JPanel {

    private TaskService taskService;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JTextField dateField;
    private JTextField timeField;
    private JComboBox<String> priorityBox;
    private int currentProjectIndex = -1;

    public TaskPanel(TaskService taskService) {
        this.taskService = taskService;

        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 248, 252));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // ===== TOP PANEL =====
        JPanel topPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        taskField = new JTextField();
        dateField = new JTextField("dd-mm-yyyy");
        timeField = new JTextField("hh:mm");
        priorityBox = new JComboBox<>(new String[]{"Low", "Medium", "High"});

        JButton addBtn = new JButton("Add Task");
        styleButton(addBtn, new Color(52, 152, 219));

        topPanel.add(new JLabel("Task"));
        topPanel.add(new JLabel("Date"));
        topPanel.add(new JLabel("Time"));
        topPanel.add(new JLabel("Priority"));
        topPanel.add(new JLabel(""));

        topPanel.add(taskField);
        topPanel.add(dateField);
        topPanel.add(timeField);
        topPanel.add(priorityBox);
        topPanel.add(addBtn);

        add(topPanel, BorderLayout.NORTH);

        // ===== LIST =====
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("SansSerif", Font.PLAIN, 15));
        taskList.setFixedCellHeight(60);
        taskList.setBackground(Color.WHITE);

        add(new JScrollPane(taskList), BorderLayout.CENTER);

        // ===== BOTTOM =====
        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(245, 248, 252));

        JButton del = new JButton("Delete");
        JButton done = new JButton("Complete");

        styleButton(del, new Color(231, 76, 60));
        styleButton(done, new Color(46, 204, 113));

        bottom.add(del);
        bottom.add(done);

        add(bottom, BorderLayout.SOUTH);

        // ===== ACTIONS =====
        addBtn.addActionListener(e -> {
            if (currentProjectIndex == -1) {
                JOptionPane.showMessageDialog(this, "Select project first");
                return;
            }

            String task = taskField.getText();
            String deadline = dateField.getText() + " " + timeField.getText();
            String priority = (String) priorityBox.getSelectedItem();

            if (!task.isEmpty()) {
                taskService.addTaskToProject(currentProjectIndex, task, deadline, priority);
                loadTasksForProject(currentProjectIndex);
                taskField.setText("");
            }
        });

        del.addActionListener(e -> {
            int i = taskList.getSelectedIndex();
            if (i != -1) {
                taskService.deleteTaskFromProject(currentProjectIndex, i);
                loadTasksForProject(currentProjectIndex);
            }
        });

        done.addActionListener(e -> {
            int i = taskList.getSelectedIndex();
            if (i != -1) {
                taskService.markTaskComplete(currentProjectIndex, i);
                loadTasksForProject(currentProjectIndex);
            }
        });
    }

    public void loadTasksForProject(int index) {
        currentProjectIndex = index;
        taskListModel.clear();
        List<Task> tasks = taskService.getTasksOfProject(index);
        for (Task t : tasks) {
            taskListModel.addElement(t.toString());
        }
    }

    public void clearTasks() {
        taskListModel.clear();
    }

    private void styleButton(JButton b, Color c) {
        b.setBackground(c);
        b.setForeground(Color.BLACK);
        b.setFocusPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 14));
    }
}