package ru.myitschool.vsu2021.markyachnyj.the_project.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.Database.DatabaseManager;
import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.activities.GradeChoiceActivity;
import ru.myitschool.vsu2021.markyachnyj.the_project.activities.TopicChoiceActivity;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views.RoundProgressBar;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;

public class GradeProgressInfoFragment extends Fragment {

    private Grade grade;
    private DatabaseManager manager;

    public GradeProgressInfoFragment(Grade g){
        super();
        this.grade = g;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grade_progress_info, container, false);
        Button Close_Btn = view.findViewById(R.id.fragment_grade_progress_info_close_btn);
        Close_Btn.setOnClickListener(Close_Btn_Listener);
        ((TextView)view.findViewById(R.id.fragment_grade_progress_info_name_tv)).setText(grade.getNumber()+" класс");
        ((TextView)view.findViewById(R.id.fragment_grade_progress_info_progress_tv)).setText("Пройдено "+grade.getTopic_completed()+" из "+grade.getTopic_count());
        ((RoundProgressBar)view.findViewById(R.id.fragment_grade_progress_info_progress_bar)).setProgress(grade.getProgress());
        Button Continue_Btn = (Button) view.findViewById(R.id.fragment_grade_progress_info_continue_btn);
        Continue_Btn.setOnClickListener(Continue_Btn_Listener);
        this.manager = new DatabaseManager(getActivity());
        return view;
    }

    private View.OnClickListener Close_Btn_Listener = v -> CloseFragment();

    private View.OnClickListener Continue_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            (new LoadTopicListAndStartGradeChoiceActivity()).execute();
        }
    };

    private void CloseFragment(){
        FrameLayout frame = getActivity().findViewById(R.id.activity_grade_choice_fading_frame);
        frame.setVisibility(View.GONE);
        frame.setClickable(false);
        Animation frame_animation = AnimationUtils.loadAnimation(getActivity(),R.anim.fade_out);
        frame.startAnimation(frame_animation);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_grade_topic_progress_info_enter,R.anim.fragment_grade_topic_progress_info_exit);
        transaction.remove(this);
        transaction.commit();
    }

    private class LoadTopicListAndStartGradeChoiceActivity extends AsyncTask<Void, Void, ArrayList<Topic>>{

        @Override
        protected ArrayList<Topic> doInBackground(Void... voids) {
            return manager.getAllTopics(grade.getNumber());
        }

        @Override
        protected void onPostExecute(ArrayList<Topic> topics) {
            super.onPostExecute(topics);
            Intent i = new Intent(getActivity(), TopicChoiceActivity.class);
            i.putExtra("topic_list",topics);
            startActivity(i);
            CloseFragment();
        }
    }

}