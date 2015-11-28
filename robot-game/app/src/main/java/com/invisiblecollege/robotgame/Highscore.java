package com.invisiblecollege.robotgame;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by randy.thedford on 11/27/15.
 */
@ParseClassName("Highscore")
public class Highscore extends ParseObject{

    public static String FIELD_SCORE = "score";

    public void setScore(int score){
        put(FIELD_SCORE, score);
    }

    public int getScore(){
        if (has(FIELD_SCORE)){
            return getInt(FIELD_SCORE);
        }
        else{
            return 0;
        }
    }

}
