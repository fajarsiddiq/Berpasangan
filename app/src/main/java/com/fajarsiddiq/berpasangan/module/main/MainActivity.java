package com.fajarsiddiq.berpasangan.module.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.fajarsiddiq.berpasangan.module.ModuleActivity;

import static com.fajarsiddiq.berpasangan.R.layout.layout_main_activity;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt_negative;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt_positive;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt_title;

/**
 * Created by Muhammad Fajar on 17/03/2016.
 */
public class MainActivity extends ModuleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_main_activity);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(string_main_activity_exit_prompt))
                .setTitle(getString(string_main_activity_exit_prompt_title));
        builder.setPositiveButton(getString(string_main_activity_exit_prompt_positive), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton(getString(string_main_activity_exit_prompt_negative), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do nothing
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
