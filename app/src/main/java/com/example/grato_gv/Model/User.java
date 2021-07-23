package com.example.grato_gv.Model;

public class User {
    private String id;
    private String hashed_pass;
    private String name;
    private final String job_type = "GV";

    public User(String id, String hashed_pass, String name) {
        this.id = id;
        this.hashed_pass = hashed_pass;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob_type() {
        return job_type;
    }

}
