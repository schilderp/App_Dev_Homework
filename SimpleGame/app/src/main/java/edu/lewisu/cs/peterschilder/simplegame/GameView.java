package edu.lewisu.cs.peterschilder.simplegame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Peter Schilder on 4/30/2016.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Paint backgroundPaint;
    private Paint circlePaint;
    private Paint textPaint;

    private Point myCircle;
    private int direction;
    private int radius;
    private GameThread gameThread;
    private int screenWidth;
    private boolean touchCircle = false;
    private GestureDetector gestureDetector;

    private SoundPool soundPool;
    private ArrayList<Integer> soundArray;
    private Activity parent;

    private int score = 0;
    private double elapsedTime = 0.0;


    public GameView(Context context) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.GRAY);
        circlePaint = new Paint();
        circlePaint.setColor(Color.BLUE);
        circlePaint.setAntiAlias(true);
        textPaint = new Paint();
        textPaint.setColor(Color.GREEN);
        textPaint.setAntiAlias(true);

        myCircle = new Point();
        myCircle.x=0;
        myCircle.y=300;
        direction = 1;
        radius = 30;

        DoubleTapListener doubleTapListener = new DoubleTapListener();
        gestureDetector = new GestureDetector(getContext(), doubleTapListener);

        parent = (Activity)context;
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        soundArray = new ArrayList<>();
        soundArray.add(soundPool.load(parent, R.raw.blocker_hit, 1));
        soundArray.add(soundPool.load(parent, R.raw.cannon_fire, 1));
        soundArray.add(soundPool.load(parent, R.raw.target_hit, 1));
    }

    private void drawGameElements(Canvas canvas){
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawCircle(myCircle.x, myCircle.y, radius, circlePaint);
        String scoreString = String.format("Score: %1$d",score);
        canvas.drawText(scoreString, 30, 100, textPaint);
    }

    public void stopGame(){
        if(gameThread != null){
            gameThread.setRunning(false);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        int eventX = (int)event.getX();
        int eventY = (int)event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(inCircle(eventX,eventY)){
                    touchCircle = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(touchCircle){
                    moveCircle(eventX, eventY);
                }
                break;
            case MotionEvent.ACTION_UP:
                touchCircle = false;
                break;
            default:
                return false;


        }
        gestureDetector.onTouchEvent(event);
        return true;
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
            soundPool.play(soundArray.get(0), 1, 1,0,1,1);
            score++;
        }else if(myCircle.x <= 0){
            myCircle.x=0;
            direction = 1;
            soundPool.play(soundArray.get(1),1,1,0,1,1);
            score++;
        }

        if(score >= 5){
            gameThread.setRunning(false);
            showGameOver();
        }

    }

    public void moveCircle(int x, int y){
        myCircle.x = x;
        myCircle.y = y;
    }

    public boolean inCircle(int x, int y){
        double square_dist = Math.pow(myCircle.x-x,2) + Math.pow(myCircle.y - y,2);
        return square_dist < Math.pow(radius,2);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        textPaint.setTextSize(width/20);
        radius = width/30;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(gameThread != null){
            gameThread.setRunning(false);
        }
    }

    private void showGameOver(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Game Over");
        builder.setCancelable(false);
        String message = String.format("Total Time: %1$.1f", elapsedTime);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        soundPool.play(soundArray.get(2), 1, 1, 0, 1, 1);
        parent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                builder.show();
            }
        });
    }

    private class DoubleTapListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            int x = (int)e.getX();
            int y = (int)e.getY();
            moveCircle(x,y);
            return true;
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
                        elapsedTime += elapsedTimeMS;
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
