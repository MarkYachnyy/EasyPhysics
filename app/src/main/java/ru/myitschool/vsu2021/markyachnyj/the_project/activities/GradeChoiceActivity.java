package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.Database.DatabaseManager;
import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.fragments.GradeProgressInfoFragment;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Adapters.GradeAdapter;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;

public class GradeChoiceActivity extends AppCompatActivity {

    private ListView listView;
    private GradeAdapter adapter;

    private ImageButton Settings_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_choice);
        listView = (ListView) findViewById(R.id.activity_grade_choice_list);
        adapter = new GradeAdapter(getApplicationContext(), (ArrayList<Grade>) getIntent().getSerializableExtra("grade_list"));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(ItemListener);
        Settings_Btn = (ImageButton) findViewById(R.id.activity_grade_choice_settings_btn);
        Settings_Btn.setOnClickListener(Settings_Btn_Listener);
    }

    @Override
    public void onBackPressed() {
        ShowExitAlertDialog();
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
        GradeProgressInfoFragment fragment = new GradeProgressInfoFragment(grade);
        transaction.setCustomAnimations(R.anim.fragment_grade_topic_progress_info_enter,R.anim.fragment_grade_topic_progress_info_exit);
        transaction.add(R.id.activity_grade_choice_grade_info_fragment_holder,fragment);
        transaction.commit();
    }

    private void ShowSettingsAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = (LayoutInflater.from(this)).inflate(R.layout.alert_dialog_settings, (FrameLayout) findViewById(R.id.alert_dialog_settings_dialog_holder));
        ((Button) view.findViewById(R.id.alert_dialog_settings_developer_btn)).setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/octaneinreallife"));
            startActivity(i);
        });
        ((Button) view.findViewById(R.id.alert_dialog_settings_reset_database_btn)).setOnClickListener(v -> Toast.makeText(GradeChoiceActivity.this, "Чтобы сбросить БД, зажмите кнопку", Toast.LENGTH_SHORT).show());
        ((Button) view.findViewById(R.id.alert_dialog_settings_reset_database_btn)).setOnLongClickListener(v -> {
            ((Button) v).setText("Сброс...");
            (new ResetDBTask()).execute();
            return false;
        });
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        ((Button) view.findViewById(R.id.alert_dialog_setings_close_btn)).setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    private View.OnClickListener Settings_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ShowSettingsAlertDialog();
        }
    };

    private class ResetDBTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            DatabaseManager db = new DatabaseManager(GradeChoiceActivity.this);
            db.deleteAllGrades();
            db.deleteAllTopics();
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            System.exit(0);
        }
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