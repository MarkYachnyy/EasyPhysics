package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RoundProgressBar extends View {

    private Paint paint;
    private float progress = 0f;

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setSubpixelText(true);
        paint.setTextSize(getWidth()/3f);
    }

    public void setProgress(float new_progress){
        progress = new_progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        canvas.drawCircle(getWidth()/2f, getHeight(), getWidth()/2f,paint);
        paint.setColor(Color.GREEN);
        canvas.drawArc(0,getHeight()-getWidth()/2f,getWidth(),getHeight()+getWidth()/2f,180,180*progress,true,paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(getWidth()/2f, getHeight(), getWidth()/2.5f,paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(getWidth()/5f);
        String text = (int)(progress*100)+"%";
        float text_width = paint.measureText(text);
        canvas.drawText(text,(getWidth()-text_width)/2f,getHeight()-30,paint);
    }

}
