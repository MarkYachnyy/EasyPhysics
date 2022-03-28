package ru.myitschool.vsu2021.markyachnyj.the_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.fragments.task_fragments.SimpleAnswerTaskFragment;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Test;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public class TestSolverActivity extends AppCompatActivity {

    private Test test;
    private HashMap<Button, Fragment> fragment_map;
    private ArrayList<Button> task_button_list;

    private LinearLayout Task_Buttons_LL;
    private Button Next_Task_Btn;
    private Button Previous_Task_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_solver);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.activity_test_solver_task_fragment_holder,new SimpleAnswerTaskFragment());
        ft.commit();
    }
}