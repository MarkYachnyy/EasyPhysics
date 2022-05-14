package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import ru.myitschool.vsu2021.markyachnyj.the_project.theory.GithubResourceManager;
import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class TheoryReaderActivity extends AppCompatActivity {

    private String topic_name;
    private String theory;

    private TextView Theory_TV;
    private ImageButton Back_Btn;
    private TextView Topic_Name_TV;
    private ImageButton Increase_Text_Size_Btn;
    private ImageButton Reduce_Text_Size_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_reader);
        topic_name = getIntent().getStringExtra("topic_name");
        Theory_TV = (TextView) findViewById(R.id.activity_theory_reader_theory_tv);
        Back_Btn = (ImageButton) findViewById(R.id.activity_theory_reader_back_btn);
        Topic_Name_TV = (TextView) findViewById(R.id.activity_test_solver_topic_name_tv);
        Increase_Text_Size_Btn = (ImageButton) findViewById(R.id.activity_theory_reader_increase_text_size_btn);
        Reduce_Text_Size_Btn = (ImageButton) findViewById(R.id.activity_theory_reader_reduce_text_size_btn);
        Topic_Name_TV = (TextView) findViewById(R.id.activity_theory_reader_topic_name_tv);
        theory = getIntent().getStringExtra("theory");
        Theory_TV.setText(theory);
        Topic_Name_TV.setText(topic_name);
        Back_Btn.setOnClickListener(Back_Btn_Listener);
        Increase_Text_Size_Btn.setOnClickListener(Increase_TS_Btn_Listener);
        Reduce_Text_Size_Btn.setOnClickListener(Reduce_TS_Btn_Listener);
    }
    private View.OnClickListener Back_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    private View.OnClickListener Increase_TS_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(Theory_TV.getTextSize()<120)
            Theory_TV.setTextSize(TypedValue.COMPLEX_UNIT_PX,Theory_TV.getTextSize()+10);
        }
    };
    private View.OnClickListener Reduce_TS_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(Theory_TV.getTextSize()>10){
                Theory_TV.setTextSize(TypedValue.COMPLEX_UNIT_PX,Theory_TV.getTextSize()-10);
            }
        }
    };
}