package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.fragments.TopicProgressInfoFragment;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.ArrayAdapters.TopicAdapter;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;
import ru.myitschool.vsu2021.markyachnyj.the_project.theory.GithubResourceManager;

public class TopicChoiceActivity extends AppCompatActivity {

    private TopicAdapter adapter;
    private ArrayList<Topic> data;
    private ListView list;
    private Button Back_Button;

    private GithubResourceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_choice);
        Back_Button = findViewById(R.id.activity_topic_choice_back_btn);
        list = (ListView) findViewById(R.id.activity_topic_choice_list);
        manager = new GithubResourceManager();
        data = manager.getTopicArrayList(getIntent().getIntExtra("grade_number",7));
        adapter = new TopicAdapter(getApplicationContext(),data);
        list.setAdapter(adapter);
        list.setOnItemClickListener(ItemListener);
        Back_Button.setOnClickListener(Back_Btn_Listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        data = manager.getTopicArrayList(getIntent().getIntExtra("grade_number",7));
        adapter = new TopicAdapter(getApplicationContext(),data);
        list.setAdapter(adapter);
    }

    private View.OnClickListener Back_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private AdapterView.OnItemClickListener ItemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            OpenFragment((Topic) adapter.getItem(position));
        }
    };

    private void OpenFragment(Topic topic){
        FrameLayout frame = findViewById(R.id.activity_topic_choice_fading_frame);
        frame.setVisibility(View.VISIBLE);
        frame.setClickable(true);
        Animation frame_animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        frame.startAnimation(frame_animation);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        TopicProgressInfoFragment fragment = new TopicProgressInfoFragment(topic);
        transaction.setCustomAnimations(R.anim.fragment_grade_topic_progress_info_enter,R.anim.fragment_grade_topic_progress_info_exit);
        transaction.add(R.id.activity_topic_choice_topic_info_fragment_holder,fragment);
        transaction.commit();
    }
}