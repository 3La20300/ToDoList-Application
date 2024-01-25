package com.example.todolist.Model;

public class Model {


    private  int id;
    private String task;
    private int status;

    public Model(int id, String task, int status) {
        this.id = id;
        this.task = task;
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
