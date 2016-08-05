package com.fajarsiddiq.berpasangan.helper;

import android.content.Context;

import static com.fajarsiddiq.berpasangan.R.array.array_level_name;
/**
 * Created by Muhammad Fajar on 05/07/2016.
 */
public enum GameMode {
    MODE_4x4(0),
    MODE_4x5(1),
    MODE_5x5(2),
    MODE_6x5(3);

    private int mMode;

    GameMode(final int mode) {
        this.mMode = mode;
    }

    public int getMode() {
        return mMode;
    }

    public String getName(final Context context) {
        return new StringBuilder("MODE ").append(context.getResources().getStringArray(array_level_name)[mMode]).toString();
    }

    public static GameMode fromValue(final int mode) {
        if(mode == MODE_4x4.mMode)
            return MODE_4x4;
        else if(mode == MODE_4x5.mMode)
            return MODE_4x5;
        else if(mode == MODE_5x5.mMode)
            return MODE_5x5;
        else if(mode == MODE_6x5.mMode)
            return MODE_6x5;
        else
            return null;
    }

}