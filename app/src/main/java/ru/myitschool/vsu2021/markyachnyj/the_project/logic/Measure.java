package ru.myitschool.vsu2021.markyachnyj.the_project.logic;

import java.io.Serializable;

public class Measure implements Serializable{
    private String name;
    private String unit;

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public Measure(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }
}
