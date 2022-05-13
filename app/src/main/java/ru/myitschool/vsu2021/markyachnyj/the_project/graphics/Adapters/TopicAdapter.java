package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Adapters;

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

public class TopicAdapter extends BaseAdapter {

    private ArrayList<Topic> data;
    private LayoutInflater inflater;
    private View view;

    public TopicAdapter(Context context, ArrayList<Topic> data){
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
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Topic topic = data.get(position);
            convertView = inflater.inflate(R.layout.list_item_grade_topic,null);
            ((GradeTopicListItemBGView)convertView.findViewById(R.id.grade_topic_list_item_bg)).setProgress(topic.getProgress());
            ((TextView)convertView.findViewById(R.id.grade_topic_list_item_name_tv)).setText(topic.getName());
            TextView Progress_TV = convertView.findViewById(R.id.grade_topic_list_item_progress_tv);
            Progress_TV.setTextSize(22);
            Progress_TV.setText("Лучший прогресс по тесту: "+(int)(topic.getProgress()*100)+" %");
            if(topic.getProgress()==0){
                Progress_TV.setTextColor(view.getResources().getColor(R.color.light_gray));
            } else if(topic.getProgress()>=0.9f){
                Progress_TV.setTextColor(view.getResources().getColor(R.color.light_green_pastel));
            } else {
                Progress_TV.setTextColor(view.getResources().getColor(R.color.white));
            }
        return convertView;
    }

    public void setNewData(ArrayList<Topic> data) {
        this.data = data;
        notifyDataSetChanged();
        notifyDataSetInvalidated();
    }
}
