package ru.spbstu.lab3;

public class Student
{
    private int labsCount;
    private String subjectName;

    public Student(int labs, String subject)
    {
        if (!((labs == 10) || (labs == 20) || (labs  == 100))) {
            throw new IllegalArgumentException("Illegal labsCount number" + labs);
        }

        if (!((subject.equals("OOP")) || (subject.equals("Physics")) || (subject.equals("Math")))) {
            throw new IllegalArgumentException("Illegal subject name");
        }

        labsCount = labs;
        subjectName = subject;
    }

    public int getLabsCount() {
        return labsCount;
    }

    public String getSubjectName() {
        return subjectName;
    }
}
