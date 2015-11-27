package com.invisiblecollege.robotgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by randy.thedford on 11/27/15.
 */
public class CustomView extends View{


    CustomViewEvents mCallback;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

    }

    public void setCustomViewListener(CustomViewEvents listener){
        mCallback = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mCallback.onUpdate();
        mCallback.onDraw(canvas);

        this.postInvalidate();

    }

    interface CustomViewEvents {
        void onUpdate();
        void onDraw(Canvas c);
    }
}
