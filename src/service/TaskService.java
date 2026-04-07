package service;

import model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private List<Task> tasks = new ArrayList<>();

    public void addTask(String title) {
        tasks.add(new Task(title));
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public void markComplete(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markCompleted();
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }
}