package com.invisiblecollege.robotgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

/**
 * Created by randy.thedford on 11/27/15.
 */
public class AnimatedSprite {

    RectF mRect = new RectF();

    float mX;
    float mY;

    float mMovementXPerSecond = 150;
    float mMovementYPerSecond = 150;

    ArrayList<Bitmap> mBitmaps;

    int mCurrentFrame = 0;
    long mTimeBetweenFrames = 150;

    long mTimer = 0;


    public AnimatedSprite(){
        mBitmaps = new ArrayList<>();
    }

    public AnimatedSprite(ArrayList<Bitmap> bitmaps){
        mBitmaps = bitmaps;
    }

    public void setCurrentFrame(int frame){
        mCurrentFrame = frame;

        //Just in case  Bounds Check
        if (mCurrentFrame >= mBitmaps.size() || mCurrentFrame < 0){
            setCurrentFrame(0);
        }
    }

    public void nextFrame(){
        mCurrentFrame++;
        if (mCurrentFrame >= mBitmaps.size()){
            setCurrentFrame(0);
        }
    }

    public void update(long elapsedTime){
        if (elapsedTime > 100){
            elapsedTime = 100;
        }
        mTimer += elapsedTime;

        if (mTimer >= mTimeBetweenFrames){
            mTimer -= mTimeBetweenFrames;
            nextFrame();
        }


        //TODO Change movement to time based
        mX += (elapsedTime/1000f) * mMovementXPerSecond;
        mY += (elapsedTime/1000f) * mMovementYPerSecond;

        Bitmap bitmap = mBitmaps.get(mCurrentFrame);

        mRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        mRect.offsetTo(mX, mY);

    }

    public void draw(Canvas c){
        c.drawBitmap(mBitmaps.get(mCurrentFrame), mX, mY, null);
    }

    public boolean wasTapped(float x, float y){
        return mRect.contains(x, y);
    }
}
