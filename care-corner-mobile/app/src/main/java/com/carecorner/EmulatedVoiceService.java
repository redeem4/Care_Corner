package com.carecorner;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import android.net.Uri;

import androidx.annotation.Nullable;

import java.net.URI;

public class EmulatedVoiceService extends Service {
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Set intent(voice selection) to voiceNumber variable
        int voiceNumber = intent.getIntExtra("callerVoice", 0);

        //create different voices based on selection
        switch(voiceNumber) {
            case 1:
                player = MediaPlayer.create(this, R.raw.emulated_voice01);

                //the following code will make output play through earpiece of headphones
                //player.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
                break;
            default:
                player = MediaPlayer.create(this, R.raw.emulated_voice00);

                //the following code will make output play through earpiece of headphones
                //player.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
        }

        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
    }
}
