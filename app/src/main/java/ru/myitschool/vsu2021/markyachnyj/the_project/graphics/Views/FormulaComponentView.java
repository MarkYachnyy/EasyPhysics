package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class FormulaComponentView extends View {

    private final int r = 10;
    private final int border = 20;
    private final Paint paint;
    private float text_size;

    private boolean is_chosen = false;
    private final String text;

    public FormulaComponentView(Context context, String text) {
        super(context);
        paint = new Paint();
        this.text = text;
    }

    public void set_Chosen(boolean is_chosen){
        this.is_chosen = is_chosen;
        invalidate();
    }

    public String getText() {
        return text;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(is_chosen){
            paint.setColor(getResources().getColor(R.color.white));
        } else {
            paint.setColor(getResources().getColor(R.color.dark_blue));
        }
        canvas.drawRoundRect(0,0,getWidth(),getHeight(),r,r,paint);
        paint.setColor(getResources().getColor(R.color.dark_blue_pastel));
        canvas.drawRoundRect(border,border,getWidth()-border,getHeight()-border,r,r,paint);
        text_size = getHeight()/2f;
        paint.setTextSize(text_size);
        paint.setColor(getResources().getColor(R.color.white));
        float text_width = paint.measureText(text);
        canvas.drawText(text, getWidth()/2f-text_width/2f,getHeight()/2f+text_size/2f,paint);
    }
}
