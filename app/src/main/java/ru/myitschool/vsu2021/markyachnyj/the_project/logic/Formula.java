package ru.myitschool.vsu2021.markyachnyj.the_project.logic;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Formula implements Serializable {
    private String name;

    private ArrayList<String> numerator;
    private ArrayList<String> denominator;

    public Formula(String name, String[] num, String[] denom){
        this.name = name;
        numerator = new ArrayList<>();
        denominator = new ArrayList<>();
        numerator.addAll(Arrays.asList(num));
        denominator.addAll(Arrays.asList(num));
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getNumerator() {
        return numerator;
    }

    public ArrayList<String> getDenominator() {
        return denominator;
    }

    @NonNull
    @Override
    public String toString() {
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
}
