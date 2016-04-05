package com.fajarsiddiq.berpasangan.module.board;

import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;

import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_grid_layout;
import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_score_text_view;
import static com.fajarsiddiq.berpasangan.R.layout.layout_board_fragment;
import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_timer_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_remaining_text_view;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardFragment extends ModuleFragment {
    private BoardController mController;
    private GridLayout mGridLayout;
    private TextView mRemainingTextView;
    private TextView mTimerTextView;
    private TextView mScoreTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_board_fragment, null);
        mGridLayout = (GridLayout) view.findViewById(id_board_fragment_grid_layout);
        mRemainingTextView = (TextView) view.findViewById(id_board_fragment_remaining_text_view);
        mTimerTextView = (TextView) view.findViewById(id_board_fragment_timer_text_view);
        mScoreTextView = (TextView) view.findViewById(id_board_fragment_score_text_view);
        mController = new BoardController(this);
        initTimer(10);

        Bundle bundle = getActivity().getIntent().getExtras();
        initQuestion(bundle.getInt(BoardActivity.row), bundle.getInt(BoardActivity.column));


        return view;
    }

    public TextView getRemainingTextView() {
        return mRemainingTextView;
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

    public void stopTimer() {
        mController.stopTimer();
    }
}
