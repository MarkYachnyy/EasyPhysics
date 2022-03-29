package ru.myitschool.vsu2021.markyachnyj.the_project.fragments.task_fragments;

import androidx.fragment.app.Fragment;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public abstract class TaskFragment extends Fragment {
    public abstract Task getTask();
}
