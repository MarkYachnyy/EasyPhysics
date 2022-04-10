package ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks;

import java.io.Serializable;

public class AnswerChoiceTask extends Task implements Serializable {
    private String chosen_answer;
    private String right_answer;
    private String[] answers;

    private String exercise;

    @Override
    public boolean CheckAnswer() {
        return chosen_answer.equals(right_answer);
    }

    @Override
    public String getRightAnswer() {
        return null;
    }

    @Override
    public String getGivenAnswer() {
        return null;
    }
}
