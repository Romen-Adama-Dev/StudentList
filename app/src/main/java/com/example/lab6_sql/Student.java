package com.example.lab6_sql;

import java.util.ArrayList;
import java.util.Date;

public class Student {
    public static ArrayList<Student> studentArrayList = new ArrayList<>();
    public static String STUDENT_EDIT_EXTRA =  "studentEdit";

    private int id;
    private String name;
    private String description;
    private Date deleted;

    public Student(int id, String name, String description, Date deleted)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deleted = deleted;
    }

    public Student(int id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        deleted = null;
    }

    public static Student getStudentForID(int passedStudentID)
    {
        for (Student student : studentArrayList)
        {
            if(student.getId() == passedStudentID)
                return student;
        }

        return null;
    }

    public static ArrayList<Student> nonDeletedStudents()
    {
        ArrayList<Student> nonDeleted = new ArrayList<>();
        for(Student student : studentArrayList)
        {
            if(student.getDeleted() == null)
                nonDeleted.add(student);
        }

        return nonDeleted;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Date deleted)
    {
        this.deleted = deleted;
    }

}
