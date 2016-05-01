package edu.lewisu.cs.peterschilder.simplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Peter Schilder on 4/30/2016.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Paint backgroundPaint;
    private Paint circlePaint;
    private Point myCircle;
    private int direction;
    private int radius;
    private GameThread gameThread;
    private int screenWidth;


    public GameView(Context context) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.GRAY);
        circlePaint = new Paint();
        circlePaint.setColor(Color.BLUE);
        circlePaint.setAntiAlias(true);

        myCircle = new Point();
        myCircle.x=0;
        myCircle.y=300;
        direction = 1;
        radius = 30;
    }

    private void drawGameElements(Canvas canvas){
        canvas.drawRect(0,0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawCircle(myCircle.x, myCircle.y, radius, circlePaint);
    }

    public void stopGame(){
        if(gameThread != null){
            gameThread.setRunning(false);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread = new GameThread(holder);
        gameThread.setRunning(true);
        gameThread.start();
    }

    private void updatePositions(double elapsedTime){
        myCircle.x += elapsedTime/5*direction;

        if (myCircle.x > screenWidth){
            myCircle.x = screenWidth;
            direction = -1;
        }else if(myCircle.x <= 0){
            myCircle.x=0;
            direction = 1;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(gameThread != null){
            gameThread.setRunning(false);
        }
    }

    private class GameThread extends Thread{
        private final SurfaceHolder surfaceHolder;
        private boolean threadRunning = true;

        public GameThread(SurfaceHolder holder){
            surfaceHolder = holder;
            setName("GameThread");
        }

        public void setRunning(boolean state){
            threadRunning = state;
        }

        @Override
        public void run() {
            Canvas canvas = null;
            long previousFrameTime = System.currentTimeMillis();
            long currentTime;
            double elapsedTimeMS;


            while (threadRunning){
                try{
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder){
                        currentTime = System.currentTimeMillis();
                        elapsedTimeMS = currentTime - previousFrameTime;
                        previousFrameTime = currentTime;
                        updatePositions(elapsedTimeMS);
                        drawGameElements(canvas);
                    }
                }finally {
                    if(canvas != null ){
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
}
