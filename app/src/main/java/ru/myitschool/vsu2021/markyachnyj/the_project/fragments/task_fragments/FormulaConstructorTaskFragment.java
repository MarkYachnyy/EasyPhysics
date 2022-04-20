package ru.myitschool.vsu2021.markyachnyj.the_project.fragments.task_fragments;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.activities.TestSolverActivity;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views.FormulaComponentPlaceholderView;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views.FormulaComponentView;
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

    private FormulaComponentView Chosen_View=null;

    private final int ComponentViewMeasurement = 200;
    private final int numerator_size;
    private final int denominator_size;

    private FormulaConstructorTask task;

    public FormulaConstructorTaskFragment(FormulaConstructorTask task) {
        super();
        this.task = task;
        numerator_size = task.getFormula().getNumerator().size();
        denominator_size = task.getFormula().getDenominator().size();
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
        PlaceComponentViews();
        return view;
    }

    private void PlaceComponentPlaceholders(){
        for(String s:task.getFormula().getNumerator()){
            FrameLayout fl = new FrameLayout(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ComponentViewMeasurement,ComponentViewMeasurement);
            fl.setLayoutParams(params);
            Numerator_LL.addView(fl);
            fl.setOnClickListener(FormulaComponentViewPlaceHolderFLListener);
            fl.addView(new FormulaComponentPlaceholderView(getActivity()));
        }
        if(!task.getFormula().getDenominator().isEmpty()){
            Denominator_LL = new LinearLayout(getActivity());
            LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            Denominator_LL.setLayoutParams(params);
            Denominator_LL.setGravity(Gravity.CENTER);
            Denominator_LL.setOrientation(LinearLayout.HORIZONTAL);
            Fraction_LL.addView(Denominator_LL);
            for(String s:task.getFormula().getDenominator()){
                FrameLayout fl = new FrameLayout(getActivity());
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ComponentViewMeasurement,ComponentViewMeasurement);
                fl.setLayoutParams(params1);
                Denominator_LL.addView(fl);
                fl.setOnClickListener(FormulaComponentViewPlaceHolderFLListener);
                fl.addView(new FormulaComponentPlaceholderView(getActivity()));
            }
        }
    }

    private void PlaceComponentViews(){
        for(String s:task.getAllComponents()){
            FormulaComponentView view = new FormulaComponentView(getActivity(),s);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ComponentViewMeasurement, ComponentViewMeasurement);
            view.setLayoutParams(params);
            Components_LL.addView(view);
            view.setFocusable(true);
            view.setOnClickListener(FormulaComponentViewListener);
        }
    }

    private View.OnClickListener FormulaComponentViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getParent().equals(Components_LL)){
                if(Chosen_View!=null){
                    Chosen_View.set_Chosen(false);
                }
                ((FormulaComponentView)v).set_Chosen(true);
                Chosen_View = (FormulaComponentView) v;
            } else {
                FrameLayout fl = (FrameLayout) v.getParent();
                fl.removeView(v);
                Components_LL.addView(v);
                if(Chosen_View!=null){
                    Chosen_View.set_Chosen(false);
                    Components_LL.removeView(Chosen_View);
                    (fl).addView(Chosen_View);
                    Chosen_View = null;
                }
            }
            CheckIfTheAnswerIsGiven();
        }
    };

    private View.OnClickListener FormulaComponentViewPlaceHolderFLListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(Chosen_View!=null){
                Chosen_View.set_Chosen(false);
                Components_LL.removeView(Chosen_View);
                ((FrameLayout)v).addView(Chosen_View);
                Chosen_View = null;
            }
            CheckIfTheAnswerIsGiven();
        }
    };

    private void CheckIfTheAnswerIsGiven(){
        boolean flag = true;
        for(int i=0;i<numerator_size;i++){
            FrameLayout frameLayout = (FrameLayout) Numerator_LL.getChildAt(i);
            if(frameLayout.getChildAt(1)==null){
                flag = false;
                break;
            }
        }
        for(int i=0;i<denominator_size;i++){
            FrameLayout frameLayout = (FrameLayout) Denominator_LL.getChildAt(i);
            if(frameLayout.getChildAt(1)==null){
                flag =false;
                break;
            }
        }
        ((TestSolverActivity)getActivity()).GiveAnswer(task, flag);
    }

    @Override
    public Task getTask() {
        return task;
    }
}