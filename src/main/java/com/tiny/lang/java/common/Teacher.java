package com.tiny.lang.java.common;

/**
 * @author tiny
 */
public class Teacher extends Person implements ISpeaker {

    private String position;

    private int workingAge;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getWorkingAge() {
        return workingAge;
    }

    public void setWorkingAge(int workingAge) {
        this.workingAge = workingAge;
    }

    @Override
    public void speak(String content) {
        System.out.println("make every effort to go to a good college.");
    }
}
