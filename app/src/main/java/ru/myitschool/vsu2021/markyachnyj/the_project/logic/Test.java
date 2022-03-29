package ru.myitschool.vsu2021.markyachnyj.the_project.logic;

import java.util.ArrayList;
import java.util.HashMap;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public class Test {
    private ArrayList<Task> tasks;
    private HashMap<Task, Boolean> given_answers;

    public Test(ArrayList<Task> tasks) {
        this.tasks = tasks;
        given_answers = new HashMap<>();
        for(Task task:this.tasks){
            given_answers.put(task, false);
        }
    }

    public void giveAnswer(Task task){
        given_answers.put(task,true);
    }

    public HashMap<Task, Boolean> getGiven_answers() {
        return given_answers;
    }

    public boolean isAnswerGiven(Task task){
        return given_answers.get(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
