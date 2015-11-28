package com.invisiblecollege.robotgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button play = (Button)findViewById(R.id.play);
        play.setOnClickListener(this);

        Button hs = (Button)findViewById(R.id.view_highscore);
        hs.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.play:
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.view_highscore:
                Intent intent2 = new Intent(this, HighscoreActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
