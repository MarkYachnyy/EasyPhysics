package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class BasicProgressBarView extends View {

    private float progress=0;
    private Paint paint;

    public BasicProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(getResources().getColor(R.color.light_green_pastel));
        canvas.drawColor(getResources().getColor(R.color.red_pastel));
        canvas.drawRect(0,0,getWidth()*progress,getHeight(),paint);
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }
}
