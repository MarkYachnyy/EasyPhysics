package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import org.json.JSONObject;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.fragments.GradeProgressInfoFragment;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.ArrayAdapters.GradeAdapter;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;
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
        listView.setOnItemClickListener(ItemListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setNewData(GithubResources.getGradeArrayList());
    }

    private AdapterView.OnItemClickListener ItemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            OpenInfoFragment((Grade) adapter.getItem(position));
        }
    };

    private void OpenInfoFragment(Grade grade){
        FrameLayout frame = findViewById(R.id.activity_grade_choice_fading_frame);
        frame.setVisibility(View.VISIBLE);
        frame.setClickable(true);
        Animation frame_animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        frame.startAnimation(frame_animation);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        GradeProgressInfoFragment fragment = new GradeProgressInfoFragment();
        transaction.setCustomAnimations(R.anim.fragment_grade_topic_progress_info_enter,R.anim.fragment_grade_topic_progress_info_exit);
        transaction.add(R.id.activity_grade_choice_garde_info_fragment_holder,fragment);
        transaction.commit();
    }

}