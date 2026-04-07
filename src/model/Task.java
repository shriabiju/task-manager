package model;

public class Task {
    private String title;
    private String deadline;
    private String priority;
    private boolean completed;

    public Task(String title, String deadline, String priority) {
        this.title = title;
        this.deadline = deadline;
        this.priority = priority;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "<html><b>" + title + "</b><br>" +
               "Deadline: " + deadline + "<br>" +
               "Priority: " + priority + "<br>" +
               "Status: " + (completed ? "Completed" : "Pending") +
               "</html>";
    }
}