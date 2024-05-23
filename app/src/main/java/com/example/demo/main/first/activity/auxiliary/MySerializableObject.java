package com.example.demo.main.first.activity.auxiliary;

import java.io.Serializable;

public class MySerializableObject implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MySerializableObject() {
    }

    public MySerializableObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "MySerializableObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
