package college.invisible.gridviewdemo;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setNestedScrollingEnabled(true);
        gridview.setOnScrollChangeListener(new AbsListView.OnScrollChangeListener() {

            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                StringBuilder logMessage = new StringBuilder();
                logMessage.append(view.getId() + " ");
                logMessage.append(i + " ");
                logMessage.append(i1 + " ");
                logMessage.append(i2 + " ");
                logMessage.append(i3 + " ");

                Log.i(LOG_TAG, logMessage.toString());

            }
        });
        /*
        gridview.setOnScrollChangeListener(new ScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                StringBuilder logMessage = new StringBuilder();
                logMessage.append(view.getId() + " ");
                logMessage.append(i + " ");
                logMessage.append(i1 + " ");
                logMessage.append(i2 + " ");
                logMessage.append(i3 + " ");

                Log.i(LOG_TAG, logMessage.toString());

            }

        });
        */
    }
}
