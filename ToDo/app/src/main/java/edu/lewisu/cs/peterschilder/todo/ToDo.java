package edu.lewisu.cs.peterschilder.todo;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Peter Schilder on 2/11/2016.
 */
public class ToDo {
    private   UUID id;
    private String title;
    private Date dueDate;
    int priority;
    boolean complete;

    public ToDo() {
        id = UUID.randomUUID();
        dueDate = new Date();
    }

    public ToDo(String title, int priority, boolean complete) {
        id = UUID.randomUUID();
        dueDate = new Date();
        this.title = title;
        this.priority = priority;
        this.complete = complete;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
