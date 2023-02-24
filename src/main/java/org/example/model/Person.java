package org.example.model;

import java.util.Map;
import java.util.Objects;

public class Person {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private Map<String, Integer> level;
    private Long fingerPrintId;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Map<String, Integer> getLevel() {
        return level;
    }

    public void setLevel(Map<String, Integer> level) {
        this.level = level;
    }

    public Long getFingerPrintId() {
        return fingerPrintId;
    }

    public void setFingerPrintId(Long fingerPrintId) {
        this.fingerPrintId = fingerPrintId;
    }
}
