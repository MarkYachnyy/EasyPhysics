package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class TaskResultListItemBGView extends View {

    final private Paint paint;
    final private int r = 50;
    final private int border = 10;

    public TaskResultListItemBGView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.dark_blue_pastel));
        canvas.drawRoundRect(0,0,getWidth(),getHeight(),r,r,paint);
        paint.setColor(getResources().getColor(R.color.light_blue));
        canvas.drawRoundRect(border,border,getWidth()-border,getHeight()-border,r,r,paint);
        paint.setColor(getResources().getColor(R.color.dark_blue_pastel));
        int h = getHeight()/3;
        canvas.drawRect(0,h-border/2f,getWidth(),h+border/2f,paint);
        canvas.drawRect(getWidth()/2f-border/2f,0,getWidth()/2f+border/2f,getHeight(),paint);
    }

}
