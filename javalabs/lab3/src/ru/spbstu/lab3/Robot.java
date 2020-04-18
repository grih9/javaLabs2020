package ru.spbstu.lab3;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Robot extends Thread {

    private int labsCount;
    private Subjects subject;
    private Queue<Student> studentsQueue;
    private ReentrantLock queueLock;
    private Condition queueCond;
    private static final int checkingTime = 100;

    public Robot(Subjects subject, Queue<Student> queue)
    {
        this(subject, queue, null, null);
    }

    public Robot(Subjects subject, Queue<Student> queue, ReentrantLock lock, Condition condition)
    {
        if ((subject != Subjects.OOP) && (subject != Subjects.Physics) && (subject != Subjects.Math)) {
            throw new IllegalArgumentException("Illegal subject name");
        }

        this.subject = subject;
        studentsQueue = queue;
        queueLock = lock;
        queueCond = condition;
    }

    private void checkLabs() {
        while (true) {
            if (labsCount <= 0) {

                queueLock.lock();
                try {

                    if (!studentsQueue.isEmpty() && studentsQueue.peek().getSubject() == subject) {
                        labsCount = studentsQueue.remove().getLabsCount();
                        System.out.println("Robot " + subject + " STARTED checking labs from a student.\n"
                                + studentsQueue.size() + " student(s) in the queue.");
                        queueCond.signalAll();
                    }

                } finally {
                    queueLock.unlock();
                }

            } else {

                try {
                    sleep(checkingTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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
        checkLabs();
    }
}
