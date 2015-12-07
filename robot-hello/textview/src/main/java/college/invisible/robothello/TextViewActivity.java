package college.invisible.robothello;

import java.util.List;
import java.util.Arrays;
import android.graphics.Color;
import java.util.HashMap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;


public class TextViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RelativeLayout rootView = (RelativeLayout) this.findViewById(android.R.id.content).getRootView();
        RelativeLayout rl = (RelativeLayout) this.findViewById(R.id.relative_view);
        Context context = rl.getContext();
        //LinearLayout lL = new LinearLayout(context);
        //lL.setOrientation(LinearLayout.VERTICAL);
        List<String> authorNames = Arrays.asList("Chuck Palahniuk", "Gobra Cobra", "Tim Ferriss");
        final HashMap<Integer,String> map = new HashMap<Integer,String>();
        int prevId = R.id.hello_view;

        for (int i=0;i< authorNames.size();i++) {
            TextView tv = new TextView(this);
            int currId = prevId + 1;
            tv.setId(currId);
            tv.setText(authorNames.get(i));
            tv.setTextColor(Color.BLACK);
            Integer a = tv.hashCode();
            map.put(a, authorNames.get(i));

            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params1.addRule(RelativeLayout.BELOW, prevId);
            prevId = currId;
            tv.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    System.out.println("Clicked " + map.get(v.hashCode()));
                }
            });
            //lL.addView(tv);
            rl.addView(tv, params1);
        }
//        try {
//            if (true) {
//                System.out.println("There was an old lady who swallowed a fly.");
//                Thread.sleep(1000);
//                System.out.println("I don't know why she swallowed the fly.");
//                Thread.sleep(1000);
//                System.out.println("Perhaps she'll die.");
//                Thread.sleep(1000);
//            }
//        } catch (InterruptedException e) {
//            System.err.println("Sleep interrupted!");
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View v) {
        System.out.println("Print something");
        TextView tv = (TextView)v;
        tv.setText("Good-bye cruel world");
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
}
