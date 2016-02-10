package com.company;

import java.util.*;

public class Main {
    /**
     * Main class - handles IO, and runs the queues
     * */

    public static void main(String[] args) {
        /*
         * Main function, gets the patients names and
         */

        // Variable declarations
        Scanner scanner = new Scanner(System.in);
        LinkedQueue<com.company.Patient> waitingRoom = new LinkedQueue<>();
        LinkedQueue<Patient> nurse1 = new LinkedQueue<>();
        LinkedQueue<Patient> nurse2 = new LinkedQueue<>();
        int nurse1Time = 0;
        int nurse2Time = 0;
        int fastestTime;

        // Ask the user how many patients are in the waiting room and get their response
        System.out.println("How many patients are there? ");
        int numPatients = scanner.nextInt();

        for (int i = 0; i < numPatients; i++) { // Iterate for the number of patients
            // Ask user for patient's name and the time they need with the nurse, and get their answers
            System.out.println("Patient name: ");
            String name = scanner.next();
            System.out.println("Time needed: ");
            int timeNeeded = scanner.nextInt();

            waitingRoom.add(new Patient(name, timeNeeded)); // Create a new Patient with the entered values and add it to the waitingRoom queue
            System.out.println(); // Print a blank line for spacing
        }

        waitingRoom.add(new Patient("you", 0)); // Add you to the queue, with zero time needed, as we just need to find time to get to the doctor

        int[] timeHolder = userControl(waitingRoom, nurse1, nurse2, scanner); // Call the userControl method

        fastestTime = findFastestTime(waitingRoom); // Call the findFastestTime method

        // Tell the user the total time for each nurse, the time they waited, and the fastest time possible
        System.out.println("\n\nNurse 1 total wait time: " + timeHolder[0]);
        System.out.println("Nurse 2 total wait time: " + timeHolder[1]);
        System.out.println("You went to Nurse " + timeHolder[2] + ". Therefore, you had to wait " + timeHolder[timeHolder[2]-1] + " minutes.");
        System.out.println("The fastest time possible for everyone is : " + fastestTime + " minutes");
    }

    @SuppressWarnings("Duplicates")
    private static int[] userControl(LinkedQueue<Patient> waitingRoom, LinkedQueue<Patient> nurse1, LinkedQueue<Patient> nurse2, Scanner scanner) {
        Patient curPat;
        LinkedQueue<Patient> copyWaitingRoom = waitingRoom.copy();
        int nurseChoice = 1;
        int totalTime = 0;
        int nurse1Time = 0;
        int nurse2Time = 0;

        assert copyWaitingRoom != null;
        int holdSize = copyWaitingRoom.getSize();
        for (int i = 0; i < holdSize; i++) {
            curPat = copyWaitingRoom.pop();
            System.out.println("Which nurse (1 or 2) should " + curPat.getName() + " go to?");
            while (true) {
                nurseChoice = scanner.nextInt();
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


            nurse1Time = 0;
            nurse2Time = 0;

            int holdSizeNurse = nurse1.getSize();
            for (int x = 0; x < holdSizeNurse; x++) { // Iterate for the size of the queue
                Patient temp = nurse1.pop(); // Pop the front of the queue
                nurse1.add(temp); // Add that element to this queue again
                nurse1Time += temp.getTimeNeeded(); // Add that time to nurse1Time
            }

            holdSizeNurse = nurse2.getSize();
            for (int x = 0; x < holdSizeNurse; x++) { // Iterate for the size of the queue
                Patient temp = nurse2.pop(); // Pop the front of the queue
                nurse2.add(temp); // Add that element to this queue again
                nurse2Time += temp.getTimeNeeded(); // Add that time to nurse1Time
            }

            System.out.println("Nurse 1 current wait time: " + nurse1Time);
            System.out.println("Nurse 2 current wait time: " + nurse2Time);

            for (int a = 0; a < nurse1.getSize() ; a++) {
                Patient temp = nurse1.pop();
                if (temp.getTimeNeeded() > 0) {
                    nurse1.add(new Patient(temp.getName(), temp.getTimeNeeded()-1));
                } else {
                    System.out.println(temp.getName() + " saw Nurse 1 and was dequeued");
                }
            }

            for (int b = 0; b < nurse2.getSize() ; b++) {
                Patient temp = nurse2.pop();
                if (temp.getTimeNeeded() > 0) {
                    nurse2.add(new Patient(temp.getName(), temp.getTimeNeeded()-1));
                } else {
                    System.out.println(temp.getName() + " saw Nurse 2 and was dequeued");
                }
            }
            totalTime++;
        }

        return new int[] {totalTime + nurse1Time + 1, totalTime + nurse2Time + 1, nurseChoice};
    }

    @SuppressWarnings("unchecked")
    private static int findFastestTime (LinkedQueue<Patient> waitingRoom) {
        int fastestTime = 0;
        
        LinkedQueue<Patient> nurse1 = new LinkedQueue<>();
        LinkedQueue<Patient> nurse2 = new LinkedQueue<>();

        while(nurse1.getSize() > 0 || nurse2.getSize() > 0) {
            for (int i = 0; i < waitingRoom.getSize(); i++) {

            }

        }
        
        return fastestTime;
    }
}
