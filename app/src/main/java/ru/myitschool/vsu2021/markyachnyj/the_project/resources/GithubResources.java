package ru.myitschool.vsu2021.markyachnyj.the_project.resources;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;

public class GithubResources {
    public static ArrayList<Grade> getGradeArrayList() {
        ArrayList<Grade> result = new ArrayList<>();
        result.add(new Grade(7,6,3));
        return result;
    }
    public static ArrayList<Topic> getTopicArrayList(int grade_number){
        return getTestTopicArrayList();
    }
    private static ArrayList<Topic> getTestTopicArrayList(){
        ArrayList<Topic> result = new ArrayList<>();
        for(String string:new String[]{"Масса, объём, плотность","Механическое движение", "Поняти силы","Давление","Сила Архимеда","Работа, мощность, КПД","Правило моментов, рычаги силы"}){
            result.add(new Topic(string, 6,3));
        };
        return result;
    }
}
