package com.example.taskmanage;
import java.time.LocalDate;

public interface Task {
    void createTask(String taskName, String taskDescription);
    void setTaskName(String taskName);
    void setTaskDescription(String taskDescription);
    boolean isCompleted();
    void markAsIncomplete();
    void markAsComplete();
    Priority getPriority();
    void setPriority(Priority priority);
    void setDeadline(LocalDate date);
    String getTaskName();
    String getTaskDescription();
    LocalDate getDeadline();
    boolean isComplete();
}
