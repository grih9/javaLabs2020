package ru.spbstu.lab3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Robot extends Thread {

    private int labsCount;
    private Subjects subject;
    private BlockingQueue<Student> studentsQueue;
    private ReentrantLock queueLock;
    private static final int checkingTime = 100;

    public Robot(Subjects subject, BlockingQueue<Student> queue)
    {
        if ((subject != Subjects.OOP) && (subject != Subjects.Physics) && (subject != Subjects.Math)) {
            throw new IllegalArgumentException("Illegal subject name");
        }

        this.subject = subject;
        studentsQueue = queue;
        queueLock = null;
    }

    public Robot(Subjects subject, BlockingQueue<Student> queue, ReentrantLock queueLock)
    {
        if ((subject != Subjects.OOP) && (subject != Subjects.Physics) && (subject != Subjects.Math)) {
            throw new IllegalArgumentException("Illegal subject name");
        }

        this.subject = subject;
        studentsQueue = queue;
        this.queueLock = queueLock;
    }

    private void checkLabs() throws InterruptedException{
        while (true) {
            if (labsCount <= 0) {
                queueLock.lock();
                try {
                    if (!studentsQueue.isEmpty() && studentsQueue.peek().getSubject() == subject) {
                        labsCount = studentsQueue.take().getLabsCount();
                        System.out.println("Robot " + subject + " STARTED checking labs from a student.\n"
                                + studentsQueue.size() + " student(s) in the queue.");
                    }
                } finally {
                    queueLock.unlock();
                }
            } else {
                sleep(checkingTime);
                labsCount -= 5;
                if (labsCount == 0) {
                    System.out.println("Robot " + subject + " FINISHED checking labs from a student");
                }
            }
        }
    }

    public Subjects getSubject() {
        return subject;
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
