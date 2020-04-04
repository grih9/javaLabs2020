package ru.spbstu.lab3;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final int QUEUE_CAPACITY = 10;

    public static void main(String[] args) {
        ArrayBlockingQueue<Student> studentsQueue = new ArrayBlockingQueue<Student>(QUEUE_CAPACITY);
        StudentGenerator studentGenerator = new StudentGenerator(studentsQueue);

        Robot robotOOP = new Robot("OOP", studentsQueue);
        Robot robotPhysics = new Robot("Physics", studentsQueue);
        Robot robotMath = new Robot("Math", studentsQueue);

        robotOOP.setName("Robot OOP");
        robotPhysics.setName("Robot Physics");
        robotMath.setName("Robot Math");

        ReentrantLock myLock = new ReentrantLock();
        Robot.setQueueLock(myLock);
        StudentGenerator.setQueueLock(myLock);

        studentGenerator.start();

        robotOOP.start();
        robotMath.start();
        robotPhysics.start();

    }
}
