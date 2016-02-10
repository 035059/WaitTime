package com.company;

/**
 * Created by allin on 28/01/16.
 */
public class Patient {
    /**
     * Holds the data for each patient, consisting of their name, and the time they need with the doc
     * */

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
}
