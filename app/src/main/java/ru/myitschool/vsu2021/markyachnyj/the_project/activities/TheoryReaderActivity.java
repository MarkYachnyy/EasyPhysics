package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.myitschool.vsu2021.markyachnyj.the_project.theory.GithubResourceManager;
import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class TheoryReaderActivity extends AppCompatActivity {

    private String topic_name;
    private String theory;

    private TextView Theory_TV;
    private Button Back_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_reader);
        topic_name = getIntent().getStringExtra("topic_name");
        Theory_TV = (TextView) findViewById(R.id.activity_theory_reader_theory_tv);
        Back_Btn = (Button) findViewById(R.id.activity_theory_reader_back_btn);
        theory = getIntent().getStringExtra("theory");
        Theory_TV.setText(theory);
        Back_Btn.setOnClickListener(Back_Btn_Listener);
    }
    private View.OnClickListener Back_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}