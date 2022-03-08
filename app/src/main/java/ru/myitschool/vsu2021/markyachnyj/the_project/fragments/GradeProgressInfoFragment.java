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

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class GradeProgressInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grade_progress_info, container, false);
        Button Close_Btn = view.findViewById(R.id.fragment_grade_progress_info_close_btn);
        Close_Btn.setOnClickListener(Close_Btn_Listener);
        return view;
    }

    private View.OnClickListener Close_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CloseFragment();
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

}