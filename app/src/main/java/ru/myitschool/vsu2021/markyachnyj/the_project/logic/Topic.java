package ru.myitschool.vsu2021.markyachnyj.the_project.logic;

public class Topic {
    private String name;
    private int test_count;
    private int test_passed;

    public Topic(String name, int test_count, int test_passed) {
        this.name = name;
        this.test_count = test_count;
        this.test_passed = test_passed;
    }

    public float getProgress(){
        return 1f*test_passed/test_count;
    }

    public String getName() {
        return name;
    }

    public int getTest_count() {
        return test_count;
    }

    public int getTest_passed() {
        return test_passed;
    }
}
