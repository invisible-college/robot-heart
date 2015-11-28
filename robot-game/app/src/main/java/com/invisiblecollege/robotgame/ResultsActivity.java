package com.invisiblecollege.robotgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by randy.thedford on 11/27/15.
 */
public class ResultsActivity extends AppCompatActivity implements View.OnClickListener{

    int mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);

        Intent intent = getIntent();

        mScore = intent.getIntExtra("score", 0);

        //TODO save to parse
        Toast.makeText(ResultsActivity.this, "TODO Save to Parse", Toast.LENGTH_SHORT).show();

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(this);

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
}
