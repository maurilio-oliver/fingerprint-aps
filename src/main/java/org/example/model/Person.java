package org.example.model;

import java.util.Map;
import java.util.Objects;

public class Person {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private Integer level;
    private String pathFingerprint;

    public String getPathFingerprint() {
        return pathFingerprint;
    }

    public void setPathFingerprint(String pathFingerprint) {
        this.pathFingerprint = pathFingerprint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.replaceAll("_", " ");
    }

    public void setName(String name) {
        name = name.replaceAll("_", " ");
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", level=" + level +
                ", pathFingerprint='" + pathFingerprint + '\'' +
                '}';
    }
}
