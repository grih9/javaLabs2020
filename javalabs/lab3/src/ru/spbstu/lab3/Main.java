package ru.spbstu.lab3;

import java.util.concurrent.*;
import java.util.Queue;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        Queue<Student> studentsQueue = new ConcurrentLinkedQueue<Student>();
        StudentGenerator studentGenerator = new StudentGenerator(studentsQueue);
        Robot robotOOP = new Robot("OOP", studentsQueue);
        Robot robotPhysics = new Robot("Physics", studentsQueue);
        Robot robotMath = new Robot("Math", studentsQueue);

        studentGenerator.start();
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robotOOP.start();
        robotMath.start();
        robotPhysics.start();

        while (true) {
        }
    }
}
