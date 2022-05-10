package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class FractionDividerView extends View {

    Paint paint;

    public FractionDividerView(Context context) {
        super(context);
        paint = new Paint();
    }

    public FractionDividerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(getResources().getColor(R.color.white));
        int a=5;
        int b=50;
        canvas.drawRect(b,getHeight()/2f-a,getWidth()-b,getHeight()/2f+a,paint);
    }
}
