package org.example.model;

import java.util.Map;

public class Table {
    private Long id;
    private String name;
    private String description;
    private Map<String, Integer> level;
    private Map<String, String> field;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Integer> getLevel() {
        return level;
    }

    public void setLevel(Map<String, Integer> level) {
        this.level = level;
    }

    public Map<String, String> getField() {
        return field;
    }

    public void setField(Map<String, String> field) {
        this.field = field;
    }
}
