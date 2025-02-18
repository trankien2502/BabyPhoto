package com.tkt.spin_wheel.database.icon;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "icon")
public class IconModel implements Serializable {
    public static final int COLOR_NONE = 0;
    public static final int COLOR_WHITE = 1;
    public static final int COLOR_BLACK = 2;
    public static final int COLOR_GRAY = 3;
    public static final int COLOR_ORANGE = 4;
    public static final int COLOR_BROWN = 5;
    public static final int COLOR_YELLOW = 6;

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String path;
    private int source;
    private boolean isSelect;
    private String category;
    private int color;
    private boolean isAvailable;


    public IconModel() {
        this.path = "";
        this.isSelect = true;
        this.source = -1;
        this.color = COLOR_NONE;
        this.isAvailable = true;
    }


    public IconModel(String path) {
        this.path = path;
        this.isSelect = false;
        this.source = 0;
        this.color = COLOR_NONE;
        this.isAvailable = false;
    }

    public IconModel(String path, String category) {
        this.path = path;
        this.isSelect = false;
        this.category = category;
        this.source = 0;
        this.color = COLOR_NONE;
        this.isAvailable = false;
    }

    public IconModel(int source) {
        this.source = source;
        this.path = "";
        this.isSelect = false;
        this.color = COLOR_NONE;
        this.isAvailable = false;
    }

    public IconModel(int source, boolean isSelect) {
        this.source = source;
        this.path = "";
        this.isSelect = isSelect;
        this.color = COLOR_NONE;
        this.isAvailable = false;
    }


    public IconModel(String path, int color) {
        this.path = path;
        this.isSelect = false;
        this.color = color;
        this.isAvailable = false;
    }

    public IconModel(String path, String category, int color) {
        this.path = path;
        this.category = category;
        this.isSelect = false;
        this.color = color;
        this.isAvailable = false;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}