package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class GradeTopicProgressInfoFragmentBGView extends View {

    final private int r = 50;
    final private int border = 10;

    private Paint paint;

    public GradeTopicProgressInfoFragmentBGView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(getResources().getColor(R.color.dark_blue_pastel));
        canvas.drawRoundRect(0,0,getWidth(),getHeight()+r,r,r,paint);
        paint.setColor(getResources().getColor(R.color.light_blue));
        canvas.drawRoundRect(border,border,getWidth()-border,getHeight()+r,r,r,paint);
    }
}
