package com.invisiblecollege.robotgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by randy.thedford on 11/27/15.
 */
public class AnimatedSprite {

    private RectF mRect = new RectF();

    private float mX;
    private  float mY;

    private float mMovementSpeed = 150;

    private float mMovementXPerSecond = mMovementSpeed;
    private float mMovementYPerSecond = mMovementSpeed;


    private ArrayList<Bitmap> mBitmaps;

    private int mCurrentFrame = 0;
    private long mTimeBetweenFrames = 150;

    private long mTimer = 0;

    private Random mRandom;

    public int mHP = 1;

    public AnimatedSprite(){
        mBitmaps = new ArrayList<>();
    }

    public AnimatedSprite(ArrayList<Bitmap> bitmaps){
        mBitmaps = bitmaps;
    }

    public RectF getRect(){
        return mRect;
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
        if (isDead()){
            return;
        }
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
        if (isDead()){
            return;
        }
        c.drawBitmap(mBitmaps.get(mCurrentFrame), mX, mY, null);
    }

    public boolean wasTapped(float x, float y){
        if (mRect.contains(x, y)){
            mHP--;
            return  true;
        }
        else {
            return false;
        }
    }

    public boolean isDead(){
        if (mHP <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void setAutoMoveX(float value){
        mMovementXPerSecond = value;
    }

    public void setAutoMoveY(float value){
        mMovementYPerSecond = value;
    }

    public void randomized(RectF screenRect){
        if (mRandom == null){
            mRandom = new Random();
        }

        mHP = mRandom.nextInt(3) + 1;

        int choice = mRandom.nextInt(4);

        switch (choice){
            case 0:
                //set the robot to the left side
                mX = -mRect.width();
                mY = mRandom.nextInt( (int)(screenRect.height() - mRect.height()) );

                //set movement to the right
                setAutoMoveX(mMovementSpeed);
                setAutoMoveY(0);
                break;
            case 1:
                //set the robot to the right side
                mX = screenRect.width();
                mY = mRandom.nextInt( (int)(screenRect.height() - mRect.height()) );

                //set movement to the left
                setAutoMoveX(-mMovementSpeed);
                setAutoMoveY(0);

                break;
            case 2:
                //set the robot to the top side
                mX = mRandom.nextInt( (int)(screenRect.width() - mRect.width()) );
                mY = -mRect.height();

                //set movement to the bottom
                setAutoMoveX(0);
                setAutoMoveY(mMovementSpeed);
                break;
            case 3:
                //set the robot to the bottom side
                mX = mRandom.nextInt( (int)(screenRect.width() - mRect.width()) );
                mY = screenRect.height();

                //set movement to the top
                setAutoMoveX(0);
                setAutoMoveY(-mMovementSpeed);
                break;
        }
    }
}
