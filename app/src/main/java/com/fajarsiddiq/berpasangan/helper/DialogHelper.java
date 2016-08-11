package com.fajarsiddiq.berpasangan.helper;

import android.app.Activity;
import android.app.Dialog;

import static android.view.Window.FEATURE_NO_TITLE;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class DialogHelper {
    public static Dialog buildDialog(final Activity activity, final int layoutId) {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(FEATURE_NO_TITLE);
        dialog.setContentView(layoutId);
        return dialog;
    }
}
