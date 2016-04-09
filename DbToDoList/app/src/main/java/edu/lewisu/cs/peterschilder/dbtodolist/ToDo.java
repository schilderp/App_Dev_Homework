package edu.lewisu.cs.peterschilder.dbtodolist;

import java.util.UUID;

/**
 * Created by Peter Schilder on 2/8/16.
 */
public class ToDo {
    private UUID id;
    private String title;
    private int priority;
    private boolean complete;

    public ToDo() {
        id = UUID.randomUUID();
    }

    public ToDo(String title, int priority, boolean complete) {
        id = UUID.randomUUID();
        this.title = title;
        this.priority = priority;
        this.complete = complete;
    }

    public ToDo(String uuid, String title, int priority, int complete) {
        id = UUID.fromString(uuid);
        this.title = title;
        this.priority = priority;
        if(complete== 0){
            this.complete = false;
        }else {
            this.complete = true;
        }

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
