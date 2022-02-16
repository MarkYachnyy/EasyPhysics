package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.ArrayAdapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;

public class GradeArrayAdapter extends ArrayAdapter<Grade> {
    public GradeArrayAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
