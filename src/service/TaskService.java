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
        projects.add(new Project(name));
    }

    public void deleteProject(int index) {
        if (index >= 0 && index < projects.size()) {
            projects.remove(index);
        }
    }

    public void addTaskToProject(int projectIndex, String title, String deadline, String priority) {
        if (projectIndex >= 0 && projectIndex < projects.size()) {
            projects.get(projectIndex).addTask(new Task(title, deadline, priority));
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

    public List<Task> getTasksOfProject(int projectIndex) {
        if (projectIndex >= 0 && projectIndex < projects.size()) {
            return projects.get(projectIndex).getTasks();
        }
        return new ArrayList<>();
    }
}