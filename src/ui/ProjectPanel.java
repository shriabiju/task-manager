package ui;

import model.Project;
import service.TaskService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ProjectPanel extends JPanel {

    private JList<Project> projectList;
    private DefaultListModel<Project> projectListModel;
    private final TaskService taskService;
    private final TaskPanel taskPanel;

    // Theme Colors
    private final Color SIDEBAR = new Color(28, 28, 38);
    private final Color LIST_BG = new Color(36, 36, 48);
    private final Color ACCENT = new Color(138, 92, 245);
    private final Color SUCCESS = new Color(76, 175, 80);
    private final Color DANGER = new Color(244, 67, 54);
    private final Color TEXT_PRIMARY = new Color(245, 245, 245);
    private final Color TEXT_SECONDARY = new Color(180, 180, 190);

    public ProjectPanel(TaskService taskService, TaskPanel taskPanel) {
        this.taskService = taskService;
        this.taskPanel = taskPanel;

        setLayout(new BorderLayout(0, 15));
        setPreferredSize(new Dimension(260, 0));
        setBackground(SIDEBAR);
        setBorder(new EmptyBorder(10, 5, 10, 5));

        // ===== TITLE SECTION =====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(SIDEBAR);
        topPanel.setBorder(new EmptyBorder(10, 10, 5, 10));

        JLabel title = new JLabel("MY PROJECTS");
        title.setForeground(TEXT_PRIMARY);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel sub = new JLabel("Manage your workspaces");
        sub.setForeground(TEXT_SECONDARY);
        sub.setFont(new Font("SansSerif", Font.PLAIN, 12));

        topPanel.add(title);
        topPanel.add(Box.createVerticalStrut(4));
        topPanel.add(sub);

        add(topPanel, BorderLayout.NORTH);

        // ===== PROJECT LIST =====
        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);

        projectList.setFont(new Font("SansSerif", Font.PLAIN, 15));
        projectList.setBackground(LIST_BG);
        projectList.setForeground(TEXT_PRIMARY);
        projectList.setSelectionBackground(ACCENT);
        projectList.setSelectionForeground(Color.WHITE);
        projectList.setFixedCellHeight(42);
        projectList.setBorder(new EmptyBorder(10, 10, 10, 10));
        projectList.setCellRenderer(new ProjectListRenderer());

        projectList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = projectList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskPanel.loadTasksForProject(selectedIndex);
                }
            }
        });

        JScrollPane scroll = new JScrollPane(projectList);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(LIST_BG);
        scroll.setBackground(LIST_BG);

        add(scroll, BorderLayout.CENTER);

        // ===== BUTTON AREA =====
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 0, 12));
        bottomPanel.setBackground(SIDEBAR);
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton addBtn = new JButton("＋ Add Project");
        JButton deleteBtn = new JButton("🗑 Delete Project");

        styleButton(addBtn, SUCCESS);
        styleButton(deleteBtn, DANGER);

        addBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter Project Name:");
            if (name != null && !name.trim().isEmpty()) {
                taskService.addProject(name.trim());
                refreshProjectList();

                // Auto-select latest project
                int lastIndex = projectListModel.getSize() - 1;
                if (lastIndex >= 0) {
                    projectList.setSelectedIndex(lastIndex);
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            int selected = projectList.getSelectedIndex();
            if (selected != -1) {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this project?",
                        "Delete Project",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    taskService.deleteProject(selected);
                    refreshProjectList();
                    taskPanel.clearTasks();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a project to delete.");
            }
        });

        bottomPanel.add(addBtn);
        bottomPanel.add(deleteBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        refreshProjectList();

        // Auto-select first project if available
        if (!projectListModel.isEmpty()) {
            projectList.setSelectedIndex(0);
        }
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
    btn.setForeground(Color.WHITE);
    btn.setFont(new Font("SansSerif", Font.BOLD, 14));
    btn.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
    btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

    // IMPORTANT FIX
    btn.setOpaque(true);
    btn.setContentAreaFilled(true);
    btn.setBorderPainted(false);
}
    // Custom renderer for better list appearance
    private class ProjectListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus
            );

            label.setText("📁 " + value.toString());
            label.setFont(new Font("SansSerif", Font.PLAIN, 15));
            label.setBorder(new EmptyBorder(8, 12, 8, 12));

            if (isSelected) {
                label.setBackground(ACCENT);
                label.setForeground(Color.WHITE);
            } else {
                label.setBackground(LIST_BG);
                label.setForeground(TEXT_PRIMARY);
            }

            return label;
        }
    }
}