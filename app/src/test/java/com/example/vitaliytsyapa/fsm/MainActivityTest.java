package com.example.vitaliytsyapa.fsm;

/**
 * Created by Vitaliy Tsyapa on 10/21/2017.
 */

import android.widget.Button;
import android.widget.TextView;

import com.example.vitaliytsyapa.fsm.activities.MainActivity;

import org.json.JSONObject;
import org.junit.*;
import org.junit.runner.RunWith;
import org.robolectric.*;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "/AndroidManifest.xml", packageName = "com.example.vitaliytsyapa.fsm")
public class MainActivityTest {
    private MainActivity activity;
    private FSM fsm;
    private String json;

    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() throws Exception{
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        activity=Robolectric.setupActivity(MainActivity.class);
        fsm=new FSM(activity);
        json="{"+
                "\"array\":[\"value1\",\"value2\",\"value3\"],"+
                "\"field\":\"value\""+
            "}";
    }

    // @Test => JUnit 4 annotation specifying this is a test to be run
    // The test simply checks that our TextView exists and has the text "Hello world!"

    @Test
    public void checkActivityNotNull() {
        assertNotNull(activity);
    }

    @Test
    public void checkTextViewNotNull() {
        TextView tvState = (TextView) activity.findViewById(R.id.textView);
        assertNotNull(tvState);
    }

    @Test
    public void checkBtnLockNotNull() {
        Button btnLock=(Button) activity.findViewById(R.id.buttonLock);
        assertNotNull(btnLock);
    }

    @Test
    public void checkBtnLockX2NotNull() {
        Button btnLockX2=(Button) activity.findViewById(R.id.buttonLockX2);
        assertNotNull(btnLockX2);
    }

    @Test
    public void checkBtnUnlockNotNull() {
        Button btnUnlock=(Button) activity.findViewById(R.id.buttonUnlock);
        assertNotNull(btnUnlock);
    }

    @Test
    public void checkBtnUnlockX2NotNull() {
        Button btnUnlockX2=(Button) activity.findViewById(R.id.buttonUnlockX2);
        assertNotNull(btnUnlockX2);
    }
/*
    @Test
    public void checkReadArrayFromJson() throws Exception{
        assertNotNull(fsm.readArrayFromJson("array",new JSONObject(json)));
    }

    @Test
    public void checkReadFieldFromJson() throws Exception{
        assertNotNull(fsm.readFieldFromJson("field",new JSONObject(json)));
    }
*/
}
