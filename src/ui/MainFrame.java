package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Project & Task Manager");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        // Header
        JLabel header = new JLabel("Project & Task Manager", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 26));
        header.setOpaque(true);
        header.setBackground(new Color(44, 62, 80));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(1000, 70));

        // Panels
        ProjectPanel projectPanel = new ProjectPanel();
        TaskPanel taskPanel = new TaskPanel();

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(projectPanel, BorderLayout.WEST);
        mainPanel.add(taskPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);
    }
}