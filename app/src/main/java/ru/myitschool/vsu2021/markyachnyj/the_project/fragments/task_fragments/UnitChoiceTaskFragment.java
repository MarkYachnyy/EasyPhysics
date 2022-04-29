package ru.myitschool.vsu2021.markyachnyj.the_project.fragments.task_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.activities.TestSolverActivity;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.UnitChoiceTask;

public class UnitChoiceTaskFragment extends TaskFragment {

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
        Exercise_TV = (TextView) view.findViewById(R.id.fragment_unit_choice_task_exercise_tv);
        Exercise_TV.setText(task.getExercise());
        for(String s:task.getAllAnswers()){
            RadioButton rb =new RadioButton(getActivity());
            rb.setText(s);
            rb.setTextSize(30);
            rb.setTextColor(getResources().getColor(R.color.white));
            rb.setBackgroundColor(getResources().getColor(R.color.dark_blue_pastel));
            RadioGroup.LayoutParams params =new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
            params.weight = 1;
            params.setMargins(20,20,20,20);
            rb.setLayoutParams(params);
            Answers_RG.addView(rb);
            rb.setOnClickListener(v -> {
                task.setChosen_answer(((RadioButton)v).getText().toString());
                ((TestSolverActivity)getActivity()).GiveAnswer(task, true);
            });
        }

        return view;
    }

    @Override
    public Task getTask() {
        return task;
    }
}