package com.fajarsiddiq.berpasangan.module.board;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.module.ModuleActivity;
import com.norbsoft.typefacehelper.TypefaceHelper;

import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_grid_layout;
import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_pause_image_view;
import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_score_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_timer_text_view;
import static com.fajarsiddiq.berpasangan.R.layout.layout_board_activity;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_time_pause;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_time_pause_title;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_time_resume;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardActivity extends ModuleActivity implements View.OnClickListener {
    public static String row = BoardActivity.class.getSimpleName() + ".row";
    public static String column = BoardActivity.class.getSimpleName() + ".column";
    public static String mode = BoardActivity.class.getSimpleName() + ".mode";
    public static int totalQuestion;
    public static int attemp = 0;
    public static int answered = 0;
    public static int zonk = 0;
    public static int gameMode;

    private BoardController mController;
    private GridLayout mGridLayout;
    private ImageView mPauseImageView;
    private TextView mTimerTextView;
    private TextView mScoreTextView;
    private BoardSound mBoardSound;

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
        initUI();
    }

    public void initUI() {
        mGridLayout = (GridLayout) findViewById(id_board_fragment_grid_layout);
        mPauseImageView = (ImageView) findViewById(id_board_fragment_pause_image_view);
        mPauseImageView.setOnClickListener(this);
        mTimerTextView = (TextView) findViewById(id_board_fragment_timer_text_view);
        mScoreTextView = (TextView) findViewById(id_board_fragment_score_text_view);
        mController = new BoardController(this);

        Bundle bundle = getIntent().getExtras();
        initQuestion(bundle.getInt(BoardActivity.row), bundle.getInt(BoardActivity.column));
        initTimer();

        mBoardSound = new BoardSound(this);
    }

    public ImageView getPauseImageView() {
        return mPauseImageView;
    }

    public TextView getTimerTextView() {
        return mTimerTextView;
    }

    public TextView getScoreTextView() {
        return mScoreTextView;
    }

    public GridLayout getGridLayout() {
        return mGridLayout;
    }

    public BoardSound getBoardSound() {
        return mBoardSound;
    }

    private void initTimer() {
        mController.initTimer();
    }

    private void initQuestion(final int x, final int y) {
        mGridLayout.setRowCount(x);
        mGridLayout.setColumnCount(y);
        mController.initQuestion(x, y);
    }

    public boolean isSame(final int id1, final int id2) {
        return mController.isSame(id1, id2);
    }

    public boolean isZonk(final int id) {
        return mController.isZonk(id);
    }

    public Item getCell(final int id) {
        return mController.getCell(id);
    }

    public void refreshBoard(final Integer id1, final Integer id2) {
        if(id1 != null && id2 != null)
            mController.getView(id1, id2);
        else if(id1 != null && id2 == null)
            mController.getView(id1);
        else
            mController.getView();
    }

    public void updateScore(final int diff) {
        mController.updateScore(diff);
    }

    public void resumeTimer() {
        mController.resumeTimer();
        mPauseImageView.setOnClickListener(this);
    }

    public void pauseTimer() {
        mController.pauseTimer();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case id_board_fragment_pause_image_view:
                mBoardSound.playSound(mBoardSound.PAUSE_SOUND);
                pauseTimer();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(string_board_fragment_time_pause))
                        .setTitle(getString(string_board_fragment_time_pause_title));
                builder.setPositiveButton(getString(string_board_fragment_time_resume), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resumeTimer();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
                mPauseImageView.setOnClickListener(null);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        mBoardSound.release();
        super.onDestroy();
    }
}
