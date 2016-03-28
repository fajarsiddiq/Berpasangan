package com.fajarsiddiq.berpasangan.module.board;

import android.os.Bundle;

import com.fajarsiddiq.berpasangan.module.ModuleActivity;

import static com.fajarsiddiq.berpasangan.R.layout.layout_board_activity;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardActivity extends ModuleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_board_activity);
    }
}
