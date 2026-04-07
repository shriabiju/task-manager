package ui;

import service.TaskService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Project & Task Manager");
        setSize(1300, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        TaskService taskService = new TaskService();
        TaskPanel taskPanel = new TaskPanel(taskService);
        ProjectPanel projectPanel = new ProjectPanel(taskService, taskPanel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(244, 247, 252));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(18, 32, 47));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(22, 32, 22, 20));

        JLabel title = new JLabel("Project & Task Manager");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 32));

        JLabel subtitle = new JLabel("Manage projects, tasks, deadlines and priorities efficiently");
        subtitle.setForeground(new Color(195, 205, 215));
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 15));

        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(6));
        headerPanel.add(subtitle);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(projectPanel, BorderLayout.WEST);
        mainPanel.add(taskPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);
    }
}