package ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Formula;

public class FormulaConstructorTask extends Task implements Serializable {

    private Formula formula;
    private ArrayList<String> numerator;
    private ArrayList<String> denominator;

    private String exercise;

    public FormulaConstructorTask(Formula formula){
        this.formula = formula;
        this.numerator = new ArrayList<>();
        this.denominator = new ArrayList<>();
        exercise = "Составьте правильную формулу для величины: "+formula.getValue_name();
    }

    @Override
    public boolean CheckAnswer() {
        ArrayList<String> right_numerator = formula.getNumerator();
        ArrayList<String> right_denominator = formula.getDenominator();

        return numerator.containsAll(right_numerator)&&
                right_numerator.containsAll(numerator)&&
                denominator.containsAll(right_denominator)&&
                right_denominator.containsAll(denominator);
    }

    @Override
    public String getRightAnswer() {
        return formula.toString();
    }

    @Override
    public String getGivenAnswer() {
        String result="";
        for(String s:numerator){
            result+=s;
        };
        result+="/";
        for(String s:denominator){
            result+=s;
        }
        return result;
    }

    public Formula getFormula() {
        return formula;
    }

    public String getExercise() {
        return exercise;
    }
}
