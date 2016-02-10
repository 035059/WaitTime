package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedQueue<com.company.Patient> waitingRoom = new LinkedQueue<>();
        LinkedQueue<Patient> nurse1 = new LinkedQueue<>();
        LinkedQueue<Patient> nurse2 = new LinkedQueue<>();
        int nurse1Time = 0;
        int nurse2Time = 0;
        int fastestTime = 0;

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

        System.out.println("Time you need with the doctor: ");
        int timeNeeded = scanner.nextInt();

        Patient curPat = new Patient("you", timeNeeded);
        waitingRoom.add(curPat);

        int[] timeHolder = userControl(waitingRoom, nurse1, nurse2, nurse1Time, nurse2Time, scanner);

        try {
            fastestTime = findFastestTime(waitingRoom, nurse1, nurse2);
        } catch (IllegalAccessException | InstantiationException ignore) {
        }

        System.out.println("Nurse 1 total wait time: " + timeHolder[0]);
        System.out.println("Nurse 2 total wait time: " + timeHolder[1]);
        System.out.println("You went to Nurse " + timeHolder[2] + ". Therefor, you had to wait " + timeHolder[timeHolder[2]-1] + " minutes.");

        System.out.println("The fastest time possible for everyone is : " + fastestTime + " minutes");
    }

    @SuppressWarnings("Duplicates")
    private static int[] userControl(LinkedQueue<Patient> waitingRoom, LinkedQueue<Patient> nurse1, LinkedQueue<Patient> nurse2, int nurse1Time, int nurse2Time, Scanner scanner) {
        int timeCounter = 0;
        Patient curPat;
        LinkedQueue<Patient> copyWaitingRoom = waitingRoom.copy();
        int nurseChoice = 1;

        assert copyWaitingRoom != null;
        for (int i = 0; i < copyWaitingRoom.getSize(); i++) {
            curPat = copyWaitingRoom.pop();
            System.out.println("Which nurse (1 or 2) should " + curPat.getName() + " go to?");
            while (true) {
                timeCounter++;
                nurseChoice = scanner.nextInt();
                if (nurseChoice == 1) {
                    nurse1.add(curPat);
                    nurse1Time += curPat.getTimeNeeded();
                    break;
                } else if (nurseChoice == 2) {
                    nurse2.add(curPat);
                    nurse2Time += curPat.getTimeNeeded();
                    break;
                } else {
                    System.out.println("Not a valid input");
                }
            }

            for (int a = 0; a < nurse1.getSize() ; a++) {
                Patient temp = nurse1.pop();
                if (temp.getTimeNeeded() > timeCounter) {
                    nurse1.add(temp);
                } else {
                    System.out.println(temp.getName() + " saw the doctor and was dequeued");
                    timeCounter = 0;
                }
            }

            for (int b = 0; b < nurse2.getSize() ; b++) {
                Patient temp = nurse2.pop();
                if (temp.getTimeNeeded() > timeCounter) {
                    nurse2.add(temp);
                } else {
                    System.out.println(temp.getName() + " saw the doctor and was dequeued");
                    timeCounter = 0;
                }
            }

            if (nurse1Time > 0)
                nurse1Time--;
            if (nurse2Time > 0)
                nurse2Time--;
            System.out.println("Nurse 1 Wait time: " + nurse1Time);
            System.out.println("Nurse 2 Wait time: " + nurse2Time);
        }

        return new int[] {nurse1Time, nurse2Time, nurseChoice};
    }

    @SuppressWarnings("unchecked")
    private static int findFastestTime (LinkedQueue<Patient> waitingRoom, LinkedQueue<Patient> nurse1, LinkedQueue<Patient> nurse2) throws IllegalAccessException, InstantiationException {
        int minTime;
        int nurse1Time = 0;
        int nurse2Time = 0;

        System.out.println("To get the fastest time: ");
        while (waitingRoom.getSize() > 0){
            Patient curPat = waitingRoom.pop();
            if (nurse1Time <= nurse2Time){
                System.out.println(curPat.getName() + " should go to Nurse 1");
                nurse1.add(curPat);
                nurse1Time += curPat.getTimeNeeded();
            } else {
                System.out.println(curPat.getName() + " should go to Nurse 2");
                nurse2.add(curPat);
                nurse2Time += curPat.getTimeNeeded();
            }

        }
        if (nurse1Time <= nurse2Time){
            minTime = nurse1Time;
        } else {
            minTime = nurse2Time;
        }

        return minTime - waitingRoom.getSize();
    }
}
