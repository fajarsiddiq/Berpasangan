package com.fajarsiddiq.berpasangan.helper;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Muhammad Fajar on 23/07/2017.
 */

public class ImageLoader {
    public static void loadImage(Context context, String url, ImageView view) {
        Picasso.with(context).load(url).fit().into(view);
    }
}
