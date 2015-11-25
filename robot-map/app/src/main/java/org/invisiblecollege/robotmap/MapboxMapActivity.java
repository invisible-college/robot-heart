package org.invisiblecollege.robotmap;

import android.app.Activity;
import android.os.Bundle;

import com.mapbox.mapboxsdk.constants.MyLocationTracking;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.views.MapView;


public class MapboxMapActivity extends Activity {

    private MapView mapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapbox_map);

        MapView mapView = (MapView) findViewById(R.id.mapboxMapView);
        mapView.setStyleUrl(Style.MAPBOX_STREETS);
        mapView.setCenterCoordinate(new LatLng(47.605967, -122.334539));
        mapView.setZoomLevel(11);

        // Track user's location and display it on the map
        mapView.setMyLocationEnabled(true);

        // Disable continuously tracking user location
        mapView.setMyLocationTrackingMode(MyLocationTracking.TRACKING_NONE);
        
        // Continue to track the user location and update the position on the map
        //mapView.setMyLocationTrackingMode(MyLocationTracking.TRACKING_FOLLOW);

        mapView.onCreate(savedInstanceState);
    }
}
