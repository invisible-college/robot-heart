package com.invisiblecollege.robotgame;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by randy.thedford on 11/27/15.
 */
public class RobotApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Define sub classes
        ParseObject.registerSubclass(Highscore.class);

        Parse.initialize(this, "V32UgJ0GBZYk5bUhGux6nd4UsqU2kyAI4gawpJs6", "DE9g3hd2qLAEqgkOgs5dT7TYcxHI8jBumAZJFTIc");

    }
}
