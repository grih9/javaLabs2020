package ru.spbstu.lab3;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Robot extends Thread {

    private int labsCount;
    private String subjectName;
    private Queue<Student> studentsQueue;
    private static final int checkingTime = 500;  // 0.5 sec
    private static ReentrantLock robotLock;

    public Robot(String subject, Queue<Student> queue)
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

    private void checkLabs() {
        while (true) {
            robotLock.lock();
            try {
                if (labsCount <= 0) {
                    if (!studentsQueue.isEmpty()) {
                        if (studentsQueue.peek().getSubjectName().equals(subjectName)) {
                            labsCount = studentsQueue.poll().getLabsCount();
                        }
                    }
                } else {
                    try {
                        sleep(checkingTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    labsCount -= 5;
                    System.out.println("Robot " + subjectName + " checked 5 labs." + labsCount + " remaining.");
                }
            } finally {
                robotLock.unlock();
            }
        }
    }

    public String getSubjectName() {
        return subjectName;
    }

    @Override
    public void run() {
        checkLabs();
    }
}
