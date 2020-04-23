package ru.spbstu.lab3;

public class Student
{
    private int labsCount;
    private Subjects subject;

    public Student(int labsCount, Subjects subject)
    {
        if ((labsCount != 10) && (labsCount != 20) && (labsCount  != 100)) {
            throw new IllegalArgumentException("Illegal labsCount number - " + labsCount);
        }

        if ((subject != Subjects.OOP) && (subject != Subjects.Physics) && (subject != Subjects.Math)) {
            throw new IllegalArgumentException("Illegal subject name");
        }

        this.labsCount = labsCount;
        this.subject = subject;
    }

    public int getLabsCount() {
        return labsCount;
    }

    public Subjects getSubject() {
        return subject;
    }
}
