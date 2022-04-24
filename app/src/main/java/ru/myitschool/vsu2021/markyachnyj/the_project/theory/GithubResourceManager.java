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

    private ArrayList<String> getRandom(ArrayList<String> list, int number){
        ArrayList<String> result = new ArrayList<>();
        for(int i=0;i<number;i++){
            String s = list.get(random.nextInt(list.size()));
            if(!result.contains(s)){
                result.add(s);
            }
        }
        return result;
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
        ArrayList<Integer> result = new ArrayList<>();
        for(String s:arr){
            result.add(Integer.parseInt(s));
        }
        return result;
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
        String json = (String) getRandom(gson.fromJson(executeCall(topic.getGrade_number()+"/"+topic.getName()+"/measures.json"),ArrayList.class),1).get(0);
        Measure measure = gson.fromJson(json, Measure.class);
        ArrayList<String> all_units = getAllUnits(topic.getGrade_number());
        ArrayList<String> extra_units = new ArrayList<>();
        while (extra_units.size()<3) {
            String s = getRandom(all_units, 1).get(0);
            if ((!s.equals(measure.getUnit()) && (!extra_units.contains(s)))) {
                extra_units.add(s);
            }
        }
        return new UnitChoiceTask(measure, extra_units);
    }

    private FormulaConstructorTask getRandomFormulaConstructorTask(Topic topic){
        String json = (String) getRandom(gson.fromJson(executeCall(topic.getGrade_number()+"/"+topic.getName()+"/formulas.json"),ArrayList.class),1).get(0);
        Formula formula = gson.fromJson(json,Formula.class);
        ArrayList<String> all_components = getAllComponents(topic.getGrade_number());
        ArrayList<String> extra_components = new ArrayList<>();
        while(extra_components.size()<2){
            String s = getRandom(all_components,1).get(0);
            if((!extra_components.contains(s))&&(!formula.getNumerator().contains(s))&&(!formula.getDenominator().contains(s))&&(!extra_components.contains(s.toLowerCase()))&&(!formula.getNumerator().contains(s.toLowerCase()))&&(!formula.getDenominator().contains(s.toLowerCase()))){
                extra_components.add(s);
            }
        }

        return new FormulaConstructorTask(formula, extra_components);
    }

    private SimpleAnswerTask getRandomSimpleAnswerTask(Topic topic, int tier_123){
        ArrayList<String> arr = gson.fromJson(executeCall(topic.getGrade_number()+"/"+topic.getName()+"/"+tier_123+".json"),ArrayList.class);
        return gson.fromJson(getRandom(arr,1).get(0),SimpleAnswerTask.class);
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
