package com.invisiblecollege.robotgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by randy.thedford on 11/27/15.
 */
public class GameActivity extends AppCompatActivity implements CustomView.CustomViewEvents, View.OnTouchListener{

    CustomView mGameView;

    int mLoops = 0;
    int mScore = 0;

    Paint mPaint;

    AnimatedSprite mRobot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameView = (CustomView)findViewById(R.id.game_view);

        mGameView.setCustomViewListener(this);

        mGameView.setOnTouchListener(this);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(50);

        Bitmap robotBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.robot1);
        mRobot = new AnimatedSprite(robotBitmap);

    }

    @Override
    public void onUpdate() {
        mLoops++;
        mRobot.update();
    }

    @Override
    public void onDraw(Canvas c) {
        c.drawColor(Color.BLUE);

        mRobot.draw(c);

        c.drawText("Loops: " + String.valueOf(mLoops), 100, 100, mPaint);
        c.drawText("Score: " + String.valueOf(mScore), 100, 200, mPaint);
    }

    private void userTapped(float x, float y){
        //Check to see if robot is pressed
        if (mRobot.wasTapped(x, y)){
            mScore += 10;

            //add hit sound
        }
        else {
            //add miss sound

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();

        switch (action &  MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();

                //send finger data to another method

                userTapped(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
}
