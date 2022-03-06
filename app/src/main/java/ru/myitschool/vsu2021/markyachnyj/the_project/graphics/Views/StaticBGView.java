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

public class StaticBGView extends View {

    private Bitmap bitmap;
    private int Height;
    private int Width;
    private Rect src_rect;
    private int image_width;
    private int image_height;
    private Paint paint;

    public StaticBGView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        setDefaultParams();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<Width;i+=image_width){
            for(int j=0;j<Height;j+=image_height){
                canvas.drawBitmap(bitmap,src_rect,new Rect(i,j,image_width+i,image_height+j),paint);
            }
        }
    }

    private void setDefaultParams(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.animated_bg);
        src_rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
    }

    public void setDrawable(Bitmap drawable){
        bitmap = drawable;
        src_rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        image_width = Width/2;
        image_height = bitmap.getHeight()*image_width/bitmap.getWidth();
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Height = h;
        Width = w;
        image_width = Width/2;
        image_height = bitmap.getHeight()*image_width/bitmap.getWidth();
        invalidate();
    }
}
