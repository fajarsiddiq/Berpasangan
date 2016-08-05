package com.fajarsiddiq.berpasangan.helper;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

/**
 * Created by fajar on 8/5/16.
 * Constructor based on http://stackoverflow.com/a/19311820
 */

public class SoundHelper {
    protected final int MAX_NUMBER_OF_STREAM;
    protected int[] mSoundIDs;
    protected SoundPool mSoundPool;
    private Context mContext;

    protected SoundHelper(final Context context, final int maxStream) {
        mContext  = context;
        MAX_NUMBER_OF_STREAM = maxStream;
        mSoundIDs = new int[MAX_NUMBER_OF_STREAM];
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attrs = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(MAX_NUMBER_OF_STREAM)
                    .setAudioAttributes(attrs)
                    .build();
        } else {
            mSoundPool = new SoundPool(MAX_NUMBER_OF_STREAM, AudioManager.STREAM_MUSIC, 0);
        }
    }

    protected void addSound(final int index, final int sound) {
        mSoundIDs[index] = mSoundPool.load(mContext, sound, 1);
    }

    protected void playSong(final int index) {
        mSoundPool.play(mSoundIDs[index], 1, 1, 1, 0, 1.0f);
    }

    protected void release() {
        mSoundPool.release();
    }
}
