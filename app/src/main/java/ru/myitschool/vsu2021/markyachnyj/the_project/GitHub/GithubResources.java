package ru.myitschool.vsu2021.markyachnyj.the_project.GitHub;

import java.util.ArrayList;
import java.util.Random;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;

public class GithubResources {
    public static ArrayList<Grade> getGradeArrayList() {
        ArrayList<Grade> result = new ArrayList<>();
        result.add(new Grade(7,6,3));
        return result;
    }
    public static ArrayList<Topic> getTopicArrayList(int grade_number){
        return getTestTopicArrayList7();
    }
    private static ArrayList<Topic> getTestTopicArrayList7(){
        ArrayList<Topic> result = new ArrayList<>();
        Random random = new Random();
        for(String string:new String[]{"Масса, объём, плотность","Механическое движение", "Поняти силы","Давление","Сила Архимеда","Работа, мощность, КПД","Правило моментов, рычаги силы"}){
            result.add(new Topic(string, (random.nextInt(101))/100f));
        };
        return result;
    }
}
