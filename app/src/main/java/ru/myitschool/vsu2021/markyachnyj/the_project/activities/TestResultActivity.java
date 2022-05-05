package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.Database.DatabaseManager;
import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Adapters.TestResultAdapter;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views.BasicProgressBarView;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Test;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;

public class TestResultActivity extends AppCompatActivity {

    private Test test;
    private TestResultAdapter adapter;
    private DatabaseManager manager;

    private TextView Title_TV;
    private ListView ListView;
    private BasicProgressBarView ProgressBar;
    private Button Close_Btn;

    @Override
    public void onBackPressed() {
        Close_Btn.callOnClick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        Title_TV = (TextView) findViewById(R.id.activity_test_result_title_tv);
        ListView = (ListView) findViewById(R.id.activity_test_result_list);
        ProgressBar = (BasicProgressBarView) findViewById(R.id.activity_test_result_progress_bar);
        Close_Btn = (Button) findViewById(R.id.activity_test_result_close_btn);
        test = (Test) getIntent().getSerializableExtra("test");
        adapter = new TestResultAdapter(this,test);
        Title_TV.setText("Тест по теме\""+test.getTopic().getName()+"\" завершён");
        ListView.setAdapter(adapter);
        ProgressBar.setProgress(test.getProgress());
        Close_Btn.setOnClickListener(Close_Btn_Listener);
        manager = new DatabaseManager(this);
    }

    private View.OnClickListener Close_Btn_Listener = v -> (new AsTask()).execute();

    private class AsTask extends AsyncTask<Void, Void, ArrayList<Topic>>{

        @Override
        protected ArrayList<Topic> doInBackground(Void... voids) {
            Topic topic = new Topic(test.getTopic().getGrade_number(), test.getTopic().getName(), test.getProgress());
            manager.updateTopic(topic);
            manager.invalidateGragesData();
            return manager.getAllTopics(test.getTopic().getGrade_number());
        }

        @Override
        protected void onPostExecute(ArrayList<Topic> topics) {
            super.onPostExecute(topics);
            Intent i = new Intent(TestResultActivity.this, TopicChoiceActivity.class);
            i.putExtra("topic_list",topics);
            startActivity(i);
        }

    }

}