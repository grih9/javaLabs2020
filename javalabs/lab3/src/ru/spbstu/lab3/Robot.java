package ru.spbstu.lab3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Robot extends Thread {

    private int labsCount;
    private String subjectName;
    private ArrayBlockingQueue<Student> studentsQueue;
    private static final int checkingTime = 1000;  // 1 sec
    private static ReentrantLock robotLock;

    public Robot(String subject, ArrayBlockingQueue<Student> queue)
    {
        if (!((subject.equals("OOP")) || (subject.equals("Physics")) || (subject.equals("Math")))) {
            throw new IllegalArgumentException("Illegal subject name");
        }

        subjectName = subject;
        this.studentsQueue = queue;
    }

    public static void setRobotLock(ReentrantLock robotLock) {
        Robot.robotLock = robotLock;
    }

    private void checkLabs() throws InterruptedException{
        while (true) {
            if (labsCount <= 0) {
                robotLock.lock();
                try {
                    if (studentsQueue.peek() != null && studentsQueue.peek().getSubjectName().equals(subjectName)) {
                        labsCount = studentsQueue.take().getLabsCount();
                        sleep(100);
                        System.out.println("Robot " + subjectName + " STARTED checking labs from a student.\n"
                                + studentsQueue.size() + " student(s) in the queue.");
                    }
                } finally {
                    robotLock.unlock();
                }
            } else {
                sleep(checkingTime);
                labsCount -= 5;
                if (labsCount == 0) {
                    System.out.println("Robot " + subjectName + " FINISHED checking labs from a student");
                    sleep(100);
                }
            }
        }
    }

    public String getSubjectName() {
        return subjectName;
    }

    @Override
    public void run() {
        try {
            checkLabs();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
