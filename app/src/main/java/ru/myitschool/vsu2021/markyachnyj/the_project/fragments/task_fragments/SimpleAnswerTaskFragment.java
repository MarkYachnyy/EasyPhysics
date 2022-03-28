package ru.myitschool.vsu2021.markyachnyj.the_project.fragments.task_fragments;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.SimpleAnswerTask;


public class SimpleAnswerTaskFragment extends Fragment {

    private SimpleAnswerTask task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple_answer_task, container, false);

    }
}