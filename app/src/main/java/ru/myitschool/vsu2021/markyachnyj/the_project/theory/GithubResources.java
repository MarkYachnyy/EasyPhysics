package ru.myitschool.vsu2021.markyachnyj.the_project.theory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public class GithubResources {

    private static String buildURL(String path){
        String result = "";
        URI uri = null;
        try{
            uri = new URI("https","raw.githubusercontent.com","/MarkYachnyy/The_Theory/main"+path,null);
        } catch (URISyntaxException e){
            e.printStackTrace();
        }
        return uri.toASCIIString();
    }

    public static ArrayList<Grade> getGradeArrayList() {
        ArrayList<Grade> result = new ArrayList<>();
        result.add(new Grade(7,3,7));
        return result;
    }
    public static ArrayList<Topic> getTopicArrayList(int grade_number){
        return getTestTopicArrayList7();
    }
    public static String getTheory(String topic){
        OkHttpClient client = new OkHttpClient();
        String result="";
        Request request = new Request.Builder().url(buildURL("/7/"+topic+"/theory.txt")).build();
        try{
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    private static ArrayList<Topic> getTestTopicArrayList7(){
        ArrayList<Topic> result = new ArrayList<>();
        Random random = new Random();
        for(String string:new String[]{"Масса, объём, плотность","Механическое движение", "Понятие силы","Давление","Сила Архимеда","Работа, мощность, КПД","Правило моментов"}){
            result.add(new Topic(string, (random.nextInt(101))/100f));
        };
        return result;
    }
}
