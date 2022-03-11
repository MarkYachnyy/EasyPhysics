package ru.myitschool.vsu2021.markyachnyj.the_project.fragments;

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

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views.RoundProgressBar;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;

public class TopicProgressInfoFragment extends Fragment {

    private Topic topic;

    public TopicProgressInfoFragment(Topic t){
        super();
        this.topic = t;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_progress_info, container, false);
        Button Back_Btn = (Button)view.findViewById(R.id.fragment_topic_progress_info_close_btn);
        Back_Btn.setOnClickListener(Back_Btn_Listener);
        ((TextView)view.findViewById(R.id.fragment_topic_progress_info_name_tv)).setText(topic.getName());
        ((TextView)view.findViewById(R.id.fragment_topic_progress_info_progress_tv)).setText("Лучший прогресс по тесту: "+(int)(100*topic.getProgress())+"%");
        ((RoundProgressBar)view.findViewById(R.id.fragment_topic_progress_info_progress_bar)).setProgress(topic.getProgress());
        return view;
    }

    private void CloseFragment(){
        FrameLayout frame = getActivity().findViewById(R.id.activity_topic_choice_fading_frame);
        frame.setVisibility(View.GONE);
        frame.setClickable(false);
        Animation frame_animation = AnimationUtils.loadAnimation(getActivity(),R.anim.fade_out);
        frame.startAnimation(frame_animation);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.fragment_grade_topic_progress_info_enter,R.anim.fragment_grade_topic_progress_info_exit);
        ft.remove(this);
        ft.commit();
    }

    private View.OnClickListener Back_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CloseFragment();
        }
    };
}