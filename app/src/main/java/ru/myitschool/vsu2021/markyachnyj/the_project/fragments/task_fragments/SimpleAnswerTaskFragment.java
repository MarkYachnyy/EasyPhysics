package ru.myitschool.vsu2021.markyachnyj.the_project.fragments.task_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.activities.TestSolverActivity;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.SimpleAnswerTask;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;


public class SimpleAnswerTaskFragment extends TaskFragment{

    private SimpleAnswerTask task;
    private TextView Exercise_TV;
    private EditText Answer_ET;
    private ImageButton Save_Answer_Btn;

    public SimpleAnswerTaskFragment(SimpleAnswerTask task){
        super();
        this.task = task;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_answer_task, container, false);
        Exercise_TV = (TextView) view.findViewById(R.id.fragment_simple_answer_task_exercise_tv);
        Answer_ET = (EditText) view.findViewById(R.id.fragment_simple_answer_task_answer_et);
        Save_Answer_Btn = (ImageButton) view.findViewById(R.id.fragment_simple_answer_task_save_answer_btn);
        Exercise_TV.setText(task.getExercise());
        Save_Answer_Btn.setOnClickListener(Save_Answer_Btn_Listener);
        return view;
    }

    private View.OnClickListener Save_Answer_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!Answer_ET.getText().toString().equals("")){
                task.giveAnswer(Answer_ET.getText().toString());
                ((TestSolverActivity)getActivity()).GiveAnswer(task, true);
                Toast.makeText(getActivity(), "Ответ сохранён", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public Task getTask() {
        return task;
    }
}