package ru.myitschool.vsu2021.markyachnyj.the_project.fragments.task_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views.FormulaComponentPlaceholderView;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Test;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.FormulaConstructorTask;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public class FormulaConstructorTaskFragment extends TaskFragment {

    private TextView Exercise_TV;
    private TextView Value_Symbol_TV;
    private LinearLayout Fraction_LL;
    private LinearLayout Numerator_LL;
    private LinearLayout Denominator_LL;
    private LinearLayout Components_LL;

    private final int ComponentViewMeasurement = 200;

    private FormulaConstructorTask task;

    public FormulaConstructorTaskFragment(FormulaConstructorTask task) {
        super();
        this.task = task;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formula_constructor_task, container, false);
        Exercise_TV = (TextView) view.findViewById(R.id.fragment_formula_constructor_task_exercise_tv);
        Fraction_LL = (LinearLayout) view.findViewById(R.id.fragment_formula_constructor_task_fraction_ll);
        Numerator_LL = (LinearLayout) view.findViewById(R.id.fragment_formula_constructor_task_numerator_ll);
        Components_LL = (LinearLayout) view.findViewById(R.id.fragment_formula_constructor_task_components_ll);
        Value_Symbol_TV = (TextView) view.findViewById(R.id.fragment_formula_constructor_task_value_symbol_tv);
        Exercise_TV.setText(task.getExercise());
        Value_Symbol_TV.setText(task.getFormula().getValue_symbol());
        PlaceComponentPlaceholders();
        return view;
    }

    private void PlaceComponentPlaceholders(){
        for(String s:task.getFormula().getNumerator()){
            FrameLayout fl = new FrameLayout(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ComponentViewMeasurement,ComponentViewMeasurement);
            fl.setLayoutParams(params);
            Numerator_LL.addView(fl);
            fl.addView(new FormulaComponentPlaceholderView(getActivity()));
        }
        if(!task.getFormula().getDenominator().isEmpty()){
            Denominator_LL = new LinearLayout(getActivity());
            LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            Denominator_LL.setLayoutParams(params);
            Denominator_LL.setGravity(Gravity.CENTER);
            Fraction_LL.addView(Denominator_LL);
            for(String s:task.getFormula().getDenominator()){
                FrameLayout fl = new FrameLayout(getActivity());
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ComponentViewMeasurement,ComponentViewMeasurement);
                fl.setLayoutParams(params1);
                Denominator_LL.addView(fl);
                fl.addView(new FormulaComponentPlaceholderView(getActivity()));
            }
        }
    }

    @Override
    public Task getTask() {
        return task;
    }
}