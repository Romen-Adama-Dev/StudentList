package com.example.lab6_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class StudentsDetailActivity extends AppCompatActivity {

    private EditText nameEditText, descEditText;
    private Button deleteButton;
    private Student selectedStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_detail);
        initWidgets();
        checkForEditStudent();
    }

    private void initWidgets()
    {
        nameEditText = findViewById(R.id.nameEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteStudentButton);
    }

    private void checkForEditStudent()
    {
        Intent previousIntent = getIntent();

        int passedStudentID = previousIntent.getIntExtra(Student.STUDENT_EDIT_EXTRA, -1);
        selectedStudent = Student.getStudentForID(passedStudentID);

        if (selectedStudent != null)
        {
            nameEditText.setText(selectedStudent.getName());
            descEditText.setText(selectedStudent.getDescription());
        }
        else
        {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveStudent(View view)
    {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String name = String.valueOf(nameEditText.getText());
        String desc = String.valueOf(descEditText.getText());

        if(selectedStudent == null)
        {
            int id = Student.studentArrayList.size();
            Student newStudent = new Student(id, name, desc);
            Student.studentArrayList.add(newStudent);
            sqLiteManager.addStudentToDatabase(newStudent);
        }
        else
        {
            selectedStudent.setName(name);
            selectedStudent.setDescription(desc);
            sqLiteManager.updateStudentInDB(selectedStudent);
        }

        finish();
    }

    public void deleteStudent(View view)
    {
        selectedStudent.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateStudentInDB(selectedStudent);
        finish();
    }
}