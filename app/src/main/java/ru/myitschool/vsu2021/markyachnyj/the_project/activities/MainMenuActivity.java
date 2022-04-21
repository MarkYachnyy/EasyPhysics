package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.Database.GradeDB;
import ru.myitschool.vsu2021.markyachnyj.the_project.Database.TopicDB;
import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Test;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;
import ru.myitschool.vsu2021.markyachnyj.the_project.theory.GithubResourceManager;

public class MainMenuActivity extends AppCompatActivity {

    private TextView Test_TV;

    private GradeDB gradeDB;
    private TopicDB topicDB;
    private GithubResourceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Test_TV = (TextView) findViewById(R.id.activity_main_menu_test_tv);
        gradeDB = new GradeDB(MainMenuActivity.this);
        topicDB = new TopicDB(MainMenuActivity.this);
        manager = new GithubResourceManager();
        (new CheckDataBaseRelevanceTask()).execute();
    }
    private class CheckDataBaseRelevanceTask extends AsyncTask<Void, Void, String >{

        @Override
        protected String  doInBackground(Void... voids) {
            String result = "";
            try{
                topicDB = new TopicDB(MainMenuActivity.this);
                ArrayList<Integer> grade_numbers = manager.getGradeArrayList();
                for(int number:grade_numbers){
                    boolean b = gradeDB.containsGrade(number);
                    if(!b){
                        Grade empty_grade = manager.getEmptyGrade(number);
                        gradeDB.insert(empty_grade);
                    }
                }
                ArrayList<Grade> grades = gradeDB.getAll();
                /*for(Grade g:grades){
                    int number = g.getNumber();
                    ArrayList<String> topic_names = manager.getTopicArrayList(number);
                    for(String s:topic_names){
                        boolean b = topicDB.containsTopic(s);
                        if(!b){
                            topicDB.insert(new Topic(s,0f),number);
                        }
                    }
                }
                ArrayList<Topic> topics = topicDB.getAll(7);*/
                result = grades.toString();
            } catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Test_TV.setText(s);
        }
    }
    private class LoadGradeListTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}