package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;

import android.content.Context;
import android.content.res.ColorStateList;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.Database.DatabaseManager;
import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views.SimpleDrawable;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;
import ru.myitschool.vsu2021.markyachnyj.the_project.theory.GithubResourceManager;

public class MainMenuActivity extends AppCompatActivity {

    private LinearLayout No_Internet_LL;
    private FrameLayout Screen_FL;

    private DatabaseManager databaseManager;
    private GithubResourceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Screen_FL = (FrameLayout) findViewById(R.id.activity_main_menu_screen_fl);
        databaseManager = new DatabaseManager(MainMenuActivity.this);
        manager = new GithubResourceManager();
        if(isNetworkConnected()){
            (new CheckDataBaseRelevanceTask()).execute();
        } else {
            ShowNoInternetLayout();
        }
    }
    private class CheckDataBaseRelevanceTask extends AsyncTask<Void, Void, Void >{

        @Override
        protected Void  doInBackground(Void... voids) {
            String result = "";
            try{
                ArrayList<Integer> grade_numbers = manager.getGradeArrayList();
                for(int number:grade_numbers){
                    boolean b = databaseManager.containsGrade(number);
                    if(!b){
                        Grade empty_grade = manager.getEmptyGrade(number);
                        databaseManager.insertGrade(empty_grade);
                    }
                }
                ArrayList<Grade> grades = databaseManager.getAllGrades();
                for(Grade g:grades){
                    int number = g.getNumber();
                    ArrayList<String> topic_names = manager.getTopicArrayList(number);
                    for(String s:topic_names){
                        boolean b = databaseManager.containsTopic(s);
                        if(!b){
                            databaseManager.insertTopic(new Topic(s,0f),number);
                        }
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
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
    private void ShowNoInternetLayout(){
        No_Internet_LL = new LinearLayout(this);
        No_Internet_LL.setOrientation(LinearLayout.VERTICAL);
        No_Internet_LL.setGravity(Gravity.CENTER);
        FrameLayout.LayoutParams lp0 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        No_Internet_LL.setLayoutParams(lp0);
        Screen_FL.addView(No_Internet_LL);
        SimpleDrawable drawable = new SimpleDrawable(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(600,600);
        drawable.setLayoutParams(lp);
        No_Internet_LL.addView(drawable);
        TextView tv = new TextView(this);
        tv.setGravity(Gravity.CENTER);
        tv.setText("Отсутствует подключение к интернету");
        tv.setTextColor(getResources().getColor(R.color.white));
        tv.setTextSize(25);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(0,15,0,15);
        tv.setLayoutParams(lp1);
        No_Internet_LL.addView(tv);
        Button btn = new Button(this);
        btn.setCompoundDrawablesWithIntrinsicBounds(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_autorenew_45,null),null,null,null);
        ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(getResources().getColor(R.color.dark_blue_pastel)));
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(lp2);
        No_Internet_LL.addView(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}