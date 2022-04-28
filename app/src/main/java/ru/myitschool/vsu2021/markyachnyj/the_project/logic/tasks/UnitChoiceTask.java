package ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Measure;

public class UnitChoiceTask extends Task implements Serializable {
    private String chosen_answer;
    private ArrayList<String> answers;

    private String exercise;
    private Measure measure;

    public UnitChoiceTask(Measure measure, ArrayList<String> extra_units){
        this.measure = measure;
        this.exercise = "Выберите правильную единицу измерения для величины: "+measure.getName();
        chosen_answer = "-";
        this.answers =new ArrayList<>();
        this.answers.add(measure.getUnit());
        this.answers.addAll(extra_units);
        Collections.sort(this.answers);
    }

    public String getChosen_answer() {
        return chosen_answer;
    }

    public ArrayList<String> getAllAnswers() {
        return answers;
    }

    public String getExercise() {
        return exercise;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setChosen_answer(String chosen_answer) {
        this.chosen_answer = chosen_answer;
    }

    @Override
    public boolean CheckAnswer() {
        return chosen_answer.equals(measure.getUnit());
    }

    @Override
    public String getRightAnswer() {
        return measure.getUnit();
    }

    @Override
    public String getGivenAnswer() {
        return chosen_answer;
    }
}
