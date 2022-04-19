package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class FormulaComponentPlaceholderView extends View {

    private final int r = 10;
    private final int border = 20;
    private final Paint paint= new Paint();

    public FormulaComponentPlaceholderView(Context context) {
        super(context);
    }

    public FormulaComponentPlaceholderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(getResources().getColor(R.color.dark_blue));
        canvas.drawRoundRect(0,0,getWidth(),getHeight(),r,r,paint);
        paint.setColor(getResources().getColor(R.color.dark_blue_pastel));
        canvas.drawRoundRect(border,border,getWidth()-border,getHeight()-border,r,r,paint);
    }
}
