package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class TestResultActivity extends AppCompatActivity {

    private String topic_name;

    private TextView Title_TV;

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,TopicChoiceActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        Title_TV = (TextView) findViewById(R.id.activity_test_result_title_tv);
        topic_name = getIntent().getStringExtra("topic_name");
    }
}