package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.Database.DatabaseManager;
import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Adapters.TestResultAdapter;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.SimpleMessageDialog;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views.BasicProgressBarView;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Test;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;

public class TestResultActivity extends AppCompatActivity {

    private Test test;
    private TestResultAdapter adapter;
    private DatabaseManager manager;

    private TextView Title_TV;
    private ListView ListView;
    private TextView Progress_TV;
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
        Progress_TV = (TextView) findViewById(R.id.activity_test_result_progress_tv);
        Close_Btn = (Button) findViewById(R.id.activity_test_result_close_btn);
        test = (Test) getIntent().getSerializableExtra("test");
        adapter = new TestResultAdapter(this,test);
        Title_TV.setText("Тест по теме\""+test.getTopic().getName()+"\" завершён");
        ListView.setAdapter(adapter);
        ListView.setOnItemClickListener(ListItemListener);
        ProgressBar.setProgress(test.getProgress());
        Progress_TV.setText((Progress_TV.getText().toString()).replace("N",""+(int)(test.getProgress()*100)));
        Close_Btn.setOnClickListener(Close_Btn_Listener);
        manager = new DatabaseManager(this);
    }

    private AdapterView.OnItemClickListener ListItemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            SimpleMessageDialog dialog = new SimpleMessageDialog(TestResultActivity.this, test.getTasks().get(position).getExercise());
            dialog.show();
        }
    };

    private View.OnClickListener Close_Btn_Listener = v -> (new FinishActivityTask()).execute();

    private class FinishActivityTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            if(manager.getProgress(test.getTopic().getName())<=test.getProgress()){
                Topic topic = new Topic(test.getTopic().getGrade_number(), test.getTopic().getName(), test.getProgress());
                manager.updateTopic(topic);
                manager.invalidateGragesData();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            finish();
        }

    }

}