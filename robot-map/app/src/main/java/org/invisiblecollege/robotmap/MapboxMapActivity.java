package org.invisiblecollege.robotmap;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.constants.MyLocationTracking;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.views.MapView;

import java.util.List;


public class MapboxMapActivity extends Activity {

    private MapView mapView;
    private SavedPointsHelper savedPointsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapbox_map);

        savedPointsHelper = new SavedPointsHelper(this);

        mapView = (MapView) findViewById(R.id.mapboxMapView);

        mapView.setStyleUrl(Style.MAPBOX_STREETS);
        mapView.setCenterCoordinate(new LatLng(47.605967, -122.334539));
        mapView.setZoomLevel(11);

        // Track user's location and display it on the map
        mapView.setMyLocationEnabled(true);

        // Disable continuously tracking user location
        mapView.setMyLocationTrackingMode(MyLocationTracking.TRACKING_NONE);
        
        // Continue to track the user location and update the position on the map
        //mapView.setMyLocationTrackingMode(MyLocationTracking.TRACKING_FOLLOW);

        // Load previously saved points from internal storage and display on map
        showAllSavedPoints();

        final Button button = (Button) findViewById(R.id.saveLocationButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Location myLocation = mapView.getMyLocation();
                addPointToMap(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()));
                savedPointsHelper.saveNewPoint(myLocation.getLatitude(), myLocation.getLongitude());
            }
        });

        mapView.onCreate(savedInstanceState);
    }

    private void addPointToMap(LatLng locationToAdd) {
        String latLngAsString = locationToAdd.getLatitude() + "," + locationToAdd.getLongitude();
        mapView.addMarker(new MarkerOptions()
                .position(new LatLng(locationToAdd.getLatitude(), locationToAdd.getLongitude()))
                .title(latLngAsString));
    }

    private void showAllSavedPoints() {
        List<LatLng> savedPoints = savedPointsHelper.getSavedPoints();
        for (LatLng point : savedPoints) {
            addPointToMap(point);
        }
    }
}
