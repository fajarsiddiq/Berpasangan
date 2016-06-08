package com.fajarsiddiq.berpasangan.module.result;

import android.os.Bundle;
import android.util.Log;

import com.fajarsiddiq.berpasangan.module.ModuleActivity;
import com.norbsoft.typefacehelper.TypefaceHelper;

import static com.fajarsiddiq.berpasangan.R.layout.layout_result_activity;
/**
 * Created by Muhammad Fajar on 28/05/2016.
 */
public class ResultActivity extends ModuleActivity {
    public static String data = ResultActivity.class.getName() + ".data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_result_activity);
        TypefaceHelper.typeface(this);
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}
