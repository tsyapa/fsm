package com.example.vitaliytsyapa.fsm;

import android.widget.Button;
import android.widget.TextView;
import com.example.vitaliytsyapa.fsm.activities.MainActivity;
import org.junit.*;
import org.junit.runner.RunWith;
import org.robolectric.*;
import org.robolectric.annotation.Config;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "/AndroidManifest.xml", packageName = "com.example.vitaliytsyapa.fsm")
public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup() throws Exception{
        activity=Robolectric.setupActivity(MainActivity.class);
    }

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

}
