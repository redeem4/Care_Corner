package com.carecorner.util;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import org.junit.Test;

public class NetworkConnectionTest {
    private static final String FAKE_STRING = "HELLO_WORLD";
    private Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void networkConnectionTest_Online_ReturnsTrue() {
        boolean hasConnection = NetworkConnection.hasActiveInternetConnection(context);
        assertThat(hasConnection).isTrue();
    }
}
