package com.fajarsiddiq.berpasangan.module.highscore;

import android.os.Bundle;

import com.fajarsiddiq.berpasangan.module.ModuleActivity;
import com.norbsoft.typefacehelper.TypefaceHelper;

import static com.fajarsiddiq.berpasangan.R.layout.layout_highscore_activity;

/**
 * Created by Muhammad Fajar on 16/06/2016.
 */
public class HighscoreActivity extends ModuleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_highscore_activity);
        TypefaceHelper.typeface(this);
    }
}
