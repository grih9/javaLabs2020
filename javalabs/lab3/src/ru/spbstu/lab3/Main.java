package ru.spbstu.lab3;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final int QUEUE_CAPACITY = 10;

    public static void main(String[] args) {
        BlockingQueue<Student> studentsQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

        ReentrantLock myLock = new ReentrantLock();
        Condition isFull = myLock.newCondition();

        StudentGenerator studentGenerator = new StudentGenerator(studentsQueue, myLock, isFull);

        Robot robotOOP = new Robot(Subjects.OOP, studentsQueue, myLock, isFull);
        Robot robotPhysics = new Robot(Subjects.Physics, studentsQueue, myLock, isFull);
        Robot robotMath = new Robot(Subjects.Math, studentsQueue, myLock, isFull);

        robotOOP.setName("Robot OOP");
        robotPhysics.setName("Robot Physics");
        robotMath.setName("Robot Math");

        robotOOP.start();
        robotMath.start();
        robotPhysics.start();

        studentGenerator.start();

    }
}
