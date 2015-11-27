package com.invisiblecollege.robotgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by randy.thedford on 11/27/15.
 */
public class GameActivity extends AppCompatActivity implements CustomView.CustomViewEvents{

    CustomView mGameView;

    int mLoops = 0;

    Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameView = (CustomView)findViewById(R.id.game_view);

        mGameView.setCustomViewListener(this);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(50);

    }

    @Override
    public void onUpdate() {
        mLoops++;
    }

    @Override
    public void onDraw(Canvas c) {
        c.drawColor(Color.BLUE);

        c.drawText(String.valueOf(mLoops), 100, 100, mPaint);
    }
}
