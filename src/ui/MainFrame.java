package ui;

import service.TaskService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Project & Task Manager");
        setSize(1250, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        TaskService taskService = new TaskService();
        TaskPanel taskPanel = new TaskPanel(taskService);
        ProjectPanel projectPanel = new ProjectPanel(taskService, taskPanel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 244, 248));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(20, 30, 48));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 20));

        JLabel title = new JLabel("Project & Task Manager");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 30));

        JLabel subtitle = new JLabel("Organize projects, tasks, deadlines and priorities");
        subtitle.setForeground(new Color(200, 210, 220));
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));

        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitle);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(projectPanel, BorderLayout.WEST);
        mainPanel.add(taskPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);
    }
}