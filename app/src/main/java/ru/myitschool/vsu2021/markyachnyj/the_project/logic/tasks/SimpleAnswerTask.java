package ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks;

public class SimpleAnswerTask extends Task{
    private String right_answer;
    private String given_answer;

    private String exercise;

    public SimpleAnswerTask(String exercise, String right_answer) {
        this.right_answer = right_answer;
        this.exercise = exercise;
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
}
