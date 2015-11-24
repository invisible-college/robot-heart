package org.invisiblecollege.robotmap;

import android.app.Activity;
import android.os.Bundle;

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
        mapView.onCreate(savedInstanceState);
    }
}
