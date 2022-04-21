package ru.myitschool.vsu2021.markyachnyj.the_project.logic;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Grade implements Serializable {
    private int number;
    private int topic_count;
    private int topic_completed;

    public Grade(int number, int topic_completed, int topic_count) {
        this.number = number;
        this.topic_count = topic_count;
        this.topic_completed = topic_completed;
    }

    public float getProgress(){
        return 1f*topic_completed/topic_count;
    }

    public int getNumber() {
        return number;
    }

    public int getTopic_count() {
        return topic_count;
    }

    public int getTopic_completed() {
        return topic_completed;
    }

    @NonNull
    @Override
    public String toString() {
        return "{"+number+" "+topic_completed+" "+topic_count+"}";
    }
}
