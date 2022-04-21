package ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Formula;

public class FormulaConstructorTask extends Task implements Serializable {

    private Formula formula;
    private ArrayList<String> numerator;
    private ArrayList<String> denominator;

    private String exercise;
    private String[] extra_components;

    public FormulaConstructorTask(Formula formula, String[] extra_components){
        this.formula = formula;
        this.numerator = new ArrayList<>();
        this.denominator = new ArrayList<>();
        exercise = "Составьте правильную формулу для величины: "+formula.getValue_name();
        this.extra_components = extra_components;
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

    public ArrayList<String> getAllComponents(){
        TreeSet<String> tree = new TreeSet<>(formula.getNumerator());
        tree.addAll(formula.getDenominator());
        tree.addAll(Arrays.asList(extra_components));
        return new ArrayList<>(tree);
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

    public void setNumerator(ArrayList<String> numerator) {
        this.numerator = numerator;
    }

    public void setDenominator(ArrayList<String> denominator) {
        this.denominator = denominator;
    }
}
