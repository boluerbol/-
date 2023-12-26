package com.example.taskmanage;

import java.time.LocalDate;

public class HomeworkTask implements Task{

    private String taskName;
    private String taskType;
    private String description;
    private boolean completed;
    private Priority priority;
    private LocalDate deadline;
    private int taskId;

    @Override
    public void createTask(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.description = taskDescription;
        this.completed = false;
    }

    @Override
    public String toString() {
        // Customize the string representation for HomeworkTask
        String completedStatus = completed ? "completed" : "not completed";
        return taskType + ": " + taskName + " - " + deadline + " - " + completedStatus;
    }


    @Override
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    @Override
    public void setTaskDescription(String taskDescription) {
        this.description = taskDescription;
    }
    @Override
    public boolean isCompleted() {return false;}
    @Override
    public void markAsComplete() {
        this.completed = true;
    }
    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    @Override
    public void setDeadline(LocalDate date) {
        this.deadline = date;
    }
    public String getTaskName() {
        return taskName;
    }
    @Override
    public String getTaskDescription() {
        return description;
    }
    public Priority getPriority() {
        return priority;
    }
    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public boolean isComplete() {return false;}
    public void markAsIncomplete() {
        this.completed = false;
    }
    public boolean getCompleted()
    {
        return completed;
    }
}
