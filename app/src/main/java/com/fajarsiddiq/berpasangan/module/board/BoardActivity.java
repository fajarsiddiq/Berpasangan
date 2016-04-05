package com.fajarsiddiq.berpasangan.module.board;

import android.os.Bundle;

import com.fajarsiddiq.berpasangan.module.ModuleActivity;

import static com.fajarsiddiq.berpasangan.R.layout.layout_board_activity;
import static com.fajarsiddiq.berpasangan.R.id.id_board_activity_fragment;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardActivity extends ModuleActivity {
    public static String row = BoardActivity.class.getSimpleName() + ".row";
    public static String column = BoardActivity.class.getSimpleName() + ".column";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_board_activity);
    }

    @Override
    public void onBackPressed() {
        BoardFragment fragment = (BoardFragment) getSupportFragmentManager().findFragmentById(id_board_activity_fragment);
        fragment.stopTimer();
        super.onBackPressed();
    }
}
