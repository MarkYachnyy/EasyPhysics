package ru.myitschool.vsu2021.markyachnyj.the_project.graphics.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import ru.myitschool.vsu2021.markyachnyj.the_project.R;

public class AnimatedBGView extends SurfaceView implements SurfaceHolder.Callback {

    private Bitmap bitmap;
    private int Height;
    private int Width;
    private int step_x;
    private int step_y;
    private int offset_x;
    private int offset_y;
    private int step_time_ms;
    private DrawThread drawThread;
    private Rect src_rect;
    private int image_width;
    private int image_height;

    public AnimatedBGView(Context context) {
        super(context);
        getHolder().addCallback(this);
        drawThread = new DrawThread();
        setDefaultParams();
    }

    public AnimatedBGView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        drawThread = new DrawThread();
        setDefaultParams();
    }

    public void setMotionParams(int step_x, int step_y, int step_time_ms){
        this.step_x = step_x;
        this.step_y = step_y;
        this.step_time_ms = step_time_ms;
    }

    public void setDrawable(Bitmap drawable){
        bitmap = drawable;
        src_rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        image_width = Width/2;
        image_height = bitmap.getHeight()*image_width/bitmap.getWidth();
    }

    public void setDefaultParams(){
        offset_x=0;
        offset_y=0;
        step_x=1;
        step_y=1;
        step_time_ms=11;
        setDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.animated_bg));
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        drawThread = new DrawThread();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        /*Height = height;
        Width = width;
        if(height!=0 && width!=0){
            image_width = Width/2;
            image_height = bitmap.getHeight()*image_width/bitmap.getWidth();
            drawThread.ready=true;
        } else {
            drawThread.request_stop();
            drawThread.ready = false;
        }*/
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Height = h;
        Width = w;
        if(h!=0 && w!=0){
            image_width = Width/2;
            image_height = bitmap.getHeight()*image_width/bitmap.getWidth();
            drawThread.ready=true;
        } else {
            drawThread.request_stop();
            drawThread.ready = false;
        }
    }

    public void activate(){
        if(drawThread!=null){
            if(drawThread.ready && !drawThread.running){
                drawThread.running=true;
                drawThread.start();
            }else{
                Thread t = new Thread(){
                    @Override
                    public void run(){
                        while (!drawThread.ready){

                        }
                        drawThread.running=true;
                        drawThread.start();
                    }
                };
                t.start();
            }
        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if(drawThread!=null){
            drawThread.interrupt();
        }
    }
    private class DrawThread extends Thread{
        private boolean running = false;
        private boolean ready = false;
        @Override
        public void run() {
            while (running){
                Canvas canvas = getHolder().lockCanvas();
                if(canvas!=null){
                    try{
                        for(int i=-offset_x;i<Width;i+=image_width){
                            for(int j=-offset_y;j<Height;j+=image_height){
                                canvas.drawBitmap(bitmap,src_rect,new Rect(i,j,image_width+i,image_height+j),new Paint());
                            }

                        }

                    }finally {
                        getHolder().unlockCanvasAndPost(canvas);
                    }
                }
                move();
                try{
                    Thread.sleep(step_time_ms);
                } catch(InterruptedException e) {

                }
            }
        }
        public void request_stop(){
            running = false;
        }
    }
    private void move(){
        offset_x+=step_x;
        offset_y+=step_y;
        offset_x%=image_width;
        offset_y%=image_height;
    }
}