package ru.spbstu.lab3;

public class Student
{
    private int labsCount;
    private Subjects subject;

    public Student(int labs, Subjects subject)
    {
        if (!((labs == 10) || (labs == 20) || (labs  == 100))) {
            throw new IllegalArgumentException("Illegal labsCount number" + labs);
        }

        if ((subject != Subjects.OOP) && (subject != Subjects.Physics) && (subject != Subjects.Math)) {
            throw new IllegalArgumentException("Illegal subject name");
        }

        labsCount = labs;
        this.subject = subject;
    }

    public int getLabsCount() {
        return labsCount;
    }

    public Subjects getSubject() {
        return subject;
    }
}
