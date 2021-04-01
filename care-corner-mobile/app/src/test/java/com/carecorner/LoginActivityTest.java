package com.carecorner;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class LoginActivityTest {
    private LoginActivity activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(LoginActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void loginButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        Button button = (Button) shadowActivity.getContentView().findViewById(R.id.btnLogin);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(MainMenuActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void registerButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        Button button = (Button) shadowActivity.getContentView().findViewById(R.id.btnRegister);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(RegisterActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void forgotUsernameButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        Button button = (Button) shadowActivity.getContentView().findViewById(R.id.btnForgotUsername);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(ForgotUsernameActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void forgotPasswordButtonClickShouldStartNewActivity() throws Exception
    {
        ShadowActivity shadowActivity = shadowOf(activity);
        Button button = (Button) shadowActivity.getContentView().findViewById(R.id.btnForgotPassword);
        button.callOnClick();
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(ForgotPasswordActivity.class, shadowIntent.getIntentClass());
    }

    //TODO: More testing needs to be done once there is actual user authentication.
}