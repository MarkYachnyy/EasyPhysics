package ru.myitschool.vsu2021.markyachnyj.the_project.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public class Test implements Serializable {
    private String topic_name;
    private ArrayList<Task> tasks;
    private HashMap<Task, Boolean> given_answers;

    public Test(String topic_name, ArrayList<Task> tasks) {
        this.topic_name = topic_name;
        this.tasks = tasks;
        given_answers = new HashMap<>();
        for(Task task:this.tasks){
            given_answers.put(task, false);
        }
    }

    public String getTopicName() {
        return topic_name;
    }

    public void giveAnswer(Task task){
        given_answers.put(task,true);
    }

    public boolean isAnswerGiven(Task task){
        return given_answers.get(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public float getProgress(){
        int all = tasks.size();
        int done = 0;
        for(Task task:tasks){
            if(task.CheckAnswer()){
                done++;
            }
        }
        return (1f*done/all);
    }

}
