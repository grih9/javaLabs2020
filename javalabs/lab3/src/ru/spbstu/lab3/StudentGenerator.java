package ru.spbstu.lab3;


import java.util.concurrent.ArrayBlockingQueue;

public class StudentGenerator extends Thread {
    private static final int GENERATING_INTERVAL = 4000; // 4.3 sec

    private ArrayBlockingQueue<Student> studentsQueue;

    public StudentGenerator(ArrayBlockingQueue<Student> studentsQueue) {
        if (studentsQueue == null) {
            throw new IllegalArgumentException("Queue is null!");
        }
        this.studentsQueue = studentsQueue;
    }

    @Override
    public void run() {
        while (true) {
            int labsCount = (int) (Math.random() * (2 + 1)) + 1;
            int subjectNameNum = (int) (Math.random() * (2 + 1)) + 1;
            String subjectName = "";

            switch (labsCount) {
                case 1: {
                    labsCount = 10;
                    break;
                }
                case 2: {
                    labsCount = 20;
                    break;
                }
                case 3: {
                    labsCount = 100;
                    break;
                }
            }

            switch (subjectNameNum) {
                case 1: {
                    subjectName = "OOP";
                    break;
                }
                case 2: {
                    subjectName = "Physics";
                    break;
                }
                case 3: {
                    subjectName = "Math";
                    break;
                }
            }

            try {
                sleep(GENERATING_INTERVAL);
                studentsQueue.put(new Student(labsCount, subjectName));
                System.out.println("GENERATED " + labsCount + " labs."+ " Subject is " + subjectName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
