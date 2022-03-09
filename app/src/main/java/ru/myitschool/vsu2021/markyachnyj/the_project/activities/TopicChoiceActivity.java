package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.ArrayAdapters.TopicAdapter;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;
import ru.myitschool.vsu2021.markyachnyj.the_project.GitHub.GithubResources;

public class TopicChoiceActivity extends AppCompatActivity {

    private TopicAdapter adapter;
    private ArrayList<Topic> data;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_choice);
        list = (ListView) findViewById(R.id.activity_topic_choice_list);
        data = GithubResources.getTopicArrayList(getIntent().getIntExtra("grade_number",7));
        adapter = new TopicAdapter(getApplicationContext(),data);
        list.setAdapter(adapter);
        ((Button)findViewById(R.id.activity_topic_choice_back_button)).setOnClickListener(Back_Btn_Listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        data = GithubResources.getTopicArrayList(getIntent().getIntExtra("grade_number",7));
        adapter = new TopicAdapter(getApplicationContext(),data);
        list.setAdapter(adapter);
    }

    private View.OnClickListener Back_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}