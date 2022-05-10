package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.Database.DatabaseManager;
import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.fragments.TopicProgressInfoFragment;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Adapters.TopicAdapter;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Test;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;
import ru.myitschool.vsu2021.markyachnyj.the_project.theory.GithubResourceManager;

public class TopicChoiceActivity extends AppCompatActivity {

    private TopicAdapter adapter;
    private ArrayList<Topic> data;
    private ListView list;
    private Button Back_Button;
    private TextView Grade_TV;
    private FrameLayout Screen_FL;
    private ImageButton Info_Btn;

    private Grade grade;

    private GithubResourceManager githubManager;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_choice);
        Back_Button = findViewById(R.id.activity_topic_choice_back_btn);
        list = (ListView) findViewById(R.id.activity_topic_choice_list);
        githubManager = new GithubResourceManager();
        databaseManager  =new DatabaseManager(this);
        data = (ArrayList<Topic>) getIntent().getSerializableExtra("topic_list");
        Grade_TV = (TextView) findViewById(R.id.activity_topic_choice_grade_tv);
        Screen_FL = (FrameLayout) findViewById(R.id.activity_topic_choice_screen_fl);
        grade = (Grade) getIntent().getSerializableExtra("grade");
        Info_Btn = (ImageButton) findViewById(R.id.activity_topic_choice_info_btn);
        adapter = new TopicAdapter(getApplicationContext(),data);
        list.setAdapter(adapter);
        list.setOnItemClickListener(ItemListener);
        Back_Button.setOnClickListener(Back_Btn_Listener);
        Grade_TV.setText(grade.getNumber()+" класс");
        Info_Btn.setOnClickListener(Info_Btn_Listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        (new UpdateTopicListTask()).execute();
        try{
            Screen_FL.removeViewAt(4);
        } catch (Exception e){

        }
    }

    private View.OnClickListener Back_Btn_Listener = v -> finish();

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

    public void StartTestActivity(Topic topic){
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setBackgroundColor(getResources().getColor(R.color.main_bg_green));
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Screen_FL.addView(frameLayout);
        TextView textView = new TextView(this);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setTextSize(50);
        textView.setGravity(Gravity.CENTER);
        textView.setText("Подождите...");
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        textView.setLayoutParams(params);
        frameLayout.addView(textView);
        (new LoadTestAndStartTestSolverActivityTask()).execute(topic);
    }

    private class UpdateTopicListTask extends AsyncTask<Void,Void,ArrayList<Topic>>{

        @Override
        protected ArrayList<Topic> doInBackground(Void... voids) {
            databaseManager = new DatabaseManager(getApplicationContext());
            return databaseManager.getAllTopics(grade.getNumber());
        }

        @Override
        protected void onPostExecute(ArrayList<Topic> topics) {
            super.onPostExecute(topics);
            adapter.setNewData(topics);
        }
    }

    private class LoadTestAndStartTestSolverActivityTask extends AsyncTask<Topic, Void, Test>{

        @Override
        protected Test doInBackground(Topic... topics) {
            Topic t = topics[0];
            return githubManager.BuildTest(t);
        }

        @Override
        protected void onPostExecute(Test test) {
            super.onPostExecute(test);
            Intent intent =new Intent(TopicChoiceActivity.this, TestSolverActivity.class);
            intent.putExtra("test",test);
            startActivity(intent);
        }
    }

    private View.OnClickListener Info_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(TopicChoiceActivity.this);
            View view =(LayoutInflater.from(TopicChoiceActivity.this)).inflate(R.layout.alert_dialog_topic_choice_info,(FrameLayout)findViewById(R.id.alert_dialog_topic_choice_info_dialog_holder));
            builder.setView(view);
            AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
            ((Button)view.findViewById(R.id.alert_dialog_topic_choice_info_ok_btn)).setOnClickListener(v1 -> dialog.dismiss());
            dialog.show();
        }
    };
}