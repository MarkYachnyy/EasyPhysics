package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.ArrayAdapters.TopicArrayAdapter;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;

public class TopicChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_choice);
        ListView listView = (ListView) findViewById(R.id.activity_topic_choice_list);
        Topic[] a = new Topic[]{new Topic("topic1",3,1),new Topic("topic2",6,5),new Topic("topic1",3,3),new Topic("topic1",3,0)};
        listView.setAdapter(new TopicArrayAdapter(getApplicationContext(),R.layout.grade_topic_list_item,a));
    }


}