package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class ClassTopicBGView extends View {

    final private Paint paint;
    final private int r = 50;
    final private int border = 10;
    private int progress=0;
    private boolean is_chosen = false;

    public ClassTopicBGView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(        );
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(is_chosen){
            paint.setColor(getResources().getColor(R.color.white));
        } else {
            paint.setColor(getResources().getColor(R.color.dark_blue));
        }
        canvas.drawRoundRect(0,0,getWidth(),getHeight(),r,r,paint);
        paint.setColor(getResources().getColor(R.color.light_blue));
        canvas.drawRoundRect(border,border,getWidth()-border,getHeight()-border,r,r,paint);
        paint.setColor(getResources().getColor(R.color.red));
        canvas.drawRect(border,getHeight()/2f-10,getWidth()-border,getHeight()/2f+10,paint);
        paint.setColor(getResources().getColor(R.color.light_green));
        canvas.drawRect(border,getHeight()/2f-10,border+(getWidth()-2*border)*progress/100f,getHeight()/2f+10,paint);
    }

    public void setProgress(int new_progress){
        progress=new_progress;
        invalidate();
    }

    public void setChosen(boolean new_chosen){
        is_chosen = new_chosen;
        invalidate();
    }
}

