package college.invisible.zoomviewdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * TODO: document your custom view class.
 */
public class MyView extends ImageView {
    private String mExampleString;
    private int mExampleColor = Color.RED;
    private float mExampleDimension;
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;
    private Context mContext;
    public int mImageResId;

    private final static String LOG_TAG = MyView.class.getSimpleName();

    public MyView(Context context, int imageResId, String displayName) {
        super(context);
        this.mContext = context;
        this.mExampleString = displayName;
        this.mImageResId = imageResId;
        this.setExampleDrawable(ContextCompat.getDrawable(mContext, imageResId));
        init(null, 0, context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0, context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle, context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        StringBuilder sb = new StringBuilder();
        sb.append(l + " ");
        sb.append(t + " ");
        sb.append(r + " ");
        sb.append(b + " ");
        Log.i(LOG_TAG, sb.toString());
    }

    private void init(AttributeSet attrs, int defStyle, Context context) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyView, defStyle, 0);

        String styleString = a.getString(R.styleable.MyView_exampleString);
        if (mExampleString == null) {
            mExampleString = styleString;
        }
        mExampleColor = a.getColor(
                R.styleable.MyView_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = getResources().getDimension(R.dimen.myview_default_dimension);
        /*
        a.getDimension(
                R.styleable.MyView_exampleDimension);
                */
        Log.i(LOG_TAG, "Example Dimension " + mExampleDimension);

        if (a.hasValue(R.styleable.MyView_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.MyView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
        setMinimumWidth(200);
        setMinimumHeight(200);

        //setExampleDimension(2.0f*R.dimen.myview_default_dimension);
        // We start out with 300 x 300, try increasing to 600 x 600

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();

    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        //setLayoutParams(new ViewGroup.MarginLayoutParams(600, 600));

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the example drawable on top of the text.
        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
        }

        // Draw the text.
        canvas.drawText(mExampleString,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeight) / 2,
                mTextPaint);

        canvas.restore();

    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec);
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh)
    {
        super.onSizeChanged(w, w, oldw, oldh);
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }

    // A common animator shared by all zoomable image views
    private static Animator mCurrentAnimator;
    private final static int mShortAnimationDuration = 1000;

    // Listener class for the expanded view to shrink back down to thumbnail view.
    private class ExpandedListener implements OnClickListener, OnTouchListener {

        MyView mThumbView;
        private Rect mStartBounds;
        private Rect mFinalBounds;
        private float mStartScale;

        public ExpandedListener(MyView thumbView, Rect startBounds, Rect finalBounds, float startScale) {
            mStartBounds = startBounds;
            mFinalBounds = finalBounds;
            mStartScale = startScale;
            mThumbView = thumbView;
        }

        @Override
        public void onClick(final View expandedView) {
            // If there's an animation in progress, cancel it immediately and
            // proceed with this one.
            if (mCurrentAnimator != null) {
                mCurrentAnimator.cancel();
            }

            AnimatorSet set = new AnimatorSet();
            set.play(
                    ObjectAnimator.ofFloat(expandedView, View.X,
                            mStartBounds.left))
                    .with(ObjectAnimator.ofFloat(expandedView, View.Y,
                            mStartBounds.top))
                    .with(ObjectAnimator.ofFloat(expandedView, View.SCALE_X,
                            mStartScale))
                    .with(ObjectAnimator.ofFloat(expandedView, View.SCALE_Y,
                            mStartScale));
            set.setDuration(mShortAnimationDuration);
            set.setInterpolator(new DecelerateInterpolator());
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    expandedView.setVisibility(View.GONE);
                    mThumbView.setAlpha(1f);
                    mCurrentAnimator = null;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    mCurrentAnimator = null;
                }
            });
            set.start();
            mCurrentAnimator = set;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                onClick(v);
            }
            return false;
        }
    }

    public void zoomFromThumb(final MyView expandedView) {
        // If there's an animation in progress, cancel it immediately and
        // proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.

        expandedView.setExampleDrawable(this.getExampleDrawable());
        expandedView.setExampleString( this.getExampleString() );
        expandedView.setExampleColor( this.getExampleColor() );
        expandedView.setExampleDimension( this.getExampleDimension() );

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        // Rect's are a 4-tuple (top, left, bottom, right) of absolute coordinates.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the
        // final bounds are the global visible rectangle of the container view.
        // Also
        // set the container view's offset as the origin for the bounds, since
        // that's
        // the origin for the positioning animation properties (X, Y).
        this.getGlobalVisibleRect(startBounds);
        ((Activity) mContext).findViewById(R.id.activity_main)
                .getGlobalVisibleRect(finalBounds, globalOffset);

        // These are offest based on activity_main's position, which may be shifted
        // due to toolbar, etc.
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the
        // "center crop" technique. This prevents undesirable stretching during
        // the animation.
        // Also calculate the start scaling factor (the end scaling factor is
        // always 1.0).
        float startScale;

        // Start scale at the smallest dimension of start thumbnail.

        if ((float) finalBounds.width() / finalBounds.height() > (float) startBounds
                .width() / startBounds.height()) {

            // Final aspect ratio is fatter (wider) than start aspect ratio.
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            // Then center horizontally
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Final aspect ratio is narrower (taller) than start aspect ratio
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            // Then center vertically
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins,
        // it will position the zoomed-in view in the place of the thumbnail.
        this.setAlpha(0f); // make original thumbnail invisible
        expandedView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations to the
        // top-left corner of
        // the zoomed-in view (the default is the center of the view).
        //expandedView.setPivotX(0f);
        //expandedView.setPivotY(0f);
        expandedView.setScaleType(ScaleType.CENTER_CROP);

        // Construct and run the parallel animation of the four translation and
        // scale properties
        // (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set.play(
                ObjectAnimator.ofFloat(expandedView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedView, View.SCALE_X,
                        startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedView, View.SCALE_Y,
                        startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
                expandedView.setClickable(true);
                expandedView.setFocusable(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        ExpandedListener listener = new ExpandedListener(this, startBounds, finalBounds, startScale);
        expandedView.setOnClickListener(listener);
        expandedView.setOnTouchListener(listener);
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down to the
        // original bounds
        // and show the thumbnail instead of the expanded image.

    }

}
