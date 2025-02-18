package com.tkt.spin_wheel.database.design;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "design")
public class DesignModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String path;

    public DesignModel(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
