package com.fajarsiddiq.berpasangan.helper;


import android.app.Activity;
import android.content.Context;

import static com.nispok.snackbar.Snackbar.with;
import static com.nispok.snackbar.Snackbar.SnackbarDuration.LENGTH_SHORT;

/**
 * Created by Muhammad Fajar on 07/04/2016.
 */
public class SnackBarHelper {
    public static void useSnackBar(final Context context, final Activity activity, final String message) {
        with(context).duration(LENGTH_SHORT)
                .text(message).show(activity);
    }
}
