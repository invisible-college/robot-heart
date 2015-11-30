package org.invisiblecollege.robotmap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by adrian on 5/23/15.
 */
public class SavedPointsHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "robotmap";

    private static final int DATABASE_VERSION = 1;
    private static final String POINTS_TABLE_NAME = "points";

    private static final String POINT_ID = "point_id";
    private static final int POINT_ID_INDEX = 0;
    private static final String POINT_LAT = "point_lat";
    private static final int POINT_LAT_INDEX = 1;
    private static final String POINT_LNG = "point_lng";
    private static final int POINT_LNG_INDEX = 2;

    private static final String POINTS_TABLE_CREATE =
            "CREATE TABLE " + POINTS_TABLE_NAME + "("
                    + POINT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + POINT_LAT + " REAL,"
                    + POINT_LNG + " REAL" + ")";

    public SavedPointsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(POINTS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + POINTS_TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void saveNewPoint(final double lat, final double lng) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(POINT_LAT, lat);
        values.put(POINT_LNG, lng);
        db.insert(POINTS_TABLE_NAME, null, values);
        db.close();
    }

    public List<LatLng> getSavedPoints() {
        List<LatLng> points = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + POINTS_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                LatLng point = getPointFromDbRow(cursor);
                points.add(point);
            } while (cursor.moveToNext());
        }
        db.close();
        return points;
    }

    private LatLng getPointFromDbRow(Cursor cursor) {
        double lat = cursor.getDouble(POINT_LAT_INDEX);
        double lng = cursor.getDouble(POINT_LNG_INDEX);
        return new LatLng(lat, lng);
    }
}

