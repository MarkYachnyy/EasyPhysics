package ru.myitschool.vsu2021.markyachnyj.the_project.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class TopicProgressInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topic_progress_info, container, false);
    }
}