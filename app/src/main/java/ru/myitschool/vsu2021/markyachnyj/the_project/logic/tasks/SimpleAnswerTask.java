package ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks;

import java.io.Serializable;

public class SimpleAnswerTask extends Task implements Serializable {

    private String right_answer;
    private String given_answer;

    private String exercise;

    public SimpleAnswerTask(String exercise, String right_answer) {
        this.right_answer = right_answer;
        this.exercise = exercise;
        this.given_answer = "-";
    }

    public String getExercise() {
        return exercise;
    }

    public void giveAnswer(String answer){
        given_answer = answer;
    }

    @Override
    public boolean CheckAnswer() {
        return right_answer.equals(given_answer);
    }

    @Override
    public String getRightAnswer() {
        return right_answer;
    }

    @Override
    public String getGivenAnswer() {
        return given_answer;
    }
}
