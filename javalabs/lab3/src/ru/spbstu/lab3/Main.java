package ru.spbstu.lab3;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final int QUEUE_CAPACITY = 10;

    public static void main(String[] args) {
        BlockingQueue<Student> studentsQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
        StudentGenerator studentGenerator = new StudentGenerator(studentsQueue);

        ReentrantLock myLock = new ReentrantLock();

        Robot robotOOP = new Robot(Subjects.OOP, studentsQueue, myLock);
        Robot robotPhysics = new Robot(Subjects.Physics, studentsQueue, myLock);
        Robot robotMath = new Robot(Subjects.Math, studentsQueue, myLock);

        robotOOP.setName("Robot OOP");
        robotPhysics.setName("Robot Physics");
        robotMath.setName("Robot Math");

        studentGenerator.start();

        robotOOP.start();
        robotMath.start();
        robotPhysics.start();

    }
}
