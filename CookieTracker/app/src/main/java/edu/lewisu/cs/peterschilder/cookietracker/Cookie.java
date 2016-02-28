package edu.lewisu.cs.peterschilder.cookietracker;

import java.util.UUID;

/**
 * Created by Peter Schilder on 2/27/2016.
 */
public class Cookie {
    private UUID id;
    private String name;
    private String topping;
    private String shape;
    private int category;
    private boolean healthy;

    public Cookie() {
        id = UUID.randomUUID();
    }

    public Cookie(String name, String topping, String shape, int category, boolean healthy) {
        id = UUID.randomUUID();
        this.name = name;
        this.topping = topping;
        this.shape = shape;
        this.category = category;
        this.healthy = healthy;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }
}
