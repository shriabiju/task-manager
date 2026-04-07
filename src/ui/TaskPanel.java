package ui;

import javax.swing.*;
import java.awt.*;

public class TaskPanel extends JPanel {

    DefaultListModel<String> taskListModel;

    public TaskPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Top panel
        JPanel topPanel = new JPanel();
        JTextField taskField = new JTextField(20);
        JButton addButton = new JButton("Add Task");

        topPanel.add(taskField);
        topPanel.add(addButton);

        add(topPanel, BorderLayout.NORTH);

        // Task list
        taskListModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(taskListModel);

        add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        JButton deleteBtn = new JButton("Delete Task");
        JButton completeBtn = new JButton("Mark Complete");

        bottomPanel.add(deleteBtn);
        bottomPanel.add(completeBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // Actions
        addButton.addActionListener(e -> {
            String task = taskField.getText();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskField.setText("");
            }
        });

        deleteBtn.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                taskListModel.remove(selected);
            }
        });

        completeBtn.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                String task = taskListModel.get(selected);
                taskListModel.set(selected, "✔ " + task);
            }
        });
    }
}