package ru.myitschool.vsu2021.markyachnyj.the_project.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public class Test implements Serializable {
    private Topic topic;
    private ArrayList<Task> tasks;
    private HashMap<Task, Boolean> given_answers;

    public Test(Topic topic, ArrayList<Task> tasks) {
        this.topic=topic;
        this.tasks = tasks;
        given_answers = new HashMap<>();
        for(Task task:this.tasks){
            given_answers.put(task, false);
        }
    }

    public Topic getTopic(){return topic;}

    public void giveAnswer(Task task, boolean value){
        given_answers.put(task,value);
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
