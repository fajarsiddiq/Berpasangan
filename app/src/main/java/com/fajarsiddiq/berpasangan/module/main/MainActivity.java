package com.fajarsiddiq.berpasangan.module.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.fajarsiddiq.berpasangan.helper.SQLiteHelper;
import com.fajarsiddiq.berpasangan.module.ModuleActivity;
import com.fajarsiddiq.berpasangan.sqlite.User;
import com.orm.SugarContext;

import static java.lang.System.currentTimeMillis;
import static com.fajarsiddiq.berpasangan.R.id.id_main_activity_fragment;
import static com.fajarsiddiq.berpasangan.R.layout.layout_main_activity;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt_negative;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt_positive;
import static com.fajarsiddiq.berpasangan.R.string.string_main_activity_exit_prompt_title;

/**
 * Created by Muhammad Fajar on 17/03/2016.
 */
public class MainActivity extends ModuleActivity {
    private long duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SugarContext.init(this);
        SQLiteHelper.prepareDb("Fajar");
        super.onCreate(savedInstanceState);
        setContentView(layout_main_activity);
        duration = currentTimeMillis();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(string_main_activity_exit_prompt))
                .setTitle(getString(string_main_activity_exit_prompt_title));
        builder.setPositiveButton(getString(string_main_activity_exit_prompt_positive), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                User user = User.findById(User.class, 1);
                duration = currentTimeMillis() - duration;
                duration = duration + user.getTotalDuration();
                user.setTotalDuration(duration);
                user.save();
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

    @Override
    protected void onResume() {
        super.onResume();
        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(id_main_activity_fragment);
        fragment.refreshCoin();
    }
}
