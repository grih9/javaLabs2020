package ru.spbstu.lab3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class StudentGenerator extends Thread {
    private static final int GENERATING_INTERVAL = 4300; // 4.3 sec

    private ArrayBlockingQueue<Student> studentsQueue;
    private static ReentrantLock queueLock;

    public StudentGenerator(ArrayBlockingQueue<Student> studentsQueue) {
        if (studentsQueue == null) {
            throw new IllegalArgumentException("Queue is null!");
        }
        this.studentsQueue = studentsQueue;
    }

    public static void setQueueLock(ReentrantLock queueLock) {
        StudentGenerator.queueLock = queueLock;
    }

    @Override
    public void run() {
        while (true) {
            int labsCount = (int) (Math.random() * 3) + 1;
            int subjectNameNum = (int) (Math.random() * 3) + 1;
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            queueLock.lock();
            try {
                if (studentsQueue.remainingCapacity() != 0) {
                    studentsQueue.add(new Student(labsCount, subjectName));
                    System.out.println("GENERATED " + labsCount + " labs." + " Subject is " + subjectName
                            + "\n" + studentsQueue.size() + " student(s) in the queue.");
                }
            } finally {
                queueLock.unlock();
            }
        }
    }

}
