package com.example.taskmanage;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements Task {
    private Connection con;
    private int taskId;
    private String taskName;
    private String description;
    private Priority priority;
    private LocalDate deadline;
    private boolean isComplete;

    public TaskDAO(){
        String url = "jdbc:postgresql://localhost:5432/tasks";
        String username = "postgres";
        String pass = "123";
        try{
            this.con = DriverManager.getConnection(url, username, pass);
            System.out.println("database successfully connected");
        }
        catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    @Override
    public void createTask(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.description = taskDescription;
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
    public boolean isCompleted() {
        return false;
    }

    @Override
    public void markAsIncomplete() {
        this.isComplete = false;
    }
    @Override
    public void markAsComplete() {
        this.isComplete = true;
    }

    @Override
    public Priority getPriority() {
        return this.priority ;
    }

    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void setDeadline(LocalDate date) {
        this.deadline = date;
    }

    @Override
    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String getTaskDescription() {
        return this.description;
    }

    @Override
    public LocalDate getDeadline() {
        return this.deadline;
    }
    @Override
    public boolean isComplete() {
        return false;
    }

    public void insertTask(Task task) {
        String sql = "INSERT INTO tasks (task_name, task_description, deadline, priority, is_completed) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, task.getTaskName());
            stmt.setString(2, task.getTaskDescription());
            stmt.setDate(3, Date.valueOf(task.getDeadline()));
            stmt.setString(4, task.getPriority().toString()); // Assuming getPriority returns an enum value
            stmt.setBoolean(5, task.isComplete());
            stmt.executeUpdate();
            System.out.println("Task inserted successfully");
        } catch (SQLException e) {
            System.out.println("Insertion error: " + e.getMessage());
        }
    }


    public void updateTask(Task task) {
        String sql = "UPDATE tasks SET task_name = ?, task_description = ?, deadline = ?, priority = ?, is_complete = ? WHERE task_name = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, task.getTaskName());
            stmt.setString(2, task.getTaskDescription());
            stmt.setDate(3, Date.valueOf(task.getDeadline()));
            stmt.setString(4, task.getPriority().toString()); // Assuming getPriority returns an enum value
            stmt.setBoolean(5, task.isComplete());
            stmt.setString(6, task.getTaskName());
            stmt.executeUpdate();
            System.out.println("Task updated successfully");
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }


    public void deleteTask(Task task) {
        String sql = "DELETE FROM tasks WHERE task_name = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, task.getTaskName());
            stmt.executeUpdate();
            System.out.println("Task deleted successfully");
        } catch (SQLException e) {
            System.out.println("Deletion error: " + e.getMessage());
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Task task = new HomeworkTask(); // Instantiate your Task implementation
                // Populate task attributes from the ResultSet
                task.setTaskName(rs.getString("task_name"));
                task.setTaskDescription(rs.getString("task_description"));

                // Retrieve additional attributes from the ResultSet and set them to the Task object
                task.setDeadline(rs.getDate("deadline").toLocalDate()); // Assuming 'deadline' is a Date column
                task.setPriority(Priority.valueOf(rs.getString("priority"))); // Assuming 'priority' is a String column representing enum values
                if (rs.getBoolean("is_completed")) {
                    task.markAsComplete();
                } else {
                    task.markAsIncomplete();
                }

                // Set other attributes if needed

                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println("Retrieval error: " + e.getMessage());
        }
        return tasks;
    }




}

