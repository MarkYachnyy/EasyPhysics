package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.ArrayAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views.GradeTopicBGView;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;

public class TopicArrayAdapter extends ArrayAdapter<Topic> {

    public TopicArrayAdapter(@NonNull Context context, int resource, @NonNull Topic[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /*Topic topic = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grade_topic_list_item,null);
        }
        ((GradeTopicBGView)convertView.findViewById(R.id.grade_topic_choice_view_bg)).setProgress(topic.getProgress());
        ((TextView)convertView.findViewById(R.id.grade_topic_choice_view_name)).setText(topic.getName());
        TextView progress_TV = ((TextView)convertView.findViewById(R.id.grade_topic_choice_view_progress));
        progress_TV.setText("Пройдено "+topic.getTest_passed()+" из "+topic.getTest_count()+" тестов");
        View v = new View(getContext());
        if(topic.getTest_passed()==0){
            progress_TV.setTextColor(v.getResources().getColor(R.color.light_gray));
        } else if(topic.getTest_passed()==topic.getTest_count()){
            progress_TV.setTextColor(v.getResources().getColor(R.color.light_green));
        } else {
            progress_TV.setTextColor(v.getResources().getColor(R.color.white));
        }*/
        return convertView;
    }
}
