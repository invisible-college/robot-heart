package college.invisible.robothello;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void setUp() {
        System.out.println("We ran the setup!");
    }

    public void tearDown() {
        System.out.println("We run the teardown!");
    }
}