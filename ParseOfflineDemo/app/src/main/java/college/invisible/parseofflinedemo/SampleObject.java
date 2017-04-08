package college.invisible.parseofflinedemo;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ppham on 3/6/17.
 */

@ParseClassName("SampleObject")
public class SampleObject extends ParseObject {

    public final static String FIELD_DISPLAY_NAME = "displayName";
    public final static String FIELD_LOCAL_ID = "localId";

    public void setDisplayName(String displayName) {
        put(FIELD_DISPLAY_NAME, displayName);
    }

    public String getDisplayName() {
        if (has(FIELD_DISPLAY_NAME)) {
            return getString(FIELD_DISPLAY_NAME);
        } else {
            return "";
        }
    }

    public void setLocalId(String localId) {
        put(FIELD_LOCAL_ID, localId);
    }

    public String getLocalId() {
        if (has(FIELD_LOCAL_ID)) {
            return getString(FIELD_LOCAL_ID);
        } else {
            return "";
        }
    }

    public SampleObject() {  };
}
