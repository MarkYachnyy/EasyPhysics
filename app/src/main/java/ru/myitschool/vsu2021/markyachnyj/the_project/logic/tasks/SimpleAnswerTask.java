package ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks;

public class SimpleAnswerTask extends Task{
    private String right_answer;
    private String chosen_answer;

    private String exercise;

    @Override
    public boolean CheckAnswer() {
        return right_answer.equals(chosen_answer);
    }
}
