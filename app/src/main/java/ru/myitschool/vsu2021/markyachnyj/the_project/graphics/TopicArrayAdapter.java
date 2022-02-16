package ru.myitschool.vsu2021.markyachnyj.the_project.graphics;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;

public class TopicArrayAdapter extends ArrayAdapter<Topic> {

    public TopicArrayAdapter(@NonNull Context context, int resource, @NonNull Topic[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
