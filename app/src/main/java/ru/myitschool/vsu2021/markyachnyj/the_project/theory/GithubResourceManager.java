package ru.myitschool.vsu2021.markyachnyj.the_project.theory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Formula;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Test;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.AnswerChoiceTask;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.FormulaConstructorTask;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.SimpleAnswerTask;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public class GithubResourceManager {

    OkHttpClient client;

    public GithubResourceManager(){
        client = new OkHttpClient();
    }

    private String buildURL(String path){
        String result = "";
        URI uri = null;
        try{
            uri = new URI("https","raw.githubusercontent.com","/MarkYachnyy/The_Theory/main"+path,null);
        } catch (URISyntaxException e){
            e.printStackTrace();
        }
        return uri.toASCIIString();
    }

    public ArrayList<Grade> getGradeArrayList() {
        ArrayList<Grade> result = new ArrayList<>();
        result.add(new Grade(7,3,7));
        return result;
    }
    public ArrayList<Topic> getTopicArrayList(int grade_number){
        return getTestTopicArrayList7();
    }
    public String getTheory(String topic_name){
        String result="";
        Request request = new Request.Builder().url(buildURL("/7/"+topic_name+"/theory.txt")).build();
        try{
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    private ArrayList<Topic> getTestTopicArrayList7(){
        ArrayList<Topic> result = new ArrayList<>();
        Random random = new Random();
        for(String string:new String[]{"Масса, объём, плотность","Механическое движение", "Понятие силы","Давление","Сила Архимеда","Работа, мощность, КПД","Правило моментов"}){
            result.add(new Topic(string, (random.nextInt(101))/100f));
        };
        return result;
    }

    public Test BuildTest(Topic topic){
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new FormulaConstructorTask(new Formula("value1","V1",new String[]{"el1", "el2"},new String[]{"el3"}),new String[]{"el4","el5"}));
        for(int i=1;i<=3;i++){
            tasks.add(new SimpleAnswerTask("exercise "+i,"answer"));
        }
        return new Test(topic,tasks);
    }
}
