package com.company;

import java.util.*;
import com.company.LinkedQueue;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalTime = 0;

        LinkedQueue<Patient> waitingRoom = new LinkedQueue<>();
        LinkedQueue<Patient> tempWaitingRoom = new LinkedQueue<>();
        LinkedQueue<Patient> nurse1 = new LinkedQueue<>();
        LinkedQueue<Patient> nurse2 = new LinkedQueue<>();
        int nurse1Time = 0;
        int nurse2Time = 0;
        int fastestTime = 0;
        int yourTime;

        System.out.println("How many patients are there? ");
        int numPatients = scanner.nextInt();

        for (int i = 0; i < numPatients; i++) {
            System.out.println("Patient name: ");
            String name = scanner.next();
            System.out.println("Time needed: ");
            int timeNeeded = scanner.nextInt();


            Patient curPat = new Patient(name, timeNeeded);
            waitingRoom.add(curPat);
            System.out.println();
        }
        try {
            fastestTime = findFastestTime(waitingRoom, nurse1, nurse2);
        } catch (IllegalAccessException | InstantiationException ignore) {
        }

        System.out.println("The fastest time possible is : " + fastestTime + " minutes");
    }

    private void userControl(LinkedQueue<Patient> waitingRoom, LinkedQueue<Patient> nurse1, LinkedQueue<Patient> nurse2) {

    }

    @SuppressWarnings("unchecked")
    private static int findFastestTime (LinkedQueue<Patient> waitingRoom, LinkedQueue<Patient> nurse1, LinkedQueue<Patient> nurse2)
            throws IllegalAccessException, InstantiationException {
        int minTime = 0;
        int nurse1Time = 0;
        int nurse2Time = 0;

        while (waitingRoom.getSize() > 0){
            Patient curPat = waitingRoom.pop();
            if (nurse1Time <= nurse2Time){
                nurse1.add(curPat);
                nurse1Time += curPat.getTimeNeeded();
            } else {
                nurse2.add(curPat);
                nurse2Time += curPat.getTimeNeeded();
            }
        }
        if (nurse1Time <= nurse2Time){
            minTime = nurse1Time - waitingRoom.getSize();
        } else {
            minTime = nurse2Time - waitingRoom.getSize();
        }

        return minTime - waitingRoom.getSize();
    }
}
