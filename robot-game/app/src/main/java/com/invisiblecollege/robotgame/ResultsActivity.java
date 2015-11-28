package com.invisiblecollege.robotgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.SaveCallback;

/**
 * Created by randy.thedford on 11/27/15.
 */
public class ResultsActivity extends AppCompatActivity implements View.OnClickListener, SaveCallback{

    int mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);

        Intent intent = getIntent();

        mScore = intent.getIntExtra("score", 0);

        saveToParse();

        TextView score = (TextView)findViewById(R.id.score);
        score.setText( String.valueOf(mScore) );

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(this);

    }

    public void saveToParse(){
        Highscore hs = new Highscore();
        hs.setScore(mScore);

        hs.saveInBackground(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.back:
                finish();
                break;
        }


    }

    @Override
    public void done(ParseException e) {
        if (e != null){
            Toast.makeText(this, "Error saving highscore", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Saved highscore to parse", Toast.LENGTH_SHORT).show();
        }
    }
}
