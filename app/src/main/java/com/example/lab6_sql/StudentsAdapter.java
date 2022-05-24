package com.example.lab6_sql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StudentsAdapter extends ArrayAdapter<Student> {
    public StudentsAdapter(Context context, List<Student> students)
    {
        super(context, 0, students);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Student student = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_cell, parent, false);

        TextView name = convertView.findViewById(R.id.cellName);
        TextView desc = convertView.findViewById(R.id.cellDesc);

        name.setText(student.getName());
        desc.setText(student.getDescription());

        return convertView;
    }
}
