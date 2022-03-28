package ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks;

public class AnswerChoiceTask extends Task{
    private String chosen_answer;
    private String right_answer;
    private String[] answers;

    private String exercise;

    @Override
    public boolean CheckAnswer() {
        return chosen_answer.equals(right_answer);
    }
}
