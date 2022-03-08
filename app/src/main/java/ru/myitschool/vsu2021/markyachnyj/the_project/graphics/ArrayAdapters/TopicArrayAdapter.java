package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.ArrayAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views.GradeTopicListItemBGView;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;

public class TopicArrayAdapter extends BaseAdapter {

    private ArrayList<Topic> data;
    private LayoutInflater inflater;
    private View view;

    public TopicArrayAdapter(Context context, ArrayList<Topic> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        view = new View(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        if(!data.isEmpty()) {
            return data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Topic topic = data.get(position);
        if(convertView==null){
            convertView = inflater.inflate(R.layout.grade_topic_list_item,null);
            ((GradeTopicListItemBGView)convertView.findViewById(R.id.grade_topic_list_item_bg)).setProgress(topic.getProgress());
            ((TextView)convertView.findViewById(R.id.grade_topic_list_item_name_tv)).setText(topic.getName());
            TextView Progress_TV = convertView.findViewById(R.id.grade_topic_list_item_progress_tv);
            Progress_TV.setText("Пройдено "+topic.getTest_passed()+" тем из "+topic.getTest_count());
            if(topic.getTest_passed()==0){
                Progress_TV.setTextColor(view.getResources().getColor(R.color.light_gray));
            } else if(topic.getTest_passed()==topic.getTest_count()){
                Progress_TV.setTextColor(view.getResources().getColor(R.color.light_green));
            } else {
                Progress_TV.setTextColor(view.getResources().getColor(R.color.white));
            }
        }
        return convertView;
    }
}
