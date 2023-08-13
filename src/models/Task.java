package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Task implements Serializable {
    private int id;
    static int counter = 1;
    private String name;
    private String description;
    private LocalDateTime creationTime;
    private LocalDateTime editTime;
    private LocalDateTime endTime;
    public ExecutionStatus status;

    public void setName(String name) {
        this.name = name;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.creationTime = LocalDateTime.now();
        this.status = ExecutionStatus.ACTIVE;
        id =counter++;
    }

    public int getId() {
        return id;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(ExecutionStatus status) {
        this.status = status;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public ExecutionStatus getStatus() {
        return status;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationTime=" + creationTime +
                ", endTime=" + endTime +
                ", status=" + status +
                '}';
    }
}
