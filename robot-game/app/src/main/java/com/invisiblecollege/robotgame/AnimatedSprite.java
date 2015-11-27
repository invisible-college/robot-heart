package com.invisiblecollege.robotgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by randy.thedford on 11/27/15.
 */
public class AnimatedSprite {

    RectF mRect = new RectF();

    float mX;
    float mY;

    Bitmap mBitmap;

    public AnimatedSprite(Bitmap bitmap){
        mBitmap = bitmap;
    }

    public void update(){
        mX++;
        mY++;

        mRect.set(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        mRect.offsetTo(mX, mY);

    }

    public void draw(Canvas c){
        c.drawBitmap(mBitmap, mX, mY, null);
    }

    public boolean wasTapped(float x, float y){
        return mRect.contains(x, y);
    }
}
