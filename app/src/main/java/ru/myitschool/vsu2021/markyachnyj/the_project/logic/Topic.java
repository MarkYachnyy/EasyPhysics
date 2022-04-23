package ru.myitschool.vsu2021.markyachnyj.the_project.logic;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Topic implements Serializable {
    private int grade_number;
    private String name;
    private float test_progress;

    public Topic(int grade_number,String name, float test_progress) {
        this.grade_number = grade_number;
        this.test_progress = test_progress;
        this.name = name;
    }

    public float getProgress(){
        return test_progress;
    }

    public String getName() {
        return name;
    }

    public int getGrade_number() {
        return grade_number;
    }

    @NonNull
    @Override
    public String toString() {
        return name+" "+test_progress;
    }
}
