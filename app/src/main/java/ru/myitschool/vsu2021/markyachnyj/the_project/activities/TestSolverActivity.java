package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.fragments.task_fragments.SimpleAnswerTaskFragment;
import ru.myitschool.vsu2021.markyachnyj.the_project.fragments.task_fragments.TaskFragment;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Test;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.SimpleAnswerTask;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public class TestSolverActivity extends AppCompatActivity {

    private Test test;
    private HashMap<Button, TaskFragment> fragment_map;
    private ArrayList<Button> task_buttons;
    private int current_task_id=0;

    private LinearLayout Task_Buttons_LL;
    private ImageButton Next_Task_Btn;
    private ImageButton Previous_Task_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_solver);
        Task_Buttons_LL = (LinearLayout) findViewById(R.id.activity_test_solver_task_buttons_ll);
        Next_Task_Btn = (ImageButton) findViewById(R.id.activity_test_solver_next_task_btn);
        Previous_Task_Btn = (ImageButton) findViewById(R.id.activity_test_solver_previous_task_btn);
        task_buttons = new ArrayList<>();
        fragment_map = new HashMap<>();
        Next_Task_Btn.setOnClickListener(Next_Task_Btn_Listener);
        Previous_Task_Btn.setOnClickListener(Previous_Task_Btn_Listener);
        /*TEST*/
        ArrayList<Task> tasks = new ArrayList<>();
        for(int i=0;i<6;i++){
            tasks.add(new SimpleAnswerTask("Exercise"+String.valueOf(i+1),"???"));
        }
        test = new Test(tasks);
        /*TEST*/
        PlaceExerciseButtons();
        MakeExerciseFragments();
        task_buttons.get(0).callOnClick();
    }

    private void PlaceExerciseButtons(){
        for(int i=0;i<test.getTasks().size();i++){
            Button btn = new Button(this);
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(getResources().getColor(R.color.red_pastel)));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins(0,5,0,5);
            btn.setLayoutParams(params);
            btn.setText(String.valueOf(i+1));
            btn.setTextColor(getResources().getColor(R.color.white));
            btn.setTextSize(30);
            btn.setOnClickListener(Task_Button_Listener);
            Task_Buttons_LL.addView(btn);
            task_buttons.add(btn);
        }
    }

    private void MakeExerciseFragments(){
        for(int i=0;i<test.getTasks().size();i++){
            Task task = test.getTasks().get(i);
            if(task.getClass().equals(SimpleAnswerTask.class)){
                fragment_map.put(task_buttons.get(i),new SimpleAnswerTaskFragment((SimpleAnswerTask)task));
            }
        }
    }

    public void GiveAnswer(Task task){
        test.giveAnswer(task);
    }

    private Comparator<Button> task_button_cmp = new Comparator<Button>() {
        @Override
        public int compare(Button o1, Button o2) {
            return o1.getText().toString().compareTo(o2.getText().toString());
        }
    };

    private View.OnClickListener Task_Button_Listener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int new_current_task_id = Collections.binarySearch(task_buttons, (Button) v,task_button_cmp)+1;
            SetActivatedNextPreviousTaskBtn(Previous_Task_Btn, new_current_task_id != 1);
            SetActivatedNextPreviousTaskBtn(Next_Task_Btn, new_current_task_id != task_buttons.size());
            if(new_current_task_id!=current_task_id){
                ((Button) v).setTextSize(50);
                if(current_task_id>0){
                    task_buttons.get(current_task_id-1).setTextSize(30);
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if(new_current_task_id>current_task_id){
                    ft.setCustomAnimations(R.anim.fragment_task_enter_from_right, R.anim.fragment_task_exit_to_left);
                } else {
                    ft.setCustomAnimations(R.anim.fragment_task_enter_from_left, R.anim.fragment_task_exit_to_right);
                }
                ft.replace(R.id.activity_test_solver_task_fragment_holder_fl, fragment_map.get(v));
                ft.commit();
                current_task_id = new_current_task_id;
            }
        }
    };

    private void SetActivatedNextPreviousTaskBtn(ImageButton btn, boolean activated){
        btn.setClickable(activated);
        if(activated){
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(getResources().getColor(R.color.dark_blue)));
        } else {
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(getResources().getColor(R.color.light_gray)));
        }
    }

    private View.OnClickListener Next_Task_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            task_buttons.get(current_task_id).callOnClick();
        }
    };

    private View.OnClickListener Previous_Task_Btn_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            task_buttons.get(current_task_id-2).callOnClick();
        }
    };

    public void InvalidateTaskButtons(){
        for(Map.Entry<Button,TaskFragment> entry:fragment_map.entrySet()){
            Task task = entry.getValue().getTask();
            if(test.isAnswerGiven(task)){
                ViewCompat.setBackgroundTintList(entry.getKey(), ColorStateList.valueOf(getResources().getColor(R.color.light_green_pastel)));
            }
        }
    }
}