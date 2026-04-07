package ui;

import service.TaskService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainFrame extends JFrame {

    private JLabel timeLabel;
    private JLabel dateLabel;

    // Theme Colors
    private final Color BACKGROUND = new Color(18, 18, 24);
    private final Color SIDEBAR = new Color(28, 28, 38);
    private final Color HEADER = new Color(36, 36, 48);
    private final Color ACCENT = new Color(138, 92, 245);
    private final Color TEXT_PRIMARY = new Color(245, 245, 245);
    private final Color TEXT_SECONDARY = new Color(180, 180, 190);
    private final Color CONTENT_BG = new Color(245, 247, 250);

    public MainFrame() {
        setTitle("Project & Task Manager");
        setSize(1350, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1200, 720));

        TaskService taskService = new TaskService();
        TaskPanel taskPanel = new TaskPanel(taskService);
        ProjectPanel projectPanel = new ProjectPanel(taskService, taskPanel);

        // Main Container
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(BACKGROUND);

        // Sidebar Wrapper
        JPanel sidebarWrapper = new JPanel(new BorderLayout());
        sidebarWrapper.setBackground(SIDEBAR);
        sidebarWrapper.setPreferredSize(new Dimension(300, getHeight()));
        sidebarWrapper.setBorder(new EmptyBorder(20, 20, 20, 15));

        // App Branding
        JPanel brandPanel = new JPanel();
        brandPanel.setLayout(new BoxLayout(brandPanel, BoxLayout.Y_AXIS));
        brandPanel.setBackground(SIDEBAR);
        brandPanel.setBorder(new EmptyBorder(10, 10, 25, 10));

        JLabel appName = new JLabel("TaskZen");
        appName.setForeground(Color.WHITE);
        appName.setFont(new Font("SansSerif", Font.BOLD, 28));

        JLabel appTagline = new JLabel("Plan smarter. Work better.");
        appTagline.setForeground(TEXT_SECONDARY);
        appTagline.setFont(new Font("SansSerif", Font.PLAIN, 13));

        brandPanel.add(appName);
        brandPanel.add(Box.createVerticalStrut(5));
        brandPanel.add(appTagline);

        // Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(4, 1, 0, 12));
        navPanel.setBackground(SIDEBAR);
        navPanel.setBorder(new EmptyBorder(0, 5, 20, 5));

        navPanel.add(createNavButton("📋 Dashboard"));
        navPanel.add(createNavButton("🗂 Projects"));
        navPanel.add(createNavButton("✅ Completed"));
        navPanel.add(createNavButton("⚙ Settings"));

        // Wrap existing ProjectPanel inside sidebar section
        JPanel projectSection = new JPanel(new BorderLayout());
        projectSection.setBackground(SIDEBAR);
        projectSection.setBorder(new EmptyBorder(10, 0, 0, 0));
        projectSection.add(projectPanel, BorderLayout.CENTER);

        sidebarWrapper.add(brandPanel, BorderLayout.NORTH);

        JPanel sidebarCenter = new JPanel(new BorderLayout());
        sidebarCenter.setBackground(SIDEBAR);
        sidebarCenter.add(navPanel, BorderLayout.NORTH);
        sidebarCenter.add(projectSection, BorderLayout.CENTER);

        sidebarWrapper.add(sidebarCenter, BorderLayout.CENTER);

        // Right Main Content Area
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(CONTENT_BG);

        // Top Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(HEADER);
        headerPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Left Header Text
        JPanel headerTextPanel = new JPanel();
        headerTextPanel.setLayout(new BoxLayout(headerTextPanel, BoxLayout.Y_AXIS));
        headerTextPanel.setBackground(HEADER);

        JLabel title = new JLabel("Welcome Back 👋");
        title.setForeground(TEXT_PRIMARY);
        title.setFont(new Font("SansSerif", Font.BOLD, 30));

        JLabel subtitle = new JLabel("Manage your projects, tasks, priorities and deadlines efficiently");
        subtitle.setForeground(TEXT_SECONDARY);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 15));

        headerTextPanel.add(title);
        headerTextPanel.add(Box.createVerticalStrut(6));
        headerTextPanel.add(subtitle);

        // Right Clock Card
        JPanel dateTimePanel = new JPanel();
        dateTimePanel.setLayout(new BoxLayout(dateTimePanel, BoxLayout.Y_AXIS));
        dateTimePanel.setBackground(ACCENT);
        dateTimePanel.setBorder(new EmptyBorder(12, 22, 12, 22));

        dateLabel = new JLabel();
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        timeLabel = new JLabel();
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dateTimePanel.add(dateLabel);
        dateTimePanel.add(Box.createVerticalStrut(6));
        dateTimePanel.add(timeLabel);

        updateDateTime();
        startClock();

        headerPanel.add(headerTextPanel, BorderLayout.WEST);
        headerPanel.add(dateTimePanel, BorderLayout.EAST);

        // Center task content
        JPanel taskWrapper = new JPanel(new BorderLayout());
        taskWrapper.setBackground(CONTENT_BG);
        taskWrapper.setBorder(new EmptyBorder(20, 20, 20, 20));
        taskWrapper.add(taskPanel, BorderLayout.CENTER);

        rightPanel.add(headerPanel, BorderLayout.NORTH);
        rightPanel.add(taskWrapper, BorderLayout.CENTER);

        rootPanel.add(sidebarWrapper, BorderLayout.WEST);
        rootPanel.add(rightPanel, BorderLayout.CENTER);

        setContentPane(rootPanel);
    }

    private JButton createNavButton(String text) {
    JButton button = new JButton(text);
    button.setFocusPainted(false);
    button.setBackground(new Color(245, 245, 245)); // light button
    button.setForeground(new Color(40, 40, 40));    // DARK TEXT (FIX)
    button.setFont(new Font("SansSerif", Font.BOLD, 16));
    button.setHorizontalAlignment(SwingConstants.LEFT);
    button.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    return button;
}

    private void updateDateTime() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        dateLabel.setText(today.format(DateTimeFormatter.ofPattern("EEEE, d MMM yyyy")));
        timeLabel.setText(now.format(DateTimeFormatter.ofPattern("hh:mm:ss a")));
    }

    private void startClock() {
        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();
    }
}