package college.invisible.zoomviewdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Based on example at: http://www.androidbegin.com/tutorial/android-gridview-zoom-images-animation-tutorial/
 * Created by ppham on 2/6/17.
 */

public class ImageAdapter extends BaseAdapter {

    private List<MyView> mChildViews = new ArrayList<>();

    private Context mContext;
    private ImageView expandedImageView;
    private MyView mExpandedView;
    LayoutInflater inflater;

    private static class Listener implements OnClickListener, View.OnTouchListener {

        private MyView mExpandedView;

        public Listener(MyView expandedView) {
            this.mExpandedView = expandedView;
        }

        @Override
        public void onClick(View arg0) {
            MyView thumbView = (MyView) arg0;
            thumbView.zoomFromThumb(mExpandedView);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // Only register a click if we get an UP event, after a DOWN event.
                onClick(v);
            }
            return true;
        }

    }

    public ImageAdapter(Context c) {
        mContext = c;
        mExpandedView = (MyView) ((Activity)mContext).findViewById(R.id.expanded_my_view);
        mExpandedView.setClickable(true);
        mExpandedView.setFocusable(true);
        Listener listener = new Listener(mExpandedView);
        for (int i = 0 ; i < 10; i++) {
            List<MyView> sublist = new ArrayList<>();
            sublist.add(new MyView(c, R.drawable.k2_so, "K2-SO"));
            sublist.add(new MyView(c, R.drawable.jyn_erso, "Jyn Erso"));
            sublist.add(new MyView(c, R.drawable.cassian_andor, "Cassian Andor"));
            for (MyView view : sublist) {
                view.setOnClickListener(listener);
                view.setOnTouchListener(listener);
                view.setFocusable(true);
                view.setClickable(true);
            }
            mChildViews.addAll(sublist);
        }
    }

    public int getCount() {
        return mChildViews.size();
    }

    public Object getItem(int position) {
        return mChildViews.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final MyView myView;
        if (convertView == null) {
            myView = mChildViews.get(position); //new MyView(mContext, 0, "");
            //myView = new ImageView(mContext);
            //myView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            myView = (MyView) convertView;
        }

        int imageId = mChildViews.get(position).mImageResId;
        //myView.setImage(imageId);
        //myView.setImageResource(imageId);

        return myView;
    }

}