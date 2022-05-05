package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Test;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.tasks.Task;

public class TestResultAdapter extends BaseAdapter {

    private Test test;
    private LayoutInflater inflater;

    public TestResultAdapter(Context context, Test test){
        inflater  = LayoutInflater.from(context);
        this.test = test;
    }

    @Override
    public int getCount() {
        return test.getTasks().size();
    }

    @Override
    public Object getItem(int position) {
        return test.getTasks().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(R.layout.list_item_task_result,null);
        }
        Task task = (Task) getItem(position);
        ((TextView) convertView.findViewById(R.id.list_item_task_result_given_answer_tv)).setText(task.getGivenAnswer());
        TextView Right_Answer_TV = (TextView) convertView.findViewById(R.id.list_item_task_result_right_answer_tv);
        Right_Answer_TV.setFocusable(true);
        Right_Answer_TV.setOnClickListener(v -> ((TextView)v).setText(task.getRightAnswer()));
        ((TextView) convertView.findViewById(R.id.list_item_task_result_title_tv)).setText("Задание "+position);
        boolean is_answer_right = task.CheckAnswer();
        TextView Is_Answer_Right_TV = (TextView) convertView.findViewById(R.id.list_item_task_result_is_answer_right_tv);
        if(is_answer_right){
            Is_Answer_Right_TV.setText("Верно");
            Is_Answer_Right_TV.setTextColor(convertView.getResources().getColor(R.color.light_green_pastel));
        }else{
            Is_Answer_Right_TV.setText("Неверно");
            Is_Answer_Right_TV.setTextColor(convertView.getResources().getColor(R.color.red_pastel));
        }
        return convertView;
    }
}
