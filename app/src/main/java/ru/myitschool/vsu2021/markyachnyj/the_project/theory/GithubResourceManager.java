package ru.myitschool.vsu2021.markyachnyj.the_project.theory;

import com.google.gson.Gson;

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
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.FormulaConstructorTask;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.SimpleAnswerTask;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public class GithubResourceManager {

    private OkHttpClient client;
    private Gson gson;

    public GithubResourceManager(){
        client = new OkHttpClient();
        gson = new Gson();
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

    private String executeCall(String path){
        String result = "";
        Request request = new Request.Builder().url(buildURL(path)).build();
        try{
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Integer> getGradeArrayList() {
        String json;
        json = executeCall("/grade_list.json");
        ArrayList<String> arr = gson.fromJson(json,ArrayList.class);
        ArrayList<Integer> result = new ArrayList<>();
        for(String s:arr){
            result.add(Integer.parseInt(s));
        }
        return result;
    }
    public Grade getEmptyGrade(int grade_number){
        String json = executeCall("/"+grade_number+"/empty_grade.json");
        return gson.fromJson(json, Grade.class);
    }
    public ArrayList<String> getTopicArrayList(int grade_number){
        String json = executeCall("/"+grade_number+"/topic_list.json");
        ArrayList<String> result = gson.fromJson(json, ArrayList.class);
        return result;
    }

    public String getTheory(String topic_name){
        return executeCall("/7/"+topic_name+"/theory.txt");
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
