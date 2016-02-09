package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalTime = 0;

        LinkedQueue<com.company.Patient> waitingRoom = new LinkedQueue<>();
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
        int[] timeHolder = userControl(waitingRoom, nurse1, nurse2, nurse1Time, nurse2Time, scanner);

        try {
            fastestTime = findFastestTime(waitingRoom, nurse1, nurse2);
        } catch (IllegalAccessException | InstantiationException ignore) {
        }

        System.out.println("Nurse 1 total wait time: " + timeHolder[0]);
        System.out.println("Nurse 2 total wait time: " + timeHolder[1]);
        System.out.println("You went to Nurse " + timeHolder[2] + ". Therefor, you had to wait");

        System.out.println("The fastest time possible for everyone is : " + fastestTime + " minutes");
    }

    private static int[] userControl(LinkedQueue<Patient> waitingRoom, LinkedQueue<Patient> nurse1, LinkedQueue<Patient> nurse2, int nurse1Time, int nurse2Time, Scanner scanner) {
        Patient curPat;
        LinkedQueue<Patient> copyWaitingRoom = waitingRoom.copy();
        int nurseChoice;

        assert copyWaitingRoom != null;
        for (int i = 0; i < copyWaitingRoom.getSize(); i++) {
            curPat = waitingRoom.pop();
            System.out.println("Which nurse (1 or 2) would you like to send " + curPat.getName() + " to?");
            nurseChoice = scanner.nextInt();
            while (true) {
                if (nurseChoice == 1) {
                    nurse1.add(curPat);
                    break;
                } else if (nurseChoice == 2) {
                    nurse2.add(curPat);
                    break;
                } else {
                    System.out.println("Not a valid input");
                }
            }
            nurse1Time--;
            nurse2Time--;
            System.out.println("Nurse 1 Wait time: " + nurse1Time);
            System.out.println("Nurse 2 Wait time: " + nurse2Time);
        }
        System.out.println("Which nurse (1 or 2) would you like to got to?");
        nurseChoice = scanner.nextInt();

        return new int[] {nurse1Time, nurse2Time, nurseChoice};
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

    private void printQueue(LinkedQueue queue) {

    }
}
