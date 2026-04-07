package service;

import model.Project;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private List<Project> projects;

    public TaskService() {
        projects = new ArrayList<>();
        projects.add(new Project("Personal"));
        projects.add(new Project("Work"));
        projects.add(new Project("College"));
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addProject(String name) {
        if (name != null && !name.trim().isEmpty()) {
            projects.add(new Project(name.trim()));
        }
    }

    public void deleteProject(int index) {
        if (index >= 0 && index < projects.size()) {
            projects.remove(index);
        }
    }

    public void addTaskToProject(int projectIndex, String title, String deadline, String priority) {
        if (projectIndex >= 0 && projectIndex < projects.size()) {
            if (title != null && !title.trim().isEmpty()) {
                projects.get(projectIndex).addTask(
                        new Task(title.trim(), deadline.trim(), priority)
                );
            }
        }
    }

    public void deleteTaskFromProject(int projectIndex, int taskIndex) {
        if (projectIndex >= 0 && projectIndex < projects.size()) {
            projects.get(projectIndex).deleteTask(taskIndex);
        }
    }

    public void markTaskComplete(int projectIndex, int taskIndex) {
        if (projectIndex >= 0 && projectIndex < projects.size()) {
            List<Task> tasks = projects.get(projectIndex).getTasks();
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markCompleted();
            }
        }
    }

    public void markTaskPending(int projectIndex, int taskIndex) {
        if (projectIndex >= 0 && projectIndex < projects.size()) {
            List<Task> tasks = projects.get(projectIndex).getTasks();
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markPending();
            }
        }
    }

    public List<Task> getTasksOfProject(int projectIndex) {
        if (projectIndex >= 0 && projectIndex < projects.size()) {
            return projects.get(projectIndex).getTasks();
        }
        return new ArrayList<>();
    }

    public int getTotalTaskCount() {
        int total = 0;
        for (Project project : projects) {
            total += project.getTasks().size();
        }
        return total;
    }

    public int getCompletedTaskCount() {
        int completed = 0;
        for (Project project : projects) {
            completed += project.getCompletedTaskCount();
        }
        return completed;
    }

    public int getPendingTaskCount() {
        return getTotalTaskCount() - getCompletedTaskCount();
    }
}