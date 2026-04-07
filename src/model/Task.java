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
        return title + " | Deadline: " + deadline + " | Priority: " + priority + " | " +
                (completed ? "Completed" : "Pending");
    }
}