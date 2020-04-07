package ru.spbstu.lab3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class StudentGenerator extends Thread {
    private static final int GENERATING_INTERVAL = 300;

    private BlockingQueue<Student> studentsQueue;
    private static ReentrantLock queueLock;

    public StudentGenerator(BlockingQueue<Student> studentsQueue) {
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
            Subjects subjectName = null;

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
                    subjectName = Subjects.OOP;
                    break;
                }
                case 2: {
                    subjectName = Subjects.Physics;
                    break;
                }
                case 3: {
                    subjectName = Subjects.Math;
                    break;
                }
            }

            try {
                sleep(GENERATING_INTERVAL);
                studentsQueue.put(new Student(labsCount, subjectName));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("GENERATED " + labsCount + " labs." + " Subject is " + subjectName
                        + "\n" + studentsQueue.size() + " student(s) in the queue.");
        }
    }

}
