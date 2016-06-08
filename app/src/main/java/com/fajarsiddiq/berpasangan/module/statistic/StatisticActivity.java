package com.fajarsiddiq.berpasangan.module.statistic;

import android.os.Bundle;

import com.fajarsiddiq.berpasangan.module.ModuleActivity;
import com.norbsoft.typefacehelper.TypefaceHelper;

import static com.fajarsiddiq.berpasangan.R.layout.layout_statistic_activity;

/**
 * Created by Muhammad Fajar on 07/06/2016.
 */
public class StatisticActivity extends ModuleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_statistic_activity);
        TypefaceHelper.typeface(this);
    }
}
