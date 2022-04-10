package ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks;

import java.io.Serializable;

public abstract class Task implements Serializable {
    public abstract boolean CheckAnswer();
    public abstract String getRightAnswer();
    public abstract String getGivenAnswer();
}
