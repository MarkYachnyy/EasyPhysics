package ru.myitschool.vsu2021.markyachnyj.the_project.logic;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Formula implements Serializable {
    private String value_name;
    private String value_symbol;

    private String[] numerator;
    private String[] denominator;

    public Formula(String value_name,String value_symbol, String[] num, String[] denom){
        this.value_name = value_name;
        this.value_symbol = value_symbol;
        numerator = num;
        denominator = denom;
    }

    public String getValue_symbol() {
        return value_symbol;
    }

    public String getValue_name (){
        return value_name;
    }

    public String[] getNumerator() {
        return numerator;
    }

    public String[] getDenominator() {
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
