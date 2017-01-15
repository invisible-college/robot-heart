package college.invisible.cardframelayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by ppham on 1/14/17.
 */

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.ViewHolder> {

    private String[] mDataset;
    private Context mContext;
    private Random mRandom = new Random();

    public AnimalsAdapter(Context context, String[] dataset) {
        mDataset = dataset;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public LinearLayout mLinearLayout;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv);
            mLinearLayout = (LinearLayout) v.findViewById(R.id.ll);
        }
    }

    @Override
    public AnimalsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        // Create a new View
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset[position]);

        // Generate a random color
        int color = getRandomHSVColor();

        // Set a random color for TextView background
        holder.mTextView.setBackgroundColor(getLighterColor(color));

        // Set a text color for TextView
        holder.mTextView.setTextColor(getReverseColor(color));

        // Set a gradient background for LinearLayout
        holder.mLinearLayout.setBackground(getGradientDrawable());

        // Emboss the TextView text
        applyEmbossMaskFilter(holder.mTextView);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    // Custom method to apply emboss mask filter to TextView
    protected void applyEmbossMaskFilter(TextView tv) {
        EmbossMaskFilter embossFilter = new EmbossMaskFilter(
                new float[]{1f, 5f, 1f}, // direction of the light source
                0.8f, // ambient light between 0 and 1
                8, // specular highlights
                7f // blur before applying lighting
        );
        tv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        tv.getPaint().setMaskFilter(embossFilter);
    }

    // Custom method to generate random HSV color
    protected int getRandomHSVColor() {
        // Generate a random hue value between 0 and 360
        int hue = mRandom.nextInt(361);

        // We make the color depth full
        float saturation = 1.0f;

        // We make a full bright color
        float value = 1.0f;

        // We avoid color transparency
        int alpha = 255;

        // Finally, generate the color
        int color = Color.HSVToColor(alpha, new float[] {hue, saturation, value});

        return color;
    }

    protected GradientDrawable getGradientDrawable() {
        GradientDrawable gradient = new GradientDrawable();
        gradient.setGradientType(GradientDrawable.SWEEP_GRADIENT);
        gradient.setColors(new int[]{ getRandomHSVColor(), getRandomHSVColor(), getRandomHSVColor()});
        return gradient;
    }

    // Custom method to get a darker color
    protected int getDarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] = 0.8f * hsv[2];
        return Color.HSVToColor(hsv);
    }

    // Custom method to get a lighter color
    protected int getLighterColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] = 0.2f + 0.8f * hsv[2];
        return Color.HSVToColor(hsv);
    }

    // Custom method to get reverse color
    protected int getReverseColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[0] = (hsv[0] + 180) % 360;
        return Color.HSVToColor(hsv);
    }
}
