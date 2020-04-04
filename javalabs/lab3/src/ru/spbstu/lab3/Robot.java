package ru.spbstu.lab3;

import java.util.Objects;
import java.util.Queue;

public class Robot extends Thread {

    private int labsCount;
    private String subjectName;
    private Queue<Student> studentsQueue;
    private static final int checkingTime = 500;  // 0.5 sec

    public Robot(String subject, Queue<Student> queue)
    {
        if (!((subject.equals("OOP")) || (subject.equals("Physics")) || (subject.equals("Math")))) {
            throw new IllegalArgumentException("Illegal subject name");
        }

        subjectName = subject;
        this.studentsQueue = queue;
    }

    public String getSubjectName() {
        return subjectName;
    }

    @Override
    public void run() {
        while (true) {
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
        }
    }
}
