package ru.spbstu.lab3;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class StudentGenerator extends Thread {
    private static final int GENERATING_INTERVAL = 300;

    private Queue<Student> studentsQueue;
    private ReentrantLock queueLock;
    private Condition queueCond;

    public StudentGenerator(Queue<Student> studentsQueue) {
        this(studentsQueue, null, null);
    }

    public StudentGenerator(Queue<Student> studentsQueue, ReentrantLock lock, Condition condition) {
        if (studentsQueue == null) {
            throw new IllegalArgumentException("Queue is null!");
        }

        this.studentsQueue = studentsQueue;
        queueLock = lock;
        queueCond = condition;
    }

    private void generateNewStudent() {
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            queueLock.lock();
            try {

                while (studentsQueue.size() == 10) {
                    queueCond.await();
                }

                studentsQueue.add(new Student(labsCount, subjectName));
                System.out.println("GENERATED " + labsCount + " labs." + " Subject is " + subjectName
                        + "\n" + studentsQueue.size() + " student(s) in the queue.");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }
        }
    }


    @Override
    public void run() {
        generateNewStudent();
    }

}
