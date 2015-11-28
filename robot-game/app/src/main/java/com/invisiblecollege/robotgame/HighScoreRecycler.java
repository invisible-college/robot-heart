package com.invisiblecollege.robotgame;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by randy.thedford on 11/27/15.
 */
public class HighScoreRecycler extends RecyclerView.Adapter<HighScoreRecycler.ViewHolder>{

    public List<Highscore> mHighscores;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView mHighscore;
        TextView mDateView;

        public ViewHolder(View v) {
            super(v);
            mHighscore = (TextView)v.findViewById(R.id.highscore);
            mDateView = (TextView)v.findViewById(R.id.date);
        }
    }


    public HighScoreRecycler(List<Highscore> listOfHighscores){
        mHighscores = listOfHighscores;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_highscore, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Highscore hs = mHighscores.get(position);

        holder.mHighscore.setText(String.valueOf(hs.getScore()));

        String displayDate = (String)android.text.format.DateFormat.format("yyyy-MM-dd", hs.getCreatedAt());
        holder.mDateView.setText(displayDate);
    }

    @Override
    public int getItemCount() {
        if (mHighscores != null) {
            return mHighscores.size();
        }
        else{
            return 0;
        }
    }





}
