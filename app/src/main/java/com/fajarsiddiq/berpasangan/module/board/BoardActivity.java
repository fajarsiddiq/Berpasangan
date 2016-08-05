package com.fajarsiddiq.berpasangan.module.board;

import android.media.AudioManager;
import android.os.Bundle;

import com.fajarsiddiq.berpasangan.module.ModuleActivity;
import com.norbsoft.typefacehelper.TypefaceHelper;

import static com.fajarsiddiq.berpasangan.R.layout.layout_board_activity;
import static com.fajarsiddiq.berpasangan.R.id.id_board_activity_fragment;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardActivity extends ModuleActivity {
    public static String row = BoardActivity.class.getSimpleName() + ".row";
    public static String column = BoardActivity.class.getSimpleName() + ".column";
    public static String mode = BoardActivity.class.getSimpleName() + ".mode";
    public static int totalQuestion;
    public static int attemp = 0;
    public static int answered = 0;
    public static int zonk = 0;
    public static int gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout_board_activity);
        TypefaceHelper.typeface(this);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        attemp = 0;
        answered = 0;
        totalQuestion = (getIntent().getIntExtra(row, 0) * getIntent().getIntExtra(column, 0)) / 2;
        zonk = 0;
        gameMode = getIntent().getIntExtra(mode, 0);
    }

}
