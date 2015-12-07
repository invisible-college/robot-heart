package college.invisible.robothello;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    enum Side { QUESTION, ANSWER };
    public static String EXTRA_MESSAGE = "college.invisible.MainActivity.MESSAGE";

    /* Click listener for floating action button */
    private class ClickListener implements View.OnClickListener {

        private RelativeLayout rootView;
        private Context context;
        private FloatingActionButton fab;
        private int currentId;
        private int previousId;
        private int index;

        public ClickListener(int previousId) {
            this.previousId = previousId;
            this.index = 0;
        }

        public void setRootView(RelativeLayout rootView) {
            this.rootView = rootView;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public void setFab(FloatingActionButton fab) {
            this.fab = fab;
            this.fab.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            TextView tv = new TextView(this.context);
            tv.setText(Integer.toString(this.index));

            tv.setTextColor(Color.GREEN);
            RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );

            TextView previousTv = (TextView) findViewById(this.previousId);
            previousTv.setText("Obsolete!");

            // Make it below the previous child, with id minus one
            rl.addRule(RelativeLayout.BELOW, this.previousId);
            this.currentId = this.previousId + 1;
            System.out.println("index" + this.index);
            Snackbar.make(view, "index " + this.index, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            tv.setId(this.currentId);
            System.out.println("Previous id" + this.previousId);
            this.previousId = this.currentId;
            this.index += 1;

            this.rootView.addView(tv, rl);
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
        }

    };


    private TextView tv;
    private Side showSide = Side.QUESTION; // Start out showing question by default


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RelativeLayout rootView = (RelativeLayout) this.findViewById(R.id.relative_view);
        Context context = rootView.getContext();



        // Populate the textview once here to be used later in onClick
        tv = (TextView) findViewById(R.id.hello_view);
        String helloString = getResources().getString(R.string.hello_string);
        tv.setText(helloString);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // Create the click listener with the previous text view
        ClickListener clickListener = new ClickListener(tv.getId());
        clickListener.setContext(context);
        clickListener.setFab(fab); // this also sets the click listener on the fab
        clickListener.setRootView(rootView);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.flash_card_list);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        FlashCardAdapter fca = new FlashCardAdapter(this);
        List<FlashCard> fcl = new ArrayList<>();
        fcl.add(new FlashCard("What is round and contains gold?", "An egg"));
        fcl.add(new FlashCard("Question 2", "Answer 2"));
        fca.addFlashCards(fcl);
        rv.setAdapter(fca);

        Intent intent = new Intent(this, TextViewActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "Seed message");
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
