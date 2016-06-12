package com.fajarsiddiq.berpasangan.module.board;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;

import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_grid_layout;
import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_pause_image_view;
import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_score_text_view;
import static com.fajarsiddiq.berpasangan.R.layout.layout_board_fragment;
import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_timer_text_view;
import static android.view.View.OnClickListener;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_time_pause;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_time_pause_title;
import static com.fajarsiddiq.berpasangan.R.string.string_board_fragment_time_resume;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardFragment extends ModuleFragment implements OnClickListener {
    private BoardController mController;
    private GridLayout mGridLayout;
    private ImageView mPauseImageView;
    private TextView mTimerTextView;
    private TextView mScoreTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_board_fragment, null);
        mGridLayout = (GridLayout) view.findViewById(id_board_fragment_grid_layout);
        mPauseImageView = (ImageView) view.findViewById(id_board_fragment_pause_image_view);
        mPauseImageView.setOnClickListener(this);
        mTimerTextView = (TextView) view.findViewById(id_board_fragment_timer_text_view);
        mScoreTextView = (TextView) view.findViewById(id_board_fragment_score_text_view);
        mController = new BoardController(this);
        initTimer(30);

        Bundle bundle = getActivity().getIntent().getExtras();
        initQuestion(bundle.getInt(BoardActivity.row), bundle.getInt(BoardActivity.column));


        return view;
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

    private void initTimer(final int duration) {
        mController.initTimer(duration);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case id_board_fragment_pause_image_view:
                mController.pauseTimer();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
}
