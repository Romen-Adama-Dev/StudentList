package com.example.lab6_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView studentListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setStudentAdapter();
        setOnClickListener();
    }


    private void initWidgets()
    {
        studentListView = findViewById(R.id.studentListView);
    }

    private void loadFromDBToMemory()
    {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateStudentListArray();
    }

    private void setStudentAdapter()
    {
        StudentsAdapter studentAdapter = new StudentsAdapter(getApplicationContext(), Student.nonDeletedStudents());
        studentListView.setAdapter(studentAdapter);
    }


    private void setOnClickListener()
    {
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Student selectedStudent = (Student) studentListView.getItemAtPosition(position);
                Intent editStudentIntent = new Intent(getApplicationContext(), StudentsDetailActivity.class);
                editStudentIntent.putExtra(Student.STUDENT_EDIT_EXTRA, selectedStudent.getId());
                startActivity(editStudentIntent);
            }
        });
    }


    public void newStudent(View view)
    {
        Intent newStudentIntent = new Intent(this, StudentsDetailActivity.class);
        startActivity(newStudentIntent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setStudentAdapter();
    }
}