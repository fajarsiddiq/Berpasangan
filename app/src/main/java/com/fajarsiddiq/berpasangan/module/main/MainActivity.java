package com.fajarsiddiq.berpasangan.module.main;

import android.os.Bundle;

import com.fajarsiddiq.berpasangan.module.ModuleActivity;

import static com.fajarsiddiq.berpasangan.R.layout.layout_main_activity;

/**
 * Created by Muhammad Fajar on 17/03/2016.
 */
public class MainActivity extends ModuleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_main_activity);
    }
}
