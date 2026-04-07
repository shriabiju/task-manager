package ui;

import model.Task;
import service.TaskService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TaskPanel extends JPanel {

    private final TaskService taskService;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;

    private JTextField taskField;
    private JTextField dateField;
    private JTextField timeField;
    private JComboBox<String> priorityBox;

    private int currentProjectIndex = -1;

    // Theme
    private final Color BG = new Color(245, 247, 250);
    private final Color CARD = new Color(255, 255, 255);
    private final Color ACCENT = new Color(138, 92, 245);
    private final Color SUCCESS = new Color(76, 175, 80);
    private final Color DANGER = new Color(244, 67, 54);
    private final Color TEXT_PRIMARY = new Color(40, 40, 40);
    private final Color TEXT_SECONDARY = new Color(120, 120, 130);

    public TaskPanel(TaskService taskService) {
        this.taskService = taskService;

        setLayout(new BorderLayout(20, 20));
        setBackground(BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // ===== INPUT CARD =====
        JPanel inputCard = new JPanel(new GridLayout(2, 5, 12, 12));
        inputCard.setBackground(CARD);
        inputCard.setBorder(new EmptyBorder(20, 20, 20, 20));

        taskField = createTextField("Enter task...");
        dateField = createTextField("dd-mm-yyyy");
        timeField = createTextField("hh:mm");

        priorityBox = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        priorityBox.setBackground(CARD);
        priorityBox.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JButton addBtn = new JButton("＋ Add Task");
        styleButton(addBtn, ACCENT);

        inputCard.add(createLabel("Task"));
        inputCard.add(createLabel("Date"));
        inputCard.add(createLabel("Time"));
        inputCard.add(createLabel("Priority"));
        inputCard.add(new JLabel(""));

        inputCard.add(taskField);
        inputCard.add(dateField);
        inputCard.add(timeField);
        inputCard.add(priorityBox);
        inputCard.add(addBtn);

        add(inputCard, BorderLayout.NORTH);

        // ===== TASK LIST =====
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setCellRenderer(new TaskRenderer());
        taskList.setFixedCellHeight(80);
        taskList.setBackground(BG);

        JScrollPane scroll = new JScrollPane(taskList);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(BG);

        add(scroll, BorderLayout.CENTER);

        // ===== BOTTOM BUTTONS =====
        JPanel bottom = new JPanel();
        bottom.setBackground(BG);
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));

        JButton del = new JButton("🗑 Delete");
        JButton done = new JButton("✔ Complete");

        styleButton(del, DANGER);
        styleButton(done, SUCCESS);

        bottom.add(done);
        bottom.add(del);

        add(bottom, BorderLayout.SOUTH);

        // ===== ACTIONS =====
        addBtn.addActionListener(e -> addTask());
        del.addActionListener(e -> deleteTask());
        done.addActionListener(e -> completeTask());
    }

    // ===== ACTION METHODS =====
    private void addTask() {
        if (currentProjectIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a project first.");
            return;
        }

        String task = taskField.getText().trim();
        String deadline = dateField.getText() + " " + timeField.getText();
        String priority = (String) priorityBox.getSelectedItem();

        if (!task.isEmpty()) {
            taskService.addTaskToProject(currentProjectIndex, task, deadline, priority);
            loadTasksForProject(currentProjectIndex);
            taskField.setText("");
        }
    }

    private void deleteTask() {
        int i = taskList.getSelectedIndex();
        if (i != -1) {
            taskService.deleteTaskFromProject(currentProjectIndex, i);
            loadTasksForProject(currentProjectIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to delete.");
        }
    }

    private void completeTask() {
        int i = taskList.getSelectedIndex();
        if (i != -1) {
            taskService.markTaskComplete(currentProjectIndex, i);
            loadTasksForProject(currentProjectIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to complete.");
        }
    }

    // ===== LOAD METHODS =====
    public void loadTasksForProject(int index) {
        currentProjectIndex = index;
        taskListModel.clear();

        List<Task> tasks = taskService.getTasksOfProject(index);
        for (Task t : tasks) {
            taskListModel.addElement(t);
        }
    }

    public void clearTasks() {
        taskListModel.clear();
    }

    private JTextField createTextField(String placeholder) {
    JTextField field = new JTextField(placeholder);
    field.setFont(new Font("SansSerif", Font.PLAIN, 14));
    field.setBackground(new Color(245, 245, 250));
    field.setForeground(new Color(40, 40, 40)); // FIXED text visibility
    field.setCaretColor(new Color(40, 40, 40));
    field.setBorder(new EmptyBorder(10, 10, 10, 10));
    return field;
}

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(TEXT_SECONDARY);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        return label;
    }

   private void styleButton(JButton b, Color c) {
    b.setBackground(c);
    b.setForeground(Color.WHITE);
    b.setFocusPainted(false);
    b.setFont(new Font("SansSerif", Font.BOLD, 14));
    b.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
    b.setCursor(new Cursor(Cursor.HAND_CURSOR));

    // IMPORTANT FIX
    b.setOpaque(true);
    b.setContentAreaFilled(true);
    b.setBorderPainted(false);
}

    // ===== CUSTOM TASK CARD RENDERER =====
    class TaskRenderer extends JPanel implements ListCellRenderer<Task> {

        private JLabel title;
        private JLabel details;

        public TaskRenderer() {
            setLayout(new BorderLayout(5, 5));
            setBorder(new EmptyBorder(10, 15, 10, 15));

            title = new JLabel();
            title.setFont(new Font("SansSerif", Font.BOLD, 15));

            details = new JLabel();
            details.setFont(new Font("SansSerif", Font.PLAIN, 12));
            details.setForeground(TEXT_SECONDARY);

            add(title, BorderLayout.NORTH);
            add(details, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Task> list, Task task,
                                                      int index, boolean isSelected, boolean cellHasFocus) {

            // Title
            if (task.isCompleted()) {
                title.setText("<html><strike>✔ " + task.getTitle() + "</strike></html>");
                title.setForeground(new Color(120, 120, 120));
                setBackground(new Color(235, 255, 235));
            } else {
                title.setText(task.getTitle());
                title.setForeground(TEXT_PRIMARY);
                setBackground(Color.WHITE);
            }

            // Details
            details.setText("📅 " + task.getDeadline() + "   |   ⚡ " + task.getPriority());

            // Selection
            if (isSelected) {
                setBorder(BorderFactory.createLineBorder(ACCENT, 2));
            } else {
                setBorder(new EmptyBorder(10, 15, 10, 15));
            }

            return this;
        }
    }
}