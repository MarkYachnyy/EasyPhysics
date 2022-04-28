package ru.myitschool.vsu2021.markyachnyj.the_project.theory;

import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Formula;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Measure;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Test;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.FormulaConstructorTask;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.UnitChoiceTask;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.SimpleAnswerTask;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public class GithubResourceManager {

    private OkHttpClient client;
    private Gson gson;
    private Random random;

    public GithubResourceManager(){
        client = new OkHttpClient();
        gson = new Gson();
        random = new Random();
    }

    private ArrayList getRandom(ArrayList list, int number){
        ArrayList result = new ArrayList<>();
        for(int i=0;i<number;i++){
            Object s = list.get(random.nextInt(list.size()));
            if(!result.contains(s)){
                result.add(s);
            }
        }
        return result;
    }

    private ArrayList getRandom(Object[] list, int number){
        ArrayList result = new ArrayList();
        result.addAll(Arrays.asList(list));
        return getRandom(result,number);
    }

    private String buildURL(String path){
        String result = "";
        URI uri = null;
        try{
            uri = new URI("https","raw.githubusercontent.com","/MarkYachnyy/The_Theory/main/"+path,null);
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
        json = executeCall("grade_list.json");
        ArrayList<String> arr = gson.fromJson(json,ArrayList.class);
        Integer[] result = gson.fromJson(json, Integer[].class);
        return new ArrayList<>(Arrays.asList(result));
    }
    public Grade getEmptyGrade(int grade_number){
        String json = executeCall(grade_number+"/empty_grade.json");
        return gson.fromJson(json, Grade.class);
    }
    public ArrayList<String> getTopicArrayList(int grade_number){
        String json = executeCall(grade_number+"/topic_list.json");
        ArrayList<String> result = gson.fromJson(json, ArrayList.class);
        return result;
    }

    public String getTheory(Topic topic){
        return executeCall(topic.getGrade_number()+"/"+topic.getName()+"/theory.txt");
    }

    private ArrayList<String> getAllComponents(int grade_number){
        return gson.fromJson(executeCall(grade_number+"/components.json"),ArrayList.class);
    }

    private ArrayList<String> getAllUnits(int grade_number){
        return gson.fromJson(executeCall(grade_number+"/units.json"),ArrayList.class);
    }

    private UnitChoiceTask getRandomUnitChoiceTask(Topic topic){
        Measure measure = (Measure) getRandom(gson.fromJson(executeCall(topic.getGrade_number()+"/"+topic.getName()+"/measures.json"),Measure[].class),1).get(0);
        ArrayList<String> all_units = getAllUnits(topic.getGrade_number());
        ArrayList<String> extra_units = new ArrayList<>();
        while (extra_units.size()<3) {
            String s = (String) getRandom(all_units, 1).get(0);
            if ((!s.equals(measure.getUnit()) && (!extra_units.contains(s)))) {
                extra_units.add(s);
            }
        }
        return new UnitChoiceTask(measure, extra_units);
    }

    private FormulaConstructorTask getRandomFormulaConstructorTask(Topic topic){
        Formula[] formulas = gson.fromJson(executeCall(topic.getGrade_number()+"/"+topic.getName()+"/formulas.json"),Formula[].class);
        Formula formula = (Formula) getRandom(formulas,1).get(0);
        ArrayList<String> all_components = getAllComponents(topic.getGrade_number());
        ArrayList<String> extra_components = new ArrayList<>();
        ArrayList<String> numerator = new ArrayList<>(Arrays.asList(formula.getNumerator()));
        ArrayList<String> denominator = new ArrayList<>(Arrays.asList(formula.getDenominator()));
        while(extra_components.size()<2){
            String s = (String) getRandom(all_components,1).get(0);
            if((!extra_components.contains(s))&&(!numerator.contains(s))&&(!denominator.contains(s))&&(!extra_components.contains(s.toLowerCase()))&&(!numerator.contains(s.toLowerCase()))&&(!denominator.contains(s.toLowerCase()))){
                extra_components.add(s);
            }
        }

        return new FormulaConstructorTask(formula, extra_components);
    }

    private SimpleAnswerTask getRandomSimpleAnswerTask(Topic topic, int tier_123){
        SimpleAnswerTask[] arr = gson.fromJson(executeCall(topic.getGrade_number()+"/"+topic.getName()+"/"+tier_123+".json"),SimpleAnswerTask[].class);
        return (SimpleAnswerTask) getRandom(arr,1).get(0);
    }

    public Test BuildTest(Topic topic){
        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(getRandomFormulaConstructorTask(topic));
        tasks.add(getRandomUnitChoiceTask(topic));
        for(int i=1;i<=3;i++){
            tasks.add(getRandomSimpleAnswerTask(topic,i));
        }
        return new Test(topic,tasks);
    }
}
