package ru.spbstu.lab3;

import java.util.concurrent.*;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Queue<Student> studentsQueue = new ConcurrentLinkedQueue<Student>();
        StudentGenerator studentGenerator = new StudentGenerator(studentsQueue);

        Robot robotOOP = new Robot("OOP", studentsQueue);
        Robot robotPhysics = new Robot("Physics", studentsQueue);
        Robot robotMath = new Robot("Math", studentsQueue);

        robotOOP.setName("Robot OOP");
        robotPhysics.setName("Robot Physics");
        robotMath.setName("Robot Math");

        ReentrantLock myLock = new ReentrantLock();
        Robot.setRobotLock(myLock);

        studentGenerator.start();

        robotOOP.start();
        robotMath.start();
        robotPhysics.start();

    }
}
