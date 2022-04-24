package ru.myitschool.vsu2021.markyachnyj.the_project.fragments.task_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.UnitChoiceTask;

public class UnitChoiceTaskFragment extends Fragment {

    private TextView Exercise_TV;
    private RadioGroup Answers_RG;
    private UnitChoiceTask task;

    public UnitChoiceTaskFragment(UnitChoiceTask task){
        super();
        this.task = task;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_unit_choice_task, container, false);
        Exercise_TV  = (TextView) view.findViewById(R.id.fragment_unit_choice_task_exercise_tv);
        Answers_RG = (RadioGroup) view.findViewById(R.id.fragment_unit_choice_task_answers_rg);

        return view;
    }
}