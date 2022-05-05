package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.Database.DatabaseManager;
import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.fragments.GradeProgressInfoFragment;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Adapters.GradeAdapter;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;

public class GradeChoiceActivity extends AppCompatActivity {

    ListView listView;
    GradeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_choice);
        listView = (ListView) findViewById(R.id.activity_grade_choice_list);
        adapter = new GradeAdapter(getApplicationContext(), (ArrayList<Grade>) getIntent().getSerializableExtra("grade_list"));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(ItemListener);
    }

    @Override
    public void onBackPressed() {
        ShowExitAlertDialog();
    }

    private AdapterView.OnItemClickListener ItemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            try{
                OpenInfoFragment((Grade) adapter.getItem(position));
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    };

    private void OpenInfoFragment(Grade grade){
        FrameLayout frame = findViewById(R.id.activity_grade_choice_fading_frame);
        frame.setVisibility(View.VISIBLE);
        frame.setClickable(true);
        Animation frame_animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        frame.startAnimation(frame_animation);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        GradeProgressInfoFragment fragment = new GradeProgressInfoFragment(grade);
        transaction.setCustomAnimations(R.anim.fragment_grade_topic_progress_info_enter,R.anim.fragment_grade_topic_progress_info_exit);
        transaction.add(R.id.activity_grade_choice_grade_info_fragment_holder,fragment);
        transaction.commit();
    }

    private void ShowExitAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = (LayoutInflater.from(this)).inflate(R.layout.alert_dialog_exit_app,(FrameLayout)findViewById(R.id.alert_dialog_exit_app_dialog_holder));
        ((Button) view.findViewById(R.id.alert_dialog_exit_app_negative_btn)).setOnClickListener(v -> {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        });
        builder.setView(view);
        AlertDialog dialog  =builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        ((Button) view.findViewById(R.id.alert_dialog_exit_app_positive_btn)).setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

}