package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class SimpleDrawable extends View {

    private Bitmap bitmap;
    private Paint paint = new Paint();
    private Rect src_rect;
    private Rect dest_rect;

    public SimpleDrawable(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.no_internet);
        src_rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
    }

    public SimpleDrawable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.no_internet);
        src_rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        dest_rect = new Rect(0,0,getWidth(),getHeight());
        canvas.drawBitmap(bitmap, src_rect, dest_rect, paint);
    }


}
