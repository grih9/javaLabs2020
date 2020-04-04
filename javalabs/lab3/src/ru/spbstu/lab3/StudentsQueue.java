package ru.spbstu.lab3;

import java.util.LinkedList;

public class StudentsQueue {
    private static final int capacity = 10;
    private LinkedList<Student> queue;

    public Student getStudent()
    {
        return queue.poll();
    }

    public void addStudent(Student student)
    {
        queue.add(student);
    }
}

