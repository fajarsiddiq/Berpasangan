package com.fajarsiddiq.berpasangan.helper;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.Random;

import static com.fajarsiddiq.berpasangan.R.array.array_color;

/**
 * Created by Muhammad Fajar on 23/05/2016.
 *
 * Using TypedArray by guidance from https://developer.android.com/guide/topics/resources/more-resources.html#Color
 *
 */
public class ColorPickerHelper {

    public static int getColor(final Context context) {
        int index = new Random().nextInt();
        TypedArray color = context.getResources().obtainTypedArray(array_color);
        return color.getColor(index, 0);
    }
}
