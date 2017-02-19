package college.invisible.zoomviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private MyView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        mView = (MyView) findViewById(R.id.my_view);
        mView.setExampleString("Verbus Programmaticus");
*/
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setColumnWidth(200);
        gridView.setAdapter(new ImageAdapter(this));
    }
}
