package models;

public enum ExecutionStatus {
    ACTIVE("Task is active"),
    FINISHED("Task was finished"),
    CANCELED("Task was canceled");
    private String mark
    ;

    ExecutionStatus(String mark) {
        this.mark = mark;

    }
}
