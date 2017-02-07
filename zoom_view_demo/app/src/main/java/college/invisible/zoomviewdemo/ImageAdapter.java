package college.invisible.zoomviewdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
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

    public ImageAdapter(Context c) {
        mContext = c;
        List<MyView> sublist = new ArrayList<>();
        sublist.add(new MyView(c, R.drawable.k2_so, "K2-SO"));
        sublist.add(new MyView(c, R.drawable.jyn_erso, "Jyn Erso"));
        sublist.add(new MyView(c, R.drawable.cassian_andor, "Cassian Andor"));
        for (MyView view : sublist) {
            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    MyView thumbView = (MyView) arg0;
                    thumbView.zoomFromThumb(mExpandedView);
                }
            });
        }
        for (int i = 0 ; i < 10; i++) {
            mChildViews.addAll(sublist);
        }
        mExpandedView = (MyView) ((Activity)mContext).findViewById(R.id.expanded_my_view);
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