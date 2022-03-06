package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.ArrayAdapters.GradeAdapter;
import ru.myitschool.vsu2021.markyachnyj.the_project.resources.GithubResources;

public class GradeChoiceActivity extends AppCompatActivity {

    GradeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_choice);
        ListView listView = (ListView) findViewById(R.id.activity_grade_choice_list);
        adapter = new GradeAdapter(getApplicationContext(), GithubResources.getGradeArrayList());
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setNewData(GithubResources.getGradeArrayList());
    }
}