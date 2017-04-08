package college.invisible.parseofflinedemo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ppham on 3/6/17.
 */

public class MainApplication extends Application {

    public final static String LOG_TAG = MainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        //Define sub classes
        ParseObject.registerSubclass(SampleObject.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("sgrams")
                .clientKey(null)
                .server("http://soundgrams.herokuapp.com/parse/")
                .enableLocalDataStore()
                .build());

        // We can reset the pinned objects here.
        //deleteAllPinnedObjects();

        final SampleObject newSample = new SampleObject();
        newSample.setDisplayName("Bobo 2");
        newSample.setLocalId(CryptoUtils.getRandomString(10));
        newSample.pinInBackground( new SaveCallback( ) {

            @Override
            public void done( ParseException e ) {
                if( e == null ) {

                    queryOffline();
                    //Log.i(LOG_TAG, newSample.getObjectId());
                } else {
                    Log.e(LOG_TAG, e.toString());
                }
            }
        } );
        newSample.saveInBackground();

        // As a test, try forcing a read of all soundgrams and samples from SD
        // and upload to Parse server
        // Do this first, to create Parse Object IDs for all objects in memory.

    }

    public static void deleteAllPinnedObjects() {
        ParseQuery<SampleObject> query = ParseQuery.getQuery(SampleObject.class);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<SampleObject>() {
            @Override
            public void done(List<SampleObject> results, ParseException e) {
                if (e != null || results == null) {
                    Log.e(LOG_TAG + ":syncFromParseOffline1", e.toString());
                    return;
                }
                int i = 0;
                for (SampleObject newObj : results) {
                    newObj.unpinInBackground();
                    newObj.deleteInBackground();
                }
            }
        });

    }

    public static void queryOffline() {
        ParseQuery<SampleObject> query = ParseQuery.getQuery(SampleObject.class);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<SampleObject>() {
            @Override
            public void done(List<SampleObject> results, ParseException e) {
                if (e != null || results == null) {
                    Log.e(LOG_TAG + ":syncFromParseOffline1", e.toString());
                    return;
                }
                int i = 0;
                for (SampleObject newObj : results) {
                    if (newObj.getLocalId() == null || newObj.getLocalId().isEmpty()) {
                        newObj.setLocalId(CryptoUtils.getRandomString(CryptoUtils.LOCAL_ID_LENGTH));
                        Log.i(LOG_TAG, "Setting new local ID: " + newObj.getLocalId());
                        // We created this object before we had local IDs, so give it one now
                    }
                    if (newObj.getObjectId() == null || newObj.getObjectId().isEmpty()) {
                        newObj.setObjectId(CryptoUtils.getRandomString(CryptoUtils.LOCAL_ID_LENGTH));
                        Log.i(LOG_TAG, "Setting new object ID: " + newObj.getObjectId());

                        // parse-server will not accept our locally created ID as the server ID
                        newObj.saveInBackground(); // And of course, now we try to upload it again
                        // this could fail if we're still offline, but at least we'll have an object ID
                        // this time

                    }
                    newObj.pinInBackground(); // To save either of the new localID or objectID

                    Log.i(LOG_TAG, "Retrieving new offline soundgram object from Parse: " + i + " " + newObj.getObjectId());
                    i += 1;
                }
            }
        });

    }

}
