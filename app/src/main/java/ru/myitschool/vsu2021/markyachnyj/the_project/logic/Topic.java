package ru.myitschool.vsu2021.markyachnyj.the_project.logic;

import java.io.Serializable;

public class Topic implements Serializable {
    private String name;
    private float test_progress;

    public Topic(String name, float test_progress) {
        this.test_progress = test_progress;
        this.name = name;
    }

    public float getProgress(){
        return test_progress;
    }

    public String getName() {
        return name;
    }

}
