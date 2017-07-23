package com.fajarsiddiq.berpasangan.helper;


import android.app.Activity;
import android.content.Context;

import com.nispok.snackbar.listeners.ActionClickListener;

import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_time_resume;
import static com.nispok.snackbar.Snackbar.SnackbarDuration.LENGTH_INDEFINITE;
import static com.nispok.snackbar.Snackbar.SnackbarDuration.LENGTH_SHORT;
import static com.nispok.snackbar.Snackbar.with;

/**
 * Created by Muhammad Fajar on 07/04/2016.
 */
public class SnackBarHelper {
    public static void useSnackBar(final Context context, final Activity activity, final String message) {
        with(context).duration(LENGTH_SHORT)
                .text(message)
                .show(activity);
    }

    public static void usePersistentSnackBar(final Context context, final Activity activity, final String message, final ActionClickListener listener) {
        with(context).duration(LENGTH_INDEFINITE)
                .actionLabel(string_board_fragment_time_resume)
                .actionListener(listener)
                .text(message)
                .show(activity);
    }
}
