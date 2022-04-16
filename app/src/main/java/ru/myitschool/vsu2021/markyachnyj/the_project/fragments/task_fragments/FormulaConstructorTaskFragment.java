package ru.myitschool.vsu2021.markyachnyj.the_project.fragments.task_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.FormulaConstructorTask;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public class FormulaConstructorTaskFragment extends TaskFragment {

    private FormulaConstructorTask task;

    public FormulaConstructorTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formula_constructor_task, container, false);

        return view;
    }





    @Override
    public Task getTask() {
        return task;
    }
}