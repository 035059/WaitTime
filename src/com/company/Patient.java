package com.company;

/**
 * Created by allin on 28/01/16.
 */
public class Patient implements Cloneable{

    private String name;
    private int timeNeeded;

    Patient(String name, int timeNeeded) {
        this.name = name;
        this.timeNeeded = timeNeeded;
    }

    public String getName() {
        return name;
    }

    public int getTimeNeeded() {
        return timeNeeded;
    }

    @Override
    public Patient clone() throws CloneNotSupportedException {
        super.clone();
        return new Patient(name, timeNeeded);
    }
}
