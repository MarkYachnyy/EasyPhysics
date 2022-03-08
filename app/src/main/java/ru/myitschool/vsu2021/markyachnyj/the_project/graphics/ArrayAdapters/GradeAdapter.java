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
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;

public class GradeAdapter extends BaseAdapter {

    private ArrayList<Grade> data;
    private LayoutInflater inflater;
    private View view;

    public GradeAdapter(Context context, ArrayList<Grade> data){
        this.data = data;
        inflater = LayoutInflater.from(context);
        view = new View(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        if(!data.isEmpty()){
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
        if(!data.isEmpty()){
            Grade grade = data.get(position);
            if(convertView==null){
                convertView = inflater.inflate(R.layout.grade_topic_list_item, null);
                ((GradeTopicListItemBGView)convertView.findViewById(R.id.grade_topic_list_item_bg)).setProgress(grade.getProgress());
                ((TextView)convertView.findViewById(R.id.grade_topic_list_item_name_tv)).setText(grade.getNumber()+" Класс");
                TextView Progress_TV = convertView.findViewById(R.id.grade_topic_list_item_progress_tv);
                Progress_TV.setText("Пройдено "+grade.getTopic_completed()+" тем из "+grade.getTopic_count());
                if(grade.getTopic_completed()==0){
                    Progress_TV.setTextColor(view.getResources().getColor(R.color.light_gray));
                } else if(grade.getTopic_completed()==grade.getTopic_count()){
                    Progress_TV.setTextColor(view.getResources().getColor(R.color.light_green));
                } else {
                    Progress_TV.setTextColor(view.getResources().getColor(R.color.white));
                }
            }
        }
        return convertView;
    }

    public void setNewData(ArrayList<Grade> new_data){
        data = new_data;
        notifyDataSetChanged();
    }
}
