package com.fajarsiddiq.berpasangan.module.board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fajarsiddiq.berpasangan.module.ModuleFragment;

import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_score_text_view;
import static com.fajarsiddiq.berpasangan.R.layout.layout_board_fragment;
import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_timer_text_view;
import static com.fajarsiddiq.berpasangan.R.id.id_board_fragment_remaining_text_view;

/**
 * Created by Muhammad Fajar on 28/03/2016.
 */
public class BoardFragment extends ModuleFragment {
    private BoardController mController;
    private TextView mRemainingTextView;
    private TextView mTimerTextView;
    private TextView mScoreTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layout_board_fragment, null);
        mRemainingTextView = (TextView) view.findViewById(id_board_fragment_remaining_text_view);
        mTimerTextView = (TextView) view.findViewById(id_board_fragment_timer_text_view);
        mScoreTextView = (TextView) view.findViewById(id_board_fragment_score_text_view);
        mController = new BoardController(this);
        initTimer(10);
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

    private void initTimer(final int duration) {
        mController.initTimer(duration);
    }
}
