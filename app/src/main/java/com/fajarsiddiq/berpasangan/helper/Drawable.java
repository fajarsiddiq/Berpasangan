package com.fajarsiddiq.berpasangan.helper;

import android.content.Context;
import android.util.Log;

/**
 * Created by Muhammad Fajar on 11/04/2016.
 */
public class Drawable {
    /**
     * Static method to retrieve ID of drawable using its filename
     * Code from http://stackoverflow.com/questions/15488238/using-android-getidentifier
     *
     * @param context context to access resource
     * @param drawableName the name of drawable
     * @return ID of expected drawable
     */
    public static int getDrawable(final Context context, final String drawableName) {
        Log.i("test", drawableName);
        return context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
    }
}
