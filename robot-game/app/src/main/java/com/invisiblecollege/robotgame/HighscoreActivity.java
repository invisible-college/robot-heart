package com.invisiblecollege.robotgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by randy.thedford on 11/27/15.
 */
public class HighscoreActivity extends AppCompatActivity implements FindCallback<Highscore>{

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_highscore);

        mRecyclerView = (RecyclerView)findViewById(R.id.highscore_list);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);

        ParseQuery<Highscore> query = new ParseQuery<Highscore>(Highscore.class);
        query.addDescendingOrder(Highscore.FIELD_SCORE);
        query.findInBackground(this);
    }

    @Override
    public void done(List<Highscore> list, ParseException e) {
        if (e != null){
            Toast.makeText(HighscoreActivity.this, "Couldn't connect to server", Toast.LENGTH_SHORT).show();
        }
        else{
            mAdapter = new HighScoreRecycler(list);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
