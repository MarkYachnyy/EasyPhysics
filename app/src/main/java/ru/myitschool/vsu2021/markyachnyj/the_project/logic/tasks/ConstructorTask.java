package ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks;

import java.io.Serializable;

public class ConstructorTask extends Task implements Serializable {
    @Override
    public boolean CheckAnswer() {
        return false;
    }

    @Override
    public String getRightAnswer() {
        return null;
    }

    @Override
    public String getGivenAnswer() {
        return null;
    }
}
