package college.invisible.robothello;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    enum Side { QUESTION, ANSWER };

    private TextView tv;
    private Side showSide = Side.QUESTION; // Start out showing question by default

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Populate the textview once here to be used later in onClick
        tv = (TextView) findViewById(R.id.hello_view);
        String helloString = getResources().getString(R.string.hello_string);
        tv.setText(helloString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        TextView tv = (TextView) v;

        // Toggle the QUESTION to the ANSWER or vice versa
        if (showSide == Side.QUESTION) {
            showSide = Side.ANSWER;
            String goodbyeString = getResources().getString(R.string.goodbye_string);
            tv.setText(goodbyeString);
        } else {
            showSide = Side.QUESTION;
            String helloString = getResources().getString(R.string.hello_string);
            tv.setText(helloString);
        }
    }
}
